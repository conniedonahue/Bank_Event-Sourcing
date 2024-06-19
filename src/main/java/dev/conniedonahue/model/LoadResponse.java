package dev.conniedonahue.model;


public class LoadResponse {
    private final String userId;
    private final String messageId;
    private final Amount balance;

    // Constructor
    public LoadResponse(String userId, String messageId, Amount balance) {
        this.userId = userId;
        this.messageId = messageId;
        this.balance = balance;
    }

    // Getters
    public String getUserId() {
        return userId;
    }

    public String getMessageId() {
        return messageId;
    }

    public Amount getBalance() {
        return balance;
    }
}