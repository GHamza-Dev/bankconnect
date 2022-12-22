package dev.dashboard.bankconnect.dto;

public class AuthResponse implements Response{
    private String message;
    private String token;

    public AuthResponse() {
    }

    public AuthResponse(String token) {
        this.token = token;
    }

    public AuthResponse(String token,String message) {
        this.token = token;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String getToken() {
        return token;
    }
}
