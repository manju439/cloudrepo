package com.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.model.Employee;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class EmployeeController {
    
    @RequestMapping(value = "/employee", method = RequestMethod.GET)
	@HystrixCommand(fallbackMethod = "getDataFallBack")
	public Employee firstPage() {

    	System.out.println("==========================inside original method================");
		Employee emp = new Employee();
		emp.setName("emp1");
		emp.setDesignation("manager");
		emp.setCity("Banglore");
		emp.setEmpid("3000");
		
		if(emp.getName().equalsIgnoreCase("emp1"))
			throw new RuntimeException();

		return emp;
	}

	public Employee getDataFallBack() {
		System.out.println("****************inside fallback method**************************");
		Employee emp = new Employee();
		emp.setName("fallback-emp1");
		emp.setDesignation("fallback-manager");
		emp.setEmpid("fallback-1");
		emp.setCity("fallback-city");

		return emp;
		
	}

}
