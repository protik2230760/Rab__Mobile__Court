package com.example.rab__mobile__court;

import java.io.Serializable;

public class CaseStatus implements Serializable {
    private String caseNumber;
    private String status;

    public CaseStatus(String caseNumber, String status) {
        this.caseNumber = caseNumber;
        this.status = status;
    }

    public String getCaseNumber() {
        return caseNumber;
    }

    public void setCaseNumber(String caseNumber) {
        this.caseNumber = caseNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return caseNumber + "," + status;
    }

    public static CaseStatus fromString(String fileString) {
        String[] parts = fileString.split(",");
        return new CaseStatus(parts[0], parts[1]);
    }
}
