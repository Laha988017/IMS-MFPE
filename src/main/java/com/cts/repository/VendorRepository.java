package com.cts.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.models.entityModels.Vendor;

public interface VendorRepository extends JpaRepository<Vendor, Integer> {

	Optional<Vendor> findByGstNo(String gstNo);

}
