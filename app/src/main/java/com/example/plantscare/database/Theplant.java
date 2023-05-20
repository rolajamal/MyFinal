package com.example.plantscare.database;


import java.util.ArrayList;

//النباتات
public class Theplant {
    String id;
    String plant;
    String details;
    int imga;
    //String id, int lettuce

    public Theplant() {
    }

    public Theplant(String crop, int lettuce) {
    }
    public Theplant(String id, String plant) {
        this.id=id;
        this.plant= plant;
    }

    public int getImga() {
        return imga;
    }

    int id_cat;
    ArrayList<diseases>diseasesArrayList;
    ArrayList<pharmaceutical>pharmaceuticalArrayList;


    @Override
    public String toString() {
        return "Theplant{" +
                "id='" + id + '\'' +
                ", name='" + plant+ '\'' +
                '}';
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPlant() {
        return plant;
    }

    public void setPlant(String plant) {
        this.plant = plant;
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
