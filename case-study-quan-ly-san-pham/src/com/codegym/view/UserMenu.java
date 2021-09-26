package com.codegym.view;

import com.codegym.model.Account;
import com.codegym.model.Cart;
import com.codegym.model.Product;

import static com.codegym.Main.*;

public class UserMenu {

    public void showUserMenu() {
        String choice;
        do {
            menuUser();
            choice = scanner.nextLine();
            switch (choice) {
                case "1": {
                    accountInformation();
                    break;
                }
                case "2": {
                    inputMoneyToAccount();
                    break;
                }
                case "3": {
                    filterListProductByPrice();
                    break;
                }
                case "4": {
                    searchProductByName();
                    break;
                }
                case "5": {
                    statisticalProductByCategory();
                    break;
                }
                case "6": {
                    sortProductByPrice();
                    break;
                }
                case "7": {
                    addProductToCart();
                    break;
                }
                case "8": {
                    displayProductInCart();
                    break;
                }
                case "9": {
                    cartCheckOut();
                    break;
                }
                case "10": {
                    showListProductBestSelling();
                    break;
                }
                default: {
                    System.out.println("Bạn nhập sai rồi vui lòng nhập lại");
                }
            }
        } while (!choice.equals("0"));
    }

    private void menuUser() {
        System.out.println("------Menu User------");
        System.out.println("1. Thông tin tài khoản");
        System.out.println("2. Nạp tiền vào tài khoản");
        System.out.println("3. Lọc danh sách sản phẩm theo giá");
        System.out.println("4. Tìm kiếm theo tên");
        System.out.println("5. Thống kê số lượng sản phẩm theo danh mục");
        System.out.println("6. Sắp xếp sản phẩm theo giá");
        System.out.println("7. Thêm sản phẩm vào giỏ hàng");
        System.out.println("8. Xem sản phẩm trong giỏ hàng");
        System.out.println("9. Thanh toán giỏ hàng của bạn");
        System.out.println("10. Xem danh sách sản phẩm bán chạy");
        System.out.println("0. Đăng xuất");
        System.out.println("Nhập lựa chọn của bạn");
    }

    private void showListProductBestSelling() {
        System.out.println("Danh sách sản phẩm bán chạy");
        int count = 0;
        for (int i = 0; i < productManagement.getProductList().size(); i++) {
            if (i == 0) {
                productManagement.sortProductBySold();
            }
            System.out.println(productManagement.getProductList().get(i) + " , đã bán đc " + productManagement.getProductList().get(i).getSold() + " sản phẩm.");
            count++;
            System.out.println("Nhấn phím Enter để xem tiếp.");
            if (count == 5) {
                count = 0;
                scanner.nextLine();
            }
        }
        scanner.nextLine();
    }

    private void cartCheckOut() {
        displayProductInCart();
        String choice;
        do {
            System.out.println("1. Thanh toán tất cả");
            System.out.println("2. Lựa chọn thanh toán");
            System.out.println("0. Quay lại");
            System.out.print("Nhập lựa chọn: ");
            choice = scanner.nextLine();
            switch (choice) {
                case "1": {
                    payAll();
                    break;
                }
                case "2": {
                    selectPayProduct();
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

    private void selectPayProduct() {
        System.out.println("Nhập mã sản phẩm cần thanh toán: ");
        String productId = scanner.nextLine();
        double moneyToPay = 0.0;
        if (cartManagement.getCartList().isEmpty()) {
            System.out.println("Giỏ hàng của bạn đang rỗng");
            return;
        }
        for (int i = 0; i < cartManagement.getCartList().size(); i++) {
            if (cartManagement.getCartList().get(i).getProduct().getIdProduct().equals(productId)) {
                moneyToPay += cartManagement.getCartList().get(i).getProduct().getPriceProduct() * cartManagement.getCartList().get(i).getProduct().getAmount();
            }
        }
        System.out.println("Bạn cần thanh toán " + moneyToPay + " USD");
        System.out.println("Bấm Y để thanh toán hoặc bấm phím bất kỳ để thoát");
        String check = scanner.nextLine();
        if (check.equals("Y")) {
            Account account = accountManagement.showAccountLogin(MainLogin.idAccount);
            if (account.getMoney() > moneyToPay) {
                account.setMoney(account.getMoney() - moneyToPay);
                System.out.println("thanh toán thành công");
                for (int i = 0; i < cartManagement.getCartList().size(); i++) {
                    if (cartManagement.getCartList().get(i).getProduct().getIdProduct().equals(productId)) {
                        cartManagement.removeById(cartManagement.getCartList().get(i).getProduct().getIdProduct());
                    }
                }
                int role = 0;
                for (int i = 0; i < productManagement.getProductList().size(); i++) {
                    if (productManagement.getProductList().get(i).getIdProduct().equals(productId)) {
                        role++;
                        productManagement.getProductList().get(i).setSold(role);
                    }
                }
            } else {
                System.out.println("Số tiền không đủ - bạn hãy nạp thêm tiền đi");
            }
        }

    }

    private void payAll() {
        double moneyToPay = 0.0;
        if (cartManagement.getCartList().isEmpty()) {
            System.out.println("Giỏ hàng của bạn đang rỗng");
            return;
        }
        for (int i = 0; i < cartManagement.getCartList().size(); i++) {
            moneyToPay += cartManagement.getCartList().get(i).getProduct().getPriceProduct() * cartManagement.getCartList().get(i).getProduct().getAmount();
        }
        System.out.println("Bạn cần thanh toán " + moneyToPay + " USD");
        confirm(moneyToPay);
    }

    private void confirm(double moneyToPay) {
        System.out.println("Bấm Y để thanh toán hoặc bấm phím bất kỳ để thoát");
        String check = scanner.nextLine();
        if (check.equals("Y")) {
            Account account = accountManagement.showAccountLogin(MainLogin.idAccount);
            if (account.getMoney() > moneyToPay) {
                account.setMoney(account.getMoney() - moneyToPay);
                System.out.println("thanh toán thành công");
                int role = 0;
                for (int i = 0; i < cartManagement.getCartList().size(); i++) {
                    for (int j = 0; j < productManagement.getProductList().size(); j++) {
                        if (productManagement.getProductList().get(j).getIdProduct().equals(cartManagement.getCartList().get(i).getProduct().getIdProduct())) {
                            role++;
                            productManagement.getProductList().get(j).setSold(role);
                        }
                    }
                }
                cartManagement.removeAllProduct();
            } else {
                System.out.println("Số tiền không đủ - bạn hãy nạp thêm tiền đi");
            }
        }
    }

    private void displayProductInCart() {
        cartManagement.showAll();
    }

    private void addProductToCart() {
        System.out.println("Bạn muốn thêm sản phẩm nào vào giỏ hàng");
        System.out.println("Nhập mã và tên sản phẩm mà bạn muốn thêm vào");
        System.out.print("Nhập mã sản phẩm: ");
        String idProduct = scanner.nextLine();
        System.out.print("Nhập tên sản phẩm: ");
        String nameProduct = scanner.nextLine();
        System.out.print("Nhập số lượng: ");
        int amount = scanner.nextInt();
        scanner.nextLine();
        int indexId = productManagement.findById(idProduct);
        int indexName = productManagement.findByName(nameProduct);
        if (indexId != -1 && indexName != -1) {
            Product product = productManagement.getProductList().get(indexId);
            product.setAmount(amount);
            cartManagement.addNew(new Cart(product));
            System.out.println("Thêm vào giỏ hàng thành công");
        } else {
            System.out.println("Sản phẩm mà bạn cần thêm k có trong danh sách sản phẩm");
        }
    }

    private void sortProductByPrice() {
        productManagement.sortProductByPrice();
        productManagement.showAll();
    }

    private void statisticalProductByCategory() {
        if (categoryManagement.getCategoryList().isEmpty()) {
            System.out.println("Danh mục của bạn đang trống");
            return;
        }
        System.out.print("Nhập vào danh mục cần thống kê: ");
        String nameCategory = scanner.nextLine();
        int count = categoryManagement.ProductByCategory(nameCategory);
        System.out.println("Danh mục " + nameCategory + " có: " + count + " sản phẩm");
    }

    private void searchProductByName() {
        System.out.print("Nhập vào tên sản phầm bạn muốn tìm kiếm: ");
        String productName = scanner.nextLine();
        int index = productManagement.findByName(productName);
        if (index != -1) {
            System.out.println("Sản phẩm mà bạn cần tìm");
            System.out.println(productManagement.getProductList().get(index));
        } else {
            System.out.println("Không có sản phẩm nào");
        }

    }

    private void filterListProductByPrice() {
        String choice;
        do {
            System.out.println("Lọc danh sách sản phẩm");
            System.out.println("1. Lọc danh sách sản phẩm có giá trên 1000 USD");
            System.out.println("2. Lọc danh sách sản phẩm có giá dưới 1000 USD");
            System.out.println("0. Quay lại");
            System.out.print("Nhập lựa chọn: ");
            choice = scanner.nextLine();
            switch (choice) {
                case "1": {
                    productManagement.fillListProductThan1000();
                    break;
                }
                case "2": {
                    productManagement.fillListProductLess1000();
                    break;
                }
                default: {
                    System.out.println("Nhập lại đi");
                }
            }
        } while (!choice.equals("0"));

    }

    private void inputMoneyToAccount() {
        System.out.println("Bạn muốn nạp thêm bao nhiêu tiền vào tài khoản");
        System.out.print("Nhập số tiền cần nạp: ");
        double addMoney = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Nhập mật khẩu xác thực: ");
        String passWordAuthentication = scanner.nextLine();
        Account account = accountManagement.showAccountLogin(MainLogin.idAccount);
        if (account != null) {
            if (passWordAuthentication.equals(account.getPassWord())) {
                account.addMoney(addMoney);
            }
        }
    }

    private void accountInformation() {
        Account account = accountManagement.showAccountLogin(MainLogin.idAccount);
        if (account != null) {
            System.out.println(account);
        }
    }

}
