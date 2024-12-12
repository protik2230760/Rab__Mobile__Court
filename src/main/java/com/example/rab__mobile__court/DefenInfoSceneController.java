package com.example.rab__mobile__court;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;

import java.io.*;
import java.util.regex.Pattern;

public class DefenInfoSceneController {

    @FXML
    private TextField nameTextField, nidNumTextField, permAddrTextField, contdetailsTextField, nameOfInstiTextField;
    @FXML
    private RadioButton maleRB, femaleRB, transRB;
    @FXML
    private ToggleGroup GEnder;
    @FXML
    private CheckBox relatedToOrgCheckBox;
    @FXML
    private TextArea infoTextArea;

    private final String filePath = "DefendantInfo.bin";

    @FXML
    public void saveInfoButtonOnClick(ActionEvent event) {
        if (!validateInputs()) return;

        Alert confirmation = new Alert(AlertType.CONFIRMATION, "Do you want to save this information?", ButtonType.OK, ButtonType.CANCEL);
        confirmation.showAndWait();

        if (confirmation.getResult() == ButtonType.OK) {
            try {
                String gender = ((RadioButton) GEnder.getSelectedToggle()).getText();
                boolean relatedToOrganization = relatedToOrgCheckBox.isSelected();
                String nameOfInstitution = relatedToOrganization ? nameOfInstiTextField.getText() : null;

                DefendantInfo defendantInfo = new DefendantInfo(
                        nameTextField.getText(),
                        nidNumTextField.getText(),
                        permAddrTextField.getText(),
                        contdetailsTextField.getText(),
                        gender,
                        relatedToOrganization,
                        nameOfInstitution
                );

                writeDefendantInfoToFile(defendantInfo);
                showInfoAlert("Success", "The data has been saved.");
                displaySavedInfo(); // Display all saved information in the TextArea.
            } catch (IOException ex) {
                showInfoAlert("Error", "An error occurred while saving the data.");
            }
        } else {
            showInfoAlert("Cancelled", "The data was not saved.");
        }
    }

    private void writeDefendantInfoToFile(DefendantInfo info) throws IOException {
        File f = new File(filePath);
        FileOutputStream fos = new FileOutputStream(f, true);
        ObjectOutputStream oos;

        if (f.exists() && f.length() > 0) {
            oos = new AppendableObjectOutputStream(fos);
        } else {
            oos = new ObjectOutputStream(fos);
        }

        oos.writeObject(info);
        oos.close();
    }

    private void displaySavedInfo() {
        infoTextArea.clear();
        try (FileInputStream fis = new FileInputStream(filePath);
             ObjectInputStream ois = new ObjectInputStream(fis)) {

            while (true) {
                DefendantInfo info = (DefendantInfo) ois.readObject();
                infoTextArea.appendText(info.toString() + "\n");
            }
        } catch (EOFException e) {
            // End of file reached
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    private boolean validateInputs() {
        if (nameTextField.getText().isEmpty() || nidNumTextField.getText().isEmpty() ||
                permAddrTextField.getText().isEmpty() || contdetailsTextField.getText().isEmpty() ||
                GEnder.getSelectedToggle() == null || (relatedToOrgCheckBox.isSelected() && nameOfInstiTextField.getText().isEmpty())) {
            showAlert(AlertType.WARNING, "Invalid Input", "All fields must be filled.");
            return false;
        }

        if (!Pattern.matches("\\d{10,17}", nidNumTextField.getText())) {
            showAlert(AlertType.WARNING, "Invalid Input", "NID Number must be 10-17 digits.");
            return false;
        }

        if (!Pattern.matches("\\d{10,15}", contdetailsTextField.getText())) {
            showAlert(AlertType.WARNING, "Invalid Input", "Contact Details must be 10-15 digits.");
            return false;
        }

        if (relatedToOrgCheckBox.isSelected() && nameOfInstiTextField.getText().trim().isEmpty()) {
            showAlert(AlertType.WARNING, "Invalid Input", "Name of Institution must be provided if related to an organization.");
            return false;
        }

        return true;
    }

    private void showAlert(AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showInfoAlert(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
