package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import controller.crudController.EmployeeCrudController;
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
import model.Employee;
import model.Items;
import model.TM.EmployeeTM;
import model.TM.StocksTM;
import utill.CrudUtil;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class EmployeePageFormController implements Initializable {
    public JFXButton btnBack;
    public JFXTextField txtEmplyeeId;
    public JFXTextField txtEmployeeName;
    public JFXTextField txtEmployeeAddress;
    public JFXTextField txtEmployeeNic;
    public JFXTextField txtEmployeeContact;
    public JFXTextField txtUpdateId;
    public JFXTextField txtUpdateName;
    public JFXTextField txtUpdateAddress;
    public JFXTextField txtUpdateNic;
    public JFXTextField txtUpdateContact;
    public TableView<EmployeeTM> tblEmployee;
    public TableColumn colEmployeeId;
    public TableColumn colEmployeeName;
    public TableColumn colEmployeeAddress;
    public TableColumn colEmployeeNic;
    public TableColumn colEmployeeContact;
    public JFXTextField txtEmployeeSalary;
    public JFXTextField txtUpdateSalary;
    public JFXComboBox cmbId;
    public TableColumn colEmployeeSalary;

    public void backBtnOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) btnBack.getScene().getWindow();
        stage.close();

        Parent parent = FXMLLoader.load(getClass().getResource("/view/DashBoardPageForm.fxml"));
        Stage stage2 = new Stage();
        stage2.setScene(new Scene(parent));
        stage2.setResizable(false);
        stage2.show();
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        Employee employeeAdd=new Employee((String) cmbId.getValue(),txtUpdateName.getText(),txtUpdateAddress.getText(),txtUpdateNic.getText(),txtUpdateContact.getText(),Double.parseDouble(txtUpdateSalary.getText()));

        try{
            if(CrudUtil.execute("UPDATE employee SET eName=?,eAddress=?,eContact=?,eNic=?,eSalary=? WHERE eId=?",employeeAdd.geteName(),employeeAdd.geteAddress(),employeeAdd.geteContact(),employeeAdd.geteNic(),employeeAdd.geteSalary(),employeeAdd.geteId())){
                new Alert(Alert.AlertType.CONFIRMATION, "Saved!..").show();
            }
        }catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void btnAddOnAction(ActionEvent actionEvent) {
        Employee emp= new Employee(txtEmplyeeId.getText(),txtEmployeeName.getText(),txtEmployeeAddress.getText(),txtEmployeeContact.getText(),txtEmployeeNic.getText(),Double.parseDouble(txtEmployeeSalary.getText()));

        try{
            if (CrudUtil.execute("INSERT INTO employee VALUES (?,?,?,?,?,?)",emp.geteId(),emp.geteName(),emp.geteAddress(),emp.geteContact(),emp.geteNic(),emp.geteSalary())){
                new Alert(Alert.AlertType.CONFIRMATION, "Saved!..").show();
            }
        }catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        try{
            if (CrudUtil.execute("DELETE FROM employee WHERE eId=?",String.valueOf(cmbId.getValue()))){
                new Alert(Alert.AlertType.CONFIRMATION, "Deleted!..").show();
            }else{
                new Alert(Alert.AlertType.WARNING, "Can't Delete!..").show();
            }
        }catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            setEmployeeDetails();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        setEmployeeId();

        colEmployeeId.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        colEmployeeName.setCellValueFactory(new PropertyValueFactory<>("employeeName"));
        colEmployeeAddress.setCellValueFactory(new PropertyValueFactory<>("employeeAddress"));
        colEmployeeContact.setCellValueFactory(new PropertyValueFactory<>("employeeContact"));
        colEmployeeNic.setCellValueFactory(new PropertyValueFactory<>("employeeNic"));
        colEmployeeSalary.setCellValueFactory(new PropertyValueFactory<>("employeeSalary"));
    }

    private void setEmployeeDetails() throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT * FROM employee");
        ObservableList<EmployeeTM> obList = FXCollections.observableArrayList();
        while (result.next()){
            obList.add(
                    new EmployeeTM(
                            result.getString("eId"),
                            result.getString("eName"),
                            result.getString("eAddress"),
                            result.getString("eNic"),
                            result.getString("eContact"),
                            result.getDouble("eSalary")
                    )
            );
        }
        tblEmployee.setItems(obList);
    }


    private void setEmployeeId() {
        try {

            ObservableList<String> ObList = FXCollections.observableArrayList(
                    EmployeeCrudController.getEmployeeId()
            );
            cmbId.setItems(ObList);


            cmbId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                String eId= (String) newValue;

                ResultSet result = null;
                try {
                    result = CrudUtil.execute("SELECT * FROM employee WHERE eId=?",eId);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                try {
                    if (result.next()) {
                        txtUpdateName.setText(result.getString(2));
                        txtUpdateAddress.setText(result.getString(3));
                        txtUpdateNic.setText(result.getString(4));
                        txtUpdateContact.setText(result.getString(5));
                        txtUpdateSalary.setText(String.valueOf(result.getDouble(6)));
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
