package com.example.hospital.repository.hibernate;

import com.example.hospital.domain.Admin;
import com.example.hospital.domain.Pharmacist;
import com.example.hospital.repository.interfaces.AdminRepository;
import org.hibernate.SessionFactory;

import java.util.Optional;

public class AdminHBRepository implements AdminRepository {
    private SessionFactory sessionFactory;

    public AdminHBRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Optional<Admin> findOne(Long aLong) {
        return Optional.empty();
    }

    @Override
    public Iterable<Admin> findAll() {
        return null;
    }

    @Override
    public Optional<Admin> save(Admin entity) {
        return Optional.empty();
    }

    @Override
    public Optional<Admin> delete(Long aLong) {
        return Optional.empty();
    }

    @Override
    public Optional<Admin> update(Admin entity) {
        return Optional.empty();
    }

    @Override
    public Integer size() {
        return null;
    }

    @Override
    public Optional<Admin> findByUsernamePassword(String username, String password) {
        try(var session = sessionFactory.openSession()){
            var query = session.createQuery("SELECT A FROM Admin A " +
                    "WHERE A.username like :username AND A.password like :password", Admin.class);
            query.setParameter("username", username);
            query.setParameter("password", password);
            return Optional.ofNullable(query.uniqueResult());
        }
        catch (Exception e){
            return Optional.empty();
        }
    }
}
