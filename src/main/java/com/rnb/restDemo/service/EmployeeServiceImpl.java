package com.rnb.restDemo.service;

import com.rnb.restDemo.dao.EmployeeDao;
import com.rnb.restDemo.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    private final EmployeeDao employeeDao;

    @Autowired
    public EmployeeServiceImpl(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    public List<Employee> getAllEmployees(){
        return employeeDao.findAll();
    }

    @Override
    @Transactional
    public Employee save(Employee employee) {
        return employeeDao.save(employee);
    }

    @Override
    public Employee findById(int id) {
        return employeeDao.findById(id);
    }

    @Override
    public List<Employee> findByName(String firstName) {
        return employeeDao.findByName(firstName);
    }

    @Override
    @Transactional
    public Employee update(int id, Employee employee) {
        return employeeDao.update(id, employee);
    }

    @Override
    @Transactional
    public void delete(int id) {
        employeeDao.delete(id);
    }
}
