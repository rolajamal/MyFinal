package com.example.plantscare.database;
//التصنيفات
public class Categories {
    int id_cat;
    String name;
    String imge;

    public Categories() {
    }

    public Categories(int id, String name, String imge) {
        this.id_cat = id;
        this.name = name;
        this.imge = imge;
    }

    public Categories(String name, String imge) {
        this.name = name;
        this.imge = imge;
    }

    public int getId() {
        return id_cat;
    }

    public void setId(int id) {
        this.id_cat = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImge() {
        return imge;
    }

    public void setImge(String imge) {
        this.imge = imge;
    }
}
