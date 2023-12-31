package com.luv2code.springboot.thymeleafdemo.Controller;

import com.luv2code.springboot.thymeleafdemo.entity.Employee;
import com.luv2code.springboot.thymeleafdemo.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;

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
        return "employees/employees-list";
    }


    @GetMapping("/add-employee")
    public String showForm(Model theModel) {

        // create model attribute to bind form data
        Employee theEmployee = new Employee();

        // attribute to send to form
        theModel.addAttribute("employee", theEmployee);

        return "employees/add-employee";
    }



    @PostMapping("/save")
    public String processForm(@ModelAttribute("employee") Employee theEmployee, RedirectAttributes redirectAttributes) {

        // save employee to db and store in Map
        Map<Integer, Employee> theEmp = employeeService.save(theEmployee);

        boolean containsKeyZero = theEmp.containsKey(0);

        if(containsKeyZero) {
            redirectAttributes.addFlashAttribute("successMessage","Employee saved successfully!");
        } else {
            redirectAttributes.addFlashAttribute("successMessage","Employee updated successfully!");
        }

        // use redirect to prevent duplicate submissions
        return "redirect:/employees/list";
    }


    // show form to update
    @GetMapping("/update-employee-form")
    public String updateEmployee(@RequestParam("empId") int empId, Model theModel) {

        // get employee from db
        Employee theEmp = employeeService.findById(empId);

        // add employee to model to populate form
        theModel.addAttribute("employee", theEmp);

        // send model data to our form
        return "employees/add-employee";
    }


    @GetMapping("/delete-employee")
    public String deleteEmployee(@RequestParam("empId") int empId) {

        employeeService.deleteById(empId);

        return "redirect:/employees/list";
    }




}
