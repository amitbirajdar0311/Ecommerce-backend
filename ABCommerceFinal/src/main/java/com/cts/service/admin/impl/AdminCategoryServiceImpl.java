package com.cts.service.admin.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.dto.CategoryDto;
import com.cts.entity.Category;
import com.cts.repository.CategoryRepository;
import com.cts.service.admin.CategoryService;

@Service
public class AdminCategoryServiceImpl implements CategoryService{


	@Autowired
	private  CategoryRepository categoryRepository;
	
	public Category createCategory(CategoryDto categoryDto) {
		Category category = new Category();
		category.setName(categoryDto.getName());
		category.setDescription(categoryDto.getDescription());
		return categoryRepository.save(category);
	}
	
	public List<Category> getAllCategory(){
		return categoryRepository.findAll();
	}
}
