package com.ocsc.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ocsc.Entity.CurrentOperatorSession;
import com.ocsc.Entity.Customer;
import com.ocsc.Entity.Issue;
import com.ocsc.Entity.Login;
import com.ocsc.Exception.LoginException;
import com.ocsc.Exception.OperatorException;
import com.ocsc.Service.OperatorService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/operator")
public class OperatorController {
	
	@Autowired
	private OperatorService operatorService ;
	
	
	
	@PostMapping("/operatorLogin")
	public ResponseEntity<CurrentOperatorSession> loginOperator(@RequestBody Login login) throws LoginException, OperatorException {
		
		CurrentOperatorSession result = operatorService.loginOperator(login);
			
		return new ResponseEntity<CurrentOperatorSession>(result,HttpStatus.OK );
	}
	
	
	
	@PostMapping("/addIssue")
	public ResponseEntity<Issue> addCustomerIssueHandler(@RequestBody @Valid Issue issue) throws OperatorException{
		
		Issue i = operatorService.addCustomerIssue(issue);
		
		return new ResponseEntity<Issue>(i,HttpStatus.CREATED);
	}
	
	
	
	@PostMapping("/modifyIssue")
	public ResponseEntity<Issue> modifyIssueHandler(@RequestBody @Valid Issue issue)throws OperatorException {
		
		Issue i = operatorService.modifyCustomerIssue(issue);
		
		return new ResponseEntity<Issue>(i,HttpStatus.OK);
	}
	
	
	
	@PostMapping("/closeIssue")
	public ResponseEntity<String> closeIssueHandler(@RequestBody @Valid Issue issue) throws OperatorException{
		
		String s = operatorService.closeCustomerIssue(issue);
		
		return new ResponseEntity<String>(s,HttpStatus.OK);
	}
	
	
	
	@GetMapping("/customer/{customerId}")
	public ResponseEntity<Customer> getCustomerByIdHandler(@PathVariable @Valid Integer customerId)throws OperatorException {
		
		Customer c = operatorService.findCustomerById(customerId);
		
		return new ResponseEntity<Customer>(c,HttpStatus.OK);
	}
	
	
	
	@GetMapping("/customer/{name}")
	public ResponseEntity<List<Customer>> getByFirstNameHandler(@PathVariable @Valid String name) throws OperatorException{
		
		List<Customer> cList =  operatorService.findCustomerByName(name);
		
		return new ResponseEntity<List<Customer>>(cList,HttpStatus.OK);
	}
	
	
	
	@GetMapping("/customer/{email}")
	public ResponseEntity<Customer> getByEmailHandler(@PathVariable @Valid String email)throws OperatorException {
		
		Customer c =  operatorService.findCustomerByEmail(email);
		
		return new ResponseEntity<Customer>(c,HttpStatus.OK);
	}
	
	
}
