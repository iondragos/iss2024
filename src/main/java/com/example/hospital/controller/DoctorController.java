package com.example.hospital.controller;

import com.example.hospital.domain.Doctor;
import com.example.hospital.domain.Medicine;
import com.example.hospital.domain.Pharmacist;
import com.example.hospital.service.Service;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class DoctorController {
    private Doctor doctor;
    private Service service;

    @FXML
    public TableView<Medicine> tableMedicine;
    @FXML
    public TableColumn<Medicine, String> columnName;
    @FXML
    public TableColumn<Medicine, Float> columnPrice;
    @FXML
    public TableColumn<Medicine, String> columnDescription;
    @FXML
    public TableColumn<Medicine, Integer> columnLeftQuantity;
    private ObservableList<Medicine> medicineObservableList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        columnName.setCellValueFactory(new PropertyValueFactory<Medicine, String>("name"));
        columnPrice.setCellValueFactory(new PropertyValueFactory<Medicine, Float>("price"));
        columnDescription.setCellValueFactory(new PropertyValueFactory<Medicine, String>("description"));
        columnLeftQuantity.setCellValueFactory(new PropertyValueFactory<Medicine, Integer>("quantity"));
        tableMedicine.setItems(medicineObservableList);
    }

    public void setUp(Doctor doctor, Service service) {
        this.doctor = doctor;
        this.service = service;
        initModel();
    }

    private void initModel() {
        List<Medicine> medicineTableList = (List<Medicine>) service.getAllMedicine();
        medicineObservableList.setAll(medicineTableList);
    }
}
