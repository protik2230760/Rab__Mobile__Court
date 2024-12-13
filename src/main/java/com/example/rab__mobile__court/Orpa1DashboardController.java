package com.example.rab__mobile__court;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class Orpa1DashboardController
{
    @javafx.fxml.FXML
    private BorderPane dashboardBP;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void assignSurveyMissionOnClick(ActionEvent actionEvent)throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("initialSurvey.fxml"));
        Node node = fxmlLoader.load() ;
        dashboardBP.setCenter(node);
    }

    @javafx.fxml.FXML
    public void viewSurveyReportOnClick(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void signOutOA(ActionEvent actionEvent) throws IOException {
        SceneSwitcher.switchScene("Login.fxml",actionEvent, "DashBoard");
    }

    @Deprecated
    public void EmergencySupportOA(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void generateCaseReportOnClick(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void ApproveSurveyRequestOnClick(ActionEvent actionEvent)throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("approve_Survey_request.fxml"));
        Node node = fxmlLoader.load() ;
        dashboardBP.setCenter(node);
    }

    @Deprecated
    public void scheduleMeetingOnClick(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void makeChartOfCaseOnClick(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void viewSurveyChartOnAction(ActionEvent actionEvent) {
    }

    @Deprecated
    public void makeScheeduleOnAction(ActionEvent actionEvent) {
    }

    @Deprecated
    public void emergencyAlertOnAction(ActionEvent actionEvent) {
    }

    @Deprecated
    public void InitialSurveyReportingOnAction(ActionEvent actionEvent) {
    }

    @Deprecated
    public void submitSurveyReportOnAction(ActionEvent actionEvent) {
    }

    @Deprecated
    public void checkScheduleOnAction(ActionEvent actionEvent) {
    }

    @Deprecated
    public void makeSurveyChartOnAction(ActionEvent actionEvent) {
    }

    @Deprecated
    public void requestForInitialSurveyOnAction(ActionEvent actionEvent) {
    }

    @Deprecated
    public void previousSurveyListOnAction(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void scheduleMeeting1OnClick(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void EmergencySupport1OA(ActionEvent actionEvent) {
    }
}