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
        try(var session = sessionFactory.openSession()){
            var medicine = session.get(Medicine.class, aLong);
            return Optional.ofNullable(medicine);
        }
        catch (Exception e){
            return Optional.empty();
        }
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
    public Optional<Medicine> delete(Long aLong) {
        var medicineOpt = findOne(aLong);
        if(medicineOpt.isEmpty())
            return Optional.empty();
        try(var session = sessionFactory.openSession()){
            var transaction = session.beginTransaction();
            session.delete(medicineOpt.get());
            transaction.commit();
            return medicineOpt;
        }
        catch (Exception e){
            return Optional.empty();
        }
    }

    @Override
    public Optional<Medicine> update(Medicine entity) {
        var medicineOpt = findOne(entity.getId());
        if(medicineOpt.isEmpty())
            return Optional.empty();
        try(var session = sessionFactory.openSession()){
            var transaction = session.beginTransaction();
            session.update(entity);
            transaction.commit();
            return medicineOpt;
        }
        catch (Exception e){
            return Optional.empty();
        }
    }

    @Override
    public Integer size() {
        try(var session = sessionFactory.openSession()){
            return session.createQuery("SELECT M FROM Medicine M", Medicine.class).list().size();
        }
        catch (Exception e){
            return 0;
        }
    }
}
