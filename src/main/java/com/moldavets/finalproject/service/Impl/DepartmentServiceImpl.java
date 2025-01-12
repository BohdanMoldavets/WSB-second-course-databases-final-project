package com.moldavets.finalproject.service.Impl;

import com.moldavets.finalproject.dao.DepartmentRepository;
import com.moldavets.finalproject.entity.Department;
import com.moldavets.finalproject.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository DEPARTMENT_REPOSITORY;

    @Autowired
    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        DEPARTMENT_REPOSITORY = departmentRepository;
    }

    @Override
    public List<Department> getAll() {
        return DEPARTMENT_REPOSITORY.findAll();
    }

    @Override
    public Department getById(int id) {
        return DEPARTMENT_REPOSITORY.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void save(Department department) {
        DEPARTMENT_REPOSITORY.save(department);
    }


    @Override
    @Transactional
    public void delete(int departmentId) {
        if(DEPARTMENT_REPOSITORY.existsById(departmentId)) {
            DEPARTMENT_REPOSITORY.deleteById(departmentId);
        }
    }

}
