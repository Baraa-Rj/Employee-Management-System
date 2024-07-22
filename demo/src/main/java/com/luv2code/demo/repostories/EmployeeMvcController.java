package com.luv2code.demo.repostories;

import com.luv2code.demo.entity.Employee;
import com.luv2code.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/employees")
public class EmployeeMvcController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeMvcController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public String listEmployees(Model model) {
        model.addAttribute("employees", employeeService.findAll());
        return "list-employees";
    }

    @GetMapping("/{id}")
    public String viewEmployee(@PathVariable int id, Model model) {
        model.addAttribute("employee", employeeService.findById(id));
        return "view-employee";
    }

    @GetMapping("/add")
    public String showFormForAdd(Model model) {
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        return "employee-form";
    }

    @GetMapping("/update/{id}")
    public String showFormForUpdate(@PathVariable int id, Model model) {
        Employee employee = employeeService.findById(id);
        model.addAttribute("employee", employee);
        return "employee-form";
    }

    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee") Employee employee) {
        employeeService.save(employee);
        return "redirect:/employees";
    }

    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable int id) {
        employeeService.deleteById(id);
        return "redirect:/employees";
    }
}
