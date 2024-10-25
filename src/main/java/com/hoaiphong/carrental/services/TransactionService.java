package com.hoaiphong.carrental.services;

import java.util.List;
import java.util.UUID;

import java.time.LocalDate;

import com.hoaiphong.carrental.dtos.transaction.TransactionDTO;
import com.hoaiphong.carrental.dtos.transaction.TransactionUpdateWalletDTO;
import com.hoaiphong.carrental.entities.Transaction;
import com.hoaiphong.carrental.entities.User;

public interface TransactionService {

    List<TransactionDTO> findAll();

    TransactionDTO findById(UUID id);

    TransactionDTO create(TransactionDTO transactionDTO);

    TransactionDTO create(TransactionUpdateWalletDTO transactionUpdateWalletDTO);

    List<Transaction> findByUser(User user);

    List<Transaction> findByUserAndDate(User user, LocalDate  startDate, LocalDate  endDate);

}