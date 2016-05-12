package com.example.ivan.apptivate.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ivan.apptivate.R;
import com.example.ivan.apptivate.actividad.MainActivity;
import com.example.ivan.apptivate.controlador.ServicioMostrarEventos;
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
 * Created by ivan on 05/05/2016.
 */
public class MostrarEventos extends Fragment {

    View view;
    List<Evento> eventos;
    String a="";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        view = inflater.inflate(R.layout.ventana_items, container, false);


        final String[] eb = new String[1];
        RestClient restClient = new RestClient();
        Retrofit retrofit = restClient.getRetrofit();


        int idUsuario = Usuario.idVista;


        //eventos = new ArrayList<>();

      // eventos.add(evento);
        //eventos.add(evento1);


        RecyclerView card = (RecyclerView)view.getRootView().findViewById(R.id.reciclador);
        LinearLayoutManager layout = new LinearLayoutManager(getContext());
        layout.setOrientation(LinearLayoutManager.VERTICAL);
        card.setLayoutManager(layout);


        System.out.println("antes de usarse: " +a);
        CardAdapter myadaptador = new CardAdapter(MainActivity.eventos);
        card.setAdapter(myadaptador);

        return view;
        //return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }
}
