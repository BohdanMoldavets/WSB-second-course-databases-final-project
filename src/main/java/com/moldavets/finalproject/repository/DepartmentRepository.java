package com.moldavets.finalproject.repository;

import com.moldavets.finalproject.model.Department;
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
