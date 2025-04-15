package com.rnb.restDemo.repository;

import com.rnb.restDemo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    Optional<Employee> findByFirstName(String firstName);

    List<Employee> findAllByFirstNameContainingIgnoreCase(String fragment);
}
