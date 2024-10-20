package com.hoaiphong.carrental.dtos.transaction;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionUpdateWalletDTO {

    private UUID id;

    private double amount;

    private String type;

    private LocalDateTime dateTime;

}
