package com.moldavets.finalproject.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="dates")
public class Date {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="employee_id")
    private Employee employee;

    @Column(name="employment_day")
    private java.util.Date employmentDate;

    @Column(name="payment_date")
    private java.util.Date paymentDate;

    public Date() {
    }

    public Date(Employee employee, java.util.Date employmentDate, java.util.Date paymentDate) {
        this.employee = employee;
        this.employmentDate = employmentDate;
        this.paymentDate = paymentDate;
    }
}
