package com.example.rab__mobile__court;

import java.time.LocalDate;

public class Survey_Request {
    private String investigatorId;
    private String institutionName;
    private String institutionLocation;
    private LocalDate surveyDate;
    private String surveyDetails;

    public Survey_Request() {
    }

    public Survey_Request(String investigatorId, String institutionName, String institutionLocation, LocalDate surveyDate, String surveyDetails) {
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


    public void setInvestigatorId(String investigatorId) {
        this.investigatorId = investigatorId;
    }

    public void setInstitutionName(String institutionName) {
        this.institutionName = institutionName;
    }

    public void setInstitutionLocation(String institutionLocation) {
        this.institutionLocation = institutionLocation;
    }

    public void setSurveyDate(LocalDate surveyDate) {
        this.surveyDate = surveyDate;
    }

    public void setSurveyDetails(String surveyDetails) {
        this.surveyDetails = surveyDetails;
    }

    @Override
    public String toString() {
        return "investigatorId : " + investigatorId + ", institutionName : " + institutionName +
                ", institutionLocation : " + institutionLocation + ", surveyDate : " + surveyDate +
                ", surveyDetails=" + surveyDetails + "\n";
    }





}
