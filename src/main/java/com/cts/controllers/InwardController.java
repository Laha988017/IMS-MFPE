package com.cts.controllers;

import java.util.List;

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
import com.cts.models.entityModels.Inward;
import com.cts.models.responseModels.ApiResponse;
import com.cts.services.InwardService;
import com.cts.services.LinkerService;

@RestController
public class InwardController {

	@Autowired
	private InwardService inwardService;

	@Autowired
	private LinkerService linkService;

	@PostMapping("/inward/create")
	public ResponseEntity<?> createInward(@RequestBody @Valid Inward inward) throws ApiException {
		Inward savedInward = inwardService.createInward(inward);
		return ResponseEntity.ok(new ApiResponse(200, "Inward Created", savedInward));
	}

	@GetMapping("/inward/viewAll")
	public ResponseEntity<?> viewAllInward() {
		List<Inward> viewAllInwards = inwardService.viewAllInwards();
		if (viewAllInwards.isEmpty()) {
			return ResponseEntity.ok(new ApiResponse(200, "Inward is Empty"));
		}
		return ResponseEntity.ok(new ApiResponse(200, "Inward Details", viewAllInwards));
	}

	@GetMapping("/inward/view/{inwardId}")
	public ResponseEntity<?> viewInwardById(@PathVariable int inwardId) throws ApiException {
		return ResponseEntity.ok(new ApiResponse(200, "Inward Created", inwardService.viewById(inwardId)));
	}

	@PutMapping("/inward/update/{inwardId}")
	public ResponseEntity<?> updateInward(@RequestBody @Valid Inward inward, @PathVariable int inwardId)
			throws ApiException {
		Inward updatedInward = inwardService.updateInward(inward, inwardId);
		return ResponseEntity.ok(new ApiResponse(HttpStatus.OK.value(), "Inward updated successfully", updatedInward));
	}

	@DeleteMapping("/inward/delete/{inwardId}")
	public ResponseEntity<?> deleteInwardName(@PathVariable int inwardId) throws ApiException {
		boolean updatedInward = inwardService.deleteInward(inwardId);
		return ResponseEntity.ok(new ApiResponse(HttpStatus.OK.value(), "Inward updated successfully", updatedInward));
	}

	@PostMapping("/inward/create-with-productList/{vendorId}/{userId}")
	public ResponseEntity<?> createWithProductList(@RequestBody @Valid Inward inward, @PathVariable int vendorId,
			@PathVariable int userId) throws ApiException {
		Inward inward1 = linkService.createInwardAttachUserIdAndVendorId(inward, vendorId, userId);
		return ResponseEntity.ok(new ApiResponse(HttpStatus.OK.value(), "Inward Created successfully", inward1));
	}

	@PutMapping("/inward/linkProduct/{invoiceId}/{productId}")
	public ResponseEntity<?> linkAProductWithInward(@PathVariable int invoiceId, @PathVariable int productId)
			throws ApiException {
		Inward i = linkService.linkProductWithInward(invoiceId, productId);
		return ResponseEntity.ok(new ApiResponse(HttpStatus.OK.value(), "Product Attached to inward", i));
	}

}
