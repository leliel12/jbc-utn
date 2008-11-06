/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package borges.DOM;

/**
 *
 * @author juan
 */
public class Pagina extends GenericBorgesDOM{

    private String nombre;

    public Pagina(String nombre) {
        this.nombre = nombre;
    }

    public Pagina() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
