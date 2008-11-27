

import java.io.*;
import java.util.*;

/**
 * Una lista genérica
 * @author Ing. Valerio Frittelli (con modificaciones)
 * @version Febrero 2006
 */
public class Lista extends EstructuraDinamicaLineal
{
      /** 
       * Constructor por defecto
       */
      public Lista ()
      {
        super();
      }
      
	  /**
	  * Constructor con indicacion de polimorfismo como parametro
	  * @param polimorfica boolean que indica si es una lista polimorfica
	  */
	  public Lista(boolean polimorfica)
	  {
	  	super(polimorfica);
	  }      
      
      /**
       * Constructor con los parametros nombre y descripcion de la lista
       * @param nombre String que es el nombre de la lista
       * @param descripcion String que es la descripcion de la lista
       */
      public Lista (String nombre,String descripcion)
      {
      	super(nombre,descripcion);
      }
      
      /**
       * Constructor con los parametros nombre y descripcion de la lista e indicacion de polimorfismo
       * @param nombre String que es el nombre de la lista
       * @param descripcion String que es la descripcion de la lista
	   * @param polimorfica boolean que indica si es una lista polimorfica
       */
      public Lista (String nombre,String descripcion,boolean polimorfica)
      {
      	super(nombre,descripcion,polimorfica);
      }      

      /**
       * Constructor con el parametro nombre
       * @param nombre String que es el nombre de la lista
       */      
      public Lista (String nombre)
      {
      	super(nombre);
      }
      
      /**
       * Constructor con el parametro nombre e indicacion de polimorfismo
       * @param nombre String que es el nombre de la lista
	   * @param polimorfica boolean que indica si es una lista polimorfica
       */      
      public Lista (String nombre,boolean polimorfica)
      {
      	super(nombre,polimorfica);
      }      
      
      /**
       * Inserta objetos al principio de la lista pasados por parametro en un vector
       * @param x vector de objetos a ser insertados
       * @return vector de boolean indicando el exito de las inserciones
       */
      public boolean[] insertar(Comparable x[])
      {
      	if (x==null) return null;
      	boolean res[]=new boolean[x.length];
      	for (int i=0;i<res.length;i++)
      		res[i]=insertar(x[i]);
      	return res;
      }
      
      /**
       * Inserta objetos en forma ordenada pasados por parametro en un vector
       * @param x vector de objetos a ser insertados
       * @return vector de boolean indicando el exito de las inserciones
       */
      public boolean[] insertarOrdenado(Comparable x[])
      {
      	if (x==null) return null;
      	boolean res[]=new boolean[x.length];
      	for (int i=0;i<res.length;i++)
      		res[i]=insertarOrdenado(x[i]);
      	return res;
      }      
      
      /**
       * Inserta objetos al final de la lista pasados por parametro en un vector
       * @param x vector de objetos a ser insertados
       * @return vector de boolean indicando el exito de las inserciones
       */      
      public boolean[] insertarUltimo(Comparable x[])
      {
      	if (x==null) return null;
      	boolean res[]=new boolean[x.length];
      	for (int i=0;i<res.length;i++)
      		res[i]=insertarUltimo(x[i]);
      	return res;
      }      
      
      /**
       * Inserta un nodo al principio de la lista
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
     * Inserta un nodo al principio de la lista, manteniendo la lista ordenada
     * @param x el objeto a almacenar en el nuevo nodo
     * @return boolean que indica el exito de la insercion
     */
      public boolean insertarOrdenado(Comparable x)
      {
        if (x!=null)
        {
           if (!esInsercionCorrecta(x)) return false;           
           Nodo p = frente, q = null;
           while(p!=null && x.compareTo(p.getInfo()) <= 0)
           {
              q = p;
              p = p.getNext();
           }
           
           Nodo nuevo = new Nodo(x, p);
           cantidadNodos++;
           idNodo++;
           if(q!=null){ q.setNext(nuevo); }
           else { frente = nuevo; }
           return true;
        }
        return false;
      } 
      
     /**
     * Inserta un nodo al final de la lista, manteniendo la lista ordenada
     * @param x el objeto a almacenar en el nuevo nodo
     * @return boolean que indica el exito de la insercion
     */      
      public boolean insertarUltimo(Comparable x)
      {
	    if (x==null) return false;
        if (!esInsercionCorrecta(x)) return false;           	    
		Nodo r=new Nodo();
		Nodo p=frente;
		while(p!=null&&p.getNext()!=null)
		{
			p=p.getNext();
		}
		r.setInfo(x);
		r.setNext(null);
		if (p!=null)
		{
			p.setNext(r);
		}
		else
		{
			frente=r;
		}      
		cantidadNodos++;	
		idNodo++;
		return true;
      }
      
      /**
       * Toma y elimina el primer nodo
       * @return una referencia al info del nodo eliminado, o null la lista estaba vacía
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
      * Elimina el primer nodo
      * @return boolean que indica el exito de la eliminacion
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
       * Retorna el Comparable del nodo de la posicion pasada por parametro
       * @param posicion int que es la posicion dentro de la lista
       * @return una referencia al Comparable ubicado en la posicion pasada por parametro, o null si no existiera
       */
      public Comparable getInfoEnPosicion (int posicion)
      {
		Comparable aux[]=getInfos();
      	if (posicion<0||posicion>=aux.length) return null;
      	Nodo nAux=buscar(aux[posicion]);
      	if (nAux==null) return null;
      	return nAux.getInfo();
      }      

	  /**
	  * Borra objetos de la lista
	  * @param x objetos a ser borrados
	  * @return vector de boolean indicando el exito de la operacion
	  */
	  public boolean[] borrar(Comparable[] x)
	  {
		if (x==null) return new boolean[0];
		boolean res[]=new boolean[x.length];
		for (int i=0;i<x.length;i++)
			res[i]=borrar(x[i]);
		return res;
	  }      	  

      /**
       * Elimina el nodo cuya posicion sea la pasada por parametro
       * @param pos la posicion del objeto a eliminar
       * @return boolean que indica el exito de la eliminacion
       */      
      public boolean borrar(int pos)
      {
      	Comparable aux[]=getInfos();
      	if (pos<0||pos>=aux.length) return false;
      	return borrar(aux[pos]);
      }

      /**
       * Elimina el nodo con info = x
       * @param x el objeto a eliminar
       * @return boolean que indica el exito de la eliminacion
       */
      public boolean borrar(Comparable x) 
      {
         if(x!=null)
         {
            if(frente!=null && x.getClass()!=frente.getInfo().getClass()) return false;
            
            Nodo p = frente, q = null;
            while(p!=null && x.compareTo(p.getInfo())!=0)
            {
               q = p;
               p = p.getNext();
            }
            
            if(p!=null)
            {
    	      cantidadNodos--;
              if(q!=null)
              {
                 q.setNext(p.getNext());   
              }
              else
              {
                 frente = p.getNext();   
              }
              return true;
            }
         }
         return false;
      } 

   /**
    * Redefine el metodo equals heredado de Object
    * @param o un Object que sera la lista a comparar
    * @return boolean que indica si la lista es igual a la pasada por parametro
    */	
	public boolean equals(Object o)
	{
		if (o.getClass()!=Lista.class) return false;
		Lista t=(Lista)o;
		return (this.hashCode()==t.hashCode());
	}

   /**
    * Define el metodo compareTo de la interface Comparable
    * @param o un Object que sera la lista a comparar
    * @return int que indica si el codigo de la lista es igual, menor o mayor que el codigo de la lista pasada por parametro
    */	
	public int compareTo(Object o)
	{
		if (o.getClass()!=Lista.class) return -1;
		Lista t=(Lista)o;
		return this.nombre.compareTo(t.nombre);
	}
}
