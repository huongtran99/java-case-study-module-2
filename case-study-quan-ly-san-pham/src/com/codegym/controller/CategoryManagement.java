package com.codegym.controller;

import com.codegym.model.Category;

import java.util.ArrayList;
import java.util.List;

import static com.codegym.Main.*;

public class CategoryManagement implements IGeneralManagement<Category> {

    private final List<Category> categoryList = new ArrayList<>();

    public List<Category> getCategoryList() {
        return categoryList;
    }

    @Override
    public void showAll() {
        if (categoryList.isEmpty()) {
            System.out.println("Không có danh mục nào hết trơn");
        }
        for (Category category : categoryList) {
            System.out.println(category);
        }
    }

    @Override
    public void addNew(Category category) {
        categoryList.add(category);
    }

    @Override
    public void updateById(String id, Category category) {
        int index = findById(id);
        categoryList.set(index, category);
    }

    @Override
    public void removeById(String id) {
        int index = findById(id);
        categoryList.remove(index);
    }

    @Override
    public int findById(String id) {
        int index = -1;
        for (int i = 0; i < categoryList.size(); i++) {
            if (categoryList.get(i).getIdCategory().equals(id)) {
                index = i;
                break;
            }
        }
        return index;
    }

    public int findByNameCategory(String nameCategory) {
        int index = -1;
        for (int i = 0; i < categoryList.size(); i++) {
            if (categoryList.get(i).getNameCategory().equals(nameCategory)) {
                index = i;
                break;
            }
        }
        return index;
    }

    public boolean displayProduct(String nameCategory) {
        boolean check = false;
        for (int i = 0; i < productManagement.getProductList().size(); i++) {
            if (productManagement.getProductList().get(i).getCategory() != null) {
                if (productManagement.getProductList().get(i).getCategory().getNameCategory().equals(nameCategory)) {
                    System.out.println(productManagement.getProductList().get(i));
                    check = true;
                }
            }
        }
        return check;
    }

    public int ProductByCategory(String nameCategory) {
        int count = 0;
        for (int i = 0; i < productManagement.getProductList().size(); i++) {
            if (productManagement.getProductList().get(i).getCategory().getNameCategory().equals(nameCategory)) {
                count++;
            }
        }
        return count;
    }
}
