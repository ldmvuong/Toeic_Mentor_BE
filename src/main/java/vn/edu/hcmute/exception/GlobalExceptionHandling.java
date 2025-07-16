package vn.edu.hcmute.exception;


import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.Instant;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.*;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandling {

    /* ---------- 400 ---------- */
    @ExceptionHandler({ ConstraintViolationException.class,
            MissingServletRequestParameterException.class,
            MethodArgumentNotValidException.class })
    @ResponseStatus(BAD_REQUEST)
    public ErrorResponse handleBadRequest(Exception e, WebRequest req) {
        String message = resolveBadRequestMessage(e);
        return buildErrorResponse(BAD_REQUEST, req, "Bad Request", message);
    }

    /* ---------- 401 ---------- */
    @ExceptionHandler(InternalAuthenticationServiceException.class)
    public ResponseEntity<ErrorResponse> handleUnauthorized(InternalAuthenticationServiceException e,
                                                            WebRequest req) {
        return ResponseEntity.status(UNAUTHORIZED)
                .body(buildErrorResponse(UNAUTHORIZED, req,
                        "Unauthorized",
                        "Username or password is incorrect"));
    }

    /* ---------- 403 ---------- */
    @ExceptionHandler({ ForbiddenException.class, AccessDeniedException.class })
    public ResponseEntity<ErrorResponse> handleForbidden(Exception e, WebRequest req) {
        return ResponseEntity.status(FORBIDDEN)
                .body(buildErrorResponse(FORBIDDEN, req, "Forbidden", e.getMessage()));
    }

    /* ---------- 404 ---------- */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(ResourceNotFoundException e, WebRequest req) {
        return ResponseEntity.status(NOT_FOUND)
                .body(buildErrorResponse(NOT_FOUND, req, "Not Found", e.getMessage()));
    }

    /* ---------- 409 ---------- */
    @ExceptionHandler(InvalidDataException.class)
    public ResponseEntity<ErrorResponse> handleConflict(InvalidDataException e, WebRequest req) {
        return ResponseEntity.status(CONFLICT)
                .body(buildErrorResponse(CONFLICT, req, "Conflict", e.getMessage()));
    }

    /* ---------- 500 fallback ---------- */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleAll(Exception e, WebRequest req) {
        log.error("Unexpected error", e);
        return ResponseEntity.status(INTERNAL_SERVER_ERROR)
                .body(buildErrorResponse(INTERNAL_SERVER_ERROR, req,
                        "Internal Server Error",
                        "Unexpected error, please try again later"));
    }

    /* ---------- Helpers ---------- */
    private ErrorResponse buildErrorResponse(HttpStatus status, WebRequest req,
                                             String error, String message) {
        return new ErrorResponse(Instant.now(),
                status.value(),
                req.getDescription(false).replace("uri=", ""),
                error,
                message);
    }

    private String resolveBadRequestMessage(Exception e) {
        if (e instanceof MethodArgumentNotValidException ex) {
            return ex.getBindingResult().getFieldErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.joining("; "));
        }
        if (e instanceof ConstraintViolationException ex) {
            return ex.getConstraintViolations()
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.joining("; "));
        }
        return e.getMessage();
    }

    public record ErrorResponse(Instant timestamp,
                                int status,
                                String path,
                                String error,
                                String message) {}
}