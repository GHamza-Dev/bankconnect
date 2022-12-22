package dev.dashboard.bankconnect.dto;


import java.util.Collection;

public class AuthResponse extends Response  {
    private String token;

    public AuthResponse() {
    }

    public AuthResponse(String token) {
        this.token = token;
    }

    public AuthResponse(String message, Integer status, Collection data, String token) {
        super(message, status, data);
        this.token = token;
    }

    public AuthResponse(String message, Integer status, String token) {
        super(message, status);
        this.token = token;
    }

    public AuthResponse(String message, String token) {
        super(message);
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
