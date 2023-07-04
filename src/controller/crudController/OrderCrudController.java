package controller.crudController;

import model.Order;
import model.OrderDetail;
import utill.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderCrudController {
    public boolean saveOrder(Order order) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO Orders VALUES(?,?,?,?,?,?,?)",
                order.getOrderId(), order.getItemCode(), order.getItemName(), order.getInitPrice(), order.getQty(), order.getTotal(), order.getDate());
    }
    public boolean saveOrderDetails(ArrayList<OrderDetail> detail) throws SQLException, ClassNotFoundException {
        for (OrderDetail det:detail
        ) {
            boolean isDetailsSaved=CrudUtil.execute("INSERT INTO OrderDetail VALUES(?,?,?,?)",
                    det.getOrId(),det.getItemCode(),det.getUnitPrice(),det.getQty());
            if (isDetailsSaved){
                if (!updateQty(det.getItemCode(), det.getQty())){
                    return false;
                }
            }else{
                return false;
            }
        }
        return true;
    }

    private boolean updateQty(String itemCode, int qty) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE Items SET Qty_On_Hand=Qty_On_Hand-? WHERE Item_code=?", qty,itemCode);
    }
    public String getOrderId() throws SQLException, ClassNotFoundException, SQLException {
        ResultSet set = CrudUtil.execute("SELECT id FROM Orders ORDER BY id DESC LIMIT 1");
        if (set.next()){
            return set.getString(1);
        }else{
            return "D001";
        }
    }
}
