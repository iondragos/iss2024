package com.example.hospital.repository.interfaces;

import com.example.hospital.domain.Admin;
import com.example.hospital.domain.Doctor;

import java.util.Optional;

public interface AdminRepository extends Repository<Admin> {
    Optional<Admin> findByUsernamePassword(String username, String password);
}
