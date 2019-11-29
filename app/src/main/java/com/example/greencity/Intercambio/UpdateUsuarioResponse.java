package com.example.greencity.Intercambio;

public class UpdateUsuarioResponse {
    private Boolean exito;
    private String mensaje;

    public UpdateUsuarioResponse(Boolean exito, String mensaje) {
        this.exito = exito;
        this.mensaje = mensaje;
    }

    public Boolean getExito() {
        return exito;
    }

    public void setExito(Boolean exito) {
        this.exito = exito;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
