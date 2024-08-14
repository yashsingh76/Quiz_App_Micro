package com.quizApp.quiz_service.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.bouncycastle.util.Integers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.quizApp.quiz_service.Dao.QuizDao;
import com.quizApp.quiz_service.Entities.QuestionWrapper;
import com.quizApp.quiz_service.Entities.Quiz;
import com.quizApp.quiz_service.Entities.Response;
import com.quizApp.quiz_service.feign.QuizInterface;

@Service
public class QuizService {

	@Autowired
	private QuizDao quizDao;

	@Autowired
	private QuizInterface quizInterface;

	public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
		List<Integer> question = quizInterface.getQuestionsForQuiz(category, numQ).getBody();
		Quiz quiz = new Quiz();
		quiz.setTitle(title);
		quiz.setQuestionIds(question);
		quizDao.save(quiz);
		return new ResponseEntity<>("Success", HttpStatus.CREATED);
	}

	public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
		Quiz quiz = quizDao.findById(id).get();
		List<Integer> questionIds = quiz.getQuestionIds();
		ResponseEntity<List<QuestionWrapper>> questions = quizInterface.getQuestionsFromId(questionIds);
		return questions;
	}

	public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
		ResponseEntity<Integer> score = quizInterface.getScore(responses);
		return score;
	}
}
