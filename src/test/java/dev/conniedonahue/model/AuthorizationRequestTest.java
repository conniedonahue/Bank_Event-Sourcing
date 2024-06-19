package dev.conniedonahue.model;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class AuthorizationRequestTest {

    @Test
    public void testAuthorizationRequestConstructorAndGetters() {

        String userId = "validUserId";
        String messageId = "validMessageId";
        Amount transactionAmount = new Amount("300", "USD", DebitCredit.DEBIT);

        AuthorizationRequest authorizationRequest = new AuthorizationRequest(userId, messageId, transactionAmount);

        assertEquals(userId, authorizationRequest.getUserId());
        assertEquals(messageId, authorizationRequest.getMessageId());
        assertEquals(transactionAmount, authorizationRequest.getTransactionAmount());
    }

    @Test
    public void testAuthorizationRequestSetters() {
        Amount transactionAmount = new Amount("300", "USD", DebitCredit.DEBIT);
        Amount newTransactionAmount = new Amount("400", "USD", DebitCredit.DEBIT);

        AuthorizationRequest authorizationRequest = new AuthorizationRequest("validUserId", "validMessageId", transactionAmount);

        authorizationRequest.setTransactionAmount(newTransactionAmount);

        assertEquals(newTransactionAmount, authorizationRequest.getTransactionAmount());
    }
}

