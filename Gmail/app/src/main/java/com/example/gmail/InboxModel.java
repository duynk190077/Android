package com.example.gmail;

public class InboxModel {
    public InboxModel(int avatarColor, String name, String subject, String description, String time) {
        this.avatarColor = avatarColor;
        this.name = name;
        this.subject = subject;
        this.description = description;
        this.time = time;
    }

    public int getAvatarColor() {
        return avatarColor;
    }

    public void setAvatarColor(int avatarColor) {
        this.avatarColor = avatarColor;
    }

    private int avatarColor;
    private String name;
    private String subject;
    private String description;
    private String time;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
