package com.example.rab__mobile__court;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Optional;

public class DO5_AssignLawyerFXMLController {

    @FXML
    private TextField caseNumberTextField;
    @FXML
    private ComboBox<String> lawyerNameComboBox;
    @FXML
    private TextField caseDetailsTextField;
    @FXML
    private TableView<CaseAssignment> caseAndLaywerNameTableView;
    @FXML
    private TableColumn<CaseAssignment, String> caseNumColumn;
    @FXML
    private TableColumn<CaseAssignment, String> lawyerNameColumn;
    @FXML
    private TableColumn<CaseAssignment, String> caseDetailsColumn;

    private final ObservableList<CaseAssignment> caseList = FXCollections.observableArrayList();
    @FXML
    private Label showerrormessageLabel;

    @FXML
    public void initialize() {

        caseNumColumn.setCellValueFactory(new PropertyValueFactory<>("caseNumber"));
        lawyerNameColumn.setCellValueFactory(new PropertyValueFactory<>("lawyerName"));
        caseDetailsColumn.setCellValueFactory(new PropertyValueFactory<>("caseDetails"));


        lawyerNameComboBox.getItems().addAll("Lawyer A", "Lawyer B", "Lawyer C");
    }

    @FXML
    public void loadInTheTableButtonOnClick(ActionEvent event) {
        String caseNumber = caseNumberTextField.getText().trim();
        String lawyerName = lawyerNameComboBox.getValue();
        String caseDetails = caseDetailsTextField.getText().trim();


        if (caseNumber.isEmpty() || lawyerName == null || caseDetails.isEmpty()) {
            showErrorMessage("All fields must be filled.");
            return;
        }

        if (caseList.stream().anyMatch(caseItem -> caseItem.getCaseNumber().equals(caseNumber))) {
            showErrorMessage("Case number must be unique.");
            return;
        }


        CaseAssignment newCase = new CaseAssignment(caseNumber, lawyerName, caseDetails);
        caseList.add(newCase);
        caseAndLaywerNameTableView.setItems(caseList);


        caseNumberTextField.clear();
        lawyerNameComboBox.setValue(null);
        caseDetailsTextField.clear();

        showErrorMessage("");
    }

    @FXML
    public void savetoFileOnClick(ActionEvent event) {
        if (caseList.isEmpty()) {
            showErrorMessage("No data to save.");
            return;
        }


        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Save Confirmation");
        confirmationAlert.setHeaderText(null);
        confirmationAlert.setContentText("Do you want to save the data to a file?");
        Optional<ButtonType> result = confirmationAlert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            try (FileWriter fw = new FileWriter(new File("CaseAssignments.txt"), true)) {
                for (CaseAssignment caseAssignment : caseList) {
                    fw.write(caseAssignment.toString() + "\n");
                }
                showErrorMessage("Data saved successfully.");
            } catch (Exception e) {
                showErrorMessage("Failed to save data.");
            }
        } else {
            showErrorMessage("Save operation cancelled.");
        }
    }
    private void showErrorMessage(String message) {
        showerrormessageLabel.setText(message);
    }


}
