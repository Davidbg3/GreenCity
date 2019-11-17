package com.example.greencity.Intercambio;

import android.graphics.Bitmap;

public class GetUsuarioResponse {
    private long cod_usuario;
    private String nom_usuario;
    private String pass_usuario;
    private String nombres;
    private String apellidos;
    private String telefono;
    private String correo;
    private Bitmap foto;
    private String latitud;
    private String longitud;
    private int cod_tipo_usuario;

    public GetUsuarioResponse(long cod_usuario, String nom_usuario, String pass_usuario, String nombres, String apellidos, String telefono, String correo, Bitmap foto, String latitud, String longitud, int cod_tipo_usuario) {
        this.cod_usuario = cod_usuario;
        this.nom_usuario = nom_usuario;
        this.pass_usuario = pass_usuario;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.correo = correo;
        this.foto = foto;
        this.latitud = latitud;
        this.longitud = longitud;
        this.cod_tipo_usuario = cod_tipo_usuario;
    }

    public long getCod_usuario() {
        return cod_usuario;
    }

    public void setCod_usuario(long cod_usuario) {
        this.cod_usuario = cod_usuario;
    }

    public String getNom_usuario() {
        return nom_usuario;
    }

    public void setNom_usuario(String nom_usuario) {
        this.nom_usuario = nom_usuario;
    }

    public String getPass_usuario() {
        return pass_usuario;
    }

    public void setPass_usuario(String pass_usuario) {
        this.pass_usuario = pass_usuario;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Bitmap getFoto() {
        return foto;
    }

    public void setFoto(Bitmap foto) {
        this.foto = foto;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public int getCod_tipo_usuario() {
        return cod_tipo_usuario;
    }

    public void setCod_tipo_usuario(int cod_tipo_usuario) {
        this.cod_tipo_usuario = cod_tipo_usuario;
    }
}
