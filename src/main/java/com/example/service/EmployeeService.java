package com.example.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.converter.EmployeeConverter;
import com.example.dto.EmployeeDto;
import com.example.model.Address;
import com.example.model.Employee;
import com.example.repository.EmployeeRepo;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepo employeeRepo;
	@Autowired
	private EmployeeConverter converter;

	@PostConstruct
	public void initDB() {
		List<Employee> employee = IntStream.rangeClosed(1, 200).mapToObj(i -> new Employee((long) i, "name" + i,
				new Address((long) i, "mobileNum" + i, "city" + i, "state" + i))).collect(Collectors.toList());
		employeeRepo.saveAll(employee);
	}

	public List<EmployeeDto> getAllEmployees() {
		List<Employee> employee = new ArrayList<>();
		employeeRepo.findAll().forEach(employee1 -> employee.add(employee1));
		return converter.entityToDto(employee);
	}

	public Optional<EmployeeDto> getEmployeeById(int id) {
		return converter.entityToDto(employeeRepo.findById(id));
	}

	public void saveOrUpdate(Employee employee) {
		employeeRepo.save(employee);
	}

	public void delete(int id) {
		employeeRepo.deleteById(id);
	}

	public void deleteAll() {
		employeeRepo.deleteAll();
	}

	public List<EmployeeDto> employeeWithSorting(String field) {
		return converter.entityToDto(employeeRepo.findAll(Sort.by(Sort.Direction.ASC, field)));
	}

	public Page<Employee> employeeWithPagination(int offset, int pageSize) {
		Page<Employee> empPage = employeeRepo.findAll(PageRequest.of(offset, pageSize));
		return empPage;
	}

	public Page<Employee> employeeWithPaginationAndSorting(int offset, int pageSize, String field) {
		Page<Employee> empPageAndSort = employeeRepo.findAll(PageRequest.of(offset, pageSize).withSort(Sort.by(field)));
		return empPageAndSort;
	}

}
