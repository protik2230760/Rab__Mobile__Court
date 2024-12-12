package com.example.rab__mobile__court;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class Send_Emergency_AlertController {

    @FXML
    private ComboBox<String> selectUserComboBox;
    @FXML
    private TextField emergencyElertId, institutionNameTextField, institutionLocationTextField;
    @FXML
    private TextArea descriptionTextArea, showEmergencyAlertTextArea;

    private final String filePath = "EmergencyAlerts.txt";

    @FXML
    public void initialize() {
        // Populate ComboBox with example user roles
        selectUserComboBox.getItems().addAll("Operations Administrator", "Security Officer", "Director");
    }

    @FXML
    public void sendEmergencyAlertButtonOnClick(ActionEvent event) {
        if (!validateInputs()) return;

        Alert confirmation = new Alert(AlertType.CONFIRMATION, "Do you want to send this emergency alert?", ButtonType.OK, ButtonType.CANCEL);
        confirmation.showAndWait();

        if (confirmation.getResult() == ButtonType.OK) {
            try {
                writeAlertToFile();
                showInfoAlert("Success", "Emergency alert has been saved.");
            } catch (Exception e) {
                showInfoAlert("Error", "Failed to save the emergency alert.");
                e.printStackTrace();
            }
        } else {
            showInfoAlert("Cancelled", "Emergency alert was not saved.");
        }
    }

    @FXML
    public void loadAlertButtonOnClick(ActionEvent event) {
        showEmergencyAlertTextArea.clear();
        try {
            File f = new File(filePath);
            Scanner scanner = new Scanner(f);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                showEmergencyAlertTextArea.appendText(line + "\n");
            }
            scanner.close();
        } catch (Exception e) {
            showInfoAlert("Error", "Failed to load emergency alerts.");
            e.printStackTrace();
        }
    }

    private void writeAlertToFile() throws Exception {
        File f = new File(filePath);
        FileWriter fw = null;

        try {
            if (f.exists()) {
                fw = new FileWriter(f, true);
            } else {
                fw = new FileWriter(f);
            }

            String data = String.format(
                    "emergancyAlertId: %s,\n institutionName: %s,\n institutionLocation: %s,\n alertReciever: %s,\n description: %s\n\n",
                    emergencyElertId.getText(),
                    institutionNameTextField.getText(),
                    institutionLocationTextField.getText(),
                    selectUserComboBox.getValue(),
                    descriptionTextArea.getText()
            );

            fw.write(data);
        } finally {
            if (fw != null) {
                fw.close();
            }
        }
    }

    private boolean validateInputs() {
        if (emergencyElertId.getText().isEmpty()) {
            showAlert(AlertType.WARNING, "Validation Error", "Emergency Alert ID cannot be empty.");
            return false;
        }
        try {
            Integer.parseInt(emergencyElertId.getText());
        } catch (NumberFormatException e) {
            showAlert(AlertType.WARNING, "Validation Error", "Emergency Alert ID must be an integer.");
            return false;
        }

        if (institutionNameTextField.getText().isEmpty()) {
            showAlert(AlertType.WARNING, "Validation Error", "Institution Name cannot be empty.");
            return false;
        }

        if (institutionLocationTextField.getText().isEmpty()) {
            showAlert(AlertType.WARNING, "Validation Error", "Institution Location cannot be empty.");
            return false;
        }

        if (selectUserComboBox.getValue() == null || selectUserComboBox.getValue().isEmpty()) {
            showAlert(AlertType.WARNING, "Validation Error", "Please select an Emergency Alert Receiver from the ComboBox.");
            return false;
        }

        if (descriptionTextArea.getText().isEmpty()) {
            showAlert(AlertType.WARNING, "Validation Error", "Please enter a description of the alert.");
            return false;
        }

        return true;
    }

    private void showAlert(AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showInfoAlert(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    public void backDashBoardOnAction(ActionEvent actionEvent) {
    }
}

