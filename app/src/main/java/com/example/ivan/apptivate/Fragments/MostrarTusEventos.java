package com.example.ivan.apptivate.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ivan.apptivate.R;
import com.example.ivan.apptivate.controlador.MostrarEventosById;
import com.example.ivan.apptivate.modelo.CardAdapter;
import com.example.ivan.apptivate.modelo.Evento;
import com.example.ivan.apptivate.modelo.RestClient;
import com.example.ivan.apptivate.modelo.Usuario;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by ivan on 12/05/2016.
 */
public class MostrarTusEventos extends Fragment {

    View view;
    public static List<Evento> eventos  ;
    int idUsuario = Usuario.idVista;
    RecyclerView card;
    CardAdapter myadaptador;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.ventana_items, container, false);

        eventos = new ArrayList<>();
        yourEvents();

        card = (RecyclerView)view.getRootView().findViewById(R.id.reciclador);
        LinearLayoutManager layout = new LinearLayoutManager(getContext());
        layout.setOrientation(LinearLayoutManager.VERTICAL);
        card.setLayoutManager(layout);

        return view;
    }

    public void yourEvents(){

        RestClient restClient = new RestClient();
        Retrofit retrofit = restClient.getRetrofit();


        MostrarEventosById servicio = retrofit.create(MostrarEventosById.class);
        Call<List<Evento>> respuesta = servicio.getEventosById(idUsuario);

        respuesta.enqueue(new Callback<List<Evento>>() {
            @Override
            public void onResponse(Call<List<Evento>> call, Response<List<Evento>> response) {
                eventos = response.body();
                myadaptador = new CardAdapter(eventos);
                card.setAdapter(myadaptador);
                Log.i("yourEvents","ERROR12Nombre : "+eventos.get(0).getNombre());
                Log.i("yourEvents","ERROR12 : "+response.body().toString());
            }

            @Override
            public void onFailure(Call<List<Evento>> call, Throwable t) {
                Log.i("yourEvents","ERROR12 : "+t.getMessage());
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
