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
class GetTransactionByCustomerIdTest {

    @Autowired
    TransactionService service;

    @Test
    void getTransactionByCustomerId() {

        Transactions tran = new Transactions(10022, 453, "INR", new BigDecimal("5000"), "DEPOSIT", "COMPLETED");

        service.createTransaction(tran);

        Response response = service.getTransactionByCusId(453);

        assertTrue(response.isSuccess());
        assertEquals(200, response.getStatusCode());
        assertEquals("Fetched All Transactions By Customer ID", response.getMessage());
    }

    @Test
    void customerHasNoTransactions() {

        Response response = service.getTransactionByCusId(9999);

        assertTrue(response.isSuccess());
        assertEquals(200, response.getStatusCode());
        assertEquals("No Transactions by this Customer Id", response.getMessage());
    }

    @Test
    void invalidCustomerId() {

        Response response = service.getTransactionByCusId(-1);

        assertEquals(false, response.isSuccess());
        assertEquals(400, response.getStatusCode());
        assertEquals("Invalid Customer ID", response.getMessage());
    }
}