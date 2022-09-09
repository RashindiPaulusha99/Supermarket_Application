package DAO.Custom.Impl;

import DAO.CrudUtil;
import DAO.Custom.CustomerDAO;
import Entity.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {

    @Override
    public boolean add(Customer c) throws SQLException, ClassNotFoundException {
        //save new customer in database
        return CrudUtil.executeUpdate("INSERT INTO Customer VALUES(?,?,?,?,?,?,?)", c.getId(), c.getTitle(), c.getName(), c.getAddress(), c.getCity(), c.getProvince(), c.getPostalCode());
    }

    @Override
    public boolean update(Customer c) throws SQLException, ClassNotFoundException {
        //update customer's details
        return CrudUtil.executeUpdate("UPDATE Customer SET title=?,name=?,address=?,city=?,province=?,postalCode=? WHERE id=?",
                c.getTitle(), c.getName(), c.getAddress(), c.getCity(), c.getProvince(), c.getPostalCode(), c.getId());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        if (CrudUtil.executeUpdate("DELETE FROM Customer WHERE id=?",id)){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public Customer search(String id) throws SQLException, ClassNotFoundException {
        //search relevant customer
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM Customer WHERE id=?",id);
        if (rst.next()){
            //return relevant customer
            return new Customer(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getString(7)
            );
        } else {
            //if data didn't come, return null
            return null;
        }
    }

    @Override
    public ArrayList<Customer> getAll() throws SQLException, ClassNotFoundException {
        //take data that should load to table
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM Customer");
        ArrayList<Customer> customers = new ArrayList<>();
        while (rst.next()) {
            customers.add(new Customer(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getString(7)
            ));
        }
        //return data
        return customers;
    }

    @Override
    public List<String> getCustomerIds() throws SQLException, ClassNotFoundException {
        //take customer ids from database
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM Customer");
        List<String> ids = new ArrayList<>();
        while (rst.next()){
            ids.add(rst.getString(1));
        }
        return ids;
    }

    @Override
    public boolean ifCustomerExists(String id) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeQuery("SELECT * FROM Customer WHERE id=?", id).next();
    }
}
