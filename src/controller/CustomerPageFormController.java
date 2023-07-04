package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import controller.crudController.CustomerCrudController;
import controller.crudController.ItemCrudController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Customer;
import model.Items;
import model.TM.CustomerTM;
import model.TM.StocksTM;
import utill.CrudUtil;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CustomerPageFormController implements Initializable {
    public JFXButton btnBack;
    public TableView tblCustomer;
    public TableColumn colCustomerID;
    public TableColumn colCustomerName;
    public TableColumn colCustomerAddress;
    public TableColumn colCustomerContact;
    public TableColumn colCustomerDelete;
    public JFXTextField txtCustomerId;
    public JFXTextField txtCustomerName;
    public JFXTextField txtCustomerAddress;
    public JFXTextField txtCustomerContact;
    public JFXTextField txtUNAme;
    public JFXTextField txtUContact;
    public JFXTextField txtUAddress;
    public JFXComboBox cmbUId;
    public JFXComboBox cmbDeleteId1;
    public JFXTextField txtDeleteNAme1;
    public JFXTextField txtDeleteAddress1;
    public JFXTextField txtDeleteContact1;

    public void backBtnOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) btnBack.getScene().getWindow();
        stage.close();

        Parent parent = FXMLLoader.load(getClass().getResource("/view/DashBoardPageForm.fxml"));
        Stage stage2 = new Stage();
        stage2.setScene(new Scene(parent));
        stage2.setResizable(false);
        stage2.show();
    }

    public void btnAddCustomerOnAction(ActionEvent actionEvent) {
        Customer cust = new Customer(txtCustomerId.getText(),txtCustomerName.getText(),txtCustomerContact.getText(),txtCustomerAddress.getText());

        try{
            if (CrudUtil.execute("INSERT INTO customer VALUES (?,?,?,?)",cust.getCustomerId(),cust.getCustomerName(),cust.getCustomerContact(),cust.getAddress())){
                new Alert(Alert.AlertType.CONFIRMATION, "Saved!..").show();
            }
        }catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void btnUpdateCustomerOnAction(ActionEvent actionEvent) {
        Customer c=new Customer((String) cmbUId.getValue(), txtUNAme.getText(),txtUContact.getText(),txtUAddress.getText());

        try{
            if(CrudUtil.execute("UPDATE customer SET customerName=?,customerContact=?,customerAddress=?  WHERE customerId=?",c.getCustomerName(),c.getCustomerContact(),c.getAddress(),c.getCustomerId())){
                new Alert(Alert.AlertType.CONFIRMATION, "Saved!..").show();
            }
        }catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setCustomerId();
        deleteCustomerId();
        try {
            setCustomerDetals();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        colCustomerID.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        colCustomerName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        colCustomerAddress.setCellValueFactory(new PropertyValueFactory<>("customerAddress"));
        colCustomerContact.setCellValueFactory(new PropertyValueFactory<>("customerContact"));

    }

    private void setCustomerDetals() throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT * FROM customer");
        ObservableList<CustomerTM> obList = FXCollections.observableArrayList();
        while (result.next()){
            obList.add(
                    new CustomerTM(
                            result.getString("customerId"),
                            result.getString("customerName"),
                            result.getString("customerContact"),
                            result.getString("customerAddress")
                    )
            );
        }
        tblCustomer.setItems(obList);
    }

    private void setCustomerId() {
        try {

            ObservableList<String> ObList = FXCollections.observableArrayList(
                    CustomerCrudController.getcustomerId()
            );
            cmbUId.setItems(ObList);


            cmbUId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                String itemCode= (String) newValue;

                ResultSet result = null;
                try {
                    result = CrudUtil.execute("SELECT * FROM customer WHERE customerId=?", String.valueOf(cmbUId.getValue()));
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                try {
                    if (result.next()) {
                        txtUNAme.setText(result.getString(2));
                        txtUAddress.setText(result.getString(4));
                        txtUContact.setText(result.getString(3));
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

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        try{
            if (CrudUtil.execute("DELETE FROM customer WHERE customerId=?",String.valueOf(cmbDeleteId1.getValue()))){
                new Alert(Alert.AlertType.CONFIRMATION, "Deleted!..").show();
            }else{
                new Alert(Alert.AlertType.WARNING, "Can't Delete!..").show();
            }
        }catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void deleteCustomerId() {
        try {

            ObservableList<String> ObList = FXCollections.observableArrayList(
                    CustomerCrudController.getcustomerId()
            );
            cmbDeleteId1.setItems(ObList);


            cmbDeleteId1.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                String itemCode= (String) newValue;

                ResultSet result = null;
                try {
                    result = CrudUtil.execute("SELECT * FROM customer WHERE customerId=?", String.valueOf(cmbDeleteId1.getValue()));
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                try {
                    if (result.next()) {
                        txtDeleteNAme1.setText(result.getString(2));
                        txtDeleteAddress1.setText(result.getString(4));
                        txtDeleteContact1.setText(result.getString(3));
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

}
