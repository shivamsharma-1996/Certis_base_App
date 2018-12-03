package com.certis_base_app.model;

//shivam sharma
public class LatestTaskUpdate {
    private String task_update_time;
    private String task_title;
    private String task_subtitle;
    private String task_status;

    public LatestTaskUpdate(String task_update_time, String task_title, String task_subtitle, String task_status) {
        this.task_update_time = task_update_time;
        this.task_title = task_title;
        this.task_subtitle = task_subtitle;
        this.task_status = task_status;
    }

    public String getTask_update_time() {
        return task_update_time;
    }

    public void setTask_update_time(String task_update_time) {
        this.task_update_time = task_update_time;
    }

    public String getTask_title() {
        return task_title;
    }

    public void setTask_title(String task_title) {
        this.task_title = task_title;
    }

    public String getTask_subtitle() {
        return task_subtitle;
    }

    public void setTask_subtitle(String task_subtitle) {
        this.task_subtitle = task_subtitle;
    }

    public String getTask_status() {
        return task_status;
    }

    public void setTask_status(String task_status) {
        this.task_status = task_status;
    }
}
