package com.example.hospital.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name="Medicine")
@AttributeOverrides({
        @AttributeOverride(name="id", column = @Column(name="id"))
})
public class Medicine extends EntityInterface implements Serializable {
    @Column(name="name")
    private String name;
    @Column(name="price")
    private float price;
    @Column(name="description")
    private String description;
    @Column(name="quantity")
    private int quantity;

    public Medicine(){}

    public Medicine(String name, float price, String description, int quantity) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Medicine medicine)) return false;
        if (!super.equals(o)) return false;
        return Float.compare(getPrice(), medicine.getPrice()) == 0 && getQuantity() == medicine.getQuantity() && Objects.equals(getName(), medicine.getName()) && Objects.equals(getDescription(), medicine.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getName(), getPrice(), getDescription(), getQuantity());
    }

    @Override
    public String toString() {
        return "Medicine{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
