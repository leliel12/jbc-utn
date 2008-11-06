/*
 * Gears.java
 *
 * Created on 13 de marzo de 2008, 20:39
 *
 *  Copyright (C) -  2008 - juan
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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

/**
 * El corazon de la aplicación
 * @author JuanBC
 * @version 1.0
 */
public class Gears {

    //archivo a procesar
    private File file;
    //random acces file para el archivo
    private RandomAccessFile r;

    /**
     * crea una nueva instancia de la clase guears
     * @param pathOfFile url donde se encuentra el archivo
     * @throws java.io.FileNotFoundException
     */
    public Gears(String pathOfFile) throws FileNotFoundException {
        file = new File(pathOfFile);
        r = new RandomAccessFile(getFile(), RAFConstants.RW);
    }

    /**
     * agrega un libro al archivo
     * @param book libro a agregar
     * @throws java.io.IOException
     */
    public void addLibro(Book book) throws IOException {
        long l = r.length();
        r.seek(l);
        r.writeInt(book.getISBN());
        r.writeUTF(book.getTitulo());
        r.writeFloat(book.getPrecio());
        System.out.println("add: "+ book.getTitulo());
    }

    /**
     * busca un libro en el archivo por su titulo
     * @param titulo titulo del libro a buscar
     * @return libro con el titulo requerido
     * @throws java.io.IOException
     */
    public Book[] getLibroByName(String titulo) throws IOException {
        titulo=titulo.toLowerCase();
        r.seek(0);
        ArrayList<Book> book = new ArrayList<Book>();
        try {
            while (true) {
                Book b = new Book();
                b.setISBN(r.readInt());
                b.setTitulo(r.readUTF());
                b.setPrecio(r.readFloat());
                if(b.getTitulo().toLowerCase().matches(titulo)){
                    book.add(b);
                } //if
            }//while
        }catch(IOException ex) {}
        return book.toArray(new Book[book.size()]);
    }
    
    /**
     * busca un libro en el archivo dado su isbn
     * @param isbn isbn del libro a buscar
     * @return libro requerido
     * @throws java.io.IOException
     */
    public Book[] getLibroByISBN(int isbn) throws IOException {
        r.seek(0);
        ArrayList<Book> book = new ArrayList<Book>();
        try {
            while (true) {
                Book b = new Book();
                b.setISBN(r.readInt());
                b.setTitulo(r.readUTF());
                b.setPrecio(r.readFloat());
                if(b.getISBN()==isbn){
                    book.add(b);
                } //if
            }//while
        }catch(IOException ex) {}
        return book.toArray(new Book[book.size()]);
    }
    
    /**
     * devuelve todos los libros almacenados en el archivo
     * @return todos los libros almacenados en el archivo
     * @throws java.io.IOException
     */
    public Book[] getAllLibros() throws IOException {
        r.seek(0);
        ArrayList<Book> book = new ArrayList<Book>();
        try {
            while (true) {
                Book b = new Book();
                b.setISBN(r.readInt());
                b.setTitulo(r.readUTF());
                b.setPrecio(r.readFloat());
                book.add(b);
            }//while
        }catch(IOException ex) {}
        return book.toArray(new Book[book.size()]);
    }

    /**
     * archivo que se esta operando
     * @return archivo que se esta operando
     */
    public File getFile() {
        return file;
    }
    
}
