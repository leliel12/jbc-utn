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
        private static Register <Alumno>   reg1;
        private static Alumno   alu;
 
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
            alu = new Alumno();
            
            do
            {
                System.out.println ("\n\nOpciones ABM de archivos");
                System.out.println ("1. Alta de un registro de Alumno");
                System.out.println ("2. Baja de un registro de Alumno (lógica)");
                System.out.println ("3. Ordenamiento del archivo");
                System.out.println ("4. Búsqueda binaria");
                System.out.println ("5. Listado de Alumnos");
                System.out.println ("6. Depuracion de Alumnos (bajas fisicas)");
                System.out.println ("7. Salir");
    
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
                             System.out.print("Ingrese el legajo del alumno a borrar: ");
                             x = Consola.readInt();
                             alu.setLegajo(x);
                             m1.remove( alu );
                             break;
            
                    case 3:
                             System.out.println("Se ordena el archivo...");
                             //m1.selectionSort( );
                             m1.quickSort();
                             
                             m1 = new RegisterFile <Alumno>("Alumnos.dat", "rw", new Alumno());
                             System.out.println("Hecho...");
                             break;
                             
                    case 4:
                             System.out.print("Ingrese el legajo del alumno a buscar: ");
                             x = Consola.readInt();
                             alu.setLegajo(x);
                             long b = m1.binarySearch( alu );
                             if ( b != -1 )System.out.print("Alumno encontrado (binarySearch()): " + alu);
                             else System.out.print("Alumno no encontrado (binarySearch())");
                             break;
            
                    case 5:  
                             System.out.print("Se muestra el archivo de Alumnos (incluyendo 'borrados')...");
                             mostrarTodo(m1, alu);
                             break;
            
                    case 6:   
                             System.out.println("Se eliminan registros de Alumnos marcados...");
                             m1.clean();
                             
                             m1 = new RegisterFile <Alumno> ("Alumnos.dat", "rw", new Alumno());
                             System.out.println("\nHecho...");
                             break;
                         
                    case 7: 
                             m1.close();
                             break;
                }
             }
             while (op != 7);
             System.exit(0);
       }
}
