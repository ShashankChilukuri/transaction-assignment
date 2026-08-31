package com.example.transactionstarter.Tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.transactionstarter.models.Response;
import com.example.transactionstarter.models.Transactions;
import com.example.transactionstarter.service.TransactionService;

@SpringBootTest
class GetTransactionByIdTest {

    @Autowired
    TransactionService service;

    @Test
    void getTransactionById() {

        Transactions tran = new Transactions(10021, 453, "INR", new BigDecimal("5000"), "DEPOSIT", "COMPLETED");

        service.createTransaction(tran);

        Response response = service.getTransactionById(10021);

        assertTrue(response.isSuccess());
        assertEquals(200, response.getStatusCode());
        assertEquals("Fetched Transaction", response.getMessage());
    }

    @Test
    void transactionNotFound() {

        Response response = service.getTransactionById(99999);

        assertTrue(response.isSuccess());
        assertEquals(200, response.getStatusCode());
        assertEquals("No Transaction Found", response.getMessage());
    }

    @Test
    void invalidTransactionId() {

        Response response = service.getTransactionById(-1);

        assertEquals(false, response.isSuccess());
        assertEquals(400, response.getStatusCode());
        assertEquals("Invalid Transaction ID", response.getMessage());
    }
}