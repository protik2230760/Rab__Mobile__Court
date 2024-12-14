package com.example.rab__mobile__court;

import java.io.Serializable;


public class SurveyData implements Serializable {
    private String month;
    private String year;
    private String category;
    private int value;

    public SurveyData(String month, String year, String category, int value) {
        this.month = month;
        this.year = year;
        this.category = category;
        this.value = value;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "SurveyData{" +
                "Month='" + month + '\'' +
                ", Year='" + year + '\'' +
                ", Category='" + category + '\'' +
                ", Value=" + value +
                '}';
    }
}
