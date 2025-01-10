package com.moldavets.finalproject.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="departments")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="abbreviation", length = 3)
    private String abbreviation;

    @Column(name="name")
    private String name;

    public Department() {
    }

    public Department(String abbreviation, String name) {
        this.abbreviation = abbreviation;
        this.name = name;
    }
}
