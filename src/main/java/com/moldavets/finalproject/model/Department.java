package com.moldavets.finalproject.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name="departments")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @NotNull(message = "Is required")
    @Size(max = 3, message = "Abbreviation length must be equals 3 or less then 3")
    @Column(name="abbreviation", length = 3)
    private String abbreviation;

    @NotNull(message = "Is required")
    @Column(name="name")
    private String name;

    public Department() {
    }

    public Department(String abbreviation, String name) {
        this.abbreviation = abbreviation;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
