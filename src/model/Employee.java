package model;

public class Employee {
    private String eId;
    private String eName;
    private String eAddress;
    private String eContact;
    private String eNic;
    private double eSalary;

    public Employee() {
    }

    public Employee(String eId, String eName, String eAddress, String eContact, String eNic, double eSalary) {
        this.eId = eId;
        this.eName = eName;
        this.eAddress = eAddress;
        this.eContact = eContact;
        this.eNic = eNic;
        this.eSalary = eSalary;
    }

    public String geteId() {
        return eId;
    }

    public void seteId(String eId) {
        this.eId = eId;
    }

    public String geteName() {
        return eName;
    }

    public void seteName(String eName) {
        this.eName = eName;
    }

    public String geteAddress() {
        return eAddress;
    }

    public void seteAddress(String eAddress) {
        this.eAddress = eAddress;
    }

    public String geteContact() {
        return eContact;
    }

    public void seteContact(String eContact) {
        this.eContact = eContact;
    }

    public String geteNic() {
        return eNic;
    }

    public void seteNic(String eNic) {
        this.eNic = eNic;
    }

    public double geteSalary() {
        return eSalary;
    }

    public void seteSalary(double eSalary) {
        this.eSalary = eSalary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "eId='" + eId + '\'' +
                ", eName='" + eName + '\'' +
                ", eAddress='" + eAddress + '\'' +
                ", eContact='" + eContact + '\'' +
                ", eNic='" + eNic + '\'' +
                ", eSalary=" + eSalary +
                '}';
    }
}
