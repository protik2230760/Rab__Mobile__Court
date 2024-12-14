package com.example.rab__mobile__court;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class L5_UpdateCaseStatusFXMLController {

    @FXML
    private TextField caseNumberTextField, caseStatusCountTextField;

    @FXML
    private RadioButton inProgressRadioButton, pendingRadioButton, closedRadioButton, activeRadioButton;

    @FXML
    private TableView<CaseStatus> caseStatusTableView;

    @FXML
    private TableColumn<CaseStatus, String> caseNumberColumn, caseStatusColumn;

    @FXML
    private ComboBox<String> caseStatusComboBox;

    private final ObservableList<CaseStatus> caseStatusList = FXCollections.observableArrayList();
    private final String caseStatusFilePath = "CaseStatus.bin";
    @FXML
    private ToggleGroup caseStatusTGrp;
    @FXML
    private TextArea outPutTextArea;

    @FXML
    public void initialize() {
        caseNumberColumn.setCellValueFactory(new PropertyValueFactory<>("caseNumber"));
        caseStatusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        caseStatusComboBox.setItems(FXCollections.observableArrayList("In Progress", "Pending", "Closed", "Active"));

        loadCaseStatusFromFile();
    }

    @FXML
    public void loadTableButtonOnClick(ActionEvent event) {
        String caseNumber = caseNumberTextField.getText().trim();
        String status = getSelectedStatus();

        if (caseNumber.isEmpty() || status == null) {
            showAlert("Error", "All fields must be filled.");
            return;
        }

        CaseStatus caseStatus = new CaseStatus(caseNumber, status);
        caseStatusList.add(caseStatus);
        caseStatusTableView.setItems(caseStatusList);

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Do you want to save the case status?");
        if (alert.showAndWait().get() == ButtonType.OK) {
            saveCaseStatusToFile();
            showAlert("Success", "Case status saved successfully.");
        } else {
            showAlert("Information", "Case status not saved.");
        }
    }

    @FXML
    public void saveToFileButtonOnClickj(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Do you want to save the case statuses?");
        if (alert.showAndWait().get() == ButtonType.OK) {
            saveCaseStatusToFile();
            showAlert("Success", "Case statuses saved successfully.");
        } else {
            showAlert("Information", "Case statuses not saved.");
        }
    }

    @FXML
    public void generateBarChartButtonOnclick(ActionEvent event) {
        String selectedStatus = caseStatusComboBox.getValue();
        if (selectedStatus == null) {
            showAlert("Error", "Please select a case status to count.");
            return;
        }

        long count = caseStatusList.stream()
                .filter(caseStatus -> caseStatus.getStatus().equals(selectedStatus))
                .count();

        caseStatusCountTextField.setText(String.valueOf(count));
        outPutTextArea.setText("Number of cases with status '" + selectedStatus + "': " + count);
    }

    private String getSelectedStatus() {
        if (inProgressRadioButton.isSelected()) return "In Progress";
        if (pendingRadioButton.isSelected()) return "Pending";
        if (closedRadioButton.isSelected()) return "Closed";
        if (activeRadioButton.isSelected()) return "Active";
        return null;
    }

    private void saveCaseStatusToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(caseStatusFilePath))) {
            oos.writeObject(new ArrayList<>(caseStatusList));
        } catch (IOException e) {
            showAlert("Error", "Failed to save case statuses.");
        }
    }

    private void loadCaseStatusFromFile() {
        File file = new File(caseStatusFilePath);
        if (!file.exists()) return;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            List<CaseStatus> loadedStatuses = (List<CaseStatus>) ois.readObject();
            caseStatusList.addAll(loadedStatuses);
            caseStatusTableView.setItems(caseStatusList);
        } catch (IOException | ClassNotFoundException e) {
            showAlert("Error", "Failed to load case statuses.");
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
    public void returnHomeButtonOnClickj(ActionEvent actionEvent) {
    }
}
