package com.example.rab__mobile__court;

import java.time.LocalDate;

public class Evidence {

    public int evidenceId;
    public String eviName;
    public String evidenceType;
    public LocalDate dateOfAcquire;
    public int caseId;

    public Evidence(int evidenceId, String eviName, String evidenceType, LocalDate dateOfAcquire, int caseId) {
        this.evidenceId = evidenceId;
        this.eviName = eviName;
        this.evidenceType = evidenceType;
        this.dateOfAcquire = dateOfAcquire;
        this.caseId = caseId;
    }

    public Evidence() {
    }

    public int getEvidenceId() {
        return evidenceId;
    }

    public void setEvidenceId(int evidenceId) {
        this.evidenceId = evidenceId;
    }

    public String getEviName() {
        return eviName;
    }

    public void setEviName(String eviName) {
        this.eviName = eviName;
    }

    public String getEvidenceType() {
        return evidenceType;
    }

    public void setEvidenceType(String evidenceType) {
        this.evidenceType = evidenceType;
    }

    public LocalDate getDateOfAcquire() {
        return dateOfAcquire;
    }

    public void setDateOfAcquire(LocalDate dateOfAcquire) {
        this.dateOfAcquire = dateOfAcquire;
    }

    public int getCaseId() {
        return caseId;
    }

    public void setCaseId(int caseId) {
        this.caseId = caseId;
    }

    @Override
    public String toString() {
        return "Evidence{" + "evidenceId=" + evidenceId + ", eviName=" + eviName + ", evidenceType=" + evidenceType + ", dateOfAcquire=" + dateOfAcquire + ", caseId=" + caseId+'}';
}

}