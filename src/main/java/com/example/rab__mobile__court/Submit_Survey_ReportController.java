package com.example.rab__mobile__court;

import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Submit_Survey_ReportController
{
    @javafx.fxml.FXML
    private TextArea reportDetailsTextArea;
    @javafx.fxml.FXML
    private TextField surveyIdTextField;
    @javafx.fxml.FXML
    private TextField institutionNameTextField;
    @javafx.fxml.FXML
    private TextArea displaySurveyReportTextArea;
    @javafx.fxml.FXML
    private ComboBox monthComboBox;
    @javafx.fxml.FXML
    private DatePicker surveyReportDatePicker;
    @javafx.fxml.FXML
    private ComboBox yearComboBox1;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void saveReportOnAction(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void bacKToDashBoardOnAction(ActionEvent actionEvent) {
    }
}