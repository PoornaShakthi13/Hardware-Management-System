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
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.Duration;
import uiloader.loadUi;
import utill.CrudUtil;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;

public class CashierDashboardFormController implements Initializable {
    public JFXButton btnLogOut;
    public Label lblTime;
    public Label lblDate;

    public void BtnCustomerOnAction(ActionEvent actionEvent) throws IOException {
        loader("CashierCustomerPageForm");
    }

    public void BtnItemOnAction(ActionEvent actionEvent) throws IOException {
        loader("CashierItemPageForm");
    }

    public void BtnStocksOnAction(ActionEvent actionEvent) throws IOException {
        loader("CashierStockPageForm");
    }

    public void BtnOrderOnAction(ActionEvent actionEvent) throws IOException {
        loader("CashierOrderPageForm");
    }

    public void btnLogOutOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) btnLogOut.getScene().getWindow();
        stage.close();

        Parent parent = FXMLLoader.load(getClass().getResource("/view/HomePageForm.fxml"));
        Stage stage2 = new Stage();
        stage2.setScene(new Scene(parent));
        stage2.setResizable(false);
        stage2.show();
    }

    public void loader(String location) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/view/"+location+".fxml"));
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

}
