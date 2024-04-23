package com.cts.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.dto.CategoryDto;
import com.cts.entity.Category;
import com.cts.service.admin.CategoryService;

import lombok.extern.slf4j.Slf4j;


@RestController
@RequestMapping("/api/admin")
@Slf4j
public class AdminCategoryController {
	@Autowired
	private  CategoryService categoryService;
	
	
	@PostMapping("/categories")
	public ResponseEntity<Category> createCategory(@RequestBody CategoryDto categoryDto){
		Category category = categoryService.createCategory(categoryDto);
		log.info("Add the category{} ",category);
		return ResponseEntity.status(HttpStatus.CREATED).body(category);
	}
	
	@GetMapping("/categories")
	public ResponseEntity<List<Category>> getAllCategory() {
		log.info("Get all categorites ");
		return ResponseEntity.ok(categoryService.getAllCategory());
	}
	
}
