package com.example.converter;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.example.dto.AddressDto;
import com.example.dto.EmployeeDto;
import com.example.model.Address;
import com.example.model.Employee;

@Component
public class EmployeeConverter {

	public EmployeeDto entityToDto(Employee employee) {
		EmployeeDto employeeDto = new EmployeeDto();
		AddressDto addressDto = new AddressDto();

		employeeDto.setId(employee.getId());
		employeeDto.setName(employee.getName());

		Address add = employee.getAddress();
		addressDto.setAddressId(add.getAddressId());
		addressDto.setMobileNumber(add.getMobileNumber());
		addressDto.setCity(add.getCity());
		addressDto.setState(add.getState());

		employeeDto.setAddress(addressDto);
		return employeeDto;
	}

	public Optional<EmployeeDto> entityToDto(Optional<Employee> employee) {
		EmployeeDto employeeDto = new EmployeeDto();
		AddressDto addressDto = new AddressDto();

		employeeDto.setId(employee.get().getId());
		employeeDto.setName(employee.get().getName());

		Address add = employee.get().getAddress();
		addressDto.setAddressId(add.getAddressId());
		addressDto.setMobileNumber(add.getMobileNumber());
		addressDto.setCity(add.getCity());
		addressDto.setState(add.getState());

		employeeDto.setAddress(addressDto);
		return Optional.of(employeeDto);
	}

	public Employee dtoToEntity(EmployeeDto employeeDto) {
		Employee employee = new Employee();
		Address address = new Address();

		employee.setId(employeeDto.getId());
		employee.setName(employeeDto.getName());

		AddressDto addDto = employeeDto.getAddress();
		address.setAddressId(addDto.getAddressId());
		address.setMobileNumber(addDto.getMobileNumber());
		address.setCity(addDto.getCity());
		address.setState(addDto.getState());

		employee.setAddress(address);

		return employee;
	}

	public List<EmployeeDto> entityToDto(List<Employee> employee) {
		return employee.stream().map(emp -> entityToDto(emp)).collect(Collectors.toList());
	}

	public List<Employee> dtoToEntity(List<EmployeeDto> employeeDto) {
		return employeeDto.stream().map(emp -> dtoToEntity(emp)).collect(Collectors.toList());
	}

}