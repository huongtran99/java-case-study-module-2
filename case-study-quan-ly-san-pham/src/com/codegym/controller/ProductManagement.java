package com.codegym.controller;

import com.codegym.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductManagement implements IGeneralManagement<Product> {

    private final List<Product> productList = new ArrayList<>();

    public List<Product> getProductList() {
        return productList;
    }

    @Override
    public void showAll() {
        if (productList.isEmpty()) {
            System.out.println("Không có sản phẩm nào hết trơn");
        }
        for (Product product : productList) {
            System.out.println(product);
        }
    }

    @Override
    public void addNew(Product product) {
        productList.add(product);
    }

    @Override
    public void updateById(String id, Product product) {
        int index = findById(id);
        productList.set(index, product);
    }

    @Override
    public void removeById(String id) {
        int index = findById(id);
        productList.remove(index);
    }

    @Override
    public int findById(String id) {
        int index = -1;
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getIdProduct().equals(id)) {
                index = i;
                break;
            }
        }
        return index;
    }

    public int findByName(String nameProduct) {
        int index = -1;
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getNameProduct().equals(nameProduct)) {
                index = i;
                break;
            }
        }
        return index;
    }

    public void fillListProductThan1000() {
        for (Product product : productList) {
            if (product.getPriceProduct() > 1000) {
                System.out.println(product);
            }
        }
    }

    public void fillListProductLess1000() {
        for (Product product : productList) {
            if (product.getPriceProduct() < 1000) {
                System.out.println(product);
            }
        }
    }

    public void sortProductByPrice() {
        Product product;
        int position;
        for (int i = 0; i < productList.size(); i++) {
            product = productList.get(i);
            position = i;
            while (position > 0 && (product.getPriceProduct() < productList.get(position - 1).getPriceProduct())) {
                productList.set(position, productList.get(position - 1));
                position--;
            }
            productList.set(position, product);
        }
    }

    public void sortProductBySold() {
        Product product;
        int position;
        for (int i = 0; i < productList.size(); i++) {
            product = productList.get(i);
            position = i;
            while (position > 0 && (product.getSold() > productList.get(position - 1).getSold())) {
                productList.set(position, productList.get(position - 1));
                position--;
            }
            productList.set(position, product);
        }
    }

}
