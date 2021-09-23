package com.examportal.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examportal.models.Question;
import com.examportal.models.Quiz;
import com.examportal.servicesImpl.QuestionServiceImpl;
import com.examportal.servicesImpl.QuizServiceImpl;

@RestController
@CrossOrigin("*")
@RequestMapping("/question")
public class QuestionController {
	
	
	@Autowired
	private QuestionServiceImpl questionServiceImpl;
	
	@Autowired
	private QuizServiceImpl quizServiceImpl;
	
	@PostMapping("/")
	 public ResponseEntity<Question> addQuestion(@RequestBody Question question){
		 return ResponseEntity.ok(this.questionServiceImpl.addQuestion(question));
	 }
	
	
	@PutMapping("/")
	public ResponseEntity<Question> updateQuestion(@RequestBody Question question){
		return ResponseEntity.ok(this.questionServiceImpl.updateQuestion(question));
	}
	
	@GetMapping("/")
	public ResponseEntity<?> getQuestions(){
		return ResponseEntity.ok(this.questionServiceImpl.getQuestions());
	}
	
	@GetMapping("/size")
	public Long getTotalQuestinsSize() {
		return (long) this.questionServiceImpl.getQuestions().size();
	}
	
	
	@GetMapping("/{questionId}")
	public Question getQuestion(@PathVariable("questionId") Long questionId) {
		return this.questionServiceImpl.getQuestion(questionId);
	}
	
	@GetMapping("/quiz/{qId}")
	public ResponseEntity<?> getQuestionOfQuiz(@PathVariable("qId") Long qId){
		Quiz quiz = this.quizServiceImpl.getQuiz(qId);
		Set<Question> question = quiz.getQuestion();
		List<Question> list =  new ArrayList<>(question);
		if(list.size() > Integer.parseInt(quiz.getNumberOfQuestions())) {
			list =  list.subList(0, Integer.parseInt(quiz.getNumberOfQuestions())+1);
		}
		
		// because want dont want to send the answer to the user
		list.forEach((q)->{
			q.setAnswer("");
		});
		
		Collections.shuffle(list);
		return ResponseEntity.ok(list);
	}
	
	//for admin
	@GetMapping("/quiz/all/{qId}")
	public ResponseEntity<?> getAllQuestionOfQuiz(@PathVariable("qId") Long qId){
		Quiz quiz = this.quizServiceImpl.getQuiz(qId);
		Set<Question> question = quiz.getQuestion();
		List list =  new ArrayList<>(question);
		return ResponseEntity.ok(list);
	}
	
	
	@DeleteMapping("/{questionId}")
	public void deleteQuestion(@PathVariable("questionId") Long questionId) {
		 this.questionServiceImpl.deleteQuestion(questionId);
	}
	
	
	@PutMapping("/eval-quiz")
	public ResponseEntity<?> evalQUiz(@RequestBody List<Question> questions) {
		
		Integer marksGot  = 0;
		  Integer correctAnswer = 0;
		 Integer  attempted = 0;
		
		 Integer oneQuesMarks = Integer.parseInt(questions.get(0).getQuiz().getMaxMarks())/ questions.size();
		 
		for(Question q : questions){
			Question question = this.questionServiceImpl.getQuestion(q.getQid());
			if(question.getAnswer().equals(q.getGivenAnswer())) {
				correctAnswer++;
				marksGot += oneQuesMarks;
			}
			
			if((q.getGivenAnswer() != null) && (!q.getGivenAnswer().equals(""))) {
				System.out.println("Given answer-->"+q.getGivenAnswer());
				attempted++;
			}
			
		}
		Map<String, Integer> map = Map.of("marksGot", marksGot, "correctAnswer", correctAnswer, "attempted", attempted);
		
		return ResponseEntity.ok(map);
		
	}

}
