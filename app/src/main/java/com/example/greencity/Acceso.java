package com.example.greencity;

public class Acceso {
    public Boolean Logeo(String usuario, String password){
        Boolean resultado = false;
        if (usuario.equals("administrador.appgc@gmail.com") && password.equals("Admin2019")){
            resultado = true;
        }
        return resultado;
    }
}
