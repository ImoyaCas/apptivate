package com.example.ivan.apptivate.actividad;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ivan.apptivate.R;
import com.example.ivan.apptivate.controlador.EnviarImagen;
import com.example.ivan.apptivate.modelo.RestClient;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by ivan on 18/05/2016.
 */
public class Perfil extends AppCompatActivity {

    ImageView avatar;
    Button changeAvatar;
    TextView nombre, email;

    private AlertDialog _photoDialog
    private Uri mImageUri;
    private static final int ACTIVITY_SELECT_IMAGE = 1020,
            ACTIVITY_SELECT_FROM_CAMERA = 1040, ACTIVITY_SHARE = 1030;
    private PhotoUtils photoUtils;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadFile();
                Snackbar.make(view, "aqui llamo a la funcion", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void uploadFile() {
/**********Meter Uri fileUri como parametro en el metodo uploapFile en introducirla en new File************/
        // create upload service client
        RestClient restClient = new RestClient();
        Retrofit retrofit = restClient.getRetrofit();

        EnviarImagen servicio = retrofit.create(EnviarImagen.class);

        // use the FileUtils to get the actual file by uri
        /****Meter direccion de imagen*****/
        File file = new File(String.valueOf(R.drawable.logo));

        // create RequestBody instance from file
        final RequestBody requestFile =
                RequestBody.create(MediaType.parse("multipart/form-data"), file);

        // MultipartBody.Part is used to send also the actual file name
        MultipartBody.Part body =
                MultipartBody.Part.createFormData("picture", file.getName(), requestFile);

        // add another part within the multipart request
        String descriptionString = "prueba";
        RequestBody description =
                RequestBody.create(
                        MediaType.parse("multipart/form-data"), descriptionString);

        Log.d("ANTES DEL RESPONSE ","YAAAAAAA"+requestFile.toString());

        // finally, execute the request
        Call<String> call = servicio.upload(requestFile, description);
        call.enqueue(new Callback<String>() {

            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.d("onresponsePerfil","YAAAAAAA");
                Log.d("REQUESTFILE ","YAAAAAAA"+requestFile.toString());
                Log.d("respueta ","YAAAAAAA"+response.body().toString());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.d("onresponsePerfil","ERROR"+t.getMessage());
            }
        });
    }
}
