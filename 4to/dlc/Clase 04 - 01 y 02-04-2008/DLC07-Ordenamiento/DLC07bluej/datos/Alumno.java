package datos;

/**
 * Representa un estudiante de una carrera cualquiera, que podr� ser usado dentro de un Register 
 * para grabar en un RegisterFile. Como Grabable extiende a Comparable, la clase Alumno debe dar una 
 * implementaci�n de compareTo().
 * 
 * @author Ing. Valerio Frittelli
 * @version Marzo de 2008
 */

import java.io.*;
import javax.swing.*;
import persistencia.*;

public class Alumno implements Grabable
{
       private int     legajo;      // 4 bytes en disco
       private String  nombre;      // una cadena, que queremos sea de 30 caracteres m�ximo = 60 bytes en disco
       private float   promedio;    // 4 bytes en disco 
       
       /**
        * Constructor por defecto. Los atributos quedan con valores por default.
        */
       public Alumno ()
       {
       }
       
       /**
        * Inicializa cada atributo de acuerdo a los par�metros.
        */
       public Alumno (int leg, String nom, float prom)
       {
           legajo   = leg;
           nombre   = nom;
           promedio = prom;
       }
       
       /**
        * Accede al valor del legajo.
        * @return el valor del atributo legajo.
        */
       public int getLegajo()
       {
          return legajo;
       }

       /**
        * Accede al valor del nombre.
        * @return el valor del atributo nombre.
        */
       public String getNombre()
       {
          return nombre;
       }
       
       /**
        * Accede al valor del promedio.
        * @return el valor del atributo promedio.
        */
       public float getPromedio()
       {
          return promedio; 
       }
       
       /**
        * Cambia el valor del legajo.
        * @param leg el nuevo valor del atributo legajo.
        */
       public void setLegajo (int leg)
       {
          legajo = leg;
       }

       /**
        * Cambia el valor del nombre.
        * @param nom el nuevo valor del atributo nombre.
        */
       public void setNombre (String nom)
       {
          nombre = nom;
       }

       /**
        * Cambia el valor del promedio.
        * @param prom el nuevo valor del atributo promedio.
        */
       public void setPromedio (float prom)
       {
          promedio = prom;
       }

       /**
        *  Calcula el tama�o en bytes del objeto, tal como ser� grabado. Pedido por Grabable.
        *  @return el tama�o en bytes del objeto.
        */
       public int sizeOf()
       {
             int tam = 68;  // 4 + 60 + 4. �Alguna duda?
             return tam;
       }
    
       /**
        *  Indica c�mo grabar un objeto. Pedido por Grabable.
        *  @param el archivo donde ser� grabado el objeto
        */
       public void grabar (RandomAccessFile a)
       {
           try
           {
                a.writeInt(legajo);
                RegisterFile.writeString (a, nombre, 30);
                a.writeFloat(promedio);
           }
           catch(IOException e)
           {
                JOptionPane.showMessageDialog(null, "Error al grabar el registro: " + e.getMessage());
                System.exit(1);
           }
       }

       /**
        *  Indica c�mo leer un objeto. Pedido por Grabable.
        *  @param a el archivo donde se har� la lectura.
        */
       public void leer (RandomAccessFile a)
       {
           try
           {
                legajo   = a.readInt();
                nombre   = RegisterFile.readString(a, 30).trim();
                promedio = a.readFloat();
           }
           catch(IOException e)
           {
                JOptionPane.showMessageDialog(null, "Error al leer el registro: " + e.getMessage());
                System.exit(1);
           }
       }
        
       /**
        * Compara dos objetos de la clase Alumno. 
        * @return 0 si los objetos eran iguales, 1 si el primero era mayor, -1 en caso contrario.
        * @param x el objeto contra el cual se compara.
        */
       public int compareTo (Object x)
       {
         Alumno a = (Alumno) x;
         return this.legajo - a.legajo;
       } 
        
       /**
        *  Redefinici�n del heredado desde Object. Considera que dos Alumnos son iguales si sus legajos lo son
        *  @param x el objeto contra el cual se compara.
        *  @return true si los legajos son iguales, false en caso contrario.
        */
       public boolean equals (Object x)
       {
           if(x == null) return false;
           
           Alumno a = (Alumno)x;
           return (legajo == a.legajo);
       }
       
       /**
        *  Redefinici�n del heredado desde Object. La convenci�n es si equals() dice que dos objetos son iguales, entonces
        *  hashCode() deber�a retornar el mismo valor para ambos.
        *  @return el hashCode del Alumno. Se eligi� el n�mero de legajo para ese valor.
        */
       public int hashCode ()
       {
           return legajo;
       }
       
       /**
        *  Redefinici�n del heredado desde Object. 
        *  @return una cadena representando el contenido del objeto.
        */
       public String toString()
       {
           return "\nLegajo: " + legajo + "\tNombre: " + nombre + "\tPromedio: " + promedio;     
       }
}
