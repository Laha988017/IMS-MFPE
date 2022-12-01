package com.cts.models.entityModels;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Component
@Entity
@Table(name = "categories")
public class Category {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int categoryId;

	@Column(name = "cat_name", unique = true, nullable = true)
	@NotNull(message = "Category name must not be null")
	@NotEmpty(message = "Category name must not be empty")
	@NotBlank(message = "Category name must not be blank")
	@Size(min = 4, max = 80, message = "Category name must be in between 4 and 80 character")
	private String categoryName;

	@Column(name = "hsn_code")
	@NotNull(message = "HSN Code must not be null")
	@NotEmpty(message = "HSN Code must not be empty")
	@NotBlank(message = "HSN Code must not be blank")
	@Size(min = 4, max = 15, message = "HSN Code must be in between 4 and 15 character")
	private String hsnCode;

	@Column(name = "tax_slab")
	private double taxSlab;

	public int getCategoryId() {
		return categoryId;
	}

	@OneToMany(targetEntity = Product.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "category_id")
	private Set<Product> productList;

	@JsonManagedReference
	public Set<Product> getProductList() {
		return productList;
	}

	public void setProductList(Set<Product> productList) {
		this.productList = productList;
	}

	public void addProductList(Product product) {
		productList.add(product);
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getHsnCode() {
		return hsnCode;
	}

	public void setHsnCode(String hsnCode) {
		this.hsnCode = hsnCode;
	}

	public double getTaxSlab() {
		return taxSlab;
	}

	public void setTaxSlab(double taxSlab) {
		this.taxSlab = taxSlab;
	}

}
