package com.example.greencity.Modelo;

public class historial_Recolector {
    Integer cod_recojo, cod_estado;
    String material, direccion,fecha,hora, tlf;
    Double peso, monto ;


    public historial_Recolector(Integer cod_recojo, Integer cod_estado, String material, String direccion, String fecha, String hora, Double peso, Double monto, String tlf) {
        this.cod_recojo = cod_recojo;
        this.cod_estado = cod_estado;
        this.material = material;
        this.direccion = direccion;
        this.fecha = fecha;
        this.hora = hora;
        this.peso = peso;
        this.monto = monto;
        this.tlf = tlf;
    }

    public String getTlf() {
        return tlf;
    }

    public void setTlf(String tlf) {
        this.tlf = tlf;
    }

    public Integer getCod_recojo() {
        return cod_recojo;
    }

    public void setCod_recojo(Integer cod_recojo) {
        this.cod_recojo = cod_recojo;
    }

    public Integer getCod_estado() {
        return cod_estado;
    }

    public void setCod_estado(Integer cod_estado) {
        this.cod_estado = cod_estado;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
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

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }
}

