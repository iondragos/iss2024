package com.example.hospital.service;

import com.example.hospital.domain.Admin;
import com.example.hospital.domain.Doctor;
import com.example.hospital.domain.Medicine;
import com.example.hospital.domain.Pharmacist;
import com.example.hospital.repository.interfaces.*;

import java.util.List;
import java.util.Optional;

public class Service {
    private AdminRepository adminRepository;
    private DoctorRepository doctorRepository;
    private PharmacistRepository pharmacistRepository;
    private MedicineRepository medicineRepository;
    private OrderRepository orderRepository;
    private MedicineOrderRepository medicineOrderRepository;
    private SectionRepository sectionRepository;


    public Service(AdminRepository adminRepository, DoctorRepository doctorRepository, PharmacistRepository pharmacistRepository, MedicineRepository medicineRepository, OrderRepository orderRepository, MedicineOrderRepository medicineOrderRepository, SectionRepository sectionRepository) {
        this.adminRepository = adminRepository;
        this.doctorRepository = doctorRepository;
        this.pharmacistRepository = pharmacistRepository;
        this.medicineRepository = medicineRepository;
        this.orderRepository = orderRepository;
        this.medicineOrderRepository = medicineOrderRepository;
        this.sectionRepository = sectionRepository;
    }

    public Optional<Admin> findAdmin(String username, String password){
        return adminRepository.findByUsernamePassword(username, password);
    }

    public Optional<Doctor> findDoctor(String username, String password){
        return doctorRepository.findByUsernamePassword(username, password);
    }

    public Optional<Pharmacist> findPharmacist(String username, String password){
        return pharmacistRepository.findByUsernamePassword(username, password);
    }

    public Iterable<Medicine> getAllMedicine(){
        return medicineRepository.findAll();
    }

}
