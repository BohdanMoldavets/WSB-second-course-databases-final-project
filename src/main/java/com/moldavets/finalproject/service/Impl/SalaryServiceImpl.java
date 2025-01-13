package com.moldavets.finalproject.service.Impl;

import com.moldavets.finalproject.dao.EmployeeRepository;
import com.moldavets.finalproject.dao.SalaryRepository;
import com.moldavets.finalproject.entity.Employee;
import com.moldavets.finalproject.entity.Salary;
import com.moldavets.finalproject.service.SalaryService;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class SalaryServiceImpl implements SalaryService {

    private final SalaryRepository SALARY_REPOSITORY;
    private final EntityManager ENTITY_MANAGER;

    @Autowired
    public SalaryServiceImpl(SalaryRepository SALARY_REPOSITORY, EntityManager entityManager) {
        this.SALARY_REPOSITORY = SALARY_REPOSITORY;
        this.ENTITY_MANAGER = entityManager;
    }


    @Override
    public List<Salary> getAll() {
        return SALARY_REPOSITORY.findAll();
    }

    @Override
    public Salary getById(int id) {
        return SALARY_REPOSITORY.findById(id).orElse(null);
    }

    @Override
    public Salary getByEmployeeId(int employeeId) {
        return SALARY_REPOSITORY.findByEmployeeId(employeeId);
    }

    @Override
    @Transactional
    public void save(Salary salary) {
        SALARY_REPOSITORY.save(salary);
    }

    @Override
    @Transactional
    public void update(Salary salary) {
        ENTITY_MANAGER.createQuery("UPDATE Salary SET amount=:amount, currency=:currency WHERE id=:id")
                .setParameter("amount", salary.getAmount())
                .setParameter("currency", salary.getCurrency())
                .setParameter("id", salary.getId())
                .executeUpdate();
    }
}
