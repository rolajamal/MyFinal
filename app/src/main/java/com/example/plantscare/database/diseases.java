package com.example.plantscare.database;
//الامراض
public class diseases {
    int id;
    String name;
    String detalis;

    public diseases(int id, String name, String detalis) {
        this.id = id;
        this.name = name;
        this.detalis = detalis;
    }

    public diseases(String name, String detalis) {
        this.name = name;
        this.detalis = detalis;
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

    public String getDetalis() {
        return detalis;
    }

    public void setDetalis(String detalis) {
        this.detalis = detalis;
    }
}
