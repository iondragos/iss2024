package com.example.hospital.repository.hibernate;

import com.example.hospital.domain.Medicine;
import com.example.hospital.domain.Section;
import com.example.hospital.repository.interfaces.SectionRepository;
import org.hibernate.SessionFactory;

import java.util.Optional;

public class SectionHBRepository implements SectionRepository {
    private SessionFactory sessionFactory;

    public SectionHBRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    @Override
    public Optional<Section> findOne(Long aLong) {
        try(var session = sessionFactory.openSession()){
            var section = session.get(Section.class, aLong);
            return Optional.ofNullable(section);
        }
        catch (Exception e){
            return Optional.empty();
        }
    }

    @Override
    public Iterable<Section> findAll() {
        return null;
    }

    @Override
    public Optional<Section> save(Section entity) {
        return Optional.empty();
    }

    @Override
    public Optional<Section> delete(Long aLong) {
        return Optional.empty();
    }

    @Override
    public Optional<Section> update(Section entity) {
        return Optional.empty();
    }

    @Override
    public Integer size() {
        return null;
    }
}
