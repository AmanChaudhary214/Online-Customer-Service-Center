package com.ocsc.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ocsc.Entity.CurrentCustomerSession;
import com.ocsc.Entity.Customer;
import com.ocsc.Entity.Issue;
import com.ocsc.Entity.IssueStatus;
import com.ocsc.Entity.Login;
import com.ocsc.Exception.CustomerException;
import com.ocsc.Exception.LoginException;
import com.ocsc.Repository.CurrentCustomerSessionRepository;
import com.ocsc.Repository.CustomerRepository;
import com.ocsc.Repository.IssueRepository;

import net.bytebuddy.utility.RandomString;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private IssueRepository issueRepository;
	
	@Autowired
	private CurrentCustomerSessionRepository currentCustomerSessionRepository;


	
	@Override
	public Customer registerCustomer(Customer customer) throws CustomerException {
		
		if (customer == null) {
			throw new CustomerException("Customer can't be null");
		}
		
		Optional<Customer> existingCustomer = customerRepository.findByEmail(customer.getEmail());
		
		if(existingCustomer.isPresent()) 
			throw new CustomerException("Customer Already Registered with customerId");
		
		customerRepository.save(customer);
		
		return customer;
	}
	

	
	@Override
	public Issue viewIssuesById(Integer issueId) throws CustomerException {
		
		if (issueId == null) {
			throw new CustomerException("IssueId can't be null");
		}

		Optional<Issue> issue = issueRepository.findById(issueId);
		
		if(issue.isEmpty())
			throw new CustomerException("Issue doesn't exist with given issueId for the given Customer");
		
		return issue.get();
	}
	

	
	@Override
	public Issue reOpenIssue(Integer issueId, IssueStatus newStatus) throws CustomerException {
		
		if (issueId == null) {
			throw new CustomerException("IssueId can't be null");
		}

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
		
		if (customerId == null) {
			throw new CustomerException("CustomerId can't be null");
		}

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
	
	
	@Override
	public CurrentCustomerSession loginCustomer(Login login)throws CustomerException, LoginException{
		
		Customer existingCustomer = customerRepository.findByUserName(login.getUsername());
		
		if(existingCustomer == null) {
			throw new LoginException("Please Enter a valid username number");
		}	
		
		Optional<CurrentCustomerSession> validCustomerSessionOpt = currentCustomerSessionRepository.findById(existingCustomer.getCustomerId());
		
		if(validCustomerSessionOpt.isPresent()) {
			throw new LoginException("User already Logged In with this username");
		}
		
		if(existingCustomer.getPassword().equals(login.getPassword())) {
			
			String key= RandomString.make(6);
			
			CurrentCustomerSession currentCustomerSession = new CurrentCustomerSession(existingCustomer.getCustomerId(),key,LocalDateTime.now());
			
			currentCustomerSessionRepository.save(currentCustomerSession);

			return currentCustomerSession;
		}
		else
			throw new LoginException("Please Enter a valid password");
	}
	
	
//	@Override
//	public String logOutFromAccount(String token)throws LoginException {
//		
//		CurrentUserSession validCustomerSession = currentUserSessionRepository.findByToken(token);
//		
//		
//		if(validCustomerSession == null) {
//			throw new LoginException("User Not Logged In with this number");
//			
//		}
//		
//		
//		currentUserSessionRepository.delete(validCustomerSession);
//		
//		
//		return "Logged Out !";
//		
//		
//	}


}
