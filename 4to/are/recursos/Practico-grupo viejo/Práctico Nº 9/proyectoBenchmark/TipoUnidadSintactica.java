import java.io.*;

public class TipoUnidadSintactica implements Comparable,Serializable
{
	private String nombre;
	private String valores[];
	private boolean multiple;
	
	public TipoUnidadSintactica(String nombre,String valores[],boolean multiple)
	{
		setNombre(nombre);
		setValores(valores);
		setMultiple(multiple);
	}
	
	public String getNombre()
	{
		return nombre;
	}
	
	public String[] getValores()
	{
		return valores;
	}
	
	public boolean getMultiple()
	{
		return multiple;
	}
	
	public boolean esMultiple()
	{
		return multiple;
	}
	
	public void setNombre(String nombre)
	{
		this.nombre=nombre;
	}
	
	public void setValores(String valores[])
	{
		this.valores=valores;
	}
	
	public void setMultiple(boolean multiple)
	{
		this.multiple=multiple;
	}
	
	public boolean pertenece(String expresion)
	{
		int i;
		for (i=0;i<valores.length&&!expresion.equals(valores[i]);i++);
		return i!=valores.length;
	}
	
	public int compareTo(Object o)
	{
		if (o.getClass()!=TipoUnidadSintactica.class) return -1;
		TipoUnidadSintactica u=(TipoUnidadSintactica)o;
		return this.nombre.compareTo(u.nombre);
	}	
}