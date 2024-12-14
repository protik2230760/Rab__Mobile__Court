package com.example.rab__mobile__court;

import java.io.Serializable;

public class Case implements Serializable {
    private final String caseId;
    private final String caseTitle;
    private final String officerBadgeNumber;
    private final String evidenceList;
    private final String eyeWitnessInfo;
    private final String defendantInfo;

    public Case(String caseId, String caseTitle, String officerBadgeNumber, String evidenceList, String eyeWitnessInfo, String defendantInfo) {
        this.caseId = caseId;
        this.caseTitle = caseTitle;
        this.officerBadgeNumber = officerBadgeNumber;
        this.evidenceList = evidenceList;
        this.eyeWitnessInfo = eyeWitnessInfo;
        this.defendantInfo = defendantInfo;
    }

    public String getCaseId() {
        return caseId;
    }

    public String getCaseTitle() {
        return caseTitle;
    }

    public String getOfficerBadgeNumber() {
        return officerBadgeNumber;
    }

    public String getEvidenceList() {
        return evidenceList;
    }

    public String getEyeWitnessInfo() {
        return eyeWitnessInfo;
    }

    public String getDefendantInfo() {
        return defendantInfo;
    }

    public String toFileString() {
        return String.join(",", caseId, caseTitle, officerBadgeNumber, evidenceList, eyeWitnessInfo, defendantInfo);
    }

    public static Case fromFileString(String fileString) {
        String[] tokens = fileString.split(",");
        return new Case(tokens[0], tokens[1], tokens[2], tokens[3], tokens[4], tokens[5]);
    }

    @Override
    public String toString() {
        return "Case ID: " + caseId + ", Case Title: " + caseTitle + ", Eye Witness Info: " + eyeWitnessInfo;
    }
}