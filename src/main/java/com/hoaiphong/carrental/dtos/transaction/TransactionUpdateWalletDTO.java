package com.hoaiphong.carrental.dtos.transaction;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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

    @NotNull(message = "Wallet balance cannot be null") // Thay NotBlank bằng NotNull
    @Positive(message = "Wallet balance must be positive") // Thêm ràng buộc số dương nếu cần
    private double amount;

    private String type;

    private LocalDateTime dateTime;

    private UUID userId;

}