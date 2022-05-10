package com.example.foodapp.data;

public class Food {
    private final String id;
    private final String name;
    private final int size;
    private final int cost;
    private final String category;
    private final Auxdata auxdata;

    public Food(String id, String name, int size, int cost, String category, Auxdata auxdata) {
        this.id = id;
        this.name = name;
        this.size = size;
        this.cost = cost;
        this.category = category;
        this.auxdata = auxdata;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    
    public int getSize() {
        return size;
    }

    public int getCost() {
        return cost;
    }

    public String getCategory() {
        return category;
    }

    public Auxdata getAuxdata() {
        return auxdata;
    }

}
