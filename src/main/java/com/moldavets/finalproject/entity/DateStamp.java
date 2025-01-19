package com.moldavets.finalproject.entity;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name="date_stamps")
public class DateStamp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="employee_id")
    private Employee employee;

    @Column(name="employment_day", length = 16)
    private String employmentDate;

    @Column(name="payment_date", length = 16)
    private String paymentDate;

    public DateStamp() {
    }

    public DateStamp(Employee employee, String employmentDate, String paymentDate) {
        this.employee = employee;
        this.employmentDate = employmentDate;
        this.paymentDate = paymentDate;
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

    public String getEmploymentDate() {
        return employmentDate;
    }

    public void setEmploymentDate(String employmentDate) {
        this.employmentDate = employmentDate;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }
}
