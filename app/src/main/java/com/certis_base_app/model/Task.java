package com.certis_base_app.model;

public class Task {
    private String title;
    private String status;
    private String taskTimeInBetween;
    private int progress;

    public Task() {
    }


    public Task(String title, String status, String taskTimeInBetween, int progress) {
        this.title = title;
        this.status = status;
        this.taskTimeInBetween = taskTimeInBetween;
        this.progress = progress;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTaskTimeInBetween() {
        return taskTimeInBetween;
    }

    public void setTaskTimeInBetween(String taskTimeInBetween) {
        this.taskTimeInBetween = taskTimeInBetween;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
