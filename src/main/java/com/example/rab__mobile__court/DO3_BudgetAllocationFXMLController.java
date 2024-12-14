package com.example.rab__mobile__court;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class DO3_BudgetAllocationFXMLController {

    @FXML
    private TableView<Budget> budgetAllocationTableView;

    @FXML
    private TableColumn<Budget, String> operationTableCol;

    @FXML
    private TableColumn<Budget, String> placeTableCol;

    @FXML
    private TableColumn<Budget, Double> budgetTableCol;

    @FXML
    private ComboBox<String> operationTypeComboBox, placeComboBox, opesearchrationTypeComboBox1;

    @FXML
    private TextField budgetTextField;

    @FXML
    private PieChart budgetPieChart;

    private final ObservableList<Budget> budgetList = FXCollections.observableArrayList();
    private final String budgetFilePath = "BudgetAllocations.txt";

    @FXML
    public void initialize() {
        operationTableCol.setCellValueFactory(new PropertyValueFactory<>("operationType"));
        placeTableCol.setCellValueFactory(new PropertyValueFactory<>("place"));
        budgetTableCol.setCellValueFactory(new PropertyValueFactory<>("budget"));

        operationTypeComboBox.setItems(FXCollections.observableArrayList("Rescue", "Investigation", "Surveillance", "Other"));
        placeComboBox.setItems(FXCollections.observableArrayList("Dhaka", "Chittagong", "Rajshahi", "Sylhet"));
        opesearchrationTypeComboBox1.setItems(FXCollections.observableArrayList("Rescue", "Investigation", "Surveillance", "Other"));

        loadBudgetsFromFile();
    }

    @FXML
    public void allocateBudgetOnClick(ActionEvent event) {
        String operation = operationTypeComboBox.getValue();
        String place = placeComboBox.getValue();
        String budgetStr = budgetTextField.getText().trim();

        if (operation == null || place == null || budgetStr.isEmpty()) {
            showAlert("Error", "All fields must be filled.");
            return;
        }

        double budget;
        try {
            budget = Double.parseDouble(budgetStr);
        } catch (NumberFormatException e) {
            showAlert("Error", "Invalid budget amount.");
            return;
        }

        if (budget > 20000) {
            showAlert("Error", "Budget cannot exceed 20,000 Tk.");
            return;
        }

        Budget allocation = new Budget(operation, place, budget);
        budgetList.add(allocation);
        budgetAllocationTableView.setItems(budgetList);
    }

    @FXML
    public void saveToFileOnClick(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Do you want to save the budget allocations?");

        if (alert.showAndWait().get() == ButtonType.OK) {
            writeBudgetToFile();
            showAlert("Success", "Budget allocations saved successfully.");
        } else {
            showAlert("Information", "Budget allocations were not saved.");
        }
    }

    private void writeBudgetToFile() {
        FileWriter fw = null;
        try {
            File f = new File(budgetFilePath);
            fw = new FileWriter(f);
            for (Budget allocation : budgetList) {
                fw.write(allocation.toFileString() + "\n");
            }
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
    public void pieChartButtonOnClick(ActionEvent event) {
        ObservableList<PieChart.Data> pieData = FXCollections.observableArrayList();

        for (Budget allocation : budgetList) {
            pieData.add(new PieChart.Data(allocation.getOperationType(), allocation.getBudget()));
        }

        budgetPieChart.setData(pieData);

        for (PieChart.Data data : budgetPieChart.getData()) {
            data.getNode().addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, event1 -> {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Budget Details");
                alert.setHeaderText("Operation Type: " + data.getName());
                alert.setContentText("Budget: " + data.getPieValue() + " Tk.");
                alert.showAndWait();
            });
        }
    }

    private void loadBudgetsFromFile() {
        File file = new File(budgetFilePath);
        if (!file.exists()) return;

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                budgetList.add(Budget.fromFileString(line));
            }
            budgetAllocationTableView.setItems(budgetList);
        } catch (Exception e) {
            showAlert("Error", "Error occurred while reading the file.");
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
