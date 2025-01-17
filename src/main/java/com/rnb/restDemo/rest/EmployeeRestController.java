package com.rnb.restDemo.rest;

import com.rnb.restDemo.dto.EmployeeDTO;
import com.rnb.restDemo.entity.Employee;
import com.rnb.restDemo.exception.RestDemoException;
import com.rnb.restDemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author RBudukh
 */
@RestController
@RequestMapping("/rnb/api")
public class EmployeeRestController {

    private EmployeeService employeeService;

    @Autowired
    public EmployeeRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(path = "/employees", produces = {"application/json"})
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

    @PostMapping(path = "/employees", consumes = {"application/json"})
    public Employee save(@RequestBody EmployeeDTO employeeDto) {
        Employee employee = new Employee(employeeDto.firstName(), employeeDto.lastName(), employeeDto.email());
        return employeeService.save(employee);
    }

    @PutMapping("/employees")
    public Employee update(@RequestBody Employee employee) {
        Employee updated = employeeService.update(employee.getId(), employee);
        return updated;
    }

    @DeleteMapping("/employees/{employeeId}")
    public String delete(@PathVariable int employeeId) throws RestDemoException {
        Employee employee = employeeService.findById(employeeId);
        if (employee == null) {
            throw new RestDemoException("Employee not found for id=%d !!!".formatted(employeeId));
        }
        employeeService.delete(employeeId);
        return "Deleted employee with id=" + employeeId;
    }
}
