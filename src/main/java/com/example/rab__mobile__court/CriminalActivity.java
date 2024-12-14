package com.example.rab__mobile__court;

import java.io.Serializable;

public class CriminalActivity implements Serializable {
    private String crimeType;
    private int occurrenceAmount;
    private int occurrenceYear;

    public CriminalActivity(String crimeType, int occurrenceAmount, int occurrenceYear) {
        this.crimeType = crimeType;
        this.occurrenceAmount = occurrenceAmount;
        this.occurrenceYear = occurrenceYear;
    }

    public String getCrimeType() {
        return crimeType;
    }

    public void setCrimeType(String crimeType) {
        this.crimeType = crimeType;
    }

    public int getOccurrenceAmount() {
        return occurrenceAmount;
    }

    public void setOccurrenceAmount(int occurrenceAmount) {
        this.occurrenceAmount = occurrenceAmount;
    }

    public int getOccurrenceYear() {
        return occurrenceYear;
    }

    public void setOccurrenceYear(int occurrenceYear) {
        this.occurrenceYear = occurrenceYear;
    }

    @Override
    public String toString() {
        return "Crime Type: " + crimeType +
                ", Occurrence Amount: " + occurrenceAmount +
                ", Occurrence Year: " + occurrenceYear;
    }
}

