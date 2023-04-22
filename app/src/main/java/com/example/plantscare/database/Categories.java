package com.example.plantscare.database;
//التصنيفات
public class Categories {
    String id_cat;
    String name;
    String imge;

    public Categories() {
    }

    public Categories(String id, String name, String imge) {
        this.id_cat = id;
        this.name = name;
        this.imge = imge;
    }

    public Categories(String name, String imge) {
        this.name = name;
        this.imge = imge;
    }

    public String getId() {
        return id_cat;
    }

    public void setId(String id) {
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
