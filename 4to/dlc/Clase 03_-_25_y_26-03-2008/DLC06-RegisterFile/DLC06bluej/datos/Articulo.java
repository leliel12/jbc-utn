package datos;

/**
 * Representa un art�culo a la venta en un comercio cualquiera, que podr� ser usado dentro de un 
 * Register para grabar en un RegisterFile. Como Grabable extiende a Comparable, la clase Articulo debe 
 * dar una implementaci�n de compareTo().
 * 
 * @author Ing. Valerio Frittelli
 * @version Marzo de 2008
 */

import java.io.*;
import javax.swing.*;
import persistencia.*;

public class Articulo implements Grabable
{
       private int     codigo;       // 4 bytes en disco
       private String  descripcion;  // una cadena, que queremos sea de 50 caracteres m�ximo = 100 bytes en disco
       
       /**
        * Constructor por defecto. Los atributos quedan con valores por default.
        */
       public Articulo ()
       {
       }
       
       /**
        * Inicializa cada atributo de acuerdo a los par�metros.
        */
       public Articulo (int cod, String nom)
       {
           codigo   = cod;
           descripcion = nom;
       }
       
       /**
        * Accede al valor del codigo.
        * @return el valor del atributo codigo.
        */
       public int getCodigo()
       {
          return codigo;
       }

       /**
        * Accede al valor de la descripci�n.
        * @return el valor del atributo descripci�n.
        */
       public String getDescripcion()
       {
          return descripcion;
       }
       
       /**
        * Cambia el valor del codigo.
        * @param c el nuevo valor del atributo codigo.
        */
       public void setCodigo (int c)
       {
          codigo = c;
       }

       /**
        * Cambia el valor de la descripcion.
        * @param nom el nuevo valor del atributo descripcion.
        */
       public void setDescripcion (String nom)
       {
          descripcion = nom;
       }

       /**
        *  Calcula el tama�o en bytes del objeto, tal como ser� grabado. Pedido por Grabable.
        *  @return el tama�o en bytes del objeto.
        */
       public int sizeOf()
       {
             int tam = 104;  // 4 + 100 �Alguna duda?
             return tam;
       }
    
       /**
        *  Indica c�mo grabar un objeto. Pedido por Grabable.
        *  @param el archivo donde ser� grabado el objeto.
        */
       public void grabar (RandomAccessFile a)
       {
           try
           {
                a.writeInt(codigo);
                RegisterFile.writeString (a, descripcion, 50);
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
                codigo  = a.readInt();
                descripcion  = RegisterFile.readString(a, 50).trim();
           }
           catch(IOException e)
           {
                JOptionPane.showMessageDialog(null, "Error al leer el registro: " + e.getMessage());
                System.exit(1);
           }
       }
        
       /**
        * Compara dos objetos de la clase Articulo. 
        * @return 0 si los objetos eran iguales, 1 si el primero era mayor, -1 en caso contrario.
        * @param x el objeto contra el cual se compara.
        */
       public int compareTo (Object x)
       {
         Articulo a = (Articulo) x;
         return this.codigo - a.codigo;
       } 


       /**
        *  Redefinici�n del heredado desde Object. Considera que dos Articulos son iguales si sus c�digos lo son
        *  @param x el objeto contra el cual se compara.
        *  @return true si los c�digos son iguales, false en caso contrario.
        */
       public boolean equals (Object x)
       {
           if(x == null) return false;
           
           Articulo a = (Articulo)x;
           return (codigo == a.codigo);
       }
       
       /**
        *  Redefinici�n del heredado desde Object. La convenci�n es si equals() dice que dos objetos son iguales, entonces
        *  hashCode() deber�a retornar el mismo valor para ambos.
        *  @return el hashCode del Articulo. Se eligi� el c�digo para ese valor.
        */
       public int hashCode ()
       {
           return codigo;
       }

       /**
        *  Redefinici�n del heredado desde Object. 
        *  @return una cadena representando el contenido del objeto.
        */
       public String toString()
       {
           return "\nCodigo: " + codigo + "\tDescripcion: " + descripcion;     
       }
}
