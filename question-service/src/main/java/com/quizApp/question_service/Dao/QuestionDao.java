package com.quizApp.question_service.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.quizApp.question_service.Entities.Question;

@Repository
public interface QuestionDao extends JpaRepository<Question, Integer>{

	List<Question> findByCategory(String category);

	@Query(value = "SELECT id FROM question WHERE category= ? ORDER BY RAND() LIMIT ?", nativeQuery = true)
	List<Integer> findRandomQuestionByCategory(String category, int numQ);
}
