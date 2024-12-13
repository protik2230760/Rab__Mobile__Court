package com.example.rab__mobile__court;

import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LoginController
{
    @javafx.fxml.FXML
    private ComboBox userTypeComboBox;
    @javafx.fxml.FXML
    private Label notificationlabel;
    @javafx.fxml.FXML
    private TextField enterUserNTextField;
    @javafx.fxml.FXML
    private PasswordField enterUserpasswordField;
    @javafx.fxml.FXML
    private TextField enterUserpasswordTextField;

    @javafx.fxml.FXML
    public void initialize() {userTypeComboBox.getItems().addAll("District Officer","Rab Officer", "Operations Administrator", "Lawyer", "Magistrate","Undercover Investigator") ;
    }

    @javafx.fxml.FXML
    public void logInOA(ActionEvent actionEvent) throws IOException {
        String userId = this.enterUserNTextField.getText();
        if (userId.equals("")) {
            this.notificationlabel.setText("Select User Id ");
        }

        String password = this.enterUserpasswordField.getText();
        if (password.equals("")) {
            this.notificationlabel.setText("Select password ");
        }

        String userType = (String)this.userTypeComboBox.getValue();
        if (userType == null) {
            this.notificationlabel.setText("Select User Type ");
        }
        if (userId.equals("2222526") && password.equals("123") && "Operations Administrator".equals(userType)) {
            SceneSwitcher.switchScene("Orpa1Dashboard.fxml", actionEvent, "DashBoard");
        }
        if (userId.equals("2230760") && password.equals("123") && "Rab Officer".equals(userType)) {
            SceneSwitcher.switchScene("Item.fxml", actionEvent, "DashBoard");
        }
        if (userId.equals("2111288") && password.equals("123") && "Lawyer".equals(userType)) {
            SceneSwitcher.switchScene("", actionEvent, "");
        }
        if (userId.equals("2230760") && password.equals("123") && "Magistrate".equals(userType)) {
            SceneSwitcher.switchScene("", actionEvent, "");
        }
        if (userId.equals("2222526") && password.equals("1234") && "Undercover Investigator".equals(userType)) {
            SceneSwitcher.switchScene("Orpa2DashBoard.fxml", actionEvent, "DashBoard");
        }
        if (userId.equals("2111288") && password.equals("1234") && "Undercover Investigator".equals(userType)) {
            SceneSwitcher.switchScene("", actionEvent, "DashBoard");
        }
    }
}