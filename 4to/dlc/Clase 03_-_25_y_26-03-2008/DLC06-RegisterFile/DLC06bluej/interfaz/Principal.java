package interfaz;

/**
 * Clase para contener al método main.
 * 
 * @author Ing. Valerio Frittelli
 * @version Marzo de 2008
 */
import persistencia.*;
import datos.*;
import interfaz.*;
public class Principal
{
        private static RegisterFile <Alumno>   m1;
        private static RegisterFile <Articulo> m2;   
        
        private static Register <Alumno>   reg1;
        private static Register <Articulo> reg2;
        
        private static Alumno   alu;
        private static Articulo art;

        /**
         * Muestra el contenido de un archivo (incluidos los registros marcados como borrados) en consola estandar
         */
        public static void mostrarTodo (RegisterFile <? extends Grabable> m, Grabable obj)
        {
              Register r = new Register(obj);
              m.rewind();
              while (!m.eof())
              {
                   m.read(r);
                   mostrarRegistro(r);
              }
        }  
    
        /**
         * Muestra el contenido del archivo (sólo los registros que no estén marcados como borrados) en consola estandar
         */
        public static void mostrar (RegisterFile <? extends Grabable> m, Grabable obj)
        {
             Register r = new Register(obj);
             m.rewind();
             while (!m.eof())
             {
                  m.read(r);
                  if(r.isActive()) mostrarRegistro(r);
             }
        }  


       /**
        * Carga un legajo por teclado 
        */
       public static void cargarLegajo( )
       {
              System.out.print("Ingrese el Legajo: ");
              int legajo = Consola.readInt();
              alu.setLegajo(legajo);
       }
       
       /**
        * Carga un registro de Alumno por teclado 
        */
       public static void leerAlumno ( )
       { 
             cargarLegajo();
             System.out.print("Ingrese el Nombre: ");
             String nombre = Consola.readLine();
             alu.setNombre(nombre);
             System.out.print("Ingrese el Promedio: ");
             float promedio = (float)Consola.readDouble();
             alu.setPromedio(promedio);
       } 
       
       /**
        * Carga un codigo de Articulo por teclado 
        */
       public static void cargarCodigo( )
       {
              System.out.print("Ingrese el Código: ");
              int codigo = Consola.readInt();
              art.setCodigo(codigo);
       }
       
       /**
        * Carga un registro de Articulo por teclado 
        */
       public static void leerArticulo ( )
       { 
             cargarCodigo();
             System.out.print("Ingrese la descripción: ");
             String nombre = Consola.readLine();
             art.setDescripcion(nombre);
       } 
       
       
       /**
        * Muestra un registro por consola estandar 
        */
       public static void mostrarRegistro (Register reg)
       {
          System.out.print(reg.toString());
       }

    
    public static void main (String[] args)
    {
        int x, op;

        m1 = new RegisterFile <Alumno> ("Alumnos.dat", "rw", new Alumno());
        m2 = new RegisterFile <Articulo> ("Articulos.dat", "rw", new Articulo());
        
        alu = new Alumno();
        art = new Articulo();
        
        do
        {
            System.out.println ("\n\nOpciones ABM de archivos");
            System.out.println ("1.  Alta de un registro de Alumno");
            System.out.println ("2.  Alta de un registro de Articulo");
            System.out.println ("3.  Baja de un registro de Alumno (lógica)");
            System.out.println ("4.  Baja de un registro de Articulo (lógica)");
            System.out.println ("5.  Modificacion de un registro de Alumno");
            System.out.println ("6.  Modificacion de un registro de Articulo");
            System.out.println ("7.  Listado de Alumnos");
            System.out.println ("8.  Listado de Articulos");
            System.out.println ("9.  Depuracion de Alumnos (bajas fisicas)");
            System.out.println ("10. Depuración de Artículos (bajas físicas)");
            System.out.println ("11. Salir");

            System.out.print ("Ingrese opcion: ");
            op = Consola.readInt ();
            switch (op)
            {
                case 1:  
                         System.out.println("Ingrese los datos del Alumno: ");
                         leerAlumno();
                         m1.add( alu );
                         break;   
                
                case 2:  
                         System.out.println("Ingrese los datos del Artículo: ");
                         leerArticulo();
                         m2.add( art );
                         break;   
                         
                case 3:  
                         System.out.print("Ingrese el legajo del alumno a borrar: ");
                         x = Consola.readInt();
                         alu.setLegajo(x);
                         m1.remove( alu );
                         break;
        
                case 4:  
                         System.out.print("Ingrese el código del artículo a borrar: ");
                         x = Consola.readInt();
                         art.setCodigo(x);
                         m2.remove( art );
                         break;
                         
                case 5:
                         System.out.print("Ingrese los datos para modificar un registro de Alumno: ");
                         leerAlumno();
                         m1.update( alu );
                         break;

                case 6:
                         System.out.print("Ingrese los datos para modificar un registro de Artículo: ");
                         leerArticulo();
                         m2.update( art );
                         break;
        
                case 7:  
                         System.out.print("Se muestra el archivo de Alumnos (incluyendo 'borrados')...");
                         mostrarTodo(m1, alu);
                         break;
        
                case 8:  
                         System.out.print("Se muestra el archivo de Articulos (incluyendo 'borrados')...");
                         mostrarTodo(m2, art);
                         break;
                         
                case 9:   
                         System.out.println("Se eliminan registros de Alumnos marcados...");
                         m1.clean();
                         
                         m1 = new RegisterFile <Alumno> ("Alumnos.dat", "rw", new Alumno());
                         System.out.println("\nOperacion terminada...");
                         break;
                     
                case 10:   
                         System.out.println("Se eliminan registros de Articulos marcados...");
                         m2.clean();

                         m2 = new RegisterFile <Articulo> ("Articulos.dat", "rw", new Articulo());
                         System.out.println("\nOperacion terminada...");
                         break;
                       
                case 11: 
                         m1.close();
                         m2.close();
                         break;
            }
         }
         while (op != 11);
         System.exit(0);
    }
}
