package com.ocsc.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ocsc.Entity.Admin;
import com.ocsc.Entity.CurrentAdminSession;
import com.ocsc.Entity.CurrentCustomerSession;
import com.ocsc.Entity.Department;
import com.ocsc.Entity.Login;
import com.ocsc.Entity.Operator;
import com.ocsc.Exception.AdminException;
import com.ocsc.Exception.LoginException;
import com.ocsc.Repository.AdminRepository;
import com.ocsc.Repository.CurrentAdminSessionRepository;
import com.ocsc.Repository.DepartmentRepository;
import com.ocsc.Repository.OperatorRepository;

import net.bytebuddy.utility.RandomString;

@Service
public class AdminServiceImpl implements AdminService {

	
	@Autowired
	private DepartmentRepository departmentRepository;
	
	@Autowired
	private OperatorRepository operatorRepository;
	
	@Autowired
	private CurrentAdminSessionRepository currentAdminSessionRepository;
	
	@Autowired
	private AdminRepository adminRepository;
	
	
	
	@Override
	public String addDepartment(Department department) throws AdminException {
		
		if (department == null) {
			throw new AdminException("Department can't be null");
		}

		Optional<Department> existingDepartment = departmentRepository.findById(department.getDepartmentId());
		
		if(existingDepartment.isPresent()) 
			throw new AdminException("Department Already Registered with given departmentId");
		
		departmentRepository.save(department);
		
		return "Department added successfully";
		
	}
	
	

	@Override
	public String removeDepartment(Integer departmentId) throws AdminException {
		
		if (departmentId == null) {
			throw new AdminException("DepartmentId can't be null");
		}

		Optional<Department> existingDepartment = departmentRepository.findById(departmentId);
		
		if(existingDepartment == null) 
			throw new AdminException("Department doesn't exist with given departmentId");
		
		departmentRepository.deleteById(departmentId);
		
		return "Department added successfully";
	}
	
	

	@Override
	public Department modifyDepartment(Department department) throws AdminException {
		
		if (department == null) {
			throw new AdminException("Department can't be null");
		}

		Optional<Department> existingDepartment = departmentRepository.findById(department.getDepartmentId());
		
		if(existingDepartment == null) 
			throw new AdminException("Department doesn't exist with given departmentId");
		
		existingDepartment.get().setDepartmentName(department.getDepartmentName());
		
		return existingDepartment.get();
	}
	
	

	@Override
	public Department findDepartmentById(Integer departmentId) throws AdminException {
		
		if (departmentId == null) {
			throw new AdminException("DepartmentId can't be null");
		}

		Optional<Department> existingDepartment = departmentRepository.findById(departmentId);
		
		if(existingDepartment == null) 
			throw new AdminException("Department doesn't exist with given departmentId");
		
		return existingDepartment.get();
	}
	
	

	@Override
	public String addOperator(Operator operator) throws AdminException {
		
		if (operator == null) {
			throw new AdminException("Operator can't be null");
		}

		Optional<Operator> existingOperator = operatorRepository.findById(operator.getOperatorId());
		
		if(existingOperator.isPresent()) 
			throw new AdminException("Operator Already Registered with given operatorId");
		
		operatorRepository.save(operator);
		
		return "Operator added successfully";
	}
	
	

	@Override
	public String removeOperator(Integer operatorId) throws AdminException {
		
		if (operatorId == null) {
			throw new AdminException("OperatorId can't be null");
		}
		
		Optional<Operator> existingOperator = operatorRepository.findById(operatorId);
		
		if(existingOperator == null) 
			throw new AdminException("Operator doesn't exist with with given operatorId");
		
		operatorRepository.deleteById(operatorId);
		
		return "Operator deleted successfully";
	}
	
	

	@Override
	public Operator modifyOperator(Operator operator) throws AdminException {
		
		if (operator == null) {
			throw new AdminException("Operator can't be null");
		}

		Optional<Operator> existingOperator = operatorRepository.findById(operator.getOperatorId());
		
		if(existingOperator == null) 
			throw new AdminException("Operator doesn't exist with with given operatorId");
		
		existingOperator.get().setName(operator.getName());
		existingOperator.get().setEmail(operator.getEmail());
		existingOperator.get().setPassword(operator.getPassword());
		existingOperator.get().setMobile(operator.getMobile());
		existingOperator.get().setCity(operator.getCity());
		
		return existingOperator.get();
		
	}

	
	
	@Override
	public Operator findOperatorById(Integer operatorId) throws AdminException {
		
		if (operatorId == null) {
			throw new AdminException("OperatorId can't be null");
		}
		
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



	@Override
	public CurrentAdminSession loginAdmin(Login login) throws AdminException, LoginException {

		Admin existingAdmin = adminRepository.findByUsername(login.getUsername());
		
		if(existingAdmin == null) {
			throw new LoginException("Please Enter a valid username number");
		}	
		
		Optional<CurrentAdminSession> validAdminSessionOpt = currentAdminSessionRepository.findById(existingAdmin.getAdminId());
		
		if(validAdminSessionOpt.isPresent()) {
			throw new LoginException("User already Logged In with this username");	
		}
		
		if(existingAdmin.getPassword().equals(login.getPassword())) {
			
			String key= RandomString.make(6);
			
			CurrentAdminSession currentAdminSession = new CurrentAdminSession(existingAdmin.getAdminId(),key,LocalDateTime.now());
			
			currentAdminSessionRepository.save(currentAdminSession);

			return currentAdminSession;
		}
		else
			throw new LoginException("Please Enter a valid password");
	}
	

}
