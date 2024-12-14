package com.example.rab__mobile__court;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.text.CollationElementIterator;
import java.util.Scanner;


public class ViewSurveyReportController  {

    @FXML
    private TextField surveyIdTextField;
    @FXML
    private ComboBox<String> surveyMonthComboBox;
    @FXML
    private ComboBox<String> surveyYearComboBox;
    @FXML
    private TextArea displaySurveyReportOnClick;


    @javafx.fxml.FXML
    public void initialize() {
        surveyMonthComboBox.getItems().addAll("January","February","March","April",
                "May","June","July","August","September","October",
                "November","December");

        surveyYearComboBox.getItems().addAll("2023","2024","2025");

        // Load Survey Report of previous month (November) when the scene is Initialized
        String str="Survey Report of November 2023 . Select month Year and id to search specific Survey.\n";
        try{
            String fileName="Submit_Survey_Report Decembar 2023.txt";
            File file=new File(fileName);
            if(!file.exists()){
                displaySurveyReportOnClick.setText("Survey File Doesnt Exist");
                return;
            }
            Scanner sc=new Scanner(file);


            while(sc.hasNextLine()){
                String [] part =sc.nextLine().split("#");
                U8_Survey_Report surveyReport=new U8_Survey_Report(part[0],part[1],part[2]);
                str+=surveyReport.toString() +"\n";

            }
        }catch(Exception e){

            System.out.println("Something went Wrong!");
        }
        displaySurveyReportOnClick.setText(str);


    }

    @FXML
    private void viewSurveyReportOnClick(ActionEvent event) {
        String surveyId=surveyIdTextField.getText();
        if(surveyId.equals("")){
            showAlert("Please Enter Survey ID", "Survey ID cannot be empty.");

            return;
        }else if (isInteger(surveyId)) {

            int parsedSurveyId = Integer.parseInt(surveyId);
            System.out.println("Survey Id (Integer): " + parsedSurveyId);
        } else {
            showAlert("Invalid Survey Id", "Survey Id must be an integer.");
            return;
        }

        String month=surveyMonthComboBox.getValue();
        if(month == null){
            showAlert("Please Select Month of Survey Report", "Month of Survey Report cannot be empty.");

            return;
        }
        String year=surveyYearComboBox.getValue();
        if(year == null){
            showAlert("Please Select Year of Survey Report", "Year of Survey Report cannot be empty.");

            return;
        }

        try{
            String fileName="Submit_Survey_Report"+month+year+".txt";
            File file=new File(fileName);
            if(!file.exists()){
                showAlert("Survey File Doesnt Exist", "Survey File Doesnt Exist.");
                return;
            }
            Scanner sc=new Scanner(file);
            String surveyDetails="";
            boolean surveyIdExist=false;
            while(sc.hasNextLine()){
                String [] part=sc.nextLine().split("#");
                if(surveyId.equals(part[0])){
                    surveyDetails=part[2];
                    surveyIdExist=true;
                    break;

                }

            }
            if(surveyIdExist){
                displaySurveyReportOnClick.setText(surveyDetails);
                return;
            }
            else{
                showAlert("Survey ID not Found", "Survey ID not Found.");
            }
        }catch(Exception e){

        }



    }

    @FXML
    private void backToDashBoardOnClick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Orpa1Dashboard.fxml"));
        Parent secondRoot=loader.load();
        Stage stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(secondRoot));

    }
    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    //Method to check valid integer
    private boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
 }

}