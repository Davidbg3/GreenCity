package com.example.greencity.Intercambio;

public class LoginResponse {

    private Success success;

    private String error;

    public class Success{
        private String token;
        private String cod_usuario;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getCod_usuario() {
            return cod_usuario;
        }

        public void setCod_usuario(String cod_usuario) {
            this.cod_usuario = cod_usuario;
        }
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
