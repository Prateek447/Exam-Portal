package com.examportal.servicesImpl;

import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examportal.dao.CategoryRepository;
import com.examportal.models.Category;
import com.examportal.services.CategoryService;


@Service
public class CategoryServiceImpl implements CategoryService{

	
	@Autowired
	private CategoryRepository repository;
	
	@Override
	public Category addCategory(Category category) {
		return this.repository.save(category);
	}

	@Override
	public Category updateCategory(Category category) {
		return this.repository.save(category);
	}

	@Override
	public Set<Category> getCategories() {
		return new LinkedHashSet<>(this.repository.findAll());
	}

	@Override
	public Category getCategory(Long categoryId) {
		return this.repository.findById(categoryId).get();
	}

	@Override
	public void deleteCategory(Long categoryId) {
		this.repository.deleteById(categoryId);
	}

	
}
