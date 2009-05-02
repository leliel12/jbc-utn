package clase_02.back;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;

public class Gears
{
  private File file;
  private RandomAccessFile r;

  public Gears(String pathOfFile)
    throws FileNotFoundException
  {
    this.file = new File(pathOfFile);
    this.r = new RandomAccessFile(getFile(), "rw");
  }

  public void addLibro(Book book)
    throws IOException
  {
    long l = this.r.length();
    this.r.seek(l);
    this.r.writeInt(book.getISBN());
    this.r.writeUTF(book.getTitulo());
    this.r.writeFloat(book.getPrecio());
    System.out.println("add: " + book.getTitulo());
  }

  public Book[] getLibroByName(String titulo)
    throws IOException
  {
    titulo = titulo.toLowerCase();
    this.r.seek(0L);
    ArrayList book = new ArrayList();
    try
    {
      Book b = new Book();
      b.setISBN(this.r.readInt());
      b.setTitulo(this.r.readUTF());
      b.setPrecio(this.r.readFloat());
      if (b.getTitulo().toLowerCase().matches(titulo))
        book.add(b);
    }
    catch (IOException ex) {
    }
    return ((Book[])book.toArray(new Book[book.size()]));
  }

  public Book[] getLibroByISBN(int isbn)
    throws IOException
  {
    this.r.seek(0L);
    ArrayList book = new ArrayList();
    try
    {
      Book b = new Book();
      b.setISBN(this.r.readInt());
      b.setTitulo(this.r.readUTF());
      b.setPrecio(this.r.readFloat());
      if (b.getISBN() == isbn)
        book.add(b);
    }
    catch (IOException ex) {
    }
    return ((Book[])book.toArray(new Book[book.size()]));
  }

  public Book[] getAllLibros()
    throws IOException
  {
    this.r.seek(0L);
    ArrayList book = new ArrayList();
    try
    {
      Book b = new Book();
      b.setISBN(this.r.readInt());
      b.setTitulo(this.r.readUTF());
      b.setPrecio(this.r.readFloat());
      book.add(b);
    } catch (IOException ex) {
    }
    return ((Book[])book.toArray(new Book[book.size()]));
  }

  public File getFile()
  {
    return this.file;
  }
}