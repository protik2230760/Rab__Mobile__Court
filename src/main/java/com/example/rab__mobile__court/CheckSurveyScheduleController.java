package com.example.rab__mobile__court;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.*;
import java.util.Scanner;

public class CheckSurveyScheduleController {

    @FXML
    private TableView<SurveySchedule> surveyScheduleTableView;

    @FXML
    private TableColumn<SurveySchedule, String> surveyIdCol;

    @FXML
    private TableColumn<SurveySchedule, String> institutionCol;

    @FXML
    private TableColumn<SurveySchedule, String> locationCol;

    @FXML
    private TableColumn<SurveySchedule, String> surveyDateCol;

    private final String filePath = "SurveySchedule.txt";

    private final String completedFilePath = "CompletedSurveys.txt";
    @FXML
    private AnchorPane loadSurveySchedule;

    @FXML
    public void initialize() {
        surveyIdCol.setCellValueFactory(new PropertyValueFactory<>("surveyId"));
        institutionCol.setCellValueFactory(new PropertyValueFactory<>("institutionName"));
        locationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        surveyDateCol.setCellValueFactory(new PropertyValueFactory<>("surveyDate"));
        loadSurveySchedule();
    }

    @FXML
    private void loadSurveySchedule() {
        surveyScheduleTableView.getItems().clear();
        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.trim().isEmpty()) continue;

                String[] tokens = line.split(",");
                if (tokens.length != 4) {
                    System.out.println("Skipping malformed line: " + line);
                    continue;
                }

                SurveySchedule schedule = new SurveySchedule(tokens[0].trim(), tokens[1].trim(), tokens[2].trim(), tokens[3].trim());
                surveyScheduleTableView.getItems().add(schedule);
            }
        } catch (Exception e) {
            showAlert(AlertType.ERROR, "Error", "Failed to load survey schedules.");
            e.printStackTrace();
        }
    }

    @FXML
    public void makeSurveyDoneOnClick(ActionEvent event) {
        SurveySchedule selectedSchedule = surveyScheduleTableView.getSelectionModel().getSelectedItem();
        if (selectedSchedule == null) {
            showAlert(AlertType.WARNING, "Validation Error", "No survey schedule selected.");
            return;
        }

        Alert confirmationAlert = new Alert(AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Confirmation");
        confirmationAlert.setContentText("Mark selected survey as done?");

        confirmationAlert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                saveCompletedSurvey(selectedSchedule);
            } else {
                showAlert(AlertType.INFORMATION, "Cancelled", "Survey was not marked as done.");
            }
        });
    }

    private void saveCompletedSurvey(SurveySchedule schedule) {
        try (FileWriter fw = new FileWriter(completedFilePath, true)) {
            fw.write(schedule.toFileString() + "\n");
            showAlert(AlertType.INFORMATION, "Success", "Survey marked as done and saved successfully.");
        } catch (IOException e) {
            showAlert(AlertType.ERROR, "Error", "Failed to save completed survey.");
            e.printStackTrace();
        }
    }

    @FXML
    public void BackDashBoardOnAction(ActionEvent actionEvent)throws IOException {
        SceneSwitcher.switchScene("Orpa2DashBoard.fxml",actionEvent, "DashBoard");

    }

    private void showAlert(AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
