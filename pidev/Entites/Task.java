package com.example.rym.pidev.Entites;

/**
 * Created by rym on 24/11/2017.
 */

public class Task {

    private int id;
    private String name;
    private String description;
    private String state;
    private String deadline;
  //  private String user ;


    public Task() {
    }

    public Task(int id, String name, String description, String state, String deadline) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.state = state;
        this.deadline = deadline;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }


  /* public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }*/
}
