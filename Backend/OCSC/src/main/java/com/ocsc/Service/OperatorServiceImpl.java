package com.ocsc.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ocsc.Entity.CurrentAdminSession;
import com.ocsc.Entity.CurrentOperatorSession;
import com.ocsc.Entity.Customer;
import com.ocsc.Entity.Issue;
import com.ocsc.Entity.IssueStatus;
import com.ocsc.Entity.Login;
import com.ocsc.Entity.Operator;
import com.ocsc.Exception.LoginException;
import com.ocsc.Exception.OperatorException;
import com.ocsc.Repository.CurrentOperatorSessionRepository;
import com.ocsc.Repository.CustomerRepository;
import com.ocsc.Repository.IssueRepository;
import com.ocsc.Repository.OperatorRepository;

import net.bytebuddy.utility.RandomString;

@Service
public class OperatorServiceImpl implements OperatorService{
	
	@Autowired
	private IssueRepository issueRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private OperatorRepository operatorRepository;
	
	@Autowired
	private CurrentOperatorSessionRepository currentOperatorSessionRepository;
	
	

	@Override
	public CurrentOperatorSession loginOperator(Login login) throws OperatorException, LoginException{

		Operator existingOperator = operatorRepository.findByUserName(login.getUsername());
		
		if(existingOperator == null) {
			throw new LoginException("Please Enter a valid username number");
		}	
		
		Optional<CurrentOperatorSession> validOperatorSessionOpt = currentOperatorSessionRepository.findById(existingOperator.getOperatorId());
		
		if(validOperatorSessionOpt.isPresent()) {
			throw new LoginException("User already Logged In with this username");	
		}
		
		if(existingOperator.getPassword().equals(login.getPassword())) {
			
			String key= RandomString.make(6);
			
			CurrentOperatorSession currentOperatorSession = new CurrentOperatorSession(existingOperator.getOperatorId(),key,LocalDateTime.now());
			
			currentOperatorSessionRepository.save(currentOperatorSession);

			return currentOperatorSession;
		}
		else
			throw new LoginException("Please Enter a valid password");
	}
	
	

	@Override
	public Issue addCustomerIssue(Issue issue) throws OperatorException {
		
		if (issue == null) {
			throw new OperatorException("Issue can't be null");
		}

		Optional<Issue> existingIssue = issueRepository.findById(issue.getIssueld());
		
		if(existingIssue.isPresent()) 
			throw new OperatorException("Issue already exists with given issueId");
		
		issueRepository.save(issue);
		
		return issue;
	}
	
	

	@Override
	public Issue modifyCustomerIssue(Issue issue) throws OperatorException {

		if (issue == null) {
			throw new OperatorException("Issue can't be null");
		}
		
		Optional<Issue> existingIssue = issueRepository.findById(issue.getIssueld());
		
		if(existingIssue.isEmpty()) 
			throw new OperatorException("Issue doesn't exist with given issueId");
		
		existingIssue.get().setIssueType(issue.getIssueType());
		existingIssue.get().setDescription(issue.getDescription());
		existingIssue.get().setStatus(issue.getStatus());
		
		return existingIssue.get();
	}	
	


	@Override
	public String closeCustomerIssue(Issue issue) throws OperatorException {
		
		if (issue == null) {
			throw new OperatorException("Issue can't be null");
		}

		Optional<Issue> existingIssue = issueRepository.findById(issue.getIssueld());
		
		if(existingIssue.isEmpty())
			throw new OperatorException("Issue doesn't exist with given issueId");
		
		if (existingIssue.get().getStatus() == IssueStatus.PENDING) {
			existingIssue.get().setStatus(IssueStatus.RESOLVED);
		}
		
		return "Issue closed successfully";
	}
	
	

	@Override
	public Customer findCustomerById(Integer customerId) throws OperatorException {
		
		if (customerId == null) {
			throw new OperatorException("CustomerId can't be null");
		}

		Optional<Customer> existingCustomer = customerRepository.findById(customerId);
		
		if(existingCustomer.isEmpty()) 
			throw new OperatorException("No customer exists with given customerId");
		
		return existingCustomer.get();
	}
	
	

	@Override
	public List<Customer> findCustomerByName(String name) throws OperatorException {
		
		if (name == null) {
			throw new OperatorException("Name can't be null");
		}

		List<Customer> customers = customerRepository.findAll();

		if(customers.isEmpty()) 
			throw new OperatorException("No customer exists with given customerId");
		
		List<Customer> list = new ArrayList<>();
		
		for (int i=0; i<customers.size(); i++) {
			if (customers.get(i).getName() == name) {
				list.add(customers.get(i));
			}
		}
		
		return list;
	}
	
	

	@Override
	public Customer findCustomerByEmail(String email) throws OperatorException {
		
		if (email == null) {
			throw new OperatorException("Email can't be null");
		}

		Optional<Customer> existingCustomer = customerRepository.findByEmail(email);
		
		if(existingCustomer.isEmpty()) 
			throw new OperatorException("No customer exists with given customerId");
		
		return existingCustomer.get();
	}
	
	
	
	@Override
	public String sendIntimidationEmailToCustomer(Integer issueId, Integer customerId) throws OperatorException {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	@Override
	public String sendModificationEmailToCustomer(Integer issueId, Integer customerId) throws OperatorException {
		// TODO Auto-generated method stub
		return null;
	}
	
	

	@Override
	public boolean lockCustomer(Integer customerId) throws OperatorException {
		// TODO Auto-generated method stub
		return false;
	}

	
}
