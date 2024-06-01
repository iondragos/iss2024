package com.example.hospital.controller;

import com.example.hospital.domain.*;
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

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.List;

public class DoctorController implements Observer {
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
    public TableView<OrderItem> tableOrderItem;
    @FXML
    public TableColumn<OrderItem, String> columnMedicineName;
    @FXML
    public TableColumn<OrderItem, Integer> columnQuantity;
    private List<OrderItem> orderItemList = new ArrayList<>();
    private ObservableList<OrderItem> orderItemObservableList = FXCollections.observableArrayList();
    @FXML
    public TextField quantityTF;

    @FXML
    public TableView<Order> tableOrder;
    @FXML
    public TableColumn<Order, Long> columnOrderId;
    @FXML
    public TableColumn<Order, String> columnOrderStatus;
    private ObservableList<Order> orderObservableList = FXCollections.observableArrayList();



    @FXML
    public void initialize() {
        columnName.setCellValueFactory(new PropertyValueFactory<Medicine, String>("name"));
        columnPrice.setCellValueFactory(new PropertyValueFactory<Medicine, Float>("price"));
        columnDescription.setCellValueFactory(new PropertyValueFactory<Medicine, String>("description"));
        columnLeftQuantity.setCellValueFactory(new PropertyValueFactory<Medicine, Integer>("quantity"));
        tableMedicine.setItems(medicineObservableList);

        columnMedicineName.setCellValueFactory(new PropertyValueFactory<OrderItem, String>("medicineName"));
        columnQuantity.setCellValueFactory(new PropertyValueFactory<OrderItem, Integer>("quantity"));
        tableOrderItem.setItems(orderItemObservableList);

        columnOrderId.setCellValueFactory(new PropertyValueFactory<Order, Long>("id"));
        columnOrderStatus.setCellValueFactory(new PropertyValueFactory<Order, String>("orderStatus"));
        tableOrder.setItems(orderObservableList);
    }

    public void setUp(Doctor doctor, Service service) {
        this.doctor = doctor;
        this.service = service;

        service.addObserver(this);
        initModel();
    }

    private void initModel() {
        List<Medicine> medicineTableList = (List<Medicine>) service.getAllMedicine();
        medicineObservableList.clear();
        medicineObservableList.setAll(medicineTableList);

        List<Order> orderTableList = (List<Order>) service.getAllOrders();
        orderObservableList.clear();
        orderObservableList.setAll(orderTableList);
    }

    @Override
    public void update() {
        initModel();
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

    private boolean itemAlreadySelected(String name){
        for(OrderItem item : orderItemList){
            if(name.equals(item.getMedicineName())){
                return true;
            }
        }
        return false;
    }

    public void addMedicineItem(){
        Medicine medicine = (Medicine) tableMedicine.getSelectionModel().getSelectedItem();
        String quantity = quantityTF.getText();
        if(medicine == null) {
            Stage stage = (Stage) quantityTF.getScene().getWindow();
            MessageAlert.showErrorMessage(stage,"Select a medicine");
        }else if(quantity.equals("")){
            Stage stage = (Stage) quantityTF.getScene().getWindow();
            MessageAlert.showErrorMessage(stage,"Please select quantity!");
        }else if(!isNumeric(quantity)){
            Stage stage = (Stage) quantityTF.getScene().getWindow();
            MessageAlert.showErrorMessage(stage,"Please enter a numeric value!");
        }else if(Integer.valueOf(quantity) > medicine.getQuantity()){
            Stage stage = (Stage) quantityTF.getScene().getWindow();
            MessageAlert.showErrorMessage(stage,"There are not as many products left!");
        }else if(itemAlreadySelected(medicine.getName())){
            Stage stage = (Stage) quantityTF.getScene().getWindow();
            MessageAlert.showErrorMessage(stage,"Medicine already selected!");
        }
        else{
            orderItemList.add(new OrderItem(medicine.getId(), medicine.getName(), Integer.valueOf(quantity)));
            orderItemObservableList.setAll(orderItemList);
        }
    }

    public void deleteMedicineItem(ActionEvent actionEvent) {
        OrderItem orderItem = (OrderItem) tableOrderItem.getSelectionModel().getSelectedItem();
        if(orderItem == null) {
            Stage stage = (Stage) quantityTF.getScene().getWindow();
            MessageAlert.showErrorMessage(stage,"Select an item");
        }
        else{
            orderItemObservableList.remove(orderItem);
        }
    }

    public void sendOrder(){
        if(orderItemList.isEmpty()){
            Stage stage = (Stage) quantityTF.getScene().getWindow();
            MessageAlert.showErrorMessage(stage,"Order list is empty.");
        }else{
            Order order = service.addOrder(doctor.getSection(), "pending");

            for (OrderItem item : orderItemList){
                Medicine medicine = service.getMedicine(item.getIdMedicine());
                service.addMedicineOrder(medicine, order, item.getQuantity());
                service.updateMedicine(medicine.getId(), medicine.getName(), medicine.getPrice(),
                        medicine.getDescription(), medicine.getQuantity() - item.getQuantity());
            }

            orderItemList.clear();
            orderItemObservableList.setAll(orderItemList);
        }
    }
}
