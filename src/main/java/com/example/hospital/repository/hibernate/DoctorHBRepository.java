package com.example.hospital.repository.hibernate;

import com.example.hospital.domain.Doctor;
import com.example.hospital.domain.Pharmacist;
import com.example.hospital.repository.interfaces.DoctorRepository;
import org.hibernate.SessionFactory;

import java.util.Optional;

public class DoctorHBRepository implements DoctorRepository {

    private SessionFactory sessionFactory;

    public DoctorHBRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    @Override
    public Optional<Doctor> findOne(Long aLong) {
        return Optional.empty();
    }

    @Override
    public Iterable<Doctor> findAll() {
        return null;
    }

    @Override
    public Optional<Doctor> save(Doctor entity) {
        return Optional.empty();
    }

    @Override
    public Optional<Doctor> delete(Long aLong) {
        return Optional.empty();
    }

    @Override
    public Optional<Doctor> update(Doctor entity) {
        return Optional.empty();
    }

    @Override
    public Integer size() {
        return null;
    }

    @Override
    public Optional<Doctor> findByUsernamePassword(String username, String password) {
        try(var session = sessionFactory.openSession()){
            var query = session.createQuery("SELECT D FROM Doctor D " +
                    "WHERE D.username like :username AND D.password like :password", Doctor.class);
            query.setParameter("username", username);
            query.setParameter("password", password);
            return Optional.ofNullable(query.uniqueResult());
        }
        catch (Exception e){
            return Optional.empty();
        }
    }
}
