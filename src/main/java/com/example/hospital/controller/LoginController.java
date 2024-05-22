package com.example.hospital.controller;

import com.example.hospital.HelloApplication;
import com.example.hospital.domain.Admin;
import com.example.hospital.domain.Doctor;
import com.example.hospital.domain.Pharmacist;
import com.example.hospital.service.Service;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;
import java.util.Properties;

public class LoginController {
    private Service service;
    @FXML
    public TextField nameField;
    @FXML
    public PasswordField passwordField;

    public void setUp(Service service) {
        this.service = service;
    }

    public void handleUserPage() throws IOException{
        try {
            FXMLLoader fxmlLoaderAdmin = new FXMLLoader(HelloApplication.class.getResource("admin_page.fxml"));
            Parent viewParentAdmin = fxmlLoaderAdmin.load();
            AdminController adminController = fxmlLoaderAdmin.getController();

            FXMLLoader fxmlLoaderDoctor = new FXMLLoader(HelloApplication.class.getResource("doctor_page.fxml"));
            Parent viewParentDoctor = fxmlLoaderDoctor.load();
            DoctorController doctorController = fxmlLoaderDoctor.getController();

            FXMLLoader fxmlLoaderPharmacist = new FXMLLoader(HelloApplication.class.getResource("pharmacist_page.fxml"));
            Parent viewParentPharmacist = fxmlLoaderPharmacist.load();
            PharmacistController pharmacistController = fxmlLoaderPharmacist.getController();

            Stage stage = (Stage) nameField.getScene().getWindow(); // Get the current stage

            String username = nameField.getText();
            String password = passwordField.getText();
            Optional<Admin> admin = service.findAdmin(username, password);
            Optional<Doctor> doctor = service.findDoctor(username, password);
            Optional<Pharmacist> pharmacist = service.findPharmacist(username, password);
            if(username.equals("") || password.equals("")) {
                MessageAlert.showErrorMessage(stage, "Please complete all the fields.");
            }else if (!admin.isEmpty()) {
                Stage newStage = new Stage();
                newStage.initModality(Modality.WINDOW_MODAL);

                adminController.setUp(admin.get(), service);

                Scene adminViewScene = new Scene(viewParentAdmin);
                newStage.setScene(adminViewScene);
                newStage.setTitle("Admin: " + admin.get().getUsername());
                newStage.show();
            } else if (!doctor.isEmpty()) {
                Stage newStage = new Stage();
                newStage.initModality(Modality.WINDOW_MODAL);

                doctorController.setUp(doctor.get(), service);

                Scene doctorViewScene = new Scene(viewParentDoctor);
                newStage.setScene(doctorViewScene);
                newStage.setTitle("Doctor: " + doctor.get().getUsername());
                newStage.show();
            } else if (!pharmacist.isEmpty()) {
                Stage newStage = new Stage();
                newStage.initModality(Modality.WINDOW_MODAL);

                pharmacistController.setUp(pharmacist.get(), service);

                Scene pharmacistViewScene = new Scene(viewParentPharmacist);
                newStage.setScene(pharmacistViewScene);
                newStage.setTitle("Pharmacist: " + pharmacist.get().getUsername());
                newStage.show();
            } else {
                MessageAlert.showErrorMessage(stage, "Invalid user.");
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
