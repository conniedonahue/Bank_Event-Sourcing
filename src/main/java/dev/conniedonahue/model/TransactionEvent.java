package dev.conniedonahue.model;

public class TransactionEvent {
  private final String userId;
  private final String messageId;
  private final Amount transactionAmount;
  private final String postTransactionBalance;
  private final long timestamp;
  private final ResponseCode responseCode;

  // Constructor
  public TransactionEvent(String userId, String messageId, Amount transactionAmount, ResponseCode responseCode, String postTransactionBalance, long timestamp) {
    this.userId = userId;
    this.messageId = messageId;
    this.transactionAmount = transactionAmount;
    this.responseCode = responseCode;
    this.postTransactionBalance = postTransactionBalance;
    this.timestamp = timestamp;
  }

  // Getters
  public String getUserId() {
    return userId;
  }
  public String getMessageId() {return  messageId;}
  public Amount getTransactionAmount() {
    return transactionAmount;
  }
  public ResponseCode getResponseCode() {return responseCode;}
  public String getPostTransactionBalance() {
    return postTransactionBalance;
  }
  public long getTimestamp() {
    return timestamp;
  }
}


