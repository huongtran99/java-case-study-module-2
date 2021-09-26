package com.codegym.model;

public class Product {
    private String idProduct;
    private String nameProduct;
    private String descriptionProduct;
    private double priceProduct;
    private int sold;
    private int amount;
    private Category category;

    public Product() {

    }

    public Product(String idProduct, String nameProduct, String descriptionProduct, double priceProduct, int sold) {
        this.idProduct = idProduct;
        this.nameProduct = nameProduct;
        this.descriptionProduct = descriptionProduct;
        this.priceProduct = priceProduct;
        this.sold = sold;
    }

    public Product(String idProduct, String nameProduct, String descriptionProduct, double priceProduct, Category category, int sold) {
        this.idProduct = idProduct;
        this.nameProduct = nameProduct;
        this.descriptionProduct = descriptionProduct;
        this.priceProduct = priceProduct;
        this.category = category;
        this.sold = sold;
    }

    public String getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(String idProduct) {
        this.idProduct = idProduct;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public String getDescriptionProduct() {
        return descriptionProduct;
    }

    public void setDescriptionProduct(String descriptionProduct) {
        this.descriptionProduct = descriptionProduct;
    }

    public double getPriceProduct() {
        return priceProduct;
    }

    public void setPriceProduct(double priceProduct) {
        this.priceProduct = priceProduct;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return  "idProduct= " + idProduct +
                ", nameProduct= " + nameProduct +
                ", descriptionProduct= " + descriptionProduct +
                ", priceProduct= " + priceProduct +
                ", " + category;
    }

     void show(){

    }

    public int getSold() {
        return sold;
    }

    public void setSold(int sold) {
        this.sold = sold;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
