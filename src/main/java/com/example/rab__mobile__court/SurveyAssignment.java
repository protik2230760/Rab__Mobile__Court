package com.example.rab__mobile__court;

import java.io.Serializable;

public class SurveyAssignment implements Serializable {

    private String institutionName;
    private String institutionLocation;
    private String surveyDescription;

    public SurveyAssignment(String institutionName, String institutionLocation, String surveyDescription) {
        this.institutionName = institutionName;
        this.institutionLocation = institutionLocation;
        this.surveyDescription = surveyDescription;
    }

    public String toFileString() {
        return institutionName + "," + institutionLocation + "," + surveyDescription.replaceAll("\n", "|");
    }

    public static SurveyAssignment fromFileString(String fileString) {
        String[] tokens = fileString.split(",");
        String description = tokens[2].replaceAll("\\|", "\n");
        return new SurveyAssignment(tokens[0], tokens[1], description);
    }

    public String toFormattedString() {
        return "Institution Name: " + institutionName + ", Institution Location: " + institutionLocation + ", Survey Description: " + surveyDescription;
    }
}
