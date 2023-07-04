package model;

public class OrderDetail {
    private String orId;
    private String CustomerId;
    private String itemCode;
    private int qty;
    private double unitPrice;

    public OrderDetail() {
    }

    public OrderDetail(String orId, String customerId, String itemCode, int qty, double unitPrice) {
        this.orId = orId;
        CustomerId = customerId;
        this.itemCode = itemCode;
        this.qty = qty;
        this.unitPrice = unitPrice;
    }

    public String getOrId() {
        return orId;
    }

    public void setOrId(String orId) {
        this.orId = orId;
    }

    public String getCustomerId() {
        return CustomerId;
    }

    public void setCustomerId(String customerId) {
        CustomerId = customerId;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public String
    toString() {
        return "OrderDetail{" +
                "orId='" + orId + '\'' +
                ", CustomerId='" + CustomerId + '\'' +
                ", itemCode='" + itemCode + '\'' +
                ", qty=" + qty +
                ", unitPrice=" + unitPrice +
                '}';
    }
}
