package com.ocsc.Service;

import java.util.List;

import com.ocsc.Entity.Customer;
import com.ocsc.Entity.Issue;
import com.ocsc.Entity.Login;
import com.ocsc.Exception.OperatorException;

public interface OperatorService {
	
	public String loginOperator(Login login) throws OperatorException;

	public Issue addCustomerIssue(Issue issue) throws OperatorException;
	
	public String sendIntimidationEmailToCustomer(Integer issueId, Integer customerId) throws OperatorException;

	public Issue modifyCustomerIssue(Issue issue) throws OperatorException;
	
	public String sendModificationEmailToCustomer(Integer issueId, Integer customerId) throws OperatorException;
	
	public String closeCustomerIssue(Issue issue) throws OperatorException;
	
	public Customer findCustomerById(Integer customerId) throws OperatorException;
	
	public List<Customer> findCustomerByName(String fName) throws OperatorException;
	
	public Customer findCustomerByEmail(String email) throws OperatorException;
	
	public boolean lockCustomer(Integer customerId) throws OperatorException;
	
}