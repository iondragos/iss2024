package com.example.hospital.repository.hibernate;

import com.example.hospital.domain.Pharmacist;
import com.example.hospital.repository.interfaces.PharmacistRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Optional;

public class PharmacistHBRepository implements PharmacistRepository {
    private SessionFactory sessionFactory;

    public PharmacistHBRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    @Override
    public Optional<Pharmacist> findOne(Long aLong) {
        return Optional.empty();
    }

    @Override
    public Iterable<Pharmacist> findAll() {
        return null;
    }

    @Override
    public Optional<Pharmacist> save(Pharmacist entity) {
        return Optional.empty();
    }

    @Override
    public Optional<Pharmacist> delete(Long aLong) {
        return Optional.empty();
    }

    @Override
    public Optional<Pharmacist> update(Pharmacist entity) {
        return Optional.empty();
    }

    @Override
    public Integer size() {
        return null;
    }

    @Override
    public Optional<Pharmacist> findByUsernamePassword(String username, String password) {
        try(var session = sessionFactory.openSession()){
            var query = session.createQuery("SELECT P FROM Pharmacist P " +
                    "WHERE P.username like :username AND P.password like :password", Pharmacist.class);
            query.setParameter("username", username);
            query.setParameter("password", password);
            return Optional.ofNullable(query.uniqueResult());
        }
        catch (Exception e){
            return Optional.empty();
        }
    }
}
