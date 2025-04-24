package com.rnb.restDemo.rest;

import com.rnb.restDemo.entity.Employee;
import com.rnb.restDemo.exception.RestDemoException;
import com.rnb.restDemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/rnb/api")
public class EmployeeRestController {

    private EmployeeService employeeService;

    @Autowired
    public EmployeeRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getEmployees(){
        List<Employee> employees = employeeService.getAllEmployees();
        if (employees != null && !employees.isEmpty()){
            return new ResponseEntity<>(employees, HttpStatus.OK);
        }
        return new ResponseEntity<>(employees, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/employees/{employeeId}")
    public ResponseEntity<Employee> getEmployeesById(@PathVariable("employeeId") int employeeId) throws RestDemoException {

        Optional<Employee> employee = employeeService.findById(employeeId);
        if (employee.isEmpty()) {
            throw new RestDemoException("Employee not found for id = " + employeeId);
        }
        return new ResponseEntity<>(employee.get(), HttpStatus.OK);
    }

    @GetMapping("/employees/name/{employeeFirstName}")
    public List<Employee> getEmployeesByName(@PathVariable("employeeFirstName") String employeeFirstName){
        return employeeService.findByName(employeeFirstName);
    }

    @PostMapping("/employees")
    public Employee save(@RequestBody Employee employee){
        Employee saved = employeeService.save(employee);
        return saved;
    }

    @PutMapping("/employees")
    public Employee update(@RequestBody Employee employee){
        Employee updated = employeeService.update(employee.getId(), employee);
        return updated;
    }
}
