package com.moldavets.finalproject.service;

import com.moldavets.finalproject.model.Salary;

import java.util.List;

public interface SalaryService {

    List<Salary> getAll();
    Salary getById(int id);
    List<Salary> getAllOrderByIdAsc();
    List<Salary> getAllOrderByIdDesc();
    List<Salary> getAllOrderByEmployeeIdAsc();
    List<Salary> getAllOrderByEmployeeIdDesc();
    List<Salary> getAllOrderByEmployeeFirstNameAsc();
    List<Salary> getAllOrderByEmployeeFirstNameDesc();
    List<Salary> getAllOrderByAmountAsc();
    List<Salary> getAllOrderByAmountDesc();
    List<Salary> getAllOrderByCurrencyAsc();
    List<Salary> getAllOrderByCurrencyDesc();

    List<Salary> getAllByInputString(String query);

    void save(Salary salary);
    void update(Salary salary);

}
