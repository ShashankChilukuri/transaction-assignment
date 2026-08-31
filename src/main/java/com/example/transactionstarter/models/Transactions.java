package com.example.transactionstarter.models;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Transactions {

    @Id
    private int tranId;

    private int cusId;

    private String currency;

    private BigDecimal amt;

    private String tranType;

    private String status;

    public Transactions() {
    }

    public Transactions(int tranId, int cusId, String currency,
                        BigDecimal amt, String tranType,
                        String status) {
        this.tranId = tranId;
        this.cusId = cusId;
        this.currency = currency;
        this.amt = amt;
        this.tranType = tranType;
        this.status = status;
    }

    public int getTranId() {
        return tranId;
    }

    public void setTranId(int tranId) {
        this.tranId = tranId;
    }

    public int getCusId() {
        return cusId;
    }

    public void setCusId(int cusId) {
        this.cusId = cusId;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public BigDecimal getAmt() {
        return amt;
    }

    public void setAmt(BigDecimal amt) {
        this.amt = amt;
    }

    public String getTranType() {
        return tranType;
    }

    public void setTranType(String tranType) {
        this.tranType = tranType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}