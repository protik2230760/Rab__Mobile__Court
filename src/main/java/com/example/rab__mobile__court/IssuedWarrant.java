package com.example.rab__mobile__court;

import java.io.Serializable;
import java.time.LocalDate;

public class IssuedWarrant extends SearchWarrant implements Serializable {
    private String rabOfficerId;
    private int numberOfSubordinates;
    private String userName;
    private LocalDate issueDate;

    public IssuedWarrant(String warrantId, String institutionName, String institutionType, int badgeNumber,
                         LocalDate searchDate, String reasonForSearch, String rabOfficerId,
                         int numberOfSubordinates, String userName, LocalDate issueDate) {
        super(warrantId, institutionName, institutionType, badgeNumber, searchDate, reasonForSearch);
        this.rabOfficerId = rabOfficerId;
        this.numberOfSubordinates = numberOfSubordinates;
        this.userName = userName;
        this.issueDate = issueDate;
    }

    public String getRabOfficerId() {
        return rabOfficerId;
    }

    public void setRabOfficerId(String rabOfficerId) {
        this.rabOfficerId = rabOfficerId;
    }

    public int getNumberOfSubordinates() {
        return numberOfSubordinates;
    }

    public void setNumberOfSubordinates(int numberOfSubordinates) {
        this.numberOfSubordinates = numberOfSubordinates;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", RAB Officer ID= " + rabOfficerId +
                ", Number Of Subordinates= " + numberOfSubordinates +
                ", User Name= " + userName +
                ", Issue Date= " + issueDate;
    }
}
