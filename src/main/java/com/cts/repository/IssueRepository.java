package com.cts.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.models.entityModels.Issue;

public interface IssueRepository extends JpaRepository<Issue, Integer> {
	
	Optional<List<Issue>> findByEmpId(int empId);
	
	Optional<List<Issue>> findBySolved(boolean solved);

}
