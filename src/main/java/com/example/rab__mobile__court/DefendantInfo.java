package com.example.rab__mobile__court;

import java.io.Serializable;

public class DefendantInfo implements Serializable {
    private String name;
    private String nidNumber;
    private String permanentAddress;
    private String contactDetails;
    private String gender;
    private boolean relatedToOrganization;
    private String nameOfInstitution;

    public DefendantInfo(String name, String nidNumber, String permanentAddress, String contactDetails, String gender, boolean relatedToOrganization, String nameOfInstitution) {
        this.name = name;
        this.nidNumber = nidNumber;
        this.permanentAddress = permanentAddress;
        this.contactDetails = contactDetails;
        this.gender = gender;
        this.relatedToOrganization = relatedToOrganization;
        this.nameOfInstitution = nameOfInstitution;
    }

    public String getName() {
        return name;
    }

    public String getNidNumber() {
        return nidNumber;
    }

    public String getPermanentAddress() {
        return permanentAddress;
    }

    public String getContactDetails() {
        return contactDetails;
    }

    public String getGender() {
        return gender;
    }

    public boolean isRelatedToOrganization() {
        return relatedToOrganization;
    }

    public String getNameOfInstitution() {
        return nameOfInstitution;
    }

    @Override
    public String toString() {
        return String.format(
                "Defendant Info: Name= %s, NID Number= %s, Address= %s, Contact= %s, Gender= %s, Related to Organization= %b, Institution Name= %s",
                name, nidNumber, permanentAddress, contactDetails, gender, relatedToOrganization, nameOfInstitution == null ? "N/A" : nameOfInstitution
        );
    }
}
