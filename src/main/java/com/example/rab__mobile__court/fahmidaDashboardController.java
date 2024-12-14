package com.example.rab__mobile__court;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class fahmidaDashboardController
{
    @javafx.fxml.FXML
    private BorderPane dashboardBP;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void availableLawyerButtonOnClick(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void viewCrimeRatesButtonOnClick(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void signOutOA(ActionEvent actionEvent) throws IOException {
        SceneSwitcher.switchScene("Login.fxml",actionEvent, "DashBoard");
    }

    @javafx.fxml.FXML
    public void caseDetailsButtonOnClick(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DO2_CaseDetails.fxml"));
        Node node = fxmlLoader.load() ;
        dashboardBP.setCenter(node);
    }

    @javafx.fxml.FXML
    public void assignNewOperationButtonOnClick(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DO4_AssignNewOperations.fxml"));
        Node node = fxmlLoader.load() ;
        dashboardBP.setCenter(node);
    }

    @javafx.fxml.FXML
    public void assignLawyerButtonOnClick(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DO5_AssignLawyer.fxml"));
        Node node = fxmlLoader.load() ;
        dashboardBP.setCenter(node);
    }

    @javafx.fxml.FXML
    public void createReportButtonOnClick(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void BudgetAllocationButtonOnClick(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DO3_BudgetAllocation.Fxml"));
        Node node = fxmlLoader.load() ;
        dashboardBP.setCenter(node);
    }

    @javafx.fxml.FXML
    public void CommandOAdministratorButtonOnClick(ActionEvent actionEvent)throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DO1_CommandOperationsAdministrator.Fxml"));
        Node node = fxmlLoader.load() ;
        dashboardBP.setCenter(node);
    }
}