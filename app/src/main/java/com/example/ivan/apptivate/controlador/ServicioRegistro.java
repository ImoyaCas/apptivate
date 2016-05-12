package com.example.ivan.apptivate.controlador;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by ivan on 21/04/2016.
 */
public interface ServicioRegistro {

    @GET("php/registrar.php")
    Call<String> setUser(@Query("username") String user, @Query("password") String pass, @Query("email") String email);

}
