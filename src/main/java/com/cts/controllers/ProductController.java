package com.cts.controllers;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.Valid;

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
import com.cts.models.entityModels.Product;
import com.cts.models.responseModels.ApiResponse;
import com.cts.services.ProductService;

@RestController
public class ProductController {

	@Autowired
	private ProductService productService;

	@PostMapping("/product/create-without-category")
	public ResponseEntity<?> createProductBlank(@RequestBody @Valid Product product) throws ApiException {
		Product addProduct = productService.addProduct(product);
		return ResponseEntity.ok(new ApiResponse(HttpStatus.OK.value(), "Product Created", addProduct));
	}

	@PutMapping("/product/add/product-with-category/{categoryId}")
	public ResponseEntity<?> createProductWithCategoryId(@RequestBody @Valid Product product,
			@PathVariable int categoryId) throws ApiException {
		Category addProductWithAddedCategory = productService.addProductWithAddedCategory(product, categoryId);
		if (addProductWithAddedCategory != null)
			return ResponseEntity
					.ok(new ApiResponse(HttpStatus.OK.value(), "Product Created", addProductWithAddedCategory));
		return ResponseEntity.ok(new ApiResponse(HttpStatus.BAD_REQUEST.value(), "Product Not Created", null));
	}

	@PutMapping("/product/update/{productId}")
	public ResponseEntity<?> updateProductName(@RequestBody @Valid Product product, @PathVariable int productId)
			throws ApiException {
		Product updatedProduct = productService.updateProductName(product, productId);
		return ResponseEntity
				.ok(new ApiResponse(HttpStatus.OK.value(), "Product updated successfully", updatedProduct));
	}

	@DeleteMapping("/product/delete/{productId}")
	public ResponseEntity<?> deleteProductName(@PathVariable int productId) throws ApiException {
		boolean updatedProduct = productService.deleteProduct(productId);
		return ResponseEntity
				.ok(new ApiResponse(HttpStatus.OK.value(), "Product updated successfully", updatedProduct));
	}

	@GetMapping("/product/viewAll")
	public ResponseEntity<?> viewAllProduct() {
		List<Product> viewAllProducts = productService.viewAllProducts();
		if (viewAllProducts.isEmpty()) {
			return ResponseEntity
					.ok(new ApiResponse(HttpStatus.NOT_FOUND.value(), "No Products Added", viewAllProducts));
		}
		return ResponseEntity
				.ok(new ApiResponse(HttpStatus.OK.value(), "Product fetched successfully", viewAllProducts));
	}

	@GetMapping("/product/view/{productId}")
	public ResponseEntity<?> viewAllProduct(@PathVariable int productId) throws ApiException {
		Product p = productService.getProductByProductId(productId);
		return ResponseEntity.ok(new ApiResponse(HttpStatus.OK.value(), "Product fetched successfully", p));
	}

	@GetMapping("/product/view/stock")
	public ResponseEntity<?> viewStock() throws ApiException {
		Set<Map<String, String>> p = productService.getStockBasedOnName();
		return ResponseEntity.ok(new ApiResponse(HttpStatus.OK.value(), "Product fetched successfully", p));
	}

	@GetMapping("/product/view/name")
	public ResponseEntity<?> viewAllProductNames() {
		List<String> viewAllProducts = productService.viewAllProductNames();
		if (viewAllProducts.isEmpty()) {
			return ResponseEntity
					.ok(new ApiResponse(HttpStatus.NOT_FOUND.value(), "No Products Added", viewAllProducts));
		}
		return ResponseEntity
				.ok(new ApiResponse(HttpStatus.OK.value(), "Product fetched successfully", viewAllProducts));
	}

	@GetMapping("/product/whereInwardIsEmpty")
	public ResponseEntity<?> getProductWhereInwardIsEmpty() {
		List<Product> pList = productService.getProductWhereInwardIsEmpty();
		return ResponseEntity.ok(new ApiResponse(HttpStatus.OK.value(), "Product not attached to any inward", pList));
	}

}
