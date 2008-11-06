/**
 * Clase para testear métodos de la clase RandomAccessFile.
 * 
 * @author Ing. Valerio Frittelli
 * @version Marzo de 2008
 */
package test;
import java.io.*;
public class RAF
{
    private File fd = new File ("prueba.dat");
    private RandomAccessFile f;

    // algunos atributos para testear métodos de la clase RandomAccessFile
    private int codigo = 0;
    private String nombre = "desconocido";
    private float sueldo = 0.0f;
    private byte edad = 0;

    public int getCodigo()
    {
        return codigo;   
    }
    
    public void setCodigo(int c)
    {
        codigo = c;   
    }
    
    public String getNombre()
    {
        return nombre;   
    }
    
    public void setNombre(String n)
    {
        nombre = n;   
    }
    
    public float getSueldo()
    {
        return sueldo;   
    }
    
    public void setSueldo(float f)
    {
        sueldo = f;   
    }
    
    public byte getEdad()
    {
        return edad;   
    }
    
    public void setEdad(byte e)
    {
        edad = e;   
    }
    
    /**
     * Retorna una cadena con el contenido de los atributos de prueba
     */
    public String toString()
    {
        return "\nCódigo: " + codigo + "\tNombre: " + nombre + "\tSueldo: " + sueldo + "\tEdad: " + edad;   
    }
    
    /**
     * Este método muestra la forma básica de abrir (y posiblemente crear) un
     * archivo con un RandomAccessFile, para luego grabar algunos valores en él.
     */
    public void grabar()
    {
        try
        {   
            // se abre el archivo para leer o grabar, dejando el file pointer al
            // inicio del mismo
            f = new RandomAccessFile(fd, "rw");
            
            // llevamos el file pointer al final para no pisar datos...
            f.seek( f.length() );
            
            // se graba un conjunto de datos, al final del archivo
            f.writeInt(codigo);
            f.writeUTF(nombre);
            f.writeFloat(sueldo);
            f.writeByte(edad);
            
            // se cierra el archivo
            f.close();
        }
        catch(IOException e)
        {
            System.out.println("Error al grabar: " + e.getMessage());
            System.exit(1);
        }
    }
    
    /**
     * Este método muestra la forma básica de leer datos desde un RandomAccessFile.
     */
    public void leer ()
    {
            try
            {
                // se abre el archivo, que debe existir previamente, sólo para leer...
                // ... el file pointer arranca apuntando al byte cero
                f = new RandomAccessFile(fd, "r");   
                
                // se leen los datos, en el mismo orden en que se supone que se grabaron
                codigo = f.readInt();
                nombre = f.readUTF();
                sueldo = f.readFloat();
                edad   = f.readByte();
                
                // se cierra el archivo
                f.close();
            }
            catch( IOException e )
            {
                System.out.println("Error al leer: " + e.getMessage());
                System.exit(1);
            }
    }
}
