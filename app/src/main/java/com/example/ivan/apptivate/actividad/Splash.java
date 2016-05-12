package com.example.ivan.apptivate.actividad;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.ivan.apptivate.R;

/**
 * Created by ivan on 15/04/2016.
 */
public class Splash extends Activity {

    private final int DURACION_SPLASH = 4000; // 4 segundos

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);


        new Handler().postDelayed(new Runnable(){
            public void run(){
                // Cuando pasen los 4 segundos, pasamos a la actividad principal de la aplicación
                Intent intent=new Intent(Splash.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        },DURACION_SPLASH);

    }

    protected void onPause(){
        super.onPause();
        finish();
    }
}