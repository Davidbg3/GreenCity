//-----------------------------------com.example.green.Recojo.java-----------------------------------
package com.example.greencity.Intercambio;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Recojo {

    @SerializedName("cod_recojo")
    @Expose
    private String codRecojo;
    @SerializedName("fecha")
    @Expose
    private String fecha;
    @SerializedName("hora")
    @Expose
    private String hora;
    @SerializedName("peso")
    @Expose
    private String peso;
    @SerializedName("cod_estado")
    @Expose
    private String codEstado;
    @SerializedName("cod_material")
    @Expose
    private String codMaterial;
    @SerializedName("cod_tipo_material")
    @Expose
    private String codTipoMaterial;
    @SerializedName("cod_usuario_vecino")
    @Expose
    private String codUsuarioVecino;
    @SerializedName("cod_usuario_recolector")
    @Expose
    private String codUsuarioRecolector;

    public String getCodRecojo() {
        return codRecojo;
    }

    public void setCodRecojo(String codRecojo) {
        this.codRecojo = codRecojo;
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

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getCodEstado() {
        return codEstado;
    }

    public void setCodEstado(String codEstado) {
        this.codEstado = codEstado;
    }

    public String getCodMaterial() {
        return codMaterial;
    }

    public void setCodMaterial(String codMaterial) {
        this.codMaterial = codMaterial;
    }

    public String getCodTipoMaterial() {
        return codTipoMaterial;
    }

    public void setCodTipoMaterial(String codTipoMaterial) {
        this.codTipoMaterial = codTipoMaterial;
    }

    public String getCodUsuarioVecino() {
        return codUsuarioVecino;
    }

    public void setCodUsuarioVecino(String codUsuarioVecino) {
        this.codUsuarioVecino = codUsuarioVecino;
    }

    public String getCodUsuarioRecolector() {
        return codUsuarioRecolector;
    }

    public void setCodUsuarioRecolector(String codUsuarioRecolector) {
        this.codUsuarioRecolector = codUsuarioRecolector;
    }

}