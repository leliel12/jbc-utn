/**
 * Mostramos la forma de trabajar con archivos de texto en Java
 * @author Ing. Valerio Frittelli 
 * @version Junio de 2004
 */

  /*
   * 1.) Aqui mostramos en que forma dos clases "colaboran" entre ellas para acceder al contenido de un archivo, de forma que cada una
   * de ellas agrega capacidades y formato al trabajo de la otra. Normalmente, para acceder al contenido de un "archivo", se precisa 
   * el uso de un par de clases: una de ellas se encarga del trabajo de acceso "a bajo nivel" (comunicacion con el dispositivo, 
   * lectura o grabacion de un byte, etc.), y la otra del trabajo "de alto nivel" (comunicacion con la aplicacion, formateado de 
   * los bytes recibidos o a enviar, etc.) Se crea primero el objeto para la clase de bajo nivel usando su constructor, y luego se 
   * crea el objeto de la clase de alto nivel, pasandole a su constructor el objeto de "bajo nivel" antes creado. La clase de alto 
   * nivel provee metodos para leer o grabar valores, que se van pasando entre las clases que comunican la aplicacion con el 
   * dispositivo.
   * 
   * 2.) Las clases de control de flujo o streams estan claramente divididas entre clases de entrada y clase de salida. Las primeras
   * se usan para acceder a datos desde un dispositivo (y no para enviar datos hacia ellos) y las segundas para enviar datos pero no 
   * para accederlos. Si se desea en una aplicacion tanto leer como enviar, deben usarse dos conjuntos de clases: uno para enviar, y 
   * el otro para leer.
   * 
   * 3.) En el caso de los archivos de texto, lo normal es usar las siguientes clases:
   *        
   *     Para grabar:
   *        FileWriter:     stream de bajo nivel que permite enviar caracteres directamente hacia un dispositivo.
   *        BufferedWriter: stream de alto nivel con buffer, que se puede conectar con un FileWriter y mejorar su eficiencia.
   *        
   *     Para leer:
   *        FileReader:     stream de bajo nivel que permite leer caracteres directamente desde un dispositivo.
   *        BufferedReader: stream de alto nivel con buffer, que se puede conectar con un FileREader y mejorar su eficiencia.
   * 
   * 4.) La clase BufferedReader provee el metodo readLine() que permite leer un string desde el dispositivo al cual accede el 
   * FileReader correspondiente. Del mismo modo, la clase BufferedWriter provee dos metodos utiles: write() y newLine(). El primero
   * permite grabar una cadena en el dispositivo conectado al FileWriter correspondiente, y el segundo envia un caracter de salto
   * de linea a ese dispositivo.
   * 
   * 5.) Finalmente, notar que salvo la clase File, practicamente todas las otras clases del paquete java.io pueden provocar alguna
   * excepcion de la clase IOException (que es chequeada) al usar sus metodos. Por lo tanto, la aplicacion debe declarar esa 
   * posibilidad, o capturar la excepcion. 
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
