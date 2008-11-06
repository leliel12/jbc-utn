/*
 * Book.java
 *
 * Created  2008, 20:39
 *
 *  Copyright (C) -  2008 - JuanBC
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

package clase_02.back;

/**
 * clase que representa un libro
 * @author JuanBC
 * @version 1.0
 */
public class Book {

    // isbn del libro
    private int isbn;
    //titulo del libro
    private String titulo;
    //precio del libro
    private float precio;

    /**
     * crea una nueva instancia de la clase libro 
     */
    public Book(){
        
    }
    
    /**
     * crea una nueva instancia de la clase libro
     * @param isbn codigo internacional del libro
     * @param titulo titulo del libro
     * @param precio precio del libro
     */
    public Book(int isbn, String titulo, float precio){
        this.isbn=isbn;
        this.titulo=titulo;
        this.precio=precio;
    }
    
    /**
     * codigo internacional del libro
     * @return isbn del libro
     */
    public int getISBN() {
        return isbn;
    }

    /**
     * ingresa un nuevo isbn para el libro
     * @param isbn codigo internacional del libro
     */
    public void setISBN(int isbn) {
        this.isbn = isbn;
    }

    /**
     * titulo del libro
     * @return titulo del libro
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * cambia el titulo del libro
     * @param titulo nuevo titulo para el libro
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * precio del libro
     * @return precio del libro
     */
    public float getPrecio() {
        return precio;
    }

    /**
     * nuevo precio para el libro
     * @param precio nuevo precio para el libro
     */
    public void setPrecio(float precio) {
        this.precio = precio;
    }
       
    
}
