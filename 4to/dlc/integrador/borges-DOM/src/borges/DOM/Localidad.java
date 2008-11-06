/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package borges.DOM;

/**
 *
 * @author juan
 */
public class Localidad extends GenericBorgesDOM {

    private String nombre;
    private Region region;

    public Localidad(String nombre, Region region) {
        this.nombre = nombre;
        this.region = region;
    }

    public Localidad() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }
}
