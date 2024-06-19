package dev.conniedonahue.model;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class AuthorizationResponseTest {

    @Test
    public void testAuthorizationResponseConstructorAndGetters() {

        String userId = "validUserId";
        String messageId = "validMessageId";
        ResponseCode responseCode = ResponseCode.APPROVED;
        Amount balance = new Amount("300", "USD", DebitCredit.DEBIT);

        AuthorizationResponse authorizationResponse = new AuthorizationResponse (userId, messageId, responseCode, balance);

        assertEquals(userId, authorizationResponse.getUserId());
        assertEquals(messageId, authorizationResponse.getMessageId());
        assertEquals(balance, authorizationResponse.getBalance());
    }
}

