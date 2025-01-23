package com.moldavets.finalproject.dao;

import com.moldavets.finalproject.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    List<Employee> findAllByOrderByIdDesc();
    List<Employee> findAllByOrderByIdAsc();
    List<Employee> findAllByOrderByFirstNameAsc();
    List<Employee> findAllByOrderByFirstNameDesc();
    List<Employee> findAllByOrderByLastNameAsc();
    List<Employee> findAllByOrderByLastNameDesc();
    List<Employee> findAllByOrderByDepartmentAsc();
    List<Employee> findAllByOrderByDepartmentDesc();
    List<Employee> findAllByOrderByBirthdayAsc();
    List<Employee> findAllByOrderByBirthdayDesc();

    @Query("SELECT e FROM Employee e " +
           "WHERE CAST(e.id AS string) LIKE ?1 OR " +
           "e.firstName LIKE ?1 OR " +
           "e.lastName LIKE ?1 OR " +
           "e.department LIKE ?1 OR " +
           "CAST(e.birthday AS string) LIKE ?1")
    List<Employee> findAllByInputString(String query);




}
