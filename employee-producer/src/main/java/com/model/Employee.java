package com.model;

public class Employee {
	private String empid;
	private String name;
	private String designation;
	private String city;

	public Employee() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getEmpId() {
		return empid;
	}

	public void setEmpId(String empid) {
		this.empid = empid;
	}

	@Override
	public String toString() {
		return "Employee [empid=" + empid + ", name=" + name + ", designation=" + designation + ", city=" + city + "]";
	}

}
