package com.luv2code.demo.service;

import com.luv2code.demo.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EmployeeService {

    Page<Employee> findAll(Pageable pageable);

    Employee findById(int theId);

    Employee save(Employee theEmployee);

    Page<Employee> search(String key, String operation, String value, Pageable pageable);

    void deleteById(int theId);

    List<Employee> findAll();
}
