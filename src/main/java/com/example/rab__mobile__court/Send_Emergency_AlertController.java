package com.example.rab__mobile__court;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Send_Emergency_AlertController {

    @FXML
    private ComboBox<String> selectUserComboBox;
    @FXML
    private TextField emergencyElertId, institutionNameTextField, institutionLocationTextField;
    @FXML
    private TextArea descriptionTextArea, showEmergencyAlertTextArea;

    private final String filePath = "EmergencyAlerts.txt";
    private final List<EmergencyAlert> alertList = new ArrayList<>();

    @FXML
    public void initialize() {

        selectUserComboBox.getItems().addAll("Operations Administrator", "Rab Officer ", "Operation Administrator");
    }

    @FXML
    public void sendEmergencyAlertButtonOnClick(ActionEvent event) {

        if (!validateInputs()) return;


        EmergencyAlert alert = new EmergencyAlert(
                emergencyElertId.getText(),
                institutionNameTextField.getText(),
                institutionLocationTextField.getText(),
                descriptionTextArea.getText(),
                selectUserComboBox.getValue()
        );


        alertList.add(alert);


        Alert confirmation = new Alert(AlertType.CONFIRMATION, "Do you want to save this emergency alert?", ButtonType.YES, ButtonType.NO);
        confirmation.showAndWait();

        if (confirmation.getResult() == ButtonType.YES) {
            try (FileWriter writer = new FileWriter(new File(filePath), true)) {
                for (EmergencyAlert a : alertList) {
                    writer.write(a.toFileString() + "\n");
                }
                showInfoAlert("Success", "Emergency alert has been saved.");
                alertList.clear();
            } catch (Exception e) {
                showInfoAlert("Error", "Failed to save the emergency alert.");
            }
        } else {
            showInfoAlert("Cancelled", "Emergency alert was not saved.");
        }
    }

    @FXML
    public void loadAlertButtonOnClick(ActionEvent event) {
        showEmergencyAlertTextArea.clear();

        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNextLine()) {
                String[] tokens = scanner.nextLine().split(",");
                if (tokens.length == 5) {
                    String formattedData = String.format(
                            "emergancyAlertId : %s,\n institutionName : %s,\n institutionLocation : %s,\n alertReciever : %s,\n description : %s\n\n",
                            tokens[0], tokens[1], tokens[2], tokens[3], tokens[4]
                    );
                    showEmergencyAlertTextArea.appendText(formattedData);
                }
            }
        } catch (Exception e) {
            showInfoAlert("Error", "Failed to load emergency alerts.");
            e.printStackTrace();
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

        if (selectUserComboBox.getValue() == null) {
            showAlert(AlertType.WARNING, "Validation Error", "Please select an Emergency Alert Receiver.");
            return false;
        }

        if (descriptionTextArea.getText().isEmpty()) {
            showAlert(AlertType.WARNING, "Validation Error", "Description cannot be empty.");
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
    public void backDashBoardOnAction(ActionEvent actionEvent) throws IOException {
        SceneSwitcher.switchScene("Orpa2DashBoard.fxml",actionEvent, "DashBoard");

    }
}

