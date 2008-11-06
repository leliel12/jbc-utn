package com.itc.modelo;

import java.io.Serializable;

public class Carrito {
int cat_items;
String producto;
    public Carrito() {
    }

    public void setCat_items(int cat_items) {
        this.cat_items = cat_items;
    }

    public int getCat_items() {
        return cat_items;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getProducto() {
        return producto;
    }
}
