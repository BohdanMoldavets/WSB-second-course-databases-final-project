package com.moldavets.finalproject.service;

import com.moldavets.finalproject.entity.Department;

import java.util.List;

public interface DepartmentService {
    List<Department> getAll();
    Department getById(int id);

    void save(Department department);
    void delete(int departmentId);
}
