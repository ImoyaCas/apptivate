package com.example.ivan.apptivate.actividad;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.ivan.apptivate.Fragments.FragmentCrearEvento;
import com.example.ivan.apptivate.Fragments.MostrarEventos;
import com.example.ivan.apptivate.R;
import com.example.ivan.apptivate.controlador.ServicioMostrarEventos;
import com.example.ivan.apptivate.modelo.Evento;
import com.example.ivan.apptivate.modelo.RestClient;
import com.example.ivan.apptivate.modelo.Usuario;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, FragmentCrearEvento.OnFragmentInteractionListener {

public static List<Evento> eventos ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "aqui para ponerse en cintacto con el admin de la app", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View header = navigationView.getHeaderView(0);
        TextView nombrevista = (TextView) header.findViewById(R.id.nombreVista);
        TextView emailvista = (TextView)header.findViewById(R.id.emailVista);


        nombrevista.setText(Usuario.nombreVista);
        emailvista.setText(Usuario.emailVista);

       RestClient restClient = new RestClient();
        Retrofit retrofit = restClient.getRetrofit();

        ServicioMostrarEventos servicio = retrofit.create(ServicioMostrarEventos.class);
        Call<List<Evento>> respuesta = servicio.getEventos("ee");
        eventos = new ArrayList<>();
        respuesta.enqueue(new Callback<List<Evento>>() {
            @Override
            public void onResponse(Call<List<Evento>> call, Response<List<Evento>> response) {
                eventos = response.body();
                Log.i("EEEE","ERROR12Nombre : "+eventos.get(0).getNombre());
                Log.i("EEEE","ERROR12 : "+response.body().toString());
            }

            @Override
            public void onFailure(Call<List<Evento>> call, Throwable t) {
                Log.i("EEEE","ERROR12 : "+t.getMessage());
            }
        });


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        Fragment fragment = null;
        boolean FragmentTransaction = false;

        if (id == R.id.crea_evento) {
            fragment = new FragmentCrearEvento();
            FragmentTransaction = true;
        } else if (id == R.id.ver_tus_eventos) {
            fragment = new MostrarEventos();
            FragmentTransaction = true;
        } else if (id == R.id.ver_todos_eventos) {

        } else if (id == R.id.galeria) {

        } else if (id == R.id.perfil) {

        } /*else if (id == R.id.nav_send) {

        }*/

        if(FragmentTransaction){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.content_main, fragment)
                    .commit();

            item.setChecked(true);
            getSupportActionBar().setTitle(item.getTitle());
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
