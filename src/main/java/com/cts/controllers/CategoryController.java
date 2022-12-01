package com.cts.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cts.exception.ApiException;
import com.cts.models.entityModels.Category;
import com.cts.models.responseModels.ApiResponse;
import com.cts.services.CategoryService;

@RestController
public class CategoryController {

	@Autowired
	CategoryService categoryService;

	@PostMapping("/category/create")
	public ResponseEntity<?> createCategory(@RequestBody Category category) throws ApiException {
		Category savedCategory = categoryService.createCategory(category);
		return ResponseEntity.ok(new ApiResponse(200, "Categories Created", savedCategory));
	}

	@PutMapping("/category/update/{categoryId}")
	public ResponseEntity<?> updateCategory(@RequestBody Category category, @PathVariable int categoryId)
			throws ApiException {
		Category savedCategory = categoryService.updateCategory(category, categoryId);
		return ResponseEntity.ok(new ApiResponse(200, "Categories Updated", savedCategory));
	}

	@DeleteMapping("/category/delete/{categoryId}")
	public ResponseEntity<?> deleteCategory(@PathVariable int categoryId) throws ApiException {
		if (categoryService.deleteCategory(categoryId)) {
			return ResponseEntity.ok(new ApiResponse(200, "Category Deleted"));
		}
		return ResponseEntity.ok(new ApiResponse(400, "Category cannot be deleted"));
	}

	@GetMapping("/category/view/{categoryId}")
	public ResponseEntity<?> getCategoryById(@PathVariable int categoryId) throws ApiException {
		Category allCategories = categoryService.getCategoryById(categoryId);
		return ResponseEntity.ok(new ApiResponse(200, "Category fetched", allCategories));
	}

	@GetMapping("/category/viewAll")
	public ResponseEntity<?> getAllCategoryProductAndProductDetails() {
		List<Category> allCategories = categoryService.getAllCategoryProductAndProductDetails();
		if (allCategories.isEmpty()) {
			return ResponseEntity.ok(new ApiResponse(HttpStatus.NOT_FOUND.value(), "No Categories Added"));
		}
		return ResponseEntity.ok(new ApiResponse(200, "Category Wise Product Details", allCategories));
	}

	@GetMapping("/category/view/name")
	public ResponseEntity<?> getAllCategoryByName() {
		List<Map<String, String>> allCategories = categoryService.getAllCategoryByName();
		if (allCategories.isEmpty()) {
			return ResponseEntity.ok(new ApiResponse(HttpStatus.NOT_FOUND.value(), "No Categories Added", null));
		}
		return ResponseEntity.ok(new ApiResponse(200, "Category Names", allCategories));
	}

}
