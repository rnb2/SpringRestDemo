package com.rnb.restDemo.service;

import com.rnb.restDemo.entity.Employee;
import com.rnb.restDemo.dao.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }

    @Override
    //@Transactional
    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee findById(int id) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        return employeeOptional.orElse(null);
    }

    @Override
    public List<Employee> findByName(String firstName) {
        return employeeRepository.findByFirstNameContaining(firstName);
    }

    @Override
    //@Transactional
    public Employee update(int id, Employee employee) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        if (employeeOptional.isPresent()) {
            Employee employeeFound = employeeOptional.get();
            employeeFound.setEmail(employee.getEmail());
            employeeFound.setFirstName(employee.getFirstName());
            employeeFound.setLastName(employee.getLastName());
            return employeeRepository.save(employeeFound);
        }

        return employee;
    }

    @Override
   // @Transactional
    public void delete(int id) {
        employeeRepository.deleteById(id);
    }
}
