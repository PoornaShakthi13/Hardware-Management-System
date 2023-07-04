package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.mysql.cj.x.protobuf.MysqlxCrud;
import controller.crudController.CustomerCrudController;
import controller.crudController.ItemCrudController;
import controller.crudController.OrderCrudController;
import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Order;
import model.OrderDetail;
import model.TM.CartTM;
import utill.CrudUtil;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class CashierOrderPageFormController implements Initializable {
    public JFXButton btnBack;
    public JFXTextField txtCustomerName;
    public JFXTextField txtAddress;
    public JFXTextField txtContact;
    public JFXComboBox cmbItemCode;
    public JFXTextField txtItemName;
    public JFXTextField txtQtyOnHand;
    public JFXTextField txtUnitPrice;
    public JFXTextField txtQty;
    public TableView<CartTM> tblCart;
    public TableColumn colItemCode;
    public TableColumn colItemName;
    public TableColumn colUnitPrice;
    public TableColumn colQty;
    public TableColumn colTotal;
    public TableColumn colDelete;
    public Label lblTotal;
    public JFXComboBox cmbCustomerId;

    int i = 0;
    int newQty;
    double profit;

    ObservableList<CartTM> tmList = FXCollections.observableArrayList();

    private CartTM isExists(String itemCode) {
        for (CartTM tm : tmList
        ) {
            if (tm.getItem_code().equals(itemCode)) {
                return tm;
            }
        }
        return null;
    }

    private void calculateTotal() {
        double total = 0;
        for (CartTM tm : tmList
        ) {
            total += tm.getTotal();
        }
        lblTotal.setText(String.valueOf(total));
    }

    public void backBtnOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) btnBack.getScene().getWindow();
        stage.close();
    }

    public void btnAddCustomerOnAction(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("../view/CashierCustomerPageForm.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.setResizable(false);
        stage.show();
    }

    public void btnAddToCartOnAction(ActionEvent actionEvent) {
        i = i + 1;
        String order = "Or00";
        String id = order + i;

        double unitPrice = Double.parseDouble(txtUnitPrice.getText());
        int qty = Integer.parseInt(txtQty.getText());
        double totalCost = unitPrice * qty;

        CartTM isExists = isExists((String) cmbItemCode.getValue());

        if (isExists != null) {
            for (CartTM temp : tmList
            ) {
                if (temp.equals(isExists)) {
                    newQty = temp.getQty() + qty;
                    temp.setQty((temp.getQty() + qty));
                    temp.setTotal(temp.getTotal() + totalCost);
                }
            }
        } else {
            Button btn = new Button("Delete");
            btn.setStyle("-fx-background-color: #2e1c1c");

            CartTM tm = new CartTM(
                    (String) cmbItemCode.getValue(),
                    txtItemName.getText(),
                    unitPrice,
                    qty,
                    totalCost,
                    btn
            );

            btn.setOnAction(e -> {
                tmList.remove(tm);
                calculateTotal();
            });

            tmList.add(tm);
            tblCart.setItems(tmList);
        }
        tblCart.refresh();
        calculateTotal();
    }

    public void placeOrderOnAction(ActionEvent actionEvent)  {

        String orId = "Or00";
        i=i+1;

        try {
            updateQty();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        String date=new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        Order order=new Order(orId+i, (String) cmbItemCode.getValue(),txtItemName.getText(),Double.parseDouble(txtUnitPrice.getText()),Integer.parseInt(txtQty.getText()),Double.parseDouble(lblTotal.getText()),date);

        ArrayList<OrderDetail> details = new ArrayList<>();
        for (CartTM tm : tmList
        ) {
            details.add(
                    new OrderDetail(
                            "D024",
                            tm.getItem_code(),
                            tm.getItem_Name(),
                            tm.getQty(),
                            tm.getUnitPrice()
                    )
            );

        }

        Connection connection= null;

        try {
            connection= DBConnection.getInstance().getConnection();
            connection.setAutoCommit(false);
            boolean isOrderSaved = new OrderCrudController().saveOrder(order);
            if (isOrderSaved) {
                boolean isDetailsSaved=new OrderCrudController().saveOrderDetails(details);
                if (isDetailsSaved){
                    connection.commit();
                    new Alert(Alert.AlertType.CONFIRMATION,"Saved!").show();
                }else{
                    connection.rollback();
                    new Alert(Alert.AlertType.ERROR,"Error!").show();
                }
            }else{
                connection.rollback();
                new Alert(Alert.AlertType.ERROR,"Error!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }


        OrderDetail s = new OrderDetail(orId+i, String.valueOf(cmbCustomerId.getValue()), String.valueOf(cmbItemCode.getValue()), Integer.parseInt(txtQty.getText()), Double.parseDouble(txtUnitPrice.getText()));
        try {
            CrudUtil.execute("INSERT INTO orderdetail VALUES (?,?,?,?,?)",s.getOrId(),s.getCustomerId(),s.getItemCode(),s.getQty(), s.getUnitPrice());
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }



    private void updateQty() throws SQLException, ClassNotFoundException {
        String itemCode = (String) cmbItemCode.getValue();
        int onHand = Integer.parseInt(txtQtyOnHand.getText());
        int qty = Integer.parseInt(txtQty.getText());
        int newQty = onHand - qty;

        CrudUtil.execute("UPDATE items SET Qty_On_Hand=? WHERE Item_code=?",newQty,itemCode);
    }

    public void printBillOnAction(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        colItemCode.setCellValueFactory(new PropertyValueFactory<>("Item_code"));
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("description"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("UnitPrice"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("Qty"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        colDelete.setCellValueFactory(new PropertyValueFactory<>("btn"));

        setCustomerIds();
        setItemCodes();
    }

    private void setItemCodes() {
        try {

            ObservableList<String> ObList = FXCollections.observableArrayList(
                    ItemCrudController.getItemId()
            );
            cmbItemCode.setItems(ObList);


            cmbItemCode.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                String itemCode= (String) newValue;

                ResultSet result = null;
                try {
                    result = CrudUtil.execute("SELECT * FROM item WHERE itemCode=?",itemCode);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                try {
                    if (result.next()) {
                        txtItemName.setText(result.getString(2));
                        txtQtyOnHand.setText(result.getString(4));
                        txtUnitPrice.setText(String.valueOf(result.getString(5)));
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

    private void setCustomerIds() {
        try {

            ObservableList<String> ObList = FXCollections.observableArrayList(
                    CustomerCrudController.getcustomerId()
            );
            cmbCustomerId.setItems(ObList);


            cmbCustomerId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                String C_ID= (String) newValue;

                ResultSet result = null;
                try {
                    result = CrudUtil.execute("SELECT * FROM customer WHERE customerId=?",C_ID);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                try {
                    if (result.next()) {
                        txtCustomerName.setText(result.getString(2));
                        txtAddress.setText(result.getString(3));
                        txtContact.setText(result.getString(4));
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
