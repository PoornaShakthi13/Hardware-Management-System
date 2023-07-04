package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import uiloader.loadUi;

import java.io.IOException;

public class HomePageFormController {
    public JFXButton btnSignIn;
    public JFXTextField txtUserName;
    public JFXPasswordField txtPassword;

    public void signinBtnOnAction(ActionEvent actionEvent) throws IOException {
        if (txtUserName.getText().equals("SHAKTHI") && txtPassword.getText().equals("SHAKTHI")){
            loader("DashboardPageForm.fxml");
            new Alert(Alert.AlertType.CONFIRMATION, "Welcome to SHAKTHI HARDWARE ").show();
        }else if (txtUserName.getText().equals("CASHIER") && txtPassword.getText().equals("CASHIER")){
            loader("CashierDashBoardForm.fxml");
            new Alert(Alert.AlertType.CONFIRMATION, "Welcome to SHAKTHI HARDWARE ").show();
        }else{
            new Alert(Alert.AlertType.ERROR, "Invalid Username or Password").show();
        }

      //  loader("DashboardPageForm.fxml");
    }

    public void canselBtnOnAction(ActionEvent actionEvent) {
    }

    public void loader(String location) throws IOException {
        Stage stage = (Stage) btnSignIn.getScene().getWindow();
        stage.close();

        Parent parent = FXMLLoader.load(getClass().getResource("../view/"+location));
        Stage stage2 = new Stage();
        stage2.setScene(new Scene(parent));
        stage2.setResizable(false);
        stage2.show();
    }


}
