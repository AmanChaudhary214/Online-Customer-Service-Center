package com.ocsc.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ocsc.Entity.Customer;
import com.ocsc.Entity.Department;
import com.ocsc.Entity.Issue;
import com.ocsc.Entity.Operator;
import com.ocsc.Exception.AdminException;
import com.ocsc.Exception.OperatorException;
import com.ocsc.Service.AdminService;
import com.ocsc.Service.OperatorService;

import jakarta.validation.Valid;

@RestController
public class AdminController {
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private OperatorService optService;
	   
	
	
	@PostMapping("/add-department")
	public ResponseEntity<String> addDepartmentHandler(@RequestBody @Valid Department department) throws AdminException{
		
		String s = adminService.addDepartment(department);
		
		return ResponseEntity.ok(s);
}
	@DeleteMapping("/depart/delete/{deptId}/{key}")
	private ResponseEntity<String> deleteDepartment(@PathVariable @Valid Integer departmentId) throws AdminException {
		String c = adminService.removeDepartment(departmentId);
		return ResponseEntity.ok(c);
		
	}
	@PostMapping("/modify-department")
	public ResponseEntity<Department> modifyDepartmentHandler(@RequestBody @Valid Department department)throws AdminException {
		Department s = adminService.modifyDepartment(department);
		return ResponseEntity.ok(s);
	}
	
	
	
	@GetMapping("/department/{departmentId}")
	public ResponseEntity<Department> getDepartmentByIdHandler(@PathVariable @Valid Integer departmentId)throws AdminException {
		Department c = adminService.findDepartmentById(departmentId);
		return ResponseEntity.ok(c);
	}
	
	@PostMapping("/add-operator")
	public ResponseEntity<String> addOperatorHandler(@RequestBody @Valid Operator operator) throws AdminException{
		
		String s = adminService.addOperator(operator);
		
		return ResponseEntity.ok(s);
}
	
	
	@DeleteMapping("/operator/delete/{optId}/{key}")
	private ResponseEntity<String> deleteOperator(@PathVariable @Valid Integer operatorId) throws AdminException {
		String c = adminService.removeDepartment(operatorId);
		return ResponseEntity.ok(c);
		
	}
	
	@PostMapping("/modify-operator")
	public ResponseEntity<Operator> modifyOperatorHandler(@RequestBody @Valid Operator operator)throws AdminException {
		Operator s = adminService.modifyOperator(operator);
		return ResponseEntity.ok(s);
	}
	@GetMapping("/operator/{operatorId}")
	public ResponseEntity<Operator> getOperatorByIdHandler(@PathVariable @Valid Integer operatorId)throws AdminException {
		Operator c = adminService.findOperatorById(operatorId);
		return ResponseEntity.ok(c);
	}
	
	@GetMapping("operator/all")
	public ResponseEntity<List<Operator>> getAllOperator(@RequestBody@Valid Operator operator)throws AdminException{
		    return new ResponseEntity<List<Operator>>(adminService.findAllOperators(), HttpStatus.OK);
	}
}
