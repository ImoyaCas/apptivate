package com.example.ivan.apptivate.controlador;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by ivan on 26/04/2016.
 */
public interface ServicioCrearEvento {

    @GET("php/crearEvento.php")
    Call<String> setUser(@Query("nombre") String nombre, @Query("lugar") String lugar, @Query("descripcion") String descripcion, @Query("precio") Double precio, @Query("plazas") int plazas, @Query("idUsuario") int idUsuario);

}
