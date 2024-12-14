package com.example.rab__mobile__court;

import java.io.Serializable;

public class Operation2 implements Serializable {
    private String operationType;
    private String place;
    private double budget;

    public Operation2(String operationType, String place, double budget) {
        this.operationType = operationType;
        this.place = place;
        this.budget = budget;
    }

    public String getOperationType() {
        return operationType;
    }

    public String getPlace() {
        return place;
    }

    public double getBudget() {
        return budget;
    }

    public String toFileString() {
        return operationType + "," + place + "," + budget;
    }

    public static Operation fromFileString(String line) {
        String[] tokens = line.split(",");
        return new Operation(tokens[0], tokens[1], Double.parseDouble(tokens[2]));
    }

    @Override
    public String toString() {
        return "Operation Type: " + operationType + ", Place: " + place + ", Budget: " + budget + " Tk";
    }
}
