/**
 * Un main para testear la clase RAF.
 * 
 * @author Ing. Valerio Frittelli
 * @version Marzo de 2008
 */
package test;
public class Principal
{
    public static void main (String args[])
    {
        RAF m = new RAF();
        
        m.setCodigo(201);
        m.setNombre("Juan");
        m.setSueldo(1234.34f);
        m.setEdad((byte)25);
        System.out.print("Datos al grabar: ");
        System.out.println(m);
        m.grabar();
        
        m.setCodigo(0);
        m.setNombre("desconocido");
        m.setSueldo(0.0f);
        m.setEdad((byte)0);
        System.out.print("\nDatos reseteados: ");
        System.out.println(m);
        
        m.leer();
        System.out.print("\nDatos leídos del archivo: ");
        System.out.println(m);
    }
}
