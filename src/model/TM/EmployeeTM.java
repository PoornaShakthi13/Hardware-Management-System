package model.TM;

public class EmployeeTM {
    private String employeeId;
    private String employeeName;
    private String employeeAddress;
    private String employeeNic;
    private String employeeContact;
    private double employeeSalary;

    public EmployeeTM() {
    }

    public EmployeeTM(String employeeId, String employeeName, String employeeAddress, String employeeNic, String employeeContact, double employeeSalary) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.employeeAddress = employeeAddress;
        this.employeeNic = employeeNic;
        this.employeeContact = employeeContact;
        this.employeeSalary = employeeSalary;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeAddress() {
        return employeeAddress;
    }

    public void setEmployeeAddress(String employeeAddress) {
        this.employeeAddress = employeeAddress;
    }

    public String getEmployeeNic() {
        return employeeNic;
    }

    public void setEmployeeNic(String employeeNic) {
        this.employeeNic = employeeNic;
    }

    public String getEmployeeContact() {
        return employeeContact;
    }

    public void setEmployeeContact(String employeeContact) {
        this.employeeContact = employeeContact;
    }

    public double getEmployeeSalary() {
        return employeeSalary;
    }

    public void setEmployeeSalary(double employeeSalary) {
        this.employeeSalary = employeeSalary;
    }

    @Override
    public String toString() {
        return "EmployeeTM{" +
                "employeeId='" + employeeId + '\'' +
                ", employeeName='" + employeeName + '\'' +
                ", employeeAddress='" + employeeAddress + '\'' +
                ", employeeNic='" + employeeNic + '\'' +
                ", employeeContact='" + employeeContact + '\'' +
                ", employeeSalary=" + employeeSalary +
                '}';
    }
}
