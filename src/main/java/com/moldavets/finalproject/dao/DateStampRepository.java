package com.moldavets.finalproject.dao;

import com.moldavets.finalproject.entity.DateStamp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DateStampRepository extends JpaRepository<DateStamp, Integer> {

}
