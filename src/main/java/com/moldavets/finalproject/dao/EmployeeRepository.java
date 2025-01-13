package com.moldavets.finalproject.dao;

import com.moldavets.finalproject.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    List<Employee> findAllByOrderByIdDesc();
    List<Employee> findAllByOrderByIdAsc();


}
