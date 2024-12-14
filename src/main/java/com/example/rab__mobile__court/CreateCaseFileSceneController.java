package com.example.rab__mobile__court;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CreateCaseFileSceneController {

    @FXML
    private TextField caseIdTextField, caseTitleTextField, offiBadgeNumTextField, eviListTextField, eyeWitInfoTextField, defInfoTextField;

    @FXML
    private TextArea viewDraftTextArea;

    private final ObservableList<Case> caseList = FXCollections.observableArrayList();
    private final String filePath = "CaseFiles.txt";

    @FXML
    public void viewDraftButtonOnClick(ActionEvent event) {
        String caseId = caseIdTextField.getText().trim();
        String caseTitle = caseTitleTextField.getText().trim();
        String officerBadge = offiBadgeNumTextField.getText().trim();
        String evidenceList = eviListTextField.getText().trim();
        String eyeWitnessInfo = eyeWitInfoTextField.getText().trim();
        String defendantInfo = defInfoTextField.getText().trim();

        if (caseId.isEmpty() || caseTitle.isEmpty() || officerBadge.isEmpty() || evidenceList.isEmpty() || eyeWitnessInfo.isEmpty() || defendantInfo.isEmpty()) {
            showAlert("Error", "All fields must be filled.");
            return;
        }

        try {
            Integer.parseInt(caseId);
        } catch (NumberFormatException e) {
            showAlert("Error", "Case ID must be an integer.");
            return;
        }

        for (Case existingCase : caseList) {
            if (existingCase.getCaseId().equals(caseId)) {
                showAlert("Error", "Case ID must be unique.");
                return;
            }
        }

        Case newCase = new Case(caseId, caseTitle, officerBadge, evidenceList, eyeWitnessInfo, defendantInfo);
        caseList.add(newCase);
        viewDraftTextArea.setText(newCase.toString());
    }

    @FXML
    public void saveCaseFileButtonOnClick(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Do you want to save this case file?");

        if (alert.showAndWait().get() == ButtonType.OK) {
            writeToFile();
            showAlert("Success", "Case file saved successfully.");
        } else {
            showAlert("Information", "Case file was not saved.");
        }
    }

    private void writeToFile() {
        FileWriter fw = null;
        try {
            File f = new File(filePath);
            fw = new FileWriter(f, true);
            for (Case c : caseList) {
                fw.write(c.toFileString() + "\n");
            }
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
    public void returnHomeButtonOnClick(ActionEvent actionEvent)throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Item.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
    }
}
