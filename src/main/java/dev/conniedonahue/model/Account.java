package dev.conniedonahue.model;

public class Account {
    private final String userId;
    private String accountBalance;

    // Constructor
    public Account(String userId, String accountBalance) {
        this.userId = userId;
        this.accountBalance = accountBalance;
    }

    // Getters and Setters
    public String getUserId() {
        return userId;
    }

    public String getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(String newBalance) {
        accountBalance = newBalance;
    }
}
