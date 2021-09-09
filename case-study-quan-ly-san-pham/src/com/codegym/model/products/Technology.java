package com.codegym.model.products;

import com.codegym.model.Category;
import com.codegym.model.Product;

public class Technology extends Product {

    private int quantity;

    public Technology(String idProduct, String nameProduct, String descriptionProduct, double priceProduct, Category category, int sold, int quantity) {
        super(idProduct, nameProduct, descriptionProduct, priceProduct, category, sold);
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
