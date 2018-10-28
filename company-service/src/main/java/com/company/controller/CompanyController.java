package com.company.controller;


import com.company.model.Company;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CompanyController {

    private static final Logger LOG = LogManager.getLogger(CompanyController.class);


    @RequestMapping(value = "/v1/company", method = RequestMethod.GET)
    public Company getEmployeeDetails() {
        LOG.info("returning company details");
        return new Company("xyz", "Bnglr");
    }

    @RequestMapping(value = "/v1", method = RequestMethod.GET)
    public String getHomePage() {
        return "Hello World!!!";
    }

}
