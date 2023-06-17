package com.ocsc.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ocsc.Entity.Customer;
import com.ocsc.Exception.CustomerException;
import com.ocsc.Exception.OperatorException;
import com.ocsc.Service.CustomerService;
import com.ocsc.Service.OperatorService;

@RestController
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private OperatorService operatorService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	@GetMapping("/hello")
	public String testHandler() {
		return "Welcome to Spring Security";
	}
	
	
	@PostMapping("/customers")
	public ResponseEntity<Customer> saveCustomerHandler(@RequestBody Customer customer) throws CustomerException{
		
		customer.setPassword(passwordEncoder.encode(customer.getPassword()));
		
		customer.setRole("ROLE_"+customer.getRole().toUpperCase());
		
		Customer registeredCustomer = customerService.registerCustomer(customer);
		
		return new ResponseEntity<>(registeredCustomer,HttpStatus.ACCEPTED);
		
	}
	
	
	@GetMapping("/signIn")
	public ResponseEntity<String> getLoggedInCustomerDetailsHandler(Authentication auth) throws OperatorException{
		
		System.out.println(auth); // this Authentication object having Principle object details
		
		 Customer customer= operatorService.findCustomerByEmail(auth.getName());
		 
		 return new ResponseEntity<>(customer.getFName()+"Logged In Successfully", HttpStatus.ACCEPTED);	
	}
	
	/*
	 
	  {
      "FName": "ram",
      "lName": "ram",
      "email": "ram@gmail.com",
      "password": "1234",
      "mobile": "delhi",
      "city": "ram",
      "role": "admin"
  }
	 
	 
	 
	 
	 
	 */
	
}
