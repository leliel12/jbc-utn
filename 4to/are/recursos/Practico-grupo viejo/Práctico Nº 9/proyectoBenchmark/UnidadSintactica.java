import java.io.*;

public class UnidadSintactica implements Comparable,Serializable
{
	private String codigo;
	private String expresion;
	private int tipo;
	
	public UnidadSintactica(String expresion,int tipo)
	{
		this ("1",expresion,tipo);
	}
	
	public UnidadSintactica(String codigo,String expresion,int tipo)
	{
		setCodigo(codigo);
		setExpresion(expresion);
		setTipo(tipo);
	}
	
	public UnidadSintactica(UnidadSintactica unidad)
	{
		setCodigo(unidad.getCodigo());
		setExpresion(unidad.getExpresion());
		setTipo(unidad.getTipo());
	}
	
	public String getCodigo()
	{
		return codigo;
	}
	
	public String getExpresion()
	{
		return expresion;
	}
	
	public int getTipo()
	{
		return tipo;
	}
	
	public void setCodigo(String codigo)
	{
		this.codigo=codigo;
	}
	
	public void setExpresion(String expresion)
	{
		this.expresion=expresion;
	}
	
	public void setTipo(int tipo)
	{
		this.tipo=tipo;
	}
	
	public static String getStringUnidadSintacticas(UnidadSintactica unidades[])
	{
		String aux="";
		if (unidades!=null)
			for (int i=0;i<unidades.length;i++)
				aux+=unidades[i].getExpresion()+" ";
		return aux;
	}
	
	public static UnidadSintactica[] getUnidades(UnidadSintactica unidades[],int inicio,int fin)
	{
		if (fin<=inicio) return null;
		UnidadSintactica aux[]=new UnidadSintactica[fin-inicio-1];
		int c=0;
		for (int i=inicio+1;c<aux.length;i++,c++)
		{
			aux[c]=unidades[i];
		}
		return aux;
	}
	
	public int compareTo(Object o)
	{
		if (o.getClass()!=UnidadSintactica.class) return -1;
		UnidadSintactica u=(UnidadSintactica)o;
		return this.codigo.compareTo(u.codigo);
	}		
}