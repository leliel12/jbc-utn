/**
 * Mostramos la forma de trabajar con archivos de texto en Java
 * @author Ing. Valerio Frittelli 
 * @version Junio de 2004
 */

import java.io.*;
public class Principal
{
      public static void main (String args[])
      {
            int op;
            // Creamos un objeto File para representar al archivo que sera accedido
            File f = new File ("archivo.dat");  // el archivo esta (o estara) ubicado por default en el directorio actual
    
            // Objetos para la comunicacion a bajo nivel
            FileWriter out;
            FileReader in;
    
            // Objetos para la comunicacion de alto nivel, con buffer
            BufferedWriter ofile;
            BufferedReader ifile;
    
            do 
            {
                  System.out.println("1. Crear");
                  System.out.println("2. Agregar");
                  System.out.println("3. Mostrar");
                  System.out.println("4. Salir");
                  System.out.print("\n\n\tIngrese: ");
                  op = Consola.readInt();
                  try
                  {
                     switch(op)
                     {
                       case 1:
                               // Hay que crear el archivo, y grabar algunas lineas en el
                               
                               // Este constructor de FileWriter crea el archivo si no existia, o lo sobreescribe si ya existia
                               out = new FileWriter(f);
                               
                               // El constructor de la clase de alto nivel toma como parametro al objeto de bajo nivel...
                               ofile = new BufferedWriter(out);
        
                               // Y de aca en mas se sigue con la clase de alto nivel, a traves de sus metodos.
                               ofile.write("Se agrego esta linea");
                               ofile.newLine();
                               ofile.write("Se agrego esta otra linea");
                               ofile.newLine();
        
                               // Se cierran los dos streams... primero el de alto nivel, luego el de bajo nivel.
                               ofile.close();
                               out.close();
        
                               System.out.println("Archivo creado... ");
                               break;
                               
                       case 2:
                               /*
                                * El segundo constructor de FileWriter toma el nombre del archivo (como String) y un boolean. Si el
                                * boolean es true, el archivo se abre en modo "append", preservando su contenido y permitiendo agregar 
                                * nuevos datos. Si es false, el archivo se sobreescribe.
                                */
                               String nombre = f.getName();
                               out = new FileWriter(nombre, true);
                               ofile = new BufferedWriter(out);
                               
                               ofile.write("Otra linea");
                               ofile.newLine();
                               ofile.write("Otra linea mas");
                               ofile.newLine();
                               
                               ofile.close();
                               out.close();
                               
                               System.out.println("Texto anadido al final... ");
                               break;
            
                       case 3:
                               // Misma idea, pero ahora buscamos leer en vez de grabar...
                               in = new FileReader(f);
                               ifile = new BufferedReader(in);
                               
                               System.out.println("Contenido del archivo:");
                               
                               // El metodo readLine retorna el String leido, o null si encontro el final del archivo
                               String linea = ifile.readLine();
                               while(linea!=null)
                               {
                                 System.out.println(linea);
                                 linea = ifile.readLine();
                               }
                               
                               ifile.close();
                               in.close();
                               
                               break;
            
                       case 4: ;
            
                     }
                  }
                  
                  catch(IOException e)
                  {
                     System.out.println("Error: " + e.getMessage() + " (al operar con el archivo)");
                  }
                  
                  catch(Exception e)
                  {
                     System.out.println("Ocurrio un error (" + e.getMessage() + ") con el programa. Revise");
                  }
            }
            while (op != 4);
      }
}
