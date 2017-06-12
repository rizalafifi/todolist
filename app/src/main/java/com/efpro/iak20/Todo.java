package com.efpro.iak20;

/**
 * Created by rzapalupi on 5/14/2017.
 */

public class Todo {
    private String title, description, date;

    public String getTitle() {
        return title;
    }

    public void setTitle(String todo) {
        this.title = todo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
