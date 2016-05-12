package com.example.ivan.apptivate.controlador;

import com.example.ivan.apptivate.modelo.Evento;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by ivan on 10/05/2016.
 */
public interface ServicioMostrarEventos {
    @GET("php/mostrarEventos.php")
    Call<List<Evento>> getEventos(@Query("nombre") String nombre);

}
