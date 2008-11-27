import java.io.*;
import java.util.*;

/**
 * Clase abstracta que es una estructura de datos dinamica lineal
 */
public abstract class EstructuraDinamicaLineal extends EstructuraDinamica
{
      /** Nodo a traves del cual se accede a todos los nodos */
      protected Nodo frente;
      
      /** Cantidad de nodos */
      protected int cantidadNodos;
      
      /** Identificador del nuevo nodo a insertar en la lista */
      protected int idNodo;
      
	/**
	* Constructor con indicacion de polimorfismo como parametro
	* @param polimorfica boolean que indica si es polimorfica
	*/
    public EstructuraDinamicaLineal(boolean polimorfica)
    {
		super();
		this.polimorfica=polimorfica;
        frente = null;
        cantidadNodos=0;
        idNodo=0;
    }

	/**
	* Constructor por defecto
	*/    
    public EstructuraDinamicaLineal()
    {
    	this(false);
    }
      
    /**
    * Constructor con los parametros nombre y descripcion de la estrucura de datos e indicacion de polimorfismo
    * @param nombre String que es el nombre de la estrucura de datos
    * @param descripcion String que es la descripcion de la estrucura de datos
    * @param polimorfica boolean que indica si es polimorfica
    */
    public EstructuraDinamicaLineal (String nombre,String descripcion,boolean polimorfica)
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
    public EstructuraDinamicaLineal (String nombre,boolean polimorfica)
    {
		this (nombre,"",polimorfica);
    }      
    
    /**
    * Constructor con los parametros nombre y descripcion de la estrucura de datos
    * @param nombre String que es el nombre de la estrucura de datos
    * @param descripcion String que es la descripcion de la estrucura de datos
    */
    public EstructuraDinamicaLineal (String nombre,String descripcion)
    {
      	this(nombre,descripcion,false);
    }

    /**
    * Constructor con el parametro nombre
    * @param nombre String que es el nombre de la estrucura de datos
    */      
    public EstructuraDinamicaLineal (String nombre)
    {
		this();
		this.nombre=nombre;
    }        
      
    /**
    * Acceso a la dirección del ultimo Nodo
    * @return dirección del ultimo Nodo
    */
    public Nodo getFrente()
    {
         return frente;   
    }
      
    /**
    * Cantidad de nodos
    * @return cantidad de nodos
    */
    public int getCantidadNodos()
    {
     	return cantidadNodos;
    }
    
    /**
    * Cantidad de elementos
    * @return cantidad de elementos
    */
    public int getCantidadElementos()
    {
     	return getCantidadNodos();
    }    
    
    /**
    * Cantidad de nodos
    * @return cantidad de nodos
    */
    public int longitud()
    {
     	return cantidadNodos;
    }    
      
    /**
    * Identificador del nuevo nodo
    * @return identificador del nuevo nodo
    */
    public int getIdNodo()
    {
      	return idNodo;
    }
    
    /**
    * Identificador del nuevo nodo
    * @return identificador del nuevo nodo
    */
    public int getId()
    {
      	return idNodo;
    }    
      
    /**
    * Modifica la dirección del primer Nodo
    * @param p nueva dirección del primer Nodo
    */
    public void setFrente(Nodo p)
    {
         frente = p;   
    }
      
    /**
    * Modifica la cantidad de nodos
    * @param c nueva cantidad de nodos
    */
    public void setCantidadNodos(int c)
    {
    	cantidadNodos=c;
    }  
          
    /**
    * Modifica el identificador del nuevo nodo
    * @param c nuevo identificador de nuevo nodo
    */
    public void setIdNodo(int c)
    {
     	idNodo=c;
    }      
      
	/**
	 * Acceso al nombre de la clase de los nodos (recomendado cuando es homomorfica)
	 * @return nombre de la clase de los nodos
	 */
	public String getNombreClase()
	{
		if (cantidadNodos==0) return "";
		if (!polimorfica) return frente.getInfo().getClass().getName();
		return "Estructura de datos polimorfica"+"\n"+GestionString.getStringVector(getNombreClases());
	}

	/**
	 * Acceso a la clase de los nodos cuando la estructura es homomorfica
	 * @return clase de los nodos
	 */	
	public Class getClase()
	{
		if (cantidadNodos==0) return null;
		if (!polimorfica) return frente.getInfo().getClass();
		return null;
	}

	/**
	 * Acceso a los nombres de las clases de los nodos (recomendado cuando es polimorfica)
	 * @return vector de String con los nombres de la clases de los nodos
	 */	
	public String[] getNombreClases()
	{
		if (cantidadNodos==0) return null;
		String aux[]=null;
		if (!polimorfica)
		{
			aux= new String[1];
			aux[0]=frente.getInfo().getClass().getName();
		} else
		{
			Lista lista=new Lista();
			Nodo p=frente;
			String aux1="";
			while (p!=null)
			{
				aux1=p.getInfo().getClass().getName();
				if (!lista.existe(aux1))
				{
					lista.insertar(aux1);
				}
				p=p.getNext();
			}
			aux=GestionString.getStringsLista(lista);
		}
		return aux;
	}
	
    /**
    * Busca un nodo en la Respaldo que contenga al objeto x 
    * @param x el objeto a buscar
    * @return una referencia al nodo que contiene a x, si existía tal nodo, o null si no existía
    */
    public Nodo buscar (Comparable x)
    {
        Nodo p = null;
        if (x!=null)
      {
            if(frente!=null && x.getClass()!=frente.getInfo().getClass()) return p;
            
            p = frente;
            while(p!=null)
          {
               Comparable y = p.getInfo();
               if(x.compareTo(y)==0) 
               {
                 break; 
               }   
               p = p.getNext();   
          }
      }
        return p;
    }
      
    /**
    * Verifica si existe un nodo en la instancia de la estrucura de datos que contenga al objeto x 
    * @param x el objeto a buscar
    * @return boolean indicando si se encontro o no el objeto pasado por parametro
    */
    public boolean existe (Comparable x)
    {
        Nodo p = null;
        if (x!=null)
      {
            if(frente!=null && x.getClass()!=frente.getInfo().getClass()) return false;
            
            p = frente;
            while(p!=null)
          {
               Comparable y = p.getInfo();
              	if(x.compareTo(y)==0) 
             	{
                  break; 
             	}   
               p = p.getNext();   
          }
            if (p==null) return false;
            return true;
      }
        return false;
    }      

    /**
    * Retorna un vector conteniendo los Info de los nodos de la Respaldo
    * @return el vector que continene los info
    */      
    public Comparable[] getInfos()
    {
       	 Comparable c[]=new Comparable[cantidadNodos];
       	 int i=0;
       	 Nodo p = frente;
       	 while(p != null)
       {
            c[i]=p.getInfo();
            p = p.getNext();
            i++;
       }
         return c;
     }

   /**
    * Redefine el metodo hashCode heredado de Object
    * @return el codigo int que identifica a la instancia de la estrucura de datos
    */
	public int hashCode()
	{
		return nombre.hashCode();
	}
	
    /**
    * Redefine el método toString
    * @return el contenido convertido a String
    */
    public String toString()
    {
         Nodo p = frente;
         String aux="Nombre: "+nombre+"\n"+"cantidad nodos: "+cantidadNodos+"\n"+"id nodo: "+idNodo+"\n";
         aux+="Contenido: "+"\n";
         String res="";
         while(p != null)
       	 {
            res = res + p.toString()+"\n";
            p = p.getNext();
         }         
         return aux+res;
    }		 
      
    /**
    * Indica si el objeto que se quiere insertar es consistente con el polimorfismo definido en la estructura de datos
    * @param x Comparable cuya consistencia de homomorfismo es evaluada
    * @return boolean que indica si el objeto que se quiere insertar es consistente con el polimorfismo definido en la estructura de datos
    */
    protected boolean esInsercionCorrecta(Comparable x) 
    {
      	if (polimorfica) return true;
      	if (frente==null)return true;
        return x.getClass()==frente.getInfo().getClass();
    }
      
    /**
    * Elimina un nodo
    * @return boolean que indica el exito de la eliminacion
    */
    public abstract boolean borrar();
      
    /**
    * Toma y elimina un nodo
    * @return una referencia al nodo eliminado
    */
    public abstract Comparable tomar();
}