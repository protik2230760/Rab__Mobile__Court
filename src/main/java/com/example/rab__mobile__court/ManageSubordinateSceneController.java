package com.example.rab__mobile__court;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ManageSubordinateSceneController {

    @FXML
    private DatePicker dateOfOpDatePicker;
    @FXML
    private TextField locationTextField, instiNameTextField, numOfSubTextField;
    @FXML
    private ComboBox<String> instiTypeCB;
    @FXML
    private TextArea viewDraftTextArea;

    private final List<SubordinateInfo> subordinateList = new ArrayList<>();
    private final String filePath = "SubordinateInfo.txt";

    @FXML
    public void initialize() {

        instiTypeCB.getItems().addAll("General", "Educational", "Medical", "Corporate");
    }

    @FXML
    public void viewDraftButtonOnClick(ActionEvent event) {
        viewDraftTextArea.clear(); // Clear the TextArea before showing the draft

        for (SubordinateInfo info : subordinateList) {
            viewDraftTextArea.appendText(info.toString() + "\n");
        }
    }

    @FXML
    public void saveInfoButtonOnClick(ActionEvent event) {
        if (subordinateList.isEmpty()) {
            showInfoAlert("Error", "No information available to save.");
            return;
        }

        FileWriter fw = null;
        try {
            File f = new File(filePath);
            if (f.exists()) {
                fw = new FileWriter(f, true); // Append mode
            } else {
                fw = new FileWriter(f); // Create new file
            }

            for (SubordinateInfo info : subordinateList) {
                fw.write(info.toString() + "\n");
            }

            showInfoAlert("Success", "Information has been saved successfully.");
        } catch (Exception e) {
            showInfoAlert("Error", "Failed to save the information.");
            e.printStackTrace();
        } finally {
            try {
                if (fw != null) fw.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void addInfoButtonOnClick(ActionEvent event) {

        if (!validateInputs()) return;


        SubordinateInfo info = new SubordinateInfo(
                dateOfOpDatePicker.getValue().toString(),
                locationTextField.getText(),
                instiNameTextField.getText(),
                instiTypeCB.getValue(),
                Integer.parseInt(numOfSubTextField.getText())
        );

        subordinateList.add(info);
        showInfoAlert("Success", "Information added to the draft.");
    }

    private boolean validateInputs() {
        if (dateOfOpDatePicker.getValue() == null) {
            showAlert(AlertType.WARNING, "Validation Error", "Date of Operation cannot be empty.");
            return false;
        }
        if (dateOfOpDatePicker.getValue().isBefore(LocalDate.now())) {
            showAlert(AlertType.WARNING, "Validation Error", "Date of Operation cannot be in the past.");
            return false;
        }

        if (locationTextField.getText().isEmpty()) {
            showAlert(AlertType.WARNING, "Validation Error", "Location cannot be empty.");
            return false;
        }

        if (instiNameTextField.getText().isEmpty()) {
            showAlert(AlertType.WARNING, "Validation Error", "Institution Name cannot be empty.");
            return false;
        }

        if (instiTypeCB.getValue() == null) {
            showAlert(AlertType.WARNING, "Validation Error", "Please select an Institution Type.");
            return false;
        }

        try {
            int numOfSub = Integer.parseInt(numOfSubTextField.getText());
            if (numOfSub < 5 || numOfSub > 15) {
                showAlert(AlertType.WARNING, "Validation Error", "Number of Subordinates must be between 5 and 15.");
                return false;
            }
        } catch (NumberFormatException e) {
            showAlert(AlertType.WARNING, "Validation Error", "Number of Subordinates must be a valid number.");
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


