package com.example.plantscare.database;
//الادوية
public class pharmaceutical {
    int id ;
    String name;
    String details;

    public pharmaceutical(int id, String name, String details) {
        this.id = id;
        this.name = name;
        this.details = details;
    }

    public pharmaceutical(String name, String details) {
        this.name = name;
        this.details = details;
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

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
