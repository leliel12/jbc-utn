/*
 * TableModel.java
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
package clase_02.view;

import clase_02.back.Book;
import java.util.ArrayList;

/**
 *  Modelo de la tabla para la libreria
 * @author juanbc
 * @version 1.0
 */
public class TableModel extends javax.swing.table.AbstractTableModel {

    // libros que carga la tabla
    private ArrayList<Book> books;
    //tipos de columna
    private Class[] types = new Class[]{java.lang.Integer.class, java.lang.String.class, java.lang.Float.class};
    //si una columna se puede editar
    private boolean[] canEdit = new boolean[]{false, false, false};

    /**
     * contructor
     * @param books vector de libros a ordenar
     */
    public TableModel(Book[] books) {
        this.books = new ArrayList<Book>();
        for (int i = 0; i < books.length; i++) {
            this.books.add(books[i]);
        }
    }

    /**
     * contructor por defecto
     */
    public TableModel(){}
    
    /**
     * retorna la clase de la columna
     * @param columnIndex numero de columna a verificar
     * @return clase de la columna
     */
    @Override
    public Class getColumnClass(int columnIndex) {
        return types[columnIndex];
    }

    /**
     * si una celda es editable o no
     * @param rowIndex indice la fila
     * @param columnIndex indice de la columna
     * @return true si es editable false si no
     */
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return canEdit[columnIndex];
    }

    /**
     * cantidad de columnas
     * @return cantidad de columnas
     */
    public int getColumnCount() {
        return 3;
    }

    /**
     * cuantos libros hay
     * @return cantidad de libros
     */
    public int getRowCount() {
        return this.books.size();
    }

    /**
     * valor de una celda determinada
     * @param arg0 fila
     * @param arg1 columna
     * @return valor de una celda
     */
    public Object getValueAt(int arg0, int arg1) {
        Book b = this.books.get(arg0);
        switch (arg1) {
            case 0:
                return b.getISBN();
            case 1:
                return b.getTitulo();
            case 2:
                return b.getPrecio();
            default:
                return null;
            }
    }
    
    public Book getValueAt(int row){
        return this.books.get(row);
    }
    
    /**
     * nombre de la columna
     * @param arg0 numero de columna
     * @return nombre de columna
     */
    @Override
    public String getColumnName(int arg0) {
        switch (arg0) {
            case 0:
                return "ISBN";
            case 1:
                return "Título";
            case 2:
                return "Precio";
            default:
                return null;
            }
    }
}
