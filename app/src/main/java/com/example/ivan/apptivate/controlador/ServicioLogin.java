package com.example.ivan.apptivate.controlador;

import com.example.ivan.apptivate.modelo.Usuario;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by ivan on 19/04/2016.
 */
public interface ServicioLogin {

    @GET("php/login.php")
    Call<Usuario> getUser(@Query("username") String user, @Query("password") String pass);
}
