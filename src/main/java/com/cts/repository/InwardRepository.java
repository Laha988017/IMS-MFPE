package com.cts.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.models.entityModels.Inward;

public interface InwardRepository extends JpaRepository<Inward, Integer> {
	
	Optional<Inward> findByInvoiceId(int invoiceId);

}
