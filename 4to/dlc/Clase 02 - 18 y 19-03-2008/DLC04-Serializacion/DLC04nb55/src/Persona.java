/**
 *  Representa una Persona con datos basicos. Los objetos de esta clase pueden compararse (pues la clase implementa Comparable) y pueden
 *  ser grabados directamente en un dispositivo a traves de un stream adecuado (pues implementa Serializable)
 *  @author Ing. Valerio Frittelli
 *  @version Junio de 2004
 */
import java.io.*;
class  Persona implements Comparable, Serializable
{
   private String nombre;
   private int edad;

   /**
    *   Crea un objeto con los valores tomados como parametro
    */
   public Persona(String nom, int ed)
   {
     nombre = nom;
     edad   = ed;
   }

   /**
    *  Acceso al nombre
    *  @return el nombre de la persona
    */
   public String getNombre()
   {
     return nombre;
   }

   /**
    *  Acceso a la edad
    *  @return el valor de la edad
    */
   public int getEdad()
   {
     return edad;
   }

   /**
    *  Cambia el nombre
    *  @param nom el nuevo nombre
    */
   public void setNombre(String nom)
   {
     nombre = nom;
   }

   /**
    *  Cambia la edad
    *  @param ed la nueva edad
    */
   public void setEdad(int ed)
   {
     edad = ed;
   }

   /**
    *  Redefinicion del metodo que se hereda de Object
    *  @return la representacion como String del contenido del objeto
    */
   public String toString()
   {
     return "\nNombre: " + nombre + "\tEdad: " + edad;
   }

    /**
     * Redefinicion del metodo pedido por la interfaz Comparable
     * @param x El objeto a comparar contra el parametro implicito
     * @return 0 si los nombres son iguales, negativo si el nombre del implicito es menor, positivo si es mayor.
     */
    public int compareTo(Object x)
    {
       Persona p = (Persona) x;
       return nombre.compareTo(p.getNombre());
    }
}
