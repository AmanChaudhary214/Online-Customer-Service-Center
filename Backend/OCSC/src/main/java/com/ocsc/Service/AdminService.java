package com.ocsc.Service;

import java.util.List;

import com.ocsc.Entity.CurrentAdminSession;
import com.ocsc.Entity.Department;
import com.ocsc.Entity.Login;
import com.ocsc.Entity.Operator;
import com.ocsc.Exception.AdminException;
import com.ocsc.Exception.LoginException;

public interface AdminService {
	
	public CurrentAdminSession loginAdmin(Login login) throws AdminException, LoginException;

	public String addDepartment(Department department) throws AdminException;
	
	public String removeDepartment(Integer departmentId) throws AdminException;
	
	public Department modifyDepartment(Department department, Integer departmentId) throws AdminException;
	
	public Department findDepartmentById(Integer departmentId) throws AdminException;
	
	public String addOperator(Operator operator) throws AdminException;
	
	public String removeOperator(Integer operatorId) throws AdminException;
	
	public Operator modifyOperator(Operator operator, Integer operatorId) throws AdminException;
	
	public Operator findOperatorById(Integer operatorId) throws AdminException;
	
	public List<Operator> findAllOperators() throws AdminException;
	
}
