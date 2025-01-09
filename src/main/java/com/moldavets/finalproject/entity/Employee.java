package com.moldavets.finalproject.entity;

import com.moldavets.finalproject.utils.DateUtils;
import jakarta.persistence.*;
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

    public int getId() {
        return id;
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
