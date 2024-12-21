package com.deepak.quizapplication.dao;

import com.deepak.quizapplication.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizDao extends JpaRepository<Quiz, Integer> {
}
