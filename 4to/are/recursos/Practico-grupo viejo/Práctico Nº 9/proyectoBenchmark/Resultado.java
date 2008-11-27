import java.io.*;

public class Resultado implements Comparable,Serializable
{
	private boolean resultado;
	private String descripcion;
	private int posicion;
	
	public Resultado(boolean resultado,String descripcion,int posicion)
	{
		setResultado(resultado);
		setDescripcion(descripcion);
		setPosicion(posicion);
	}
	
	public boolean getResultado()
	{
		return resultado;
	}
	
	public String getDescripcion()
	{
		return descripcion;
	}
	
	public int getPosicion()
	{
		return posicion;
	}

	public void setResultado(boolean resultado)
	{
		this.resultado=resultado;
	}
	
	public void setDescripcion(String descripcion)
	{
		this.descripcion=descripcion;
	}
	
	public void setPosicion(int posicion)
	{
		this.posicion=posicion;
	}
	
	public int compareTo(Object o)
	{
		if (o.getClass()!=Resultado.class) return -1;
		Resultado u=(Resultado)o;
		return this.descripcion.compareTo(u.descripcion);
	}			
}