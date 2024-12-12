package com.example.rab__mobile__court;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.*;
import java.time.LocalDate;
import java.util.Observable;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class IncidRepSceneController {

    @FXML
    private TextField instiIdTextField, instiNameTextField, inciTimeTextField, crimeDetTextField, numOfDefTextField;
    @FXML
    private DatePicker operationDatePicker;
    @FXML
    private TextArea repInciResultTextArea;

    private final String filePath = "IncidentReports.bin";

    @FXML
    public void saveResultButtonOnClick(ActionEvent event) {
        if (!validateInputs()) return;

        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to save this report?", ButtonType.OK, ButtonType.CANCEL);
        confirmation.showAndWait();

        if (confirmation.getResult() == ButtonType.OK) {
            try {
                IncidentReport report = new IncidentReport(
                        instiIdTextField.getText(),
                        instiNameTextField.getText(),
                        operationDatePicker.getValue(),
                        inciTimeTextField.getText(),
                        Integer.parseInt(numOfDefTextField.getText()),
                        crimeDetTextField.getText()
                );

                writeReportToFile(report);
                showInfoAlert("Success", "The data has been saved.");
            } catch (IOException ex) {
                showInfoAlert("Error", "An error occurred while saving the data.");
            }
        } else {
            showInfoAlert("Cancelled", "The data was not saved.");
        }
    }

    @FXML
    public void showResultButtonOnClick(ActionEvent event) {
        repInciResultTextArea.clear();
        ObservableList<IncidentReport> reports = readReportsFromFile();

        if (reports.isEmpty()) {
            repInciResultTextArea.setText("No incident reports found in the system.");
            return;
        }

        for (IncidentReport report : reports) {
            String formattedReport = String.format(
                    "Incident Report : Institution ID= %s, Time of Incident= %s, Institution Name= %s, Number of Defendants= %d, Date of Operation= %s, Crime Detail= %s",
                    report.getInstitutionId(),
                    report.getTimeOfIncident(),
                    report.getInstitutionName(),
                    report.getNumberOfDefendants(),
                    report.getOperationDate(),
                    report.getCrimeDetails()
            );
            repInciResultTextArea.appendText(formattedReport + "\n");
        }
    }

    @FXML
    public void returnHomeButtonOnClick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Item.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    private void writeReportToFile(IncidentReport report) throws IOException {
        File f = new File(filePath);
        FileOutputStream fos = new FileOutputStream(f, true);
        ObjectOutputStream oos;

        if (f.exists() && f.length() > 0) {
            oos = new AppendableObjectOutputStream(fos);
        } else {
            oos = new ObjectOutputStream(fos);
        }

        oos.writeObject(report);
        oos.close();
    }

    private ObservableList<IncidentReport> readReportsFromFile() {
        ObservableList<IncidentReport> reports = FXCollections.observableArrayList();
        File f = new File(filePath);

        if (!f.exists()) return reports;

        try (FileInputStream fis = new FileInputStream(f);
             ObjectInputStream ois = new ObjectInputStream(fis)) {

            while (true) {
                reports.add((IncidentReport) ois.readObject());
            }
        } catch (EOFException e) {

        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return reports;
    }

    private boolean validateInputs() {
        if (instiIdTextField.getText().isEmpty() || instiNameTextField.getText().isEmpty() ||
                inciTimeTextField.getText().isEmpty() || crimeDetTextField.getText().isEmpty() ||
                numOfDefTextField.getText().isEmpty() || operationDatePicker.getValue() == null) {
            showAlert(Alert.AlertType.WARNING, "Invalid Input", "All fields must be filled.");
            return false;
        }
        if (operationDatePicker.getValue().isBefore(LocalDate.now())) {
            showAlert(Alert.AlertType.WARNING, "Invalid Input", "Date of operation cannot be in the past.");
            return false;
        }
        try {
            Integer.parseInt(numOfDefTextField.getText());
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.WARNING, "Invalid Input", "Number of defendants must be a valid number.");
            return false;
        }
        return true;
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showInfoAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
