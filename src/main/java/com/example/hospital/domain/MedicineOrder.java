package com.example.hospital.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="MedicineOrders")
@AttributeOverrides({
        @AttributeOverride(name="id", column = @Column(name="id"))
})
public class MedicineOrder extends EntityInterface implements Serializable {
    @ManyToOne
    @JoinColumn(name = "id_medicine")
    private Medicine medicine;
    @ManyToOne
    @JoinColumn(name = "id_order")
    private Order order;
    @Column(name="quantity")
    private int quantity;

    public MedicineOrder() {
    }

    public MedicineOrder(Medicine medicine, Order order, int quantity) {
        this.medicine = medicine;
        this.order = order;
        this.quantity = quantity;
    }

    public Medicine getMedicine() {
        return medicine;
    }

    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
