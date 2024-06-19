package dev.conniedonahue.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AccountTest {
    @Test
    public void testAccountConstructorAndGetters() {

        String userId = "User235551";
        String accountBalance = "200.00";

        Account account = new Account(userId, accountBalance);

        assertEquals(userId, account.getUserId());
        assertEquals(accountBalance, account.getAccountBalance());
    }

    @Test
    public void testAccountSetters() {
        String newAccountBalance = "300.00";
        Account newAccount = new Account("User233313", "200.00");

        newAccount.setAccountBalance("300.00");

        assertEquals(newAccountBalance, newAccount.getAccountBalance());
    }
}
