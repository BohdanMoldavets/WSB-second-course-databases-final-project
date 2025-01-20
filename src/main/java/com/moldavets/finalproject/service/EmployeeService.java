package com.moldavets.finalproject.service;


import com.moldavets.finalproject.entity.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> getAll();
    List<Employee> getAllOrderByIdAsc();
    List<Employee> getAllOrderByIdDesc();
    List<Employee> getAllByOrderByFirstNameAsc();
    List<Employee> getAllByOrderByFirstNameDesc();
    List<Employee> getAllByOrderByLastNameAsc();
    List<Employee> getAllByOrderByLastNameDesc();
    List<Employee> getAllByOrderByDepartmentAsc();
    List<Employee> getAllByOrderByDepartmentDesc();
    List<Employee> getAllByOrderByBirthdayAsc();
    List<Employee> getAllByOrderByBirthdayDesc();

    List<Employee> getAllByInputString(String query);

    Employee getById(int id);
    void save(Employee employee);
    void deleteById(int id);
}
