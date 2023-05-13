package com.example.plantscare.database;


import java.util.ArrayList;

//النباتات
public class the_plants {
    int id;
    String name;
    String details;
    int imga;

    public the_plants(String خس, int lettuce) {
    }

    public int getImga() {
        return imga;
    }

    int id_cat;
    ArrayList<diseases>diseasesArrayList;
    ArrayList<pharmaceutical>pharmaceuticalArrayList;

 

    public the_plants(int id, String name, String details, int imga, int id_cat, ArrayList<diseases> diseasesArrayList, ArrayList<pharmaceutical> pharmaceuticalArrayList) {
        this.id = id;
        this.name = name;
        this.details = details;
        this.imga = imga;
        this.id_cat = id_cat;
        this.diseasesArrayList = diseasesArrayList;
        this.pharmaceuticalArrayList = pharmaceuticalArrayList;
    }

    public the_plants(String name, String details, int imga, int id_cat, ArrayList<diseases> diseasesArrayList, ArrayList<pharmaceutical> pharmaceuticalArrayList) {
        this.name = name;
        this.details = details;
        this.imga = imga;
        this.id_cat = id_cat;
        this.diseasesArrayList = diseasesArrayList;
        this.pharmaceuticalArrayList = pharmaceuticalArrayList;
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



    public void setImga(int imga) {
        this.imga = imga;
    }

    public int getId_cat() {
        return id_cat;
    }

    public void setId_cat(int id_cat) {
        this.id_cat = id_cat;
    }

    public ArrayList<diseases> getDiseasesArrayList() {
        return diseasesArrayList;
    }

    public void setDiseasesArrayList(ArrayList<diseases> diseasesArrayList) {
        this.diseasesArrayList = diseasesArrayList;
    }

    public ArrayList<pharmaceutical> getPharmaceuticalArrayList() {
        return pharmaceuticalArrayList;
    }

    public void setPharmaceuticalArrayList(ArrayList<pharmaceutical> pharmaceuticalArrayList) {
        this.pharmaceuticalArrayList = pharmaceuticalArrayList;
    }

 
}
