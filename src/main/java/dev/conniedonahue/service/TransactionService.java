package dev.conniedonahue.service;
import dev.conniedonahue.model.*;

public interface TransactionService {
    AuthorizationResponse authorizeTransaction(AuthorizationRequest request);
    LoadResponse loadFunds(LoadRequest request);
}

