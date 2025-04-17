package com.rnb.restDemo.service;

import com.rnb.restDemo.entity.Employee;
import com.rnb.restDemo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    private final EmployeeRepository repository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository repository) {
        this.repository = repository;
    }

    public List<Employee> getAllEmployees(){
        return repository.findAll();
    }

    @Override
    @Transactional
    public Employee save(Employee employee) {
        return repository.save(employee);
    }

    @Override
    public Optional<Employee> findById(int id) {
        return repository.findById(id);
    }

    @Override
    public List<Employee> findByName(String firstName) {
        return repository.findAllByFirstName(firstName);
    }

    @Override
    @Transactional
    public Employee update(int id, Employee employee) {
        return repository.save(employee);
    }

    @Override
    @Transactional
    public void delete(int id) {
        repository.deleteById(id);
    }
}
