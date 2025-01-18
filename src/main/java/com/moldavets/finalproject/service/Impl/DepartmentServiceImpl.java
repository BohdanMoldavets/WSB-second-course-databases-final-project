package com.moldavets.finalproject.service.Impl;

import com.moldavets.finalproject.dao.DepartmentRepository;
import com.moldavets.finalproject.entity.Department;
import com.moldavets.finalproject.service.DepartmentService;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository DEPARTMENT_REPOSITORY;
    private final EntityManager ENTITY_MANAGER;

    @Autowired
    public DepartmentServiceImpl(DepartmentRepository departmentRepository, EntityManager entityManager) {
        this.DEPARTMENT_REPOSITORY = departmentRepository;
        this.ENTITY_MANAGER = entityManager;
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
    public List<Department> getAllOrderByIdAsc() {
        return DEPARTMENT_REPOSITORY.findAllByOrderByIdAsc();
    }

    @Override
    public List<Department> getAllOrderByIdDesc() {
        return DEPARTMENT_REPOSITORY.findAllByOrderByIdDesc();
    }

    @Override
    public List<Department> getAllOrderByAbbreviationAsc() {
        return DEPARTMENT_REPOSITORY.findAllByOrderByAbbreviationAsc();
    }

    @Override
    public List<Department> getAllOrderByAbbreviationDesc() {
        return DEPARTMENT_REPOSITORY.findAllByOrderByAbbreviationDesc();
    }

    @Override
    public List<Department> getAllOrderByNameAsc() {
        return DEPARTMENT_REPOSITORY.findAllByOrderByNameAsc();
    }

    @Override
    public List<Department> getAllOrderByNameDesc() {
        return DEPARTMENT_REPOSITORY.findAllByOrderByNameDesc();
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
            String departmentName = DEPARTMENT_REPOSITORY.findById(departmentId).get().getAbbreviation();
            ENTITY_MANAGER.createQuery("UPDATE Employee SET department=null WHERE department=:departmentName")
                    .setParameter("departmentName", departmentName)
                    .executeUpdate();
            DEPARTMENT_REPOSITORY.deleteById(departmentId);
        }
    }

}
