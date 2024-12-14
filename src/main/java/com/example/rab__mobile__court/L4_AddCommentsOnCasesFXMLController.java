package com.example.rab__mobile__court;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class L4_AddCommentsOnCasesFXMLController {

    @FXML
    private TextField caseNumberTextField;

    @FXML
    private TextField titleTextField;

    @FXML
    private TextArea notesOrCommentsTextArea;

    private final String filePath = "CaseFiles.txt";
    @FXML
    private Label successfullygeneratemessageshowLabel;

    @FXML
    public void saveToFileButtonOnCliuck(ActionEvent actionEvent) {
        String caseNumber = caseNumberTextField.getText().trim();
        String caseTitle = titleTextField.getText().trim();
        String notes = notesOrCommentsTextArea.getText().trim();

        if (caseNumber.isEmpty() || caseTitle.isEmpty() || notes.isEmpty()) {
            showAlert("Error", "All fields must be filled.");
            return;
        }

        List<Case1> cases = readFromFile();
        boolean isUpdated = false;

        for (Case1 c : cases) {
            if (c.getCaseId().equals(caseNumber) && c.toString().contains("Title: " + caseTitle)) {
                c.appendNotes(notes);
                isUpdated = true;
                break;
            }
        }

        if (isUpdated) {
            writeToFile(cases);
            showAlert("Success", "Notes appended to the case successfully.");
        } else {
            showAlert("Error", "No matching case found to update.");
        }
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

    private void writeToFile(List<Case1> cases) {
        try (FileWriter writer = new FileWriter(filePath, false)) {
            for (Case1 c : cases) {
                writer.write(c.toFileString() + "\n");
            }
        } catch (IOException e) {
            showAlert("Error", "Error writing to file: " + e.getMessage());
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


    }


