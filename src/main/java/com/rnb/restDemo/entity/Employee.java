package com.rnb.restDemo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * INSERT INTO public.employee
 * (id, email, first_name, last_name)
 * VALUES(nextval('employee_id_seq'::regclass), 'rnb@gmail.com', 'rnb', 'budukh');
 * VALUES(nextval('employee_id_seq'::regclass), 'Lena@gmail.com', 'Lena', 'budukh');
 * commit
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employee")
public class Employee implements BaseEntity<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column
    private String email;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "company_id")
    private Company company;

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
