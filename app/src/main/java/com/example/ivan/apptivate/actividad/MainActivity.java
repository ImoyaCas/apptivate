package com.example.ivan.apptivate.actividad;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ivan.apptivate.Fragments.DatePickerFragment;
import com.example.ivan.apptivate.Fragments.FragmentCrearEvento;
import com.example.ivan.apptivate.Fragments.MostrarEventos;
import com.example.ivan.apptivate.Fragments.MostrarTusEventos;
import com.example.ivan.apptivate.Fragments.TimePickerFragment;
import com.example.ivan.apptivate.R;
import com.example.ivan.apptivate.modelo.Usuario;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, FragmentCrearEvento.OnFragmentInteractionListener {




    int id;
    Fragment fragment = null;
    boolean FragmentTransaction = false;


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
                startActivity(new Intent(MainActivity.this, Perfil.class));
//               Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
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

    }





    @Override
    public void onBackPressed() {
       /* DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }*/
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
        id = item.getItemId();



        if (id == R.id.crea_evento) {
            fragment = new FragmentCrearEvento();
            FragmentTransaction = true;
        } else if (id == R.id.ver_tus_eventos) {
            fragment = new MostrarTusEventos();
            FragmentTransaction = true;
        } else if (id == R.id.ver_todos_eventos) {
            fragment = new MostrarEventos();
            FragmentTransaction = true;
        } else if (id == R.id.finish) {
            System.exit(0);
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

    public void showTimePickerDialog(View v) {
        EditText time=(EditText) v.findViewById(R.id.hora);
        DialogFragment newFragment = new TimePickerFragment(time);
        newFragment.show(getSupportFragmentManager(), "timePicker");

    }
    public void showDatePickerDialog(View v) {
        EditText time=(EditText) v.findViewById(R.id.fecha);
        DatePickerFragment newFragment = new DatePickerFragment(time);
        newFragment.show(getFragmentManager(), "datePicker");
    }
}
