package model.TM;

import javafx.scene.control.Button;

public class ItemTM {
    private String itemId;
    private String itemName;
    private String qty;
    private double price;
    private Button btn;

    public ItemTM() {
    }

    public ItemTM(String itemId, String itemName, String qty, double price, Button btn) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.qty = qty;
        this.price = price;
        this.btn = btn;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }

    @Override
    public String toString() {
        return "ItemTM{" +
                "itemId='" + itemId + '\'' +
                ", itemName='" + itemName + '\'' +
                ", qty='" + qty + '\'' +
                ", price=" + price +
                ", btn=" + btn +
                '}';
    }
}
