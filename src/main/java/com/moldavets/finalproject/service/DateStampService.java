package com.moldavets.finalproject.service;

import com.moldavets.finalproject.entity.DateStamp;

import java.util.List;

public interface DateStampService {

    List<DateStamp> getAll();
    DateStamp getById(int id);
    List<DateStamp> getAllOrderByIdAsc();
    List<DateStamp> getAllOrderByIdDesc();
    List<DateStamp> getAllOrderByEmployeeIdAsc();
    List<DateStamp> getAllOrderByEmployeeIdDesc();
    List<DateStamp> getAllOrderByEmployeeFirstNameAsc();
    List<DateStamp> getAllOrderByEmployeeFirstNameDesc();
    List<DateStamp> getAllOrderByEmploymentDateAsc();
    List<DateStamp> getAllOrderByEmploymentDateDesc();
    List<DateStamp> getAllOrderByPaymentDateAsc();
    List<DateStamp> getAllOrderByPaymentDateDesc();

    void save(DateStamp dateStamp);
    void update(DateStamp dateStamp);

}
