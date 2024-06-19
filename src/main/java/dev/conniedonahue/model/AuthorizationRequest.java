package dev.conniedonahue.model;

public class AuthorizationRequest {
    private String userId;
    private String messageId;
    private Amount transactionAmount;

    // Default no-arg constructor for Jackson deserialization
    public AuthorizationRequest(){}

    // Constructor
    public AuthorizationRequest(String userId, String messageId, Amount transactionAmount) {
        this.userId = userId;
        this.messageId = messageId;
        this.transactionAmount = transactionAmount;
    }

    // Getters and Setters
    public String getUserId() {
        return userId;
    }

    public String getMessageId() {
        return messageId;
    }

    public Amount getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(Amount transactionAmount) {
        this.transactionAmount = transactionAmount;
    }
}
