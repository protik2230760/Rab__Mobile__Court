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
    public void generateCaseReport1OnClick(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void viewSurveyReport1OnClick(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ViewSurveyReport.fxml"));
        Node node = fxmlLoader.load() ;
        dashboardBP.setCenter(node);
    }

    @javafx.fxml.FXML
    public void viewSurveyChart1OnAction(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void scheduleMeeting1OnClick(ActionEvent actionEvent)  throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("meeting.fxml"));
        Node node = fxmlLoader.load() ;
        dashboardBP.setCenter(node);
    }

    @javafx.fxml.FXML
    public void ApproveSurveyRequest1OnClick(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("approve_Survey_request.fxml"));
        Node node = fxmlLoader.load() ;
        dashboardBP.setCenter(node);

    }

    @javafx.fxml.FXML
    public void signOut1OA(ActionEvent actionEvent)  throws IOException {
        SceneSwitcher.switchScene("Login.fxml",actionEvent, "DashBoard");

    }

    @javafx.fxml.FXML
    public void assignSurveyMission1OnClick(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("initialSurvey.fxml"));
        Node node = fxmlLoader.load() ;
        dashboardBP.setCenter(node);

    }

    @javafx.fxml.FXML
    public void makeChartOfCase1OnClick(ActionEvent actionEvent) {
    }
}