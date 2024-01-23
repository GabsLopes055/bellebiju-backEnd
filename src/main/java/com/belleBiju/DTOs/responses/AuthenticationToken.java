package com.belleBiju.DTOs.responses;

public class AuthenticationToken {

    private String token;

    public AuthenticationToken() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String AuthenticationToken(String token) {
        this.setToken(token);
        return getToken();
    }
}
