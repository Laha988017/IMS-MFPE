package com.cts.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.cts.exception.ApiException;
import com.cts.models.entityModels.Category;
import com.cts.repository.CategoyRepository;

@Service
public class CategoryService {

	@Autowired
	private CategoyRepository categoryRepo;

	@Transactional
	public Category createCategory(Category category) throws ApiException {
		if (categoryRepo.findByCategoryName(category.getCategoryName()).isPresent()) {
			throw new ApiException("Category Name Already present");
		}
		return categoryRepo.save(category);
	}

	public Category updateCategory(Category category, int categoryId) throws ApiException {
		if (!(category instanceof Category)) {
			throw new ApiException("Inappropriate request Body");
		}
		Category cat = getCategoryById(categoryId);
		cat.setCategoryName(category.getCategoryName());
		cat.setHsnCode(category.getHsnCode());
		cat.setTaxSlab(category.getTaxSlab());
		return categoryRepo.save(cat);
	}

	public boolean deleteCategory(int categoryId) throws ApiException {
		Category cat = getCategoryById(categoryId);
		if (!cat.getProductList().isEmpty()) {
			throw new ApiException("Category cannot be deleted as Product already exists");
		}
		categoryRepo.delete(cat);
		return !categoryRepo.existsById(categoryId);
	}

	public Category getCategoryById(int categoryId) throws ApiException {
		if (!categoryRepo.existsById(categoryId)) {
			throw new ApiException("Category Id do not exist");
		}
		return categoryRepo.findById(categoryId).get();
	}

	public List<Category> getAllCategoryProductAndProductDetails() {
		return categoryRepo.findAll(Sort.by(Sort.Direction.DESC, "categoryId"));
	}

	public List<Map<String,String>> getAllCategoryByName() {
		List<Category> categorySavedList = categoryRepo.findAll();
		
		List<Map<String,String>> categoryList = new ArrayList<>();
		System.out.println(categorySavedList.size());
		for(Category c: categorySavedList) {
			Map<String,String> categoryMap = new HashMap<>();
			categoryMap.put("categoryId",String.valueOf(c.getCategoryId()));
			categoryMap.put("categoryName",c.getCategoryName());
			categoryMap.put("productSize", String.valueOf(c.getProductList().size()));
			categoryList.add(categoryMap);
		}
		
		return categoryList;
	}

}
