package com.ankit.elearning.controller;

import com.ankit.elearning.dto.SubmitRequest;
import com.ankit.elearning.entity.TestAttempt;
import com.ankit.elearning.security.JwtUtil;
import com.ankit.elearning.service.TestAttemptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/attempts")
public class TestAttemptController {

    @Autowired
    private TestAttemptService attemptService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/start/{testId}")
    public TestAttempt startTest(@PathVariable Long testId,
                                 @RequestHeader("Authorization") String authHeader) {

        String token = authHeader.substring(7);
        String email = jwtUtil.extractEmail(token);

        return attemptService.startTest(testId, email);
    }
    @PostMapping("/submit")
    @PreAuthorize("hasRole('STUDENT')")
    public TestAttempt submitTest(@RequestBody SubmitRequest request) {
        return attemptService.submitTest(request);
    }
}