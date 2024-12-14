package com.example.rab__mobile__court;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ViewCaseDetailsFXMLController {

    @FXML
    private TextField caseNumberTextField;

    @FXML
    private TextArea caseDetailsTextArea;

    private final String filePath = "CaseFiles.txt";

    @FXML
    public void searchCaseDetailsOnClick(ActionEvent actionEvent) {
        String caseNumber = caseNumberTextField.getText().trim();

        if (caseNumber.isEmpty()) {
            showAlert("Error", "Case Number must be filled.");
            return;
        }

        List<Case1> cases = readFromFile();
        for (Case1 c : cases) {
            if (c.getCaseId().equals(caseNumber)) {
                caseDetailsTextArea.setText(c.toString());
                return;
            }
        }

        showAlert("Not Found", "No case found with the provided Case Number.");
    }

    @FXML
    public void returnHomeOnClick(ActionEvent actionEvent) throws IOException, IOException {
        SceneSwitcher.switchScene("MagistrateDashboard.fxml", actionEvent, "Dashboard");
    }

    private List<Case1> readFromFile() {
        List<Case1> cases = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] tokens = line.split(",");
                if (tokens.length == 6) {
                    cases.add(new Case1(tokens[0], tokens[1], tokens[2], tokens[3], tokens[4], tokens[5]));
                }
            }
        } catch (Exception e) {
            showAlert("Error", "Error reading from file: " + e.getMessage());
        }
        return cases;
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


}