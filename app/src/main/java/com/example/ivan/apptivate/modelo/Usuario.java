package com.example.ivan.apptivate.modelo;

/**
 * Created by ivan on 18/04/2016.
 */
public class Usuario {

    int id;
    String username;
    String password;
    String email;
    String urlImg;
    public static String nombreVista, emailVista;
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

    public String getUrlImg() {
        return urlImg;
    }

    public void setUrlImg(String urlImg) {
        this.urlImg = urlImg;
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
