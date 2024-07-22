package com.luv2code.demo.repostories;

import com.luv2code.demo.entity.Employee;
import com.luv2code.demo.service.EmployeeServiceImplementation;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

    private final EmployeeServiceImplementation employeeService;

    @Autowired
    public EmployeeRestController(EmployeeServiceImplementation theEmployeeService) {
        employeeService = theEmployeeService;
    }

    @GetMapping("/employees")
    public Page<Employee> findAll(Pageable pageable) {
        return employeeService.findAll(pageable);
    }

    @GetMapping("/employees/{employeeId}")
    public Employee getEmployee(@PathVariable int employeeId) {
        Employee theEmployee = employeeService.findById(employeeId);
        if (theEmployee == null) {
            throw new RuntimeException("Employee id not found - " + employeeId);
        }
        return theEmployee;
    }

    @Transactional
    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee theEmployee) {
        theEmployee.setId(0);
        return employeeService.save(theEmployee);
    }

    @GetMapping("/employees/search")
    public Page<Employee> search(@RequestParam("key") String key,
                                 @RequestParam("operation") String operation,
                                 @RequestParam("value") String value,
                                 Pageable pageable) {
        return employeeService.search(key, operation, value, pageable);
    }
    @GetMapping("/employees/count")
    public int countByFirstName(@RequestParam("firstName") String firstName) {
        return employeeService.countByFirstName(firstName);
    }

    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee theEmployee) {
        return employeeService.save(theEmployee);
    }

    @DeleteMapping("/employees/{employeeId}")
    public String deleteEmployee(@PathVariable int employeeId) {
        Optional<Employee> tempEmployee = Optional.ofNullable(employeeService.findById(employeeId));
        if (tempEmployee.isEmpty()) {
            throw new RuntimeException("Employee id not found - " + employeeId);
        }
        employeeService.deleteById(employeeId);
        return "Deleted employee id - " + employeeId;
    }
}
