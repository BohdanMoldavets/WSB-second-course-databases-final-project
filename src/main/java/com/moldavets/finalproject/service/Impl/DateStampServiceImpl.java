package com.moldavets.finalproject.service.Impl;

import com.moldavets.finalproject.dao.DateStampRepository;
import com.moldavets.finalproject.entity.DateStamp;
import com.moldavets.finalproject.service.DateStampService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class DateStampServiceImpl implements DateStampService {

    private final DateStampRepository DATE_STAMP_REPOSITORY;

    @Autowired
    public DateStampServiceImpl(DateStampRepository DATE_STAMP_REPOSITORY) {
        this.DATE_STAMP_REPOSITORY = DATE_STAMP_REPOSITORY;
    }

    @Override
    public List<DateStamp> getAll() {
        return DATE_STAMP_REPOSITORY.findAll();
    }

    @Override
    public DateStamp getById(int id) {
        return DATE_STAMP_REPOSITORY.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void save(DateStamp dateStamp) {

    }
}
