package com.rnb.restDemo.dao;

import com.rnb.restDemo.entity.Employee;

import java.util.List;

public interface EmployeeDao {
    Employee save(Employee employee);
    Employee findById(long id);
    List<Employee> findAll();
    List<Employee> findByName(String firstName);

    Employee update(long id, Employee studentParam);
    void delete(long id);
}
