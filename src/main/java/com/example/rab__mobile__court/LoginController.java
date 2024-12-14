package com.example.rab__mobile__court;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import java.io.IOException;

public class LoginController {

    @javafx.fxml.FXML
    private ComboBox<String> userTypeComboBox;

    @javafx.fxml.FXML
    private TextField enterUserNTextField;

    @javafx.fxml.FXML
    private PasswordField enterUserpasswordField;
    @javafx.fxml.FXML
    private Label notificationlabel;
    @javafx.fxml.FXML
    private TextField enterUserpasswordTextField;

    @javafx.fxml.FXML
    public void initialize() {
        userTypeComboBox.getItems().addAll(
                "District Officer",
                "Rab Officer",
                "Operations Administrator",
                "Lawyer",
                "Magistrate",
                "Undercover Investigator"
        );
    }

    @javafx.fxml.FXML
    public void logInOA(ActionEvent actionEvent) throws IOException {
        String userId = enterUserNTextField.getText().trim();
        String password = enterUserpasswordField.getText().trim();
        String userType = userTypeComboBox.getValue();

        if (userId.isEmpty()) {
            showAlert("Validation Error", "Please enter a valid User ID.");
            return;
        }

        if (password.isEmpty()) {
            showAlert("Validation Error", "Please enter your password.");
            return;
        }

        if (userType == null) {
            showAlert("Validation Error", "Please select a User Type.");
            return;
        }

        if (userId.equals("2222526") && password.equals("123") && userType.equals("Operations Administrator")) {
            SceneSwitcher.switchScene("Orpa1Dashboard.fxml", actionEvent, "DashBoard");
        } else if (userId.equals("2230760") && password.equals("123") && userType.equals("Rab Officer")) {
            SceneSwitcher.switchScene("Item.fxml", actionEvent, "DashBoard");
        } else if (userId.equals("2111288") && password.equals("123") && userType.equals("Lawyer")) {
            SceneSwitcher.switchScene("fahmidaDashboard2.Fxml", actionEvent, "Dashboard");
        } else if (userId.equals("2230760") && password.equals("1234") && userType.equals("Magistrate")) {
            SceneSwitcher.switchScene("MagistrateDashboard.fxml", actionEvent, "Dashboard");
        } else if (userId.equals("2222526") && password.equals("1234") && userType.equals("Undercover Investigator")) {
            SceneSwitcher.switchScene("Orpa2DashBoard.fxml", actionEvent, "DashBoard");
        } else if (userId.equals("2111288") && password.equals("1234") && userType.equals("District Officer")) {
            SceneSwitcher.switchScene("fahmidadasboard1.fxml", actionEvent, "DashBoard");
        } else {
            showAlert("Login Failed", "Invalid User ID, Password, or User Type. Please try again.");
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
