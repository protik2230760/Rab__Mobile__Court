package com.example.rab__mobile__court;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class DO2_CaseDetailsFXMLController {

    @FXML
    private TextField caseNumberTextField;

    @FXML
    private TextArea caseDetailsTextArea;

    private final String caseFilePath = "CaseFiles.txt";

    @FXML
    public void searchCaseDetailsOnClick(ActionEvent event) {
        String caseId = caseNumberTextField.getText().trim();

        if (caseId.isEmpty()) {
            showAlert("Error", "Case ID cannot be empty.");
            return;
        }

        File file = new File(caseFilePath);
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Case c = Case.fromFileString(line);
                if (c.getCaseId().equals(caseId)) {
                    caseDetailsTextArea.setText("Case ID: " + c.getCaseId() + "\n" +
                            "Case Title: " + c.getCaseTitle() + "\n" +
                            "Officer Badge Number: " + c.getOfficerBadgeNumber() + "\n" +
                            "Evidence List: " + c.getEvidenceList() + "\n" +
                            "Eye Witness Info: " + c.getEyeWitnessInfo() + "\n" +
                            "Defendant Info: " + c.getDefendantInfo());
                    return;
                }
            }
            showAlert("Error", "Case ID not found.");
        } catch (Exception e) {
            showAlert("Error", "Failed to read the case file.");
        }
    }

    @FXML
    public void saveToFileOnClick(ActionEvent event) {
        String caseDetails = caseDetailsTextArea.getText().trim();

        if (caseDetails.isEmpty()) {
            showAlert("Error", "Case details cannot be empty.");
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Do you want to save the case details?");

        if (alert.showAndWait().get() == ButtonType.OK) {
            writeToFile(caseDetails);
            showAlert("Success", "Case details saved successfully.");
        } else {
            showAlert("Information", "Case details were not saved.");
        }
    }

    private void writeToFile(String details) {
        FileWriter fw = null;
        try {
            File file = new File("SavedCaseDetails.txt");
            fw = new FileWriter(file, true);
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

    @FXML
    public void logOutButtonOnClick(ActionEvent actionEvent) throws Exception {
        SceneSwitcher.switchScene("fahmidadasboard1.fxml", actionEvent, "Dashboard");
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
