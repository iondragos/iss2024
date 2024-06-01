package com.example.hospital.repository.hibernate;

import com.example.hospital.domain.Medicine;
import com.example.hospital.domain.Order;
import com.example.hospital.repository.interfaces.OrderRepository;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Optional;

public class OrderHBRepository implements OrderRepository {
    private SessionFactory sessionFactory;

    public OrderHBRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    @Override
    public Optional<Order> findOne(Long aLong) {
        try(var session = sessionFactory.openSession()){
            var order = session.get(Order.class, aLong);
            return Optional.ofNullable(order);
        }
        catch (Exception e){
            return Optional.empty();
        }
    }

    @Override
    public Iterable<Order> findAll() {
        try(var session = sessionFactory.openSession()){
            return session.createQuery("SELECT O FROM Order O", Order.class).list();
        }
        catch (Exception e){
            return List.of();
        }
    }

    @Override
    public Optional<Order> save(Order entity) {
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
    public Optional<Order> delete(Long aLong) {
        return Optional.empty();
    }

    @Override
    public Optional<Order> update(Order entity) {
        var orderOpt = findOne(entity.getId());
        if(orderOpt.isEmpty())
            return Optional.empty();
        try(var session = sessionFactory.openSession()){
            var transaction = session.beginTransaction();
            session.update(entity);
            transaction.commit();
            return orderOpt;
        }
        catch (Exception e){
            return Optional.empty();
        }
    }

    @Override
    public Integer size() {
        return null;
    }
}
