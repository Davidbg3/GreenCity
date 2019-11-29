package com.example.greencity.Servicio;

import android.support.annotation.FloatRange;

import com.example.greencity.Global;
import com.example.greencity.Intercambio.DeleteUsuarioResponse;
import com.example.greencity.Intercambio.GetUsuarioResponse;
import com.example.greencity.Intercambio.LoginRequest;
import com.example.greencity.Intercambio.LoginResponse;
import com.example.greencity.Intercambio.UpdateUsuarioRequest;
import com.example.greencity.Intercambio.UpdateUsuarioResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface UsuarioServicio {

    @POST("loginUser")
    Call<LoginResponse> LoginUsuario(@Body LoginRequest oReq);

    @POST("usuarios/update")
    Call<List<UpdateUsuarioResponse>> UpdateUsuario(@Body UpdateUsuarioRequest oReq, @Header("Authorization") String token);

    @GET("usuarios/{id}")
    Call<List<GetUsuarioResponse>> GetUsuario(@Path("id") int id, @Header("Authorization") String token);

    @POST("usuarios/delete/{id}")
    Call<DeleteUsuarioResponse> DeleteUsuario(@Path("id") int id, @Header("Authorization") String token);
}
