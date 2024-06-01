package com.example.hospital.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name="Orders")
@AttributeOverrides({
        @AttributeOverride(name="id", column = @Column(name="id"))
})
public class Order extends EntityInterface implements Serializable {
    @ManyToOne
    @JoinColumn(name = "id_section")
    private Section section;
    @Column(name="order_status")
    private String orderStatus;

    public Order(){}

    public Order(Section section, String orderStatus) {
        this.section = section;
        this.orderStatus = orderStatus;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order order)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(getSection(), order.getSection()) && Objects.equals(getOrderStatus(), order.getOrderStatus());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getSection(), getOrderStatus());
    }

    @Override
    public String toString() {
        return "Order{" +
                "section=" + section +
                ", orderStatus='" + orderStatus + '\'' +
                '}';
    }
}
