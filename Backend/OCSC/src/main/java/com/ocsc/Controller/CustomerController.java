package com.ocsc.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.ocsc.Entity.Customer;
import com.ocsc.Entity.Issue;
import com.ocsc.Entity.IssueStatus;
import com.ocsc.Exception.CustomerException;
import com.ocsc.Service.CustomerService;

@RestController
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@GetMapping("/hello")
	public String testHandler() {
		return "Welcome to Spring Security";
	}

	@PostMapping("/customer/register")
	public ResponseEntity<Customer> registerCustomer(@RequestBody Customer customer) throws CustomerException {
		customer.setPassword(passwordEncoder.encode(customer.getPassword()));
		customer.setRole("ROLE_"+customer.getRole().toUpperCase());
		Customer registeredCustomer = customerService.registerCustomer(customer);
		return new ResponseEntity<>(registeredCustomer, HttpStatus.CREATED);
	}

	@PostMapping("/customer/login")
	public ResponseEntity<String> loginCustomer(@RequestBody Customer customer) throws CustomerException {
		String loginMessage = customerService.loginCustomer(new Login(customer.getEmail(), customer.getPassword()));
		return new ResponseEntity<>(loginMessage, HttpStatus.OK);
	}

	@GetMapping("/customer/{customerId}/issues/{issueId}")
	public ResponseEntity<Issue> viewIssuesById(@PathVariable Integer issueId) throws CustomerException {
		Issue issue = customerService.viewIssuesById(issueId);
		return new ResponseEntity<>(issue, HttpStatus.OK);
	}

	@PutMapping("/customer/{customerId}/issues/{issueId}/reopen/{newStatus}")
	public ResponseEntity<Issue> reOpenIssue(@PathVariable Integer issueId, @PathVariable IssueStatus newStatus) throws CustomerException {
		Issue reopenedIssue = customerService.reOpenIssue(issueId, newStatus);
		return new ResponseEntity<>(reopenedIssue, HttpStatus.OK);
	}

	@GetMapping("/customer/{customerId}/issues")
	public ResponseEntity<List<Issue>> viewAllIssues(@PathVariable Integer customerId) throws CustomerException {
		List<Issue> issues = customerService.viewAllIssues(customerId);
		return new ResponseEntity<>(issues, HttpStatus.OK);
	}

	@PutMapping("/customer/changePassword")
	public ResponseEntity<String> changePassword(@RequestBody Customer customer) throws CustomerException {
		String passwordMessage = customerService.changePassword(new Login(customer.getEmail(), customer.getPassword()));
		return new ResponseEntity<>(passwordMessage, HttpStatus.OK);
	}

	@PutMapping("/customer/{customerId}/forgotPassword")
	public ResponseEntity<String> forgotPassword(@PathVariable Integer customerId) throws CustomerException {
		String passwordResetMessage = customerService.forgotPassword(customerId);
		return new ResponseEntity<>(passwordResetMessage, HttpStatus.OK);
	}

	@PutMapping("/customer/{customerId}/emailPassword")
	public ResponseEntity<String> emailPassword(@PathVariable Integer customerId) throws CustomerException {
		String emailPasswordMessage = customerService.emailPassword(customerId);
		return new ResponseEntity<>(emailPasswordMessage, HttpStatus.OK);
	}
}
