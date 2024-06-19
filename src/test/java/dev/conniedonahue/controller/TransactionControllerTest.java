package dev.conniedonahue.controller;
import dev.conniedonahue.model.Error;
import dev.conniedonahue.service.TransactionService;
import dev.conniedonahue.model.*;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import io.javalin.http.HttpStatus;
import org.junit.Test;
import org.junit.Before;
import org.mockito.ArgumentCaptor;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class TransactionControllerTest {

    private TransactionController testTransactionController;




    @Before
    public void setUp() {
        TransactionService transactionServiceMock = mock(TransactionService.class);
        testTransactionController = new TransactionController(transactionServiceMock);
    }

    @Test
    public void testAuthorizeTransaction_validAmountFormat() throws Exception {
        Context ctx = mock(Context.class);

        // Setting up our valid Amount
        Amount validAmount = mock(Amount.class);
        when(validAmount.getAmount()).thenReturn("20.00");
        when(validAmount.getCurrency()).thenReturn("USD");
        when(validAmount.getDebitOrCredit()).thenReturn(DebitCredit.DEBIT);

        // Setting up our mockRequest
        AuthorizationRequest mockRequest = mock(AuthorizationRequest.class);
        when(mockRequest.getTransactionAmount()).thenReturn(validAmount);
        when(ctx.bodyAsClass(AuthorizationRequest.class)).thenReturn(mockRequest);
        when(mockRequest.getMessageId()).thenReturn("validMessageId");
        when(ctx.pathParam("messageId")).thenReturn("validMessageId");

        // Passing mockRequest through handler
        Handler handler = testTransactionController.authorizeTransaction();
        handler.handle(ctx);

        verify(ctx).status(201);
    }

    @Test
    public void testAuthorizeTransaction_InvalidAmountFormat_AMOUNT() throws Exception {
        Context ctx = mock(Context.class);

        // Setting up our invalid Amount
        Amount invalidAmount = mock(Amount.class);
        when(invalidAmount.getAmount()).thenReturn("20");
        when(invalidAmount.getCurrency()).thenReturn("USD");
        when(invalidAmount.getDebitOrCredit()).thenReturn(DebitCredit.DEBIT);

        // Setting up our mockRequest
        AuthorizationRequest mockRequest = mock(AuthorizationRequest.class);
        when(mockRequest.getTransactionAmount()).thenReturn(invalidAmount);
        when(ctx.bodyAsClass(AuthorizationRequest.class)).thenReturn(mockRequest);
        when(ctx.status()).thenReturn(HttpStatus.forStatus(400));
        when(mockRequest.getMessageId()).thenReturn("validMessageId");
        when(ctx.pathParam("messageId")).thenReturn("validMessageId");

        // Passing mockRequest through handler
        Handler handler = testTransactionController.authorizeTransaction();
        handler.handle(ctx);

        // Defining expectedError
        Error expectedError = new Error("Error: amount format is invalid. Must be a String in currency format.", "400");

        // Capturing the actual Error created from method
        ArgumentCaptor<Error> errorCaptor = ArgumentCaptor.forClass(Error.class);
        verify(ctx).json(errorCaptor.capture());

        // Comparing expected and actual values
        Error actualError = errorCaptor.getValue();
        assertEquals(expectedError.getMessage(), actualError.getMessage());
        verify(ctx).status(400);
    }
    @Test
    public void testAuthorizeTransaction_InvalidAmountFormat_DEBITCREDIT() throws Exception {
        Context ctx = mock(Context.class);

        // Setting up our invalid Amount
        Amount invalidAmount = mock(Amount.class);
        when(invalidAmount.getAmount()).thenReturn("20.00");
        when(invalidAmount.getCurrency()).thenReturn("USD");
        when(invalidAmount.getDebitOrCredit()).thenReturn(DebitCredit.CREDIT);

        // Setting up our mockRequest
        AuthorizationRequest mockRequest = mock(AuthorizationRequest.class);
        when(mockRequest.getTransactionAmount()).thenReturn(invalidAmount);
        when(ctx.bodyAsClass(AuthorizationRequest.class)).thenReturn(mockRequest);
        when(ctx.status()).thenReturn(HttpStatus.forStatus(400));
        when(mockRequest.getMessageId()).thenReturn("validMessageId");
        when(ctx.pathParam("messageId")).thenReturn("validMessageId");

        // Passing mockRequest through handler
        Handler handler = testTransactionController.authorizeTransaction();
        handler.handle(ctx);

        // Defining expectedError
        Error expectedError = new Error("Error: authorization requests must be set to DEBIT", "400");

        // Capturing the new Error created from method
        ArgumentCaptor<Error> errorCaptor = ArgumentCaptor.forClass(Error.class);
        verify(ctx).json(errorCaptor.capture());

        // Comparing expected and actual values
        Error actualError = errorCaptor.getValue();
        assertEquals(expectedError.getMessage(), actualError.getMessage());
        verify(ctx).status(400);
    }

    @Test
    public void PUT_to_load_or_authorization_gives_400_if_missing_params() throws Exception{
        Context ctx = mock(Context.class);

        // Setting up our mockRequest
        LoadRequest mockRequest = mock(LoadRequest.class);

        when(ctx.status()).thenReturn(HttpStatus.forStatus(400));

        // Passing mockRequest through handler
        Handler handler = testTransactionController.handleMissingMessageId();
        handler.handle(ctx);

        // Defining expectedError
        Error expectedError = new Error("Error: Missing messageId parameter", "400");

        // Capturing the new Error created from method
        ArgumentCaptor<Error> errorCaptor = ArgumentCaptor.forClass(Error.class);
        verify(ctx).json(errorCaptor.capture());

        // Comparing expected and actual values
        Error actualError = errorCaptor.getValue();
        assertEquals(expectedError.getMessage(), actualError.getMessage());

        verify(ctx).status(400);
    }

}
