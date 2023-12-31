package com.luv2code.springboot.thymeleafdemo.service;

import com.luv2code.springboot.thymeleafdemo.entity.Employee;

import java.util.List;
import java.util.Map;

public interface EmployeeService {

    List<Employee> findAll();

    Employee findById(int theId);

    Map<Integer, Employee> save(Employee theEmployee);

    Employee deleteById(int theId);

}
