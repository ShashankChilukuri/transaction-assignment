package com.example.transactionstarter.Tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.transactionstarter.models.Response;
import com.example.transactionstarter.models.Transactions;
import com.example.transactionstarter.service.TransactionService;

@SpringBootTest
class UpdateTransactionStatusTest {

    @Autowired
    TransactionService service;

    @Test
    void updateTransactionStatus() {

        Transactions tran = new Transactions(10023, 453, "INR", new BigDecimal("5000"), "DEPOSIT", "PENDING");

        service.createTransaction(tran);

        Response response = service.updateStatus(10023, "COMPLETED");

        assertTrue(response.isSuccess());
        assertEquals(200, response.getStatusCode());
        assertEquals("Status Updated Sucessfully", response.getMessage());
    }

    @Test
    void rejectInvalidStatus() {

        Response response = service.updateStatus(10023, "INVALID");

        assertFalse(response.isSuccess());
        assertEquals(400, response.getStatusCode());
        assertEquals("Invalid transaction status", response.getMessage());
    }

    @Test
    void transactionNotFound() {

        Response response = service.updateStatus(99999, "COMPLETED");

        assertFalse(response.isSuccess());
        assertEquals(404, response.getStatusCode());
        assertEquals("No Transaction Found Failed, to Update", response.getMessage());
    }

    @Test
    void cannotUpdateCompletedTransaction() {

        Transactions tran = new Transactions(10024, 453, "INR", new BigDecimal("5000"), "DEPOSIT", "COMPLETED");

        service.createTransaction(tran);

        Response response = service.updateStatus(10024, "FAILED");

        assertFalse(response.isSuccess());
        assertEquals(400, response.getStatusCode());
        assertEquals("Cannot Update the status", response.getMessage());
    }

    @Test
    void cannotUpdateFailedTransaction() {

        Transactions tran = new Transactions(10025, 453, "INR", new BigDecimal("5000"), "DEPOSIT", "FAILED");

        service.createTransaction(tran);

        Response response = service.updateStatus(10025, "COMPLETED");

        assertFalse(response.isSuccess());
        assertEquals(400, response.getStatusCode());
        assertEquals("Cannot Update the status", response.getMessage());
    }
}