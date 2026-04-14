package com.ankit.elearning.controller;

import com.ankit.elearning.entity.Question;
import com.ankit.elearning.security.JwtUtil;
import com.ankit.elearning.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/questions")
public class QuestionController {
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private QuestionService questionService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','AUTHOR')")
    public Question createQuestion(@RequestBody Question question,
                                   @RequestHeader("Authorization") String authHeader) {

        String token = authHeader.substring(7);
        String email = jwtUtil.extractEmail(token);

        return questionService.createQuestion(question, email);
    }
}