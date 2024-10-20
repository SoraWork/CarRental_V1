package com.hoaiphong.carrental.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hoaiphong.carrental.entities.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, UUID> {

}
