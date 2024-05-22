package com.example.hospital.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name="Sections")
@AttributeOverrides({
        @AttributeOverride(name="id", column = @Column(name="id"))
})
public class Section extends EntityInterface implements Serializable {
    @Column(name="name")
    private String name;

    public Section(){}

    public Section(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Section section)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(getName(), section.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getName());
    }

    @Override
    public String toString() {
        return "Section{" +
                "name='" + name + '\'' +
                '}';
    }
}
