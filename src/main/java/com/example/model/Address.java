package com.example.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long addressId;
	private String mobileNumber;
	private String city;
	private String state;
	@OneToMany(mappedBy = "address")
	private List<Employee> employeeList;

	public Address(Long addressId, String mobileNumber, String city, String state) {
		super();
		this.addressId = addressId;
		this.mobileNumber = mobileNumber;
		this.city = city;
		this.state = state;
	}

	public Address() {

	}

	public Long getAddressId() {
		return addressId;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

}
