package com.controllers;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.model.Employee;

@RestController
public class EmployeeController {
    @Autowired
    DataSource datasource;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @RequestMapping(value = "/employee/{empid}", method = RequestMethod.GET)
    public String getEmployeeDetailsById(@PathVariable("empid") String id) {

        StringBuilder query = new StringBuilder("select * from employee where empid='" + id + "'");
        List<Employee> el = jdbcTemplate.query(query.toString(), new BeanPropertyRowMapper(Employee.class));

        if (!el.isEmpty()) return el.get(0).toString();
        else return "No Matched Records Found";
    }

}
