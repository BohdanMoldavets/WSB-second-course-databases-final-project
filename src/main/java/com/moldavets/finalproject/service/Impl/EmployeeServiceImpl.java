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

    public EmployeeServiceImpl (EmployeeRepository employeeRepository) {
        this.EMPLOYEE_REPOSITORY = employeeRepository;
    }

    @Override
    public List<Employee> getAll() {
        return EMPLOYEE_REPOSITORY.findAllByOrderByIdAsc();
    }

    @Override
    public List<Employee> getAllOrderByIdAsc() {
        return EMPLOYEE_REPOSITORY.findAllByOrderByIdAsc();
    }

    @Override
    public List<Employee> getAllOrderByIdDesc() {
        return EMPLOYEE_REPOSITORY.findAllByOrderByIdDesc();
    }

    @Override
    public List<Employee> getAllByOrderByFirstNameAsc() {
        return EMPLOYEE_REPOSITORY.findAllByOrderByFirstNameAsc();
    }

    @Override
    public List<Employee> getAllByOrderByFirstNameDesc() {
        return EMPLOYEE_REPOSITORY.findAllByOrderByFirstNameDesc();
    }

    @Override
    public List<Employee> getAllByOrderByLastNameAsc() {
        return EMPLOYEE_REPOSITORY.findAllByOrderByLastNameAsc();
    }

    @Override
    public List<Employee> getAllByOrderByLastNameDesc() {
        return EMPLOYEE_REPOSITORY.findAllByOrderByLastNameDesc();
    }

    @Override
    public List<Employee> getAllByOrderByBirthdayAsc() {
        return EMPLOYEE_REPOSITORY.findAllByOrderByBirthdayAsc();
    }

    @Override
    public List<Employee> getAllByOrderByBirthdayDesc() {
        return EMPLOYEE_REPOSITORY.findAllByOrderByBirthdayDesc();
    }

    @Override
    public Employee getById(int id) {
        return EMPLOYEE_REPOSITORY.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void save(Employee employee) {
        EMPLOYEE_REPOSITORY.save(employee);
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        if(EMPLOYEE_REPOSITORY.existsById(id)) {
            EMPLOYEE_REPOSITORY.deleteById(id);
        }
    }

}
