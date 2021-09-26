package com.codegym.model.products;

import com.codegym.model.Category;
import com.codegym.model.Product;

public class Clothes extends Product {
    private String color;

    public Clothes() {
        super();
    }

    public Clothes(String idProduct, String nameProduct, String descriptionProduct, double priceProduct, Category category, int sold, String color) {
        super(idProduct, nameProduct, descriptionProduct, priceProduct, category, sold);
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

}
