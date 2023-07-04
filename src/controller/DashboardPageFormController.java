package controller;

import com.jfoenix.controls.JFXButton;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;

public class DashboardPageFormController implements Initializable {
    public JFXButton btnItem;
    public JFXButton btnOrder;
    public JFXButton BtnEmployee;
    public JFXButton btnCustomer;
    public JFXButton btnLogOut;
    public Label lblDate;
    public Label lblTime;
    public JFXButton btnSetting;

    public void itemBtnOnAction(ActionEvent actionEvent) throws IOException {
        loader("FirstPageForm.fxml");

    }

    public void loader(String location) throws IOException {
        Stage stage = (Stage) btnItem.getScene().getWindow();
        stage.close();

        Parent parent = FXMLLoader.load(getClass().getResource("../view/"+location));
        Stage stage2 = new Stage();
        stage2.setScene(new Scene(parent));
        stage2.setResizable(false);
        stage2.show();
    }

    public void btnOrderOnAction(ActionEvent actionEvent) throws IOException {
        loader("OrderFormPage.fxml");
    }

    public void BtnCustomerOnAction(ActionEvent actionEvent) throws IOException {
        loader("CustomerPageForm.fxml");
    }

    public void BtnEmployeeOnAction(ActionEvent actionEvent) throws IOException {
        loader("EmployeePageForm.fxml");
    }

    public void BtnStocksOnAction(ActionEvent actionEvent) throws IOException {
        loader("StockFormPage.fxml");
    }

    public void BtnVehicleOnAction(ActionEvent actionEvent) throws IOException {
        loader("VehicleFormPage.fxml");
    }

    public void BtnReportsOnAction(ActionEvent actionEvent) throws IOException {
        loader("ReportFormPage.fxml");
    }

    public void BtnLogOutOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) btnLogOut.getScene().getWindow();
        stage.close();

        Parent parent = FXMLLoader.load(getClass().getResource("/view/HomePageForm.fxml"));
        Stage stage2 = new Stage();
        stage2.setScene(new Scene(parent));
        stage2.setResizable(false);
        stage2.show();
    }

    private void loadDateAndTime() {
        lblDate.setText(new SimpleDateFormat("dd-MM-yyyy ").format(new Date()));

        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e->{
            LocalTime currentTime = LocalTime.now();
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("hh:mm a");
            String time = currentTime.format(dateTimeFormatter);
            lblTime.setText(time);
        }),
                new KeyFrame(Duration.seconds(1))
        );
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadDateAndTime();
    }

    public void BtnSettingOnAction(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("../view/SettingFormPage.fxml"));
        Stage stage2 = new Stage();
        stage2.setScene(new Scene(parent));
        stage2.setResizable(false);
        stage2.show();
    }
}
