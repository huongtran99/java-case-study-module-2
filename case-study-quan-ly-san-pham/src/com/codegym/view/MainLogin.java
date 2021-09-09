package com.codegym.view;

import com.codegym.model.Account;

import static com.codegym.Main.*;

public class MainLogin {

    static {
        accountManagement.addNew(new Account("1", "huongtran", "1", "user", 0));
        accountManagement.addNew(new Account("2", "admin", "123", "admin"));
    }

    public static String idAccount;

    public void run() {
        String choice;
        do {
            menuLogin();
            System.out.print("Nhập lựa chọn của bạn: ");
            choice = scanner.nextLine();
            switch (choice) {
                case "1": {
                    login();
                    break;
                }
                case "2": {
                    register();
                    break;
                }
                case "0": {
                    System.out.println("Bye bạn nhớ");
                    break;
                }
                default: {
                    System.out.println("Bạn nhập sai rồi, vui lòng nhập lại");
                }
            }
        } while (!choice.equals("0"));
    }

    public void login() {
        idAccount = findByIdAccount();
        UserMenu userMenu = new UserMenu();
        ManageMenu manageMenu = new ManageMenu();
        if (!idAccount.equals("empty")) {
            int index = accountManagement.findById(idAccount);
            if (accountManagement.getAccounts().get(index).getRole().equals("admin")) {
                System.out.print("---Đăng nhập thành công---");
                System.out.println("       Xin chào " + accountManagement.getAccounts().get(index).getUserName());
                manageMenu.showAdminMenu();
            } else {
                System.out.print("---Đăng nhập thành công---");
                System.out.println("       Xin chào " + accountManagement.getAccounts().get(index).getUserName());
                userMenu.showUserMenu();
            }
        } else {
            System.out.println("Tên đăng nhập hoặc mật khẩu không đúng");
        }
    }

    public String findByIdAccount() {
        idAccount = "empty";
        Account account = inputAccountLogin();
        Account acc = accountManagement.checkAccount(account);
        if (acc != null) {
            idAccount = acc.getId();
        }
        return idAccount;
    }

    public Account inputAccountLogin() {
        System.out.println("----Đăng Nhập----");
        System.out.print("Nhập tên đăng nhập: ");
        String userName = scanner.nextLine();
        System.out.print("Nhập mật khẩu: ");
        String passWord = scanner.nextLine();
        return new Account(userName, passWord);
    }

    public void register() {
        System.out.println("----Đăng Ký----");
        Account account = inputAccount();
        int indexName = accountManagement.findByName(account.getUserName());
        int indexId = accountManagement.findById(account.getId());
        if (indexId != -1 || indexName != -1) {
            System.out.println("Tên đăng nhập hoặc id đã tồn tại mời bạn dùng tên đăng nhập hoặc id khác");
        } else {
            accountManagement.addNew(account);
            System.out.println("Đăng ký tài khoản thành công");
        }
    }

    public Account inputAccount() {
        System.out.print("Nhập id: ");
        String id = scanner.nextLine();
        System.out.print("Nhập tên đăng nhập: ");
        String userName = scanner.nextLine();
        String passWord, retype;
        do {
            System.out.print("Nhập mật khẩu: ");
            passWord = scanner.nextLine();
            System.out.println("Nhập lại mật khẩu của bạn: ");
            retype = scanner.nextLine();
            if (!passWord.equals(retype)) {
                System.out.println("không khớp !!");
            }
        } while (!passWord.equals(retype));
        String role = "user";
        return new Account(id, userName, passWord, role);
    }

    public void menuLogin() {
        System.out.println("------Xin Chào------");
        System.out.println("1. Đăng Nhập");
        System.out.println("2. Đăng Kí");
        System.out.println("0. Thoát");
    }

}
