/**
 * Representa un árbol de Huffman, implementado sobre un arreglo.
 * 
 * @author Ing. Valerio Frittelli
 * @version Octubre de 2004
 */
public class ArbolHuffman
{
    public static final int MAXSYMBS = 256;               // todos los valores ASCII...
    public static final int MAXNODES = 2 * MAXSYMBS - 1;  // cantidad total de nodos del árbol si usamos todos los ASCII    

    private int h;                                        // cantidad de signos (hojas) del árbol
    private CodigoHuffman codigos[];                      // acá guardamos los códigos Huffman de cada signo
    private NodoHuffman   arbol  [];                      // este es el arbol: los primeros "tam" casilleros son las hojas (signos) 
    private byte signos [];                               // acá guardamos los valores de los signos
    private ColaDePrioridad fila;                         // acá vamos ordenando las frecuencias
 
    /**
     *  Constructor por defecto. Crea un árbol para 256 signos (todo el espectro ASCII)
     */ 
    public ArbolHuffman ()
    {
        this(MAXSYMBS); 
    }
 
    /**
     *  Constructor. Crea un árbol para una tabla de n signos
     */ 
    public ArbolHuffman (int n)
    {
        h = n;                 // cantidad de signos (o sea, hojas) a emplear
        int i, t = 2*h - 1;    // cantidad total de nodos

        codigos = new CodigoHuffman[h];
        for(i=0; i < h; i++)
        {
           codigos[i] = new CodigoHuffman();
        }
        
        arbol   = new NodoHuffman[t];
        for(i=0; i < t; i++)
        {
           arbol[i] = new NodoHuffman();
        }

        signos  = new byte[h];        
        for(i=0; i < h; i++)
        {
           signos[i] = 0;
        }
        
        fila = new ColaDePrioridad();
    }

    /**
     *  Devuelve la cantidad de signos que está manejando el árbol.
     *  @return la cantidad h de signos del árbol. 
     */
    public int length() 
    { 
        return h; 
    }

    /**
     *  Agrega un signo de la tabla al árbol.
     *  @param x el valor del signo a agregar.
     *  @param f la frecuencia del signo.
     *  @param i la posición del signo en el arreglo que representa al árbol.
     */
    public void setNodo(byte x, int f, int i)
    {
         setSigno(x, i);
         setFrecuencia(f, i);
         Frecuencia p = new Frecuencia(f, i);
         fila.insertar(p);   
    }

    /**
     *  Accede al valor de un signo específico.
     *  @param i el índice del signo a acceder.
     *  @return el valor del signo en la posición i
     */
    public byte getSigno(int i)
    { 
        return signos[i];
    }
    
    /**
     *  Cambia el valor del signo en la posición i.
     *  @param x el nuevo valor del signo.
     *  @param i el índice del signo a cambiar.
     */
    public void setSigno(byte x, int i)
    { 
        signos[i] = x;
    }

    /**
     *  Obtiene la frecuencia del signo en la posición i.
     *  @param i indice del signo cuya frecuencia se pide.
     *  @return el valor de la frecuencia de ese signo.
     */
    public int  getFrecuencia(int i)
    { 
        return arbol[i].getFrecuencia(); 
    }
    
    /**
     *  Cambia la frecuencia de un signo.
     *  @param f la nueva frecuencia.
     *  @param i el indice del signo cuya frecuencia se quiere cambiar.
     */
    public void setFrecuencia(int f, int i)
    { 
        arbol[i].setFrecuencia(f);
    }

    /**
     *  Obtiene el código Huffman de un signo.
     *  @param i el indice del signo cuyo código se quiere acceder
     *  @return una referencia al objeto que contiene al código
     */
    public CodigoHuffman getCodigo(int i)
    { 
        return codigos[i]; 
    }
    
    /**
     *  Cambia el código Huffman de un signo, salvo que el parámetro sea null, en cuyo
     *  caso deja el código que estaba.
     *  @param c una referencia al objeto con el nuevo código
     *  @param i el indice del signo cuyo código se desea cambiar
     */
    public void setCodigo(CodigoHuffman c, int i)
    { 
        if( c != null ) codigos[i] = c;
    }

    /**
     *  Obtiene el código Huffman de un signo.
     *  @param x el signo cuyo código se quiere acceder
     *  @return una referencia al objeto que contiene al código
     */
    public CodigoHuffman getCodigo(byte x)
    {
        int i = buscar (x);
        return getCodigo(i);
    }
    
    /**
     *  Cambia el código Huffman de un signo.
     *  @param c una referencia al objeto con el nuevo código
     *  @param x el signo cuyo código se desea cambiar
     */
    public void setCodigo(CodigoHuffman c, byte x)
    {
        if( c != null )
        {
          int i = buscar(x);
          codigos[i] = c;  
        }
    }
    
    /**
     *  Devuelve el arreglo que representa al Arbol de Huffman
     *  @return una referencia al arreglo que contiene al Arbol de Huffman
     */
    public NodoHuffman [] getArbol()
    {
        return arbol;   
    }


    /**
     *  Arma el árbol con todos los signos, y obtiene los códigos Huffman de cada uno
     */
    public void codificar()
    {
        int i, p, f, iraiz;
        Frecuencia p1, p2, raiz;
        for(p = h; p < 2*h-1; p++)
        {
            /*
                p apunta al siguiente nodo disponible en el árbol. Obtener los objetos
                p1 y p2 con menor frecuencia de la cola de prioridad
            */
            p1 = (Frecuencia)fila.borrar();
            p2 = (Frecuencia)fila.borrar();
    
            // ponemos ambos como hijos del nodo p
            int ip1 = p1.getIndice();
            int ip2 = p2.getIndice();
            arbol[ip1].setPadre(p);
            arbol[ip1].setLeft(true);
            arbol[ip2].setPadre(p);
            arbol[ip2].setLeft(false);
            f = arbol[ip1].getFrecuencia() + arbol[ip2].getFrecuencia();
            arbol[p].setFrecuencia(f);
            arbol[p].setIzquierdo(ip1);
            arbol[p].setDerecho(ip2);
            Frecuencia nf = new Frecuencia (f, p);
            fila.insertar(nf);
        }
    
        // en este punto sólo nos queda un nodo con padre == -1
        raiz = (Frecuencia)fila.borrar();
        iraiz = raiz.getIndice();
    
        // ahora extraemos los códigos del árbol y los guardamos en el vector de códigos
        for(i=0; i<h; i++)
        {
            p = i;
            while(p != iraiz)
            {
                if(arbol[p].isLeft())
                {
                    codigos[i].setBit((byte)0);
                }
                else
                {
                    codigos[i].setBit((byte)1);
                }
                p = arbol[p].getPadre();
            }
        }
    }

    /**
     *  Obtiene una tabla con los signos y sus códigos Huffman, en forma de String
     *  @return un string con la tabla de signos y sus códigos
     */
    public String toString()
    {
        StringBuffer res = new StringBuffer("Signos Codificados");
 
        for(int i=0; i<h; i++)
        {
            res.append("\nSigno: " + (char)signos[i] + "\tCodigo: " + codigos[i].toString());
        }
  
        return res.toString();
    }


    private int buscar(byte x)
    {
       int i;
       for(i = 0; i<h; i++)
       {
          if(x==signos[i]) return i;
       }
       return -1;
    }
}
