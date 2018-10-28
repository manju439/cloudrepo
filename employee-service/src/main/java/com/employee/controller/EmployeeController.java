package com.employee.controller;

import com.employee.model.Company;
import com.employee.model.Employee;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class EmployeeController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    private static final Logger LOG = LogManager.getLogger(EmployeeController.class);

    @RequestMapping(value = "/v1/employee", method = RequestMethod.GET)
    public Employee getEmployeeDetails() {
        return new Employee(123, "Manju");
    }

    @RequestMapping(value = "/v1/employee/company", method = RequestMethod.GET)
    @HystrixCommand(fallbackMethod = "getEmployeeDefaultCompany")
    public String getEmployeeCompany() {
        ServiceInstance companyServiceInstance = discoveryClient.getInstances(
                "COMPANY-SERVICE").get(0);
        String companyServiceUrl = companyServiceInstance.getUri().toString();
        LOG.info("companyServiceUrl={}", companyServiceUrl);
        Company company = restTemplate.getForObject(companyServiceUrl
                + "/v1/company", Company.class);
        return "";
    }

    public String getEmployeeDefaultCompany() {
        LOG.info("Getting Default Company Details");
        return "defaultCompany";
    }

    @RequestMapping(value = "/v1", method = RequestMethod.GET)
    public String getHomePage() {
        LOG.info("invoking default ");
        return "Hello World!!!";
    }

}
