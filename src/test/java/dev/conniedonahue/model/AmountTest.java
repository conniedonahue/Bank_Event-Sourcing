package dev.conniedonahue.model;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class AmountTest {

    @Test
    public void testAmountConstructorAndGetters() {

        String amountValue = "100.00";
        String currencyValue = "USD";
        DebitCredit debitCreditValue = DebitCredit.DEBIT;

        Amount amount = new Amount(amountValue, currencyValue, debitCreditValue);

        assertEquals(amountValue, amount.getAmount());
        assertEquals(currencyValue, amount.getCurrency());
        assertEquals(debitCreditValue, amount.getDebitOrCredit());
    }

    @Test
    public void testAmountSetters() {
        String amountValue = "200.00";
        String currencyValue = "EUR";
        DebitCredit debitCreditValue = DebitCredit.CREDIT;

        Amount newAmount = new Amount("10.00", "USD", DebitCredit.DEBIT);
        newAmount.setAmount(amountValue);
        newAmount.setCurrency(currencyValue);
        newAmount.setDebitOrCredit(debitCreditValue);

        assertEquals(amountValue, newAmount.getAmount());
        assertEquals(currencyValue, newAmount.getCurrency());
        assertEquals(debitCreditValue, newAmount.getDebitOrCredit());
    }
}

