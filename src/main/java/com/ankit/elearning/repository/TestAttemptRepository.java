package com.ankit.elearning.repository;

import com.ankit.elearning.entity.TestAttempt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestAttemptRepository extends JpaRepository<TestAttempt, Long> {
}