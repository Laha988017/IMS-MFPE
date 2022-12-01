package com.cts.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.cts.exception.ApiException;
import com.cts.models.entityModels.Category;
import com.cts.models.entityModels.Product;
import com.cts.repository.CategoyRepository;
import com.cts.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	ProductRepository prodRepo;

	@Autowired
	CategoyRepository categoryRepo;

	@Transactional
	public Category addProductWithAddedCategory(Product product, int categoryId) throws ApiException {
		if (!categoryRepo.findById(categoryId).isPresent()) {
			throw new ApiException("Category Id not found");
		}
		if (prodRepo.findByBatchNo(product.getBatchNo()).isPresent()) {
			throw new ApiException("Batch No already exists");

		}
		if (!(product instanceof Product)) {
			throw new ApiException("Incorrect Product Details");
		}
		Category category = categoryRepo.findById(categoryId).get();
		product.setCostPrice(product.getMaxRetailPrice() - product.getDiscount() * product.getMaxRetailPrice());

		category.addProductList(product);
		// addProduct(product);
		Category categorySaved = null;
		try {
			categorySaved = categoryRepo.save(category);
		} catch (Exception e) {
			throw new ApiException(e.getCause().getMessage());
		}
		return categorySaved;
	}

	public Product addProduct(Product product) throws ApiException {
		if (prodRepo.findByBatchNo(product.getBatchNo()).isPresent()) {
			throw new ApiException("Batch No already exists");
		}
		if (!(product instanceof Product)) {
			throw new ApiException("Incorrect Product Details");
		}
		product.setCostPrice(product.getMaxRetailPrice() - product.getDiscount() * product.getMaxRetailPrice());
		Product p = prodRepo.save(product);
		Product pSaved = prodRepo.findById(p.getProductId()).get();
		return pSaved;
	}

	public Product updateProductName(Product product, int productId) throws ApiException {
		Product p = getProductByProductId(productId);
		if (!(product instanceof Product)) {
			throw new ApiException("Invalid request body");
		}
		p.setProductName(product.getProductName());

		return prodRepo.save(p);
	}

	public boolean deleteProduct(int productId) throws ApiException {
		Product p = getProductByProductId(productId);
		prodRepo.delete(p);
		return !prodRepo.existsById(productId);
	}

	public Product getProductByProductId(int productId) throws ApiException {
		if (!prodRepo.existsById(productId)) {
			throw new ApiException("Product Id donot exists");
		}
		return prodRepo.findById(productId).get();
	}

	public Set<Map<String, String>> getStockBasedOnName() {
		List<Product> product = viewAllProducts();
		Set<Map<String, String>> mapList = new HashSet<Map<String,String>>();
		for(Product p:product) {
			Map<String, String> res = new TreeMap<String, String>();
			if(res.containsKey(p.getProductName())) {
				res.put("quantity",String.valueOf(p.getQuantity() + Integer.parseInt(res.get(p.getProductName()))));
			}
			else {
				res.put("productName",p.getProductName());
				res.put("quantity", String.valueOf(p.getQuantity()));
			}
			mapList.add(res);
		}
		return mapList;
	}

	public List<Product> viewAllProducts() {
		return prodRepo.findAll(Sort.by(Sort.Direction.DESC, "productId"));
	}

	public List<String> viewAllProductNames() {
		List<Product> productList = prodRepo.findAll();
		return productList.stream().filter(e -> e.getProductName() != null).map(e -> e.getProductName())
				.collect(Collectors.toList());
	}

	public boolean checkIfBatchNoExists(String batchNo) {
		return prodRepo.findByBatchNo(batchNo).isPresent();

	}

	public List<Product> getProductWhereInwardIsEmpty() {
		List<Product> resList = new ArrayList<Product>();
		List<Product> pList = prodRepo.findAll();
		for (Product p : pList) {
			if (p.getInward() == null) {
				resList.add(p);
			}
		}
		return resList;
	}

}
