package com.moldavets.finalproject.entity;

import jakarta.persistence.*;
import lombok.Data;



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
    private String currency;

    public Salary() {
    }

    public Salary(Employee employee, float amount, String currency) {
        this.employee = employee;
        this.amount = amount;
        this.currency = currency;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return "Salary{" +
                "id=" + id +
                ", employee=" + employee +
                ", amount=" + amount +
                ", currency='" + currency + '\'' +
                '}';
    }
}
