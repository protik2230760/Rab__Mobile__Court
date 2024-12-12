package com.example.rab__mobile__court;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class Orpa2DashboardController
{
    @javafx.fxml.FXML
    private BorderPane dashboardBP;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void signOutOA(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void makeScheeduleOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Overview.fxml"));
        Node node = fxmlLoader.load() ;
        dashboardBP.setCenter(node);
    }

    @javafx.fxml.FXML
    public void emergencyAlertOnAction(ActionEvent actionEvent)throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SendEmergencyAlert.fxml"));
        Node node = fxmlLoader.load() ;
        dashboardBP.setCenter(node);
    }

    @javafx.fxml.FXML
    public void InitialSurveyReportingOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Overview.fxml"));
        Node node = fxmlLoader.load() ;
        dashboardBP.setCenter(node);
    }

    @javafx.fxml.FXML
    public void submitSurveyReportOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Overview.fxml"));
        Node node = fxmlLoader.load() ;
        dashboardBP.setCenter(node);
    }

    @javafx.fxml.FXML
    public void checkScheduleOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Overview.fxml"));
        Node node = fxmlLoader.load() ;
        dashboardBP.setCenter(node);
    }

    @javafx.fxml.FXML
    public void makeSurveyChartOnAction(ActionEvent actionEvent)throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Overview.fxml"));
        Node node = fxmlLoader.load() ;
        dashboardBP.setCenter(node);
    }

    @javafx.fxml.FXML
    public void requestForInitialSurveyOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("requestSurvey.fxml"));
        Node node = fxmlLoader.load() ;
        dashboardBP.setCenter(node);
    }

    @javafx.fxml.FXML
    public void previousSurveyListOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PreviouslySurveyedInstitutionList.fxml"));
        Node node = fxmlLoader.load() ;
        dashboardBP.setCenter(node);
    }
}