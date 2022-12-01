package com.cts.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.cts.exception.ApiException;
import com.cts.models.entityModels.Inward;
import com.cts.repository.InwardRepository;

@Service
public class InwardService {

	@Autowired
	private InwardRepository inwardRepo;

	public Inward createInward(Inward inward) throws ApiException {
		if (inwardRepo.findByInvoiceId(inward.getInvoiceId()).isPresent()) {
			throw new ApiException("Invoice No. already exists");
		}
		return inwardRepo.save(inward);
	}

	public List<Inward> viewAllInwards() {
		return inwardRepo.findAll(Sort.by(Sort.Direction.DESC, "id"));
	}

	public Inward viewById(int inwardId) throws ApiException {
		if (!inwardRepo.existsById(inwardId)) {
			throw new ApiException("No Inward found with this Id");
		}
		return inwardRepo.findById(inwardId).get();
	}

	public Inward updateInward(Inward inward, int inwardId) throws ApiException {
		if (!inwardRepo.existsById(inwardId)) {
			throw new ApiException("No Inward found with this Id");
		}
		if (inwardRepo.findByInvoiceId(inward.getInvoiceId()).isPresent()) {
			throw new ApiException("Invoice Id already present");
		}
		Inward i = inwardRepo.findById(inwardId).get();
		i.setInvoiceDate(inward.getInvoiceDate());
		i.setInvoiceId(inward.getInvoiceId());
		i.setInwardDate(inward.getInwardDate());
		i.setTotalPrice(inward.getTotalPrice());
		return inwardRepo.save(i);
	}

	public boolean deleteInward(int inwardId) throws ApiException {
		if (!inwardRepo.existsById(inwardId)) {
			throw new ApiException("No Inward found with this Id");
		}
		if (!inwardRepo.findById(inwardId).get().getProductList().isEmpty()) {
			throw new ApiException("Inward has Product list in it hence cannot be deleted");
		}
		inwardRepo.deleteById(inwardId);
		return !inwardRepo.existsById(inwardId);
	}

}
