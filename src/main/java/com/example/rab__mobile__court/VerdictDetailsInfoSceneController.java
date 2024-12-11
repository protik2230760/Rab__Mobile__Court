package com.example.rab__mobile__court;

import javafx.event.ActionEvent;
import javafx.scene.chart.PieChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class VerdictDetailsInfoSceneController
{
    @javafx.fxml.FXML
    private ComboBox verTypeCB;
    @javafx.fxml.FXML
    private TextField caseIdTextField;
    @javafx.fxml.FXML
    private TableColumn caseIdTableCol;
    @javafx.fxml.FXML
    private TableColumn verTypeTableCol;
    @javafx.fxml.FXML
    private TableView tableView;
    @javafx.fxml.FXML
    private TextField verIdTextField;
    @javafx.fxml.FXML
    private TableColumn verIDTableCol;
    @javafx.fxml.FXML
    private PieChart pieChart;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void saveVerDetailsButtonOnClick(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void returnHomeButtonOnClick(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void viewInTableButtonOnClick(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void viewInPieChartButtonOnClick(ActionEvent actionEvent) {
    }
}