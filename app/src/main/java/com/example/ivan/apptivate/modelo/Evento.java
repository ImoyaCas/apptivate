package com.example.ivan.apptivate.modelo;

/**
 * Created by ivan on 21/04/2016.
 */
public class Evento {

    int id;
    String nombre;
    String lugar;
    String descripcion;
    double precio;
    int plazas;
    int idUsuario;
    String fecha;
    String hora;
    int plazasOcupadas;

    public Evento() {}

    public Evento(int id, String nombre, String lugar, String descripcion, double precio, int plazas, String fecha, String hora, int idUsuario) {
        this.id = id;
        this.nombre = nombre;
        this.lugar = lugar;
        this.descripcion = descripcion;
        this.precio = precio;
        this.plazas = plazas;
        this.fecha = fecha;
        this.hora = hora;
        this.idUsuario = idUsuario;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public int getPlazasOcupadas() {
        return plazasOcupadas;
    }

    public void setPlazasOcupadas(int plazasOcupadas) {
        this.plazasOcupadas = plazasOcupadas;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getPlazas() {
        return plazas;
    }

    public void setPlazas(int plazas) {
        this.plazas = plazas;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
}
