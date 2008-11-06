/**
 * Contiene el main para testear el arbol de Huffman.
 * 
 * @author Ing. Valerio Frittelli
 * @version Septiembre de 2007
 */
package huffman;
public class Principal
{
    private static ArbolHuffman t;
    
    public static void cargar () 
    {
        int f, n = t.length();
        char x;
    
        for(int i = 0; i<n; i++)
        {
           System.out.print("Signo[" + i + "]: ");
           x = Consola.readChar();
           
           System.out.print("Frecuencia: ");
           f = Consola.readInt();
           
           byte b = (byte)x;
           t.setNodo(b,f,i);
        }
    }

    public static void main (String args[])
    {
             int n, op;
             System.out.print("Cuantos signos desea? ");
             n = Consola.readInt();
             t = new ArbolHuffman(n);
             
             do 
             {
                  System.out.println("    Arbol de Huffman");
                  System.out.print("\n1. Cargar signos");
                  System.out.print("\n2. Mostrar signos y codigos");
                  System.out.print("\n3. Determinar codigos");
                  System.out.print("\n4. Salir");
                  System.out.print("\n\n\n\t\tIngrese: ");
                  op = Consola.readInt();
                  
                  switch(op)
                  {
                     case 1: 
                             cargar ();
                             break;
            
                     case 2: 
                             System.out.print("\n\nLos signos son:\n\n");
                             System.out.println(t.toString());
                             break;
                
                     case 3: 
                             t.codificar();
                             System.out.println( "Hecho...");
                             break;
            
                            
                     case 4: ;
                  }
             }
             while (op != 4);
    } 
}
