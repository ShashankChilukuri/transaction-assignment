package com.example.transactionstarter.controller;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.pulsar.PulsarProperties.Transaction;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.transactionstarter.models.Response;
import com.example.transactionstarter.models.Transactions;
import com.example.transactionstarter.service.TransactionService;

@RestController
public class TransactionController {
    @Autowired
    TransactionService service;
    @GetMapping("/transactions/{tranId}")
    public Response getTransactions(@PathVariable int tranId) {
        return service.getTransactionById(tranId);
    }
    @GetMapping("/customer/transactions/{cusId}")
    public Response getTransactionsByCust(@PathVariable int cusId){
        return service.getTransactionByCusId(cusId);
    }
    @PostMapping("/transactions")
    public Response createTran(@RequestBody Transactions tran){
       return service.createTransaction(tran);
    }
    @PutMapping("/transactions/{tranId}/status")
    public Response updateStatus(@PathVariable int tranId,@RequestBody String status) {
    return service.updateStatus(tranId, status);
    }
}
