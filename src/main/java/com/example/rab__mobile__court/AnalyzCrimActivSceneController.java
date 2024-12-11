package com.example.rab__mobile__court;

import javafx.event.ActionEvent;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class AnalyzCrimActivSceneController
{
    @javafx.fxml.FXML
    private NumberAxis yAxis;
    @javafx.fxml.FXML
    private TextField occYearTextField;
    @javafx.fxml.FXML
    private CategoryAxis xAxis;
    @javafx.fxml.FXML
    private Label outputLabel;
    @javafx.fxml.FXML
    private TextField occAmountTextField;
    @javafx.fxml.FXML
    private TextField inYrOfCriTextField;
    @javafx.fxml.FXML
    private ComboBox crimeTypeCB;
    @javafx.fxml.FXML
    private BarChart anaCriActBarChart;
    @javafx.fxml.FXML
    private TextArea viewSavedInfoTextArea;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void viewMaxMinActButtonOnClick(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void saveActInfoButtonOnClick(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void loadBarChartButtonOnClick(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void returnHomeButtonOnClick(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void viewSavedInfoButtonOnClick(ActionEvent actionEvent) {
    }
}