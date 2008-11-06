/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package borges.DOM;

/**
 *
 * @author juan
 */
public class Servicio extends GenericBorgesDOM{
    
    private String nombre;
    private float precio;

    public Servicio() {
    }

    public Servicio(String nombre, float precio) {
        this.nombre = nombre;
        this.precio = precio;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }
    
}
