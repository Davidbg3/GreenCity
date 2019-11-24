package com.example.greencity.Servicio;

import com.example.greencity.Global;
import com.example.greencity.Intercambio.LoginRequest;
import com.example.greencity.Intercambio.LoginResponse;
import com.example.greencity.Intercambio.UpdateUsuarioRequest;
import com.example.greencity.Intercambio.UpdateUsuarioResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface UsuarioServicio {

    @POST("loginUser")
    Call<LoginResponse> LoginUsuario(@Body LoginRequest oReq);

    @POST("update")
    Call<UpdateUsuarioResponse> UpdateUsuario(@Body UpdateUsuarioRequest oReq, @Header("Authorization") String token);

}
