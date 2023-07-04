package model;

public class Customer {
    private String customerId;
    private String customerName;
    private String customerContact;
    private String address;

    public Customer() {
    }

    public Customer(String customerId, String customerName, String customerContact, String address) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerContact = customerContact;
        this.address = address;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerContact() {
        return customerContact;
    }

    public void setCustomerContact(String customerContact) {
        this.customerContact = customerContact;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId='" + customerId + '\'' +
                ", customerName='" + customerName + '\'' +
                ", customerContact='" + customerContact + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}

