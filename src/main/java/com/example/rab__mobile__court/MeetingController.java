package com.example.rab__mobile__court;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MeetingController {

    @FXML
    private TextField meetingIdTextField;

    @FXML
    private DatePicker meetingDatePicker;

    @FXML
    private TextField meetingTimeTextFiled;

    @FXML
    private CheckBox underCoverInvestigatorCheckBox;

    @FXML
    private CheckBox magistrateCheckBox;

    @FXML
    private CheckBox rabOfficerCheckBox;

    @FXML
    private CheckBox districtOfficerCheckBox;

    @FXML
    private TableView<Meeting> scheeduleMeetingTableView;

    @FXML
    private TableColumn<Meeting, String> meetingIdCol;

    @FXML
    private TableColumn<Meeting, String> timeCol;

    @FXML
    private TableColumn<Meeting, String> participantsCol;

    @FXML
    private TableColumn<Meeting, String> dateCol;

    private final ObservableList<Meeting> meetingList = FXCollections.observableArrayList();
    private final String filePath = "MeetingSchedule.txt";

    @FXML
    public void initialize() {
        meetingIdCol.setCellValueFactory(new PropertyValueFactory<>("meetingId"));
        timeCol.setCellValueFactory(new PropertyValueFactory<>("time"));
        participantsCol.setCellValueFactory(new PropertyValueFactory<>("participants"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
    }

    @FXML
    public void saveButtonOnClick(ActionEvent actionEvent) {
        String meetingId = meetingIdTextField.getText().trim();
        String meetingTime = meetingTimeTextFiled.getText().trim();
        String meetingDate = meetingDatePicker.getValue() != null ? meetingDatePicker.getValue().toString() : "";

        if (meetingId.isEmpty() || meetingTime.isEmpty() || meetingDate.isEmpty() ||
                (!underCoverInvestigatorCheckBox.isSelected() &&
                        !magistrateCheckBox.isSelected() &&
                        !rabOfficerCheckBox.isSelected() &&
                        !districtOfficerCheckBox.isSelected())) {
            showAlert("Error", "All fields must be filled, and at least one participant must be selected.");
            return;
        }

        String participants = buildParticipants();
        Meeting meeting = new Meeting(meetingId, meetingTime, meetingDate, participants);

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Do you want to save this meeting schedule?");

        if (alert.showAndWait().get() == ButtonType.OK) {
            meetingList.add(meeting);
            writeToFile(meeting);
            showAlert("Success", "Meeting schedule saved successfully.");
        } else {
            showAlert("Information", "Meeting schedule was not saved.");
        }
    }

    private String buildParticipants() {
        List<String> participants = new ArrayList<>();
        if (underCoverInvestigatorCheckBox.isSelected()) participants.add("Undercover Investigator");
        if (magistrateCheckBox.isSelected()) participants.add("Magistrate");
        if (rabOfficerCheckBox.isSelected()) participants.add("RAB Officer");
        if (districtOfficerCheckBox.isSelected()) participants.add("District Officer");
        return String.join(", ", participants);
    }

    private void writeToFile(Meeting meeting) {
        FileWriter fw = null;
        try {
            File f = new File(filePath);
            fw = new FileWriter(f, true);
            fw.write(meeting.toFileString() + "\n");
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
    public void showMeetingScheduleOnClick(ActionEvent actionEvent) {
        scheeduleMeetingTableView.getItems().clear();
        File f = new File(filePath);
        try (Scanner scanner = new Scanner(f)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Meeting meeting = Meeting.fromFileString(line);
                scheeduleMeetingTableView.getItems().add(meeting);
            }
        } catch (Exception e) {
            showAlert("Error", "Failed to load meeting schedules.");
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