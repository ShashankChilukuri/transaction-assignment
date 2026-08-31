package com.example.transactionstarter.models;

public class Response<T> {

    private boolean success;
    private String message;
    private int statusCode;
    private T data;

    public Response() {
    }

    public Response(boolean success,int statusCode, String message, T data) {
        this.success = success;
        this.statusCode=statusCode;
        this.message = message;
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setStatusCode(int st){
        this.statusCode=st;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    public int getStatusCode(){
        return statusCode;
    }
    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}