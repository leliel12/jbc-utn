

/**
 * Una clase proporciona metodos estaticos para el manejo de vectores de enteros, doubles y Comparables.
 * @version Noviembre de 2005
 */
public class GestionVector
{
	/**
	 * int que indica que el ordenamiento se hara utilizando el algoritmo de Insercion
	 */
	public static final int INSERCION=0;
	
	/**
	 * int que indica que el ordenamiento se hara utilizando el algoritmo de Seleccion
	 */	
	public static final int SELECCION=1;
	
	/**
	 * int que indica que el ordenamiento se hara utilizando el algoritmo de Intercambio
	 */	
	public static final int INTERCAMBIO=2;
	
	/**
	 * int que indica que el ordenamiento se hara utilizando el algoritmo de QuickSort
	 */
	public static final int QUICK_SORT=3;

	/**
	 * int que indica que el ordenamiento se hara utilizando el algoritmo de HeapSort
	 */
	public static final int HEAP_SORT=4;

	/**
	 * int que indica que el ordenamiento se hara utilizando el algoritmo de ShellSort
	 */
	public static final int SHELL_SORT=5;
	
	/**
	 * boolean que indica que el ordenamiento se realiza ordenando los elementos comenzando con aquellos de menor valor
	 */
	public static final boolean MENOR_A_MAYOR=false;

	/**
	 * boolean que indica que el ordenamiento se realiza ordenando los elementos comenzando con aquellos de mayor valor
	 */	
	public static final boolean MAYOR_A_MENOR=true;
	
	/**
	 * genera un vector con enteros aletorios (entre 1 y 100) cuyo tamanio sera el pasado por parametro
	 * @param tamanio int que indica el tamanio del vector a generar
	 * @return vector de int que es el vector de enteros aleatorio generado
	 */
	public static int[] generarVector(int tamanio)
	{
		int vector[]=new int[tamanio];
       	for (int i=0;i<vector.length;i++)
       	{
	    	vector[i]=(int) Math.round(100*Math.random());
       	}	
       	return vector;       	
	}
	
    /**
     * indica si un vector de enteros pasado por parametro esta ordenado en forma creciente
     * @param vector que es el vector de enteros cuyo ordenamiento es evaluado
     * @return boolean que indica si el vector esta ordenado
     */
    public static boolean verificarOrdenado(int vector[])
    {
       	int i;
       	for (i=0;i<(vector.length-1);i++)
       	{
	     	if(vector[i]>vector[i+1]) return false;
       	}
       	return true;
    }	
    
    /**
     * coloca en 0 todos los elementos de un vector de double pasado por parametro
     * @param v vector de double cuyos valores de elementos seran 0
     */
    public static void limpiarVector(double v[])
    {
    	for (int i=0;i<v.length;i++)
    	{
    		v[i]=(double)0.0;
    	}
    }
    
    /**
     * coloca en 0 todos los elementos de un arreglo de double pasado por parametro
     * @param v arreglo de double cuyos valores de elementos seran 0
     */
    public static void limpiarArreglo(double v[][])
    {
    	for (int i=0;i<v.length;i++)
    	{
    		for (int j=0;j<v[i].length;j++)
    		{
    			v[i][j]=(double)0.0;
    		}
    	}
    }    

    /**
     * coloca en 0 todos los elementos de un vector de enteros pasado por parametro
     * @param v vector de enteros cuyos valores de elementos seran 0
     */
    public static void limpiarVector(int v[])
    {
    	for (int i=0;i<v.length;i++)
    	{
    		v[i]=0;
    	}
    }    
    
    /**
     * retorna el mayor entero de un vector pasado por parametro
     * @param vector vector de int del cual se quiere conocer el mayor valor
     * @return int que es el mayor valor encontrado en el vector
     */
    public static int getMayor(int vector[])
    {
    	int aux=vector[0];
		for (int i=1;i<vector.length;i++)
		{
			if (vector[i]>aux)
			{
				aux=vector[i];
			}
		}
		if (aux==0) return 1;
		return aux;    	
    }
    
    /**
     * retorna el mayor entero de un vector pasado por parametro
     * @param vector vector de int del cual se quiere conocer el mayor valor
     * @return int que es el mayor valor encontrado en el vector
     */
    public static double getMayor(double vector[])
    {
    	double aux=vector[0];
		for (int i=1;i<vector.length;i++)
		{
			if (vector[i]>aux)
			{
				aux=vector[i];
			}
		}
		//if (aux==0) return 1;
		return aux;    	
    }    
    
    public static double sumar(double vector[])
    {
		double sum=0;
		for (int i=0;i<vector.length;i++)
			sum+=vector[i];
		return sum;    	
    }     
    
    /**
     * retorna el mayor entero de un vector pasado por parametro
     * @param vector vector de int del cual se quiere conocer el mayor valor
     * @return int que es el mayor valor encontrado en el vector
     */
    public static double getMenor(double vector[])
    {
    	double aux=vector[0];
		for (int i=1;i<vector.length;i++)
		{
			if (vector[i]<aux)
			{
				aux=vector[i];
			}
		}
		//if (aux==0) return 1;
		return aux;    	
    }      
    
    /**
     * retorna un vector de int cuyos elementos surgen de la conversion de los 
     * elementos double de un vector pasado por parametro
     * @param v vector de double a convertir en vector de enteros
     * @return vector de enteros convertido a partir del vector de double
     */
    public static int[] convertirVectorDoubleInt(double v[])
    {
    	int r[]=new int [v.length];
    	for (int i=0;i<v.length;i++)
    	{
    		r[i]=(int)v[i];
    	}
    	return r;
    }
    
    /**
     * retorna un vector de int cuyos elementos surgen de la conversion de los 
     * elementos double de un vector pasado por parametro
     * @param v vector de double a convertir en vector de enteros
     * @return vector de enteros convertido a partir del vector de double
     */
    public static double[] convertirVectorIntDouble(int v[])
    {
    	double r[]=new double [v.length];
    	for (int i=0;i<v.length;i++)
    	{
    		r[i]=v[i];
    	}
    	return r;
    }    
    
    /**
     * indica si el mayor componente del vector de enteros pasado por parametro
     * es igual a la suma de todos los valores de ese vector
     * @param valores que es el vector de enteros a analizar
     * @return boolean que indica si el mayor componente del vector de enteros es igual a la suma de todos los valores del vector
     */
    public static boolean verificarUnoTotal(int valores[])
    {
    	int mayor=getMayor(valores);
    	int sum=0;
    	for (int i=0;i<valores.length;i++)
    	{
    		sum+=valores[i];
    	}
    	return sum==mayor;
    }
    
    /**
     * ordena los componentes de un vector de Comparable pasado por parametro utilizando el algoritmo de Intercambio
     * @param vector vector de Comparable a ordenar
     * @return vector ordenado
	 */
    public static Comparable[] ordenamientoIntercambio (Comparable vector[])
    {
    	if (vector==null||vector.length==0) return vector;
    	boolean ordenado = false;
    	int tamanio=vector.length;
      	int i,j;
      	Comparable aux;
      	for (i=0; i<tamanio-1 && !ordenado; i++)
	    {
		    ordenado = true;
	    	for (j=0; j<tamanio-i-1; j++)
	    	{
	    		if (vector[j].compareTo(vector[j+1])>0)
	     		{
	       			ordenado = false;
		       		aux = vector [j];
		       		vector [j] = vector [j+1];
	    	   		vector [j+1] = aux;
	     		}
	    	}
      	}
      	return vector;
    }

    /**
     * ordena los componentes de un vector de Comparable pasado por parametro utilizando el algoritmo de Seleccion
     * @param vector vector de Comparable a ordenar
     * @return vector ordenado
	 */
    public static Comparable[] ordenamientoSeleccion (Comparable vector[])
    {
      	if (vector==null||vector.length==0) return vector;
      	int izq, der;
      	Comparable aux;
      	int tamanio=vector.length;
      	for (izq = 0; izq < tamanio - 1; izq++)
      	{
	    	for (der = izq + 1; der < tamanio; der++)
	    	{
	     		if (vector[izq].compareTo(vector[der])>0)
	     		{
	       			aux = vector [der];
	       			vector [der] = vector [izq];
	       			vector [izq] = aux;
	     		}
	    	}
      	}
      	return vector;
    }

    /**
     * ordena los componentes de un vector de Comparable pasado por parametro utilizando el algoritmo de Insercion
     * @param vector vector de Comparable a ordenar
     * @return vector ordenado
	 */
    public static Comparable[] ordenamientoInsercion (Comparable vector[])
    {
      	if (vector==null||vector.length==0) return vector;
      	int tamanio=vector.length;
      	int i, k;
      	Comparable y;
      	for (k = 1; k < tamanio; k++)
      	{
	    	y = vector[k];
	    	for (i = k-1; i>=0 && y.compareTo(vector[i])<0; i--)
	    	{
	      		vector[i+1]= vector[i];
	    	}
	    	vector[i+1]= y;
      	}
      	return vector;
    }

    /**
     * ordena los componentes de un vector de Comparable pasado por parametro utilizando el algoritmo de QuickSort
     * @param vector vector de Comparable a ordenar
     * @return vector ordenado
	 */
    public static Comparable[] ordenamientoQuickSort (Comparable vector[])
    {
       	if (vector==null||vector.length==0) return vector;
       	int tamanio=vector.length;
       	ordenar (0, tamanio - 1,vector);
       	return vector;
    }

    /**
     * ordena recursivamente los componentes de un vector de Comparable pasado por parametro utilizando el algoritmo de QuickSort
     * @izq int que es el limite inferior a considerar en el ordenamiento
     * @der int que es el limite superior a considerar en el ordenamiento
     * @param vector vector de Comparable a ordenar
	 */    
    private static void ordenar (int izq, int der,Comparable vector[])
    {
       	int i, j;
       	Comparable x, y;
       	int tamanio=vector.length;
       	i = izq;
       	j = der;
       	x = vector[(izq + der) / 2];
       	do 
       	{
	    	while (vector[i].compareTo(x)<0 && i<der) 
	    	{
	    		i++;
	    	}
	    	while (x.compareTo(vector[j])<0 && j>izq) 
	    	{
	    		j--;
	    	}
	    	if (i<=j)
	    	{
		  		y = vector[i];
		  		vector[i] = vector[j];
		  		vector[j] = y;
		  		i++;
		  		j--;
	    	}
       }
       while (i <= j);
       if (izq < j) ordenar(izq, j,vector);
       if (i < der) ordenar(i, der,vector);
    }


    /**
     * ordena los componentes de un vector de Comparable pasado por parametro utilizando el algoritmo de HeapSort
     * @param vector vector de Comparable a ordenar
     * @return vector ordenado
	 */
    public static Comparable[] ordenamientoHeapSort(Comparable vector[])
    {
       	if (vector==null||vector.length==0) return vector;
       	int i, s, f;
       	Comparable e,valori;
       	int tamanio=vector.length;

       	// crear el grupo inicial...
       	for (i = 1; i < tamanio; i++)
       	{
	    	e = vector [i];
	    	s = i;
	    	f = (s-1)/2;
	    	while (s>0 && vector[f].compareTo(e)<0)
	    	{
	      		vector[s] = vector[f];
	      		s = f;
	      		f = (s-1)/2;
	    	}
	    	vector[s] = e;
       	}

       	// se extrae la raiz, y se reordena el vector y el grupo...
       	for (i = tamanio-1; i>0; i--)
       	{
	    	valori = vector[i];
	    	vector[i] = vector[0];
	    	f = 0;
	    	if (i == 1) s = -1; else s = 1;
	    	if (i > 2 && vector[2].compareTo(vector[1])>0)  s = 2;
	    	while ( s >= 0 && valori.compareTo(vector[s])<0)
	    	{
	      		vector[f] = vector[s];
	      		f = s;
	      		s = 2*f + 1;
	      		if (s + 1 <= i - 1 && vector[s].compareTo(vector[s+1])<0) s++;
	      		if (s > i - 1) s = -1;
	    	}
	    	vector[f] = valori;
       	}
       	return vector;
    }

    /**
     * ordena los componentes de un vector de Comparable pasado por parametro utilizando el algoritmo de ShellSort
     * @param vector vector de Comparable a ordenar
     * @return vector ordenado
	 */
    public static Comparable[] ordenamientoShellSort(Comparable vector[])
    {
       	if (vector==null||vector.length==0) return vector;
       	int tamanio=vector.length;
       	int j, k, h;
       	Comparable y;
       	for (h = 1; h <= tamanio / 9; h = 3*h + 1);
       	for ( ; h > 0; h /= 3)
       	{
	     	for (j = h; j < tamanio; j++)
	     	{
	      		y = vector[j];
	      		for (k = j - h; k >= 0 && y.compareTo(vector[k])<0; k-=h)
	      		{
	       			vector[k+h] = vector[k];
	      		}
	      		vector[k+h] = y;
	     	}
       	}
       	return vector;
    }
    
    /**
     * ordena los componentes de un vector de Comparable pasado por parametro
     * @param vector vector de Comparable a ordenar
     * @return vector ordenado
	 */
    public static Comparable[] ordenamiento(Comparable vector[])
    {
    	return ordenamientoQuickSort(vector);
    }

    /**
     * ordena los componentes de un vector de Comparable pasado por parametro. El parametro orden indica si se ordena se mayor a menor o de menor a mayor.
     * @param vector vector de Comparable a ordenar
     * @param orden boolean indica si el ordenamiento es de menor a mayor o de mayor a menor
     * @return vector ordenado
	 */
    public static Comparable[] ordenamiento(Comparable vector[],boolean orden)
    {
    	ordenamientoQuickSort(vector);
    	if (orden==MAYOR_A_MENOR)
		{
			invertirVector(vector);
		}
		return vector;
    }
	
    /**
     * ordena los componentes de un vector de Comparable pasado por parametro utilizando el algoritmo
     * indicado por el parametro int tipo. El parametro orden indica si se ordena se mayor a menor o de menor a mayor.
     * @param vector vector de Comparable a ordenar
     * @param tipo int que indica el tipo de algoritmo de ordenamiento a utilizar
     * @param orden boolean indica si el ordenamiento es de menor a mayor o de mayor a menor
     * @return vector ordenado
	 */    	
	public static Comparable[] ordenamiento(Comparable vector[],int tipo,boolean orden)
	{
		ordenamiento(vector,tipo);
		if (orden==MAYOR_A_MENOR)
		{
			invertirVector(vector);
		}
		return vector;
	}
	
    /**
     * ordena los componentes de un vector de Comparable pasado por parametro utilizando el algoritmo
     * indicado por el parametro int tipo
     * @param vector vector de Comparable a ordenar
     * @param tipo int que indica el tipo de algoritmo de ordenamiento a utilizar
     * @return vector ordenado
	 */    
    public static Comparable[] ordenamiento(Comparable vector[],int tipo)	
    {
    	if (vector==null||vector.length==0) return vector;
    	if (tipo==INSERCION)
		{
			ordenamientoInsercion(vector);return vector;
		}
    	if (tipo==SELECCION)
		{
			ordenamientoSeleccion(vector);return vector;
		}
    	if (tipo==INTERCAMBIO)
		{
			ordenamientoIntercambio(vector);return vector;
		}
    	if (tipo==HEAP_SORT)
		{
			ordenamientoHeapSort(vector);return vector;
		}
    	if (tipo==SHELL_SORT)
		{
			ordenamientoShellSort(vector);return vector;
		}
    	if (tipo==QUICK_SORT)
		{
			ordenamientoQuickSort(vector);return vector;
		}
		return vector;
    }
    
    /**
     * Invierte el orden en que se encuentra un vector
     * @param vector vector cuyo orden es invertido
     * @return vector invertido
     */
    public static Comparable[] invertirVector(Comparable vector[])
    {
    	Comparable aux=null;
    	if (vector==null||vector.length<=1) return vector;
    	for (int i=0,j=vector.length-1;i<j;i++,j--)
    	{
    		aux=vector[i];
    		vector[i]=vector[j];
    		vector[j]=aux;
    	}
    	return vector;
    }
    
    /**
     * desordena los componentes de un vector de Comparable pasado por parametro
     * @param vector vector de Comparable a desordenar
     * @return vector ordenado
	 */
    public static Comparable[] desordenamiento(Comparable vector[])
    {
		if (vector==null||vector.length<=1) return vector;
		int origen,destino;
		origen=destino=0;
		Comparable aux=null;
		int maximo=vector.length*vector.length*2;
		for (int i=0;i<maximo;i++)
		{
			origen=(int)(Math.random()*vector.length);
			destino=(int)(Math.random()*vector.length);
			aux=vector[destino];
			vector[destino]=vector[origen];
			vector[origen]=aux;
		}    	
		return vector;
    }
    
    /**
     * retorna un vector de Comparable cuyos componentes seran aquellos que estan en los dos vectores pasados por parametro
     * @param vectorOrigen vector de Comparable que es el primer vector a analizar
     * @param vectorComparacion vector de Comparable que es el segundo vector a analizar
     * @return vector de Comparable cuyos componentes seran aquellos que estan en los dos vectores pasados por parametro
     */
    public static Comparable[] getComponenteVectorExisteEnVector(Comparable vectorOrigen[],Comparable vectorComparacion[])
    {
    	if (vectorOrigen==null||vectorComparacion==null) return null;
    	Lista lista=new Lista();
    	for (int i=0;i<vectorComparacion.length;i++)
    	{
    		if (!existeComponenteEnVector(vectorOrigen,vectorComparacion[i]))
    		{
    			lista.insertar(vectorComparacion[i]);
    		}
    	}
    	return lista.getInfos();
    }
    
    /**
     * indica si un Comparable pasado por parametro esta presente en un vector tambien pasado por parametro
     * @param vector vector de Comparable donde se verifica la existencia del componente buscado
     * @param componente Comparable cuya existencia en el vector es evaluada
     * @return boolean que indica si un Comparable pasado por parametro esta presente en un vector tambien pasado por parametro
     */
    public static boolean existeComponenteEnVector(Comparable vector[],Comparable componente)
    {
    	int i;
    	for (i=0;i<vector.length&&vector[i].compareTo(componente)!=0;i++);
    	return !(i==vector.length);
    }
    
    /**
     * Muestra por consola los componentes de un vector de double
     * @param vector vector de double a mostrar
     */    
    public static void mostrar(double vector[])
    {
    	if (vector==null) return;
    	for (int i=0;i<vector.length;i++)
    		System.out.println(i+": "+vector[i]);
    }
    
    /**
     * Muestra por consola los componentes de un vector de double
     * @param vector vector de double a mostrar
     */    
    public static void mostrar(int vector[])
    {
    	if (vector==null) return;
    	for (int i=0;i<vector.length;i++)
    		System.out.println(i+": "+vector[i]);
    }    
    
    /**
     * Muestra por consola los toString de los objetos pasados por parametro
     * @param vector vector de object a mostrar
     */    
    public static void mostrar(Object vector[])
    {
    	if (vector==null) return;
    	for (int i=0;i<vector.length;i++)
    		System.out.println(i+": "+vector[i]);
    }        
}