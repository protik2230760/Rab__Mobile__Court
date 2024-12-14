package com.example.rab__mobile__court;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.*;
import java.time.LocalDate;
import java.util.Scanner;

public class Initial_Survey_ReportController {

    @FXML
    private TextField InitialSurveyIdTextField;

    @FXML
    private TextField institutionNameTextField;

    @FXML
    private TextField InsttitutionLocationTextField;

    @FXML
    private DatePicker surveydatePicker;

    @FXML
    private TextArea SuspeciousActvitiesTextArea;

    @FXML
    private TextArea showReportTextArea;

    private ObservableList<SurveyReport> surveyReports = FXCollections.observableArrayList();
    private final String filePath = "InitialSurveyReports.txt";

    @FXML
    public void saveToFileButtonOnClick(ActionEvent actionEvent) {
        String surveyId = InitialSurveyIdTextField.getText().trim();
        String institutionName = institutionNameTextField.getText().trim();
        String institutionLocation = InsttitutionLocationTextField.getText().trim();
        LocalDate surveyDate = surveydatePicker.getValue();
        String suspiciousActivities = SuspeciousActvitiesTextArea.getText().trim();

        if (surveyId.isEmpty() || institutionName.isEmpty() || institutionLocation.isEmpty() || surveyDate == null || suspiciousActivities.isEmpty()) {
            showAlert("Error", "All fields must be filled.");
            return;
        }

        if (surveyDate.isBefore(LocalDate.now())) {
            showAlert("Error", "Survey date cannot be in the past.");
            return;
        }

        for (SurveyReport report : surveyReports) {
            if (report.getSurveyId().equals(surveyId)) {
                showAlert("Error", "Survey ID must be unique.");
                return;
            }
        }

        SurveyReport newReport = new SurveyReport(surveyId, institutionName, institutionLocation, surveyDate, suspiciousActivities);

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Do you want to save this report?");

        if (alert.showAndWait().get() == ButtonType.OK) {
            surveyReports.add(newReport);
            writeToFile(newReport);
            showAlert("Success", "Report saved successfully.");
        } else {
            showAlert("Information", "Report not saved.");
        }
    }

    private void writeToFile(SurveyReport report) {
        FileWriter fw = null;
        try {
            File f = new File(filePath);
            if (f.exists()) {
                fw = new FileWriter(f, true);
            } else {
                fw = new FileWriter(f);
            }
            fw.write(report.toFileString() + "\n");
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
    public void loadreportOnclick(ActionEvent actionEvent) {
        StringBuilder reportContent = new StringBuilder();
        File f = new File(filePath);
        Scanner scanner = null;
        try {
            if (!f.exists()) {
                showAlert("Error", "No reports found.");
                return;
            }
            scanner = new Scanner(f);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                SurveyReport report = SurveyReport.fromFileString(line);
                reportContent.append(report.toFormattedString()).append("\n\n");
            }
            showReportTextArea.setText(reportContent.toString());
        } catch (Exception e) {
            showAlert("Error", "Failed to load reports.");
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
    public void backDashBoardOnAction(ActionEvent actionEvent) throws IOException {
        SceneSwitcher.switchScene("Orpa2DashBoard.fxml", actionEvent, "DashBoard");
    }
}
