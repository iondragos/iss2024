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

import java.util.ArrayList;
import java.util.List;

public class PharmacistController implements Observer {
    private Pharmacist pharmacist;
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
    public TableView<Order> tableOrder;
    @FXML
    public TableColumn<Order, Long> columnOrderId;
    @FXML
    public TableColumn<Order, String> columnOrderStatus;
    private ObservableList<Order> orderObservableList = FXCollections.observableArrayList();


    @FXML
    public TableView<OrderItem> tableOrderItem;
    @FXML
    public TableColumn<OrderItem, String> columnMedicineName;
    @FXML
    public TableColumn<OrderItem, Integer> columnQuantity;
    private List<OrderItem> orderItemList = new ArrayList<>();
    private ObservableList<OrderItem> orderItemObservableList = FXCollections.observableArrayList();



    @FXML
    public void initialize() {
        columnName.setCellValueFactory(new PropertyValueFactory<Medicine, String>("name"));
        columnPrice.setCellValueFactory(new PropertyValueFactory<Medicine, Float>("price"));
        columnDescription.setCellValueFactory(new PropertyValueFactory<Medicine, String>("description"));
        columnLeftQuantity.setCellValueFactory(new PropertyValueFactory<Medicine, Integer>("quantity"));
        tableMedicine.setItems(medicineObservableList);

        columnOrderId.setCellValueFactory(new PropertyValueFactory<Order, Long>("id"));
        columnOrderStatus.setCellValueFactory(new PropertyValueFactory<Order, String>("orderStatus"));
        tableOrder.setItems(orderObservableList);

        columnMedicineName.setCellValueFactory(new PropertyValueFactory<OrderItem, String>("medicineName"));
        columnQuantity.setCellValueFactory(new PropertyValueFactory<OrderItem, Integer>("quantity"));
        tableOrderItem.setItems(orderItemObservableList);
    }

    public void setUp(Pharmacist pharmacist, Service service) {
        this.pharmacist = pharmacist;
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

    public void loadOrder(){
        Order order = (Order) tableOrder.getSelectionModel().getSelectedItem();
        if(order == null) {
            Stage stage = (Stage) quantityTF.getScene().getWindow();
            MessageAlert.showErrorMessage(stage,"Select an item");
        }else {
            Iterable<MedicineOrder> medicineOrderList = service.getAllMedicineOrders();
            orderItemList.clear();
            for(MedicineOrder medicineOrder : medicineOrderList){
                if(medicineOrder.getOrder().getId() == order.getId()){
                    orderItemList.add(new OrderItem(medicineOrder.getMedicine().getId(), medicineOrder.getMedicine().getName(), medicineOrder.getQuantity()));
                }
            }
            orderItemObservableList.clear();
            orderItemObservableList.setAll(orderItemList);
        }
    }

    public void honourOrder(){
        Order order = (Order) tableOrder.getSelectionModel().getSelectedItem();
        if(order == null) {
            Stage stage = (Stage) quantityTF.getScene().getWindow();
            MessageAlert.showErrorMessage(stage,"Select an item");
        }else if(order.getOrderStatus().equals("honored")) {
            Stage stage = (Stage) quantityTF.getScene().getWindow();
            MessageAlert.showErrorMessage(stage,"Order already honored!");
        } else if(order.getOrderStatus().equals("refused")) {
            Stage stage = (Stage) quantityTF.getScene().getWindow();
            MessageAlert.showErrorMessage(stage,"Order already refused!");
        } else{
            service.updateOrder(order.getId(), order.getSection(), "honored");
        }
    }

    public void refuseOrder(){
        Order order = (Order) tableOrder.getSelectionModel().getSelectedItem();
        if(order == null) {
            Stage stage = (Stage) quantityTF.getScene().getWindow();
            MessageAlert.showErrorMessage(stage,"Select an item");
        }else if(order.getOrderStatus().equals("honored")) {
            Stage stage = (Stage) quantityTF.getScene().getWindow();
            MessageAlert.showErrorMessage(stage,"Order already honored!");
        } else if(order.getOrderStatus().equals("refused")) {
            Stage stage = (Stage) quantityTF.getScene().getWindow();
            MessageAlert.showErrorMessage(stage,"Order already refused!");
        } else{
            Iterable<OrderItem> orderItemListCopy = orderItemList;

            service.updateOrder(order.getId(), order.getSection(), "refused");

            for (OrderItem item : orderItemListCopy){
                Medicine medicine = service.getMedicine(item.getIdMedicine());
                service.updateMedicine(medicine.getId(), medicine.getName(), medicine.getPrice(),
                        medicine.getDescription(), medicine.getQuantity() + item.getQuantity());
            }
        }
    }

}
