package com.example.greencity.Intercambio;
import java.util.Date;
public class GananciasRequest {

    private int cod_usuario;
    private Date fecha;

    public GananciasRequest(int cod_usuario, Date fecha) {
        this.cod_usuario = cod_usuario;
        this.fecha = fecha;
    }

    public void setCod_usuario(int cod_usuario) {
        this.cod_usuario = cod_usuario;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getCod_usuario() { return cod_usuario; }

    public Date getFecha() { return fecha;}
}
