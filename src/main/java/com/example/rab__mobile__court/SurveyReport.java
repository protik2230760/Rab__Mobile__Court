package com.example.rab__mobile__court;

import java.io.Serializable;
import java.time.LocalDate;

public class SurveyReport implements Serializable {

    private String surveyId;
    private String institutionName;
    private String institutionLocation;
    private LocalDate surveyDate;
    private String suspiciousActivities;

    public SurveyReport(String surveyId, String institutionName, String institutionLocation, LocalDate surveyDate, String suspiciousActivities) {
        this.surveyId = surveyId;
        this.institutionName = institutionName;
        this.institutionLocation = institutionLocation;
        this.surveyDate = surveyDate;
        this.suspiciousActivities = suspiciousActivities;
    }

    public String getSurveyId() {
        return surveyId;
    }

    public String toFileString() {
        return surveyId + "," + institutionName + "," + institutionLocation + "," + surveyDate + "," + suspiciousActivities.replaceAll("\n", "|");
    }

    public static SurveyReport fromFileString(String fileString) {
        String[] tokens = fileString.split(",");
        String activities = tokens[4].replaceAll("\\|", "\n");
        return new SurveyReport(tokens[0], tokens[1], tokens[2], LocalDate.parse(tokens[3]), activities);
    }

    public String toFormattedString() {
        return "Initial Survey ID: " + surveyId + "\n" +
                "Institution Name: " + institutionName + "\n" +
                "Institution Location: " + institutionLocation + "\n" +
                "Survey Date: " + surveyDate + "\n" +
                "Suspicious Activities:\n" + suspiciousActivities;
    }
}

