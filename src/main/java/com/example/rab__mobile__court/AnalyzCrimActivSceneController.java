package com.example.rab__mobile__court;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import java.io.*;
import java.util.ArrayList;

public class AnalyzCrimActivSceneController {

    @FXML
    private ComboBox<String> crimeTypeCB;

    @FXML
    private TextField occAmountTextField, occYearTextField, inYrOfCriTextField;

    @FXML
    private TextArea viewSavedInfoTextArea;

    @FXML
    private Label outputLabel;

    @FXML
    private PieChart anaCriActpieChart;

    private final ArrayList<CriminalActivity> criminalActivities = new ArrayList<>();
    private final String filePath = "CriminalActivityData.bin";

    @FXML
    public void initialize() {
        crimeTypeCB.setItems(FXCollections.observableArrayList("Robbery", "Fraud", "Murder", "Assault"));
    }

    @FXML
    public void saveActInfoButtonOnClick(ActionEvent event) {
        String crimeType = crimeTypeCB.getValue();
        String occurrenceAmountStr = occAmountTextField.getText();
        String occurrenceYearStr = occYearTextField.getText();

        if (crimeType == null || occurrenceAmountStr.isEmpty() || occurrenceYearStr.isEmpty()) {
            showAlert("Error", "All fields must be filled!");
            return;
        }

        int occurrenceAmount;
        int occurrenceYear;

        try {
            occurrenceAmount = Integer.parseInt(occurrenceAmountStr);
            occurrenceYear = Integer.parseInt(occurrenceYearStr);
        } catch (NumberFormatException e) {
            showAlert("Error", "Occurrence Amount and Year must be integers!");
            return;
        }

        CriminalActivity activity = new CriminalActivity(crimeType, occurrenceAmount, occurrenceYear);
        criminalActivities.add(activity);
        saveActivitiesToFile();
        showAlert("Success", "Activity information saved successfully!");
    }

    @FXML
    public void viewSavedInfoButtonOnClick(ActionEvent event) {
        viewSavedInfoTextArea.clear();
        loadActivitiesFromFile();
        for (CriminalActivity activity : criminalActivities) {
            viewSavedInfoTextArea.appendText(activity.toString() + "\n");
        }
    }

    @FXML
    public void loadpieChartButtonOnClick(ActionEvent event) {
        anaCriActpieChart.getData().clear();
        loadActivitiesFromFile();

        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

        for (CriminalActivity activity : criminalActivities) {
            pieChartData.add(new PieChart.Data(activity.getCrimeType(), activity.getOccurrenceAmount()));
        }

        anaCriActpieChart.setData(pieChartData);

        for (PieChart.Data data : anaCriActpieChart.getData()) {
            data.getNode().addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, event1 -> {
                Alert dataAlert = new Alert(Alert.AlertType.INFORMATION);
                dataAlert.setContentText("Crime Type: " + data.getName() + "\nOccurrence Amount: " + (int) data.getPieValue());
                dataAlert.showAndWait();
            });
        }
    }

    @FXML
    public void viewMaxMinActButtonOnClick(ActionEvent event) {
        String inputYearStr = inYrOfCriTextField.getText();

        if (inputYearStr.isEmpty()) {
            showAlert("Error", "Input year must be provided!");
            return;
        }

        int inputYear;
        try {
            inputYear = Integer.parseInt(inputYearStr);
        } catch (NumberFormatException e) {
            showAlert("Error", "Input year must be an integer!");
            return;
        }

        loadActivitiesFromFile();

        CriminalActivity maxActivity = null;
        CriminalActivity minActivity = null;

        for (CriminalActivity activity : criminalActivities) {
            if (activity.getOccurrenceYear() == inputYear) {
                if (maxActivity == null || activity.getOccurrenceAmount() > maxActivity.getOccurrenceAmount()) {
                    maxActivity = activity;
                }
                if (minActivity == null || activity.getOccurrenceAmount() < minActivity.getOccurrenceAmount()) {
                    minActivity = activity;
                }
            }
        }

        if (maxActivity == null || minActivity == null) {
            outputLabel.setText("No activities found for the year " + inputYear);
        } else {
            outputLabel.setText("Max: " + maxActivity + "\nMin: " + minActivity);
        }
    }

    private void saveActivitiesToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            for (CriminalActivity activity : criminalActivities) {
                oos.writeObject(activity);
            }
        } catch (IOException e) {
            showAlert("Error", "Error occurred while saving activities to file.");
        }
    }

    private void loadActivitiesFromFile() {
        criminalActivities.clear();
        File file = new File(filePath);

        if (!file.exists()) {
            return;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            while (true) {
                CriminalActivity activity = (CriminalActivity) ois.readObject();
                criminalActivities.add(activity);
            }
        } catch (EOFException ignored) {
            // End of file reached
        } catch (IOException | ClassNotFoundException e) {
            showAlert("Error", "Error occurred while loading activities from file.");
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
        SceneSwitcher.switchScene("MagistrateDashboard.fxml", actionEvent, "Home");
    }
}
