

/**
 * Clase que proporciona metodos para la gestion de Strings
 * @version Diciembre de 2005
 */
public class GestionString implements ValoresPatron
{
   /**
    * Indica si un texto parametro se contiene alguno de los patrones parametro
    * @param textos texto a ser analizado
    * @param patron patrones a partir de los cuales se evaluza el texto
    * @return boolean que indica si un texto parametro se corresponde con los patrones parametro
    */
   public static boolean contieneString(String textos,String patron[])
   {
   		if (textos==null||patron==null) return false;
   		for (int i=0;i<patron.length;i++)
   			if (contieneString(textos,patron[i]))
   				return true;
   		return false;
   }
   
   /**
    * Indica si un texto parametro se contiene alguno de los patrones parametro en su cabecera
    * @param textos texto a ser analizado
    * @param patron patrones a partir de los cuales se evaluza el texto
    * @return boolean que indica si un texto parametro se corresponde con los patrones parametro
    */   
   public static boolean contieneStringComienzo(String textos,String patron[])
   {
   		if (textos==null||patron==null) return false;
   		for (int i=0;i<patron.length;i++)
   			if (contieneStringComienzo(textos,patron[i]))
   				return true;
   		return false;
   }

   /**
    * Indica si un texto parametro se contiene alguno de los patrones parametro en su cola
    * @param textos texto a ser analizado
    * @param patron patrones a partir de los cuales se evaluza el texto
    * @return boolean que indica si un texto parametro se corresponde con los patrones parametro
    */   
   public static boolean contieneStringFinal(String textos,String patron[])
   {
   		if (textos==null||patron==null) return false;
   		for (int i=0;i<patron.length;i++)
   			if (contieneStringFinal(textos,patron[i]))
   				return true;
   		return false;
   }
   
   /**
    * Verifica si un string contiene un patron (ambos pasados por parametro)
    * @param string que representa el String donde se busca el parametro
    * @param patron que representa el String cuya existencia es buscada
    * @return boolean que indica si fue encontrado el patron en el string
    */
	public static boolean contieneString(String string,String patron)	
	{
		if (string==null||patron==null) return false;
		if (patron.equals("")) return true;
		for (int i=0;(i+patron.length())<=string.length();i++)
		{
			if (string.substring(i,patron.length()+i).equals(patron))
			{
				return true;
			}
		}
		return false;
	}	
	
   /**
    * Verifica si un string contiene un patron al comienzo del mismo(ambos pasados por parametro)
    * @param string que representa el String donde se busca el parametro
    * @param patron que representa el String cuya existencia es buscada
    * @return boolean que indica si fue encontrado el patron en el comienzo del string
    */	
	public static boolean contieneStringComienzo(String string,String patron)
	{
		if (string==null||patron==null) return false;
		if (patron.equals("")) return true;
		if (patron.length()>string.length()) return false;
		return string.substring(0,patron.length()).equals(patron);
	}	

   /**
    * Verifica si un string contiene un patron al final del mismo(ambos pasados por parametro)
    * @param string que representa el String donde se busca el parametro
    * @param patron que representa el String cuya existencia es buscada
    * @return boolean que indica si fue encontrado el patron en el final del string
    */	
	public static boolean contieneStringFinal(String string,String patron)
	{
		if (string==null||patron==null) return false;
		if (patron.equals("")) return true;
		if (patron.length()>string.length()) return false;
		return string.substring(string.length()-patron.length(),string.length()).equals(patron);
	}		
	
   /**
    * Metodo que retorna las palabras de un string pasado por parametro
    * @param texto string del cual se extraen las palabras
    * @return arreglo unidimensional de String que contiene las palabras del string
    */
	public static String[] getPalabrasString(String texto)
	{
		
		if (texto==null) return null;
		if (texto.length()==0) return new String[0];

		Lista lista=new Lista();
		boolean enEspacio=false;
		String palabra="";
		String linea="";
		char car=texto.charAt(0);
		if (esCaracterSeparacion(car))
		{
			enEspacio=true;
		}			
		for (int i=0;i<texto.length();i++)
		{
			car=texto.charAt(i);
			if (esCaracterSeparacion(car))
			{
				if (!enEspacio)
				{	
					lista.insertarUltimo(palabra);
					enEspacio=true;	
					palabra="";
				}
				palabra+=""+texto.charAt(i);
			}
			else
			{
				if (enEspacio)
				{
					lista.insertarUltimo(palabra);
					enEspacio=false;	
					palabra="";
				}
				palabra+=""+texto.charAt(i);
			}
		}
		lista.insertarUltimo(palabra);
		Comparable aux[]=lista.getInfos();
		palabra="";
		for (int i=0;i<aux.length;i++)
		{
			palabra=(String)aux[i];
			if (esTodoSeparacion(palabra))
			{
				lista.borrar(aux[i]);
			}
		}
		String palabras[]=new String[lista.getCantidadNodos()];
		aux=lista.getInfos();		
		for (int i=0;i<palabras.length;i++)
		{
			palabras[i]=(String)aux[i];
		}
		return palabras;
	}		
	
   /**
    * Metodo que retorna los terminos de un string pasado por parametro
    * @param texto string del cual se extraen los terminos
    * @return arreglo unidimensional de String que contiene los terminos del string
    */
	public static String[] getPalabras(String texto)
	{
		if (texto==null) return null;
		if (texto.length()==0) return new String[0];

		Lista lista=new Lista();
		String palabra="";
		char car;
		for (int i=0;i<texto.length();i++)
		{
			car=texto.charAt(i);
			if (Character.isLetterOrDigit(car))
				palabra+=""+texto.charAt(i);
			else
			{
				if (!palabra.equals(""))
				{
					lista.insertarUltimo(palabra);
					palabra="";
				}
				//lista.insertarUltimo(""+car);
			}
		}
		if (!palabra.equals(""))
			lista.insertarUltimo(palabra);		
		return getStrings(lista);
	}	
	
	/**
	 * Indica si la palabra parametro es tal
	 * @param palabra palabra a ser analizada
	 * @return boolean que indica si la palabra parametro es tal
	 */
	public static boolean esPalabra(String palabra)
	{
		if (palabra==null||palabra.length()==0) return false;
		for (int i=0;i<palabra.length();i++)
			if (!Character.isLetterOrDigit(palabra.charAt(0)))
				return false;
		return true;
	}
	
	/**
	 * Indica si un texto contiene todas las palabras, ambos parametros
	 * @param texto texto a ser analizado
	 * @param palabras palabras cuya existencia en el texto son verificadas
	 * @return boolean que indica si un texto contiene todas las palabras, ambos parametros
	 */
	public static boolean contieneTodasPalabras(String texto,String palabras[])
	{
		if (texto==null||palabras==null) return false;
		int c=0;
		String a[]=GestionString.getPalabras(texto);
		if (a==null||a.length==0) return false;
		for (int i=0;i<palabras.length;i++)
		{
			if (esPalabra(palabras[i]))
			{
				for (int j=0;j<a.length;j++)
				{
					c++;
					if (!a[j].equals(palabras[i]))
						return false;
				}
			}
		}
		return false;
	}
	
	/**
	 * Indica si el texto parametro contiene alguna de las palabras tambien parametros
	 * @param texto texto a ser analizado
	 * @param palabras palabras cuya existencia en el texto son verificadas
	 * @return boolean que indica si el texto parametro contiene alguna de las palabras tambien parametros
	 */
	public static boolean contieneAlgunaPalabra(String texto,String palabras[])
	{
		if (texto==null||palabras==null) return false;
		int c=0;
		String a[]=GestionString.getPalabras(texto);
		if (a==null||a.length==0) return false;
		for (int i=0;i<palabras.length;i++)
		{
			if (esPalabra(palabras[i]))
			{
				for (int j=0;j<a.length;j++)
				{
					c++;
					if (a[j].equals(palabras[i]))
						return true;
				}
			}
		}
		return false;
	}	
	
   /**
    * Metodo que retorna un vector de string que representa los string guardados en un lista
    * @param lista que es la Lista de la cual se extraen los strings
    * @return arreglo unidimensional de String que representa los nodos de la lista en forma de string
    */
    public static String[] getStrings(Lista lista)
    {
    	return getStringsLista(lista);
    }

   /**
    * Metodo que retorna un vector de string que representa los string guardados en un lista
    * @param lista que es la Lista de la cual se extraen los strings
    * @return arreglo unidimensional de String que representa los nodos de la lista en forma de string
    */	
	public static String[] getStringsLista(Lista lista)
	{
		String strings[]=new String[lista.getCantidadNodos()];
		Comparable aux[]=lista.getInfos();
		for (int i=0;i<strings.length;i++)
		{
			strings[i]=(String)aux[i];
		}
		return strings;    			
	}

   /**
    * Metodo que indica si todo el string pasado por parametro son caracteres de separacion
    * @param palabra en la cual se verifica si es todo separacion
    * @return boolean que indica si es todo el string separacion
    */	
	private static boolean esTodoSeparacion(String palabra)
	{
		int i;
		for (i=0;i<palabra.length()&&esCaracterSeparacion(palabra.charAt(i));i++);
		return i==palabra.length();
	}

   /**
    * Metodo que indica si todo el string pasado por parametro tiene como caracteres el espacio
    * @param palabra en la cual se verifica si es todo es caracter espacio
    * @return boolean que indica si es todo el string es caracter espacio
    */	
	private static boolean esTodoCaracterSeparacion(String palabra)
	{
		int i;
		for (i=0;i<palabra.length()&&palabra.charAt(i)==' ';i++);
		return i==palabra.length();
	}	
	
   /**
    * Metodo que indica si el caracter pasado por parametro es de separacion
    * @param car que es el caracter a verificar
    * @return boolean que indica si es caracter de separacion
    */
	private static boolean esCaracterSeparacion(char car)
	{
		return (car!=39&&!(Character.isLetterOrDigit(car)));
	}
	
   /**
    * Metodo que retorna un vector de string con los strings del vector vectorComparacion que se encuentran en vectorOrigen (ambos pasados por parametro)
    * @param vectorOrigen vector de string donde se buscan los strings del otro vector
    * @param vectorComparacion vector de string cuya existencia es buscada en el otro vector
    * @return vector de string con los strings comunes a ambos vectores
    */	
    public static String[] getStringVectorExisteEnVector(String vectorOrigen[],String vectorComparacion[])
    {
    	if (vectorOrigen==null||vectorComparacion==null) return null;
    	Lista lista=new Lista();
    	for (int i=0;i<vectorComparacion.length;i++)
    	{
    		if (!existeStringEnVector(vectorOrigen,vectorComparacion[i]))
    		{
    			lista.insertar(vectorComparacion[i]);
    		}
    	}
    	return getStringsLista(lista);
    }

   /**
    * Metodo que indica si un string existe en un vector de strings (ambos pasados por parametro)
    * @param vector vector de string donde se busca el string componente
    * @param componente que es el string cuya existencia quiere ser verificada
    * @return boolean que indica la existencia de componente en vector
    */	    
    public static boolean existeStringEnVector(String vector[],String componente)
    {
    	int i;
    	for (i=0;i<vector.length&&!vector[i].equals(componente);i++);
    	return !(i==vector.length);
    }	
    
   /**
    * Metodo que retorna un string unico que combina los strings de un vector de string pasado por parametro
    * @param vector del cual se extraen los strings
    * @return string con la combinacion de los trings del vector
    */	    
    public static String getStringVector(String vector[])
    {
    	String aux="";
    	if (vector.length==0) return aux;
    	int i;
    	for (i=0;i<vector.length-1;i++)
    	{
    		aux+=vector[i]+"\n";
    	}
    	aux+=vector[i];
    	return aux;
    }
    
    /**
     * Verifica si la palabra recibida por parametro solo contiene caracteres alfanumericos
     * @param palabra String que es la palabra cuya composicion de alfanumericos es verificada
     * @return boolean que indica si la palabra recibida por parametro solo contiene caracteres alfanumericos
     */
    public static boolean esTodoAlfanumerico(String palabra)
    {
    	if (palabra==null) return false;
    	int i;
    	for (i=0;i<palabra.length()&&Character.isLetterOrDigit(palabra.charAt(i));i++);
    	return i==palabra.length();
    }
    
    /**
     * Retorna una lista cuyos componentes seran los componentes del vector pasado por parametro
     * @param vector vector cuyos componentes seran componentes de la lista
     * @return Lista cuyos componentes seran los componentes del vector pasado por parametro
     */
    public static Lista getListaVector(String vector[])
    {
    	if (vector==null) return null;
    	Lista lista=new Lista();
    	for (int i=0;i<vector.length;i++)
    		lista.insertar(vector[i]);
    	return lista;
    }

    /**
     * Retorna un vector con los strings diferentes de un vector parametro
     * @param vector vector cuyos componentes diferentes seran componentes de la lista
     * @return vector con los strings diferentes de un vector parametro
     */
    public static String[] getStringDiferentes(String vector[])
    {
    	if (vector==null) return null;
    	Lista lista=new Lista();
    	for (int i=0;i<vector.length;i++)
    		if (!lista.existe(vector[i]))
				lista.insertar(vector[i]);
    	return getStringsLista(lista);
    }
    
    /**
     * Muestra por consola los componentes de un vector de String
     * @param vector vector de String a mostrar
     */
    public static void mostrar(String vector[])
    {
    	if (vector==null) return;
    	for (int i=0;i<vector.length;i++)
    		System.out.println(i+": "+vector[i]);
    }

    /**
     * Crea un vector copia del pasado por parametro
     * @param vector vector a copiar
     * @return vector copia del pasado por parametro
     */
    public static String[] copiar(String vector[])
    {
    	if (vector==null) return null;
    	String r[]=new String[vector.length];
    	for (int i=0;i<vector.length;i++)
    		r[i]=new String(vector[i]);
    	return r;
    }
}