package com.moldavets.finalproject.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Data
@Entity
@Table(name="employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="department")
    private String department;

    @Column(name="birthday")
    private java.util.Date birthday;

    @OneToOne(mappedBy = "employee",
              cascade = CascadeType.ALL)
    private Salary salary;

    @OneToOne(mappedBy = "employee",
            cascade = CascadeType.ALL)
    private com.moldavets.finalproject.entity.Date date;

    public Employee() {
    }

    public Employee(String firstName, String lastName, String department, Date birthday) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
        this.birthday = birthday;
    }
}
