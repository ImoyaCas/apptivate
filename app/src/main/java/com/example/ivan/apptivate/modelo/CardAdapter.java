package com.example.ivan.apptivate.modelo;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ivan.apptivate.Fragments.MostrarEventos;
import com.example.ivan.apptivate.R;
import com.example.ivan.apptivate.controlador.ManejarPlazas;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by ivan on 05/05/2016.
 */
public class CardAdapter extends RecyclerView.Adapter<CardAdapter.EventoViewHolder> {

    private List<Evento> items;
    int plz;
    String estado;
    private int idEvento;

    public static class EventoViewHolder extends RecyclerView.ViewHolder {
        // Campos respectivos de un item
        public ImageView imagen;
        public TextView nombre, lugar, plazas;
        public Button boton;
        CardView cv;

        public EventoViewHolder(View v) {
            super(v);
            cv = (CardView) itemView.findViewById(R.id.mycard);
            imagen = (ImageView) v.findViewById(R.id.img);
            nombre = (TextView) v.findViewById(R.id.nombreEven);
            lugar = (TextView) v.findViewById(R.id.lugarEven);
            plazas = (TextView)v.findViewById(R.id.plazasEvent);
            boton = (Button)v.findViewById(R.id.action_button);
        }
    }

    public CardAdapter(List<Evento> items) {
        this.items = items;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public EventoViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item, viewGroup, false);
        EventoViewHolder evh = new EventoViewHolder(v);

        return evh;
    }

    @Override
    public void onBindViewHolder(final EventoViewHolder viewHolder, final int i) {
        viewHolder.imagen.setImageResource(R.drawable.img1);
        viewHolder.nombre.setText(items.get(i).getNombre());
        viewHolder.lugar.setText(items.get(i).getLugar());
        viewHolder.plazas.setText(items.get(i).getPlazasOcupadas()+"/"+items.get(i).getPlazas());
        viewHolder.boton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                idEvento = items.get(i).getId();
                plazasLibres(viewHolder,i);
                   //llamar a funcion que borre y cambiar texto, despues añadir rresto de codigo en el else
            }
        });
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }


    public void notificaciones(final EventoViewHolder viewHolder, final int i) {

        AlertDialog.Builder dialogo;
        dialogo = new AlertDialog.Builder(viewHolder.boton.getContext());
        dialogo.setTitle("Inscribiendote");
        dialogo.setMessage("Confirma tu inscripción");

        dialogo.setPositiveButton("Si", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                acepta(viewHolder, i);
            }

        });

        dialogo.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                cancela(viewHolder);
            }
        });
        dialogo.show();
    }


    public void plazasLibres(EventoViewHolder viewHolder, int i){

        MostrarEventos.allEvents();

        Log.i("plazasocupadas","ERROR12 : "+items.get(i).getPlazasOcupadas());
        Log.i("plazas","ERROR12 : "+items.get(i).getPlazas());

        if(items.get(i).getPlazasOcupadas()< items.get(i).getPlazas()){
            notificaciones(viewHolder,i);

        }else{
            Toast toast = Toast.makeText(viewHolder.boton.getContext(),"No hay plazas", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    public void SumarPlazaEvento(){

        RestClient restClient = new RestClient();
        Retrofit retrofit = restClient.getRetrofit();


        ManejarPlazas servicio = retrofit.create(ManejarPlazas.class);
        Call<String> respuesta = servicio.SumarPlaza(idEvento);
        respuesta.enqueue(new Callback<String>() {

            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                estado = response.body();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.i("insertarplaza","ERROR12 : "+t.getMessage());
            }
        });

    }

    public void almacenarInscrito(){

        RestClient restClient = new RestClient();
        Retrofit retrofit = restClient.getRetrofit();


        ManejarPlazas servicio = retrofit.create(ManejarPlazas.class);
        Call<String> respuesta = servicio.inscribir(Usuario.idVista, idEvento);
        respuesta.enqueue(new Callback<String>() {

            @Override
            public void onResponse(Call<String> call, Response<String> response) {

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.i("restarplaza","ERROR12 : "+t.getMessage());
            }
        });

    }

    public void RestarPlazaEvento(){

        RestClient restClient = new RestClient();
        Retrofit retrofit = restClient.getRetrofit();


        ManejarPlazas servicio = retrofit.create(ManejarPlazas.class);
        Call<String> respuesta = servicio.RestarPlaza(idEvento);
        respuesta.enqueue(new Callback<String>() {

            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                estado = response.body();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.i("restarplaza","ERROR12 : "+t.getMessage());
            }
        });

    }

    public void acepta(EventoViewHolder viewHolder,int i) {

        MostrarEventos.allEvents();
        Toast toast = Toast.makeText(viewHolder.boton.getContext(),"Registrado en actividad", Toast.LENGTH_SHORT);
        toast.show();
        almacenarInscrito();
        SumarPlazaEvento();
    }

    public void cancela(EventoViewHolder viewHolder) {

        MostrarEventos.allEvents();
        Toast toast = Toast.makeText(viewHolder.boton.getContext(),"No te has registrado", Toast.LENGTH_SHORT);
        toast.show();
        RestarPlazaEvento();
    }

}

