package com.ankit.elearning.service;

import com.ankit.elearning.entity.Question;
import com.ankit.elearning.entity.User;
import com.ankit.elearning.repository.QuestionRepository;
import com.ankit.elearning.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private QuestionRepository questionRepository;

    public Question createQuestion(Question question, String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        question.setCreatedBy(user);

        return questionRepository.save(question);
    }
}