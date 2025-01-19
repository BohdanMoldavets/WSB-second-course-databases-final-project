package com.moldavets.finalproject.dao;

import com.moldavets.finalproject.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {

    List<Department> findAllByOrderByIdAsc();
    List<Department> findAllByOrderByIdDesc();
    List<Department> findAllByOrderByAbbreviationAsc();
    List<Department> findAllByOrderByAbbreviationDesc();
    List<Department> findAllByOrderByNameAsc();
    List<Department> findAllByOrderByNameDesc();

}
