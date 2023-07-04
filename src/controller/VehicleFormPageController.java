package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import controller.crudController.CustomerCrudController;
import controller.crudController.VehicleCrudController;
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
import model.TM.CustomerTM;
import model.TM.VehicleTM;
import model.Vehicle;
import utill.CrudUtil;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class VehicleFormPageController implements Initializable {
    public JFXButton btnBack;
    public JFXTextField txtVehicleNumber;
    public JFXTextField txtDate;
    public JFXComboBox cmbVehicleType;

    public TableView<VehicleTM> tblVehicle;
    public TableColumn colVehicleNumber;
    public TableColumn colVehicleType;
    public TableColumn colDate;

    public JFXTextField txtVehicleType;
    public JFXTextField txtDeleteDate;
    public JFXComboBox cmbVehcleNumber;

    public void backBtnOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) btnBack.getScene().getWindow();
        stage.close();

        Parent parent = FXMLLoader.load(getClass().getResource("/view/DashBoardPageForm.fxml"));
        Stage stage2 = new Stage();
        stage2.setScene(new Scene(parent));
        stage2.setResizable(false);
        stage2.show();
    }

    public void addBtnOnAction(ActionEvent actionEvent) {
        Vehicle v = new Vehicle(txtVehicleNumber.getText(), (String) cmbVehicleType.getValue(),txtDate.getText());

        try{
            if (CrudUtil.execute("INSERT INTO vehicle VALUES (?,?,?)",v.getVehicleNumber(),v.getVehicleType(),v.getDate())){
                new Alert(Alert.AlertType.CONFIRMATION, "Saved!..").show();
            }
        }catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void deleteBtnOnAction(ActionEvent actionEvent) {
        try{
            if (CrudUtil.execute("DELETE FROM vehicle WHERE VNumber=?",String.valueOf(cmbVehcleNumber.getValue()))){
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
        String[] vehicle={"Lorry","Cab","Car"};
        cmbVehicleType.getItems().addAll(vehicle);

        setVehicleNumbers();

        try {
            setTbleDetails();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        colVehicleNumber.setCellValueFactory(new PropertyValueFactory<>("vehicleNumber"));
        colVehicleType.setCellValueFactory(new PropertyValueFactory<>("vehicleType"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
    }

    private void setTbleDetails() throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT * FROM vehicle");
        ObservableList<VehicleTM> obList = FXCollections.observableArrayList();
        while (result.next()){
            obList.add(
                    new VehicleTM(
                            result.getString("VNumber"),
                            result.getString("VType"),
                            result.getString("date")
                    )
            );
        }
        tblVehicle.setItems(obList);
    }

    private void setVehicleNumbers() {
        try {

            ObservableList<String> ObList = FXCollections.observableArrayList(
                    VehicleCrudController.getVehicleNumbers()
            );
            cmbVehcleNumber.setItems(ObList);


            cmbVehcleNumber.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                String itemCode= (String) newValue;

                ResultSet result = null;
                try {
                    result = CrudUtil.execute("SELECT * FROM vehicle WHERE VNumber=?", String.valueOf(cmbVehcleNumber.getValue()));
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                try {
                    if (result.next()) {
                        txtVehicleType.setText(result.getString(2));
                        txtDeleteDate.setText(result.getString(3));
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
