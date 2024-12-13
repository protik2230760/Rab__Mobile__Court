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
import java.util.Scanner;

public class Make_Survey_ScheduleController {

    @FXML
    private TextField surveyIdTextField, InstitutionNameTextField, institutionLocationTextField;
    @FXML
    private DatePicker surveyDatePicker;

    private final List<SurveySchedule> surveyList = new ArrayList<>();
    private final String filePath = "SurveySchedule.txt";

    @FXML
    public void saveSurveyScheduleOnClick(ActionEvent event) {
        if (!validateInputs()) return;


        SurveySchedule survey = new SurveySchedule(
                surveyIdTextField.getText(),
                InstitutionNameTextField.getText(),
                institutionLocationTextField.getText(),
                surveyDatePicker.getValue().toString()
        );


        surveyList.add(survey);


        FileWriter fw = null;
        try {
            File file = new File(filePath);
            fw = new FileWriter(file, true);

            fw.write(survey.toFileString() + "\n");
            showInfoAlert("Success", "Survey Schedule saved successfully.");
        } catch (Exception e) {
            showInfoAlert("Error", "Failed to save survey schedule.");
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
    public void backToDashBoardOnAction(ActionEvent actionEvent) throws IOException {
        SceneSwitcher.switchScene("Orpa2DashBoard.fxml",actionEvent, "DashBoard");
    }

    private boolean validateInputs() {
        if (surveyIdTextField.getText().isEmpty() || InstitutionNameTextField.getText().isEmpty()
                || institutionLocationTextField.getText().isEmpty() || surveyDatePicker.getValue() == null) {
            showAlert(AlertType.WARNING, "Validation Error", "All fields must be filled.");
            return false;
        }

        if (surveyDatePicker.getValue().isBefore(LocalDate.now())) {
            showAlert(AlertType.WARNING, "Validation Error", "Survey Date cannot be in the past.");
            return false;
        }

        if (!isSurveyIdUnique(surveyIdTextField.getText())) {
            showAlert(AlertType.WARNING, "Validation Error", "Survey ID must be unique.");
            return false;
        }

        return true;
    }

    private boolean isSurveyIdUnique(String surveyId) {
        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNextLine()) {
                String[] tokens = scanner.nextLine().split(",");
                if (tokens[0].equals(surveyId)) return false;
            }
        } catch (Exception ignored) {
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
}
