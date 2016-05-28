package com.example.ivan.apptivate.actividad;

import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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

import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

/**
 * Created by ivan on 18/05/2016.
 */
public class Perfil extends AppCompatActivity {

    ImageView avatar;
    TextView nombre, email;
    CoordinatorLayout vista;
    FloatingActionButton fab;

    private static String APP_DIRECTORY = "ImagenesApptivate/";/******** <-------------- poner aqui la direccion del directorio principal************************/
    private static String MEDIA_DIRECTORY = APP_DIRECTORY + "avatar";/********* <---------------------- POMER AWQUI LA DIREECCION DEL DIRECTORIO EN EL CUAL SE GUARDAN LAS IMAGENES**********/

    private final int MY_PERMISSIONS = 100;
    private final int PHOTO_CODE = 200;
    private final int SELECT_PICTURE = 300;

    private String mPath; /*************<------------- Para configurar el paht, saber en q ruta se guardo nuestra imagen*********************************/



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        avatar = (ImageView)findViewById(R.id.imgPerfil);
        nombre = (TextView)findViewById(R.id.nombrePerfil);
        email = (TextView)findViewById(R.id.emailPerfil);
        vista = (CoordinatorLayout)findViewById(R.id.vistaPerfil);
        fab = (FloatingActionButton) findViewById(R.id.fab);

        if(mayRequestStoragePermission())
            fab.setEnabled(true);
        else
        fab.setEnabled(false);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showOptions();
                //uploadFile();
                //Snackbar.make(view, "aqui llamo a la funcion", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private boolean mayRequestStoragePermission(){

        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.M)
            return true;

        if((checkSelfPermission(WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) && (checkSelfPermission(CAMERA) == PackageManager.PERMISSION_GRANTED))
            return true;

        if((shouldShowRequestPermissionRationale(WRITE_EXTERNAL_STORAGE)) || (shouldShowRequestPermissionRationale(CAMERA))){
            Snackbar.make(vista,"Los permisos son necesarios para poder usar la aplicacion", Snackbar.LENGTH_INDEFINITE).setAction(android.R.string.ok, new View.OnClickListener() {
                @TargetApi(Build.VERSION_CODES.M)
                @Override
                public void onClick(View v) {
                    requestPermissions(new String[]{WRITE_EXTERNAL_STORAGE, CAMERA}, MY_PERMISSIONS);
                }
            }).show();
        }else{
            requestPermissions(new String[]{WRITE_EXTERNAL_STORAGE, CAMERA}, MY_PERMISSIONS);
        }

        return false;
    }

    private void showOptions(){

        final CharSequence[] option = {"Tomar foto", "Elegir de galeria", "Cancelar"}; //Opciones q contendra nuestro alertdialog
        final AlertDialog.Builder dialogo = new AlertDialog.Builder(Perfil.this);
        dialogo.setTitle("Elige una opción");
        dialogo.setItems(option, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(option[which] == "Tomar foto"){
                    openCamera();
                }else if(option[which] == "Elegir de galeria"){
                    Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.setType("image/*");
                    startActivityForResult(intent.createChooser(intent,"Seleciona una imagen"),SELECT_PICTURE);
                }else{
                    dialog.dismiss();
                }
            }
        });
        dialogo.show();
    }

    private void openCamera() {

        File file = new File(Environment.getExternalStorageDirectory(), MEDIA_DIRECTORY);
        boolean isDirectoryCreated = file.exists();

        if(!isDirectoryCreated){
            isDirectoryCreated = file.mkdirs();
        }

        if(isDirectoryCreated){
            Long timeStamp = System.currentTimeMillis() / 1000;
            String imageName = timeStamp.toString() + ".png";

            mPath = Environment.getExternalStorageDirectory() + File.separator + MEDIA_DIRECTORY + File.separator +imageName;

            File newFile = new File(mPath);

            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(newFile));
            startActivityForResult(intent, PHOTO_CODE);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("file_path", mPath);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        mPath = savedInstanceState.getString("file_path");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK){
            switch (requestCode) {
                case PHOTO_CODE:
                    MediaScannerConnection.scanFile(this,
                            new String[]{mPath}, null,
                            new MediaScannerConnection.OnScanCompletedListener() {

                        @Override
                        public void onScanCompleted(String path, Uri uri) {
                            Log.i("ExternalStorage", "Scanned " + path + ":");
                            Log.i("ExternalStorage", "-> uri = " + uri);
                        }
                    });

                    Bitmap bitmap = BitmapFactory.decodeFile(mPath);
                    uploadFile(mPath,"hola");
                    avatar.setImageBitmap(bitmap);
                    break;

                case SELECT_PICTURE:
                    Uri path = data.getData();
                    avatar.setImageURI(path);
                    break;
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == MY_PERMISSIONS){
            if(grantResults.length == 2 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(Perfil.this, "Permisos aceptados", Toast.LENGTH_SHORT).show();
                /********AQUI TIENE PUESTO SETENABLED()TRUE HABILITA EL BOTON*************/
            }
        }else{
            ShowExplanation();
        }
    }

    private void ShowExplanation() {
        AlertDialog.Builder dialogo = new AlertDialog.Builder(Perfil.this);
        dialogo.setTitle("Permisos denegados");
        dialogo.setMessage("Para usar las funciones de la app necesitas aceptar los permisos");
        dialogo.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", getPackageName(), null);
                intent.setData(uri);
                startActivity(intent);
            }
        });
        dialogo.setPositiveButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                finish();
            }
        });
        dialogo.show();
    }

    private void uploadFile(String ruta, String desc) {
/**********Meter Uri fileUri como parametro en el metodo uploapFile en introducirla en new File************/
        // create upload service client
        RestClient restClient = new RestClient();
        Retrofit retrofit = restClient.getRetrofit();

        EnviarImagen servicio = retrofit.create(EnviarImagen.class);

        // use the FileUtils to get the actual file by uri
        /****Meter direccion de imagen*****/
        File file = new File(ruta);

        // create RequestBody instance from file
        final RequestBody requestFile =
                RequestBody.create(MediaType.parse("multipart/form-data"), file);

        // MultipartBody.Part is used to send also the actual file name
        MultipartBody.Part body =
                MultipartBody.Part.createFormData("picture", file.getName(), requestFile);

        // add another part within the multipart request
        String descriptionString = desc;
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
