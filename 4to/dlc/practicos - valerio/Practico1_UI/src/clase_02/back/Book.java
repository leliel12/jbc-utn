package clase_02.back;

public class Book
{
  private int isbn;
  private String titulo;
  private float precio;

  public Book()
  {
  }

  public Book(int isbn, String titulo, float precio)
  {
    this.isbn = isbn;
    this.titulo = titulo;
    this.precio = precio;
  }

  public int getISBN()
  {
    return this.isbn;
  }

  public void setISBN(int isbn)
  {
    this.isbn = isbn;
  }

  public String getTitulo()
  {
    return this.titulo;
  }

  public void setTitulo(String titulo)
  {
    this.titulo = titulo;
  }

  public float getPrecio()
  {
    return this.precio;
  }

  public void setPrecio(float precio)
  {
    this.precio = precio;
  }
}