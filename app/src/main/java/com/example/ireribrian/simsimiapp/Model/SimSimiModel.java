package com.example.ireribrian.simsimiapp.Model;

/**
 * Created by ireribrian on 11/1/2018.
 */

public class SimSimiModel {

    public String response;
    public String id;
    public String result;
    public String message;

    public SimSimiModel(String response, String id, String result, String message) {
        this.response = response;
        this.id = id;
        this.result = result;
        this.message = message;
    }

    public SimSimiModel() {
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
