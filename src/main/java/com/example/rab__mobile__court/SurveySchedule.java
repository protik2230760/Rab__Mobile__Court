package com.example.rab__mobile__court;

public class SurveySchedule {
    private String surveyId;
    private String institutionName;
    private String location;
    private String surveyDate;

    public SurveySchedule(String surveyId, String institutionName, String location, String surveyDate) {
        this.surveyId = surveyId;
        this.institutionName = institutionName;
        this.location = location;
        this.surveyDate = surveyDate;
    }

    public String getSurveyId() {
        return surveyId;
    }

    public String getInstitutionName() {
        return institutionName;
    }

    public String getLocation() {
        return location;
    }

    public String getSurveyDate() {
        return surveyDate;
    }

    @Override
    public String toString() {
        return String.format("Survey Id: %s, Institution Name: %s, Location: %s, Survey Date: %s",
                surveyId, institutionName, location, surveyDate);
    }

    public String toFileString() {
        return surveyId + "," + institutionName + "," + location + "," + surveyDate;
    }
}
