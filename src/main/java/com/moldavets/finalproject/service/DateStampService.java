package com.moldavets.finalproject.service;

import com.moldavets.finalproject.entity.DateStamp;

import java.util.List;

public interface DateStampService {

    List<DateStamp> getAll();
    DateStamp getById(int id);

    void save(DateStamp dateStamp);

}
