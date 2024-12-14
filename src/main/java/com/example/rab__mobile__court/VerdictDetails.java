package com.example.rab__mobile__court;

import java.io.Serializable;

public class VerdictDetails implements Serializable {
    private String caseId;
    private String verdictId;
    private String verdictType;

    public VerdictDetails(String caseId, String verdictId, String verdictType) {
        this.caseId = caseId;
        this.verdictId = verdictId;
        this.verdictType = verdictType;
    }

    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    public String getVerdictId() {
        return verdictId;
    }

    public void setVerdictId(String verdictId) {
        this.verdictId = verdictId;
    }

    public String getVerdictType() {
        return verdictType;
    }

    public void setVerdictType(String verdictType) {
        this.verdictType = verdictType;
    }

    @Override
    public String toString() {
        return "VerdictDetails{" +
                "caseId='" + caseId + '\'' +
                ", verdictId='" + verdictId + '\'' +
                ", verdictType='" + verdictType + '\'' +
                '}';
    }
}

