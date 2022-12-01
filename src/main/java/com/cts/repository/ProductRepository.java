package com.cts.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.models.entityModels.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

	Optional<Product> findByProductName(String productname);

	Optional<Product> findByBatchNo(String batchNo);

}
