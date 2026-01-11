package com.moldavets.finalproject.repository;

import com.moldavets.finalproject.model.Salary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
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

    @Query("SELECT s FROM Salary s WHERE CAST(s.id AS string) LIKE ?1 OR " +
            "CAST(s.employee.id AS string) LIKE ?1 OR " +
            "s.employee.firstName LIKE ?1 OR " +
            "s.employee.lastName LIKE ?1 OR " +
            "CAST(s.amount AS string ) LIKE ?1 OR " +
            "s.currency LIKE ?1")
    List<Salary> findAllByInputString(String query);
}
