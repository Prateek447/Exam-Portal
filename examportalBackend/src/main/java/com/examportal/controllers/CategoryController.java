package com.examportal.controllers;

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
import com.examportal.servicesImpl.CategoryServiceImpl;

@RestController
@CrossOrigin("*")
@RequestMapping("/category")
public class CategoryController {
	
	
	@Autowired
	private CategoryServiceImpl categoryServiceImpl;
	
	@PostMapping("/")
	public ResponseEntity<Category> addCategory(@RequestBody Category category){
		return ResponseEntity.ok(this.categoryServiceImpl.addCategory(category));
	}

	
	@GetMapping("/{categoryId}")
	public Category getCategory(@PathVariable("categoryId") Long categoryId ) {
		return this.categoryServiceImpl.getCategory(categoryId);
		
	}
	
	
	@GetMapping("/")
	public ResponseEntity<?> getCategories(){
		return   ResponseEntity.ok( this.categoryServiceImpl.getCategories());
	}
	
	
	@PutMapping("/")
	public Category updateCategory(@RequestBody Category category) {
		return this.categoryServiceImpl.updateCategory(category);
	}
	
	@DeleteMapping("/{categoryId}")
	public void deleteCategory(@PathVariable("categoryId") Long categoryId) {
		this.categoryServiceImpl.deleteCategory(categoryId);
	}
}
