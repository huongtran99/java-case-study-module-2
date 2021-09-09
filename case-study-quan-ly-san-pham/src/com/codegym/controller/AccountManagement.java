package com.codegym.controller;

import com.codegym.model.Account;

import java.util.ArrayList;
import java.util.List;

public class AccountManagement implements IGeneralManagement<Account> {
    List<Account> accounts = new ArrayList<>();

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    @Override
    public void showAll() {
        if(accounts.isEmpty()) {
            System.out.println("hiện chưa có tài khoản");
        }
        for (Account account : accounts) {
            System.out.println(account);
        }
    }

    public Account showAccountLogin(String idAccount) {
        if(accounts.isEmpty()) {
            return null;
        }
        for (Account account : accounts) {
            if (account.getId().equals(idAccount)) {
                return account;
            }
        }
        return null;
    }

    @Override
    public void addNew(Account account) {
        accounts.add(account);
    }

    @Override
    public void updateById(String id, Account account) {
        int index = findById(id);
        accounts.set(index, account);
    }

    @Override
    public void removeById(String id) {
        int index = findById(id);
        accounts.remove(index);
    }

    @Override
    public int findById(String id) {
        int index = -1;
        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).getId().equals(id)) {
                index = i;
                break;
            }
        }
        return index;
    }

    public int findByName(String name) {
        int index = -1;
        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).getUserName().equals(name)) {
                index = i;
                break;
            }
        }
        return index;
    }

    public Account checkAccount(Account accountInput) {
        for (Account account : accounts) {
            if (accountInput.getUserName().equals(account.getUserName()) && accountInput.getPassWord().equals(account.getPassWord())) {
                return account;
            }
        }
        return null;
    }

}
