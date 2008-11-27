public interface Valores
{
	public static final String DELIMITADOR_INICIO="(";
	public static final String DELIMITADOR_FINAL=")";
	public static final String SUMA="+";
	public static final String RESTA="-";
	
	public static final String NEGATIVO="$";
	
	public static final String FACTORIAL="!";
	public static final String LOGARITMO="L";
	public static final String POTENCIA="p";
	public static final String RAIZ="r";
	
	public static final char NUMERO_NEPERIANO='e';
	public static final char PI='f';
	
	public static final int FINAL_EXPRESION=-3;
	public static final int NULL=-2;
	public static final int SIN_ERROR=-1;
	
	public static final int FUNCION=0;
	public static final int OPERACION=1;
	public static final int NUMERICO=2;
	public static final int DELIMITADOR=3;
	public static final int CARACTER=4;
	public static final int VARIABLE=5;
	public static final int CONSTANTE=6;
	public static final int OTRO=7;	
	public static final int MARCA=8;
}