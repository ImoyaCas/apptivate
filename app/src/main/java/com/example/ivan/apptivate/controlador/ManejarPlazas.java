package com.example.ivan.apptivate.controlador;

import com.example.ivan.apptivate.modelo.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by ivan on 16/05/2016.
 */
public interface ManejarPlazas {

    @GET("php/insertarPlaza.php")
    Call<String> SumarPlaza(@Query("id") int id);

    @GET("php/restarPlaza.php")
    Call<String> RestarPlaza(@Query("id") int id);

    @GET("php/inscribir.php")
    Call<String> inscribir(@Query("idUsuario") int idUsuario, @Query("idEvento") int idEvento);

    @GET("php/getUsuariosInscritos.php")
    Call<List<Usuario>> listarInscritos(@Query("idEvento") int idEvento);

}
