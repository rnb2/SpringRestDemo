package com.rnb.restDemo.service;

import com.rnb.restDemo.entity.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    private static final int EMPLOYEE_ID = 1;

    @Mock
    private EmployeeService employeeService;

    @Test
    public void testFindById() {

        Employee expectedResult = new Employee();
        expectedResult.setId(EMPLOYEE_ID);

        Mockito.when(employeeService.findById(EMPLOYEE_ID)).thenReturn(expectedResult);

        Employee actualResult = employeeService.findById(EMPLOYEE_ID);

        Assertions.assertNotNull(actualResult);
        Assertions.assertEquals(expectedResult, actualResult);
    }
}
