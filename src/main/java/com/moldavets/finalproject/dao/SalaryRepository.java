package com.moldavets.finalproject.dao;

import com.moldavets.finalproject.entity.Salary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalaryRepository extends JpaRepository<Salary, Integer> {

    List<Salary> findAllByOrderByIdAsc();
    List<Salary> findAllByOrderByIdDesc();
    List<Salary> findAllByOrderByEmployeeIdAsc();
    List<Salary> findAllByOrderByEmployeeIdDesc();
    List<Salary> findAllByOrderByEmployeeFirstNameAsc();
    List<Salary> findAllByOrderByEmployeeFirstNameDesc();
    List<Salary> findAllByOrderByAmountAsc();
    List<Salary> findAllByOrderByAmountDesc();
    List<Salary> findAllByOrderByCurrencyAsc();
    List<Salary> findAllByOrderByCurrencyDesc();
}
