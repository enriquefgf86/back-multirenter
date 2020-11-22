package com.example.demo.responses;

import java.io.Serializable;

public class AppResponse <T> implements Serializable {
    private static final long serialVersionUID=1L;

    private String status;
    private String code;
    private String message;
    private T data;

    public AppResponse(String status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

    public AppResponse(String status, String code, String message,T data) {
        super();
        this.status = status;
        this.code = code;
        this.message = message;
        this.data=data;
    }

    ///////////////////////getters and setters//////////////////////////
    public String getStatus() {     return status; }
    public void setStatus(String status) {    this.status = status;}

    public String getCode() {    return code; }
    public void setCode(String code) {    this.code = code;}

    public String getMessage() {   return message;}
    public void setMessage(String message) {   this.message = message;}

    public T getData() {    return data;  }
    public void setData(T data) {   this.data = data; }
}
