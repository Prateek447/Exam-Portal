package com.examportal.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.examportal.models.Category;
import com.examportal.models.Question;
import com.examportal.models.Quiz;

public interface QuizRepository extends JpaRepository<Quiz, Long> {

	public List<Quiz> findBycategory(Category category);
	
	public List<Quiz>  findByActive(boolean b);
	
	public List<Quiz> findByCategoryAndActive(Category c,boolean b);
}
