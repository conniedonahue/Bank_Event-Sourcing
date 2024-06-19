package dev.conniedonahue.model;

public class AuthorizationResponse {
    private final String userId;
    private final String messageId;
    private final ResponseCode responseCode;
    private final Amount balance;

    // Constructor
    public AuthorizationResponse(String userId, String messageId, ResponseCode responseCode, Amount balance) {
        this.userId = userId;
        this.messageId = messageId;
        this.responseCode = responseCode;
        this.balance = balance;
    }

    // Getters
    public String getUserId() {
        return userId;
    }

    public String getMessageId() {
        return messageId;
    }

    public ResponseCode getResponseCode() {
        return responseCode;
    }

    public Amount getBalance() {
        return balance;
    }
}
