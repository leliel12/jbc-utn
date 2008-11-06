/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package borges.DOM;

/**
 *
 * @author juan
 */
public class Libro extends GenericBorgesDOM{

    private String isbn;
    private String autor;
    private String editorial;
    private String titulo;
    private float precio;

    public Libro(String isbn, String autor, String editorial, String titulo, float precio) {
        this.isbn = isbn;
        this.autor = autor;
        this.editorial = editorial;
        this.titulo = titulo;
        this.precio = precio;
    }

    public Libro(){}
    
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

}
