package com.example.greencity.Modelo;

public class Alerta {

    int cod_recojo;
    String fecha;
    String hora;
    float peso;
    int cod_estado;
    int cod_material;
    int cod_tipo_material;
    String nom_usuario_vecino;
    String nom_usuario_recolector;

    public Alerta(String i, String i1, String s, String s1) {
    }

    public Alerta(int cod_recojo, String fecha, String hora, float peso, int cod_estado, int cod_material, int cod_tipo_material, String nom_usuario_vecino, String nom_usuario_recolector) {
        this.cod_recojo = cod_recojo;
        this.fecha = fecha;
        this.hora = hora;
        this.peso = peso;
        this.cod_estado = cod_estado;
        this.cod_material = cod_material;
        this.cod_tipo_material = cod_tipo_material;
        this.nom_usuario_vecino = nom_usuario_vecino;
        this.nom_usuario_recolector = nom_usuario_recolector;
    }

    public String getNom_usuario_recolector() {
        return nom_usuario_recolector;
    }

    public void setNom_usuario_recolector(String cod_usuario_recolector) {
        this.nom_usuario_recolector = cod_usuario_recolector;
    }

    public int getCod_recojo() {
        return cod_recojo;
    }

    public void setCod_recojo(int cod_recojo) {
        this.cod_recojo = cod_recojo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public int getCod_estado() {
        return cod_estado;
    }

    public void setCod_estado(int cod_estado) {
        this.cod_estado = cod_estado;
    }

    public int getCod_material() {
        return cod_material;
    }

    public void setCod_material(int cod_material) {
        this.cod_material = cod_material;
    }

    public int getCod_tipo_material() {
        return cod_tipo_material;
    }

    public void setCod_tipo_material(int cod_tipo_material) {
        this.cod_tipo_material = cod_tipo_material;
    }

    public String getNom_usuario_vecino() {
        return nom_usuario_vecino;
    }

    public void setNom_usuario_vecino(String cod_usuario_vecino) {
        this.nom_usuario_vecino = cod_usuario_vecino;
    }
}
