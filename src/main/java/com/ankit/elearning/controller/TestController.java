package com.ankit.elearning.controller;

import com.ankit.elearning.entity.Test;
import com.ankit.elearning.security.JwtUtil;
import com.ankit.elearning.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tests")
public class TestController {

    @Autowired
    private TestService testService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','AUTHOR')")
    public Test createTest(@RequestBody Test test,
                           @RequestHeader("Authorization") String authHeader) {

        String token = authHeader.substring(7);
        String email = jwtUtil.extractEmail(token);

        return testService.createTest(test, email);
    }
}