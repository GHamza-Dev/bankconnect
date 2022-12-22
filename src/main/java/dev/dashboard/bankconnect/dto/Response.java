package dev.dashboard.bankconnect.dto;

import java.util.ArrayList;
import java.util.Collection;

public class Response {
    private String message;
    private Integer status;
    private Collection data;

    public Response(String message, Integer status, Collection data) {
        this.message = message;
        this.status = status;
        this.data = data;
    }

    public Response(String message,Integer status) {
        this.message = message;
        this.status = status;
        this.data = new ArrayList();
    }

    public Response(String message) {
        this.message = message;
        this.status = 200;
        this.data = new ArrayList();
    }

    public Response() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Collection getData() {
        return data;
    }

    public void setData(Collection data) {
        this.data = data;
    }
}
