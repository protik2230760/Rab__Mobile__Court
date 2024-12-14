package com.example.rab__mobile__court;

import java.time.LocalDate;

public class CommandOpertaion {
    private String institutionName;
    private String location;
    private String urgencyReason;
    private LocalDate investigationDate;

    public CommandOpertaion(String institutionName, String location, String urgencyReason, LocalDate investigationDate) {
        this.institutionName = institutionName;
        this.location = location;
        this.urgencyReason = urgencyReason;
        this.investigationDate = investigationDate;
    }

    public String getInstitutionName() {
        return this.institutionName;
    }

    public String getLocation() {
        return this.location;
    }

    public String getUrgencyReason() {
        return this.urgencyReason;
    }

    public LocalDate getInvestigationDate() {
        return this.investigationDate;
    }

    public void setInstitutionName(String institutionName) {
        this.institutionName = institutionName;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setUrgencyReason(String urgencyReason) {
        this.urgencyReason = urgencyReason;
    }

    public void setInvestigationDate(LocalDate investigationDate) {
        this.investigationDate = investigationDate;
    }

    public String toString() {
        return "CommandOpertaion{institutionName=" + this.institutionName + ", location=" + this.location + ", urgencyReason=" + this.urgencyReason + ", investigationDate=" + this.investigationDate + '}';
    }
}