package com.examportal.dao;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.examportal.models.Question;
import com.examportal.models.Quiz;

public interface QuestionsRepository extends JpaRepository<Question, Long>{

   public Set<Question> findByQuiz(Quiz quiz);

}
