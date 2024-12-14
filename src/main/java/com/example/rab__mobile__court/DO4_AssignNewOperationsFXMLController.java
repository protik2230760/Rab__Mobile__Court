package com.example.rab__mobile__court;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.io.File;
import java.io.FileWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

public class DO4_AssignNewOperationsFXMLController {

    @FXML
    private TextField institutionNameTextField;

    @FXML
    private TextField locationTextField;

    @FXML
    private ComboBox<Integer> numberOfOfficerComboBox;

    @FXML
    private ComboBox<String> magistrateComboBox;

    @FXML
    private DatePicker operationDateDatePicker;

    @FXML
    private TextArea operationDetailsTextArea;

    private final ArrayList<String> operationList = new ArrayList<>();

    @FXML
    public void initialize() {
        // Populate ComboBoxes
        numberOfOfficerComboBox.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        magistrateComboBox.getItems().addAll("Magistrate A", "Magistrate B", "Magistrate C");
    }

    @FXML
    public void operationDetailsButtonOnClick(ActionEvent event) {
        // Get Input
        String institutionName = institutionNameTextField.getText().trim();
        String location = locationTextField.getText().trim();
        Integer numberOfOfficers = numberOfOfficerComboBox.getValue();
        String magistrateName = magistrateComboBox.getValue();
        LocalDate operationDate = operationDateDatePicker.getValue();

        // Validate Input
        if (institutionName.isEmpty() || location.isEmpty() || numberOfOfficers == null || magistrateName == null || operationDate == null) {
            showAlert(Alert.AlertType.ERROR, "Validation Error", "All fields must be filled.");
            return;
        }

        if (operationDate.isBefore(LocalDate.now())) {
            showAlert(Alert.AlertType.ERROR, "Validation Error", "Operation date cannot be in the past.");
            return;
        }

        // Format Data
        String operationDetails = String.format("Institution: %s, Location: %s, Officers: %d, Magistrate: %s, Date: %s",
                institutionName, location, numberOfOfficers, magistrateName, operationDate);

        // Store Data
        operationList.add(operationDetails);

        // Reset Input Fields
        institutionNameTextField.clear();
        locationTextField.clear();
        numberOfOfficerComboBox.setValue(null);
        magistrateComboBox.setValue(null);
        operationDateDatePicker.setValue(null);

        showAlert(Alert.AlertType.INFORMATION, "Success", "Operation details saved to list.");
    }


    @FXML
    public void saveToFileButtonOnClick(ActionEvent event) {
        // Confirm Save
        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Save Confirmation");
        confirmationAlert.setHeaderText(null);
        confirmationAlert.setContentText("Do you want to save the operations to a file?");
        Optional<ButtonType> result = confirmationAlert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            FileWriter fw = null;
            try {
                File file = new File("Operations.txt");
                fw = new FileWriter(file, true); // Open file in append mode

                for (String operation : operationList) {
                    fw.write(operation + "\n");
                }

                fw.flush(); // Ensure data is written to file

                // Update TextArea with saved data
                operationDetailsTextArea.clear();
                for (String operation : operationList) {
                    operationDetailsTextArea.appendText(operation + "\n");
                }

                operationList.clear(); // Clear the list after saving
                showAlert(Alert.AlertType.INFORMATION, "Success", "Operations saved to file successfully.");
            } catch (Exception e) {
                e.printStackTrace();
                showAlert(Alert.AlertType.ERROR, "Error", "Failed to save operations to file.");
            } finally {
                try {
                    if (fw != null) fw.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            showAlert(Alert.AlertType.INFORMATION, "Cancelled", "Save operation cancelled.");
        }
    }


    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
