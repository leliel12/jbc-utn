

import java.io.*;
import java.util.*;

/**
 * Una pila (LIFO) genérica
 * @version Diciembre de 2004
 */
public class Pila extends EstructuraDinamicaLineal
{
      /** 
      * Constructor por defecto
      */
      public Pila ()
      {
        super ();
      }
      
	  /**
	  * Constructor con indicacion de polimorfismo como parametro
	  * @param polimorfica boolean que indica si es una pila polimorfica
	  */
	  public Pila(boolean polimorfica)
	  {
	  	super(polimorfica);
	  }
	
      /**
       * Constructor con los parametros nombre y descripcion de la pila
       * @param nombre String que es el nombre de la pila
       * @param descripcion String que es la descripcion de la pila
       */
      public Pila (String nombre,String descripcion)
      {
      	super(nombre,descripcion);
      }
      
      /**
       * Constructor con los parametros nombre y descripcion de la pila e indicacion de polimorfismo
       * @param nombre String que es el nombre de la pila
       * @param descripcion String que es la descripcion de la pila
	   * @param polimorfica boolean que indica si es una pila polimorfica
       */
      public Pila (String nombre,String descripcion,boolean polimorfica)
      {
      	super(nombre,descripcion,polimorfica);
      }      

      /**
       * Constructor con el parametro nombre
       * @param nombre String que es el nombre de la pila
       */      
      public Pila (String nombre)
      {
      	super(nombre);
      }
      
      /**
       * Constructor con el parametro nombre e indicacion de polimofismo
       * @param nombre String que es el nombre de la pila
	   * @param polimorfica boolean que indica si la pila es polimorfica
       */      
      public Pila (String nombre,boolean polimorfica)
      {
      	super(nombre,polimorfica);
      }      
            
      /**
       * Inserta un nodo en la pila
       * @param x el objeto a almacenar en el nuevo nodo
       * @return boolean que indica el exito de la insercion
       */
      public boolean insertar(Comparable x)
      {
        if (x!=null)
        {
           if (!esInsercionCorrecta(x)) return false;           
           Nodo p = new Nodo(x, frente);
           frente = p;
           cantidadNodos++;
           idNodo++;
           return true;
        }
        return false;
      } 
      
      /**
       * Toma y elimina el ultimo nodo insertado
       * @return una referencia al info  del ultimo nodo insertado o null la pila estaba vacía
       */
      public Comparable tomar() 
      {
        Comparable p = null;
        if(frente != null)
        {
           p = frente.getInfo();
           frente = frente.getNext();
           cantidadNodos--;
        }  
        return p;
      } 

      /**
       * Elimina el ultimo nodo insertado
       * @return true que indica que se borro el ultimo nodo insertado o false si la pila estaba vacía
       */      
      public boolean borrar() 
      {
        if(frente != null)
        {
           frente = frente.getNext();
           cantidadNodos--;
           return true;
        }  
        return false;
      }       
      
   /**
    * Redefine el metodo equals heredado de Object
    * @param o un Object que sera la pila a comparar
    * @return boolean que indica si la pila es igual a la pasada por parametro
    */	
	public boolean equals(Object o)
	{
		if (o.getClass()!=Pila.class) return false;
		Pila t=(Pila)o;
		return (this.hashCode()==t.hashCode());
	}

   /**
    * Define el metodo compareTo de la interface Comparable
    * @param o un Object que sera la pila a comparar
    * @return int que indica si el codigo de la pila es igual, menor o mayor que el codigo de la pila pasada por parametro
    */	
	public int compareTo(Object o)
	{
		if (o.getClass()!=Pila.class) return -1;
		Pila t=(Pila)o;
		return this.nombre.compareTo(t.nombre);
	}
}
