package com.moldavets.finalproject.service.Impl;

import com.moldavets.finalproject.dao.EmployeeRepository;
import com.moldavets.finalproject.entity.Employee;
import com.moldavets.finalproject.service.EmployeeService;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository EMPLOYEE_REPOSITORY;
    private final EntityManager ENTITY_MANAGER;

    public EmployeeServiceImpl (EmployeeRepository employeeRepository, EntityManager entityManager) {
        this.EMPLOYEE_REPOSITORY = employeeRepository;
        this.ENTITY_MANAGER = entityManager;
    }

    @Override
    public List<Employee> getAll() {
        return EMPLOYEE_REPOSITORY.findAll();
    }

    @Override
    public Employee getById(Long id) {
        return EMPLOYEE_REPOSITORY.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void save(Employee employee) {
        EMPLOYEE_REPOSITORY.save(employee);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        if(EMPLOYEE_REPOSITORY.existsById(id)) {
            EMPLOYEE_REPOSITORY.deleteById(id);
        }
    }

}
