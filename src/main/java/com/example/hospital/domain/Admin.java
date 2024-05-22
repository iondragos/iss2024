package com.example.hospital.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name="Admins")
@AttributeOverrides({
        @AttributeOverride(name="id", column = @Column(name="id"))
})
public class Admin extends EntityInterface implements Serializable {
    @Column(name="username")
    private String username;
    @Column(name="password")
    private String password;

    public Admin(){}
    public Admin(String username, String password) {
        this.username = username;
        this.password = password;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Admin admin)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(getUsername(), admin.getUsername()) && Objects.equals(getPassword(), admin.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getUsername(), getPassword());
    }

    @Override
    public String toString() {
        return "Admin{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
