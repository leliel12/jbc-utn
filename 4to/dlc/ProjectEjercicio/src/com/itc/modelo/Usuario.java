package com.itc.modelo;

import java.io.Serializable;

public class Usuario implements Serializable{

String nombre, password;
int dni;
    public Usuario(String n, String p, int d) {
        setNombre(n);
        setPassword(p);
        setDni(d);
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public int getDni() {
        return dni;
    }
}
