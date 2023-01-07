package com.example.contact;

public class ContactModel {
    private int ID;
    private String fullname;
    private String phoneNumber;
    private String email;

    public ContactModel(int ID, String fullname, String phoneNumber, String email) {
        this.ID = ID;
        this.fullname = fullname;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
