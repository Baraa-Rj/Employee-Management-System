package com.luv2code.demo.service;

import com.luv2code.demo.dao.EmployeeRepository;
import com.luv2code.demo.entity.Employee;
import com.luv2code.demo.repostories.EmployeeSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImplementation implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImplementation(EmployeeRepository theEmployeeRepository) {
        System.out.println("EmployeeServiceImplementation: " + theEmployeeRepository);
        this.employeeRepository = theEmployeeRepository;
    }

    @Override
    public Page<Employee> findAll(Pageable pageable) {
        System.out.println("EmployeeServiceImplementation: " + employeeRepository);
        return employeeRepository.findAll(pageable);
    }

    @Override
    public Employee findById(int id) {
        return employeeRepository.findById(id).orElse(null);
    }

    @Override
    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public void deleteById(int id) {
        employeeRepository.deleteById(id);
    }

    public Page<Employee> search(String key, String operation, String value, Pageable pageable) {
        Specification<Employee> spec = switch (operation) {
            case "equals" -> EmployeeSpecification.hasKeyAndValue(key, value);
            case "contains" -> EmployeeSpecification.containsValue(key, value);
            case "greaterThan" -> EmployeeSpecification.greaterThanValue(key, value);
            case "lessThan" -> EmployeeSpecification.lessThanValue(key, value);
            default -> throw new RuntimeException("Operation not supported");
        };
        return employeeRepository.findAll(spec, pageable);
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public int countByFirstName(String firstName) {
        return employeeRepository.countByFirstName(firstName);
    }
}
