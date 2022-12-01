package com.cts.controllers;

import java.util.List;
import java.util.Map;

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
import com.cts.models.entityModels.Vendor;
import com.cts.models.responseModels.ApiResponse;
import com.cts.services.VendorService;

@RestController
public class VendorController {

	@Autowired
	private VendorService vendorService;

	@PostMapping("/vendor/create")
	public ResponseEntity<?> createVendor(@RequestBody @Valid Vendor vendor) throws ApiException {
		Vendor savedVendor = vendorService.createVendor(vendor);
		// Also add the user id @Dipanjan
		return ResponseEntity.ok(new ApiResponse(200, "Vendor Created", savedVendor));
	}

	@GetMapping("/vendor/viewAll")
	public ResponseEntity<?> viewAllVendor() {
		List<Vendor> viewAllVendors = vendorService.viewAllVendors();
		if (viewAllVendors.isEmpty()) {
			return ResponseEntity.ok(new ApiResponse(200, "Vendor is Empty"));
		}
		return ResponseEntity.ok(new ApiResponse(200, "Vendor Details", viewAllVendors));
	}

	@GetMapping("/vendor/view/{vendorId}")
	public ResponseEntity<?> viewVendorById(@PathVariable int vendorId) throws ApiException {
		Vendor viewById = vendorService.viewById(vendorId);
		return ResponseEntity.ok(new ApiResponse(200, "Vendor Created", viewById));
	}

	@GetMapping("/vendor/view/name")
	public ResponseEntity<?> getAllCategoryByName() {
		List<Map<String, String>> allvendor = vendorService.getAllVendorByName();
		if (allvendor.isEmpty()) {
			return ResponseEntity.ok(new ApiResponse(HttpStatus.NOT_FOUND.value(), "No Vendor Added", null));
		}
		return ResponseEntity.ok(new ApiResponse(200, "Vendor Names", allvendor));
	}

	@PutMapping("/vendor/update/{vendorId}")
	public ResponseEntity<?> updateVendor(@RequestBody @Valid Vendor vendor, @PathVariable int vendorId)
			throws ApiException {
		Vendor updatedVendor = vendorService.updateVendor(vendor, vendorId);
		return ResponseEntity.ok(new ApiResponse(HttpStatus.OK.value(), "Vendor updated successfully", updatedVendor));
	}

	@DeleteMapping("/vendor/delete/{vendorId}")
	public ResponseEntity<?> deleteVendorName(@PathVariable int vendorId) throws ApiException {
		boolean updatedVendor = vendorService.deleteVendor(vendorId);
		return ResponseEntity.ok(new ApiResponse(HttpStatus.OK.value(), "Vendor updated successfully", updatedVendor));
	}

}
