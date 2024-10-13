package com.hoaiphong.carrental.services.impl;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hoaiphong.carrental.dtos.user.UserDTOBase;
import com.hoaiphong.carrental.entities.Role;
import com.hoaiphong.carrental.entities.User;
import com.hoaiphong.carrental.repositories.RoleRepository;
import com.hoaiphong.carrental.repositories.UserRepository;
import com.hoaiphong.carrental.services.AuthService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class AuthServiceImpl implements AuthService, UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder,
                           RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;

        this.roleRepository = roleRepository;
    }

    @Override
    public UUID save(UserDTOBase userDTOBase) {
        var existingUser = existsByUsername(userDTOBase.getUsername());
        if (existingUser) {
            throw new IllegalArgumentException("Username already exists");
        }

        var user = new User();

        user.setId(userDTOBase.getId());
        user.setName(userDTOBase.getName());
        user.setDateOfBirth(userDTOBase.getDateOfBirth());
        user.setNationalId(userDTOBase.getNationalId());
        user.setPhone(userDTOBase.getPhone());
        user.setAddress(userDTOBase.getAddress());
        user.setEmail(userDTOBase.getEmail());
        user.setDrivingLicense(userDTOBase.getDrivingLicense());
        user.setWallet(userDTOBase.getWallet());
        user.setUsername(userDTOBase.getUsername());
        user.setEmail(userDTOBase.getEmail());
        user.setPassword(passwordEncoder.encode(userDTOBase.getPassword()));
        if (userDTOBase.getRoleName() != null && !userDTOBase.getRoleName().isEmpty()) {

            var existingRoles = roleRepository.findAll();
            System.out.println(existingRoles);

            Set<Role> roles = userDTOBase.getRoleName().stream()
                    .map(roleName -> {
                        var existedRole = existingRoles.stream()
                                .filter(role -> role.getName().equals(roleName))
                                .findFirst()
                                .orElse(null);

                        if (existedRole == null) {
                            throw new IllegalArgumentException("Role with name " + roleName + " does not exist");
                        }

                        return existedRole;
                    })
                    .collect(Collectors.toSet());

            user.setRoles(roles);
        }

        userRepository.save(user);

        return user.getId();
    }

    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        System.out.println(user);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }

        // Lấy các quyền (roles) của người dùng
        Set<GrantedAuthority> grantedAuthorities = user.getRoles().stream()
                .map(auth -> "ROLE_" + auth.getName())
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toSet());

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
                grantedAuthorities);
    }
}