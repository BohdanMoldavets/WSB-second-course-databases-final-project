package com.moldavets.finalproject.dao;

import com.moldavets.finalproject.entity.DateStamp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DateStampRepository extends JpaRepository<DateStamp, Integer> {

    List<DateStamp> findAllByOrderByIdAsc();
    List<DateStamp> findAllByOrderByIdDesc();
    List<DateStamp> findAllByOrderByEmployeeIdAsc();
    List<DateStamp> findAllByOrderByEmployeeIdDesc();
    List<DateStamp> findAllByOrderByEmployeeFirstNameAsc();
    List<DateStamp> findAllByOrderByEmployeeFirstNameDesc();
    List<DateStamp> findAllByOrderByEmploymentDateAsc();
    List<DateStamp> findAllByOrderByEmploymentDateDesc();
    List<DateStamp> findAllByOrderByPaymentDateAsc();
    List<DateStamp> findAllByOrderByPaymentDateDesc();

    @Query("SELECT d FROM DateStamp d where CAST(d.id AS string) LIKE ?1 OR " +
            "CAST(d.employee.id AS string) LIKE ?1 OR " +
            "d.employee.firstName LIKE ?1 OR " +
            "d.employee.lastName LIKE ?1 OR " +
            "d.employmentDate LIKE ?1 OR " +
            "d.paymentDate LIKE ?1")
    List<DateStamp> findAllByInputString(String query);
}
