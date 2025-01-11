package com.moldavets.finalproject.entity;

import com.moldavets.finalproject.utils.DateUtils;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import java.util.Date;
import java.util.stream.Stream;

@Entity
@Table(name="employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @NotNull(message = "is required")
    @Size(min=1, max = 50, message = "is required")
    @Column(name="first_name")
    private String firstName;

    @NotNull(message = "is required")
    @Size(min=1, max = 50, message = "is required")
    @Column(name="last_name")
    private String lastName;

    @NotNull(message = "is required")
    @Size(max = 3)
    @Column(name="department", length = 3)
    private String department;

    @NotNull(message = "is required")
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public java.util.Date getBirthday() {
        return birthday;
    }

    public String getBirthdayString() {
        return DateUtils.parseDateToString(this.birthday);
    }

    public void setBirthdayString(String birthday) {
        this.birthday = DateUtils.parseStringToDate(birthday);
    }

    public void setBirthday(String birthday) {
        this.birthday = DateUtils.parseStringToDate(birthday);
    }

//    public void setBirthday(Date birthday) {
//        this.birthday = birthday;
//    }

    public Salary getSalary() {
        return salary;
    }

    public void setSalary(Salary salary) {
        this.salary = salary;
    }

    public com.moldavets.finalproject.entity.Date getDate() {
        return date;
    }

    public void setDate(com.moldavets.finalproject.entity.Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", department='" + department + '\'' +
                ", birthday=" + birthday +
                ", salary=" + salary +
                ", date=" + date +
                '}';
    }
}
