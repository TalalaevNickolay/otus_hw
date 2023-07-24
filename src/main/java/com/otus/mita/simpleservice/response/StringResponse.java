package com.otus.mita.simpleservice.response;

public class StringResponse {

    private String status;

    public StringResponse(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
