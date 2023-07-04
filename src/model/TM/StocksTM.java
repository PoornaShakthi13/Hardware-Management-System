package model.TM;

public class StocksTM {
    private String itemCode;
    private String itemName;
    private String itemType;
    private String qtyOnHand;
    private double unitPrice;

    public StocksTM() {
    }

    public StocksTM(String itemCode, String itemName, String itemType, String qtyOnHand, double unitPrice) {
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.itemType = itemType;
        this.qtyOnHand = qtyOnHand;
        this.unitPrice = unitPrice;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getQtyOnHand() {
        return qtyOnHand;
    }

    public void setQtyOnHand(String qtyOnHand) {
        this.qtyOnHand = qtyOnHand;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public String toString() {
        return "StocksTM{" +
                "itemCode='" + itemCode + '\'' +
                ", itemName='" + itemName + '\'' +
                ", itemType='" + itemType + '\'' +
                ", qtyOnHand='" + qtyOnHand + '\'' +
                ", unitPrice=" + unitPrice +
                '}';
    }
}
