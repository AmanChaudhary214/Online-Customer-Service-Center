package com.ocsc.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ocsc.Entity.Department;
import com.ocsc.Entity.Operator;
import com.ocsc.Exception.AdminException;
import com.ocsc.Repository.DepartmentRepository;
import com.ocsc.Repository.OperatorRepository;

@Service
public class AdminServiceImpl implements AdminService {

	
	@Autowired
	private DepartmentRepository departmentRepository;
	
	@Autowired
	private OperatorRepository operatorRepository;
	
	
	
	@Override
	public String addDepartment(Department department) throws AdminException {

		Optional<Department> existingDepartment = departmentRepository.findById(department.getDepartmentId());
		
		if(existingDepartment.isPresent()) 
			throw new AdminException("Department Already Registered with given departmentId");
		
		departmentRepository.save(department);
		
		return "Department added successfully";
		
	}
	
	

	@Override
	public String removeDepartment(Integer departmentId) throws AdminException {

		Optional<Department> existingDepartment = departmentRepository.findById(departmentId);
		
		if(existingDepartment == null) 
			throw new AdminException("Department doesn't exist with given departmentId");
		
		departmentRepository.deleteById(departmentId);
		
		return "Department added successfully";
	}
	
	

	@Override
	public Department modifyDepartment(Department department) throws AdminException {

		Optional<Department> existingDepartment = departmentRepository.findById(department.getDepartmentId());
		
		if(existingDepartment == null) 
			throw new AdminException("Department doesn't exist with given departmentId");
		
		existingDepartment.get().setDepartmentName(department.getDepartmentName());
		
		return existingDepartment.get();
	}
	
	

	@Override
	public Department findDepartmentById(Integer departmentId) throws AdminException {

		Optional<Department> existingDepartment = departmentRepository.findById(departmentId);
		
		if(existingDepartment == null) 
			throw new AdminException("Department doesn't exist with given departmentId");
		
		return existingDepartment.get();
	}
	
	

	@Override
	public String addOperator(Operator operator) throws AdminException {

		Optional<Operator> existingOperator = operatorRepository.findById(operator.getOperatorld());
		
		if(existingOperator.isPresent()) 
			throw new AdminException("Operator Already Registered with given operatorId");
		
		operatorRepository.save(operator);
		
		return "Operator added successfully";
	}
	
	

	@Override
	public String removeOperator(Integer operatorId) throws AdminException {
		
		Optional<Operator> existingOperator = operatorRepository.findById(operatorId);
		
		if(existingOperator == null) 
			throw new AdminException("Operator doesn't exist with with given operatorId");
		
		operatorRepository.deleteById(operatorId);
		
		return "Operator deleted successfully";
	}
	
	

	@Override
	public Operator modifyOperator(Operator operator) throws AdminException {

		Optional<Operator> existingOperator = operatorRepository.findById(operator.getOperatorld());
		
		if(existingOperator == null) 
			throw new AdminException("Operator doesn't exist with with given operatorId");
		
		existingOperator.get().setFirstName(operator.getFirstName());
		existingOperator.get().setLastName(operator.getLastName());
		existingOperator.get().setEmail(operator.getEmail());
		existingOperator.get().setMobile(operator.getMobile());
		existingOperator.get().setCity(operator.getCity());
		
		return existingOperator.get();
		
	}

	
	
	@Override
	public Operator findOperatorById(Integer operatorId) throws AdminException {
		
		Optional<Operator> existingOperator = operatorRepository.findById(operatorId);
		
		if(existingOperator == null) 
			throw new AdminException("Operator doesn't exist with with given operatorId");
		
		return existingOperator.get();
	}
	
	

	@Override
	public List<Operator> findAllOperators() throws AdminException {

		List<Operator> operators = operatorRepository.findAll();
		
		if (operators.isEmpty()) {
			throw new AdminException("No operators found");
		}
		
		return operators;
	}
	

}
