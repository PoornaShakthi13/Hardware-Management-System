package model;

public class Order {
    private String OrderId;
    private String ItemCode;
    private String itemName;
    private double unitPrice;
    private int qty;
    private double total;
    private String date;

    public Order() {
    }

    public Order(String orderId, String itemCode, String itemName, double unitPrice, int qty, double total, String date) {
        OrderId = orderId;
        ItemCode = itemCode;
        this.itemName = itemName;
        this.unitPrice = unitPrice;
        this.qty = qty;
        this.total = total;
        this.date = date;
    }

    public String getOrderId() {
        return OrderId;
    }

    public void setOrderId(String orderId) {
        OrderId = orderId;
    }

    public String getItemCode() {
        return ItemCode;
    }

    public void setItemCode(String itemCode) {
        ItemCode = itemCode;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getInitPrice() {
        return unitPrice;
    }

    public void setInitPrice(String initPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
                "OrderId='" + OrderId + '\'' +
                ", ItemCode='" + ItemCode + '\'' +
                ", itemName='" + itemName + '\'' +
                ", initPrice='" + unitPrice + '\'' +
                ", qty=" + qty +
                ", total=" + total +
                ", date='" + date + '\'' +
                '}';
    }
}
