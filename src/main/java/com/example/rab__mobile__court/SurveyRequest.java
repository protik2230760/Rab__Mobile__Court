package com.example.rab__mobile__court;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.Serializable;
import java.time.LocalDate;

public class SurveyRequest implements Serializable {

    private String investigatorId;
    private String institutionName;
    private String institutionLocation;
    private LocalDate surveyDate;
    private String surveyDetails;

    public SurveyRequest(String investigatorId, String institutionName, String institutionLocation, LocalDate surveyDate, String surveyDetails) {
        this.investigatorId = investigatorId;
        this.institutionName = institutionName;
        this.institutionLocation = institutionLocation;
        this.surveyDate = surveyDate;
        this.surveyDetails = surveyDetails;
    }

    public String getInvestigatorId() {
        return investigatorId;
    }

    public String getInstitutionName() {
        return institutionName;
    }

    public String getInstitutionLocation() {
        return institutionLocation;
    }

    public LocalDate getSurveyDate() {
        return surveyDate;
    }

    public String getSurveyDetails() {
        return surveyDetails;
    }

    public String toFileString() {
        return investigatorId + "," + institutionName + "," + institutionLocation + "," + surveyDate + "," + surveyDetails;
    }

    public static SurveyRequest fromFileString(String fileString) {
        String[] tokens = fileString.split(",");
        return new SurveyRequest(tokens[0], tokens[1], tokens[2], LocalDate.parse(tokens[3]), tokens[4]);
    }

    public StringProperty investigatorIdProperty() {
        return new SimpleStringProperty(investigatorId);
    }

    public StringProperty institutionNameProperty() {
        return new SimpleStringProperty(institutionName);
    }

    public StringProperty institutionLocationProperty() {
        return new SimpleStringProperty(institutionLocation);
    }

    public StringProperty surveyDateProperty() {
        return new SimpleStringProperty(surveyDate.toString());
    }

    public StringProperty surveyDetailsProperty() {
        return new SimpleStringProperty(surveyDetails);
    }
}