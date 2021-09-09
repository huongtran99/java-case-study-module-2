package com.codegym.view;

import com.codegym.model.Category;
import com.codegym.model.Product;
import com.codegym.model.products.Clothes;
import com.codegym.model.products.Food;
import com.codegym.model.products.Technology;

import java.io.*;
import java.util.Arrays;
import java.util.List;

import static com.codegym.Main.*;

public class ManageMenu {

    static {
        productManagement.addNew(new Product("001","Cơm","ngon", 58, 0));
        productManagement.addNew(new Product("002","Áo","ngon", 70, 4));
        productManagement.addNew(new Product("003","Gạo","ngon", 21, 0));
        productManagement.addNew(new Product("004","Rau"," ngon", 63, 3));
        productManagement.addNew(new Product("005","Bóng đèn"," ngon", 21, 1));
        productManagement.addNew(new Product("006","Laptop"," ngon", 37, 5));
    }

    public void showAdminMenu() {
        String choice;
        do {
            menuAdmin();
            choice = scanner.nextLine();
            switch (choice) {
                case "1": {
                    MenuProductManager();
                    break;
                }
                case "2": {
                    MenuCategoryManager();
                    break;
                }
                case "0": {

                    break;
                }
                default: {
                    System.out.println("Nhập sai rồi bạn ơi, nhập lại đi nào");
                }
            }
        } while (!choice.equals("0"));
    }

    private void menuAdmin() {
        System.out.println("---------Menu Admin---------");
        System.out.println("1. Quản lý sản phẩm");
        System.out.println("2. Quản lý danh sách");
        System.out.println("0. Đăng xuất");
        System.out.print("Nhập vào lựa chọn của bạn: ");
    }

    private void MenuCategoryManager() {
        String choice;
        do {
            menuCategory();
            choice = scanner.nextLine();
            switch (choice) {
                case "1": {
                    addCategory();
                    break;
                }
                case "2": {
                    displayCategory();
                    break;
                }
                case "3": {
                    displayProductOfCategory();
                    break;
                }
                case "4": {
                    editCategory();
                    break;
                }
                case "5": {
                    deleteCategory();
                    break;
                }
                case "6": {
                    addProductToCategory();
                    break;
                }
                case "0": {

                    break;
                }
                default: {
                    System.out.println("Nhập sai rồi bạn ơi, nhập lại đi nào");
                }
            }
        } while (!choice.equals("0"));

    }

    private void menuCategory() {
        System.out.println("--------Quản lý danh sách--------");
        System.out.println("1. Thêm danh mục");
        System.out.println("2. Hiển thị các danh mục");
        System.out.println("3. Lọc danh sách sản phẩm theo tên danh mục");
        System.out.println("4. Sửa danh mục");
        System.out.println("5. Xóa danh mục");
        System.out.println("6. Thêm sản phẩm vào danh mục");
        System.out.println("0. Quay lại");
        System.out.print("Nhập lựa chọn của bạn: ");
    }

    private void addProductToCategory() {
        System.out.print("Bạn muốn thêm vào danh mục nào: ");
        String nameCategory = scanner.nextLine();
        int indexNameCategory = categoryManagement.findByNameCategory(nameCategory);
        if (indexNameCategory != -1) {
            System.out.print("Nhập mã sản phẩm cần thêm: ");
            String idProduct = scanner.nextLine();
            int indexProduct = productManagement.findById(idProduct);
            if (indexProduct != -1) {
                productManagement.getProductList().get(indexProduct).setCategory(categoryManagement.getCategoryList().get(indexNameCategory));
                System.out.println("Thêm vào thành công!!");
            } else {
                System.out.println("không tìm thấy id nào như vậy");
            }
        } else {
            System.out.println("Không có danh mục này !!!");
        }
    }

    private void displayProductOfCategory() {
        System.out.print("Bạn muốn hiển thị sản phẩm của danh mục nào: ");
        String nameCategory = scanner.nextLine();
        boolean check = categoryManagement.displayProduct(nameCategory);
        if (!check) {
            System.out.println("Không có sản phẩm nào");
        }
    }

    private void deleteCategory() {
        System.out.println("Xóa một danh mục");
        System.out.print("Nhập mã danh mục cần xóa: ");
        String categoryId = scanner.nextLine();
        int index = categoryManagement.findById(categoryId);
        if (index != -1) {
            categoryManagement.removeById(categoryId);
            for (int i = 0; i < productManagement.getProductList().size(); i++) {
                if (productManagement.getProductList().get(i).getCategory().getIdCategory().equals(categoryId)) {
                    productManagement.removeById(productManagement.getProductList().get(i).getIdProduct());
                }
            }
        } else {
            System.out.println("Không tìm thấy :((");
        }
    }

    private void editCategory() {
        System.out.println("Sửa thông tin danh mục");
        System.out.print("Nhập mã danh mục cần sửa: ");
        String categoryId = scanner.nextLine();
        int index = categoryManagement.findById(categoryId);
        if (index != -1) {
            Category category = inputCategory();
            categoryManagement.updateById(categoryId, category);
            for (int i = 0; i < productManagement.getProductList().size(); i++) {
                if (productManagement.getProductList().get(i).getCategory().getIdCategory().equals(categoryId)) {
                    productManagement.getProductList().get(i).setCategory(category);
                }
            }
        } else {
            System.out.println("Không tìm thấy :((");
        }
    }

    private void displayCategory() {
        categoryManagement.showAll();
    }

    private void addCategory() {
        Category category = inputCategory();
        if (!categoryManagement.getCategoryList().isEmpty()) {
            int index = categoryManagement.findById(category.getIdCategory());
            if (index != -1) {
                System.out.println("id này đã tồn tại mời nhập lại");
            } else {
                categoryManagement.addNew(category);
            }
        } else {
            categoryManagement.addNew(category);
        }
    }

    private Category inputCategory() {
        System.out.println("Nhập danh mục sản phẩm");
        System.out.print("Nhập mã danh mục: ");
        String idCategory = scanner.nextLine();
        System.out.print("Nhập tên danh mục: ");
        String nameCategory = scanner.nextLine();
        return new Category(idCategory, nameCategory);
    }

    /*-----------------------------------------------------------------------------------------------------------------*/

    private void MenuProductManager() {
        String choice;
        do {
            menuProduct();
            choice = scanner.nextLine();
            switch (choice) {
                case "1": {
                    addProduct();
                    break;
                }
                case "2": {
                    displayProduct();
                    break;
                }
                case "3": {
                    editProduct();
                    break;
                }
                case "4": {
                    deleteProduct();
                    break;
                }
                case "5": {
                    writeProductToFileCsv();
                    break;
                }
                case "6": {
                    readProductToFileCsv();
                    break;
                }
                case "0": {

                    break;
                }
                default: {
                    System.out.println("Nhập sai rồi bạn ơi, nhập lại đi nào");
                }
            }
        } while (!choice.equals("0"));
    }

    private void menuProduct() {
        System.out.println("--------Quản lý sản phẩm---------");
        System.out.println("1. Thêm sản phẩm mới");
        System.out.println("2. Hiển thị danh sách sản phẩm");
        System.out.println("3. Sửa thông tin sản phẩm");
        System.out.println("4. Xóa sản phẩm");
        System.out.println("5. Ghi sản phẩm vào file");
        System.out.println("6. Đọc từ file");
        System.out.println("0. Quay lại");
        System.out.print("Nhập lựa chọn của bạn: ");
    }

    private void readProductToFileCsv() {
        try {
            System.out.print("Nhập vào file cần đọc: ");
            String filePath = scanner.nextLine();
            File file = new File(filePath);

            FileReader fileReader = new FileReader(file);
            BufferedReader br = new BufferedReader(fileReader);
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(Arrays.toString(line.split(",")));
            }
            br.close();
        } catch (IOException ie) {
            System.err.println("Fie không tồn tại hoặc nội dung có lỗi!");
        }
    }

    private void writeProductToFileCsv() {
        List<Product> products = productManagement.getProductList();
        try {
            System.out.print("Bạn muốn ghi vào file nào: ");
            String filePath = scanner.nextLine();
            File file = new File(filePath);
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fileWriter);
            for (Product product: products) {
                bw.write(String.valueOf(product));
                bw.newLine();
            }
            System.out.println("Ghi file thành công");
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void deleteProduct() {
        System.out.println("Xóa một sản phẩm");
        System.out.print("Nhập mã sản phẩm mà bạn muốn xóa: ");
        String productId = scanner.nextLine();
        int index = productManagement.findById(productId);
        if (index != -1) {
            productManagement.removeById(productId);
        } else {
            System.out.println("Không tìm thấy :((");
        }
    }

    private void editProduct() {
        System.out.println("Sửa thông tin sản phẩm");
        System.out.print("Nhập mã sản phẩm cần sửa: ");
        String productId = scanner.nextLine();
        int index = productManagement.findById(productId);
        if (index != -1) {
            Product product = inputProductNew();
            productManagement.updateById(productId, product);
        } else {
            System.out.println("Không tìm thấy :((");
        }
    }

    private void displayProduct() {
        productManagement.showAll();
    }

    private void addProduct() {
        Product product = inputProductNew();
        if (!productManagement.getProductList().isEmpty()) {
            int index = productManagement.findById(product.getIdProduct());
            if (index != -1) {
                System.out.println("id này đã tồn tại mời nhập lại");
            } else {
                productManagement.addNew(product);
            }
        } else {
            productManagement.addNew(product);
        }
    }

    private Product inputProductNew() {
        System.out.println("Nhập thông tin sản phẩm");
        System.out.print("Nhập tên danh mục cần thêm vào: ");
        String nameCategory = scanner.nextLine();
        System.out.print("Nhập mã sản phẩm: ");
        String idProduct = scanner.nextLine();
        System.out.print("Nhập tên sản phẩm: ");
        String nameProduct = scanner.nextLine();
        System.out.print("Nhập mô tả sản phẩm: ");
        String descriptionProduct = scanner.nextLine();
        System.out.print("Nhập giá bán: ");
        double priceProduct = scanner.nextDouble();
        switch (nameCategory) {
            case "clothes": {
                System.out.print("Nhập màu sắc: ");
                String color = scanner.nextLine();
                Category category = new Category("idClothes", nameCategory);
                for (int i = 0; i < categoryManagement.getCategoryList().size(); i++) {
                    if (categoryManagement.getCategoryList().get(i).getIdCategory().equals(category.getIdCategory())) {
                        return new Clothes(idProduct, nameProduct, descriptionProduct, priceProduct, category, 0, color);
                    }
                }
                categoryManagement.getCategoryList().add(category);
                return new Clothes(idProduct, nameProduct, descriptionProduct, priceProduct, category, 0, color);
            }
            case "food": {
                System.out.print("Nhập trọng lượng: ");
                double weight = scanner.nextInt();
                scanner.nextLine();
                Category category = new Category("idFood", nameCategory);
                for (int i = 0; i < categoryManagement.getCategoryList().size(); i++) {
                    if (categoryManagement.getCategoryList().get(i).getIdCategory().equals(category.getIdCategory())) {
                        return new Food(idProduct, nameProduct, descriptionProduct, priceProduct, category, 0, weight);
                    }
                }
                categoryManagement.getCategoryList().add(category);
                return new Food(idProduct, nameProduct, descriptionProduct, priceProduct, category, 0, weight);
            }
            case "technology": {
                System.out.print("Nhập số lượng: ");
                int quantity = scanner.nextInt();
                scanner.nextLine();
                Category category = new Category("idTechnology", nameCategory);
                for (int i = 0; i < categoryManagement.getCategoryList().size(); i++) {
                    if (categoryManagement.getCategoryList().get(i).getIdCategory().equals(category.getIdCategory())) {
                        return new Technology(idProduct, nameProduct, descriptionProduct, priceProduct, category, 0, quantity);
                    }
                }
                categoryManagement.getCategoryList().add(category);
                return new Technology(idProduct, nameProduct, descriptionProduct, priceProduct, category, 0, quantity);
            }
            default: {
                for (int i = 0; i < categoryManagement.getCategoryList().size(); i++) {
                    if (categoryManagement.getCategoryList().get(i).getNameCategory().equals(nameCategory)) {
                        return new Product(idProduct, nameProduct, descriptionProduct, priceProduct, categoryManagement.getCategoryList().get(i), 0);
                    }
                }
                System.out.println("Danh mục bạn thêm chưa có trong danh mục");
            }
        }
        return new Product(idProduct, nameProduct, descriptionProduct, priceProduct, 0);
    }

}
