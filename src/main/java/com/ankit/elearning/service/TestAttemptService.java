package com.ankit.elearning.service;

import com.ankit.elearning.dto.SubmitRequest;
import com.ankit.elearning.entity.*;
import com.ankit.elearning.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TestAttemptService {

    @Autowired
    private TestAttemptRepository attemptRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TestRepository testRepository;

    public TestAttempt startTest(Long testId, String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Test test = testRepository.findById(testId)
                .orElseThrow(() -> new RuntimeException("Test not found"));

        TestAttempt attempt = new TestAttempt();
        attempt.setUser(user);
        attempt.setTest(test);
        attempt.setStartTime(LocalDateTime.now());

        return attemptRepository.save(attempt);
    }
    public TestAttempt submitTest(SubmitRequest request) {

        TestAttempt attempt = attemptRepository.findById(request.getAttemptId())
                .orElseThrow(() -> new RuntimeException("Attempt not found"));

        int score = 0;

        for (Question q : attempt.getTest().getQuestions()) {

            String correct = q.getCorrectAnswer();
            String userAnswer = request.getAnswers().get(q.getId());

            if (correct != null && correct.equals(userAnswer)) {
                score++;
            }
        }

        attempt.setScore(score);
        attempt.setEndTime(LocalDateTime.now());

        return attemptRepository.save(attempt);
    }
}