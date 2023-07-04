package model.TM;

public class VehicleTM {
    private String vehicleNumber;
    private String vehicleType;
    private String date;

    public VehicleTM() {
    }

    public VehicleTM(String vehicleNumber, String vehicleType, String date) {
        this.vehicleNumber = vehicleNumber;
        this.vehicleType = vehicleType;
        this.date = date;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "vehicleNumber='" + vehicleNumber + '\'' +
                ", vehicleType='" + vehicleType + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
