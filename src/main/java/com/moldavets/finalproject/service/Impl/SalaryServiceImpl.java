package com.moldavets.finalproject.service.Impl;

import com.moldavets.finalproject.repository.SalaryRepository;
import com.moldavets.finalproject.model.Salary;
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
    public List<Salary> getAllOrderByIdAsc() {
        return SALARY_REPOSITORY.findAllByOrderByIdAsc();
    }

    @Override
    public List<Salary> getAllOrderByIdDesc() {
        return SALARY_REPOSITORY.findAllByOrderByIdDesc();
    }

    @Override
    public List<Salary> getAllOrderByEmployeeIdAsc() {
        return SALARY_REPOSITORY.findAllByOrderByEmployeeIdAsc();
    }

    @Override
    public List<Salary> getAllOrderByEmployeeIdDesc() {
        return SALARY_REPOSITORY.findAllByOrderByEmployeeIdDesc();
    }

    @Override
    public List<Salary> getAllOrderByEmployeeFirstNameAsc() {
        return SALARY_REPOSITORY.findAllByOrderByEmployeeFirstNameAsc();
    }

    @Override
    public List<Salary> getAllOrderByEmployeeFirstNameDesc() {
        return SALARY_REPOSITORY.findAllByOrderByEmployeeFirstNameDesc();
    }

    @Override
    public List<Salary> getAllOrderByAmountAsc() {
        return SALARY_REPOSITORY.findAllByOrderByAmountAsc();
    }

    @Override
    public List<Salary> getAllOrderByAmountDesc() {
        return SALARY_REPOSITORY.findAllByOrderByAmountDesc();
    }

    @Override
    public List<Salary> getAllOrderByCurrencyAsc() {
        return SALARY_REPOSITORY.findAllByOrderByCurrencyAsc();
    }

    @Override
    public List<Salary> getAllOrderByCurrencyDesc() {
        return SALARY_REPOSITORY.findAllByOrderByCurrencyDesc();
    }

    @Override
    public List<Salary> getAllByInputString(String query) {
        return SALARY_REPOSITORY.findAllByInputString("%" + query + "%");
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
