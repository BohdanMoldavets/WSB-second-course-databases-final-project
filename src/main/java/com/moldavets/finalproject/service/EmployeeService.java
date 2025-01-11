package com.moldavets.finalproject.service;


import com.moldavets.finalproject.entity.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> getAll();
    Employee getById(Long id);
    void save(Employee employee);
    void deleteById(Long id);
}
