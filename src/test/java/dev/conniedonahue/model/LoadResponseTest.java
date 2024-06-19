package dev.conniedonahue.model;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class LoadResponseTest {

    @Test
    public void testLoadResponseConstructorAndGetters() {

        String userId = "validUserId";
        String messageId = "validMessageId";
        Amount balance = new Amount("300", "USD", DebitCredit.CREDIT);

        LoadResponse loadResponse = new LoadResponse (userId, messageId, balance);

        assertEquals(userId, loadResponse.getUserId());
        assertEquals(messageId, loadResponse.getMessageId());
        assertEquals(balance, loadResponse.getBalance());
    }
}

