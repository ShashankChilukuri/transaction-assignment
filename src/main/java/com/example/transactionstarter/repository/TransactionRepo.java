package com.example.transactionstarter.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.transactionstarter.models.Transactions;

@Repository
public interface TransactionRepo extends JpaRepository<Transactions, Integer> {

    List<Transactions> getByCusId(int cusId);

}
