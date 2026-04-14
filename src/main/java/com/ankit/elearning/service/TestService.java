package com.ankit.elearning.service;

import com.ankit.elearning.entity.Test;
import com.ankit.elearning.entity.User;
import com.ankit.elearning.repository.TestRepository;
import com.ankit.elearning.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestService {

    @Autowired
    private TestRepository testRepository;

    @Autowired
    private UserRepository userRepository;

    public Test createTest(Test test, String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        test.setCreatedBy(user);

        return testRepository.save(test);
    }
}