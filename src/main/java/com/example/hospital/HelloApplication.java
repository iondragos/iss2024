package com.example.hospital;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        displayAllWindows();
    }

    public static void main(String[] args) {
        launch();
    }


    public void displayAllWindows() throws IOException {
        Stage stage = new Stage();
        stage.initModality(Modality.WINDOW_MODAL);
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login_page.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Log in page");
        stage.setScene(scene);
        stage.show();

        Stage stage2 = new Stage();
        stage2.initModality(Modality.WINDOW_MODAL);
        FXMLLoader fxmlLoader2 = new FXMLLoader(HelloApplication.class.getResource("signup_page.fxml"));
        Scene scene2 = new Scene(fxmlLoader2.load());
        stage2.setTitle("Sign up page");
        stage2.setScene(scene2);
        stage2.show();

        Stage stage3 = new Stage();
        stage3.initModality(Modality.WINDOW_MODAL);
        FXMLLoader fxmlLoader3 = new FXMLLoader(HelloApplication.class.getResource("doctor_page.fxml"));
        Scene scene3 = new Scene(fxmlLoader3.load());
        stage3.setTitle("Doctor page");
        stage3.setScene(scene3);
        stage3.show();

        Stage stage4 = new Stage();
        stage4.initModality(Modality.WINDOW_MODAL);
        FXMLLoader fxmlLoader4 = new FXMLLoader(HelloApplication.class.getResource("pharmacist_page.fxml"));
        Scene scene4 = new Scene(fxmlLoader4.load());
        stage4.setTitle("Pharmacist page");
        stage4.setScene(scene4);
        stage4.show();

        Stage stage5 = new Stage();
        stage5.initModality(Modality.WINDOW_MODAL);
        FXMLLoader fxmlLoader5 = new FXMLLoader(HelloApplication.class.getResource("admin_page.fxml"));
        Scene scene5 = new Scene(fxmlLoader5.load());
        stage5.setTitle("Admin page");
        stage5.setScene(scene5);
        stage5.show();

        Stage stage6 = new Stage();
        stage6.initModality(Modality.WINDOW_MODAL);
        FXMLLoader fxmlLoader6 = new FXMLLoader(HelloApplication.class.getResource("add_medicine_page.fxml"));
        Scene scene6 = new Scene(fxmlLoader6.load());
        stage6.setTitle("Add medicine page");
        stage6.setScene(scene6);
        stage6.show();

        Stage stage7 = new Stage();
        stage7.initModality(Modality.WINDOW_MODAL);
        FXMLLoader fxmlLoader7 = new FXMLLoader(HelloApplication.class.getResource("update_medicine_page.fxml"));
        Scene scene7 = new Scene(fxmlLoader7.load());
        stage7.setTitle("Update medicine page");
        stage7.setScene(scene7);
        stage7.show();
    }
}