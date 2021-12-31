package com.example.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.EmployeeDto;
import com.example.model.Employee;
import com.example.service.EmployeeService;

@RestController
@RequestMapping("/Employee")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@PostMapping("/getAllEmp")
	private List<EmployeeDto> getAllEmployee() {
		List<EmployeeDto> allEmp = employeeService.getAllEmployees();
		return allEmp;
	}

	@PostMapping("/getEmpById/{empId}")
	private EmployeeDto getEmployeeById(@PathVariable int empId) {
		Optional<EmployeeDto> opt = employeeService.getEmployeeById(empId);
		if (opt.isPresent()) {
			return opt.get();
		} else {
			return null;
		}
	}

	@PostMapping("/deleteEmp/{empId}")
	private void deleteEmployee(@PathVariable int empId) {
		employeeService.delete(empId);
	}

	@PostMapping("/deleteAllEmp")
	private void deleteAllEmployee() {
		employeeService.deleteAll();
	}

	@PostMapping(value = "/addEmp", consumes = { MediaType.ALL_VALUE })
	private String addEmployee(@RequestBody Employee employee) {
		employeeService.saveOrUpdate(employee);
		return "EmpId :" + employee.getId() + "added";
	}

	@PostMapping("/updateEmp")
	private Employee updateEmployee(@RequestBody Employee employee) {
		employeeService.saveOrUpdate(employee);
		return employee;
	}

	@PostMapping("/sorting/{field}")
	private List<EmployeeDto> getProductsWithSort(@PathVariable String field) {
		List<EmployeeDto> allEmployees = employeeService.employeeWithSorting(field);
		return allEmployees;
	}

	@PostMapping("/pagination/{offset}/{pageSize}")
	private Page<Employee> getProductsWithPagination(@PathVariable int offset, @PathVariable int pageSize) {
		Page<Employee> employeeWithPagination = employeeService.employeeWithPagination(offset, pageSize);
		return employeeWithPagination;
	}

	@PostMapping("/paginationAndSort/{offset}/{pageSize}/{field}")
	private Page<Employee> getProductsWithPaginationAndSort(@PathVariable int offset, @PathVariable int pageSize,
			@PathVariable String field) {
		Page<Employee> employeeWithPaginationAndSort = employeeService.employeeWithPaginationAndSorting(offset,
				pageSize, field);
		return employeeWithPaginationAndSort;
	}
}
