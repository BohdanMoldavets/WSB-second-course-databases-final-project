package com.moldavets.finalproject.service.Impl;

import com.moldavets.finalproject.repository.DateStampRepository;
import com.moldavets.finalproject.model.DateStamp;
import com.moldavets.finalproject.service.DateStampService;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DateStampServiceImpl implements DateStampService {

    private final DateStampRepository dateStampRepository;
    private final EntityManager entityManager;

    @Override
    public List<DateStamp> getAll() {
        return dateStampRepository.findAll();
    }

    @Override
    public DateStamp getById(int id) {
        return dateStampRepository.findById(id).orElse(null);
    }

    @Override
    public List<DateStamp> getAllOrderByIdAsc() {
        return dateStampRepository.findAllByOrderByIdAsc();
    }

    @Override
    public List<DateStamp> getAllOrderByIdDesc() {
        return dateStampRepository.findAllByOrderByIdDesc();
    }

    @Override
    public List<DateStamp> getAllOrderByEmployeeIdAsc() {
        return dateStampRepository.findAllByOrderByEmployeeIdAsc();
    }

    @Override
    public List<DateStamp> getAllOrderByEmployeeIdDesc() {
        return dateStampRepository.findAllByOrderByEmployeeIdDesc();
    }

    @Override
    public List<DateStamp> getAllOrderByEmployeeFirstNameAsc() {
        return dateStampRepository.findAllByOrderByEmployeeFirstNameAsc();
    }

    @Override
    public List<DateStamp> getAllOrderByEmployeeFirstNameDesc() {
        return dateStampRepository.findAllByOrderByEmployeeFirstNameDesc();
    }

    @Override
    public List<DateStamp> getAllOrderByEmploymentDateAsc() {
        return dateStampRepository.findAllByOrderByEmploymentDateAsc();
    }

    @Override
    public List<DateStamp> getAllOrderByEmploymentDateDesc() {
        return dateStampRepository.findAllByOrderByEmploymentDateDesc();
    }

    @Override
    public List<DateStamp> getAllOrderByPaymentDateAsc() {
        return dateStampRepository.findAllByOrderByPaymentDateAsc();
    }

    @Override
    public List<DateStamp> getAllOrderByPaymentDateDesc() {
        return dateStampRepository.findAllByOrderByPaymentDateDesc();
    }

    @Override
    public List<DateStamp> getAllByInputString(String query) {
        return dateStampRepository.findAllByInputString("%" + query + "%");
    }

    @Override
    @Transactional
    public void save(DateStamp dateStamp) {
        dateStampRepository.save(dateStamp);
    }

    @Override
    @Transactional
    public void update(DateStamp dateStamp) {
        entityManager.createQuery("UPDATE DateStamp SET employmentDate=:employmentDate, " +
                                     "paymentDate=:paymentDate WHERE id=:id")
                .setParameter("id", dateStamp.getId())
                .setParameter("employmentDate", dateStamp.getEmploymentDate())
                .setParameter("paymentDate", dateStamp.getPaymentDate())
                .executeUpdate();
    }


}
