package com.cts.service.admin;

import java.util.List;

import com.cts.dto.CategoryDto;
import com.cts.entity.Category;



public interface CategoryService {

	Category createCategory(CategoryDto categoryDto);
	 List<Category> getAllCategory();
}
