package dev.conniedonahue.model;

public class Amount {
    private String amount; // in the denomination of the currency, e.g. $1 = '1.00'
    private String currency;
    private DebitCredit debitOrCredit;

    // Default no-arg constructor for Jackson deserialization
    public Amount() {
    }

    // Constructor
    public Amount(String amount, String currency, DebitCredit debitOrCredit) {
        this.amount = amount;
        this.currency = currency;
        this.debitOrCredit = debitOrCredit;
    }


    // Getters and Setters
    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public DebitCredit getDebitOrCredit() {
        return debitOrCredit;
    }

    public void setDebitOrCredit(DebitCredit debitOrCredit) {
        this.debitOrCredit = debitOrCredit;
    }
}
