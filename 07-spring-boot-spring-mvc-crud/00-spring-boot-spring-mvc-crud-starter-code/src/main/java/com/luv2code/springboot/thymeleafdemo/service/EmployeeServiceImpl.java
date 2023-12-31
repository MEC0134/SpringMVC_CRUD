package com.luv2code.springboot.thymeleafdemo.service;

import com.luv2code.springboot.thymeleafdemo.dao.EmployeeRepository;
import com.luv2code.springboot.thymeleafdemo.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository theEmployeeRepository) {
        employeeRepository = theEmployeeRepository;
    }


    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAllByOrderByLastNameAsc();
    }

    @Override
    public Employee findById(int theId) {
        Optional<Employee> result = employeeRepository.findById(theId);

        Employee theEmployee = null;

        if (result.isPresent()) {
            theEmployee = result.get();
        }
        else {
            // we didn't find the employee
            throw new RuntimeException("Did not find employee id - " + theId);
        }

        return theEmployee;
    }

    @Override
    public Map<Integer, Employee> save(Employee theEmployee) {

        Map<Integer, Employee> theEmp = new HashMap<Integer, Employee>();

        theEmp.put(theEmployee.getId(), theEmployee);

        Employee savedEmp = employeeRepository.save(theEmployee);

        return theEmp;
    }


    @Override
    public Employee deleteById(int theId) {

        Optional<Employee> optionalEmployee = employeeRepository.findById(theId);

        if(optionalEmployee.isPresent()) {
            Employee deletedEmployee = optionalEmployee.get();
            employeeRepository.deleteById(theId);
            return deletedEmployee;
        } else {
            //employee not found
            return null;
        }
    }




}






