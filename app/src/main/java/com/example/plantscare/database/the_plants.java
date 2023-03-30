package com.example.plantscare.database;
//النباتات
public class the_plants {
    int id;
    String name;
    String details;
    String imga;

    public the_plants(int id, String name, String details, String imga) {
        this.id = id;
        this.name = name;
        this.details = details;
        this.imga = imga;
    }

    public the_plants(String name, String details, String imga) {
        this.name = name;
        this.details = details;
        this.imga = imga;
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

    public String getImga() {
        return imga;
    }

    public void setImga(String imga) {
        this.imga = imga;
    }
}
