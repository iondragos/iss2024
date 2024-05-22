package com.example.hospital.repository.interfaces;

import com.example.hospital.domain.Doctor;

import java.util.Optional;

public interface DoctorRepository extends Repository<Doctor>{
    Optional<Doctor> findByUsernamePassword(String username, String password);
}

