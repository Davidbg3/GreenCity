package com.example.greencity;

import android.app.Application;

public class Global extends Application {
    public final static String URL_API = "https://greencityapp.000webhostapp.com";
    public final static String METODO_ACTUALIZAR_USUARIO = "/Update";
    public final static String METODO_LOGIN = "/loginUser";
    private int IdUsuario;
    private String NombresUsuario;
    private String Token;

    public int getIdUsuarioGlobal() {
        return IdUsuario;
    }

    public void setIdUsuarioGlobal(int idUsuario) {
        IdUsuario = idUsuario;
    }

    public String getNombresUsuarioGlobal() {
        return NombresUsuario;
    }

    public void setNombresUsuarioGlobal(String nombresUsuario) {
        NombresUsuario = nombresUsuario;
    }

    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }
}
