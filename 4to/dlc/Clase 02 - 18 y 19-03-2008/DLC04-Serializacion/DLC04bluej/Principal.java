/**
 *  Incluye un main para probar la forma de grabar y levantar objetos directamente desde un stream
 *  @author Ing. Valerio Frittelli
 *  @version Junio de 2004
 */
import java.io.*;
import java.util.*;
public class Principal
{
   private static Listado t;
   
   /*
    * 1.) El paquete java.io brinda un par de clases ObjectInputStream y ObjectOutputStream, las cuales permiten leer y grabar objetos
    * completos, respectivamente, sin que el programador deba preocuparse de como "serializar" ese objeto. Los metodos readObject() (de 
    * la clase ObjectInputStream) y writeObject() (de ObjectOutputStream) hacen la lectura y la grabacion en una sola instruccion del 
    * programador.
    * 
    * 2.) Esas clases son de alto nivel, y se conectan con dos clases de bajo nivel: FileInputStream y FileOutputStream, en forma 
    * similar a lo visto para archivos de texto. Ambas clases trabajan leyendo y enviando bytes desde y hacia el dispositivo.
    * 
    * 3.) Sin embargo, el solo uso de esas clases no basta para poder leer o grabar objetos desde un stream. Las clases cuyos objetos
    * vayan a ser leidos o grabados en streams en forma directa, deberan implementar la interfaz "Serializable" del paquete java.io. Esa 
    * clase de interfaz no pide ningun metodo... la clase solo debe indicar que la implementa. Una interfaz que no pide ningun metodo se 
    * suele conocer como una "interfaz de marcado": sirve para indicarle a la JVM que la clase que la implementa cumple con cierta 
    * propiedad (en el caso de Serializable, avisa que el objeto debe ser dividido en sus atributos y grabado "pieza por pieza").
    * 
    * 4.) No toda clase puede ser Serializable. Los objetos streams no son serializables. Los atributos marcados como static en una clase
    * no son serializables. Tampoco es serializable la misma definicion de la clase. Finalmente, si el programador desea que algun 
    * atributo de la clase no sea serializado, ese atributo debe ser marcado con la palabra "transient". Por ejemplo, en la clase
    * siguiente, el atributo "x" no sera grabado al serializar el objeto:
    *  
    *                         public class Ejemplo implements Serializable
    *                         {
    *                            private transient int x;
    *                            private int y;
    *                            ....
    *                         }
    * 
    * 5.) Notar que si una clase es Serializable, y esa clase tiene a su vez un atributo de otra clase, para que ese atributo sea a su vez
    * grabado la clase del mismo debe implementar Serializable ella misma. En el modelo que se presenta en este Proyecto, la clase Listado
    * se marca como Serializable, y tambien es Serializable la clase Persona (cuyas instancias seran guardadas en el Listado).
    */
  
  
   /**
    *  Graba un objeto de tipo Listado en disco
    *  @throws FileNotFoundException - IOException si el archivo no puede abrirse
    */
   static public void grabar() throws FileNotFoundException, IOException
   {
       t = new Listado();
       Persona a,b,c;
       a = new Persona("Juan", 20);
       b = new Persona("Luis", 30);
       c = new Persona("Ana", 40);
       t.add(a);
       t.add(b);
       t.add(c);
       System.out.println(t);
       
       // Si el archivo no existe, lo crea. Si ya existia, lo sobreescribe.
       // Otro constructor de la clase toma un parametro boolean. Si es true, abre el archivo en modo append.
       FileOutputStream ostream = new FileOutputStream("listado.tmp");
	   ObjectOutputStream p = new ObjectOutputStream(ostream);

	   p.writeObject(t);

	   p.flush(); // vuelca el contenido del buffer hacia el stream de salida
	   ostream.close();
   }

   /**
    *  Lee un objeto de tipo Listado desde disco
    *  @throws FileNotFoundException - IOException si el archivo no puede abrirse
    *  @throws ClassNotFoundException si hay un error de moldeo al levantar el objeto
    */
   static public void leer()throws FileNotFoundException, IOException, ClassNotFoundException
   {
       FileInputStream istream = new FileInputStream("listado.tmp");
	   ObjectInputStream p = new ObjectInputStream(istream);

       // readObject() lee un objeto desde el stream, y retorna una referencia a un Object. Debemos moldear al tipo correcto...
       t = (Listado)p.readObject();
       
       System.out.println(t);
       p.close();
	   istream.close();
   }
  

   public static void main(String [] args) throws FileNotFoundException, IOException, ClassNotFoundException
   {
       int op;
       do
       {
          System.out.println("1. Grabar");
          System.out.println("2. Recuperar");
          System.out.println("3. Probar");
          System.out.println("4. Salir");
          System.out.print("Ingrese: ");
          op = Consola.readInt();
          switch(op)
          {
             case 1:   grabar();  
                       break;

             case 2:   leer();
                       break;

             case 3:   System.out.println(t);
          }
       }
       while(op != 4);
   }
}

