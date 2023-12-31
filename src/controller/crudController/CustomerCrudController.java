package controller.crudController;

import utill.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerCrudController {

    public static ArrayList<String> getcustomerId() throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT customerId FROM customer");
        ArrayList<String> ids= new ArrayList<>();
        while (result.next()){
            ids.add(result.getString(1));
        }
        return ids;
    }
}
