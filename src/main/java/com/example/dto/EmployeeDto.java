package com.example.dto;

import javax.persistence.Id;

import lombok.Data;

@Data
public class EmployeeDto {

	@Id
	private Long id;
	private String name;
	private AddressDto address;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public AddressDto getAddress() {
		return address;
	}

	public void setAddress(AddressDto address) {
		this.address = address;
	}

}