package com.moldavets.finalproject.service;

import com.moldavets.finalproject.entity.Department;

import java.util.List;

public interface DepartmentService {
    List<Department> getAll();
    Department getById(int id);

    List<Department> getAllOrderByIdAsc();
    List<Department> getAllOrderByIdDesc();
    List<Department> getAllOrderByAbbreviationAsc();
    List<Department> getAllOrderByAbbreviationDesc();
    List<Department> getAllOrderByNameAsc();
    List<Department> getAllOrderByNameDesc();

    void save(Department department);
    void delete(int departmentId);
}
