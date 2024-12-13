package com.example.rab__mobile__court;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class EmergencySupportSceneController {

    @FXML
    private TableView<EmergencyAlert> tableView;
    @FXML
    private TableColumn<EmergencyAlert, String> emergIdTableCol, instiNameTableCol, locationCol, helDesCol;
    @FXML
    private TextField reqIdTextField, reqNumOfSubTextField, estimTimeOfArrivTextField;
    @FXML
    private TextArea responseTextArea;

    private final String filePath = "EmergencyAlerts.txt";
    private final String responseFilePath = "EmergencyResponses.txt";
    private final Set<String> usedRequestIds = new HashSet<>();

    @FXML
    public void initialize() {
        emergIdTableCol.setCellValueFactory(new PropertyValueFactory<>("emergencyId"));
        instiNameTableCol.setCellValueFactory(new PropertyValueFactory<>("institutionName"));
        locationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        helDesCol.setCellValueFactory(new PropertyValueFactory<>("description"));
    }

    @FXML
    public void loadEmergencyReqButtonOnClick(ActionEvent event) {
        tableView.getItems().clear();

        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNextLine()) {
                String[] tokens = scanner.nextLine().split(",");
                EmergencyAlert alert = new EmergencyAlert(tokens[0], tokens[1], tokens[2], tokens[4], tokens[3]);
                tableView.getItems().add(alert);
            }
        } catch (Exception e) {
            showInfoAlert("Error", "Failed to load emergency requests.");
        }
    }

    @FXML
    public void respondToEmerSupOnClick(ActionEvent event) {
        if (!validateInputs()) return;

        Alert confirmation = new Alert(AlertType.CONFIRMATION, "Do you want to send this response?", ButtonType.YES, ButtonType.NO);
        confirmation.showAndWait();

        if (confirmation.getResult() == ButtonType.YES) {
            try (FileWriter writer = new FileWriter(new File(responseFilePath), true)) {
                String response = String.format(
                        "Request ID: %s,\nNumber of Subordinates: %s,\nEstimated Time of Arrival: %s,\nResponse: %s\n\n",
                        reqIdTextField.getText(),
                        reqNumOfSubTextField.getText(),
                        estimTimeOfArrivTextField.getText(),
                        responseTextArea.getText()
                );
                writer.write(response);
                showInfoAlert("Success", "Response has been saved.");
            } catch (Exception e) {
                showInfoAlert("Error", "Failed to save the response.");
            }
        } else {
            showInfoAlert("Cancelled", "Response was not saved.");
        }
    }

    private boolean validateInputs() {
        if (responseTextArea.getText().isEmpty()) {
            showAlert(AlertType.WARNING, "Validation Error", "Response cannot be empty.");
            return false;
        }

        String requestId = reqIdTextField.getText();
        if (requestId.isEmpty() || usedRequestIds.contains(requestId)) {
            showAlert(AlertType.WARNING, "Validation Error", "Request ID must be unique and not empty.");
            return false;
        }

        usedRequestIds.add(requestId);

        try {
            int subordinates = Integer.parseInt(reqNumOfSubTextField.getText());
            if (subordinates < 3 || subordinates > 10) {
                showAlert(AlertType.WARNING, "Validation Error", "Number of subordinates must be between 3 and 10.");
                return false;
            }
        } catch (NumberFormatException e) {
            showAlert(AlertType.WARNING, "Validation Error", "Number of subordinates must be an integer.");
            return false;
        }

        if (!estimTimeOfArrivTextField.getText().matches("\\d{2}:\\d{2}")) {
            showAlert(AlertType.WARNING, "Validation Error", "Estimated Time of Arrival must be in HH:mm format.");
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
    public void returnHomeButtonOnClick(ActionEvent actionEvent) throws IOException {
        SceneSwitcher.switchScene("Item.fxml",actionEvent, "DashBoard");
    }
}
