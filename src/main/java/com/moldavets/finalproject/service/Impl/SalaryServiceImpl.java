package com.moldavets.finalproject.service.Impl;

import com.moldavets.finalproject.repository.SalaryRepository;
import com.moldavets.finalproject.model.Salary;
import com.moldavets.finalproject.service.SalaryService;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SalaryServiceImpl implements SalaryService {

    private final SalaryRepository salaryRepository;
    private final EntityManager entityManager;

    @Override
    public List<Salary> getAll() {
        return salaryRepository.findAll();
    }

    @Override
    public Salary getById(int id) {
        return salaryRepository.findById(id).orElse(null);
    }

    @Override
    public List<Salary> getAllOrderByIdAsc() {
        return salaryRepository.findAllByOrderByIdAsc();
    }

    @Override
    public List<Salary> getAllOrderByIdDesc() {
        return salaryRepository.findAllByOrderByIdDesc();
    }

    @Override
    public List<Salary> getAllOrderByEmployeeIdAsc() {
        return salaryRepository.findAllByOrderByEmployeeIdAsc();
    }

    @Override
    public List<Salary> getAllOrderByEmployeeIdDesc() {
        return salaryRepository.findAllByOrderByEmployeeIdDesc();
    }

    @Override
    public List<Salary> getAllOrderByEmployeeFirstNameAsc() {
        return salaryRepository.findAllByOrderByEmployeeFirstNameAsc();
    }

    @Override
    public List<Salary> getAllOrderByEmployeeFirstNameDesc() {
        return salaryRepository.findAllByOrderByEmployeeFirstNameDesc();
    }

    @Override
    public List<Salary> getAllOrderByAmountAsc() {
        return salaryRepository.findAllByOrderByAmountAsc();
    }

    @Override
    public List<Salary> getAllOrderByAmountDesc() {
        return salaryRepository.findAllByOrderByAmountDesc();
    }

    @Override
    public List<Salary> getAllOrderByCurrencyAsc() {
        return salaryRepository.findAllByOrderByCurrencyAsc();
    }

    @Override
    public List<Salary> getAllOrderByCurrencyDesc() {
        return salaryRepository.findAllByOrderByCurrencyDesc();
    }

    @Override
    public List<Salary> getAllByInputString(String query) {
        return salaryRepository.findAllByInputString("%" + query + "%");
    }

    @Override
    @Transactional
    public void save(Salary salary) {
        salaryRepository.save(salary);
    }

    @Override
    @Transactional
    public void update(Salary salary) {
        entityManager.createQuery("UPDATE Salary SET amount=:amount, currency=:currency WHERE id=:id")
                .setParameter("amount", salary.getAmount())
                .setParameter("currency", salary.getCurrency())
                .setParameter("id", salary.getId())
                .executeUpdate();
    }
}
