package com.quizApp.quiz_service.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quizApp.quiz_service.Entities.Quiz;

public interface QuizDao extends JpaRepository<Quiz, Integer>{

}
