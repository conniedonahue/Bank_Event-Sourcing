package dev.conniedonahue;

import dev.conniedonahue.controller.TransactionController;
import dev.conniedonahue.service.*;
import dev.conniedonahue.service.TransactionService;
import dev.conniedonahue.service.TransactionServiceImpl;
import io.javalin.Javalin;

public class Main {

    public static void main(String[] args) {
        // Creates server listening on port 3000
        Javalin app = Javalin.create().start(3000);

        // Initializes Services and Controller
        BankLedger bankLedger = new BankLedger();
        AccountManager accountManager = new AccountManager();
        TransactionService transactionService = new TransactionServiceImpl(bankLedger, accountManager);
        TransactionController transactionController = new TransactionController(transactionService);

        // Router:
        app.get("/api/ping", transactionController.ping());
        app.put("/api/authorization/{messageId}", transactionController.authorizeTransaction());
        app.put("/api/load/{messageId}", transactionController.loadFunds());

        // Route for handling PUT requests to /api/load/ without messageId param
        app.put("/api/load", transactionController.handleMissingMessageId());
        app.put("/api/authorization", transactionController.handleMissingMessageId());

        app.error(404, ctx -> {
            ctx.status(404).result("404: Not Found");
        });
    }

}
