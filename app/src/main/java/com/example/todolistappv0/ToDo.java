package com.example.todolistappv0;

public class ToDo {

    private int id;
    private String task;

    // constructor
    public ToDo (int newId, String newTask) {
        // Reference Setters
        setID(newId);
        setTask(newTask);

    }


    // SETTERS
    private void setTask(String newTask) {
        task = newTask;
    }

    private void setID(int newId) {
        id = newId;
    }


    // GETTERS
    public int getId() {
        return id;
    }

    public String getTask() {
        return task;
    }

    // Create a toString method
    public String toString() {
        return id + "; " + task;
    }



}
