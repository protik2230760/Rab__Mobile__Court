package com.example.rab__mobile__court;

import java.io.Serializable;
import java.time.LocalDate;

public class IncidentReport implements Serializable {
    private String institutionId;
    private String institutionName;
    private LocalDate operationDate;
    private String timeOfIncident;
    private int numberOfDefendants;
    private String crimeDetails;

    public IncidentReport(String institutionId, String institutionName, LocalDate operationDate, String timeOfIncident, int numberOfDefendants, String crimeDetails) {
        this.institutionId = institutionId;
        this.institutionName = institutionName;
        this.operationDate = operationDate;
        this.timeOfIncident = timeOfIncident;
        this.numberOfDefendants = numberOfDefendants;
        this.crimeDetails = crimeDetails;
    }

    public String getInstitutionId() {
        return institutionId;
    }

    public void setInstitutionId(String institutionId) {
        this.institutionId = institutionId;
    }

    public String getInstitutionName() {
        return institutionName;
    }

    public void setInstitutionName(String institutionName) {
        this.institutionName = institutionName;
    }

    public LocalDate getOperationDate() {
        return operationDate;
    }

    public void setOperationDate(LocalDate operationDate) {
        this.operationDate = operationDate;
    }

    public String getTimeOfIncident() {
        return timeOfIncident;
    }

    public void setTimeOfIncident(String timeOfIncident) {
        this.timeOfIncident = timeOfIncident;
    }

    public int getNumberOfDefendants() {
        return numberOfDefendants;
    }

    public void setNumberOfDefendants(int numberOfDefendants) {
        this.numberOfDefendants = numberOfDefendants;
    }

    public String getCrimeDetails() {
        return crimeDetails;
    }

    public void setCrimeDetails(String crimeDetails) {
        this.crimeDetails = crimeDetails;
    }

    @Override
    public String toString() {
        return String.format(
                "Incident Report : Institution ID= %s, Time of Incident= %s, Institution Name= %s, Number of Defendants= %d, Date of Operation= %s, Crime Detail= %s",
                institutionId, timeOfIncident, institutionName, numberOfDefendants, operationDate, crimeDetails);
    }
}
