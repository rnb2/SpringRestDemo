package com.rnb.restDemo.integration.service;

import com.rnb.restDemo.annotation.IT;
import com.rnb.restDemo.entity.Employee;
import com.rnb.restDemo.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;

import java.util.Optional;


@IT
@RequiredArgsConstructor
//@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL) -
// this is the same as spring.test.constructor.autowire.mode=all in the spring.properties file
public class EmployeeServiceIT {

    private static final int EMPLOYEE_ID = 1;

    private final EmployeeService employeeService;

    @Test
    void findById() {
        Employee expectedResult = new Employee();
        expectedResult.setId(EMPLOYEE_ID);
        expectedResult.setFirstName("rnb");
        expectedResult.setLastName("budukh");
        expectedResult.setEmail("rnb@gmail.com");

        Optional<Employee> actualResult = employeeService.findById(EMPLOYEE_ID);

        Assertions.assertNotNull(actualResult);

        if (actualResult.isPresent()) {
            boolean fieldsMatches = new ReflectionEquals(expectedResult).matches(actualResult.get());
            Assertions.assertTrue(fieldsMatches);
        }

    }
}
