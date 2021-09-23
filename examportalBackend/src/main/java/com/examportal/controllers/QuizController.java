package com.examportal.controllers;

import java.util.List;

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

import com.examportal.models.Category;
import com.examportal.models.Quiz;
import com.examportal.servicesImpl.QuizServiceImpl;

@RestController
@CrossOrigin("*")
@RequestMapping("/quiz")
public class QuizController {
	
	@Autowired
	private QuizServiceImpl quizServiceImpl;
	
	
	@PostMapping("/")
	public ResponseEntity<Quiz> addQuiz(@RequestBody Quiz quiz) {
		return ResponseEntity.ok(this.quizServiceImpl.addQuiz(quiz));
	}
	
	
	@PutMapping("/")
	public ResponseEntity<Quiz>  updateQuiz(@RequestBody Quiz quiz){
		return ResponseEntity.ok(this.quizServiceImpl.updateQuiz(quiz));
	}
	
	
	@GetMapping("/")
	public ResponseEntity<?>  getQuizes(){
		return ResponseEntity.ok(this.quizServiceImpl.getQuizes());
	}
	
	@GetMapping("/{qId}")
	public ResponseEntity<Quiz> getQuiz(@PathVariable("qId") Long qId) {
		return ResponseEntity.ok(this.quizServiceImpl.getQuiz(qId));
	}
	
	@DeleteMapping("/{qId}")
	public void deleteQuiz(@PathVariable("qId") Long qId) {
		this.quizServiceImpl.deleteQuiz(qId);
	}
	
	@GetMapping("/category/{cid}")
	public List<Quiz> getQuizOfCategory(@PathVariable("cid") Long cid){
		Category category = new Category();
		category.setCid(cid);
		return this.quizServiceImpl.getQuizOfCategory(category);
	}

	
	@GetMapping("/active")
	public List<Quiz> getActiveQuizes(){
		return this.quizServiceImpl.getActiveQuizes();
	}
	
	@GetMapping("/category/active/{cid}")
	public List<Quiz> getActiveQuizOfCategory(@PathVariable("cid") Long cid){
		Category c  = new Category();
		c.setCid(cid);
		return this.quizServiceImpl.getActiveQuizesOfCategory(c);
	}
}
