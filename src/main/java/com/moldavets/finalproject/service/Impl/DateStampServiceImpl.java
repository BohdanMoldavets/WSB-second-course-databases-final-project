package com.moldavets.finalproject.service.Impl;

import com.moldavets.finalproject.dao.DateStampRepository;
import com.moldavets.finalproject.entity.DateStamp;
import com.moldavets.finalproject.entity.Salary;
import com.moldavets.finalproject.service.DateStampService;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class DateStampServiceImpl implements DateStampService {

    private final DateStampRepository DATE_STAMP_REPOSITORY;
    private final EntityManager ENTITY_MANAGER;

    @Autowired
    public DateStampServiceImpl(DateStampRepository DATE_STAMP_REPOSITORY,
                                EntityManager ENTITY_MANAGER) {
        this.DATE_STAMP_REPOSITORY = DATE_STAMP_REPOSITORY;
        this.ENTITY_MANAGER = ENTITY_MANAGER;
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
    public List<DateStamp> getAllOrderByIdAsc() {
        return DATE_STAMP_REPOSITORY.findAllByOrderByIdAsc();
    }

    @Override
    public List<DateStamp> getAllOrderByIdDesc() {
        return DATE_STAMP_REPOSITORY.findAllByOrderByIdDesc();
    }

    @Override
    public List<DateStamp> getAllOrderByEmployeeIdAsc() {
        return DATE_STAMP_REPOSITORY.findAllByOrderByEmployeeIdAsc();
    }

    @Override
    public List<DateStamp> getAllOrderByEmployeeIdDesc() {
        return DATE_STAMP_REPOSITORY.findAllByOrderByEmployeeIdDesc();
    }

    @Override
    public List<DateStamp> getAllOrderByEmployeeFirstNameAsc() {
        return DATE_STAMP_REPOSITORY.findAllByOrderByEmployeeFirstNameAsc();
    }

    @Override
    public List<DateStamp> getAllOrderByEmployeeFirstNameDesc() {
        return DATE_STAMP_REPOSITORY.findAllByOrderByEmployeeFirstNameDesc();
    }

    @Override
    public List<DateStamp> getAllOrderByEmploymentDateAsc() {
        return DATE_STAMP_REPOSITORY.findAllByOrderByEmploymentDateAsc();
    }

    @Override
    public List<DateStamp> getAllOrderByEmploymentDateDesc() {
        return DATE_STAMP_REPOSITORY.findAllByOrderByEmploymentDateDesc();
    }

    @Override
    public List<DateStamp> getAllOrderByPaymentDateAsc() {
        return DATE_STAMP_REPOSITORY.findAllByOrderByPaymentDateAsc();
    }

    @Override
    public List<DateStamp> getAllOrderByPaymentDateDesc() {
        return DATE_STAMP_REPOSITORY.findAllByOrderByPaymentDateDesc();
    }

    @Override
    @Transactional
    public void save(DateStamp dateStamp) {
        DATE_STAMP_REPOSITORY.save(dateStamp);
    }

    @Override
    @Transactional
    public void update(DateStamp dateStamp) {
        ENTITY_MANAGER.createQuery("UPDATE DateStamp SET employmentDate=:employmentDate, " +
                                     "paymentDate=:paymentDate WHERE id=:id")
                .setParameter("id", dateStamp.getId())
                .setParameter("employmentDate", dateStamp.getEmploymentDate())
                .setParameter("paymentDate", dateStamp.getPaymentDate())
                .executeUpdate();
    }


}
