package com.example.plantscare.database;

public class seasons {
    int id;
    String name;
    String imge;

    public seasons(int id, String name, String imge) {
        this.id = id;
        this.name = name;
        this.imge = imge;
    }


    public seasons(String name, String imge) {
        this.name = name;
        this.imge = imge;
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

    public String getImge() {
        return imge;
    }

    public void setImge(String imge) {
        this.imge = imge;
    }
}
