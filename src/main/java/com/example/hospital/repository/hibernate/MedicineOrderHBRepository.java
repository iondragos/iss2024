package com.example.hospital.repository.hibernate;

import com.example.hospital.domain.Medicine;
import com.example.hospital.domain.MedicineOrder;
import com.example.hospital.domain.Order;
import com.example.hospital.repository.interfaces.MedicineOrderRepository;
import org.hibernate.SessionFactory;

import java.util.List;
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
        try(var session = sessionFactory.openSession()){
            return session.createQuery("SELECT MO FROM MedicineOrder MO", MedicineOrder.class).list();
        }
        catch (Exception e){
            return List.of();
        }
    }

    @Override
    public Optional<MedicineOrder> save(MedicineOrder entity) {
        try(var session = sessionFactory.openSession()){
            var transaction = session.beginTransaction();
            session.save(entity);
            transaction.commit();
            return Optional.of(entity);
        }
        catch (Exception e){
            return Optional.empty();
        }
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
