package BO.Custom;

import BO.SuperBO;
import DTO.CustomerDTO;
import DTO.ItemDTO;
import DTO.OrderDTO;
import DTO.OrderDetailDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface PlaceOrderBO extends SuperBO {

    boolean placeOrder(OrderDTO order) throws SQLException, ClassNotFoundException;

    boolean saveOrderDetails(OrderDTO order) throws SQLException, ClassNotFoundException;

    boolean updateQty(String itemCode, int qty) throws SQLException, ClassNotFoundException;

    boolean updateOrder(OrderDTO order) throws SQLException, ClassNotFoundException;

    boolean updateOrderDetails(OrderDTO orde) throws SQLException, ClassNotFoundException;

    boolean deleteOrder(String id,ArrayList<OrderDetailDTO> items) throws SQLException, ClassNotFoundException;

    boolean deleteOrderDetail(String id, String code, ArrayList<OrderDetailDTO> items) throws SQLException, ClassNotFoundException;

    boolean updateQtyByDeleting(int sellQty, String itemCode) throws SQLException, ClassNotFoundException;

    String  setOrderIds() throws SQLException, ClassNotFoundException;

    List<String > getCustomerIds() throws SQLException, ClassNotFoundException;

    CustomerDTO searchCustomerData(String id) throws SQLException, ClassNotFoundException;

    List<String > getItemIds() throws SQLException, ClassNotFoundException;

    ItemDTO searchItemData(String id) throws SQLException, ClassNotFoundException;

}
