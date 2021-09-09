package com.codegym.model.products;

import com.codegym.model.Category;
import com.codegym.model.Product;

public class Food extends Product {
    private double weight;

    public Food(String idProduct, String nameProduct, String descriptionProduct, double priceProduct, Category category, int sold, double weight) {
        super(idProduct, nameProduct, descriptionProduct, priceProduct, category, sold);
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Food: " + super.toString() + ", " + "weight= " + weight;
    }
}
