package com.example.greencity.Servicio;

import com.example.greencity.Global;
import com.example.greencity.Intercambio.Recojo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RocojosServicio {

    @GET("recojos")
    Call<List<Recojo>> getRecojo();

}
