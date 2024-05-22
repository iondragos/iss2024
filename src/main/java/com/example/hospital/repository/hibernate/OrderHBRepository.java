package com.example.hospital.repository.hibernate;

import com.example.hospital.domain.Order;
import com.example.hospital.repository.interfaces.OrderRepository;
import org.hibernate.SessionFactory;

import java.util.Optional;

public class OrderHBRepository implements OrderRepository {
    private SessionFactory sessionFactory;

    public OrderHBRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    @Override
    public Optional<Order> findOne(Long aLong) {
        return Optional.empty();
    }

    @Override
    public Iterable<Order> findAll() {
        return null;
    }

    @Override
    public Optional<Order> save(Order entity) {
        return Optional.empty();
    }

    @Override
    public Optional<Order> delete(Long aLong) {
        return Optional.empty();
    }

    @Override
    public Optional<Order> update(Order entity) {
        return Optional.empty();
    }

    @Override
    public Integer size() {
        return null;
    }
}
