package com.cts.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.exception.ApiException;
import com.cts.models.entityModels.Inward;
import com.cts.models.entityModels.Product;
import com.cts.models.entityModels.User;
import com.cts.models.entityModels.Vendor;
import com.cts.repository.InwardRepository;
import com.cts.repository.ProductRepository;
import com.cts.repository.UserRepository;
import com.cts.repository.VendorRepository;

@Service
public class LinkerService {

	@Autowired
	private InwardRepository inwardRepo;

	@Autowired
	private VendorRepository vendorRepo;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private ProductRepository productRepo;

	@Autowired
	private VendorService vendorService;
	@Transactional
	public Inward createInwardAttachUserIdAndVendorId(Inward inward, int vendorId, int userId) throws ApiException {
		if (inwardRepo.findByInvoiceId(inward.getInvoiceId()).isPresent()) {
			throw new ApiException("Invoice Id exists");
		}
		if (!vendorRepo.existsById(vendorId)) {
			throw new ApiException("Vendor Id donot Exists");
		}
		if (!userRepo.existsById(userId)) {
			throw new ApiException("User Id donot Exists");
		}
		Inward newInward = new Inward();
		newInward.setInvoiceId(inward.getInvoiceId());
		newInward.setInvoiceDate(inward.getInvoiceDate());
		newInward.setInwardDate(inward.getInwardDate());
		Inward i = inwardRepo.save(newInward);

		Vendor v = vendorService.viewById(vendorId);
		v.addInward(i);
		User u = userRepo.findById(userId).get();
		u.addInward(i);

		return i;

	}

	public Inward linkProductWithInward(int invoiceId, int productId) throws ApiException {
		if (!inwardRepo.findByInvoiceId(invoiceId).isPresent()) {
			throw new ApiException("Invoice Id Invalid");
		}
		if (!productRepo.existsById(productId)) {
			throw new ApiException("Product Id Invalid");
		}
		Inward i = inwardRepo.findByInvoiceId(invoiceId).get();
		Product p = productRepo.findById(productId).get();
		i.setTotalPrice(i.getTotalPrice() + p.getCostPrice());
		i.addProductToProductList(p);
		Inward iSaved = inwardRepo.save(i);
		return inwardRepo.findById(iSaved.getId()).get();
	}

}
