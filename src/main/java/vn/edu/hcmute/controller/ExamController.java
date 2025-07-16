package vn.edu.hcmute.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vn.edu.hcmute.dto.response.ApiResponse;
import vn.edu.hcmute.service.IExamService;

@RestController
@RequestMapping("/api/v1/exams")
@Tag(name = "Exam Controller")
@Slf4j(topic = "EXAM-CONTROLLER")
@RequiredArgsConstructor
public class ExamController {

    private final IExamService examService;


    @Operation(summary = "Add exam", description = "Upload an exam from an Excel file")
    @PostMapping()
    public ApiResponse addExam(@RequestParam("file") MultipartFile file) throws Exception {
        log.info("Received file upload request for exam");

        examService.saveExamFromExcel(file);

        return ApiResponse.builder()
                .status(HttpStatus.CREATED.value())
                .message("Exam uploaded successfully")
                .data(null)
                .build();
    }
}
