package com.example.hospital.repository.hibernate;

import com.example.hospital.domain.Medicine;
import com.example.hospital.repository.interfaces.MedicineRepository;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Optional;

public class MedicineHBRepository implements MedicineRepository {
    private SessionFactory sessionFactory;

    public MedicineHBRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    @Override
    public Optional<Medicine> findOne(Long aLong) {
        return Optional.empty();
    }

    @Override
    public Iterable<Medicine> findAll() {
        try(var session = sessionFactory.openSession()){
            return session.createQuery("SELECT M FROM Medicine M", Medicine.class).list();
        }
        catch (Exception e){
            return List.of();
        }
    }

    @Override
    public Optional<Medicine> save(Medicine entity) {
        return Optional.empty();
    }

    @Override
    public Optional<Medicine> delete(Long aLong) {
        return Optional.empty();
    }

    @Override
    public Optional<Medicine> update(Medicine entity) {
        return Optional.empty();
    }

    @Override
    public Integer size() {
        return null;
    }
}
