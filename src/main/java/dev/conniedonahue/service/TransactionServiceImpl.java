package dev.conniedonahue.service;

import dev.conniedonahue.model.*;


public class TransactionServiceImpl implements TransactionService {

    private final BankLedger bankLedger;
    private final AccountManager accountManager;

    public TransactionServiceImpl(BankLedger bankLedger, AccountManager accountManager) {
        this.bankLedger = bankLedger;
        this.accountManager = accountManager;
    }

    /*
    AuthorizeTransaction (WITHDRAW)
    */

    @Override
    public AuthorizationResponse authorizeTransaction(AuthorizationRequest request) {
        // Ingest request
        Amount transactionAmount = request.getTransactionAmount();
        double transactionAmountDouble = Double.parseDouble(transactionAmount.getAmount());
        Account account = accountManager.getAccount(request.getUserId());

        // Assess validity
        if (account == null) {
            account = accountManager.createAccount(request.getUserId(), "0.00");
        }
        double accountBalance = Double.parseDouble(account.getAccountBalance());
        ResponseCode responseCode = (accountBalance >= transactionAmountDouble) ? ResponseCode.APPROVED : ResponseCode.DECLINED;

        // Forward to BankLedger for processing
        bankLedger.recordWithdrawal(account, request.getMessageId(), transactionAmount, System.currentTimeMillis(), responseCode);

        // Produce and return Response object
        String postTransactionBalance = account.getAccountBalance();
        Amount balance = new Amount(postTransactionBalance, transactionAmount.getCurrency(),transactionAmount.getDebitOrCredit());

        return new AuthorizationResponse(request.getUserId(), request.getMessageId(), responseCode, balance);
    }

     /*
    loadFunds (DEPOSIT)
    */

    @Override
    public LoadResponse loadFunds(LoadRequest request) {
        // Ingest request
        Amount transactionAmount = request.getTransactionAmount();
        Account account = accountManager.getAccount(request.getUserId());
        String messageId = request.getMessageId();

        // Assess validity
        if (account == null) {
            account = accountManager.createAccount(request.getUserId(), "0.00");
        }


        // Forward to BankLedger for processing
        bankLedger.recordDeposit(account, messageId, transactionAmount, System.currentTimeMillis());

        // Produce and return Response object
        String postTransactionBalance = account.getAccountBalance();
        Amount balance = new Amount(postTransactionBalance, transactionAmount.getCurrency(),transactionAmount.getDebitOrCredit());

        return new LoadResponse(request.getUserId(), messageId, balance);

    }
}

