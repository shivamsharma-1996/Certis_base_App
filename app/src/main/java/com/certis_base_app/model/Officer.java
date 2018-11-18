package com.certis_base_app.model;

import java.util.List;

public class Officer {

    private String name;
    private List<Task> taskList;

    public Officer() {
    }

    public Officer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }
}
