package com.ocsc.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ocsc.Entity.Customer;
import com.ocsc.Entity.Issue;
import com.ocsc.Entity.IssueStatus;
import com.ocsc.Entity.Login;
import com.ocsc.Exception.CustomerException;
import com.ocsc.Repository.CustomerRepository;
import com.ocsc.Repository.IssueRepository;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private IssueRepository issueRepository;


	
	@Override
	public String loginCustomer(Login login) throws CustomerException {
		// TODO Auto-generated method stub
		return null;
	}
	
	

	@Override
	public String registerCustomer(Customer customer) throws CustomerException {
		
		Optional<Customer> existingCustomer = customerRepository.findById(customer.getCustomerId());
		
		if(existingCustomer.isPresent()) 
			throw new CustomerException("Customer Already Registered with customerId");
		
		customerRepository.save(customer);
		
		return "Customer added successfully";
	}
	

	
	@Override
	public Issue viewIssuesById(Integer issueId) throws CustomerException {

		Optional<Issue> issue = issueRepository.findById(issueId);
		
		if(issue.isEmpty())
			throw new CustomerException("Issue doesn't exist with given issueId for the given Customer");
		
		return issue.get();
	}
	

	
	@Override
	public Issue reOpenIssue(Integer issueId, IssueStatus newStatus) throws CustomerException {

		Optional<Issue> issue = issueRepository.findById(issueId);
		
		if(issue.isEmpty())
			throw new CustomerException("Issue doesn't exist with given issueId for the given Customer");
		
		if (issue.get().getStatus() == IssueStatus.PENDING) {
			issue.get().setStatus(newStatus);;
		}
		
		return issue.get();
	}
	

	
	@Override
	public List<Issue> viewAllIssues(Integer customerId) throws CustomerException {

		Optional<Customer> customer = customerRepository.findById(customerId);
		
		if(customer == null) 
			throw new CustomerException("Customer is not Registered with customerId");
		
		if(customer.get().getIssueList().isEmpty()) {
    		throw new CustomerException("Empty issue list") ;
    	}
		
		return customer.get().getIssueList();
	}
	
	

	@Override
	public String changePassword(Login login) throws CustomerException {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public String forgotPassword(Integer customerId) throws CustomerException {
		// TODO Auto-generated method stub
		return null;
	}
	
	

	@Override
	public String emailPassword(Integer customerId) throws CustomerException {
		// TODO Auto-generated method stub
		return null;
	}

	
}
