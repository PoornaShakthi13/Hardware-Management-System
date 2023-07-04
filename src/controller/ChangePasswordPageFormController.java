package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import uiloader.loadUi;

import java.io.IOException;

public class ChangePasswordPageFormController implements loadUi {
    public AnchorPane context;

    public void btnOwnerOnAction(ActionEvent actionEvent) throws IOException {
        loader("SecurityPageForm");

    }

    public void btnCashierOnAction(ActionEvent actionEvent) throws IOException {
        loader("CashierSecurityPageForm");
    }


    @Override
    public void loader(String location) throws IOException {
        context.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("../view/"+location+".fxml"));
        context.getChildren().add(parent);

    }
}
