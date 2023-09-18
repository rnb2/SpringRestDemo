package com.rnb.restDemo.dao;

import com.rnb.restDemo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    List<Employee> findByFirstNameContaining(String infix);
}
