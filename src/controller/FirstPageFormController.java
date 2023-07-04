package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import controller.crudController.ItemCrudController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Items;
import model.TM.ItemTM;
import utill.CrudUtil;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class FirstPageFormController implements Initializable {
    public JFXTextField txtItemId;
    public JFXTextField txtAddItemName;
    public JFXTextField txtAddItemPrice;
    public JFXTextField txtAddQty;
    public JFXTextField txtUpdateItemName;
    public JFXTextField txtUpdateItemPrice;
    public JFXTextField txtUpdateQty;
    public TableView<ItemTM> tblItem;
    public TableColumn colItemId;
    public TableColumn colItemName;
    public TableColumn colQty;
    public TableColumn colUnitPrice;
    public TableColumn colDelete;
    public JFXButton btnBack;
    public JFXComboBox cmbItemId;
    public JFXComboBox cmbItemType;
    public JFXTextField txtItemType;
    public JFXComboBox cmbItemId1;
    public JFXTextField txtDeleteItemName1;
    public JFXTextField txtDeleteItemPrice1;
    public JFXTextField txtDeleteQty1;
    public JFXTextField txtItemType1;

    public void BtnAddOnAction(ActionEvent actionEvent) {
        Items items = new Items(txtItemId.getText(), (String) cmbItemType.getValue(),txtAddItemName.getText(),Integer.parseInt(txtAddQty.getText()),Double.parseDouble(txtAddItemPrice.getText()));

        try{
            if (CrudUtil.execute("INSERT INTO item VALUES (?,?,?,?,?)",items.getItemId(),items.getItemName(),items.getItemType(),items.getQty(),items.getPrice())){
                new Alert(Alert.AlertType.CONFIRMATION, "Saved!..").show();
            }
        }catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void BtnUpdateOnAction(ActionEvent actionEvent) {
        Items i=new Items((String) cmbItemId.getValue(), txtItemType.getText(),txtUpdateItemName.getText(),Integer.parseInt(txtUpdateQty.getText()),Double.parseDouble(txtUpdateItemPrice.getText()));

        try{
            if(CrudUtil.execute("UPDATE item SET itemName=?,itemType=?,qtyOnHand=?,unitprice=?  WHERE itemCode=?",i.getItemName(),i.getItemType(),i.getQty(),i.getPrice(),cmbItemId.getValue())){
                new Alert(Alert.AlertType.CONFIRMATION, "Saved!..").show();
            }
        }catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void backBtnOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) btnBack.getScene().getWindow();
        stage.close();

        Parent parent = FXMLLoader.load(getClass().getResource("/view/DashBoardPageForm.fxml"));
        Stage stage2 = new Stage();
        stage2.setScene(new Scene(parent));
        stage2.setResizable(false);
        stage2.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setItemCodes();

        String[] itemType = {"Construction", "PVC", "Electric", "Paints" };

        cmbItemType.getItems().addAll(itemType);

        cmbItemType.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            String type = (String) newValue;
        });

    }

    private void setItemCodes() {
        try {

            ObservableList<String> ObList = FXCollections.observableArrayList(
                    ItemCrudController.getItemId()
            );
            cmbItemId.setItems(ObList);


            cmbItemId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                String itemCode= (String) newValue;

                ResultSet result = null;
                try {
                    result = CrudUtil.execute("SELECT * FROM item WHERE ItemCode=?",itemCode);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                try {
                    if (result.next()) {
                        txtItemType.setText(result.getString(3));
                        txtUpdateItemName.setText(result.getString(2));
                        txtUpdateQty.setText(result.getString(4));
                        txtUpdateItemPrice.setText(String.valueOf(result.getDouble(5)));
                    } else {
                        new Alert(Alert.AlertType.WARNING, "Empty Result").show();
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            });
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {

            ObservableList<String> ObList2 = FXCollections.observableArrayList(
                    ItemCrudController.getItemId()
            );
            cmbItemId1.setItems(ObList2);


            cmbItemId1.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                String itemCode= (String) newValue;

                ResultSet result = null;
                try {
                    result = CrudUtil.execute("SELECT * FROM item WHERE ItemCode=?",itemCode);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                try {
                    if (result.next()) {
                        txtItemType1.setText(result.getString(3));
                        txtDeleteItemName1.setText(result.getString(2));
                        txtDeleteQty1.setText(result.getString(4));
                        txtDeleteItemPrice1.setText(String.valueOf(result.getDouble(5)));
                    } else {
                        new Alert(Alert.AlertType.WARNING, "Empty Result").show();
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            });
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public void BtnDeleteOnAction(ActionEvent actionEvent) {
        try{
            if (CrudUtil.execute("DELETE FROM item WHERE itemCode=?",String.valueOf(cmbItemId1.getValue()))){
                new Alert(Alert.AlertType.CONFIRMATION, "Deleted!..").show();
            }else{
                new Alert(Alert.AlertType.WARNING, "Can't Delete!..").show();
            }
        }catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }
}
