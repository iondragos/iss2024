package com.example.hospital.controller;

import com.example.hospital.domain.Admin;
import com.example.hospital.domain.Medicine;
import com.example.hospital.domain.Pharmacist;
import com.example.hospital.service.Service;
import com.example.hospital.utils.Observer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

public class AdminController implements Observer {
    private Admin admin;
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
    public TextField nameTF;
    @FXML
    public TextField priceTF;
    @FXML
    public TextField descriptionTF;
    @FXML
    public TextField quantityTF;

    @FXML
    public void initialize() {
        columnName.setCellValueFactory(new PropertyValueFactory<Medicine, String>("name"));
        columnPrice.setCellValueFactory(new PropertyValueFactory<Medicine, Float>("price"));
        columnDescription.setCellValueFactory(new PropertyValueFactory<Medicine, String>("description"));
        columnLeftQuantity.setCellValueFactory(new PropertyValueFactory<Medicine, Integer>("quantity"));
        tableMedicine.setItems(medicineObservableList);
    }

    public void setUp(Admin admin, Service service) {
        this.admin = admin;
        this.service = service;

        service.addObserver(this);
        initModel();
    }

    private void initModel() {
        List<Medicine> medicineTableList = (List<Medicine>) service.getAllMedicine();
        medicineObservableList.setAll(medicineTableList);
    }

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public void addMedicine(){
        String name = nameTF.getText();
        String price = priceTF.getText();
        String description = descriptionTF.getText();
        String quantity = quantityTF.getText();
        if(name.equals("") || price.equals("") || description.equals("") || quantity.equals("")){
            Stage stage = (Stage) nameTF.getScene().getWindow();
            MessageAlert.showErrorMessage(stage,"Please complete all fields!");
        }else if(!isNumeric(price)){
            Stage stage = (Stage) nameTF.getScene().getWindow();
            MessageAlert.showErrorMessage(stage,"Price field is not a number");
        }else if(!isNumeric(quantity)){
            Stage stage = (Stage) nameTF.getScene().getWindow();
            MessageAlert.showErrorMessage(stage,"Quantity field is not a number");
        } else{
            service.addMedicine(name, Float.valueOf(price), description, Integer.valueOf(quantity));
        }
    }

    public void deleteMedicine(ActionEvent actionEvent) {
        Medicine medicine = (Medicine) tableMedicine.getSelectionModel().getSelectedItem();
        if(medicine == null) {
            Stage stage = (Stage) nameTF.getScene().getWindow();
            MessageAlert.showErrorMessage(stage,"Select a medicine");
        }
        else{
            service.deleteMedicine(medicine.getId());
        }
    }

    public void updateMedicine(){
        Medicine medicine = (Medicine) tableMedicine.getSelectionModel().getSelectedItem();
        String name = nameTF.getText();
        String price = priceTF.getText();
        String description = descriptionTF.getText();
        String quantity = quantityTF.getText();
        if(medicine == null) {
            Stage stage = (Stage) nameTF.getScene().getWindow();
            MessageAlert.showErrorMessage(stage,"Select a medicine");
        }else if(name.equals("") || price.equals("") || description.equals("") || quantity.equals("")){
            Stage stage = (Stage) nameTF.getScene().getWindow();
            MessageAlert.showErrorMessage(stage,"Please complete all fields!");
        }else if(!isNumeric(price)){
            Stage stage = (Stage) nameTF.getScene().getWindow();
            MessageAlert.showErrorMessage(stage,"Price field is not a number");
        }else if(!isNumeric(quantity)){
            Stage stage = (Stage) nameTF.getScene().getWindow();
            MessageAlert.showErrorMessage(stage,"Quantity field is not a number");
        } else{
            service.updateMedicine(medicine.getId(), name, Float.valueOf(price), description, Integer.valueOf(quantity));
        }
    }

    @Override
    public void update() {
        initModel();
    }
}
