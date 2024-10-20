package com.hoaiphong.carrental.services;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.hoaiphong.carrental.dtos.user.UserDTOBase;
import com.hoaiphong.carrental.dtos.user.UserUpdateDTO;
import com.hoaiphong.carrental.dtos.user.UserUpdatePasswordDTO;

public interface UserService {
    List<UserDTOBase> findAll();

    Page<UserDTOBase> findAll(String keyword, Pageable pageable);

    UserDTOBase findById(UUID id);

    UserDTOBase create(UserDTOBase userDTOBase);

    UserDTOBase update(UUID id, UserDTOBase userDTOBase);

    UserDTOBase updatePassword(UUID id, String password);

    boolean deleteById(UUID id);

    UserUpdateDTO update(UserUpdateDTO userUpdateDTO, String email);

    UserDTOBase update(UserUpdatePasswordDTO userUpdatePasswordDTO, String email);
}