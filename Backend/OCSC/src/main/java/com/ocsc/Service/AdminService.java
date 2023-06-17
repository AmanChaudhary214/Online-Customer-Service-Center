package com.ocsc.Service;

import java.util.List;

import com.ocsc.Entity.Department;
import com.ocsc.Entity.Operator;
import com.ocsc.Exception.AdminException;

public interface AdminService {

	public String addDepartment(Department department) throws AdminException;
	
	public String removeDepartment(Integer departmentId) throws AdminException;
	
	public Department modifyDepartment(Department department) throws AdminException;
	
	public Department findDepartmentById(Integer departmentId) throws AdminException;
	
	public String addOperator(Operator operator) throws AdminException;
	
	public String removeOperator(Integer operatorId) throws AdminException;
	
	public Operator modifyOperator(Operator operator) throws AdminException;
	
	public Operator findOperatorById(Integer operatorId) throws AdminException;
	
	public List<Operator> findAllOperators() throws AdminException;
	
}
