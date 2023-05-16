package com.example.plantscare.database;


import java.util.ArrayList;

//النباتات
public class Theplant {
    String id;
    String name;
    String details;
    int imga;
    //String id, int lettuce

    public Theplant(String crop, int lettuce) {
    }
    public Theplant(String id, String name) {
        this.id=id;
        this.name= name;
    }

    public int getImga() {
        return imga;
    }

    int id_cat;
    ArrayList<diseases>diseasesArrayList;
    ArrayList<pharmaceutical>pharmaceuticalArrayList;

 

    public Theplant(String id, String name, String details, int imga, int id_cat, ArrayList<diseases> diseasesArrayList, ArrayList<pharmaceutical> pharmaceuticalArrayList) {
        this.id = id;
        this.name = name;
        this.details = details;
        this.imga = imga;
        this.id_cat = id_cat;
        this.diseasesArrayList = diseasesArrayList;
        this.pharmaceuticalArrayList = pharmaceuticalArrayList;
    }

    public Theplant(String name, String details, int imga, int id_cat, ArrayList<diseases> diseasesArrayList, ArrayList<pharmaceutical> pharmaceuticalArrayList) {
        this.name = name;
        this.details = details;
        this.imga = imga;
        this.id_cat = id_cat;
        this.diseasesArrayList = diseasesArrayList;
        this.pharmaceuticalArrayList = pharmaceuticalArrayList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
