/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package borges.DOM;

/**
 *
 * @author juan
 */
public class FormaPago extends GenericBorgesDOM{
    
    private String nombre;
    private float descuento;

    public FormaPago(String nombre, float descuento) {
        this.nombre = nombre;
        this.descuento = descuento;
    }

    public FormaPago() {
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getDescuento() {
        return descuento;
    }

    public void setDescuento(float descuento) {
        this.descuento = descuento;
    }
    
    
}
