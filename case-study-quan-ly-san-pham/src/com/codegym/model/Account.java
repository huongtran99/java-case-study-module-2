package com.codegym.model;

public class Account {
    private String id;
    private String userName;
    private String passWord;
    private String role;
    private double money;

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

    public void addMoney (double money){
        this.money += money;
    }

    @Override
    public String toString() {
        return "account information: " + userName + "\n" +
               "id= " + id + ", userName= " + userName + ", passWord= " + passWord + ", role= " + role + ", money= " + money + " USD";
    }
}
