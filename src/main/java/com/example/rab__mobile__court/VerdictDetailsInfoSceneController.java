package com.example.rab__mobile__court;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.*;

public class VerdictDetailsInfoSceneController {

    @FXML
    private TextField caseIdTextField, verIdTextField;

    @FXML
    private ComboBox<String> verTypeCB;

    @FXML
    private TableView<VerdictDetails> tableView;

    @FXML
    private TableColumn<VerdictDetails, String> caseIdTableCol, verIDTableCol, verTypeTableCol;

    @FXML
    private PieChart pieChart;

    private final ObservableList<VerdictDetails> verdictDetailsList = FXCollections.observableArrayList();
    private final String verdictFilePath = "VerdictDetails.bin";

    @FXML
    public void initialize() {
        verTypeCB.setItems(FXCollections.observableArrayList("Guilty", "Not Guilty", "Settled"));

        caseIdTableCol.setCellValueFactory(new PropertyValueFactory<>("caseId"));
        verIDTableCol.setCellValueFactory(new PropertyValueFactory<>("verdictId"));
        verTypeTableCol.setCellValueFactory(new PropertyValueFactory<>("verdictType"));

        // Do not load into table on initialization
    }

    @FXML
    public void saveVerDetailsButtonOnClick(ActionEvent event) {
        String caseId = caseIdTextField.getText();
        String verdictId = verIdTextField.getText();
        String verdictType = verTypeCB.getValue();

        if (caseId.isEmpty() || verdictId.isEmpty() || verdictType == null) {
            showAlert("Error", "All fields must be filled!");
            return;
        }

        // Ensure unique case ID and verdict ID
        for (VerdictDetails verdict : verdictDetailsList) {
            if (verdict.getCaseId().equals(caseId)) {
                showAlert("Error", "Case ID must be unique!");
                return;
            }
            if (verdict.getVerdictId().equals(verdictId)) {
                showAlert("Error", "Verdict ID must be unique!");
                return;
            }
        }

        VerdictDetails verdictDetails = new VerdictDetails(caseId, verdictId, verdictType);
        verdictDetailsList.add(verdictDetails);
        saveVerdictDetailsToFile();
        showAlert("Success", "Verdict details saved successfully!");
    }

    @FXML
    public void viewInTableButtonOnClick(ActionEvent event) {
        verdictDetailsList.clear();
        loadVerdictDetailsFromFile();
        tableView.setItems(verdictDetailsList);
    }

    @FXML
    public void viewInPieChartButtonOnClick(ActionEvent event) {
        int guiltyCount = 0;
        int notGuiltyCount = 0;
        int settledCount = 0;

        for (VerdictDetails verdict : verdictDetailsList) {
            switch (verdict.getVerdictType()) {
                case "Guilty":
                    guiltyCount++;
                    break;
                case "Not Guilty":
                    notGuiltyCount++;
                    break;
                case "Settled":
                    settledCount++;
                    break;
            }
        }

        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        if (guiltyCount > 0) {
            pieChartData.add(new PieChart.Data("Guilty", guiltyCount));
        }
        if (notGuiltyCount > 0) {
            pieChartData.add(new PieChart.Data("Not Guilty", notGuiltyCount));
        }
        if (settledCount > 0) {
            pieChartData.add(new PieChart.Data("Settled", settledCount));
        }

        pieChart.setData(pieChartData);

        for (PieChart.Data data : pieChart.getData()) {
            data.getNode().addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, event1 -> {
                Alert dataAlert = new Alert(Alert.AlertType.INFORMATION);
                dataAlert.setContentText("Verdict Type: " + data.getName() + "\nCount: " + (int) data.getPieValue());
                dataAlert.showAndWait();
            });
        }
    }

    private void saveVerdictDetailsToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(verdictFilePath))) {
            for (VerdictDetails verdict : verdictDetailsList) {
                oos.writeObject(verdict);
            }
        } catch (IOException e) {
            showAlert("Error", "Error occurred while saving verdict details to file.");
        }
    }

    private void loadVerdictDetailsFromFile() {
        File file = new File(verdictFilePath);
        if (!file.exists()) {
            return;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            while (true) {
                VerdictDetails verdict = (VerdictDetails) ois.readObject();
                verdictDetailsList.add(verdict);
            }
        } catch (EOFException ignored) {
            // End of file reached
        } catch (IOException | ClassNotFoundException e) {
            showAlert("Error", "Error occurred while loading verdict details from file.");
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
    public void returnHomeButtonOnClick(ActionEvent actionEvent) throws IOException {
        SceneSwitcher.switchScene("MagistrateDashboard.fxml", actionEvent, "DashBoard");
    }
}
