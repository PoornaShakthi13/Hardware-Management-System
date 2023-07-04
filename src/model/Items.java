package model;

public class Items {
    private String itemId;
    private String itemType;
    private String itemName;
    private int qty;
    private double price;

    public Items() {
    }

    public Items(String itemId, String itemType, String itemName, int qty, double price) {
        this.itemId = itemId;
        this.itemType = itemType;
        this.itemName = itemName;
        this.qty = qty;
        this.price = price;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Items{" +
                "itemId='" + itemId + '\'' +
                ", itemType='" + itemType + '\'' +
                ", itemName='" + itemName + '\'' +
                ", qty=" + qty +
                ", price=" + price +
                '}';
    }
}
