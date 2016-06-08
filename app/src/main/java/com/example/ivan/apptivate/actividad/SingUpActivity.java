package com.example.ivan.apptivate.actividad;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ivan.apptivate.R;
import com.example.ivan.apptivate.controlador.ServicioRegistro;
import com.example.ivan.apptivate.modelo.RestClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by ivan on 25/04/2016.
 */
public class SingUpActivity extends AppCompatActivity {
    String webservice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_up);

        final EditText username;
        final EditText password;
        final EditText eMail;

        username = (EditText) findViewById(R.id.nombre);
        password = (EditText) findViewById(R.id.password);
        eMail = (EditText) findViewById(R.id.email);

        Button registrar = (Button) findViewById(R.id.registrar);
        assert registrar != null;
        registrar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                assert username != null;
                String user = username.getText().toString();
                assert password != null;
                String pass = password.getText().toString();
                assert eMail != null;
                String email = eMail.getText().toString();

                // final Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                // startActivity(intent);

                RestClient restClient = new RestClient();
                Retrofit retrofit = restClient.getRetrofit();

                ServicioRegistro servicio = retrofit.create(ServicioRegistro.class);
                final Call<String> respuesta = servicio.setUser(user, pass, email);
                respuesta.enqueue(new Callback<String>() {


                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        username.setText("");
                        password.setText("");
                        eMail.setText("");
                        webservice = response.body();
                        accionPositiva(response);
                        System.out.println("La respuesta es: "+webservice);
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        username.setText("");
                        password.setText("");
                        eMail.setText("");
                    }
                });
            }
        });

        Button atras = (Button) findViewById(R.id.atras);
        assert atras != null;
        atras.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SingUpActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

    }

    public void accionPositiva(Response<String> response) {
        contenido();
    }

    public void contenido() {
        boolean ok = false;

        if (webservice == "true"){
            ok = true;
            System.out.println("El webservice es: "+webservice);
            System.out.println("El boolean es: "+ok);
        }
        if (ok == true) {
            Toast toast = Toast.makeText(this, "Te has registrado correctamente", Toast.LENGTH_LONG);
            toast.show();
            Intent intent = new Intent(SingUpActivity.this, LoginActivity.class);
            startActivity(intent);
        }else{
            Toast toast = Toast.makeText(this, "No te has registrado, intentalo de nuevo", Toast.LENGTH_LONG);
            toast.show();
        }
    }
}
