package com.example.rab__mobile__court;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;

import java.io.File;
import java.io.FileWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class DO1_CommandOperationsAdministratorFXMLController {

    @FXML
    private TextField institutionNameTextField, institutionNameTextField1, locationTextField, urgencyReasonTextField;

    @FXML
    private DatePicker investigationDateDatePicker;

    @FXML
    private TextArea investigationInfoTextArea;

    private final ArrayList<Operation> operationList = new ArrayList<>();
    private final String operationFilePath = "OperationsInfo.txt";

    @FXML
    public void loadInformationDetailsOnClick(ActionEvent event) {
        String title = institutionNameTextField1.getText().trim();
        String institutionName = institutionNameTextField.getText().trim();
        String institutionAddress = locationTextField.getText().trim();
        String urgencyReason = urgencyReasonTextField.getText().trim();
        LocalDate operationDate = investigationDateDatePicker.getValue();

        // Validation
        if (title.isEmpty() || institutionName.isEmpty() || institutionAddress.isEmpty() || urgencyReason.isEmpty() || operationDate == null) {
            showAlert("Error", "All fields must be filled.");
            return;
        }
        if (operationDate.isBefore(LocalDate.now())) {
            showAlert("Error", "Operation date cannot be in the past.");
            return;
        }

        // Add to ArrayList
        Operation operation = new Operation(title, institutionName, institutionAddress, urgencyReason, operationDate.toString());
        operationList.add(operation);

        // Show in TextArea
        investigationInfoTextArea.setText("");
        for (Operation op : operationList) {
            investigationInfoTextArea.appendText(op + "\n");
        }
    }

    @FXML
    public void saveToFileOnClick(ActionEvent event) {
        if (operationList.isEmpty()) {
            showAlert("Error", "No information to save. Please load details first.");
            return;
        }

        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Do you want to save this information?");
        if (alert.showAndWait().get() == ButtonType.OK) {
            writeOperationsToFile();
            showAlert("Success", "Operations information saved successfully.");
        } else {
            showAlert("Information", "Save operation was canceled.");
        }
    }

    private void writeOperationsToFile() {
        FileWriter fw = null;
        try {
            File file = new File(operationFilePath);
            fw = new FileWriter(file, true); // Append mode
            for (Operation op : operationList) {
                fw.write(op.toFileString() + "\n");
            }
            operationList.clear(); // Clear after saving
        } catch (Exception e) {
            showAlert("Error", "Error occurred while writing to the file.");
        } finally {
            try {
                if (fw != null) fw.close();
            } catch (Exception e) {
                showAlert("Error", "Error occurred while closing the file writer.");
            }
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
