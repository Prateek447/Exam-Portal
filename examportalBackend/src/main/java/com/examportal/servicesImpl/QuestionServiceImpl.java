package com.examportal.servicesImpl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examportal.dao.QuestionsRepository;
import com.examportal.models.Question;
import com.examportal.models.Quiz;
import com.examportal.services.QuestionService;


@Service
public class QuestionServiceImpl implements QuestionService {

	@Autowired
	private QuestionsRepository questionsRepository;
	
	
	
	@Override
	public Question addQuestion(Question question) {
		return this.questionsRepository.save(question);
	}

	@Override
	public Question updateQuestion(Question questin) {
		return this.questionsRepository.save(questin);
	}

	@Override
	public Set<Question> getQuestions() {
		return new HashSet<>(this.questionsRepository.findAll());
	}

	@Override
	public Question getQuestion(Long quetionId) {
		return this.questionsRepository.findById(quetionId).get();
	}

	@Override
	public Set<Question> getQuestionOfQuiz(Quiz quiz) {
		return this.questionsRepository.findByQuiz(quiz);
	}

	@Override
	public void deleteQuestion(Long qId) {
		this.questionsRepository.deleteById(qId);
	}

}
