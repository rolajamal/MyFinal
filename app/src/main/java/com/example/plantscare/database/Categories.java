package com.example.plantscare.database;
//التصنيفات
public class Categories {
    String id_cat;
    String category;
    String imge;

    public Categories() {
    }

    public Categories(String id, String category, String imge) {
        this.id_cat = id;
        this.category =category;
        this.imge = imge;
    }

    public Categories(String category, String imge) {
        this.category = category;
        this.imge = imge;
    }

    public String getId() {
        return id_cat;
    }

    public void setId(String id) {
        this.id_cat = id;
    }

    public String getcategory() {
        return category;
    }

    public void setcategory(String category) {
        this.category = category;
    }

    public String getImge() {
        return imge;
    }

    public void setImge(String imge) {
        this.imge = imge;
    }
}
