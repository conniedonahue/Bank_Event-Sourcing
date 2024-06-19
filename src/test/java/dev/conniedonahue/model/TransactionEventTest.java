package dev.conniedonahue.model;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TransactionEventTest {

    @Test
    public void testTransactionEventConstructorAndGetters() {

        String userId = "validUserId";
        String messageId = "validMessageId";
        Amount transactionAmount = new Amount("300", "USD", DebitCredit.DEBIT);
        ResponseCode responseCode = ResponseCode.APPROVED;
        String postTransactionBalance = "400";
        long timestamp = System.currentTimeMillis();

        TransactionEvent transactionEvent = new TransactionEvent (userId, messageId, transactionAmount, responseCode, postTransactionBalance, timestamp);

        assertEquals(userId, transactionEvent.getUserId());
        assertEquals(messageId, transactionEvent.getMessageId());
        assertEquals(transactionAmount, transactionEvent.getTransactionAmount());
        assertEquals(responseCode, transactionEvent.getResponseCode());
        assertEquals(postTransactionBalance, transactionEvent.getPostTransactionBalance());
        assertEquals(timestamp, transactionEvent.getTimestamp());
    }
}

