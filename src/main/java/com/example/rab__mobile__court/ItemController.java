package com.example.rab__mobile__court;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class ItemController
{

    @javafx.fxml.FXML
    private BorderPane dashboardBP;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void signOutOA(ActionEvent actionEvent)throws IOException {
        SceneSwitcher.switchScene("Login.fxml",actionEvent, "DashBoard");
    }

    @Deprecated
    public void EmergencySupportOA(ActionEvent actionEvent)throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("emergencySupport.fxml"));
        Node node = fxmlLoader.load() ;
        dashboardBP.setCenter(node);
    }

    @Deprecated
    public void additionalCaseInformationOA(ActionEvent actionEvent) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("additionallyCaseInformation.fxml"));
        Node node = fxmlLoader.load() ;
        dashboardBP.setCenter(node);
    }

    @Deprecated
    public void RecordDefendantDetailsOA(ActionEvent actionEvent) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("defendentInfo.fxml"));
        Node node = fxmlLoader.load() ;
        dashboardBP.setCenter(node);
    }

    @Deprecated
    public void CreateIncidentReportOA(ActionEvent actionEvent) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("incidentReport.fxml"));
        Node node = fxmlLoader.load() ;
        dashboardBP.setCenter(node);

    }

    @Deprecated
    public void requestSearchWarrantOA(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("requestSearchWarrent.fxml"));
        Node node = fxmlLoader.load() ;
        dashboardBP.setCenter(node);
    }

    @javafx.fxml.FXML
    public void OverviewOA(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Overview.fxml"));
        Node node = fxmlLoader.load() ;
        dashboardBP.setCenter(node);
    }

    @Deprecated
    public void manageEvidanceOA(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("manageEvidance.fxml"));
        Node node = fxmlLoader.load() ;
        dashboardBP.setCenter(node);


    }

    @Deprecated
    public void caseFileOA(ActionEvent actionEvent)throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("createCaseFile.fxml"));
        Node node = fxmlLoader.load() ;
        dashboardBP.setCenter(node);
    }
}