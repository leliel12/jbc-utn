/*
 * Benchmark.java
 *
 * Created on 16 de julio de 2006, 11:43 AM
 */

/**
 *
 * @author  pas
 */

import java.io.*;
import java.util.*;

public class Benchmark implements Serializable,Comparable
{
	private String nombreSO,arquitecturaSO,versionSO,usuarioSO,estructuraArchivos;		
	private Date creacion;
	private Prueba pruebas[];

	public Benchmark()
	{
		setCreacion(new Date());
		setNombreSO("");
		setArquitecturaSO("");
		setVersionSO("");
		setUsuarioSO("");
		setEstructuraArchivos("");
		crearPruebas();
	}

	private void crearPruebas()
	{
		pruebas=new Prueba[5];
		for (int i=0;i<pruebas.length;i++)
			pruebas[i]=new Prueba(i+1);
	}

	public void setCreacion(Date creacion)
	{
		this.creacion=creacion;
	}

	public void setNombreSO(String nombreSO)
	{
		this.nombreSO=nombreSO;
	}

	public void setArquitecturaSO(String arquitecturaSO)
	{
		this.arquitecturaSO=arquitecturaSO;
	}

	public void setVersionSO(String versionSO)
	{
		this.versionSO=versionSO;
	}

	public Date getCreacion()
	{
		return creacion;
	}

	public void setUsuarioSO(String usuarioSO)
	{
		this.usuarioSO=usuarioSO;
	}

	public void setEstructuraArchivos(String estructuraArchivos)
	{
		this.estructuraArchivos=estructuraArchivos;
	}

	public String getNombreSO()
	{
		return nombreSO;
	}

	public String getVersionSO()
	{
		return versionSO;
	}

	public String getArquitecturaSO()
	{
		return arquitecturaSO;
	}

	public String getUsuarioSO()
	{
		return usuarioSO;
	}

	public String getNombreArchivo()
	{
		return usuarioSO+versionSO;
	}

	public String getEstructuraArchivos()
	{
		return estructuraArchivos;
	}

	public Prueba getPrueba(int numero)
	{
		if (numero<1||numero>pruebas.length) return null;
		return pruebas[numero-1];
	}

	public boolean setPrueba(Prueba prueba)
	{
		if (prueba.getNumero()<1||prueba.getNumero()>pruebas.length) return false;
		pruebas[prueba.getNumero()-1]=prueba;
		return true;
	}

	public String getIdentificador()
	{
		return "nombre SO: "+nombreSO+"\n"+"arquitectura SO: "+arquitecturaSO+"\n"+"version SO: "+versionSO+"\n"+"usuario SO: "+usuarioSO+"\n"+"estructura archivos: "+estructuraArchivos;		
	}

	public String toString()
	{
		String aux="\n"+"Benchmark: "+"\n"+getIdentificador()+"\n"+"creado: "+creacion.toString()+"\n\n"+"Pruebas: "+"\n";
		for (int i=0;i<pruebas.length;i++)
			aux+="\n"+pruebas[i].toString()+"\n";
		return aux;
	}

	public int compareTo(Object o)
	{
		if (!(o instanceof Benchmark)) return -1;
		Benchmark u=(Benchmark)o;
		return this.getIdentificador().compareTo(u.getIdentificador());
	}
}