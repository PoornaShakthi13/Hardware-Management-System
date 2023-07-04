package controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.TM.StocksTM;
import utill.CrudUtil;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CashierStockPageFormController implements Initializable {
    public JFXButton btnBack;
    public TableView tblStock;
    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colQuantity;
    public TableColumn colType;
    public TableColumn colPrice1;

    public void backBtnOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) btnBack.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            setTableDetails();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        colId.setCellValueFactory(new PropertyValueFactory<>("ItemCode"));
        colName.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        colType.setCellValueFactory(new PropertyValueFactory<>("itemType"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));
        colPrice1.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
    }

    private void setTableDetails() throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT * FROM item");
        ObservableList<StocksTM> obList = FXCollections.observableArrayList();
        while (result.next()) {
            obList.add(
                    new StocksTM(
                            result.getString("itemCode"),
                            result.getString("itemName"),
                            result.getString("itemType"),
                            result.getString("qtyOnHand"),
                            result.getDouble("unitprice")
                    )
            );
        }
        tblStock.setItems(obList);
    }
}
