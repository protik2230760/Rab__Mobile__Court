package com.example.rab__mobile__court;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

import java.io.File;
import java.util.Scanner;

public class L1_ViewAssignedCasesFXMLController {

    @FXML
    private TextArea viewAssignedCaseTextArea;

    @FXML
    public void assignedCaseFileLoadButtonOnClick(ActionEvent event) {
        File file = new File("CaseAssignments.txt");
        if (!file.exists()) {
            viewAssignedCaseTextArea.setText("No file found.");
            return;
        }

        try (Scanner scanner = new Scanner(file)) {
            StringBuilder fileContent = new StringBuilder();
            while (scanner.hasNextLine()) {
                fileContent.append(scanner.nextLine()).append("\n");
            }
            viewAssignedCaseTextArea.setText(fileContent.toString());
        } catch (Exception e) {
            viewAssignedCaseTextArea.setText("Error loading file.");
        }
    }

    @FXML
    public void returnHomeButtonOnclick(ActionEvent event) throws Exception {
        SceneSwitcher.switchScene("fahmidaDashboard2.fxml", event, "Dashboard");
    }
}
