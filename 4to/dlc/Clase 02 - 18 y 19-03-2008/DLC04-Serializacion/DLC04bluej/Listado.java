/**
 * Una estructura de datos generica: la idea es poder almacenar referencias a "cualquier" clase de objeto, en forma secuencial, 
 * sin tener que redefinir la clase cuando cambia el tipo de objeto a almacenar. La clase definida aqui, admitira solo inserciones 
 * homogeneas:  no se admitiran objetos de distina clase en la misma estructura. Implementa Serializable, para poder ser grabada en 
 * una sola operacion.
 * 
 * @author Ing. Valerio Frittelli
 * @version Abril de 2004
 */
import java.io.*;
public class Listado implements Serializable
{
   /* 
    * 1.) La clase implementa Serializable, pero notar que no debemos desarrollar ningun metodo...
    */
   
   private Comparable v[]; 
   private int largo;       
   private int libre;       
   
   /**
    *  Por default, se crea un Listado de 10 elementos de largo, con referencias nulas
    */
   public Listado()
   {
      this(10);
   }
   
   /**
    *  Crea un Listado de n elementos de largo, con referencias nulas, siempre y cuando n sea mayor a cero. De lo contrario, el Listado
    *  se crea de tamano 10
    */
   public Listado(int n)
   {
      if (n <= 0) { n = 10; }
      largo = n;
      libre = 0;
      v = new Comparable[largo];
   }
   
   /**
    *  Metodo de acceso para el tamano del Listado. Provisto en lugar de "getLargo" por razones de claridad
    *  @return el tamano del Listado
    */
   public int length()
   {
      return largo;   
   }
   
   /**
    *  Metodo de acceso al indice de la primera casilla libre en el Listado. 
    *  @return el tamano del Listado
    */
   public int getPrimeraLibre()
   {
      return libre;   
   }
      
   /**
    *  Redefinicion del metodo toString heredado desde Object
    *  @return una cadena con el contenido del Listado para ser visualizado
    */
   public String toString()
   {
      String cad = "Contenido:";
      int i;
      for(i=0; i<libre; i++)
      {
        cad = cad + v[i].toString();   
      }
      return cad;
   }
   
   /**
    * Agrega un elemento al Listado, en la primera casilla libre. El metodo supone que el atributo "libre" indica el indice de la primera
    * casilla que esta desocupada en el arreglo, completandose de izquierda a derecha
    * @param x el objeto Comparable a anadir al Listado
    * @return true si el anadido tuvo exito - false si el objeto no pudo anadirse (por falta de lugar).
    */
   public boolean add (Comparable x)
   {
      boolean res = false;
      if (libre == 0 || (libre > 0 && libre < largo && x.getClass() == v[0].getClass())) 
      {
         v[libre] = x;
         libre++;
         res = true;
      }
      return res;
   }
   
   /**
    * Devuelve una referencia al objeto ubicado en la posicion i del Listado, sin removerlo del mismo.
    * @param i el indice de la casilla con el objeto a retornar
    * @return la referencia al objeto, o null si el indice estaba fuera de rango
    */
   public Comparable get (int i)
   {
     Comparable x = null;
     if (i >= 0 && i < libre)
     {
       x = v[i];   
     }
     return x;
   }

   /**
    * Busca un objeto en el Listado
    * @param x el objeto a buscar
    * @return el indice la casilla que contiene a x, si existe. Si x no existe en el Listado, retorna -1
    */
   public int buscar (Comparable x)
   {
     int i, ind = -1;
     for (i=0; i<libre; i++)
     {
        if (x.compareTo(v[i]) == 0)
        {
           ind = i;
           break;   
        }
     }
     return ind;
   }

   /** 
    * Ordena el Listado con el metodo Quick Sort
    */
   public void ordenar ()
   {
      // el metodo "quick" es privado, pues es un metodo auxiliar del metodo "ordenar"  
      quick (0, libre - 1);
   }

   private void quick (int izq, int der)
   {
       // metodo recursivo para ordenar el vector aplicando la tecnica de partir en sub-vectores y ordenar a cada uno
       Comparable x, y;
       int i, j;
       i = izq;
       j = der;
       x = v[(izq + der) / 2];
       do 
       {
            while (v[i].compareTo(x) < 0 && i < der) i++;
            while (x.compareTo(v[j]) < 0 && j > izq) j--;
            if (i<=j)
            {
            	  y = v[i];
            	  v[i] = v[j];
            	  v[j] = y;
            	  i++;
            	  j--;
            }
       }
       while (i <= j);
       if (izq < j) { quick (izq, j); }
       if (i < der) { quick (i, der); }
   }
}
