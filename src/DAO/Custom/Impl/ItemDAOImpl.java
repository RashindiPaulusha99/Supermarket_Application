package DAO.Custom.Impl;

import DAO.CrudUtil;
import DAO.Custom.ItemDAO;
import Entity.Item;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDAOImpl implements ItemDAO {
    @Override
    public boolean add(Item i) throws SQLException, ClassNotFoundException {
        //save new item in database
        return CrudUtil.executeUpdate("INSERT INTO Item VALUES(?,?,?,?,?,?)", i.getCode(), i.getDescription(), i.getPackSize(), i.getUnitPrice(), i.getDiscount(), i.getQtyOnHand());
    }

    @Override
    public boolean update(Item i) throws SQLException, ClassNotFoundException {
        //update items's details
        return CrudUtil.executeUpdate("UPDATE Item SET description=?,packSize=?,unitPrice=?,qtyOnHand=?,discount=? WHERE code=?",
                i.getDescription(), i.getPackSize(), i.getUnitPrice(), i.getQtyOnHand(), i.getDiscount(), i.getCode());
    }

    @Override
    public boolean delete(String code) throws SQLException, ClassNotFoundException {
        if (CrudUtil.executeUpdate("DELETE FROM Item WHERE code=?",code)){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public Item search(String code) throws SQLException, ClassNotFoundException {
        //search relevant item
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM Item WHERE code=?", code);
        if (rst.next()){
            //return relevant item
            return new Item(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getDouble(4),
                    rst.getDouble(5),
                    rst.getInt(6)
            );
        } else {
            //if data didn't come return null
            return null;
        }
    }

    @Override
    public ArrayList<Item> getAll() throws SQLException, ClassNotFoundException {
        //take data that should load to table
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM Item");
        ArrayList<Item> items = new ArrayList<>();
        while (rst.next()) {
            items.add(new Item(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getDouble(4),
                    rst.getDouble(5),
                    rst.getInt(6)
            ));
        }
        //return data
        return items;
    }

    @Override
    public List<String> getItemIds() throws SQLException, ClassNotFoundException {
        //take item ids from database
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM Item");
        List<String> ids = new ArrayList<>();
        while (rst.next()){
            ids.add(rst.getString(1));
        }
        return ids;
    }

    @Override
    public Item getItem(String code) throws SQLException, ClassNotFoundException {
        //take item details from database
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM Item WHERE code =?", code);
        if (rst.next()){
            return new Item(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getDouble(4),
                    rst.getDouble(5),
                    rst.getInt(6)
            );
        }else {
            return null;
        }
    }

    @Override
    public int findqty(String id) throws SQLException, ClassNotFoundException {
        //search relevant item
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM Item WHERE code=?", id);
        if (rst.next()){
            //return relevant index
            return rst.getInt(6);
        } else {
            //if data didn't come return -1
            return -1;
        }
    }

    @Override
    public String findDescription(String id) throws SQLException, ClassNotFoundException {
        //search relevant item
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM Item WHERE code=?", id);
        if (rst.next()){
            //return relevant index
            return rst.getString(2);
        } else {
            //if data didn't come return null
            return null;
        }
    }

    @Override
    public boolean searchExistsItem(String id) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeQuery("SELECT * FROM Item WHERE code=?", id).next();
    }

    @Override
    public boolean updateItemQty(int sellQty, String code) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("UPDATE Item SET qtyOnHand =(qtyOnHand - " + sellQty + " ) WHERE code=?", code);
    }

    @Override
    public boolean updateItemQtyByDeleting(int sellQty, String code) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("UPDATE Item SET qtyOnHand =(qtyOnHand + " + sellQty + " ) WHERE code=?", code);
    }
}
