package com.example.hospital.domain;

import java.util.Objects;

public class OrderItem {
    private Long idMedicine;
    private String medicineName;
    private int quantity;

    public OrderItem(Long idMedicine, String medicineName, int quantity) {
        this.idMedicine = idMedicine;
        this.medicineName = medicineName;
        this.quantity = quantity;
    }

    public Long getIdMedicine() {
        return idMedicine;
    }

    public void setIdMedicine(Long idMedicine) {
        this.idMedicine = idMedicine;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
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
        if (!(o instanceof OrderItem item)) return false;
        return getQuantity() == item.getQuantity() && Objects.equals(getIdMedicine(), item.getIdMedicine()) && Objects.equals(getMedicineName(), item.getMedicineName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdMedicine(), getMedicineName(), getQuantity());
    }
}
