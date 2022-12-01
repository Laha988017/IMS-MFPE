package com.cts.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.models.entityModels.Category;

public interface CategoyRepository extends JpaRepository<Category, Integer> {

	Optional<Category> findByCategoryName(String categoryName);

}
