package com.example.rab__mobile__court;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Submit_Survey_ReportController {

    @FXML
    private ComboBox<String> monthComboBox;
    @FXML
    private ComboBox<String> yearComboBox1;
    @FXML
    private TextField surveyIdTextField;
    @FXML
    private TextField institutionNameTextField;
    @FXML
    private TextArea reportDetailsTextArea;
    @FXML
    private DatePicker surveyReportDatePicker;
    @FXML
    private TextArea displaySurveyReportTextArea;


    @javafx.fxml.FXML
    public void initialize() {
        monthComboBox.getItems().addAll("January","February","March","April",
                "May","June","July","August","September","October",
                "November","December");
        yearComboBox1.getItems().addAll("2023","2024","2025");
    }

    @FXML
    private void bacKToDashBoardOnAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Orpa2Dashboard.fxml"));
        Parent secondRoot=loader.load();
        Stage stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(secondRoot));
    }

    @FXML
    private void saveReportOnAction(ActionEvent event) {
        String month=monthComboBox.getValue();
        if(month == null){
            showAlert("", "Please Select Month of Survey Report");
            return;
        }
        String year=yearComboBox1.getValue();
        if(year == null){
            showAlert("", "Please Select year of Survey Report");
            return;
        }

        String surveyId=surveyIdTextField.getText();
        if(surveyId.equals("")){
            showAlert("", "Please Select Survey ID");
            return;
        }else if (isInteger(surveyId)) {
            int parsedSurveyId = Integer.parseInt(surveyId);
            System.out.println("Survey Id (Integer): " + parsedSurveyId);
        } else {
            showAlert("Invalid Survey Id", "Survey Id must be an integer.");
            return;
        }


        String institutionName=institutionNameTextField.getText();
        if(institutionName.equals("")){
            showAlert("", "Please Select Institution Name");
            return;
        }

        String reportDetails=reportDetailsTextArea.getText();

        if(reportDetails.equals("")){
            showAlert("", "Please Write report Details");
            return;
        }

        try{




            String fileName="Submit_Survey_Report"+month+year+".txt";
            File file=new File(fileName);
            if(!file.exists())file.createNewFile();

            boolean surveyIdExist=false;
            Scanner sc=new Scanner(file);
            while(sc.hasNextLine()){
                String [] part=sc.nextLine().split("#");
                if(surveyId.equals(part[0])){
                    surveyIdExist=true;
                    break;
                }
            }

            if(surveyIdExist){
                displaySurveyReportTextArea.setText("The Survey Id is Already Exist!");
                return;
            }
            else{
                FileWriter fileWriter=new FileWriter(file,true);
                fileWriter.write(surveyId+"#"+institutionName+"#"+reportDetails+"\n");
                fileWriter.close();
            }
        }catch(Exception e){

        }



        U8_Survey_Report surveyreport=new U8_Survey_Report(surveyId,institutionName,reportDetails);
        displaySurveyReportTextArea.setText(surveyreport.toString());
        Alert informationAlert = new Alert(Alert.AlertType.INFORMATION);
        informationAlert.setTitle("Saved");
        informationAlert.setHeaderText(null);
        informationAlert.setContentText("Surevy Report Save Successfully");
        informationAlert.showAndWait();

        //Clear fields

        surveyIdTextField.clear();
        institutionNameTextField.clear();
        reportDetailsTextArea.clear();
    }


    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }



    private boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
      }
}
}