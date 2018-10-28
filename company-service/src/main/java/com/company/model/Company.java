package com.company.model;


public class Company {
	
	String companyName;
	String companyLocation;
	
	
	
	public Company(String companyName, String companyLocation) {
		super();
		this.companyName = companyName;
		this.companyLocation = companyLocation;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getCompanyLocation() {
		return companyLocation;
	}
	public void setCompanyLocation(String companyLocation) {
		this.companyLocation = companyLocation;
	}
	

}
