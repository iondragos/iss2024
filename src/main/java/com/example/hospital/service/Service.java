package com.example.hospital.service;

import com.example.hospital.domain.*;
import com.example.hospital.repository.interfaces.*;
import com.example.hospital.utils.Observable;

import java.util.List;
import java.util.Optional;

public class Service extends Observable {
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

    public Medicine getMedicine(Long id){
        return medicineRepository.findOne(id).get();
    }

    public Iterable<Medicine> getAllMedicine(){
        return medicineRepository.findAll();
    }

    public void addMedicine(String name, float price, String description, int quantity){
        medicineRepository.save(new Medicine(name, price, description, quantity));
        super.notifyObservers();
    }

    public void deleteMedicine(Long id){
        medicineRepository.delete(id);
        super.notifyObservers();
    }

    public void updateMedicine(Long id, String name, float price, String description, int quantity){
        Medicine newMedicine = new Medicine(name, price, description, quantity);
        newMedicine.setId(id);
        medicineRepository.update(newMedicine);
        super.notifyObservers();
    }

    public Iterable<Order> getAllOrders(){
        return orderRepository.findAll();
    }

    public Order addOrder(Section section, String status){
        Optional<Order> order = orderRepository.save(new Order(section, status));
        super.notifyObservers();
        return order.get();
    }

    public void updateOrder(Long id, Section section, String status){
        Order newOrder = new Order(section, status);
        newOrder.setId(id);
        orderRepository.update(newOrder);
        super.notifyObservers();
    }

    public Iterable<MedicineOrder> getAllMedicineOrders(){
        return medicineOrderRepository.findAll();
    }

    public void addMedicineOrder(Medicine medicine, Order order, int quantity){
        medicineOrderRepository.save(new MedicineOrder(medicine, order, quantity));
    }

}
