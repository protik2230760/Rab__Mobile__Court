package com.example.rab__mobile__court;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;

import java.io.*;
import java.util.ArrayList;

public class viewSurveyChartController {

    @FXML
    private ComboBox<String> monthComboBox, yearComboBox;

    @FXML
    private TextField CetegoryTextField, valueTextField;

    @FXML
    private TextArea showCurrentDataTextArea;

    @FXML
    private PieChart surveyPieChart;

    private final ArrayList<SurveyData> surveyDataList = new ArrayList<>();
    private final String filePath = "SurveyData.bin";

    @FXML
    public void initialize() {
        monthComboBox.setItems(FXCollections.observableArrayList(
                "January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"
        ));

        yearComboBox.setItems(FXCollections.observableArrayList(
                "2020", "2021", "2022", "2023", "2024"
        ));
    }

    @FXML
    public void addChartDataOnClick(ActionEvent event) {
        String month = monthComboBox.getValue();
        String year = yearComboBox.getValue();
        String category = CetegoryTextField.getText();
        String valueStr = valueTextField.getText();

        if (month == null || year == null || category.isEmpty() || valueStr.isEmpty()) {
            showAlert("Error", "All fields must be filled!");
            return;
        }

        int value;
        try {
            value = Integer.parseInt(valueStr);
        } catch (NumberFormatException e) {
            showAlert("Error", "Enter Value must be an integer!");
            return;
        }

        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Confirm Action");
        confirmationAlert.setHeaderText("Do you want to save this data?");
        confirmationAlert.setContentText("Category: " + category + ", Value: " + value);

        if (confirmationAlert.showAndWait().get() == ButtonType.OK) {
            SurveyData data = new SurveyData(month, year, category, value);
            surveyDataList.add(data);
            saveSurveyDataToFile();
            showAlert("Success", "Data added and saved successfully!");
        }
    }

    @FXML
    public void showChartOnClick(ActionEvent event) {
        loadSurveyDataFromFile();
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

        for (SurveyData data : surveyDataList) {
            pieChartData.add(new PieChart.Data(data.getCategory(), data.getValue()));
        }

        surveyPieChart.setData(pieChartData);

        for (PieChart.Data data : surveyPieChart.getData()) {
            data.getNode().addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, event1 -> {
                Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
                infoAlert.setTitle("Survey Data Details");
                infoAlert.setHeaderText("Category Details");
                infoAlert.setContentText("Category: " + data.getName() + "\nValue: " + (int) data.getPieValue());
                infoAlert.showAndWait();
            });
        }
    }

    private void saveSurveyDataToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            for (SurveyData data : surveyDataList) {
                oos.writeObject(data);
            }
        } catch (IOException e) {
            showAlert("Error", "Error occurred while saving data to file.");
        }
    }

    private void loadSurveyDataFromFile() {
        surveyDataList.clear();
        File file = new File(filePath);

        if (!file.exists()) {
            return;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            while (true) {
                SurveyData data = (SurveyData) ois.readObject();
                surveyDataList.add(data);
            }
        } catch (EOFException ignored) {
            // End of file reached
        } catch (IOException | ClassNotFoundException e) {
            showAlert("Error", "Error occurred while loading data from file.");
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
    public void backToDashBoardOnClick(ActionEvent actionEvent) throws IOException {
        SceneSwitcher.switchScene("Orpa1Dashboard.fxml", actionEvent, "Dashboard");
    }
}
