package com.rnb.restDemo.dao;

import com.rnb.restDemo.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDaoImpl implements EmployeeDao{

    private EntityManager entityManager;

    @Autowired
    public EmployeeDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Employee save(Employee employee) {
        //save or update an employee
        return entityManager.merge(employee);
    }

    @Override
    public Employee findById(long id) {
        return entityManager.find(Employee.class, id);
    }

    @Override
    public List<Employee> findAll() {
        TypedQuery<Employee> query = entityManager.createQuery("from Employee", Employee.class);
        return query.getResultList();
    }

    @Override
    public List<Employee> findByName(String firstName) {
        //String queryParam1= "from Employee where firstName like ?1";
        //Query query = entityManager.createQuery(queryParam1);
        //query.setParameter(1, "%"+firstName+"%");

        String queryNamedParam1= "from Employee where firstName like :nameData";
        TypedQuery<Employee> query = entityManager.createQuery(queryNamedParam1, Employee.class);
        query.setParameter("nameData", "%"+firstName+"%");

        return query.getResultList();
    }

    @Override
    public Employee update(long id, Employee employeeParam) {
        Employee found = findById(id);
        if (found == null) {
            return employeeParam;
        }
        updateField("FIRST_NAME", employeeParam.getFirstName(), found);
        updateField("LAST_NAME", employeeParam.getLastName(), found);
        updateField("EMAIL", employeeParam.getEmail(), found);
        return entityManager.merge(found);
    }

    @Override
    public void delete(long id) {
        Employee found = findById(id);
        if (found == null) {
            System.err.println("Not found employee by id=" + id + " Delete method will be not executed");
            return;
        }
        entityManager.remove(found);
    }

    private static void updateField(String fieldName, String fieldNewValue, Employee studentById) {
        if (fieldNewValue.isBlank()) {
            return;
        }
        switch (fieldName) {
            case "FIRST_NAME" -> studentById.setFirstName(fieldNewValue);
            case "LAST_NAME" ->  studentById.setLastName(fieldNewValue);
            case "EMAIL" ->  studentById.setEmail(fieldNewValue);
            default -> {  }
        }
    }
}
