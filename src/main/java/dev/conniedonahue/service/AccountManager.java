package dev.conniedonahue.service;

import dev.conniedonahue.model.Account;

import java.util.HashMap;

public class AccountManager {
    private final HashMap<String, Account> accounts;

    public AccountManager() {
        this.accounts = new HashMap<>();
    }

    public synchronized Account createAccount(String userId, String initialBalance) {
        accounts.put(userId, new Account(userId, initialBalance));
        return accounts.get(userId);
    }

    public Account getAccount(String userId) {
        return accounts.get(userId);
    }

//    public synchronized void depositToAccount(Account account, String amount) {
//        Double depositResult = Double.parseDouble(account.getAccountBalance()) + Double.parseDouble(amount);
//        account.setAccountBalance(String.format("%.2f", depositResult));
//
//    }
//
//    public synchronized void withdrawFromAccount(Account account, String amount) {
//        Double withdrawalResult = Double.parseDouble(account.getAccountBalance()) - Double.parseDouble(amount);
//        account.setAccountBalance(String.format("%.2f", withdrawalResult));
//
//    }
    // should throw Error
    public synchronized String getAccountBalance(String accountId) {
        Account account = accounts.get(accountId);
        return account != null ? account.getAccountBalance() : "0.00";
    }
}

