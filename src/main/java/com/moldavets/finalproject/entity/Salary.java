package com.moldavets.finalproject.entity;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name="salaries")
public class Salary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @Column(name="amount")
    private float amount;

    @Column(name="currency")
    private float currency;

    public Salary() {
    }

    public Salary(Employee employee, float amount, float currency) {
        this.employee = employee;
        this.amount = amount;
        this.currency = currency;
    }
}
