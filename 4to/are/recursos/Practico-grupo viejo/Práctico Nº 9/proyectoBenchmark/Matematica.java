
import java.util.*;
import java.text.*;

/**
 * Clase que proporciona metodos para resolucion de sistemas de ecuaciones, calculo de operaciones y funciones
 */
public class Matematica
{
	/** int que representa el calculo del logaritmo*/
	public static final int LOGARITMO=0;
	
	/** int que representa el calculo del logaritmo natural*/
	public static final int LOGARITMO_NATURAL=1;
	
	/** int que representa el calculo del seno*/
	public static final int SENO=2;
	
	/** int que representa el calculo del coseno*/
	public static final int COSENO=3;
	
	/** int que representa el calculo de la tangente*/
	public static final int TANGENTE=4;
	
	/** int que representa el calculo del factorial*/
	public static final int FACTORIAL=5;
	
	/** int que representa el calculo de la suma*/
	public static final int SUMA=6;
	
	/** int que representa el calculo de la diferencia*/
	public static final int DIFERENCIA=7;
	
	/** int que representa el calculo del cociente*/
	public static final int COCIENTE=8;
	
	/** int que representa el calculo del producto*/
	public static final int PRODUCTO=9;
	
	/** int que representa el calculo de la raiz*/
	public static final int RAIZ=10;
	
	/** int que representa el calculo de la potencia*/
	public static final int POTENCIA=11;
	
	/** int que representa resolucion por metodo de euler de ecuacion diferencial*/
	public static final int EULER=0;
	
	/** int que representa resolucion por metodo de euler mejorado de ecuacion diferencial*/
	public static final int EULER_MEJORADO=1;
	
	/** int que representa resolucion por metodo de euler modificado de ecuacion diferencial*/
	public static final int EULER_MODIFICADO=2;
	
	/** int que representa resolucion por metodo de runge kutta 4 orden de ecuacion diferencial*/
	public static final int RUNGE_KUTTA_4=3;
	
	/** int que representa resolucion por metodo de predictor corrector de ecuacion diferencial*/
	public static final int PREDICTOR_CORRECTOR=4;
	
	/** error por defecto para diferencias de ordenada*/
	private static double ERROR_DEFECTO=1;
	
	/** maxima cantidad de loops permitidos*/
	private static int MAXIMO_LOOP=100;
	
	/** derivadas aproximadas */
	private static Derivada derivada[]=new Derivada[23];

	/**
	 * Aplica el metodo de Cholesky para la resolucion de un sistema de 
	 * ecuaciones lineales con una matriz de coeficientes simetrica
	 * @param matriz arreglo de double que contiene los coeficientes de la matriz
	 * @param termino vector de double con el termino independiente
	 * @return vector de double con el resultado del sistema
	 */
	public static double[] calcularCholesky(double matriz[][],double termino[]) throws Exception
	{
		double matrizInicial[][]=unirMatrizCoeficienteTerminoIndependiente(matriz,termino);
		if (matrizInicial==null)
		{
			throw new Exception("Matriz null");
		}
		return calcularCholesky(matrizInicial);
	}

	/**
	 * Aplica el metodo de Cholesky para la resolucion de un sistema de 
	 * ecuaciones lineales con una matriz de coeficientes simetrica
	 * @param matrizInicial arreglo de double que contiene los coeficientes de la matriz y el termino independiente
	 * @return vector de double con el resultado del sistema
	 */	
	public static double[] calcularCholesky(double matrizInicial[][]) throws Exception
	{
		if (matrizInicial==null)
		{
			throw new Exception("Matriz nula");
		}
		if (!verificarSimetriaMatriz(matrizInicial))
		{
			throw new Exception("Matriz no simetrica");
		}
		if (!verificarDiagonalNoCero(matrizInicial))
		{
			throw new Exception("Diagonal de matriz 0");
		}
		double matrizResultado[][]=new double[matrizInicial.length][matrizInicial[0].length];
		procedimientoCholesky(matrizResultado,matrizInicial);
		return calcularSustitucionInversa(matrizResultado);
	}

	/**
	 * Aplica el metodo de Cholesky para la resolucion de un sistema de 
	 * @param matriz arreglo de double cuya simetriz es verificada
	 * @return boolean que indica si la matriz es simetrica
	 */
	public static boolean verificarSimetriaMatriz(double matriz[][])
	{
		for (int i=0;i<matriz.length;i++)
		{
			for (int j=0;j<matriz[0].length&&j<(matriz[0].length-1);j++)
			{
				if (matriz[i][j]!=matriz[j][i])
				{
					return false;
				}
			}
		}
		return true;
	}	
	
	/**
	 * Indica si la diagonal principal de la matriz es distinta de cero
	 * @param matriz matriz de double de la cual se quiere saber si la diagonal principal es distinta de cero
	 * @return boolean que indica si la diagonal principal de la matriz es distinta de cero
	 */
	public static boolean verificarDiagonalNoCero(double matriz[][])
	{
		int i;
		for (i=0;i<matriz.length&&matriz[i][i]!=0;i++);
		if (i==matriz.length)
		{
			return true;
		}
		return false;
	}
	
	/**
	 * Realiza el procedimiento de Cholesky
	 * @param matrizResultado que es la matriz donde se efectuan los calculos
	 * @param matrizInicial matriz sobre la cual se aplica Cholesky
	 */
	private static void procedimientoCholesky(double matrizResultado[][],double matrizInicial[][])
	{
		for (int i=0;i<matrizInicial.length;i++)
		{
			for (int j=0;j<(matrizInicial[i].length);j++)
			{
				if (i<=j)
				{
					if (j==i)
					{
						calcularIndiceIgualCholesky(matrizResultado,matrizInicial,i);
					} else
					{
						calcularIndiceDistintoCholesky(matrizResultado,matrizInicial,i,j);
					}
				}
			}
		}
	}
	
	/**
	 * Procedimiento de Colesky para las posiciones de la matriz cuyos indices son iguales
	 * @param matrizResultado que es la matriz donde se efectuan los calculos
	 * @param matrizInicial matriz sobre la cual se aplica Cholesky
	 * @param x indice actual a procesar
	 */
	private static void calcularIndiceIgualCholesky(double matrizResultado[][],double matrizInicial[][],int x)
	{
		double aux=(double)0.0;
		for (int k=0;k<=(x-1);k++)
		{
			aux+=matrizResultado[k][x]*matrizResultado[k][x];
		}
		matrizResultado[x][x]=Math.sqrt(matrizInicial[x][x]-aux);
	}

	/**
	 * Procedimiento de Colesky para las posiciones de la matriz cuyos indices son distintos
	 * @param matrizResultado que es la matriz donde se efectuan los calculos
	 * @param matrizInicial matriz sobre la cual se aplica Cholesky
	 * @param x indice de fila actual a procesar
	 * @param y indice de columna actual a procesar
	 */	
	private static void calcularIndiceDistintoCholesky(double matrizResultado[][],double matrizInicial[][],int x,int y)
	{
		double aux=(double)0.0;
		for (int k=0;k<=(x-1);k++)
		{
			aux+=matrizResultado[k][x]*matrizResultado[k][y];
		}
		matrizResultado[x][y]=(1/matrizResultado[x][x])*(matrizInicial[x][y]-aux);
	}
	
	/**
	 * Aplica la sustitucion inversa para el calculo de los valores de un sistema de ecuaciones lineales
	 * @param matrizResultado matriz de double sobre la cual se aplica sustitucion inversa
	 * @return vector de double con los resultados del sistema de ecuaciones lineal
	 */
	private static double[] calcularSustitucionInversa(double matrizResultado[][])
	{
		double resultado[]=new double[matrizResultado.length];
		double aux=0.0;
		for (int i=matrizResultado.length-1;i>=0;i--)
		{
			aux=matrizResultado[i][matrizResultado[i].length-1];
			for (int j=0;j<matrizResultado.length;j++)
			{
				if (j!=i)
				{
					aux-=matrizResultado[i][j]*resultado[j];
				}
			}
			resultado[i]=aux/matrizResultado[i][i];
		}
		return resultado;
	}
	
	/**
	 * Aplica el metodo de GaussSeidel para la resolucion de un sistema de ecuaciones lineal
	 * @param matrizInicial matriz de double sobre la cual se aplica el metodo de GaussSeidel
	 * @param error error maximo permitido para los valores resultantes para dejar de iterar
	 * @return vector de double con los resultados del sistema de ecuaciones lineal
	 */
	public static double[] calcularGaussSeidel(double matrizInicial[][],double error) throws Exception
	{
		if (matrizInicial==null)
		{
			throw new Exception("Matriz null");
		}	
		if (error==0)	
		{
			throw new Exception("Error no puede ser 0");
		}
		double resultado[]=new double [matrizInicial.length];
		return calcularGaussSeidel(matrizInicial,resultado,error);
	}
	
	/**
	 * Aplica el metodo de GaussSeidel para la resolucion de un sistema de ecuaciones lineal
	 * @param matrizInicial matriz de double sobre la cual se aplica el metodo de GaussSeidel
	 * @return vector de double con los resultados del sistema de ecuaciones lineal
	 */
	public static double[] calcularGaussSeidel(double matrizInicial[][]) throws Exception
	{
		return calcularGaussSeidel(matrizInicial,0.01);
	}
	
	/**
	 * Aplica el metodo de GaussSeidel para la resolucion de un sistema de ecuaciones lineal
	 * @param matrizInicial matriz de coeficientes double sobre la cual se aplica el metodo de GaussSeidel
	 * @param termino vector de double que es el vector de terminos independientes
	 * @param resultado vector de double que son las condiciones iniciales del metodo
	 * @param error error maximo permitido para los valores resultantes para dejar de iterar
	 * @return vector de double con los resultados del sistema de ecuaciones lineal
	 */	
	public static double[] calcularGaussSeidel(double matrizInicial[][],double termino[],double resultado[],double error) throws Exception
	{
		if (matrizInicial==null||termino==null)
		{
			throw new Exception("Matriz o termino null");
		}
		if (error==0)	
		{
			throw new Exception("Error no puede ser 0");
		}				
		if (resultado==null) resultado=new double [matrizInicial.length];
		return calcularGaussSeidel(unirMatrizCoeficienteTerminoIndependiente(matrizInicial,termino),resultado,error);
	}

	/**
	 * Aplica el metodo de GaussSeidel para la resolucion de un sistema de ecuaciones lineal
	 * @param matrizInicial matriz de coeficientes double sobre la cual se aplica el metodo de GaussSeidel
	 * @param resultado vector de double que son las condiciones iniciales del metodo
	 * @param error error maximo permitido para los valores resultantes para dejar de iterar
	 * @return vector de double con los resultados del sistema de ecuaciones lineal
	 */	
	public static double[] calcularGaussSeidel(double matrizInicial[][],double resultado[],double error) throws Exception
	{
		if (matrizInicial==null||resultado==null)
		{
			throw new Exception("Matriz o termino null");
		}
		if (matrizInicial.length!=matrizInicial[0].length-1)
		{
			throw new Exception("Matriz inconsistente");
		}
		if (error==0)	
		{
			throw new Exception("Error no puede ser 0");
		}		
		double anterior[]=new double[resultado.length];
		procedimientoGaussSeidel(matrizInicial,resultado,anterior);
		int paso;
		int maximosPasos=20;
		for (paso=0;paso<maximosPasos;paso++)
		{
			if (verificarErrorMenor(resultado,anterior,error))
			{
				break;
			}
			for (int i=0;i<resultado.length;i++)
				anterior[i]=resultado[i];
			procedimientoGaussSeidel(matrizInicial,resultado,anterior);
		}
		if (paso==maximosPasos&&verificarIncrementoEnPaso(resultado,anterior))
		{
			throw new Exception("no converge");
		}
		return resultado;

	}

	/**
	 * Verifica si la diferencia entre un paso de calculo y la siguiente es menor que el error en todas las posiciones
	 * @param resultado vector de double con los ultimos resultados obtenidos
	 * @param anterior vector de double con los anteriores resultados obtenidos
	 * @param error error maximo permitido para los valores resultantes
	 * @return boolean que indica si la diferencia entre un paso de calculo y la siguiente es menor que el error en todas las posiciones
	 */
	private static boolean verificarErrorMenor(double resultado[],double anterior[],double error)
	{
		int i;
		double aux;
		for (i=0;i<resultado.length;i++)
		{
			aux=resultado[i]-anterior[i];
			if (aux<0) aux*=-1;
			if (aux>error) return false;
		}
		return true;
	}
	
	/**
	 * Verifica si el error se incrementa de un paso de calculo al siguiente
	 * @param nuevo vector de double con los ultimos resultados obtenidos
	 * @param anterior vector de double con los anteriores resultados obtenidos
	 * @return boolean que indica si el error se incrementa de un paso de calculo al siguiente
	 */
	private static boolean verificarIncrementoEnPaso(double nuevo[],double anterior[])
	{
		int i;
		double aux;
		for (i=0;i<nuevo.length&&nuevo[i]<=anterior[i];i++);
		return !(i==nuevo.length);
	}	
	
	/**
	 * Aplica el metodo de GaussSeidel
	 * @param matriz matriz de double sobre la cual se aplica el metodo de GaussSeidel
	 * @param resultado vector de double con los utlimos resultados obteniendos
	 * @param resultado vector de double con los resultados anteriores obteniendos
	 */
	private static void procedimientoGaussSeidel(double matriz[][],double resultado[],double anterior[])
	{
		double aux1=(double)0.0;
		double aux2=(double)0.0;
		for (int i=0;i<resultado.length;i++)
		{
			for (int j=0;j<i;j++)
			{
				aux1+=matriz[i][j]*resultado[j];
			}
			for (int j=i+1;j<resultado.length;j++)
			{
				aux2+=matriz[i][j]*anterior[j];
			}			
			resultado[i]=(double)(1/matriz[i][i])*(matriz[i][matriz[0].length-1]-aux1-aux2);
			aux1=aux2=0;
		}
	}
	
	/**
	 * Unifica la matriz de coeficientes con el vector de terminos independientes
	 * @param matriz matriz de coeficientes a unir
	 * @param vector de terminos independientes a unir
	 * @return matriz que unifica la matriz de coeficientes con el vector de terminos independientes
	 */
	private static double[][] unirMatrizCoeficienteTerminoIndependiente(double matriz[][],double termino[]) throws Exception
	{
		if (matriz==null||termino==null)
		{
			throw new Exception("matriz o termino null");
		}
		if (matriz.length!=matriz[0].length||matriz.length!=termino.length)
		{
			throw new Exception("matriz y termino inconsistentes");
		}
		double retorno[][]=new double[matriz.length][matriz.length+1];
		for (int i=0;i<matriz.length;i++)
		 for (int j=0;j<matriz.length;j++)
		 	retorno[i][j]=matriz[i][j];
		for (int i=0;i<termino.length;i++)
			retorno[i][termino.length]=termino[i];
		return retorno;
	}
	
	/**
	 * Aplica el metodo de Lagrange para interpolar un valor a partir de un conjunto conocido de puntos
	 * @param valorBuscado valor d x cuya ordenada quiere ser interpolada
	 * @param valores matriz que contiene puntos (valores de x e y) a partir de los cuales se efecua la interpolacion
	 * @return valor de la ordenada correspondiente al valor de las abscisas pasado por parametro
	 */
	public static double calcularLagrange(double valorBuscado,double valores[][]) throws Exception
	{
		if (valores==null)
		{
			throw new Exception("matriz null");
		}
		if (valores[0].length!=2)
		{
			throw new Exception("matriz inconsistente");
		}
		if (existeValorRepetidoX(valores))
		{
			throw new Exception("matriz con valores X repetidos");
		}
		return procedimientoLagrange(valorBuscado,valores);
	}	
	
	/**
	 * Aplica el procedimiento de Lagrange para la interpolacion de un valor a partir de puntos conocidos
	 * @param valorBuscado valor d x cuya ordenada quiere ser interpolada
	 * @param valores matriz que contiene puntos (valores de x e y) a partir de los cuales se efecua la interpolacion
	 * @return valor de la ordenada correspondiente al valor de las abscisas pasado por parametro
	 */
	private static double procedimientoLagrange(double valorBuscado,double valores[][])	
	{
		double resultado=0;
		double aux=0;
		int i;
		for (i=0;i<valores.length;i++)
		{
			aux=1;
			for (int j=0;j<valores.length;j++)
			{
				if (j!=i)
				{
					aux=aux*(valorBuscado-valores[j][0])/(valores[i][0]-valores[j][0]);
				}
			}
			resultado+=valores[i][1]*aux;
			
		}
		return resultado;		
	}
	
	/**
	 * Verifica si un valor de las abscisas esta repetido
	 * @param valores mariz cuyos valores de abscisas son inspeccionados para determinar si existe una repeticion
	 * @return boolean que indica si un valor de las abscisas esta repetido
	 */
	private static boolean existeValorRepetidoX(double valores[][])
	{
		for (int i=0;i<valores.length;i++)
		{
			for (int j=i+1;j<valores.length;j++)
			{
				if (valores[i][0]==valores[j][0])
				{
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Aplica el procedimiento de Gauss para la resolucion de un sistema de ecuaciones lineales
	 * @param matriz matriz de double con los coeficientes del sistema
	 * @param termino vector de double con los terminos independientes del sistema
	 * @return vector de double con los resultados del sistema
	 */
	public static double[] calcularGauss(double matriz[][],double termino[]) throws Exception
	{
		double matrizInicial[][]=unirMatrizCoeficienteTerminoIndependiente(matriz,termino);
		return calcularGauss(matrizInicial);
	}
	
	/**
	 * Aplica el procedimiento de Gauss para la resolucion de un sistema de ecuaciones lineales
	 * @param matriz matriz de double con los coeficientes y terminos independientes del sistema
	 * @return vector de double con los resultados del sistema
	 */
	public static double[] calcularGauss(double matriz[][]) throws Exception
	{
		if (matriz==null)
		{
			throw new Exception("Matriz nula");
		}		
		if (matriz.length!=matriz[0].length-1)
		{
			throw new Exception("Matriz inconsistente");
		}		
		double anterior[][]=new double[matriz.length][matriz[0].length];
		return procedimientoGauss(matriz,anterior);
	}

	/**
	 * Implementacion del procedimiento de Gauss para la resolucion de un sistema de ecuaciones lineales
	 * @param matriz matriz de double con los coeficientes y terminos independientes del sistema
	 * @param anterior matriz obtenda en el paso de resolucion previo
	 * @return vector de double con los resultados del sistema
	 */	
	private static double[] procedimientoGauss(double matriz[][],double anterior[][])
	{
		double resultado[]=new double[matriz.length];
		double aux=0;
		copiarMatriz(matriz,anterior);
		for (int k=0;k<matriz.length-1;k++)
		{
			pivoteoParcial(matriz,k);
			for (int i=k+1;i<matriz.length;i++)
			{
				aux=(-1)*(anterior[i][k])/(anterior[k][k]);
				for (int j=k+1;j<matriz.length;j++)
				{
					matriz[i][j]=anterior[i][j]+aux*anterior[k][j];
				}
				matriz[i][matriz.length]=anterior[i][matriz.length]+aux*anterior[k][matriz.length];
			}
			copiarMatriz(matriz,anterior);
		}
		return calcularSustitucionInversa(matriz);
	}
	
	/**
	 * Ordena los elementos a traves del pivoteo parcial por filas
	 * @param matriz a ser ordenada por pivoteo
	 * @param posicion de fila y columna que sera el pivot
	 */
	private static void pivoteoParcial(double matriz[][],int pos)
	{
		int mayor=pos;
		double aux[]=new double[matriz.length+1];
		for (int i=pos;i<matriz.length;i++)
		{
			if (matriz[i][pos]>matriz[pos][pos])
			{
				for (int j=0;j<aux.length;j++)
				{
					aux[j]=matriz[pos][j];
					matriz[pos][j]=matriz[i][j];
					matriz[i][j]=aux[j];					
				}
			}
		}
	}
	
	/**
	 * Copia los elementos de una matriz a otra matriz
	 * @param origen matriz de double que sera copiada
	 * @param origen matriz de double que sera el destino de copia
	 */
	private static void copiarMatriz(double origen[][],double destino[][])
	{
		for (int i=0;i<origen.length;i++)
			for (int j=0;j<origen[i].length;j++)
				destino[i][j]=origen[i][j];			
	}
	
	/**
	 * Convierte un double en un decimal con cantidad maxima y minima de espacios decimales
	 * @param numero double a convertir
	 * @param maximo cantidad maxima de espacios decimales
	 * @param minimo cantidad minima de espacios decimales
	 * @return numero decimal convertido
	 */
	public static double convertirDecimalMaximoMinimo(double numero,int maximo,int minimo)
	{
		DecimalFormat a=new DecimalFormat();
		if (minimo>=0) a.setMinimumFractionDigits(0);
		if (maximo>=0)a.setMaximumFractionDigits(2);		
		return Double.parseDouble(a.format(numero));
	}

	/**
	 * Convierte un double en un decimal con cantidad de espacios decimales
	 * @param numero double a convertir
	 * @param maximo cantidad maxima de espacios decimales
	 * @return numero decimal convertido
	 */	
	public static double convertirDecimalMaximo(double numero,int maximo)
	{
		return convertirDecimalMaximoMinimo(numero,maximo,-1);
	}

	/**
	 * Convierte un double en un decimal con cantidad minima de espacios decimales
	 * @param numero double a convertir
	 * @param minimo cantidad minima de espacios decimales
	 * @return numero decimal convertido
	 */	
	public static double convertirDecimalMinimo(double numero,int minimo)
	{
		return convertirDecimalMaximoMinimo(numero,-1,minimo);
	}	
	
	/**
	 * Convierte un double en un decimal 
	 * @return numero decimal convertido
	 */
	public static double convertirDecimal(double numero)
	{
		return convertirDecimalMaximoMinimo(numero,0,2);
	}
	
	/**
	 * Convierte un double pasado por parametro a un formato precio (con dos espacios decimales fijos)
	 * @param numero numero a convertir
	 * @return numero convertido a formato precio
	 */
	public static double convertirPrecio(double numero)
	{
		return convertirDecimalMaximoMinimo(numero,2,2);
	}
	
	/**
	 * Convierte un double pasado por parametro a un formato precio (con dos espacios decimales fijos)
	 * @param numero numero a convertir
	 * @return numero convertido a formato precio
	 */	
	public static double precio(double numero)
	{
		return convertirPrecio(numero);
	}

	/**
	 * Convierte un double pasado por parametro a un formato precio (con dos espacios decimales fijos)
	 * @param numero numero a convertir
	 * @return numero convertido a formato precio
	 */	
	public static double decimal(double numero)	
	{
		return convertirDecimal(numero);
	}
	
	/**
	 * Convierte un vector de double pasado por parametro a un formato decimal
	 * @param numeros vector de double con los numeros a convertir
	 * @return vector de double con los numeros convertidos a formato decimal
	 */
	public static double[] decimal(double numeros[])
	{
		if (numeros==null) return null;
		double ret[]=new double[numeros.length];
		for (int i=0;i<ret.length;i++)
			ret[i]=decimal(numeros[i]);
		return ret;
	}

	/**
	 * Convierte un vector de double pasado por parametro a un formato precio (con dos espacios decimales fijos)
	 * @param numeros vector de double con los numeros a convertir
	 * @return vector de double con los numeros convertidos a formato precio
	 */	
	public static double[] precio(double numeros[])
	{
		if (numeros==null) return null;
		double ret[]=new double[numeros.length];
		for (int i=0;i<ret.length;i++)
			ret[i]=precio(numeros[i]);
		return ret;
	}	
	
	/**
	 * Realiza la suma de dos numeros pasados por parametro
	 * @param sumando1 primer valor a sumar
	 * @param sumando2 segundo valor a sumar
	 * @return resultado de la suma
	 */
	public static double suma(double sumando1,double sumando2)
	{
		return sumando1+sumando2;
	}

	/**
	 * Realiza la suma de dos numeros pasados por parametro
	 * @param minuendo valor a restar
	 * @param sustraendo valor que se extrae del minuendo
	 * @return diferencia entre los valores
	 */	
	public static double diferencia(double minuendo,double sustraendo)
	{
		return minuendo-sustraendo;
	}

	/**
	 * Realiza la division de dos numeros pasados por parametro
	 * @param dividendo valor sobre el cual se aplica la division
	 * @param divisor valor utilizado para dividir el dividendo
	 * @return cociente entre los valores
	 */		
	public static double cociente(double dividendo,double divisor)
	{
		return dividendo/divisor;
	}

	/**
	 * Realiza el producto de dos numeros pasados por parametro
	 * @param factor1 primer valor a multiplicar
	 * @param factor2 segundo valor a multiplicar
	 * @return producto entre los valores
	 */			
	public static double producto(double factor1,double factor2)
	{
		return factor1*factor2;
	}
	
	/**
	 * Calcula el factorial de un numero
	 * @param numero numero sobre el que se calcula el factorial
	 * @return factorial del numero pasado por parametro
	 */
	public static double factorial(double numero)
	{
		int num=(int)numero;
		double valor=1;
		for (int i=num;i!=0;i--)
			valor*=i;
		return valor;
	}
	
	/**
	 * Calcula una funcion sobre un numero
	 * @param calculo int que representa el tipo de calculo a efectuar
	 * @param valor numero sobre el cual se efectua el calculo
	 * @return resultado del calculo
	 */
	public static double calcular(int calculo,double valor) throws Exception
	{
		switch (calculo)
		{
			case LOGARITMO_NATURAL:return Math.log(valor);
			case SENO:return Math.sin(valor);
			case COSENO:return Math.cos(valor);
			case TANGENTE:return Math.tan(valor);
			case FACTORIAL:return factorial(valor);
		}		
		throw new Exception("funcion inexistente");
	}

	/**
	 * Calcula una operacion sobre dos numeros
	 * @param calculo int que indica el tipo de calculo a efectuar
	 * @param valor1 primer numero sobre el cual se efectua el calculo
	 * @param valor2 segundo numero sobre el cual se efectua el calculo
	 * @return resultado del calculo
	 */		
	public static double calcular(int calculo,double valor1,double valor2) throws Exception
	{
		switch (calculo)
		{
			case LOGARITMO:return Math.log(valor1)/Math.log(valor2);
			case SUMA:return suma(valor1,valor2);
			case DIFERENCIA:return diferencia(valor1,valor2);
			case COCIENTE:return cociente(valor1,valor2);
			case PRODUCTO:return producto(valor1,valor2);
			case RAIZ:return Math.pow(valor1,1/valor2);	
			case POTENCIA: return Math.pow(valor1,valor2);
		}
		throw new Exception("operacion inexistente");
	}

	/**
	 * Calcula una funcion sobre un numero
	 * @param funcion que inica el tipo de calculo a efectuar
	 * @param valor numero sobre el cual se efectua el calculo
	 * @return resultado del calculo
	 */	
	public static double calcular(String funcion,double valor) throws Exception
	{
		if (funcion.equals("ln")) return calcular(LOGARITMO_NATURAL,valor);
		if (funcion.equals("sin")) return calcular(SENO,valor);
		if (funcion.equals("cos")) return calcular(COSENO,valor);
		if (funcion.equals("tan")) return calcular(TANGENTE,valor);
		if (funcion.equals("!")) return calcular(FACTORIAL,valor);
		throw new Exception ("nombre de funcion incorrecto");
	}
	
	/**
	 * Calcula una operacion sobre dos numeros
	 * @param operacion que inica el tipo de calculo a efectuar
	 * @param valor1 primer numero sobre el cual se efectua el calculo
	 * @param valor2 segundo numero sobre el cual se efectua el calculo*
	 * @return resultado del calculo
	 */	
	public static double calcular(String operacion,double valor1,double valor2) throws Exception
	{
		if (operacion.equals("L")) return calcular(LOGARITMO,valor1,valor2);
		if (operacion.equals("+")) return calcular(SUMA,valor1,valor2);
		if (operacion.equals("-")) return calcular(DIFERENCIA,valor1,valor2);
		if (operacion.equals("/")) return calcular(COCIENTE,valor1,valor2);
		if (operacion.equals("*")) return calcular(PRODUCTO,valor1,valor2);
		if (operacion.equals("r")) return calcular(RAIZ,valor1,valor2);
		if (operacion.equals("p")) return calcular(POTENCIA,valor1,valor2);
		throw new Exception ("nombre de operacion incorrecto");
	}
	
	/**
	 * verifica la consistencia de los extremos de un intervalor para el calculo de raices
	 * @param expresion expresion de la funcion
	 * @param intervalo vector de doubles que son los extremos del intervalo
	 */
	private static void verificarConsistenciaIntervaloRaiz(String expresion,double intervalo[]) throws Exception
	{
		if (intervalo.length!=2)
		{
			throw new Exception("Tamanio de intervalo incorrecto");			
		}
		if (intervalo[0]>=intervalo[1])
		{
			throw new Exception("Inconsistencia de valores de intervalo");
		}
		if ((getY(expresion,intervalo[0])*getY(expresion,intervalo[1]))>0)
		{
			throw new Exception("Valores de ordenada de intervalo de igual signo");
		}
	}
	
	/**
	 * Calcula una raiz con el metodo de la biseccion
	 * @param expresion expresion de la funcion
	 * @param a intervalo inferior
	 * @param b intervalo superior
	 * @param errorX maximo error de abscisa permitido
	 * @param errorY maximo error de ordenada permitido
	 * @return raiz aproximada de la funcion en el intervalo
	 */
	public static double calcularRaizBiseccion(String expresion,double a,double b,double errorX,double errorY) throws Exception
	{
		return calcularRaizBiseccion(expresion,new double[]{a,b},errorX,errorY);
	}

	/**
	 * Calcula una raiz con el metodo de la biseccion
	 * @param expresion expresion de la funcion
	 * @param a intervalo inferior
	 * @param b intervalo superior
	 * @return raiz aproximada de la funcion en el intervalo
	 */	
	public static double calcularRaizBiseccion(String expresion,double a,double b) throws Exception
	{
		return calcularRaizBiseccion(expresion,new double[]{a,b},0.01,0.01);
	}	

	/**
	 * Calcula una raiz con el metodo de la biseccion
	 * @param expresion expresion de la funcion
	 * @param intervalo intervalos de calculo de raiz
	 * @return raiz aproximada de la funcion en el intervalo
	 */	
	public static double calcularRaizBiseccion(String expresion,double intervalo[]) throws Exception
	{
		return calcularRaizBiseccion(expresion,intervalo,0.01,0.01);
	}	

	/**
	 * Calcula una raiz con el metodo de la biseccion
	 * @param expresion expresion de la funcion
	 * @param intervalo intervalo de calculo de raiz
	 * @param errorX maximo error de abscisa permitido
	 * @param errorY maximo error de ordenada permitido
	 * @return raiz aproximada de la funcion en el intervalo
	 */	
	public static double calcularRaizBiseccion(String expresion,double intervalo[],double errorX,double errorY) throws Exception
	{
		verificarConsistenciaIntervaloRaiz(expresion,intervalo);

		return procedimientoBiseccion(expresion,intervalo,errorX,errorY);
	}
	
	/**
	 * Verifica inconsistencias en los errores de abscisa y ordenada
	 * @param errorX maximo error de abscisa permitido
	 * @param errorY maximo error de ordenada permitido
	 */
	private static void verificarErrorRaiz(double errorX,double errorY) throws Exception
	{
		if (errorX==0||errorY==0)
		{
			throw new Exception("Error no puede ser 0");
		}				
		if (errorX>1||errorY>1)		
		{
			throw new Exception("Error no puede ser mayor a 1");
		}
	}
	
	/**
	 * Aplica el procedimiento de la biseccion
	 * @param expresion expresion de la funcion
	 * @param intervalo intervalo de calculo de raiz
	 * @param errorX maximo error de abscisa permitido
	 * @param errorY maximo error de ordenada permitido
	 * @return raiz aproximada de la funcion en el intervalo
	 */	
	private static double procedimientoBiseccion(String expresion,double intervalo[],double errorX,double errorY) throws Exception
	{
		double ultimoX=intervalo[0];
		double x=intervalo[0];
		double y=0;
		int c=0;
		do
		{
			ultimoX=x;
			x=(intervalo[0]+intervalo[1])/2;
			y=getY(expresion,x);
			if ((y*getY(expresion,intervalo[1]))<0)
			{
				intervalo[0]=x;
				intervalo[1]=intervalo[1];
			} else
			{
				intervalo[0]=intervalo[0];
				intervalo[1]=x;
			}
			c++;
			if (c==MAXIMO_LOOP)
				throw new Exception("Maximo loop alcanzado");
		}
		while (!cumpleCondicionErrorX(x,ultimoX,errorX)||!cumpleCondicionErrorY(y,errorY));
		return x;
	}

	/**
	 * Calcula una raiz con el metodo de la secante
	 * @param expresion expresion de la funcion
	 * @param a intervalo inferior
	 * @param b intervalo superior
	 * @param errorX maximo error de abscisa permitido
	 * @param errorY maximo error de ordenada permitido
	 * @return raiz aproximada de la funcion en el intervalo
	 */
	public static double calcularRaizSecante(String expresion,double a,double b,double errorX,double errorY) throws Exception
	{
		return calcularRaizSecante(expresion,new double[]{a,b},errorX,errorY);
	}

	/**
	 * Calcula una raiz con el metodo de la secante
	 * @param expresion expresion de la funcion
	 * @param a intervalo inferior
	 * @param b intervalo superior
	 * @return raiz aproximada de la funcion en el intervalo
	 */	
	public static double calcularRaizSecante(String expresion,double a,double b) throws Exception
	{
		return calcularRaizSecante(expresion,new double[]{a,b},0.01,0.01);
	}	

	/**
	 * Calcula una raiz con el metodo de la secante
	 * @param expresion expresion de la funcion
	 * @param intervalo intervalo de calculo
	 * @return raiz aproximada de la funcion en el intervalo
	 */	
	public static double calcularRaizSecante(String expresion,double intervalo[]) throws Exception
	{
		return calcularRaizSecante(expresion,intervalo,0.01,0.01);
	}	

	/**
	 * Calcula una raiz con el metodo de la secante
	 * @param expresion expresion de la funcion
	 * @param intervalo intervalo de calculo
	 * @param errorX maximo error de abscisa permitido
	 * @param errorY maximo error de ordenada permitido
	 * @return raiz aproximada de la funcion en el intervalo
	 */	
	public static double calcularRaizSecante(String expresion,double intervalo[],double errorX,double errorY) throws Exception
	{
		verificarConsistenciaIntervaloRaiz(expresion,intervalo);
		verificarErrorRaiz(errorX,errorY);
		return procedimientoSecante(expresion,intervalo,errorX,errorY);
	}	

	/**
	 * Aplica el metodo de la secante
	 * @param expresion expresion de la funcion
	 * @param intervalo intervalo de calculo
	 * @param errorX maximo error de abscisa permitido
	 * @param errorY maximo error de ordenada permitido
	 * @return raiz aproximada de la funcion en el intervalo
	 */	
	private static double procedimientoSecante(String expresion,double intervalo[],double errorX,double errorY) throws Exception
	{
		double ultimoX=intervalo[0];
		double x=intervalo[0];
		double y=0;
		double y0,y1;
		int c=0;
		do
		{
			ultimoX=x;
			y0=getY(expresion,intervalo[0]);
			y1=getY(expresion,intervalo[1]);
			x=(intervalo[0]*y1-intervalo[1]*y0)/(y1-y0);
			y=getY(expresion,x);
			if ((y*y1)<0)
			{
				intervalo[0]=x;
			} else
			{
				intervalo[1]=x;
			}
			c++;
			if (c==MAXIMO_LOOP)
				throw new Exception("Maximo loop alcanzado");
		}
		while (!cumpleCondicionErrorX(x,ultimoX,errorX)||!cumpleCondicionErrorY(y,errorY));
		return x;
	}	
	
	/**
	 * Calcula una raiz con el metodo del punto fijo
	 * @param expresion expresion de la funcion 
	 * @param funcionG funcion g del metodo de punto fijo
	 * @param a intervalo inferior
	 * @param b intervalo superior
	 * @param errorX maximo error de abscisa permitido
	 * @param errorY maximo error de ordenada permitido
	 * @return raiz aproximada de la funcion en el intervalo
	 */	
	public static double calcularRaizPuntoFijo(String expresion,String funcionG,double a,double b,double errorX,double errorY) throws Exception
	{
		return calcularRaizPuntoFijo(expresion,funcionG,new double[]{a,b},errorX,errorY);
	}

	/**
	 * Calcula una raiz con el metodo del punto fijo
	 * @param expresion expresion de la funcion 
	 * @param funcionG funcion g del metodo de punto fijo
	 * @param a intervalo inferior
	 * @param b intervalo superior
	 * @return raiz aproximada de la funcion en el intervalo
	 */	
	public static double calcularRaizPuntoFijo(String expresion,String funcionG,double a,double b) throws Exception
	{
		return calcularRaizPuntoFijo(expresion,funcionG,new double[]{a,b},0.01,0.01);
	}	

	/**
	 * Calcula una raiz con el metodo del punto fijo
	 * @param expresion expresion de la funcion 
	 * @param funcionG funcion g del metodo de punto fijo
	 * @param intervalo intervalo de calculo
	 * @return raiz aproximada de la funcion en el intervalo
	 */		
	public static double calcularRaizPuntoFijo(String expresion,String funcionG,double intervalo[]) throws Exception
	{
		return calcularRaizPuntoFijo(expresion,funcionG,intervalo,0.01,0.01);
	}	

	/**
	 * Calcula una raiz con el metodo del punto fijo creando la funcionG 
	 * @param expresion expresion de la funcion 
	 * @param derivada funcion de la derivada a partir de la cual se construye la funcion G
	 * @param a intervalo inferior
	 * @param b intervalo superior
	 * @param errorX maximo error de abscisa permitido
	 * @param errorY maximo error de ordenada permitido
	 * @param marca boolean que indica que se construye la funcionG
	 * @return raiz aproximada de la funcion en el intervalo
	 */		
	public static double calcularRaizPuntoFijo(String expresion,String derivada,double a,double b,double errorX,double errorY,boolean marca) throws Exception
	{
		return calcularRaizPuntoFijo(expresion,derivada,new double[]{a,b},errorX,errorY,marca);
	}

	/**
	 * Calcula una raiz con el metodo del punto fijo creando la funcionG 
	 * @param expresion expresion de la funcion 
	 * @param derivada funcion de la derivada a partir de la cual se construye la funcion G
	 * @param a intervalo inferior
	 * @param b intervalo superior
	 * @param marca boolean que indica que se construye la funcionG
	 * @return raiz aproximada de la funcion en el intervalo
	 */	
	public static double calcularRaizPuntoFijo(String expresion,String derivada,double a,double b,boolean marca) throws Exception
	{
		return calcularRaizPuntoFijo(expresion,derivada,new double[]{a,b},0.01,0.01,marca);
	}	

	/**
	 * Calcula una raiz con el metodo del punto fijo creando la funcionG 
	 * @param expresion expresion de la funcion 
	 * @param derivada funcion de la derivada a partir de la cual se construye la funcion G
	 * @param intervalo intervalo de calculo
	 * @param marca boolean que indica que se construye la funcionG
	 * @return raiz aproximada de la funcion en el intervalo
	 */	
	public static double calcularRaizPuntoFijo(String expresion,String derivada,double intervalo[],boolean marca) throws Exception
	{
		return calcularRaizPuntoFijo(expresion,derivada,intervalo,0.01,0.01,marca);
	}	

	/**
	 * Calcula una raiz con el metodo del punto fijo creando la funcionG 
	 * @param expresion expresion de la funcion 
	 * @param derivada funcion de la derivada a partir de la cual se construye la funcion G
	 * @param intervalo intervalo de calculo
	 * @param errorX maximo error de abscisa permitido
	 * @param errorY maximo error de ordenada permitido
	 * @param marca boolean que indica que se construye la funcionG
	 * @return raiz aproximada de la funcion en el intervalo
	 */	
	public static double calcularRaizPuntoFijo(String expresion,String derivada,double intervalo[],double errorX,double errorY,boolean marca) throws Exception
	{
		double m=getY(derivada,intervalo[0]);
		String funcionG="(x)-(("+expresion+")/"+28+")";
		return calcularRaizPuntoFijo(expresion,funcionG,intervalo,errorX,errorY);
	}	

	/**
	 * Calcula una raiz con el metodo del punto fijo
	 * @param expresion expresion de la funcion 
	 * @param funcionG funcion g del metodo de punto fijo
	 * @param intervalo intervalo de calculo
	 * @param errorX maximo error de abscisa permitido
	 * @param errorY maximo error de ordenada permitido
	 * @return raiz aproximada de la funcion en el intervalo
	 */		
	public static double calcularRaizPuntoFijo(String expresion,String funcionG,double intervalo[],double errorX,double errorY) throws Exception
	{
		verificarConsistenciaIntervaloRaiz(expresion,intervalo);
		verificarErrorRaiz(errorX,errorY);
		if (verificarConvergenciaPuntoFijo(funcionG,intervalo))
		{
			throw new Exception("No converge funcion G");
		}		
		return procedimientoPuntoFijo(expresion,funcionG,intervalo,errorX,errorY);
	}	

	/**
	 * Aplica el metodo del punto fijo
	 * @param expresion expresion de la funcion 
	 * @param funcionG funcion g del metodo de punto fijo
	 * @param intervalo intervalo de calculo
	 * @param errorX maximo error de abscisa permitido
	 * @param errorY maximo error de ordenada permitido
	 * @return raiz aproximada de la funcion en el intervalo
	 */		
	private static double procedimientoPuntoFijo(String expresion,String funcionG,double intervalo[],double errorX,double errorY) throws Exception
	{
		double ultimoX=intervalo[0];
		double x=intervalo[0];
		double y=0;
		double y0,y1;
		int c=0;
		do
		{
			ultimoX=x;
			x=getY(funcionG,x);
			y=getY(expresion,x);
			c++;
			if (c==MAXIMO_LOOP)
				throw new Exception("Maximo loop alcanzado");
		}
		while (!cumpleCondicionErrorX(x,ultimoX,errorX)||!cumpleCondicionErrorY(y,errorY));
		return x;
	}	
	
	/**
	 * Verifica si converge el metodo de punto fijo
	 * @param funcion expresion de la funcion sobre la cual se efectua el calculo
	 * @param intervalo intervalo de calculo
	 * @return boolean que indica si converge el punto fijo
	 */
	private static boolean verificarConvergenciaPuntoFijo(String funcion,double intervalo[]) throws Exception
	{
		double y0=getY(funcion,intervalo[0]);
		double y1=getY(funcion,intervalo[1]);
		return y0<1&&y0>-11&&y1<1&&y1>-1;
	}

	/**
	 * Calcula una raiz con el metodo del punto newthon raphson
	 * @param expresion expresion de la funcion 
	 * @param derivada expresion de la derivada de la funcion
	 * @param a intervalo inferior
	 * @param b intervalo superior
	 * @param errorX maximo error de abscisa permitido
	 * @param errorY maximo error de ordenada permitido
	 * @return raiz aproximada de la funcion en el intervalo
	 */		
	public static double calcularRaizNewtonRaphson(String expresion,String derivada,double a,double b,double errorX,double errorY) throws Exception
	{
		return calcularRaizNewtonRaphson(expresion,derivada,new double[]{a,b},errorX,errorY);
	}

	/**
	 * Calcula una raiz con el metodo del punto newthon raphson
	 * @param expresion expresion de la funcion 
	 * @param derivada expresion de la derivada de la funcion
	 * @param a intervalo inferior
	 * @param b intervalo superior
	 * @return raiz aproximada de la funcion en el intervalo
	 */	
	public static double calcularRaizNewtonRaphson(String expresion,String derivada,double a,double b) throws Exception
	{
		return calcularRaizNewtonRaphson(expresion,derivada,new double[]{a,b},0.01,0.01);
	}	

	/**
	 * Calcula una raiz con el metodo del punto newthon raphson
	 * @param expresion expresion de la funcion 
	 * @param derivada expresion de la derivada de la funcion
	 * @param intervalo intervalo de calculo
	 * @return raiz aproximada de la funcion en el intervalo
	 */	
	public static double calcularRaizNewtonRaphson(String expresion,String derivada,double intervalo[]) throws Exception
	{
		return calcularRaizNewtonRaphson(expresion,derivada,intervalo,0.01,0.01);
	}		

	/**
	 * Calcula una raiz con el metodo del punto newthon raphson
	 * @param expresion expresion de la funcion 
	 * @param derivada expresion de la derivada de la funcion
	 * @param intervalo intervalo de calculo
	 * @param errorX maximo error de abscisa permitido
	 * @param errorY maximo error de ordenada permitido
	 * @return raiz aproximada de la funcion en el intervalo
	 */	
	public static double calcularRaizNewtonRaphson(String expresion,String derivada,double intervalo[],double errorX,double errorY) throws Exception
	{
		String funcionG="(x)-(("+expresion+")/(("+derivada+")))";
		return calcularRaizPuntoFijo(expresion,funcionG,intervalo,errorX,errorY);
	}	
	
	/**
	 * Verifica el cumplimiento de error en la abscisa
	 * @param ultimo ultimo valor de la abscisa
	 * @param ultimo anterior valor de la abscisa
	 * @param error maximo error de abscisa permitido
	 * @return boolean que indica el cumplimiento de error en la abscisa
	 */
	private static boolean cumpleCondicionErrorX(double ultimo,double anterior,double error) 
	{
		double res=ultimo-anterior;
		if (res<0)	res*=-1;
		if (error<0) error*=-1;
		if (res<error) return true;
		return false;
	}

	/**
	 * Verifica el cumplimiento de error en la ordenada
	 * @param ultimo ultimo valor de la ordenada
	 * @param ultimo anterior valor de la ordenada
	 * @param error maximo error de ordenada permitido
	 * @return boolean que indica el cumplimiento de error en la ordenada
	 */	
	private static boolean cumpleCondicionErrorY(double y,double error)
	{
		if (y<0) y*=-1;
		if (error<0) error*=-1;
		if (y<error) return true;
		return false;
	}
	
	/**
	 * Calcula el valor de la ordenada de una funcion para un valor de la abscisa
	 * @param expresion expresion de la funcion de calculo
	 * @param x valor de la abscisa
	 * @return valor de la ordenada para el valor de la abscisa
	 */
	private static double getY(String expresion,double x) throws Exception
	{
		double res=AnalizadorFuncion.procesarYCalcular(expresion,x);
		return res;
	}

	/**
	 * Calcula el valor de la funcion de dos variables para un valor de x e y dados
	 * @param expresion expresion de la funcion de calculo
	 * @param x valor de x
	 * @param y valor de y
	 * @return valor de la funcion para los valores x e y
	 */	
	private static double getZ(String expresion,double x,double y) throws Exception
	{
		double res=AnalizadorFuncion.procesarYCalcular(expresion,x,y);
		return res;
	}

	/**
	 * Calcula los valores de los coeficientes para los puntos y las funciones dadas
	 * @param puntos valores de x e y de los puntos existentes
	 * @param funciones expresion de las funciones cuyos coeficientes son calculados
	 * @return valores de los coeficientes para los puntos y las funciones dadas
	 */
	public static double[] calcularMinimosCuadrados(double puntos[][],String funciones[]) throws Exception
	{
		if (puntos==null||funciones==null||puntos.length==0||funciones.length==0)
		{
			throw new Exception("puntos o funciones inapropiados");
		}
		if (puntos[0].length!=2)
		{
			throw new Exception("matriz de puntos incosistentes");
		}
		return procedimientoMinimosCuadrados(puntos,funciones);
	}

	/**
	 * Obtiene la expresion de la funcion con los coeficientes y las funciones definidas
	 * @param puntos valores de x e y de los puntos existentes
	 * @param funciones expresion de las funciones cuyos coeficientes son calculados
	 * @return expresion de la funcion con los coeficientes y las funciones definidas
	 */	
	public static String obtenerExpresionMinimosCuadrados(double puntos[][],String funciones[]) throws Exception
	{
		double res[]=calcularMinimosCuadrados(puntos,funciones);
		String aux="";
		for (int i=0;i<funciones.length;i++)
		{
			aux+=res[i]+"*"+funciones[i];
			if (i!=funciones.length-1) aux+="+";
		}
		return aux;
	}
	
	/**
	 * Aplica el metodo de los minimos cuadrados para el calculo de los coeficientes de la expresion para los puntos y las funciones dadas
	 * @param puntos valores de x e y de los puntos existentes
	 * @param funciones expresion de las funciones cuyos coeficientes son calculados
	 * @return valores de los coeficientes para los puntos y las funciones dadas
	 */
	private static double[] procedimientoMinimosCuadrados(double puntos[][],String funciones[]) throws Exception
	{
		double matriz[][]=new double[funciones.length][funciones.length+1];
		for (int i=0;i<matriz.length;i++)
		{
			for (int j=0;j<matriz.length;j++)
			{
				matriz[i][j]=sumaCoeficientesFilaMinimosCuadrados(puntos,funciones,i,j);
			}
			matriz[i][funciones.length]=sumaIndependientesFilaMinimosCuadrados(puntos,funciones,i);
		}
		return calcularCholesky(matriz);
	}
	
	/**
	 * Metodo para el calculo de los valores de cada posicion de fila y columna en la matriz de coeficientes de los minimos cuadrados
	 */
	private static double sumaCoeficientesFilaMinimosCuadrados(double puntos[][],String funciones[],int i,int j) throws Exception
	{
		double aux=0;
		for (int k=0;k<puntos.length;k++)
		{
			aux+=getY(funciones[i],puntos[k][0])*getY(funciones[j],puntos[k][0]);
		}
		return aux;
	}

	/**
	 * Metodo para el calculo de los valores de cada posicion de fila en el vector de terminos independientes de los minimos cuadrados
	 */	
	private static double sumaIndependientesFilaMinimosCuadrados(double puntos[][],String funciones[],int i) throws Exception
	{
		double aux=0;
		for (int k=0;k<puntos.length;k++)
		{
			aux+=puntos[k][1]*getY(funciones[i],puntos[k][0]);
		}
		return aux;		
	}
	
	/**
	 * Calcula el valor de a ordenada en una ecuacion diferencial
	 * @param funcion expresion de la funcion del tipo y'=f(x,y)
	 * @param xm valor x de la condicion inicial
	 * @param ym valor y de la condicion inicial
	 * @param h diferencia de abscisa entre dos puntos conocidos
	 * @param pasos cantidad de pasos de calculo
	 * @param tipo int que indica tipo de metodo a utilizar para el calculo
	 * @return valor de la ordenada en una ecuacion diferencial
	 */
	public static double calcularEcuacionDiferencial(String funcion,double xm,double ym,double h,int pasos,int tipo) throws Exception
	{
		verificarEcuacionDiferencial(h,pasos);
		switch (tipo)			
		{
			case EULER:return procedimientoEuler(funcion,xm,ym,h,pasos);
			case EULER_MEJORADO:return procedimientoEulerMejorado(funcion,xm,ym,h,pasos);
			case EULER_MODIFICADO:return procedimientoEulerModificado(funcion,xm,ym,h,pasos);
			case RUNGE_KUTTA_4:return procedimientoRungeKutta4(funcion,xm,ym,h,pasos);
			case PREDICTOR_CORRECTOR:return calcularPredictorCorrector(funcion,xm+h,calcularRungeKutta4(funcion,xm,ym,h,1),ym,h,pasos,false,ERROR_DEFECTO);
		}
		throw new Exception("Metodo de ecuacion diferencial inexistente");
			
	}
	
	/**
	 * Verifica la integridad de los valores de calculo de una ecuacion diferencial
	 * @param h diferencia de abscisa entre dos puntos conocidos
	 * @param pasos cantidad de pasos de calculo
	 */
	public static void verificarEcuacionDiferencial(double h,int pasos) throws Exception
	{
		if (h>1)
		{
			throw new Exception("h no puede ser mayor que 1");
		}
		if (pasos<1)
		{
			throw new Exception("minimo de pasos igual a 1");
		}		
	}
	
	/**
	 * Aplica el procedimiento de euler para el calculo de una ecuacion diferencial
	 * @param funcion expresion de la funcion del tipo y'=f(x,y)
	 * @param xm valor x de la condicion inicial
	 * @param ym valor y de la condicion inicial
	 * @param h diferencia de abscisa entre dos puntos conocidos
	 * @param pasos cantidad de pasos de calculo
	 * @return valor de la ordenada en una ecuacion diferencial
	 */
	private static double procedimientoEuler(String funcion,double xm,double ym,double h,int pasos) throws Exception
	{
		int x,y;
		for (int i=0;i<pasos;i++)
		{
			ym=ym+h*getZ(funcion,xm,ym);
			xm=xm+h;
		}
		return ym;
	}

	/**
	 * Aplica el procedimiento de euler mejorado para el calculo de una ecuacion diferencial
	 * @param funcion expresion de la funcion del tipo y'=f(x,y)
	 * @param xm valor x de la condicion inicial
	 * @param ym valor y de la condicion inicial
	 * @param h diferencia de abscisa entre dos puntos conocidos
	 * @param pasos cantidad de pasos de calculo
	 * @return valor de la ordenada en una ecuacion diferencial
	 */	
	private static double procedimientoEulerMejorado(String funcion,double xm,double ym,double h,int pasos) throws Exception
	{
		int x,y;
		double phy;
		for (int i=0;i<pasos;i++)
		{
			phy=(0.5)*(getZ(funcion,xm,ym)+getZ(funcion,xm+h,calcularEuler(funcion,xm,ym,h,1)));
			ym=ym+h*phy;
			xm=xm+h;
		}
		return ym;
	}

	/**
	 * Aplica el procedimiento de euler modificado para el calculo de una ecuacion diferencial
	 * @param funcion expresion de la funcion del tipo y'=f(x,y)
	 * @param xm valor x de la condicion inicial
	 * @param ym valor y de la condicion inicial
	 * @param h diferencia de abscisa entre dos puntos conocidos
	 * @param pasos cantidad de pasos de calculo
	 * @return valor de la ordenada en una ecuacion diferencial
	 */	
	private static double procedimientoEulerModificado(String funcion,double xm,double ym,double h,int pasos) throws Exception
	{
		int x,y;
		double phy;
		for (int i=0;i<pasos;i++)
		{
			phy=getZ(funcion,xm+(h/2),ym+(h/2)*getZ(funcion,xm,ym));
			ym=ym+h*phy;
			xm=xm+h;
		}
		return ym;
	}
	
	/**
	 * Aplica el procedimiento de runge kutta de 4 orden para el calculo de una ecuacion diferencial
	 * @param funcion expresion de la funcion del tipo y'=f(x,y)
	 * @param xm valor x de la condicion inicial
	 * @param ym valor y de la condicion inicial
	 * @param h diferencia de abscisa entre dos puntos conocidos
	 * @param pasos cantidad de pasos de calculo
	 * @return valor de la ordenada en una ecuacion diferencial
	 */	
	private static double procedimientoRungeKutta4(String funcion,double xm,double ym,double h,int pasos) throws Exception
	{
		int x,y;
		double k1,k2,k3,k4;
		for (int i=0;i<pasos;i++)
		{
			k1=getZ(funcion,xm,ym);
			k2=getZ(funcion,xm+h/2,ym+h*k1/2);
			k3=getZ(funcion,xm+h/2,ym+h*k2/2);
			k4=getZ(funcion,xm+h,ym+h*k3);
			ym=ym+(h/6)*(k1+2*k2+2*k3+k4);
			xm=xm+h;
		}
		return ym;
	}
	
	/**
	 * Aplica el procedimiento predictor corrector para el calculo de una ecuacion diferencial
	 * @param funcion expresion de la funcion del tipo y'=f(x,y)
	 * @param xm valor x de la condicion inicial
	 * @param ym_1 valor de la ordenada para xm-1
	 * @param ym valor y de la condicion inicial
	 * @param h diferencia de abscisa entre dos puntos conocidos
	 * @param pasos cantidad de pasos de calculo
	 * @param error error maximo aceptado al corregir el valor de la ordenada
	 * @return valor de la ordenada en una ecuacion diferencial
	 */	
	private static double procedimientoPredictorCorrector(String funcion,double xm,double ym,double ym_1,double h,int pasos,double error) throws Exception
	{
		int x,y;
		double yim_1,ym_ant,dif,ym_orig,ymmas1=0;
		for (int i=0;i<pasos;i++)
		{
			ym_orig=ym;
			ymmas1=procedimientoPredictor(funcion,xm,ym,ym_1,h);
			xm=xm+h;
			int c=0;
			do
			{
				ym_ant=ymmas1;
				ymmas1=procedimientoCorrector(funcion,xm,ym,xm+h,ymmas1,h);
				dif=ymmas1-ym_ant;
				if (dif<0) dif*=-1;
				c++;
				if (c==MAXIMO_LOOP)
				{
					throw new Exception("Maximo loop alcanzado");
				}			
			}
			while (dif>error);
			ym_1=ym_orig;
		}
		return ymmas1;
	}	
	
	/**
	 * Implementa el procedimiento predictor en el predictor corrector
	 * @param funcion expresion de la funcion del tipo y'=f(x,y)
	 * @param xm valor x de la condicion inicial
	 * @param ym_1 valor de la ordenada para xm-1
	 * @param ym valor y de la condicion inicial
	 * @param h diferencia de abscisa entre dos puntos conocidos
	 * @return valor de la ordenada predicho
	 */
	private static double procedimientoPredictor(String funcion,double xm,double ym,double ym_1,double h) throws Exception
	{
		return ym_1+2*h*getZ(funcion,xm,ym);
	}

	/**
	 * Implementa el procedimiento corrector en el predictor corrector
	 * @param funcion expresion de la funcion del tipo y'=f(x,y)
	 * @param xm valor x de la condicion inicial
	 * @param xm_1 valor x de la condicion inicial mas h
	 * @param ym_1 valor de la ordenada para xm-1
	 * @param ym valor y de la condicion inicial
	 * @param h diferencia de abscisa entre dos puntos conocidos
	 * @return valor de la ordenada corregido
	 */	
	private static double procedimientoCorrector(String funcion,double xm,double ym,double xm_1,double yim_1,double h) throws Exception
	{
		double res= ym+h/2*(getZ(funcion,xm,ym))+getZ(funcion,xm_1,yim_1);
		return res;
		
	}

	/**
	 * Calcula el valor de a ordenada en una ecuacion diferencial con el metodo de euler
	 * @param funcion expresion de la funcion del tipo y'=f(x,y)
	 * @param xm valor x de la condicion inicial
	 * @param ym valor y de la condicion inicial
	 * @param h diferencia de abscisa entre dos puntos conocidos
	 * @param pasos cantidad de pasos de calculo
	 * @return valor de la ordenada en una ecuacion diferencial
	 */	
	public static double calcularEuler(String funcion,double xm,double ym,double h,int pasos) throws Exception
	{
		return calcularEcuacionDiferencial(funcion,xm,ym,h,pasos,EULER);
	}

	/**
	 * Calcula el valor de a ordenada en una ecuacion diferencial con el metodo de euler mejorado
	 * @param funcion expresion de la funcion del tipo y'=f(x,y)
	 * @param xm valor x de la condicion inicial
	 * @param ym valor y de la condicion inicial
	 * @param h diferencia de abscisa entre dos puntos conocidos
	 * @param pasos cantidad de pasos de calculo
	 * @return valor de la ordenada en una ecuacion diferencial
	 */		
	public static double calcularEulerMejorado(String funcion,double xm,double ym,double h,int pasos) throws Exception
	{
		return calcularEcuacionDiferencial(funcion,xm,ym,h,pasos,EULER_MEJORADO);
	}

	/**
	 * Calcula el valor de a ordenada en una ecuacion diferencial con el metodo de euler modificado
	 * @param funcion expresion de la funcion del tipo y'=f(x,y)
	 * @param xm valor x de la condicion inicial
	 * @param ym valor y de la condicion inicial
	 * @param h diferencia de abscisa entre dos puntos conocidos
	 * @param pasos cantidad de pasos de calculo
	 * @return valor de la ordenada en una ecuacion diferencial
	 */		
	public static double calcularEulerModificado(String funcion,double xm,double ym,double h,int pasos) throws Exception
	{
		return calcularEcuacionDiferencial(funcion,xm,ym,h,pasos,EULER_MODIFICADO);
	}

	/**
	 * Calcula el valor de a ordenada en una ecuacion diferencial con el metodo de runge kutta de 4 orden
	 * @param funcion expresion de la funcion del tipo y'=f(x,y)
	 * @param xm valor x de la condicion inicial
	 * @param ym valor y de la condicion inicial
	 * @param h diferencia de abscisa entre dos puntos conocidos
	 * @param pasos cantidad de pasos de calculo
	 * @return valor de la ordenada en una ecuacion diferencial
	 */		
	public static double calcularRungeKutta4(String funcion,double xm,double ym,double h,int pasos) throws Exception
	{
		return calcularEcuacionDiferencial(funcion,xm,ym,h,pasos,RUNGE_KUTTA_4);
	}

	/**
	 * Calcula el valor de a ordenada en una ecuacion diferencial con el metodo de predictor corrector
	 * @param funcion expresion de la funcion del tipo y'=f(x,y)
	 * @param xm valor x de la condicion inicial
	 * @param ym valor y de la condicion inicial
	 * @param ym_1 valor de la ordenada para xm-1
	 * @param h diferencia de abscisa entre dos puntos conocidos
	 * @param pasos cantidad de pasos de calculo
	 * @param calculado boolean que indica si ya ha sido calculado el valor de ym-1
	 * @param error error maximo aceptado al corregir el valor de la ordenada
	 * @return valor de la ordenada en una ecuacion diferencial
	 */	
	private static double calcularPredictorCorrector(String funcion,double xm,double ym,double ym_1,double h,int pasos,boolean calculado,double error) throws Exception
	{
		verificarEcuacionDiferencial(h,pasos);
		if (!calculado&&pasos<2)
		{
			throw new Exception("cantidad de pasos insuficientes");
		}
		if (error<=0)
		{
			throw new Exception("error no puede ser 0 o negativo");
		}
		if (!calculado) 
		{
			pasos--;
		}
		return procedimientoPredictorCorrector(funcion,xm,ym,ym_1,h,pasos,error);
	}	

	/**
	 * Calcula el valor de a ordenada en una ecuacion diferencial con el metodo de predictor corrector
	 * @param funcion expresion de la funcion del tipo y'=f(x,y)
	 * @param xm valor x de la condicion inicial
	 * @param ym valor y de la condicion inicial
	 * @param ym_1 valor de la ordenada para xm-1
	 * @param h diferencia de abscisa entre dos puntos conocidos
	 * @param pasos cantidad de pasos de calculo
	 * @param error error maximo aceptado al corregir el valor de la ordenada
	 * @return valor de la ordenada en una ecuacion diferencial
	 */	
	public static double calcularPredictorCorrector(String funcion,double xm,double ym,double ym_1,double h,int pasos,double error) throws Exception
	{
		return calcularPredictorCorrector(funcion,xm,ym,ym_1,h,pasos,true,error);
	}

	/**
	 * Calcula el valor de a ordenada en una ecuacion diferencial con el metodo de predictor corrector
	 * @param funcion expresion de la funcion del tipo y'=f(x,y)
	 * @param xm valor x de la condicion inicial
	 * @param ym valor y de la condicion inicial
	 * @param ym_1 valor de la ordenada para xm-1
	 * @param h diferencia de abscisa entre dos puntos conocidos
	 * @param pasos cantidad de pasos de calculo
	 * @return valor de la ordenada en una ecuacion diferencial
	 */		
	public static double calcularPredictorCorrector(String funcion,double xm,double ym,double ym_1,double h,int pasos) throws Exception
	{
		return calcularPredictorCorrector(funcion,xm,ym,ym_1,h,pasos,true,ERROR_DEFECTO);
	}	

	/**
	 * Calcula el valor de a ordenada en una ecuacion diferencial con el metodo de predictor corrector
	 * @param funcion expresion de la funcion del tipo y'=f(x,y)
	 * @param xm valor x de la condicion inicial
	 * @param ym valor y de la condicion inicial
	 * @param h diferencia de abscisa entre dos puntos conocidos
	 * @param pasos cantidad de pasos de calculo
	 * @param error error maximo aceptado al corregir el valor de la ordenada
	 * @param marca_error boolean que indica que el quinto parametro numerico hace referencia al error del metodo
	 * @return valor de la ordenada en una ecuacion diferencial
	 */		
	public static double calcularPredictorCorrector(String funcion,double xm,double ym,double h,int pasos,double error,boolean marca_error) throws Exception
	{
		return calcularPredictorCorrector(funcion,xm+h,calcularRungeKutta4(funcion,xm,ym,h,1),ym,h,pasos,false,error);
	}		

	/**
	 * Calcula el valor de a ordenada en una ecuacion diferencial con el metodo de predictor corrector
	 * @param funcion expresion de la funcion del tipo y'=f(x,y)
	 * @param xm valor x de la condicion inicial
	 * @param ym valor y de la condicion inicial
	 * @param h diferencia de abscisa entre dos puntos conocidos
	 * @param pasos cantidad de pasos de calculo
	 * @return valor de la ordenada en una ecuacion diferencial
	 */	
	public static double calcularPredictorCorrector(String funcion,double xm,double ym,double h,int pasos) throws Exception
	{
		return calcularEcuacionDiferencial(funcion,xm,ym,h,pasos,PREDICTOR_CORRECTOR);
	}				
	
	/**
	 * Calcula el valor de la derivada numerica
	 * @param numero numero de derivada a calcular
	 * @param valores valores de las ordenadas de los puntos conocidos
	 * @param h diferencia de abscisa entre dos puntos conocidos
	 * @return valor de la derivada numerica
	 */
	public static double calcularDerivada(int numero,double valores[],double h) throws Exception
	{
		cargarDerivadas();
		if (numero<1||numero>derivada.length)
		{
			throw new Exception("numero de derivada incorrecta");
		}
		return derivada[numero-1].calcular(valores,h);
	}
	
	/**
	 * Calcula el error del valor de la derivada numerica
	 * @param numero numero de derivada a calcular
	 * @param valores valores de las ordenadas de los puntos conocidos
	 * @param h diferencia de abscisa entre dos puntos conocidos
	 * @return valor del error del valor de la derivada numerica
	 */	
	public static double calcularErrorDerivada(int numero,double valores[],double h) throws Exception
	{
		cargarDerivadas();
		if (numero<1||numero>derivada.length)
		{
			throw new Exception("numero de derivada incorrecta");
		}
		return derivada[numero-1].calcularError(valores,h);
	}	
	
	/**
	 * Calcula el valor de la derivada numerica que surge de la combinacion de dos derivadas
	 * @param numero1 numero de la primera derivada de calculo
	 * @param numero2 numero de la segunda derivada de calculo
	 * @param valores valores de las ordenadas de los puntos conocidos
	 * @param h diferencia de abscisa entre dos puntos conocidos
	 * @return valor de la derivada numerica que surge de la combinacion de dos derivadas
	 */	
	public static double calcularDerivada(int numero1,int numero2,double valores[],double h) throws Exception
	{
		cargarDerivadas();
		if (numero1<1||numero1>derivada.length||numero2<1||numero2>derivada.length)
		{
			throw new Exception("numero de derivada incorrecta");
		}
		if (valores.length<(derivada[numero1-1].getCantidadPuntos()+derivada[numero2-1].getCantidadPuntos()-1))
		{
			throw new Exception("cantidad de puntos insuficientes");
		}
		Derivada der=procedimientoDerivada(numero1-1,numero2-1);
		return der.calcular(valores,h);
	}

	/**
	 * Calcula el valor de la derivada numerica que surge de la combinacion de dos derivadas
	 * @param numero1 numero de la primera derivada de calculo
	 * @param numero2 numero de la segunda derivada de calculo
	 * @param valores valores de las ordenadas de los puntos conocidos
	 * @param h diferencia de abscisa entre dos puntos conocidos
	 * @return valor del error de la derivada numerica que surge de la combinacion de dos derivadas
	 */	
	public static double calcularErrorDerivada(int numero1,int numero2,double valores[],double h) throws Exception
	{
		cargarDerivadas();
		if (numero1<1||numero1>derivada.length||numero2<1||numero2>derivada.length)
		{
			throw new Exception("numero de derivada incorrecta");
		}
		return derivada[numero1-1].calcularError(valores,h)+derivada[numero2-1].calcularError(valores,h);
	}	
	
	/**
	 * Aplica el procedimiento para combinar dos derivadas
	 * @param numero1 numero de la primera derivada de calculo
	 * @param numero2 numero de la segunda derivada de calculo
	 * @return Derivada resultante de la combinacion de dos derivadas
	 */
	private static Derivada procedimientoDerivada(int num1,int num2) throws Exception
	{
		double factor=derivada[num1].getFactor()*derivada[num2].getFactor();
		int orden=derivada[num1].getOrden()+derivada[num2].getOrden();
		int numero=Integer.parseInt(""+derivada[num1].getNumero()+derivada[num2].getNumero());
		int pivot=derivada[num1].getPivot()+derivada[num2].getPivot();
		int factores[]=getFactoresDerivada(derivada[num1].getFactores(),derivada[num2].getFactores());
		return new Derivada(numero,orden,factor,pivot,factores);
	}
	
	/**
	 * Calcula los factores de calculo de una derivada que surge de la combinacion de dos de ellas ya conocidas
	 * @param factores1 factores de la primer derivada conocida
	 * @param factores2 factores de la segunda derivada conocida
	 * @return factores de calculo de una derivada que surge de la combinacion de dos de ellas ya conocidas
	 */
	private static int[] getFactoresDerivada(int factores1[],int factores2[]) throws Exception
	{
		int matriz2[][]=new int[factores1.length][factores1.length+factores2.length-1];
		int matriz1[][]=new int[1][factores1.length];
		for (int i=0;i<matriz2.length;i++)
		{
			matriz1[0][i]=factores1[i];
			for (int j=0;j<factores2.length;j++)
			{
				matriz2[i][j+i]=factores2[j];
			}
		}
		double f[]=productoMatrices(convertirMatrizIntDouble(matriz1),convertirMatrizIntDouble(matriz2))[0];
		int ret[]=new int[f.length];
		for (int i=0;i<f.length;i++)
		{
			ret[i]=(int)f[i];
		}
		return ret;
	}
	
	/**
	 * Convierte una matriz de int en una matriz de double
	 * @param v matriz de int
	 * @return matriz de double
	 */
    private static double[][] convertirMatrizIntDouble(int v[][])
    {
    	double r[][]=new double [v.length][v[0].length];
    	for (int i=0;i<v.length;i++)
    		for (int j=0;j<v[i].length;j++)
    			r[i][j]=(double)v[i][j];

    	return r;
    }
	
	/**
	 * Calcula el producto de dos matrices
	 * @param matriz1 primera matriz factor
	 * @param matriz2 segunda matriz factor
	 * @return matriz resultante del producto de las matrices parametro
	 */
	public static double[][] productoMatrices(double matriz1[][],double matriz2[][]) throws Exception
	{
		if (matriz1.length==0||matriz2.length==0||matriz1[0].length==0||matriz2[0].length==0)
		{
			throw new Exception ("Tamanio de matriz incorrecto");
		}
		if (matriz1[0].length!=matriz2.length)
		{
			throw new Exception ("tamanio de matrices inconsistentes");
		}
		return procedimientoProductoMatrices(matriz1,matriz2);
	}
	
	/**
	 * Aplica el procedimeinto para el calculo del producto de dos matrices
	 * @param matriz1 primera matriz factor
	 * @param matriz2 segunda matriz factor
	 * @return matriz resultante del producto de las matrices parametro
	 */	
	private static double[][] procedimientoProductoMatrices(double matriz1[][],double matriz2[][]) throws Exception
	{
		double aux=0;
		double res[][]=new double[matriz1.length][matriz2[0].length];
		for (int i=0;i<res.length;i++)
			for (int j=0;j<res[i].length;j++)
			{
				aux=0;
				for (int k=0;k<matriz2.length;k++)
				{
					aux+=matriz1[i][k]*matriz2[k][j];
				}
				res[i][j]=aux;
			}
		return res;
	}
	
	/**
	 * Calcula el determinante de una matriz cuadrada
	 * @param matriz matriz cuyo determinante quiere ser conocido
	 * @return el determinante de una matriz cuadrada
	 */
	public static double calcularDeterminanteMatriz(double matriz[][]) throws Exception
	{
		if (matriz.length<2||matriz[0].length<2)
		{
			throw new Exception("Tamanio de matriz incorrecto");
		}
		if (matriz.length!=matriz[0].length)
		{
			throw new Exception("la matriz debe ser cuadrada");
		}
		return procedimientoDeterminanteMatriz(matriz);
	}

	/**
	 * Aplica el procedimiento para el calculo del determinante de una matriz cuadrada
	 * @param matriz matriz cuyo determinante quiere ser conocido
	 * @return el determinante de una matriz cuadrada
	 */	
	private static double procedimientoDeterminanteMatriz(double matriz[][]) throws Exception
	{
		double det=0;
		if (matriz.length==2)
			return matriz[0][0]*matriz[1][1]-matriz[0][1]*matriz[1][0];

		double c[]=new double[matriz.length];
		for (int i=0;i<c.length;i++)
			c[i]=Math.pow(-1,i)*procedimientoDeterminanteMatriz(getMatrizAuxiliarDeterminantes(matriz,i));
		for (int i=0;i<c.length;i++)
			det+=matriz[0][i]*c[i];
		return det;
	}
	
	/**
	 * Retorna la matriz auxiliar para el calculo de la determinante de una matriz
	 * @param matriz matriz cuyo determinante quiere ser conocido
	 * @param k posicion a partir de la cual se obtiene la matriz auxiliar
	 * @return la matriz auxiliar para el calculo de la determinante de una matriz
	 */
	private static double[][] getMatrizAuxiliarDeterminantes(double matriz[][],int k)
	{
		double ret[][]=new double[matriz.length-1][matriz.length-1];
		int cj=0;
		for (int i=1;i<matriz.length;i++)
		{
			cj=0;
			for (int j=0;j<matriz.length;j++)
			{
				if (j!=k)
				{
					ret[i-1][cj]=matriz[i][j];
					cj++;
				}
			}
		}
		return ret;
	}
	
	/**
	 * Carga las derivadas conocidas
	 */
	private static void cargarDerivadas() 
	{
		if (derivada[0]!=null) return;
		derivada[0]=new Derivada(1,1,1,0,new int[]{-1,1},-1.5,1,9,-1);
		derivada[1]=new Derivada(2,1,0.5,0,new int[]{-3,4,-1},0.333333333333,2,16,-1);
		derivada[2]=new Derivada(3,1,0.16666666667,0,new int[]{-11,18,-9,2},-0.25,3,21,-1);
		derivada[3]=new Derivada(4,1,0.833333333333333,0,new int[]{-25,48,-36,16,-3},0.2,4,21,-1);
		derivada[4]=new Derivada(5,1,0.5,1,new int[]{-1,0,1},-0.16666666667,2,12,-1);
		derivada[5]=new Derivada(6,1,0.16666666667,1,new int[]{-2,-3,6,-1},0.833333333333333,3,22,-1);
		derivada[6]=new Derivada(7,1,0.833333333333333,1,new int[]{-3,-10,18,-6,1},0.05,4,9,18);
		derivada[7]=new Derivada(8,1,0.833333333333333,2,new int[]{1,-8,0,8,-1},0.033333,4,12,18);
		derivada[8]=new Derivada(9,2,1,0,new int[]{1,-2,1},-1,1,9,-1);
		derivada[9]=new Derivada(10,2,1,0,new int[]{2,-5,4,-1},0.916666667,2,21,-1);
		derivada[10]=new Derivada(11,2,0.833333333333333,0,new int[]{35,-104,114,-56,11},-0.83333333,3,1,21);
		derivada[11]=new Derivada(12,2,1,1,new int[]{1,-2,1},-0.8333333333,2,22,-1);
		derivada[12]=new Derivada(13,2,1,1,new int[]{1,-2,1,0},-0.833333333,2,22,-1);
		derivada[13]=new Derivada(14,2,0.833333333333333,1,new int[]{11,-20,6,4,-1},0.833333333333333,2,1,22);
		derivada[14]=new Derivada(15,2,0.833333333333333,2,new int[]{-1,16,-30,16,-1},0.01111111111,4,1,23);
		derivada[15]=new Derivada(16,3,1,0,new int[]{-1,3,-3,1},-1.5,1,21,-1);
		derivada[16]=new Derivada(17,3,0.5,0,new int[]{-5,18,-24,14,-3},1.75,2,1,21);
		derivada[17]=new Derivada(18,3,1,1,new int[]{-1,3,-3,1},-0.5,1,22,-1);
		derivada[18]=new Derivada(19,3,0.5,1,new int[]{-3,10,-12,6,-1},0.25,2,1,22);
		derivada[19]=new Derivada(20,3,0.5,2,new int[]{-1,2,0,-2,1},-0.25,2,1,23);
		derivada[20]=new Derivada(21,4,1,0,new int[]{1,-4,6,-4,1},-2,1,1,21);
		derivada[21]=new Derivada(22,4,1,1,new int[]{1,-4,6,-4,1},-1,1,1,22);
		derivada[22]=new Derivada(23,4,1,2,new int[]{1,-4,6,-4,1},-0.16666666667,2,1,23);
	}
	
	public static boolean esNatural(int numero)
	{
		if (numero<0) return false;
		return true;
	}
	
	public static int cantidadDecimales(double num)
	{
		double ret=0;
		String aux=""+num;
		try
		{
			if (GestionString.contieneString(aux,"E")) return 0;
		String dec="";
		String a="";
		int i;
		for (i=0;i<aux.length()&&aux.charAt(i)!='.';i++)
			a+=""+aux.charAt(i);
		if (aux.charAt(i)!='.') return 0;
		for (i=i+1;i<aux.length();i++)
			dec+=""+aux.charAt(i);
		return dec.length();
		}catch (Exception qq)
		{
			return 0;	
		}
	}
	
  	public static double redondear(double val,int places) 
  	{
		long factor = (long)Math.pow(10,places);

		// Shift the decimal the correct number of places
		// to the right.
		val = val * factor;

		// Round to the nearest integer.
		long tmp = Math.round(val);

		// Shift the decimal the correct number of places
		// back to the left.
		return (double)tmp / factor;
    }	
	
	public static double truncar(double numero,int c)
	{
		if (c<0) return numero;
		double ret=0;
		String aux=""+numero;
		try
		{
			if (GestionString.contieneString(aux,"E")) return numero;
		String dec="";
		String a="";
		int i;
		for (i=0;i<aux.length()&&aux.charAt(i)!='.';i++)
			a+=""+aux.charAt(i);
		if (aux.charAt(i)!='.') return numero;
		for (i=i+1;i<aux.length();i++)
			dec+=""+aux.charAt(i);
		if (dec.length()<=c) return numero;
		return Double.parseDouble(a+"."+dec.substring(0,c));
		}catch (Exception qq)
		{
			return numero;	
		}
		//return numero;
	}
	
	public static double soloDecimal(double numero)
	{
		int i;
		String aux=""+numero;
		String a="";
		for (i=0;i<aux.length()&&aux.charAt(i)!='.';i++);
		for (i=i;i<aux.length();i++)
		a+=""+aux.charAt(i);
		//System.out.println(a);
		return Double.parseDouble(a);
	}
	
	/**
	 * Retorna un vector con los valores de abscisas que se obtienen en base a los parametro recibidos
	 * @param limiteInferior limiteInferior del intervalo de abscisa
	 * @param limiteSuperio limiteSuperior del intervalo de abscisa
	 * @param cantidadDivisiones cantidad de divisiones que se efectuan sobre el eje de abscisas
	 * @return vector con los valores de abscisas que se obtienen en base a los parametro recibidos
	 */
	public static double[] getX(double limiteInferior,double limiteSuperior,int cantidadDivisiones) throws Exception
	{
		double x[]=new double[cantidadDivisiones+1];
		double aux=(limiteSuperior-limiteInferior)/cantidadDivisiones;
		int i;
		x[0]=limiteInferior;
		for (i=1;i<x.length-1;i++)
			x[i]=limiteInferior+i*aux;
		x[x.length-1]=limiteSuperior;
		return x;
	}	
}