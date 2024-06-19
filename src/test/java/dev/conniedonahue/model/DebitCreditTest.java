package dev.conniedonahue.model;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class DebitCreditTest {

    @Test
    public void testDebitCreditEnum() {

        assertEquals("DEBIT", DebitCredit.DEBIT.toString());
        assertEquals(DebitCredit.DEBIT, DebitCredit.valueOf("DEBIT"));

        assertEquals("CREDIT", DebitCredit.CREDIT.toString());
        assertEquals(DebitCredit.CREDIT, DebitCredit.valueOf("CREDIT"));
    }
}
