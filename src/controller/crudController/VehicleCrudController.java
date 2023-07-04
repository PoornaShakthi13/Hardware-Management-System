package controller.crudController;

import utill.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class VehicleCrudController {
    public static ArrayList<String> getVehicleNumbers() throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT VNumber FROM vehicle");
        ArrayList<String> ids= new ArrayList<>();
        while (result.next()){
            ids.add(result.getString(1));
        }
        return ids;
    }
}
