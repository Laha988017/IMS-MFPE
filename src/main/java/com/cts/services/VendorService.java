package com.cts.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.exception.ApiException;
import com.cts.models.entityModels.Vendor;
import com.cts.repository.VendorRepository;

@Service
public class VendorService {

	@Autowired
	private VendorRepository vendorRepo;

	public Vendor createVendor(Vendor vendor) throws ApiException {
		if (vendorRepo.findByGstNo(vendor.getGstNo()).isPresent()) {
			throw new ApiException("GST No. already exists");
		}
		return vendorRepo.save(vendor);
	}

	public List<Vendor> viewAllVendors() {
		return vendorRepo.findAll();
	}

	public Vendor viewById(int vendorId) throws ApiException {
		if (!vendorRepo.existsById(vendorId)) {
			throw new ApiException("No Vendor found with this Id");
		}
		return vendorRepo.findById(vendorId).get();
	}

	public Vendor updateVendor(Vendor vendor, int vendorId) throws ApiException {
		if (!vendorRepo.existsById(vendorId)) {
			throw new ApiException("No Vendor found with this Id");
		}
		if (vendorRepo.findByGstNo(vendor.getGstNo()).isPresent()) {
			throw new ApiException("GST NO. Already present");
		}
		Vendor v = vendorRepo.findById(vendorId).get();
		v.setVendorName(vendor.getVendorName());
		v.setAddress(vendor.getAddress());
		v.setGstNo(vendor.getGstNo());
		return vendorRepo.save(v);
	}

	public boolean deleteVendor(int vendorId) throws ApiException {
		if (!vendorRepo.existsById(vendorId)) {
			throw new ApiException("No Vendor found with this Id");
		}
		if (!vendorRepo.findById(vendorId).get().getInwards().isEmpty()) {
			throw new ApiException("Vendor has Inwards list in it hence cannot be deleted");
		}
		vendorRepo.deleteById(vendorId);
		return !vendorRepo.existsById(vendorId);
	}

	public List<Map<String, String>> getAllVendorByName() {
		List<Vendor> vendorSavedList = vendorRepo.findAll();

		List<Map<String, String>> vendorList = new ArrayList<>();
		System.out.println(vendorSavedList.size());
		for (Vendor v : vendorSavedList) {
			Map<String, String> vendorMap = new HashMap<>();
			vendorMap.put("vendorId", String.valueOf(v.getId()));
			vendorMap.put("vendorName", v.getVendorName());
			vendorList.add(vendorMap);
		}

		return vendorList;
	}

}
