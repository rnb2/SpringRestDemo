package com.rnb.restDemo.service;

import com.rnb.restDemo.entity.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmployees();
    Employee save(Employee employee);

    Employee findById(int id);
    List<Employee> findByName(String firstName);

    Employee update(int id, Employee studentParam);
    void delete(int id);
}
