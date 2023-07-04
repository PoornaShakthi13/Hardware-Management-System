package controller.crudController;

import utill.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemCrudController {
    public static ArrayList<String> getItemId() throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT itemCode FROM item");
        ArrayList<String> ids= new ArrayList<>();
        while (result.next()){
            ids.add(result.getString(1));
        }
        return ids;
    }

    public static ArrayList<String> getAllItems() throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT itemCode FROM item");
        ArrayList<String> ids= new ArrayList<>();
        while (result.next()){
            ids.add(result.getString(1));
        }
        return ids;
    }
}
