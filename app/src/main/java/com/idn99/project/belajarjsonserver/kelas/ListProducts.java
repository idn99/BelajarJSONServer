package com.idn99.project.belajarjsonserver.kelas;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ListProducts {

    @SerializedName("data")
    private ArrayList<Product> products;

    public ListProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }
}
