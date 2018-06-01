package com.example.antonio.vulcadatos.IInterfaz;

import com.example.antonio.vulcadatos.models.EstacionesGPS;
import com.example.antonio.vulcadatos.models.EstacionesAcelero;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface IVulcaDatos {

    @GET("efg9-8jrp.json")
    Call<ArrayList<EstacionesGPS>> obtenerListaGps();

    @GET("krh6-ay3a.json")
    Call<ArrayList<EstacionesAcelero>> obtenerListaSismo();
}
