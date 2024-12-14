package com.example.rab__mobile__court;

import java.io.Serializable;

public class Operation implements Serializable {
    private String title;
    private String institutionName;
    private String institutionAddress;
    private String urgencyReason;
    private String operationDate;

    public Operation(String title, String institutionName, String institutionAddress, String urgencyReason, String operationDate) {
        this.title = title;
        this.institutionName = institutionName;
        this.institutionAddress = institutionAddress;
        this.urgencyReason = urgencyReason;
        this.operationDate = operationDate;
    }

    public Operation(String token, String token1, double v) {

    }

    public String getTitle() {
        return title;
    }

    public String getInstitutionName() {
        return institutionName;
    }

    public String getInstitutionAddress() {
        return institutionAddress;
    }

    public String getUrgencyReason() {
        return urgencyReason;
    }

    public String getOperationDate() {
        return operationDate;
    }

    @Override
    public String toString() {
        return "Title: " + title +
                ", Institution Name: " + institutionName +
                ", Institution Address: " + institutionAddress +
                ", Urgency Reason: " + urgencyReason +
                ", Operation Date: " + operationDate;
    }

    public static Operation fromFileString(String line) {
        String[] tokens = line.split(",");
        return new Operation(tokens[0], tokens[1], tokens[2], tokens[3], tokens[4]);
    }

    public String toFileString() {
        return title + "," + institutionName + "," + institutionAddress + "," + urgencyReason + "," + operationDate;
    }
}
