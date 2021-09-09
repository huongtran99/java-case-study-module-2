package com.codegym.model;

public class Category {
    private String idCategory;
    private String nameCategory;

    public Category() {

    }

    public Category(String idCategory, String nameCategory) {
        this.idCategory = idCategory;
        this.nameCategory = nameCategory;
    }

    public String getNameCategory() {
        return nameCategory;
    }

    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }

    public String getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(String idCategory) {
        this.idCategory = idCategory;
    }

    @Override
    public String toString() {
        return " idCategory= " + idCategory + ", nameCategory= " + nameCategory;
    }
}
