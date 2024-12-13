package com.example.rab__mobile__court;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Request_For_Initial_SurveyController {

    @FXML
    private TextField institutionNameTextField;

    @FXML
    private TextArea surveyDetailsTextArea;

    @FXML
    private DatePicker surveyDatePicker;

    @FXML
    private TextField investigatorIdTextField;

    @FXML
    private TextField institutionLocationTextField;

    @FXML
    private TextArea notificationTextArea;

    private ObservableList<SurveyRequest> surveyRequests = FXCollections.observableArrayList();
    private final String filePath = "InitialSurveyRequests.txt";

    @FXML
    public void sendInitialSurveyRequestOnClick(ActionEvent actionEvent) {
        String investigatorId = investigatorIdTextField.getText().trim();
        String institutionName = institutionNameTextField.getText().trim();
        String institutionLocation = institutionLocationTextField.getText().trim();
        LocalDate surveyDate = surveyDatePicker.getValue();
        String surveyDetails = surveyDetailsTextArea.getText().trim();

        if (investigatorId.isEmpty() || institutionName.isEmpty() || institutionLocation.isEmpty() || surveyDate == null || surveyDetails.isEmpty()) {
            showAlert("Error", "All fields must be filled.");
            return;
        }

        if (surveyDate.isBefore(LocalDate.now())) {
            showAlert("Error", "Survey date cannot be in the past.");
            return;
        }

        for (SurveyRequest request : surveyRequests) {
            if (request.getInvestigatorId().equals(investigatorId)) {
                showAlert("Error", "Investigator ID must be unique.");
                return;
            }
        }

        SurveyRequest newRequest = new SurveyRequest(investigatorId, institutionName, institutionLocation, surveyDate, surveyDetails);

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Do you want to send the request?");

        if (alert.showAndWait().get() == ButtonType.OK) {
            surveyRequests.add(newRequest);
            writeToFile(newRequest);
            notificationTextArea.setText(newRequest.toString());
            showAlert("Success", "Request sent successfully.");
        } else {
            showAlert("Information", "Request not sent.");
        }
    }

    private void writeToFile(SurveyRequest request) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(request.toFileString() + "\n");
        } catch (IOException e) {
            e.printStackTrace();
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
    public void backDashBoardOnAction(ActionEvent actionEvent) throws IOException {
        SceneSwitcher.switchScene("Orpa2DashBoard.fxml", actionEvent, "DashBoard");
    }
}
