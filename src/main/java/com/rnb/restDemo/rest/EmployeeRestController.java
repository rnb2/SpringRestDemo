package com.rnb.restDemo.rest;

import com.rnb.restDemo.entity.Employee;
import com.rnb.restDemo.exception.RestDemoException;
import com.rnb.restDemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/rnb/api")
public class EmployeeRestController {

    private EmployeeService employeeService;

    @Autowired
    public EmployeeRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public List<Employee> getEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/employees/{employeeId:\\d+}")
    public Employee getEmployeesById(@PathVariable("employeeId") int employeeId) throws RestDemoException {
        Employee employee = employeeService.findById(employeeId);
        if (employee == null) {
            throw new RestDemoException("Employee not found for id=%d !!!".formatted(employeeId));
        }
        return employee;
    }

    @GetMapping("/employees/name/{employeeFirstName}")
    public List<Employee> getEmployeesByName(@PathVariable("employeeFirstName") String employeeFirstName) throws RestDemoException {
        List<Employee> employeeList = employeeService.findByName(employeeFirstName);
        if (employeeList.isEmpty()) {
            throw new RestDemoException("Employee not found for name=%s !!!".formatted(employeeFirstName));
        }
        return employeeList;
    }

    @PostMapping("/employees")
    public Employee save(@RequestBody Employee employee) {
        Employee saved = employeeService.save(employee);
        return saved;
    }

    @PutMapping("/employees")
    public Employee update(@RequestBody Employee employee) {
        Employee updated = employeeService.update(employee.getId(), employee);
        return updated;
    }

    @DeleteMapping("/employees/{employeeId}")
    public void delete(@PathVariable int employeeId) {
        employeeService.delete(employeeId);
    }
}
