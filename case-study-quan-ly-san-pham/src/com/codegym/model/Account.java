package com.codegym.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Account {
    private String id;
    private String userName;
    private String passWord;
    private String role;
    private double money;

    private static final String ACCOUNT_REGEX = "^[_a-z0-9]{6,}$";

    public boolean validate(String regex) {
        Pattern pattern = Pattern.compile(ACCOUNT_REGEX);
        Matcher matcher = pattern.matcher(regex);
        return matcher.matches();
    }

    public Account() {

    }

    public Account(String userName, String passWord) {
        this.userName = userName;
        this.passWord = passWord;
    }

    public Account(String id, String userName, String passWord, String role) {
        this.id = id;
        this.userName = userName;
        this.passWord = passWord;
        this.role = role;
    }

    public Account(String id, String userName, String passWord, String role, double money) {
        this.id = id;
        this.userName = userName;
        this.passWord = passWord;
        this.role = role;
        this.money = money;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public void addMoney(double money) {
        this.money += money;
    }

    @Override
    public String toString() {
        return "account information: " + userName + "\n" +
                "id= " + id + ", userName= " + userName + ", role= " + role + ", money= " + money + " USD";
    }
}
