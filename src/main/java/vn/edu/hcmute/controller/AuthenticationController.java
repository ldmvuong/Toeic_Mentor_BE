package vn.edu.hcmute.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@Tag(name = "Authentication Controller")
@Slf4j(topic = "AUTHENTICATION-CONTROLLER")
@RequiredArgsConstructor
public class AuthenticationController {
}
