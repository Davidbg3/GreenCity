package com.example.greencity.Intercambio;

public class DeleteUsuarioResponse {
    private String Mensaje;
    private String error;

    public DeleteUsuarioResponse(String mensaje, String error) {
        Mensaje = mensaje;
        this.error = error;
    }

    public String getMensaje() {
        return Mensaje;
    }

    public void setMensaje(String mensaje) {
        Mensaje = mensaje;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
