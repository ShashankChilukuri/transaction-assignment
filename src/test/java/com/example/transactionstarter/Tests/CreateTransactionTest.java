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
class CreateTransactionTest {

    @Autowired
    TransactionService service;

    @Test
    void createTransaction() {

        Transactions tran = new Transactions(10014, 453, "INR", new BigDecimal("5000"), "DEPOSIT", "COMPLETED");

        Response response = service.createTransaction(tran);

        assertTrue(response.isSuccess());
        assertEquals(201, response.getStatusCode());
        assertEquals("Transaction Created Successfully", response.getMessage());
    }

    @Test
    void rejectInvalidAmount() {

        Transactions tran = new Transactions(10015, 453, "INR", new BigDecimal("-5000"), "DEPOSIT", "COMPLETED");

        Response response = service.createTransaction(tran);

        assertFalse(response.isSuccess());
        assertEquals(400, response.getStatusCode());
        assertEquals("Invalid amount entered", response.getMessage());
    }

    @Test
    void rejectInvalidTransactionType() {

        Transactions tran = new Transactions(10016, 453, "INR", new BigDecimal("5000"), "INVALID", "COMPLETED");

        Response response = service.createTransaction(tran);

        assertFalse(response.isSuccess());
        assertEquals(400, response.getStatusCode());
        assertEquals("Invalid Transaction Type", response.getMessage());
    }

    @Test
    void rejectInvalidTransactionStatus() {

        Transactions tran = new Transactions(10017, 453, "INR", new BigDecimal("5000"), "DEPOSIT", "INVALID");

        Response response = service.createTransaction(tran);

        assertFalse(response.isSuccess());
        assertEquals(400, response.getStatusCode());
        assertEquals("Invalid Transaction Status", response.getMessage());
    }

    @Test
    void rejectInvalidCustomerId() {

        Transactions tran = new Transactions(10018, -1, "INR", new BigDecimal("5000"), "DEPOSIT", "COMPLETED");

        Response response = service.createTransaction(tran);

        assertFalse(response.isSuccess());
        assertEquals(400, response.getStatusCode());
        assertEquals("Invalid Customer Id", response.getMessage());
    }

    @Test
    void rejectInvalidCurrency() {

        Transactions tran = new Transactions(10019, 453, "USD", new BigDecimal("5000"), "DEPOSIT", "COMPLETED");

        Response response = service.createTransaction(tran);

        assertFalse(response.isSuccess());
        assertEquals(400, response.getStatusCode());
        assertEquals("Invalid Currency", response.getMessage());
    }

    @Test
    void rejectDuplicateTransactionId() {

        Transactions tran = new Transactions(10020, 453, "INR", new BigDecimal("5000"), "DEPOSIT", "COMPLETED");

        service.createTransaction(tran);

        Response response = service.createTransaction(tran);

        assertFalse(response.isSuccess());
        assertEquals(409, response.getStatusCode());
        assertEquals("Transaction ID already exists", response.getMessage());
    }
}