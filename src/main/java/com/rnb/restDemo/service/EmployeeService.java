package com.rnb.restDemo.service;

import com.rnb.restDemo.entity.Employee;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    List<Employee> getAllEmployees();
    Employee save(Employee employee);

    Optional<Employee> findById(int id);
    List<Employee> findByName(String firstName);

    Employee update(int id, Employee studentParam);
    void delete(int id);
}
