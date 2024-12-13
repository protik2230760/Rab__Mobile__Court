package com.example.rab__mobile__court;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.*;
import java.util.List;

public class Approve_Survey_RequestController {

    @FXML
    private TableView<SurveyRequest> initialSurveyRequestTableView;

    @FXML
    private TableColumn<SurveyRequest, String> investigatorIdCol;

    @FXML
    private TableColumn<SurveyRequest, String> instituionNameCol;

    @FXML
    private TableColumn<SurveyRequest, String> institutionLocationCol;

    @FXML
    private TableColumn<SurveyRequest, String> surveyDate;

    @FXML
    private TableColumn<SurveyRequest, String> surveyDetails;

    private final String filePath = "InitialSurveyRequests.txt";
    private final String approvedFilePath = "ApprovedRequests.txt";
    private final String rejectedFilePath = "RejectedRequests.txt";
    private ObservableList<SurveyRequest> surveyRequests = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        investigatorIdCol.setCellValueFactory(data -> data.getValue().investigatorIdProperty());
        instituionNameCol.setCellValueFactory(data -> data.getValue().institutionNameProperty());
        institutionLocationCol.setCellValueFactory(data -> data.getValue().institutionLocationProperty());
        surveyDate.setCellValueFactory(data -> data.getValue().surveyDateProperty());
        surveyDetails.setCellValueFactory(data -> data.getValue().surveyDetailsProperty());

        loadSurveyRequests();
    }

    private void loadSurveyRequests() {
        surveyRequests.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                SurveyRequest request = SurveyRequest.fromFileString(line);
                surveyRequests.add(request);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        initialSurveyRequestTableView.setItems(surveyRequests);
    }

    @FXML
    public void approveSurveyRequestOnClick(ActionEvent actionEvent) {
        processRequest(approvedFilePath, "approved");
    }

    @FXML
    public void rejectSurveyRequestOnClick(ActionEvent actionEvent) {
        processRequest(rejectedFilePath, "rejected");
    }

    private void processRequest(String targetFilePath, String action) {
        SurveyRequest selectedRequest = initialSurveyRequestTableView.getSelectionModel().getSelectedItem();

        if (selectedRequest == null) {
            showAlert("Error", "No request selected.");
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Are you sure you want to " + action + " this request?");

        if (alert.showAndWait().get() == ButtonType.OK) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(targetFilePath, true))) {
                writer.write(selectedRequest.toFileString() + "\n");
                surveyRequests.remove(selectedRequest);
                initialSurveyRequestTableView.refresh();
                showAlert("Success", "Request " + action + " successfully.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            showAlert("Information", "Request not " + action + ".");
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
        SceneSwitcher.switchScene("Orpa2DashBoard.fxml", actionEvent, "DashBoard");
    }
}
