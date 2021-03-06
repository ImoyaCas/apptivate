package com.example.ivan.apptivate.modelo;

import android.graphics.Bitmap;

/**
 * Created by ivan on 18/04/2016.
 */
public class Usuario {

    int id;
    String username;
    String password;
    String email;
    String avatar;
    Bitmap imagen;
    public static String nombreVista, emailVista, urlImgVista;
    public static int  idVista;

    public Usuario() {}

    public Usuario(int id, String username, String password, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
        idVista = id;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
        urlImgVista = avatar;
    }

    public Bitmap getImagen() {
        return imagen;
    }

    public void setImagen(Bitmap imagen) {
        this.imagen = imagen;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
        nombreVista = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
        emailVista = email;
    }
}
