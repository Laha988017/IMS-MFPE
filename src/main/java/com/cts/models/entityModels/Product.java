package com.cts.models.entityModels;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Component
@Entity
@Table(name = "products")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int productId;

	@Column(name = "prod_name")
	@NotNull(message = "Product name must not be null")
	@NotEmpty(message = "Product name must not be empty")
	@NotBlank(message = "Product name must not be blank")
	@Size(min = 4, max = 100, message = "Product name must be in between 4 to 100 character")
	private String productName;

	@Column(name = "batch_no", unique = true)
	@NotNull(message = "Batch no can't be null")
	@NotEmpty(message = "Batch no can't be empty")
	@NotBlank(message = "Batch no can't be blank")
	private String batchNo;

	@Column(name = "quantity")
	private double quantity;

	@Column(name = "cost_price")
	private double costPrice;

	@Column(name = "max_retail_price")
	private double maxRetailPrice;

	@Column(name = "discount")
	private double discount;

	@Column(name = "mfd")
	private Date mfd;

	@Column(name = "is_perishible")
	private boolean perishible;

	@Column(name = "expiry_date")
	private Date expiryDate;

	@ManyToOne(fetch = FetchType.LAZY)
	private Category category;

	@ManyToOne(fetch = FetchType.LAZY)
	private Inward inward;

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	public double getCostPrice() {
		return costPrice;
	}

	public void setCostPrice(double costPrice) {
		this.costPrice = costPrice;
	}

	public double getMaxRetailPrice() {
		return maxRetailPrice;
	}

	public void setMaxRetailPrice(double maxRetailPrice) {
		this.maxRetailPrice = maxRetailPrice;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public Date getMfd() {
		return mfd;
	}

	public void setMfd(Date mfd) {
		this.mfd = mfd;
	}

	public boolean isPerishible() {
		return perishible;
	}

	public void setPerishible(boolean perishible) {
		this.perishible = perishible;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	@JsonBackReference
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@JsonBackReference(value = "product-inward")
	public Inward getInward() {
		return inward;
	}

	public void setInward(Inward inward) {
		this.inward = inward;
	}

}
