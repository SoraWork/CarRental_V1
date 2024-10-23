package com.hoaiphong.carrental.services.impl;

import java.util.List;
import java.util.UUID;

import java.time.LocalDate;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hoaiphong.carrental.dtos.transaction.TransactionDTO;
import com.hoaiphong.carrental.dtos.transaction.TransactionUpdateWalletDTO;
import com.hoaiphong.carrental.entities.Transaction;
import com.hoaiphong.carrental.entities.User;
import com.hoaiphong.carrental.repositories.TransactionRepository;
import com.hoaiphong.carrental.repositories.UserRepository;
import com.hoaiphong.carrental.services.TransactionService;


@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository, UserRepository userRepository) {
        this.userRepository = userRepository;
        this.transactionRepository = transactionRepository;
    }

    @Override
    public List<TransactionDTO> findAll() {
        // Get List of entities
        var transactions = transactionRepository.findAll();

        // Convert entities to DTOs
        var transactionDTOs = transactions.stream().map(transaction -> {
            var transactionDTO = new TransactionDTO();
            transactionDTO.setId(transaction.getId());
            transactionDTO.setAmount(transaction.getAmount());
            transactionDTO.setType(transaction.getType());
            transactionDTO.setDateTime(transaction.getDateTime());
            transactionDTO.setBookingNo(transaction.getBookingNo());
            transactionDTO.setCarName(transaction.getCarName());
            return transactionDTO;
        }).toList();

        return transactionDTOs;
       
    }

    @Override
    public TransactionDTO findById(UUID id) {
        // Get entity by id
        var transaction = transactionRepository.findById(id).orElse(null);

        // Check if entity is null then return null
        if (transaction == null) {
            return null;
        }

        // Convert entity to DTO
        var transactionDTO = new TransactionDTO();
        transactionDTO.setId(transaction.getId());
        transactionDTO.setAmount(transaction.getAmount());
        transactionDTO.setType(transaction.getType());
        transactionDTO.setDateTime(transaction.getDateTime());
        transactionDTO.setBookingNo(transaction.getBookingNo());
        transactionDTO.setCarName(transaction.getCarName());
        return transactionDTO;
    }

 
//Create when booking
    @Override
    public TransactionDTO create(TransactionDTO transactionDTO) {
       // Check if transactionDTO is null then throw exception
        if (transactionDTO == null) {
            throw new IllegalArgumentException("Transaction is required");
        }
        // Convert DTO to entity
        var transaction = new Transaction();
        transaction.setAmount(transactionDTO.getAmount());
        transaction.setType(transactionDTO.getType());
        transaction.setDateTime(transactionDTO.getDateTime());
        transaction.setBookingNo(transactionDTO.getBookingNo());
        transaction.setCarName(transactionDTO.getCarName());
        // Save entity
        transaction = transactionRepository.save(transaction);
        // Convert entity to DTO
        var transactionDTO1 = new TransactionDTO();
        transactionDTO1.setId(transaction.getId());
        transactionDTO1.setAmount(transaction.getAmount());
        transactionDTO1.setType(transaction.getType());
        transactionDTO1.setDateTime(transaction.getDateTime());
        transactionDTO1.setBookingNo(transaction.getBookingNo());
        transactionDTO1.setCarName(transaction.getCarName());
        return transactionDTO1;

    }
//Create when wallet top-up or withdraw 
    @Override
    public TransactionDTO create(TransactionUpdateWalletDTO transactionUpdateWalletDTO) {
        // Check if transactionUpdateWalletDTO is null then throw exception
        if (transactionUpdateWalletDTO == null) {
            throw new IllegalArgumentException("Transaction is required");
        }
        // Convert DTO to entity
        var transaction = new Transaction();
        transaction.setId(transactionUpdateWalletDTO.getId());
        transaction.setAmount(transactionUpdateWalletDTO.getAmount());
        transaction.setType(transactionUpdateWalletDTO.getType());
        transaction.setDateTime(transactionUpdateWalletDTO.getDateTime());
        transaction.setBookingNo("N/A");
        transaction.setCarName("N/A");
        
        var user = new User();
        user.setId(transactionUpdateWalletDTO.getUserId());
        transaction.setUser(user);
        // Save entity
        transaction = transactionRepository.save(transaction);
        // Convert entity to DTO
        var transactionDTO = new TransactionDTO();
        transactionDTO.setId(transaction.getId());
        transactionDTO.setAmount(transaction.getAmount());
        transactionDTO.setType(transaction.getType());
        transactionDTO.setDateTime(transaction.getDateTime());
        transactionDTO.setBookingNo(transaction.getBookingNo());
        transactionDTO.setCarName(transaction.getCarName());
        transactionDTO.setUserId(transaction.getUser().getId());
        return transactionDTO;
    }

    @Override
    public List<Transaction> findByUser(User user) {
        return transactionRepository.findByUser(user);
    }

    @Override
    public List<Transaction> findByUserAndDate(User user, LocalDate  startDate, LocalDate  endDate) {
        return transactionRepository.findByUserAndDateTimeBetween(user, startDate, endDate);
    }

   

    

}