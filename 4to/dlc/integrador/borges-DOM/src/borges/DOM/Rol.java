/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package borges.DOM;

import java.util.ArrayList;

/**
 *
 * @author juan
 */
public class Rol extends GenericBorgesDOM{

    private String nombre;
    private ArrayList<Pagina> paginas;

    public Rol(String nombre, Pagina[] paginas) {
        this();
        this.nombre = nombre;
    }
        
    public Rol(){
        this.paginas=new ArrayList<Pagina>();
    }
    
    public void addPaginas(Pagina[] p){
        for (int i = 0; i < p.length; i++) {
            this.addPagina(p[i]);
        }
    }
    
    public void addPagina(Pagina p){
        this.paginas.add(p);
    }
    
    public Pagina[] getPaginas(){
        return (Pagina[]) this.paginas.toArray();
    }
    
    public void setNombre(String nombre){
        this.nombre=nombre;
    }
    
    public String getNombre(){
        return nombre;
    }
    
}
