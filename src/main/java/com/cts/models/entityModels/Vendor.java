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
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Component
@Entity
@Table(name = "vendors")
public class Vendor {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", unique = true)
	private int id;

	@NotNull(message = "Vendor name must not be null")
	@NotEmpty(message = "Vendor name must not be empty")
	@NotBlank(message = "Vendor name must not be blank")
	@Size(min = 4, max = 100, message = "Vendor name must be in between 4 to 100 character")
	@Column(name = "vendor_name")
	private String vendorName;

	@Pattern(regexp = "[0-9]{2}[a-zA-Z]{5}[0-9]{4}[a-zA-Z]{1}[0-9]{1}[a-zA-Z]{1}[0-9]{1}", message = "Invalid GST No.")
	@NotNull(message = "GST No. must not be null")
	@NotEmpty(message = "GST No. must not be empty")
	@NotBlank(message = "GST No. must not be blank")
	@Column(name = "gst_no", unique = true)
	private String gstNo;

	@Size(min = 4, max = 100, message = "Vendor address must be in between 4 to 100 character")
	@NotNull(message = "Address must not be null")
	@NotEmpty(message = "Address must not be empty")
	@NotBlank(message = "Address must not be blank")
	@Column(name = "address")
	private String address;

	@OneToMany(targetEntity = Inward.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "vendor_id")
	private Set<Inward> inwards;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public String getGstNo() {
		return gstNo;
	}

	public void setGstNo(String gstNo) {
		this.gstNo = gstNo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@JsonManagedReference(value = "vendor-inward")
	public Set<Inward> getInwards() {
		return inwards;
	}

	public void setInwards(Set<Inward> inward) {
		this.inwards = inward;
	}

	public void addInward(Inward inward) {
		this.inwards.add(inward);
	}

}
