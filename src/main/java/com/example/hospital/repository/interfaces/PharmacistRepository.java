package com.example.hospital.repository.interfaces;

import com.example.hospital.domain.Doctor;
import com.example.hospital.domain.Pharmacist;

import java.util.Optional;

public interface PharmacistRepository extends Repository<Pharmacist> {
    Optional<Pharmacist> findByUsernamePassword(String username, String password);
}
