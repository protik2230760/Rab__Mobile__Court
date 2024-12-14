package com.example.rab__mobile__court;

import java.io.Serializable;

public class CaseAssignment implements Serializable {
    private String caseNumber;
    private String lawyerName;
    private String caseDetails;

    public CaseAssignment(String caseNumber, String lawyerName, String caseDetails) {
        this.caseNumber = caseNumber;
        this.lawyerName = lawyerName;
        this.caseDetails = caseDetails;
    }

    public String getCaseNumber() {
        return caseNumber;
    }

    public String getLawyerName() {
        return lawyerName;
    }

    public String getCaseDetails() {
        return caseDetails;
    }

    @Override
    public String toString() {
        return "Case Number: " + caseNumber + ", Lawyer: " + lawyerName + ", Details: " + caseDetails;
    }
}
