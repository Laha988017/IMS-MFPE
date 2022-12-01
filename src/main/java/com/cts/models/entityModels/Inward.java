package com.cts.models.entityModels;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Component
@Entity
@Table(name = "inwards")
public class Inward {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;

	@Column(name = "invoice_id", unique = true)
	private int invoiceId;

	@Column(name = "invoice_date")
	private Date invoiceDate;

	@Column(name = "inward_date")
	private Date inwardDate;

	@Column(name = "total_price")
	private double totalPrice;

	@OneToMany(targetEntity = Product.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "inward_id")
	private Set<Product> productList;

	@ManyToOne(fetch = FetchType.LAZY)
	private Vendor vendor;

	@ManyToOne(fetch = FetchType.LAZY)
	private User user;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(int invoiceId) {
		this.invoiceId = invoiceId;
	}

	public Date getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public Date getInwardDate() {
		return inwardDate;
	}

	public void setInwardDate(Date inwardDate) {
		this.inwardDate = inwardDate;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	@JsonManagedReference(value = "product-inward")
	public Set<Product> getProductList() {
		return productList;
	}

	public void setProductList(Set<Product> productList) {
		this.productList = productList;
	}

	public void addProductToProductList(Product p) {
		this.productList.add(p);
	}

	@JsonBackReference(value = "vendor-inward")
	public Vendor getVendor() {
		return vendor;
	}

	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}

	@JsonBackReference(value = "user-inward")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
