package model.TM;

import javafx.scene.control.Button;

import javax.swing.*;

public class CartTM {
    private String Item_code;
    private String Item_Name;
    private double UnitPrice;
    private int Qty;
    private double Total;
    private Button btn;

    public CartTM() {
    }

    public CartTM(String item_code, String item_Name, double unitPrice, int qty, double total, Button btn) {
        Item_code = item_code;
        Item_Name = item_Name;
        UnitPrice = unitPrice;
        Qty = qty;
        Total = total;
        this.btn = btn;
    }

    public String getItem_code() {
        return Item_code;
    }

    public void setItem_code(String item_code) {
        Item_code = item_code;
    }

    public String getItem_Name() {
        return Item_Name;
    }

    public void setItem_Name(String item_Name) {
        Item_Name = item_Name;
    }

    public double getUnitPrice() {
        return UnitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        UnitPrice = unitPrice;
    }

    public int getQty() {
        return Qty;
    }

    public void setQty(int qty) {
        Qty = qty;
    }

    public double getTotal() {
        return Total;
    }

    public void setTotal(double total) {
        Total = total;
    }

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }

    @Override
    public String toString() {
        return "CartTM{" +
                "Item_code='" + Item_code + '\'' +
                ", Item_Name='" + Item_Name + '\'' +
                ", UnitPrice=" + UnitPrice +
                ", Qty=" + Qty +
                ", Total=" + Total +
                ", btn=" + btn +
                '}';
    }
}
