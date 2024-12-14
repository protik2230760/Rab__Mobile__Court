package com.example.rab__mobile__court;

import java.io.Serializable;

public class Budget implements Serializable {
    private String operationType;
    private String place;
    private double budget;

    public Budget(String operationType, String place, double budget) {
        this.operationType = operationType;
        this.place = place;
        this.budget = budget;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public String toFileString() {
        return operationType + "," + place + "," + budget;
    }

    public static Budget fromFileString(String fileString) {
        String[] parts = fileString.split(",");
        return new Budget(parts[0], parts[1], Double.parseDouble(parts[2]));
    }
}

