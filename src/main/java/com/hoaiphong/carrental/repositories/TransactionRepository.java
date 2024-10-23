package com.hoaiphong.carrental.repositories;

import java.util.*;
import java.time.LocalDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hoaiphong.carrental.entities.Transaction;
import com.hoaiphong.carrental.entities.User;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, UUID> {

    List<Transaction> findByUser(User user);

    List<Transaction> findByUserAndDateTimeBetween(User user, LocalDate  startDate, LocalDate  endDate);
}