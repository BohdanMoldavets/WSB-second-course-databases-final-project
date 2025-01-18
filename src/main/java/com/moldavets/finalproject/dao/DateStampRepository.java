package com.moldavets.finalproject.dao;

import com.moldavets.finalproject.entity.DateStamp;
import org.springframework.data.jpa.repository.JpaRepository;
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

}
