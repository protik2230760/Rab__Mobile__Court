package com.example.rab__mobile__court;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.*;
import java.util.Scanner;

public class InitialSurveyController {

    @FXML
    private TextField institutionNametextField;

    @FXML
    private TextField institutionLocationTextField;

    @FXML
    private TextArea surveyDescriptionTextArea;

    @FXML
    private ListView<String> assignedSurveyListView;

    private ObservableList<SurveyAssignment> surveyAssignments = FXCollections.observableArrayList();
    private final String filePath = "AssignedSurveys.txt";

    @FXML
    public void assignSurveyOnClick(ActionEvent actionEvent) {
        String institutionName = institutionNametextField.getText().trim();
        String institutionLocation = institutionLocationTextField.getText().trim();
        String surveyDescription = surveyDescriptionTextArea.getText().trim();

        if (institutionName.isEmpty() || institutionLocation.isEmpty() || surveyDescription.isEmpty()) {
            showAlert("Error", "All fields must be filled.");
            return;
        }

        SurveyAssignment newAssignment = new SurveyAssignment(institutionName, institutionLocation, surveyDescription);

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Do you want to assign this survey?");

        if (alert.showAndWait().get() == ButtonType.OK) {
            surveyAssignments.add(newAssignment);
            writeToFile(newAssignment);
            showAlert("Success", "Survey assigned and saved successfully.");
        } else {
            showAlert("Information", "Survey not assigned.");
        }
    }

    private void writeToFile(SurveyAssignment assignment) {
        FileWriter fw = null;
        try {
            File f = new File(filePath);
            if (f.exists()) {
                fw = new FileWriter(f, true);
            } else {
                fw = new FileWriter(f);
            }
            fw.write(assignment.toFileString() + "\n");
        } catch (Exception e) {
            showAlert("Error", "Error occurred while writing to file.");
        } finally {
            try {
                if (fw != null) {
                    fw.close();
                }
            } catch (Exception e) {
                showAlert("Error", "Error occurred while closing the file writer.");
            }
        }
    }

    @FXML
    public void assignedSurveyListOA(ActionEvent actionEvent) {
        assignedSurveyListView.getItems().clear();
        File f = new File(filePath);
        Scanner scanner = null;
        try {
            if (!f.exists()) {
                showAlert("Error", "No assigned surveys found.");
                return;
            }
            scanner = new Scanner(f);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                SurveyAssignment assignment = SurveyAssignment.fromFileString(line);
                assignedSurveyListView.getItems().add(assignment.toFormattedString());
            }
        } catch (Exception e) {
            showAlert("Error", "Failed to load assigned surveys.");
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    public void backToDashBoardOnClick(ActionEvent actionEvent) throws IOException {
        SceneSwitcher.switchScene("Orpa1DashBoard.fxml", actionEvent, "DashBoard");
    }
}
