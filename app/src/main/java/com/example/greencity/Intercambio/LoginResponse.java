package com.example.greencity.Intercambio;

public class LoginResponse {

    private Success success;

    private String error;

    private class Success{
        private String token;
    }

    public LoginResponse(Success success, String error) {
        this.success = success;
        this.error = error;
    }

    public LoginResponse(Success success) {
        this.success = success;
    }

    public Success getSuccess() {
        return success;
    }

    public void setSuccess(Success success) {
        this.success = success;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
