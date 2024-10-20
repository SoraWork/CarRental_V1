package com.hoaiphong.carrental.services;

import java.util.List;
import java.util.UUID;

import com.hoaiphong.carrental.dtos.transaction.TransactionDTO;
import com.hoaiphong.carrental.dtos.transaction.TransactionUpdateWalletDTO;

public interface TransactionService {

    List<TransactionDTO> findAll();

    TransactionDTO findById(UUID id);

    TransactionDTO create(TransactionDTO transactionDTO);

    TransactionDTO create(TransactionUpdateWalletDTO transactionUpdateWalletDTO);

}
