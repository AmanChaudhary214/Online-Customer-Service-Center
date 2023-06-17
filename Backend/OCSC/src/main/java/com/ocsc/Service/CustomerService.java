package com.ocsc.Service;

import java.util.List;

import com.ocsc.Entity.Customer;
import com.ocsc.Entity.Issue;
import com.ocsc.Entity.IssueStatus;
import com.ocsc.Entity.Login;
import com.ocsc.Exception.CustomerException;

public interface CustomerService {

	public Customer registerCustomer(Customer customer) throws CustomerException;
	
	public String loginCustomer(Login login) throws CustomerException;

	public Issue viewIssuesById(Integer issueId) throws CustomerException;

	public Issue reOpenIssue(Integer issueId, IssueStatus newStatus) throws CustomerException;

	public List<Issue> viewAllIssues(Integer customerId) throws CustomerException;
	
	public String changePassword(Login login) throws CustomerException;
	
	public String forgotPassword(Integer customerId) throws CustomerException;
	
	public String emailPassword(Integer customerId) throws CustomerException;
	
}
