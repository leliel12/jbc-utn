/**
 * Un main para probar distintas formas de leer un RandomAccessFile.
 * 
 * @author Ing. Valerio Frittelli
 * @version Marzo de 2008
 */
package test;
import java.io.*;
public class Principal03
{
    public static void main (String args[])
    {
       File fd = new File("secuencia.dat");
       RandomAccessFile f;
       
       int i;
       System.out.println("1. Se graban algunos n�meros enteros en el archivo...");
       try
       {	
           // abrir el archivo para leer y grabar
           f  = new  RandomAccessFile(fd, "rw");

           /* 
            * llevar el puntero al final... elimine el comentario de la instrucci�n si quiere 
            * que el archivo adquiera nuevos datos en cada ejecuci�n 
            */
           //f.seek( f.length() );	

           /** agrego mi parte del codigo
            * borrando la longitud del archivo
            */
           f.setLength(0);
           
           // se graban algunos datos
           for (i=0; i < 20; i++)
           {
               f.writeInt( i * 3 );
            }
            
            // se cierra el archivo
            f.close();
        }
        catch ( IOException e )
        {
            System.out.println("Error al grabar: " + e.getMessage());
            System.exit(1);
        }
        System.out.println("Hecho...\n");
   
        System.out.println("2. Se lee el archivo calculando el n�mero de datos...");
        try
        {	
            // abrir el archivo para leer y grabar
            f  = new  RandomAccessFile(fd, "r");

            // se calcula cu�ntos int contiene el archivo...
            // ...suponiendo que sabemos que s�lo se grabaron valores int...
            long n = f.length() / 4;

            // se leen todos los datos
            for (i=0; i < n; i++)
            {
                int x = f.readInt( );
                System.out.print(x + " ");
            }

            // se cierra el archivo
            f.close();
        }
        catch ( IOException e )
        {
            System.out.println("Error al leer: " + e.getMessage());
            System.exit(1);
        }
        System.out.println("\nHecho...\n");
        
        System.out.println("3. Se lee el archivo controlando el fin de archivo con getFilePointer() y length()...");
        try
        {	
            // abrir el archivo para leer y grabar
            f  = new  RandomAccessFile(fd, "r");

            // se leen todos los datos controlando el final del archivo
            while ( f.getFilePointer() < f.length() )
            {
                int x = f.readInt( );
                System.out.print(x + " ");
            }

            // se cierra el archivo
            f.close();
        }
        catch ( IOException e )
        {
            System.out.println("Error al leer: " + e.getMessage());
            System.exit(1);
        }
        System.out.println("\nHecho...\n");

        System.out.println("4. Se lee el archivo controlando el fin de archivo capturando la EOFException...");
        try
        {	
            // abrir el archivo para leer y grabar
            f  = new  RandomAccessFile(fd, "r");

            // se leen los datos controlando el final del archivo con un try-catch
            try
            {
                for( ; ; )
                {
                    int x = f.readInt( );
                    System.out.print(x + " ");
                }
            }
            catch(EOFException e)
            {
                // podemos dejar este bloque catch en blanco!!!	
            }

            // se cierra el archivo
            f.close();
        }
        catch ( IOException e )
        {
            System.out.println("Error al leer: " + e.getMessage());
            System.exit(1);
        }
        System.out.println("\nHecho...\n");
        
        System.out.println("5. Se lee el archivo en forma salteada, usando seek()...");
        try
        {	
            	// abrir el archivo para leer
            	f  = new  RandomAccessFile(fd, "r");

            	// se leen los datos en forma salteada, usando seek()
            	try
            	{
            	    for( long pos = 0;   ; pos = f.getFilePointer() + 4 )
            	    {
            	        f.seek(pos);
            	        int x = f.readInt( );
            	        System.out.print(x + " ");
            	     }
            	}
            	catch(EOFException e)
            	{
            	}

            	// se cierra el archivo
            	f.close();
        }
        catch ( IOException e )
        {
            	System.out.println("Error al leer: " + e.getMessage());
            	System.exit(1);
        }
        System.out.println("\nHecho...\n");
    }
}
