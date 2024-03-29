package com.cts.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cts.exception.ApiException;
import com.cts.models.requestModels.CreateIssueRequest;
import com.cts.models.requestModels.ResolveIssueRequest;
import com.cts.models.responseModels.ApiResponse;
import com.cts.services.IssueService;

@RestController
public class IssueController {

	@Autowired
	IssueService issueService;

	@PostMapping("/employee/add-new-issue")
	public ResponseEntity<?> createIssue(@RequestBody CreateIssueRequest issueRequest) {
		return ResponseEntity
				.ok(new ApiResponse(200, "New Issue added successfully", issueService.saveIssue(issueRequest)));
	}

	@GetMapping("/admin/issues")
	public ResponseEntity<?> getAllIssues() throws ApiException {
		return ResponseEntity.ok(new ApiResponse(200, "Fetched all issues", issueService.fetchAllIssues()));
	}

	@GetMapping("/employee/issue/{id}")
	public ResponseEntity<?> getIssueById(@PathVariable int id) throws ApiException {
		return ResponseEntity.ok(new ApiResponse(200, "Requested Issue fetched", issueService.fetchIssueById(id)));
	}

	@GetMapping("/employee/issues/{empId}")
	public ResponseEntity<?> getIssuesByEmpId(@PathVariable int empId) throws ApiException {
		return ResponseEntity.ok(
				new ApiResponse(200, "Fetched all issues requested by you", issueService.fetchIssuesByEmpId(empId)));
	}

	@GetMapping("/admin/resolved-issues")
	public ResponseEntity<?> getResolvedIssues() throws ApiException {
		return ResponseEntity
				.ok(new ApiResponse(200, "Fetched all resolved issues", issueService.fetchResolvedIssues()));
	}

	@GetMapping("/admin/unresolved-issues")
	public ResponseEntity<?> getUnresolvedIssues() throws ApiException {
		return ResponseEntity
				.ok(new ApiResponse(200, "Fetched all unresolved issues", issueService.fetchUnresolvedIssues()));
	}

	@PutMapping("/admin/resolve-issue")
	public ResponseEntity<?> provideResponseToIssue(@RequestBody ResolveIssueRequest request) {
		return ResponseEntity.ok(new ApiResponse(200, "Issue resolved", issueService.updateIssue(request)));
	}

	@DeleteMapping("/admin/delete/{id}")
	public ResponseEntity<?> deleteAnIssue(@PathVariable int id) throws ApiException {
		return ResponseEntity.ok(new ApiResponse(200, "Successfully deleted", issueService.deleteIssue(id)));
	}

}
