package com.moldavets.finalproject.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @NotNull(message = "is required")
    @Size(min=1, max = 50, message = "is required")
    @Pattern(regexp = "([A-Za-z])+", message = "Only letters are allowed for this field")
    @Column(name="first_name")
    private String firstName;

    @NotNull(message = "is required")
    @Size(min=1, max = 50, message = "is required")
    @Pattern(regexp = "([A-Za-z])+", message = "Only letters are allowed for this field")
    @Column(name="last_name")
    private String lastName;

    @Size(max = 3, message = "is required")
    @Column(name="department", length = 3)
    private String department;

    @NotNull(message = "is required")
    @Column(name="birthday", length = 12)
    private String birthday;

    @OneToOne(mappedBy = "employee",
              cascade = CascadeType.ALL)
    private Salary salary;

    @OneToOne(mappedBy = "employee",
            cascade = CascadeType.ALL)
    private DateStamp date;

    public Employee() {
    }

    public Employee(String firstName, String lastName, String department, String birthday) {
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

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public Salary getSalary() {
        return salary;
    }

    public void setSalary(Salary salary) {
        this.salary = salary;
    }

    public DateStamp getDate() {
        return date;
    }

    public void setDate(DateStamp date) {
        this.date = date;
    }
}
