package com.example.hospital.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name="Doctors")
@AttributeOverrides({
        @AttributeOverride(name="id", column = @Column(name="id"))
})
public class Doctor extends EntityInterface implements Serializable {
    @Column(name="username")
    private String username;
    @Column(name="password")
    private String password;
    @Id
    @ManyToOne
    @JoinColumn(name = "id_section")
    private Section section;
    public Doctor(){}

    public Doctor(String username, String password, Section section) {
        this.username = username;
        this.password = password;
        this.section = section;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Doctor doctor)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(getUsername(), doctor.getUsername()) && Objects.equals(getPassword(), doctor.getPassword()) && Objects.equals(getSection(), doctor.getSection());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getUsername(), getPassword(), getSection());
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", section=" + section +
                '}';
    }
}
