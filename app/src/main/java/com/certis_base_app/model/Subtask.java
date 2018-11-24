package com.certis_base_app.model;

public class Subtask {
    private String title;
    private String location;
    private String type;
    private String endTime;

    public Subtask(String title, String location, String type, String endTime) {
        this.title = title;
        this.location = location;
        this.type = type;
        this.endTime = endTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
