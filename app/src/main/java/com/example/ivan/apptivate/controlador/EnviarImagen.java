package com.example.ivan.apptivate.controlador;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by ivan on 18/05/2016.
 */
public interface EnviarImagen {

    @GET("php/subirUrl.php")
    Call<String> setUrlImg(@Query("idUsuario") int idUsuario, @Query("nombreImg") String nombreImg);
}
