package dev.conniedonahue.service;
import dev.conniedonahue.model.TransactionEvent;
import dev.conniedonahue.model.Account;
import dev.conniedonahue.model.Amount;
import dev.conniedonahue.model.ResponseCode;

import java.util.ArrayList;
import java.util.List;

// Note: This is our Event Store
public class BankLedger {
    private final List<TransactionEvent> transactions;

    // Constructor
    public BankLedger() {
        this.transactions = new ArrayList<>();
    }


    // Synchronized methods to preserve atomicity and order of event log
    public final synchronized void recordDeposit(Account account, String messageId, Amount transactionAmount, long timestamp) {
        // Update account balance:
        Double depositResult = Double.parseDouble(account.getAccountBalance()) + Double.parseDouble(transactionAmount.getAmount());
        String depositResultString = String.format("%.2f", depositResult);
        account.setAccountBalance(depositResultString);

        String postTransactionBalance = account.getAccountBalance();

        // Records as transactionEvent
        TransactionEvent event = new TransactionEvent(account.getUserId(), messageId, transactionAmount, ResponseCode.APPROVED, postTransactionBalance, timestamp);
        transactions.add(event);
    }

    public final synchronized void recordWithdrawal(Account account, String messageId, Amount transactionAmount, long timestamp, ResponseCode responseCode) {

        // Updates account balances for APPROVED requests
        if (responseCode == ResponseCode.APPROVED){
            Double withdrawalResult = Double.parseDouble(account.getAccountBalance()) - Double.parseDouble(transactionAmount.getAmount());
            String withdrawalResultString = String.format("%.2f", withdrawalResult);
            account.setAccountBalance(withdrawalResultString);
        }

        String postTransactionBalance = account.getAccountBalance();

        // Records as transactionEvent -- even DENIED withdrawal requests.
        TransactionEvent event = new TransactionEvent(account.getUserId(), messageId, transactionAmount, responseCode, postTransactionBalance, timestamp);
        transactions.add(event);
    }
}



