package com.codegym;

import com.codegym.controller.AccountManagement;
import com.codegym.controller.CartManagement;
import com.codegym.controller.CategoryManagement;
import com.codegym.controller.ProductManagement;
import com.codegym.view.MainLogin;

import java.util.Scanner;

public class Main {

    public static Scanner scanner = new Scanner(System.in);
    public static AccountManagement accountManagement = new AccountManagement();
    public static CategoryManagement categoryManagement = new CategoryManagement();
    public static ProductManagement productManagement = new ProductManagement();
    public static CartManagement cartManagement = new CartManagement();

    public static void main(String[] args) {
        MainLogin mainLogin = new MainLogin();
        mainLogin.run();
    }

}
