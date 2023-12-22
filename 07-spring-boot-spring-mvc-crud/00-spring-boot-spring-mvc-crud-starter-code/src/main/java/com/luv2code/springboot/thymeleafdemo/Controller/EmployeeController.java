package com.luv2code.springboot.thymeleafdemo.Controller;

import com.luv2code.springboot.thymeleafdemo.entity.Employee;
import com.luv2code.springboot.thymeleafdemo.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/employees") // base mapping for URL requests
public class EmployeeController {

    private EmployeeService employeeService;

    // constructor injection, autowired is optional since we only have one constructor
    public EmployeeController(EmployeeService theEmployeeService) {
        this.employeeService = theEmployeeService;
    }

    // add mapping for listing employees
    @GetMapping("/list")
    public String employeeList(Model theModel) {

        // retrieve employees from db
        List<Employee> employees = employeeService.findAll();

        // add employees to model
        theModel.addAttribute("employees", employees);

        // return view
        return "employees-list";
    }



}
