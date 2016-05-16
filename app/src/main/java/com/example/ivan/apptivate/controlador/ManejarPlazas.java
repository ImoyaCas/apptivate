package com.example.ivan.apptivate.controlador;

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


}
