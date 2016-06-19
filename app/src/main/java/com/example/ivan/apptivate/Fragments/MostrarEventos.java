package com.example.ivan.apptivate.Fragments;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.ivan.apptivate.R;
import com.example.ivan.apptivate.controlador.ManejarPlazas;
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
    public static List<Usuario> usuarios;
    public static List<Evento> eventos ;
    static RecyclerView card;
    static CardAdapter myadaptador;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.ventana_items, container, false);

        eventos = new ArrayList<>();
        allEvents();

        card = (RecyclerView)view.getRootView().findViewById(R.id.reciclador);
        LinearLayoutManager layout = new LinearLayoutManager(getContext());
        layout.setOrientation(LinearLayoutManager.VERTICAL);
        card.setLayoutManager(layout);

        return view;
    }

    public static void allEvents(){

        RestClient restClient = new RestClient();
        Retrofit retrofit = restClient.getRetrofit();


        ServicioMostrarEventos servicio = retrofit.create(ServicioMostrarEventos.class);
        Call<List<Evento>> respuesta = servicio.getEventos("ee");
        eventos = new ArrayList<>();
        respuesta.enqueue(new Callback<List<Evento>>() {

            @Override
            public void onResponse(Call<List<Evento>> call, Response<List<Evento>> response) {
                eventos = response.body();
                myadaptador = new CardAdapter(eventos);
                card.setAdapter(myadaptador);
            }

            @Override
            public void onFailure(Call<List<Evento>> call, Throwable t) {
                Log.i("allEvents","ERROR12 : "+t.getMessage());
            }
        });
        allEvents();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    /**
     * Metodo que muestra los usuarios inscritos en una lista desplegable.
     * @param id posicion del usuario en la lista
     */
    public static void getUsuariosInscritos(int id) {
        Log.i("Principio:",""+id);
        RestClient restClient = new RestClient();
        Retrofit retrofit = restClient.getRetrofit();

        Log.i("segundo:",""+id);
        ManejarPlazas servicio = retrofit.create(ManejarPlazas.class);
        Call<List<Usuario>> respuesta = servicio.listarInscritos(id);
        usuarios = new ArrayList<>();
        Log.i("Antes del onresponse:",""+id);
        respuesta.enqueue(new Callback<List<Usuario>>() {
            @Override
            public void onResponse(Call<List<Usuario>> call, Response<List<Usuario>> response) {
                Log.i("getusuariosinscritos",""+response.body());
                usuarios = response.body();
                Log.i("getusuariosinscritos",""+usuarios.size());
                mostrarUsuarios();
            }

            @Override
            public void onFailure(Call<List<Usuario>> call, Throwable t) {
                Log.i("getusuariosinscritos","failure->"+t.getMessage());
                Log.i("getusuariosinscritos","failure->"+call.toString());
            }
        });
    }

    public static void mostrarUsuarios() {

        AlertDialog.Builder builder = new AlertDialog.Builder(card.getContext());
        builder.setTitle("Usuarios inscritos");
        ArrayList<String> nombres = new ArrayList<>();

        ListView modeList = new ListView(card.getContext());

        for(Usuario usuario:usuarios) {
            nombres.add(usuario.getUsername());
        }

        ArrayAdapter<String> modeAdapter = new ArrayAdapter<>(card.getContext(), android.R.layout.simple_list_item_1, android.R.id.text1, nombres);
        modeList.setAdapter(modeAdapter);

        builder.setView(modeList);
        final Dialog dialog = builder.create();

        dialog.show();

        Log.i("mostrarUsuarios", "ERROR12 : "  );
    }
}
