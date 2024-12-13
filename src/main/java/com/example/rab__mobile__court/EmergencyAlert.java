package com.example.rab__mobile__court;

import java.io.Serializable;

public class EmergencyAlert implements Serializable {
    private String emergencyId;
    private String institutionName;
    private String location;
    private String description;
    private String alertReceiver;

    public EmergencyAlert(String emergencyId, String institutionName, String location, String description, String alertReceiver) {
        this.emergencyId = emergencyId;
        this.institutionName = institutionName;
        this.location = location;
        this.description = description;
        this.alertReceiver = alertReceiver;
    }

    public String getEmergencyId() {
        return emergencyId;
    }

    public String getInstitutionName() {
        return institutionName;
    }

    public String getLocation() {
        return location;
    }

    public String getDescription() {
        return description;
    }

    public String getAlertReceiver() {
        return alertReceiver;
    }

    @Override
    public String toString() {
        return String.format("emergancyAlertId : %s, institutionName : %s, institutionLocation : %s, alertReciever : %s, description : %s",
                emergencyId, institutionName, location, alertReceiver, description);
    }

    public String toFileString() {
        return emergencyId + "," + institutionName + "," + location + "," + alertReceiver + "," + description;
    }
}
