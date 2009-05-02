package clase_02.view;

import clase_02.back.Book;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class TableModel extends AbstractTableModel
{
  private ArrayList<Book> books;
  private Class[] types = { Integer.class, String.class, Float.class };
  private boolean[] canEdit = { false, false, false };

  public TableModel(Book[] books)
  {
    this.books = new ArrayList();
    for (int i = 0; i < books.length; ++i)
      this.books.add(books[i]);
  }

  public TableModel()
  {
  }

  public Class getColumnClass(int columnIndex)
  {
    return this.types[columnIndex];
  }

  public boolean isCellEditable(int rowIndex, int columnIndex)
  {
    return this.canEdit[columnIndex];
  }

  public int getColumnCount()
  {
    return 3;
  }

  public int getRowCount()
  {
    return this.books.size();
  }

  public Object getValueAt(int arg0, int arg1)
  {
    Book b = (Book)this.books.get(arg0);
    switch (arg1)
    {
    case 0:
      return Integer.valueOf(b.getISBN());
    case 1:
      return b.getTitulo();
    case 2:
      return Float.valueOf(b.getPrecio());
    }
    return null;
  }

  public String getColumnName(int arg0)
  {
    switch (arg0)
    {
    case 0:
      return "ISBN";
    case 1:
      return "Título";
    case 2:
      return "Precio";
    }
    return null;
  }
}