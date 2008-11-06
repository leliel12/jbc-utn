/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package borges.DOM;

/**
 *
 * @author juan
 */
public class Region extends GenericBorgesDOM {

    private String nombre;
    private Pais pais;

    public Region(String nombre, Pais pais) {
        this.nombre = nombre;
        this.pais = pais;
    }

    public Region() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }
}
