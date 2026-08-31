package com.example.transactionstarter.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.transactionstarter.models.Response;
import com.example.transactionstarter.models.Transactions;
import com.example.transactionstarter.repository.TransactionRepo;

@Service
public class TransactionService {

    @Autowired
    TransactionRepo repo;

    public Response getTransactionById(int tranId) {
        try{
            if (tranId <= 0 )
             return new Response<>(false,400,"Invalid Transaction ID",tranId);
        Transactions tran = repo.findById(tranId).orElse(null);
        String message = tran==null ? "No Transaction Found" : "Fetched Transaction";
        return new Response(true, 200, message, tran);
        }catch(Exception e){
            return new Response<>(false, 500, "Server Error", null);
    
        }
    }

    public Response getTransactionByCusId(int cusId) {
        try {
            if (cusId <= 0 )
             return new Response<>(false,400,"Invalid Customer ID",cusId);
            List<Transactions> li = repo.getByCusId(cusId);
            String message = li.isEmpty() ? "No Transactions by this Customer Id"
                    : "Fetched All Transactions By Customer ID";
            return new Response(true, 200, message, li);
        } catch (Exception e) {
            return new Response<>(false, 500, "Server Error", null);
        }
    }

    public Response createTransaction(Transactions tran) {
        try {
            if (tran.getTranId() <= 0)
                return new Response<>(false, 400, "Invalid Transaction ID", tran.getTranId());
            if(repo.existsById(tran.getTranId()))
                return new Response<>(false, 409, "Transaction ID already exists", tran);
            if (tran.getAmt() == null || tran.getAmt().compareTo(BigDecimal.ZERO) <= 0) 
                return new Response<>(false, 400, "Invalid amount entered", tran.getAmt());
            if (tran.getTranType()==null||!tran.getTranType().equals("DEPOSIT") &&!tran.getTranType().equals("WITHDRAWAL") &&!tran.getTranType().equals("TRANSFER")) 
                    return new Response<>(false,400,"Invalid Transaction Type",tran.getTranType());
            if (tran.getStatus()==null||!tran.getStatus().equals("COMPLETED") &&!tran.getStatus().equals("FAILED") &&!tran.getStatus().equals("PENDING") ) 
                    return new Response<>(false,400,"Invalid Transaction Status",tran.getStatus());    
            if ( tran.getCusId() <= 0) 
                    return new Response<>(false,400,"Invalid Customer Id",tran.getCusId());    
            if(tran.getCurrency() == null ||!tran.getCurrency().equals("INR"))
                return new Response<>(false,400,"Invalid Currency",tran.getCurrency());    
          
            Transactions saved = repo.save(tran);
            if (saved == null) {
                return new Response<>(false, 500, "Transaction Failed to Create", null);
            }
            return new Response<>(
                    true, 201, "Transaction Created Successfully", saved);
        } catch (Exception e) {
            return new Response<>(false, 500, "Server Error", null);
        }
    }

    public Response updateStatus(int tranId, String status) {
        try {
            if (status==null||!status.equals("FAILED") &&
                    !status.equals("COMPLETED") &&!status.equals("PENDING")) {
                return new Response<>(false, 400, "Invalid transaction status", status);
            }
            if (!repo.existsById(tranId))
                return new Response<>(false, 404, "No Transaction Found Failed, to Update", null);
            Transactions tran = repo.findById(tranId).orElse(null);
            if(tran.getStatus().equals(status))
                return new Response<>(false, 400, "Status Already Updated", tran.getStatus());
            if (tran.getStatus().equals("FAILED") ||
                    tran.getStatus().equals("COMPLETED")) {
                return new Response<>(false, 400, "Cannot Update the status", status);
            }
            tran.setStatus(status);
            repo.save(tran);
            return new Response<>(true, 200, "Status Updated Sucessfully", tran);
        } catch (Exception e) {
            return new Response<>(false, 500, "Server Error", null);
        }
    }

}
