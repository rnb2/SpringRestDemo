package com.rnb.restDemo.repository;

import com.rnb.restDemo.annotation.IT;
import com.rnb.restDemo.entity.Employee;
import lombok.RequiredArgsConstructor;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

@IT
@RequiredArgsConstructor
public class EmployeeRepositoryTest {

    private final EmployeeRepository repository;

    @Test
    @Rollback
    public void deleteTest() {
        Optional<Employee> employeeOptional = repository.findById(1);

        Assertions.assertTrue(employeeOptional.isPresent());

        employeeOptional.ifPresent(repository::delete);

        Assertions.assertTrue(repository.findById(1).isEmpty());


    }

    @Test
    public void findAllByFirstNameContainingIgnoreCase() {
        String fragment ="en";
        List<Employee> allByFirstNameContainingIgnoreCase = repository.findAllByFirstNameContainingIgnoreCase(fragment);

        Assertions.assertFalse(allByFirstNameContainingIgnoreCase.isEmpty());

       // Assertions.assertEquals(2, allByFirstNameContainingIgnoreCase.size());
        MatcherAssert.assertThat(allByFirstNameContainingIgnoreCase.size(), Matchers.is(2));
    }
}
