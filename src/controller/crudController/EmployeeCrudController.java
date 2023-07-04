package controller.crudController;

import utill.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeCrudController {
    public static ArrayList<String> getEmployeeId() throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT eId FROM employee");
        ArrayList<String> ids= new ArrayList<>();
        while (result.next()){
            ids.add(result.getString(1));
        }
        return ids;
    }
}
