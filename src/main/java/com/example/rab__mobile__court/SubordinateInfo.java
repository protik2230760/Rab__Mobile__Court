package com.example.rab__mobile__court;

public class SubordinateInfo {
    private String dateOfOperation;
    private String location;
    private String institutionName;
    private String institutionType;
    private int numberOfSubordinates;

    public SubordinateInfo(String dateOfOperation, String location, String institutionName, String institutionType, int numberOfSubordinates) {
        this.dateOfOperation = dateOfOperation;
        this.location = location;
        this.institutionName = institutionName;
        this.institutionType = institutionType;
        this.numberOfSubordinates = numberOfSubordinates;
    }

    public String getDateOfOperation() {
        return dateOfOperation;
    }

    public String getLocation() {
        return location;
    }

    public String getInstitutionName() {
        return institutionName;
    }

    public String getInstitutionType() {
        return institutionType;
    }

    public int getNumberOfSubordinates() {
        return numberOfSubordinates;
    }

    @Override
    public String toString() {
        return String.format("Date of Operation: %s, Location: %s, Institution Name: %s, Institution Type: %s, Number of Subordinates: %d",
                dateOfOperation, location, institutionName, institutionType, numberOfSubordinates);
    }

    public String toFileString() {
        return dateOfOperation + "," + location + "," + institutionName + "," + institutionType + "," + numberOfSubordinates;
    }
}