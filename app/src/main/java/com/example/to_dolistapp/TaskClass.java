package com.example.to_dolistapp;

import androidx.annotation.NonNull;

public class TaskClass {
    private String task_to_do;
    private int checked;
    private int id;
    TaskClass(int id, String task_to_do)
    {
        this.task_to_do = task_to_do;
        this.id = id;
    }

    public void setTask_to_do(String task_to_do) {
        this.task_to_do = task_to_do;
    }

    public String getTask_to_do() {
        return task_to_do;
    }

    @NonNull
    @Override
    public String toString() {
        return task_to_do;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setChecked(int checked) {
        this.checked = checked;
    }

    public int getChecked() {
        return checked;
    }
}
