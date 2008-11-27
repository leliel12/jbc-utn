

import java.io.*;
import java.util.*;

/**
 * Una cola (FIFO) genérica
 * @version Diciembre de 2004
 */
public class Cola extends EstructuraDinamicaLineal
{
      /** 
       * Constructor por defecto
       */
      public Cola ()
      {
		super();
      }
      
	  /**
	  * Constructor con indicacion de polimorfismo como parametro
	  * @param polimorfica boolean que indica si es una cola polimorfica
	  */
	  public Cola(boolean polimorfica)
	  {
	  	super(polimorfica);
	  }      
      
      /**
       * Constructor con los parametros nombre y descripcion de la cola
       * @param nombre String que es el nombre de la cola
       * @param descripcion String que es la descripcion de la cola
       */
      public Cola (String nombre,String descripcion)
      {
      	super(nombre,descripcion);
      }
      
      /**
       * Constructor con los parametros nombre y descripcion de la cola e indicacion de polimorfismo
       * @param nombre String que es el nombre de la cola
       * @param descripcion String que es la descripcion de la cola
	   * @param polimorfica boolean que indica si es una cola polimorfica
       */
      public Cola (String nombre,String descripcion,boolean polimorfica)
      {
      	super(nombre,descripcion,polimorfica);
      }      

      /**
       * Constructor con el parametro nombre
       * @param nombre String que es el nombre de la cola
       */      
      public Cola (String nombre)
      {
      	super(nombre);
      }
      
      /**
       * Constructor con el parametro nombre e indicacion de polimorfismo
       * @param nombre String que es el nombre de la cola
	   * @param polimorfica boolean que indica si es una cola polimorfica
       */      
      public Cola (String nombre,boolean polimorfica)
      {
      	super(nombre,polimorfica);
      }      
      
      /**
       * Inserta un nodo en la cola
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
       * Toma y elimina el primer nodo insertado
       * @return una referencia al info  del ultimo nodo insertado o null la cola estaba vacía
       */
      public Comparable tomar() 
      {
        Nodo p=frente,q=null;
        Comparable aux = null;
        while (p!=null&&p.getNext()!=null)
        {
        	q=p;
        	p=p.getNext();
        }
        if (p!=null)
        {
        	aux=p.getInfo();
        	if (q!=null)
        	{
        		q.setNext(null);
        	} else
        	{
        		frente=null;
        	}
        	cantidadNodos--;
        }
        return aux;
      } 

      /**
       * Elimina el primer nodo insertado
       * @return true que indica que se borro el primer nodo insertado o false si la cola estaba vacía
       */      
      public boolean borrar() 
      {
        if (frente==null) return false;
        Nodo p=frente,q=null;
        while (p!=null&&p.getNext()!=null)
        {
        	q=p;
        	p=p.getNext();
        }
        if (p!=null)
        {
        	if (q!=null)
        	{
        		q.setNext(null);
        	} else
        	{
        		frente=null;
        	}
        }
		cantidadNodos--;
		return true;
      }       
	
   /**
    * Redefine el metodo equals heredado de Object
    * @param o un Object que sera la cola a comparar
    * @return boolean que indica si la cola es igual a la pasada por parametro
    */	
	public boolean equals(Object o)
	{
		if (o.getClass()!=Cola.class) return false;
		Cola t=(Cola)o;
		return (this.hashCode()==t.hashCode());
	}

   /**
    * Define el metodo compareTo de la interface Comparable
    * @param o un Object que sera la cola a comparar
    * @return int que indica si el codigo de la cola es igual, menor o mayor que el codigo de la cola pasada por parametro
    */	
	public int compareTo(Object o)
	{
		if (o.getClass()!=Cola.class) return -1;
		Cola t=(Cola)o;
		return this.nombre.compareTo(t.nombre);
	}
}
