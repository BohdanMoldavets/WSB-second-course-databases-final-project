package com.moldavets.finalproject.service.Impl;

import com.moldavets.finalproject.dao.DateStampRepository;
import com.moldavets.finalproject.entity.DateStamp;
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
