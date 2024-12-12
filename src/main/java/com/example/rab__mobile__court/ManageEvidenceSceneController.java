package com.example.rab__mobile__court;

import javafx.event.ActionEvent;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.time.LocalDate;

public class ManageEvidenceSceneController
{
    @javafx.fxml.FXML
    private TableColumn <Evidence, String> eviTypeCol;
    @javafx.fxml.FXML
    private TextField caseIdTextField;
    @javafx.fxml.FXML
    private TextField eviIdTextField;
    @javafx.fxml.FXML
    private TableColumn<Evidence, String> eviNameTableCol;
    @javafx.fxml.FXML
    private TableColumn <Evidence, LocalDate>dateCol;
    @javafx.fxml.FXML
    private TableView<Evidence> eviTable;
    @javafx.fxml.FXML
    private PieChart eviPieChart;
    @javafx.fxml.FXML
    private TableColumn<Evidence, Integer> caseIdCol;
    @javafx.fxml.FXML
    private DatePicker dateOfAcqDP;
    @javafx.fxml.FXML
    private TextField eviNameTextField;
    @javafx.fxml.FXML
    private ComboBox <String> eviTypeCB;
    @javafx.fxml.FXML
    private TableColumn<Evidence, Integer> eviIdTableCol;

    @javafx.fxml.FXML
    public void initialize()
    {eviTypeCB.getItems().addAll("Testimonial", "Eyewitness", "Solid", "Picture", "Video Footage");

    eviIdTableCol.setCellValueFactory(new PropertyValueFactory<Evidence, Integer>("evidenceId"));
    eviNameTableCol.setCellValueFactory(new PropertyValueFactory<Evidence, String>("eviName"));
    eviTypeCol.setCellValueFactory(new PropertyValueFactory<Evidence, String>("evidenceType"));
    dateCol.setCellValueFactory(new PropertyValueFactory<Evidence, LocalDate>("dateOfAcquire"));
    caseIdCol.setCellValueFactory(new PropertyValueFactory<Evidence, Integer>("caseId"));

    }

    @javafx.fxml.FXML
    public void loadPieChartButtonOnClick(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void returnHomeButtonOnClick(ActionEvent actionEvent) throws IOException {
        SceneSwitcher.switchScene("Item.fxml", actionEvent);
    }

    @javafx.fxml.FXML
    public void loadTableButtonOnClick(ActionEvent actionEvent) {
        Evidence newEvidence = new Evidence(
                Integer.parseInt(eviIdTextField.getText()),
                eviNameTextField.getText(),
                eviTypeCB.getValue(),
                dateOfAcqDP.getValue(),
                Integer.parseInt(caseIdTextField.getText())
        );
        eviTable.getItems().add(newEvidence);

    }
}