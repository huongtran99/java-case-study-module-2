package com.codegym.model;

public class Cart {
    private Product product;

    public Cart(Product product) {
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "" + product;
    }

}
