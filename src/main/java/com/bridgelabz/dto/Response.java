package com.bridgelabz.dto;

import com.bridgelabz.model.Person;

import java.io.Serializable;

public class Response implements Serializable {

    int statusCode;

    String message;

    Person data;

    public Response(String message, Person data) {
        this.message = message;
        this.data = data;
    }

    public Response(int statusCode, String message, Person data) {
        this.statusCode = statusCode;
        this.message = message;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Person data) {
        this.data = data;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
}
