package dev.conniedonahue.model;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class LoadRequestTest {

    @Test
    public void testLoadRequestConstructorAndGetters() {

        String userId = "validUserId";
        String messageId = "validMessageId";
        Amount tranactionAmount = new Amount("300", "USD", DebitCredit.DEBIT);

        LoadRequest loadRequest = new LoadRequest (userId, messageId, tranactionAmount);

        assertEquals(userId, loadRequest.getUserId());
        assertEquals(messageId, loadRequest.getMessageId());
        assertEquals(tranactionAmount, loadRequest.getTransactionAmount());
    }

    @Test
    public void testLoadRequestSetters() {
        Amount transactionAmount = new Amount("300", "USD", DebitCredit.DEBIT);
        Amount newTransactionAmount = new Amount("400", "USD", DebitCredit.DEBIT);

        LoadRequest loadRequest = new LoadRequest("validUserId", "validMessageId", transactionAmount);

        loadRequest.setTransactionAmount(newTransactionAmount);

        assertEquals(newTransactionAmount, loadRequest.getTransactionAmount());
    }
}

