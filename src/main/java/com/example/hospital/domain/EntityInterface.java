package com.example.hospital.domain;

import javax.persistence.*;
import javax.persistence.GenerationType;
import java.io.Serializable;
import java.util.Objects;

@MappedSuperclass
public class EntityInterface implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EntityInterface)) return false;
        EntityInterface entity = (EntityInterface) o;
        return getId().equals(entity.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "Entity{" +
                "id=" + id +
                '}';
    }
}
