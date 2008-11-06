package com.itc;

import java.util.Hashtable;


public class ClienteBean {

    private String razonSocial = "Panadería";
    private int cuit = 30202020;
    private Hashtable <Integer, String> hashCliente = new Hashtable <Integer, String>();

    public ClienteBean() { agregar();    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setCuit(int cuit) {
        this.cuit = cuit;
    }

    public int getCuit() {
        return cuit;
    }

    public void addClientes(int cuit, String razonSocial) {
        this.hashCliente.put(cuit, razonSocial);
    }

    public Hashtable<Integer, String> getHashCliente() {
        return hashCliente;
    }
    
 
    
    private void agregar(){
        addClientes(25717076, "Panaderia SRL");
        addClientes(25717012, "ITC");
        addClientes(3252122, "Cluster");
    }
} // Fin de la clase.

