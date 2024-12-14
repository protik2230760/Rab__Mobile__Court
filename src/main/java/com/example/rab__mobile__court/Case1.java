package com.example.rab__mobile__court;

import java.io.Serializable;

public class Case1 implements Serializable {
    private final String caseId;
    private final String caseTitle;
    private final String officerBadge;
    private final String evidenceList;
    private final String eyeWitnessInfo;
    private String defendantInfo;

    public Case1(String caseId, String caseTitle, String officerBadge, String evidenceList, String eyeWitnessInfo, String defendantInfo) {
        this.caseId = caseId;
        this.caseTitle = caseTitle;
        this.officerBadge = officerBadge;
        this.evidenceList = evidenceList;
        this.eyeWitnessInfo = eyeWitnessInfo;
        this.defendantInfo = defendantInfo;
    }

    public String getCaseId() {
        return caseId;
    }

    public void appendNotes(String notes) {
        this.defendantInfo += "\n" + notes;
    }

    @Override
    public String toString() {
        return "Case ID: " + caseId + "\n" +
                "Title: " + caseTitle + "\n" +
                "Officer Badge: " + officerBadge + "\n" +
                "Evidence List: " + evidenceList + "\n" +
                "Eye Witness Info: " + eyeWitnessInfo + "\n" +
                "Defendant Info: " + defendantInfo;
    }

    public String toFileString() {
        return caseId + "," + caseTitle + "," + officerBadge + "," + evidenceList + "," + eyeWitnessInfo + "," + defendantInfo;
    }
}
