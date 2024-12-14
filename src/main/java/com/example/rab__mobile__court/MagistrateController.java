package com.example.rab__mobile__court;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class MagistrateController
{
    @javafx.fxml.FXML
    private BorderPane dashboardBP;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void goToIssueSerWarSceneOnClick(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void goToVerPieChartSceneOnClick(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void goToAnnVerCreRepSceneOnClick(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void signOutOA(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void goToAnaCrimActiSceneOnClick(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void goToViewCaseInfoSceneOnClick(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void goToSetCourtSchedSceneOnClick(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("setCourtSchdule.fxml"));
        Node node = fxmlLoader.load() ;
        dashboardBP.setCenter(node);
    }

    @javafx.fxml.FXML
    public void goToContCourtClerkSceneOnClick(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void OverviewOA(ActionEvent actionEvent) {
    }
}