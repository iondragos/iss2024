package com.example.hospital.repository.hibernate;

import com.example.hospital.domain.MedicineOrder;
import com.example.hospital.repository.interfaces.MedicineOrderRepository;
import org.hibernate.SessionFactory;

import java.util.Optional;

public class MedicineOrderHBRepository implements MedicineOrderRepository {
    private SessionFactory sessionFactory;

    public MedicineOrderHBRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    @Override
    public Optional<MedicineOrder> findOne(Long id) {
        return Optional.empty();
    }

    @Override
    public Iterable<MedicineOrder> findAll() {
        return null;
    }

    @Override
    public Optional<MedicineOrder> save(MedicineOrder entity) {
        return Optional.empty();
    }

    @Override
    public Optional<MedicineOrder> delete(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<MedicineOrder> update(MedicineOrder entity) {
        return Optional.empty();
    }

    @Override
    public Integer size() {
        return null;
    }
}
