package com.example.rab__mobile__court;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

import java.io.*;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RequestSearchWarrentController {

    @FXML
    private TextField nameOfInstiTextField, serWarIdTextField, badgeNumTextField, reasForSerTextField;
    @FXML
    private ComboBox<String> typeOfInstiComboBox;
    @FXML
    private DatePicker propDateOfSerDatePicker;
    @FXML
    private TextArea showSaveinfoTextArea;

    private final String filePath = "SerWarObjects.bin";

    @FXML
    public void initialize() {
        typeOfInstiComboBox.getItems().addAll("Hospital", "School", "General Store", "Office");
    }

    @FXML
    public void saveWarInfoButtonOnClick(ActionEvent event) {
        if (!validateInputs()) return;

        try {
            File f = new File(filePath);
            FileOutputStream fos = null;
            ObjectOutputStream oos = null;

            try {
                if (f.exists()) {
                    fos = new FileOutputStream(f, true);
                    oos = new AppendableObjectOutputStream(fos);
                } else {
                    fos = new FileOutputStream(f);
                    oos = new ObjectOutputStream(fos);
                }

                SearchWarrant sw = new SearchWarrant(
                        serWarIdTextField.getText(),
                        nameOfInstiTextField.getText(),
                        typeOfInstiComboBox.getValue(),
                        Integer.parseInt(badgeNumTextField.getText()),
                        propDateOfSerDatePicker.getValue(),
                        reasForSerTextField.getText()
                );

                oos.writeObject(sw);

                showInfoAlertAfterConfirmation("The data has been saved.");
            } catch (IOException ex) {
                Logger.getLogger(RequestSearchWarrentController.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                if (oos != null) oos.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(RequestSearchWarrentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void showSavedInfoButtonOnClick(ActionEvent event) {
        File f = new File(filePath);
        FileInputStream fis = null;
        ObjectInputStream ois = null;

        try {
            fis = new FileInputStream(f);
            ois = new ObjectInputStream(fis);

            SearchWarrant sw;
            showSaveinfoTextArea.setText("");

            try {
                while (true) {
                    sw = (SearchWarrant) ois.readObject();
                    showSaveinfoTextArea.appendText(sw.toString() + "\n");
                }
            } catch (EOFException e) {

            } catch (ClassNotFoundException | IOException ex) {
                Logger.getLogger(RequestSearchWarrentController.class.getName()).log(Level.SEVERE, null, ex);
            }

            showSaveinfoTextArea.appendText("All objects loaded successfully from bin file.\n");
        } catch (IOException ex) {
            Logger.getLogger(RequestSearchWarrentController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (ois != null) ois.close();
            } catch (IOException ex) {
                Logger.getLogger(RequestSearchWarrentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    public void returnButtonOnClick(ActionEvent actionevent) throws IOException {
        SceneSwitcher.switchScene("Item.fxml",actionevent, "DashBoard");
    }

    private boolean validateInputs() {
        if (nameOfInstiTextField.getText().isEmpty() || !nameOfInstiTextField.getText().matches("[a-zA-Z\\s,-]+")) {
            showAlert(AlertType.WARNING, "Invalid Input", "Institution name must contain only alphabetic characters.");
            return false;
        }
        if (serWarIdTextField.getText().isEmpty() || !isInteger(serWarIdTextField.getText())) {
            showAlert(AlertType.WARNING, "Invalid Input", "Search Warrant ID must be a numeric value.");
            return false;
        }
        if (badgeNumTextField.getText().isEmpty() || !badgeNumTextField.getText().matches("\\d{6}")) {
            showAlert(AlertType.WARNING, "Invalid Input", "Badge number must be a 6-digit numeric value.");
            return false;
        }
        if (propDateOfSerDatePicker.getValue() == null || propDateOfSerDatePicker.getValue().isBefore(LocalDate.now())) {
            showAlert(AlertType.WARNING, "Invalid Input", "Proposed date cannot be in the past.");
            return false;
        }
        return true;
    }

    private boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void showInfoAlertAfterConfirmation(String str) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Information Alert");
        alert.setContentText(str);
        alert.showAndWait();
    }

    private void showAlert(AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
