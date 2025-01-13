package com.moldavets.finalproject.service;

import com.moldavets.finalproject.entity.Salary;

import java.util.List;

public interface SalaryService {

    List<Salary> getAll();
    Salary getById(int id);
    Salary getByEmployeeId(int employeeId);

    void save(Salary salary);
    void update(Salary salary);

}
