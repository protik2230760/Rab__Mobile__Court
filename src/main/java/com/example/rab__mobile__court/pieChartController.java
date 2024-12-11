package com.example.rab__mobile__court;

import javafx.event.ActionEvent;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class pieChartController
{
    @javafx.fxml.FXML
    private ComboBox yearComboBox;
    @javafx.fxml.FXML
    private BarChart barChart;
    @javafx.fxml.FXML
    private TextField CetegoryTextField;
    @javafx.fxml.FXML
    private ComboBox monthComboBox;
    @javafx.fxml.FXML
    private TextField valueTextField;
    @javafx.fxml.FXML
    private PieChart surveyPieChart;
    @javafx.fxml.FXML
    private TextArea showCurrentDataTextArea;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void addChartDataOnClick(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void backToDashBoardOnClick(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void showChartOnClick(ActionEvent actionEvent) {
    }
}