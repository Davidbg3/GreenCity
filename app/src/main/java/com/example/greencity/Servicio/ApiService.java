package com.example.greencity.Servicio;

import com.example.greencity.Intercambio.GananciasRequest;
import com.example.greencity.Intercambio.GananciasResponse;

import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {
    @POST("recursos/ganancias")
    Call<List<GananciasResponse>> getGananciasxUsuario(@Body GananciasRequest oReq);
}
