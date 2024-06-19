package dev.conniedonahue.controller;

import dev.conniedonahue.model.*;
import dev.conniedonahue.model.Error;
import dev.conniedonahue.service.*;
import io.javalin.http.Handler;

import java.util.HashSet;
import java.util.Set;

public class TransactionController {

    private final TransactionService transactionService;
    private final Set<String> messageIdSet;


    // Constructor
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
        this.messageIdSet = new HashSet<>();
    }


    // PING
    // Tests the availability of the service and returns the current server time.
    public Handler ping() {
        return ctx -> {
            try{
                ctx.json(new Ping());
            } catch (Exception e){
                ctx.status(500);
                ctx.json(new Error("An unexpected error occurred. ERROR:" + e.getMessage()));
            }

        };
    }

    // AUTHORIZE (WITHDRAW)
    // Removes funds from a user's account if sufficient funds are available.
    public Handler authorizeTransaction(){
        return ctx -> {
            try{
                AuthorizationRequest request = ctx.bodyAsClass(AuthorizationRequest.class);
                String messageId = request.getMessageId();
                if (!messageId.equals(ctx.pathParam("messageId"))) {
                    ctx.status(400);
                    ctx.json(new Error("Error: messageId param doesn't match body. Please try again.", ctx.status().toString()));
                    return;
                }
                if (messageIdSet.contains(messageId)) {
                    ctx.status(400);
                    ctx.json(new Error("Error: messageId already exists in our records. Please try again.", ctx.status().toString()));
                    return;
                }
                messageIdSet.add(request.getMessageId());

                if (isAmountFormatInvalid(request.getTransactionAmount().getAmount())) {
                    ctx.status(400);
                    ctx.json(new Error("Error: amount format is invalid. Must be a String in currency format.", ctx.status().toString()));
                } else if(request.getTransactionAmount().getCurrency() == null || request.getTransactionAmount().getCurrency().isEmpty()){
                    ctx.status(400);
                    ctx.json(new Error("Error: currency format is invalid. Must be a String with a min length of 1.", ctx.status().toString()));
                } else if (request.getTransactionAmount().getDebitOrCredit() == DebitCredit.CREDIT){
                    ctx.status(400);
                    ctx.json(new Error("Error: authorization requests must be set to DEBIT", ctx.status().toString()));
                } else {
                    AuthorizationResponse response = transactionService.authorizeTransaction(request);
                    ctx.status(201);
                    ctx.json(response);
                }
            } catch (Exception e){
                ctx.status(500);
                ctx.json(new Error("An unexpected error occurred. ERROR:" + e.getMessage()));
            }
        };
    }

    // LOAD (DEPOSIT)
    // Adds funds to a user's account.
    public Handler loadFunds(){
        return ctx -> {
            try{
                LoadRequest request = ctx.bodyAsClass(LoadRequest.class);
                String messageId = request.getMessageId();
                if (!messageId.equals(ctx.pathParam("messageId"))) {
                    ctx.status(400);
                    ctx.json(new Error("Error: messageId param doesn't match body. Please try again.", ctx.status().toString()));
                    return;
                }

                if (messageIdSet.contains(request.getMessageId())) {
                    ctx.status(400);
                    ctx.json(new Error("Error: messageId already exists in our records. Please try again.", ctx.status().toString()));
                    return;
                }
                messageIdSet.add(request.getMessageId());

                if (isAmountFormatInvalid(request.getTransactionAmount().getAmount())){
                    ctx.status(400);
                    ctx.json(new Error("Error: amount format is invalid. Must be a String in currency format.", ctx.status().toString()));
                } else if(request.getTransactionAmount().getCurrency() == null || request.getTransactionAmount().getCurrency().isEmpty()){
                    ctx.status(400);
                    ctx.json(new Error("Error: currency format is invalid. Must be a String with a min length of 1.", ctx.status().toString()));
                } else if (request.getTransactionAmount().getDebitOrCredit() == DebitCredit.DEBIT){
                    ctx.status(400);
                    ctx.json(new Error("Error: load requests must be set to CREDIT", ctx.status().toString()));
                } else{
                    LoadResponse response = transactionService.loadFunds(request);
                    ctx.status(201);
                    ctx.json(response);
                }
            } catch(Exception e){
                ctx.status(500);
                ctx.json(new Error("An unexpected error occurred. ERROR:" + e.getMessage(), ctx.status().toString()));
            }
        };
    }

    // ERROR HANDLING

    // Sends an error if messageId not included in request param
    public Handler handleMissingMessageId (){
       return ctx -> {
           ctx.status(400);
           ctx.json(new Error("Error: Missing messageId parameter", ctx.status().toString()));
       };
    }

    // Checks to see if transaction amount formatting is invalid, i.e. not in currency format
    private boolean isAmountFormatInvalid (String amount) {
        return !amount.matches("\\d+\\.\\d{2}");
    }

}