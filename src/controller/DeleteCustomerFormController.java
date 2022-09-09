package controller;

import BO.BOFactory;
import BO.Custom.CustomerBO;
import DTO.CustomerDTO;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class DeleteCustomerFormController {
    public TextField txtId;
    public TextField txtTitle;
    public TextField txtName;
    public TextField txtAddress;
    public TextField txtPostalCode;
    public TextField txtCity;
    public TextField txtProvince;

    private CustomerBO customerBO = (CustomerBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.CUSTOMER);

    public void selectCustomerOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        //search relevant customer
        CustomerDTO c1 = customerBO.searchCustomer(txtId.getText());
        if (c1 == null) {
            new Alert(Alert.AlertType.WARNING, "Empty Result Set.").show();
        } else {
            //if passed data didn't null pass data to setData method
            setData(c1);
        }
    }

    //set customer's details to textfields
    void setData(CustomerDTO c) {
        txtId.setText(c.getId());
        txtTitle.setText(c.getTitle());
        txtName.setText(c.getName());
        txtAddress.setText(c.getAddress());
        txtCity.setText(c.getCity());
        txtProvince.setText(c.getProvince());
        txtPostalCode.setText(c.getPostalCode());
    }

    public void deleteCustomerOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        //delete customer
        if (customerBO.deleteCustomer(txtId.getText())){
            new Alert(Alert.AlertType.CONFIRMATION, "Deleted.").showAndWait();
            txtId.clear();
            txtTitle.clear();
            txtName.clear();
            txtAddress.clear();
            txtCity.clear();
            txtProvince.clear();
            txtPostalCode.clear();
        }else{
            new Alert(Alert.AlertType.WARNING, "Try Again.").show();
        }
    }
}
