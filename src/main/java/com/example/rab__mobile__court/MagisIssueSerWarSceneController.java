package com.example.rab__mobile__court;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MagisIssueSerWarSceneController {

    @FXML
    private TableView<SearchWarrant> warReqTable;

    @FXML
    private TableColumn<SearchWarrant, String> warIdCol;

    @FXML
    private TableColumn<SearchWarrant, String> nameInstCol;

    @FXML
    private TableColumn<SearchWarrant, String> typeInstCol;

    @FXML
    private TableColumn<SearchWarrant, Integer> badgeNumCol;

    @FXML
    private TableColumn<SearchWarrant, LocalDate> propDateOfSerCol;

    @FXML
    private TableColumn<SearchWarrant, String> serReasCol;

    @FXML
    private TextField warIdTextField, instiNameTextField, rabOffiIdTextField, numOfSubTextField, usersNameTextField;

    @FXML
    private TextArea viewDraftTextArea;

    @FXML
    private DatePicker datePicker;

    private final ObservableList<SearchWarrant> requestList = FXCollections.observableArrayList();
    private final List<IssuedWarrant> issuedWarrantList = new ArrayList<>();
    private final String filePath = "SerWarObjects.bin";
    private final String issuedWarrantFilePath = "IssuedWarrants.bin";

    @FXML
    public void initialize() {
        warIdCol.setCellValueFactory(new PropertyValueFactory<>("warrantId"));
        nameInstCol.setCellValueFactory(new PropertyValueFactory<>("institutionName"));
        typeInstCol.setCellValueFactory(new PropertyValueFactory<>("institutionType"));
        badgeNumCol.setCellValueFactory(new PropertyValueFactory<>("badgeNumber"));
        propDateOfSerCol.setCellValueFactory(new PropertyValueFactory<>("searchDate"));
        serReasCol.setCellValueFactory(new PropertyValueFactory<>("reasonForSearch"));

        loadWarrantRequests();
        warReqTable.setItems(requestList);
    }

    @FXML
    public void loadWarReqButtonOnClick(ActionEvent event) {
        requestList.clear();
        loadWarrantRequests();
        warReqTable.setItems(requestList);
    }

    @FXML
    public void viewDraftButtonOnClick(ActionEvent event) {
        String warId = warIdTextField.getText();
        String instName = instiNameTextField.getText();
        String rabOfficerId = rabOffiIdTextField.getText();
        String numOfSubs = numOfSubTextField.getText();
        String userName = usersNameTextField.getText();
        LocalDate issueDate = datePicker.getValue();

        if (warId.isEmpty() || instName.isEmpty() || rabOfficerId.isEmpty() || numOfSubs.isEmpty() || userName.isEmpty() || issueDate == null) {
            showAlert("Error", "All fields must be filled!");
            return;
        }

        if (issueDate.isBefore(LocalDate.now())) {
            showAlert("Error", "The issue date cannot be in the past!");
            return;
        }

        try {
            int numSubordinates = Integer.parseInt(numOfSubs);
            IssuedWarrant draftWarrant = new IssuedWarrant(warId, instName, "N/A", 0, LocalDate.now(),
                    "N/A", rabOfficerId, numSubordinates, userName, issueDate);

            viewDraftTextArea.setText(draftWarrant.toString());
            issuedWarrantList.add(draftWarrant);

        } catch (NumberFormatException e) {
            showAlert("Error", "Number of subordinates must be a valid integer!");
        }
    }

    @FXML
    public void issueWarButtonOnClick(ActionEvent event) {
        if (issuedWarrantList.isEmpty()) {
            showAlert("Error", "No warrant draft available to issue!");
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Are you sure you want to issue the warrant?");
        if (alert.showAndWait().get() == ButtonType.OK) {
            saveIssuedWarrants();
            showAlert("Success", "Warrant issued and saved successfully!");
        }
    }

    private void loadWarrantRequests() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            while (true) {
                SearchWarrant warrant = (SearchWarrant) ois.readObject();
                requestList.add(warrant);
            }
        } catch (EOFException ignored) {
            // End of file reached
        } catch (IOException | ClassNotFoundException e) {
            showAlert("Error", "Error reading warrant requests from file!");
        }
    }

    private void saveIssuedWarrants() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(issuedWarrantFilePath))) {
            for (IssuedWarrant warrant : issuedWarrantList) {
                oos.writeObject(warrant);
            }
            issuedWarrantList.clear();
        } catch (IOException e) {
            showAlert("Error", "Error saving issued warrants!");
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
        SceneSwitcher.switchScene("MagistrateDashboard.fxml",actionEvent, "DashBoard");

    }


}
