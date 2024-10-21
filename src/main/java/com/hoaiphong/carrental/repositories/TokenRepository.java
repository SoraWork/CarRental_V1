package com.hoaiphong.carrental.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hoaiphong.carrental.entities.PasswordResetToken;

public interface TokenRepository extends JpaRepository<PasswordResetToken, Integer> {

    PasswordResetToken findByToken(String token);

}
