package com.luv2code.demo.repostories;

import com.luv2code.demo.entity.Employee;
import org.springframework.data.jpa.domain.Specification;

public class EmployeeSpecification {

    public static Specification<Employee> hasKeyAndValue(String key, String value) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(key), value);
    }

    public static Specification<Employee> containsValue(String key, String value) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get(key), "%" + value + "%");
    }

    public static Specification<Employee> greaterThanValue(String key, String value) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.greaterThan(root.get(key), value);
    }

    public static Specification<Employee> lessThanValue(String key, String value) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.lessThan(root.get(key), value);
    }
}
