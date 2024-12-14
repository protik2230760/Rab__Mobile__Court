package com.example.rab__mobile__court;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class SetCourtScheduleSceneController {

    @FXML
    private TableView<Case> loadCasetableView;

    @FXML
    private TableColumn<Case, String> caseIDtableColumn, caseTitleTableColumn, definfotableColumn;

    @FXML
    private TextField caseIdTextField, caseTitleTextField, defIdTextField;

    @FXML
    private DatePicker nextHearDatePicker;

    @FXML
    private ComboBox<String> propCourtRoomCB;

    @FXML
    private TextArea viewHearDetTextArea;

    private final ObservableList<Case> caseList = FXCollections.observableArrayList();
    private final String caseFilePath = "CaseFiles.txt";
    private final String hearingFilePath = "HearingDetails.txt";

    @FXML
    public void initialize() {
        caseIDtableColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getCaseId()));
        caseTitleTableColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getCaseTitle()));
        definfotableColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getDefendantInfo()));

        propCourtRoomCB.setItems(FXCollections.observableArrayList("Room 1", "Room 2", "Room 3"));
    }

    @FXML
    public void loadCasesButtonOnClick(ActionEvent event) {
        loadCasetableView.getItems().clear();
        File f = new File(caseFilePath);
        try (Scanner scanner = new Scanner(f)) {
            while (scanner.hasNextLine()) {
                Case c = Case.fromFileString(scanner.nextLine());
                loadCasetableView.getItems().add(c);
            }
        } catch (Exception e) {
            showAlert("Error", "Failed to load cases from file.");
        }
    }

    @FXML
    public void saveHearDetButtonOnClick(ActionEvent event) {
        String caseId = caseIdTextField.getText().trim();
        String caseTitle = caseTitleTextField.getText().trim();
        String defendantInfo = defIdTextField.getText().trim();
        String nextHearingDate = nextHearDatePicker.getValue() != null ? nextHearDatePicker.getValue().toString() : "";
        String proposedCourtRoom = propCourtRoomCB.getValue();

        if (caseId.isEmpty() || caseTitle.isEmpty() || defendantInfo.isEmpty() || nextHearingDate.isEmpty() || proposedCourtRoom == null) {
            showAlert("Error", "All fields must be filled.");
            return;
        }

        String hearingDetails = "Case ID: " + caseId + ", Case Title: " + caseTitle + ", Defendant Info: " + defendantInfo
                + ", Next Hearing Date: " + nextHearingDate + ", Proposed Court Room: " + proposedCourtRoom;

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Do you want to save the hearing details?");

        if (alert.showAndWait().get() == ButtonType.OK) {
            writeHearingToFile(hearingDetails);
            viewHearDetTextArea.setText(hearingDetails);
            showAlert("Success", "Hearing details saved successfully.");
        } else {
            showAlert("Information", "Hearing details were not saved.");
        }
    }

    private void writeHearingToFile(String details) {
        FileWriter fw = null;
        try {
            File f = new File(hearingFilePath);
            fw = new FileWriter(f, true);
            fw.write(details + "\n");
        } catch (Exception e) {
            showAlert("Error", "Error occurred while writing to file.");
        } finally {
            try {
                if (fw != null) fw.close();
            } catch (Exception e) {
                showAlert("Error", "Error occurred while closing the file writer.");
            }
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    public void returnHomeButtonOnClick(ActionEvent actionEvent) {
    }
}
