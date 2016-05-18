package com.example.ivan.apptivate.controlador;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.PUT;
import retrofit2.http.Part;

/**
 * Created by ivan on 18/05/2016.
 */
public interface EnviarImagen {

    @Multipart
    @PUT("php/subirimg.php")
    Call<String> upload(@Part("photo") RequestBody photo, @Part("description") RequestBody description);
}
