
import java.util.*;
import java.io.*;

/**
 * Clase abstracta que es una estructura de datos dinamica
 */
public abstract class EstructuraDinamica extends Dateable implements Comparable,Describible,Serializable
{
      /** Nombre por defecto*/
      public static final String NOMBRE_DEFECTO="nombre por defecto";
      
      /** Descripcion por defecto*/
      public static final String DESCRIPCION_DEFECTO="";
      
      /** Nombre asignado a la estructura de datos */
      protected String nombre;
      
      /** Descripcion asignada a la estructura de datos */
      protected String descripcion;
      
	  /** 
	   * Boolean que indica si la estructura es polimorfica.
	   * Si es polimorfico se pueden insertar elementos de clases diferentes.
	   */
	  protected boolean polimorfica;
	  
	/**
	* Constructor con indicacion de polimorfismo como parametro
	* @param polimorfica boolean que indica si es polimorfica
	*/
    public EstructuraDinamica(boolean polimorfica)
    {
        super();
        this.polimorfica=polimorfica;
        nombre="";
        descripcion="";      	
    }

	/**
	* Constructor por defecto
	*/    
    public EstructuraDinamica()
    {
    	this(false);
    }
      
    /**
    * Constructor con los parametros nombre y descripcion de la estrucura de datos e indicacion de polimorfismo
    * @param nombre String que es el nombre de la estrucura de datos
    * @param descripcion String que es la descripcion de la estrucura de datos
    * @param polimorfica boolean que indica si es polimorfica
    */
    public EstructuraDinamica (String nombre,String descripcion,boolean polimorfica)
    {
      	this(nombre);
      	this.descripcion=descripcion;
      	this.polimorfica=polimorfica;
    }

    /**
    * Constructor con el parametro nombre e indicacion de polimorfismo
    * @param nombre String que es el nombre de la estrucura de datos
	* @param polimorfica boolean que indica si es polimorfica
    */      
    public EstructuraDinamica (String nombre,boolean polimorfica)
    {
		this (nombre,"",polimorfica);
    }      
    
    /**
    * Constructor con los parametros nombre y descripcion de la estrucura de datos
    * @param nombre String que es el nombre de la estrucura de datos
    * @param descripcion String que es la descripcion de la estrucura de datos
    */
    public EstructuraDinamica (String nombre,String descripcion)
    {
      	this(nombre,descripcion,false);
    }

    /**
    * Constructor con el parametro nombre
    * @param nombre String que es el nombre de la estrucura de datos
    */      
    public EstructuraDinamica (String nombre)
    {
		this();
		this.nombre=nombre;
    }        
      
    /**
    * Acceso al nombre
    * @return nombre
    */
    public String getNombre()
    {
      	return nombre;
    }

    /**
    * Modifica el nombre
    * @param nombre String nuevo nombre
    */      
    public void setNombre(String nombre)
    {
      	if (nombre==null) nombre=NOMBRE_DEFECTO;
      	this.nombre=nombre;
    }
    
    /**
    * Acceso a la descripcion
    * @return descripcion
    */
    public String getDescripcion()
    {
    	return descripcion;
    }

    /**
    * Modifica la descripcion
    * @param descripcion String nueva descripcion
    */      
    public void setDescripcion(String descripcion)
    {
      	if (descripcion==null) descripcion=DESCRIPCION_DEFECTO;
      	this.descripcion=descripcion;
    }
      
    /**
    * Indica si la estructura de datos es polimorfica
    * @return boolean que indica si la estructura de datos es polimorfica
    */
    public boolean getPolimorfica()
    {
     	return polimorfica;
    }
      
    /**
    * Indica si la estructura de datos es polimorfica
    * @return boolean que indica si la estructura de datos es polimorfica
    */      
    public boolean esPolimorfica()
    {
    	return polimorfica;
    }
      
    /**
    * Modifica el boolean que indica si la estructura de datos es polimorfica
    * @param polimorfica boolean que indica si la estructura de datos es polimorfica
    */
    public void setPolimorfica(boolean polimorfica)
    {
    	this.polimorfica=polimorfica;
    }
      
	/**
	 * Acceso al nombre de la clase de los nodos (recomendado cuando es homomorfica)
	 * @return nombre de la clase de los nodos
	 */
	public abstract String getNombreClase();

	/**
	 * Acceso a la clase de los nodos cuando la estructura es homomorfica
	 * @return clase de los nodos
	 */	
	public abstract Class getClase();

	/**
	 * Acceso a los nombres de las clases de los nodos (recomendado cuando es polimorfica)
	 * @return vector de String con los nombres de la clases de los nodos
	 */	
	public abstract String[] getNombreClases();
	
    /**
    * Busca un nodo en la Respaldo que contenga al objeto x 
    * @param x el objeto a buscar
    * @return una referencia al nodo que contiene a x, si existía tal nodo, o null si no existía
    */
    public abstract Nodo buscar (Comparable x);
      
    /**
    * Verifica si existe un nodo en la instancia de la estrucura de datos que contenga al objeto x 
    * @param x el objeto a buscar
    * @return boolean indicando si se encontro o no el objeto pasado por parametro
    */
    public abstract boolean existe (Comparable x);

    /**
    * Retorna un vector conteniendo los Info de los nodos de la Respaldo
    * @return el vector que continene los info
    */      
    public abstract Comparable[] getInfos();

   /**
    * Redefine el metodo hashCode heredado de Object
    * @return el codigo int que identifica a la instancia de la estrucura de datos
    */
	public abstract int hashCode();
	
    /**
    * Redefine el método toString
    * @return el contenido convertido a String
    */
    public abstract String toString();
      
    /**
    * Inserta un elemento
    * @param x Comparable a insertar
    * @return boolean que indica el exito de la insercion
    */
    public abstract boolean insertar(Comparable x);
    
	/**
	 * Inserta nuevos nodos a la estructura
	 * @param x objetos a almacenar
	 * @return vector de boolean indicando el exito de la operacion
	 */
	  public boolean[] insertar(Comparable[] x)
	  {
	 	if (x==null) return new boolean[0];
		boolean res[]=new boolean[x.length];
		for (int i=0;i<x.length;i++)
			res[i]=insertar(x[i]);
		return res;
	  }    
}