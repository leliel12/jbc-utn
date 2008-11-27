/*
 * GestorBenchmark.java
 *
 * Created on 16 de julio de 2006, 12:46 AM
 */

/**
 *
 * @author  pas
 */

import java.io.*;
import java.util.*;

public class GestorBenchmark
{
	private Prueba prueba;
	private VentanaBenchmark ventana;
	private Benchmark benchmarkMemoria,benchmarkArchivo;

	public GestorBenchmark(VentanaBenchmark ventana)
	{
		this.ventana=ventana;
		crearBenchmarkMemoria();
		gestionarBenchmarkArchivo();		
	}

	private void gestionarBenchmarkArchivo()
	{
		try
		{
			benchmarkArchivo=(Benchmark)(GestionArchivo.cargarObjectArchivo("recursos/tests/"+benchmarkMemoria.getNombreArchivo()+ValoresPrueba.EXTENSION_ARCHIVO_BENCHMARK));
			ventana.mostrarAccion("Archivo "+benchmarkArchivo.getNombreArchivo()+" abierto");
		} catch (Exception qq)
		{
			crearBenchmarkArchivo();
			GestionArchivo.grabarObjectArchivo(benchmarkArchivo,"recursos/tests/"+benchmarkArchivo.getNombreArchivo()+ValoresPrueba.EXTENSION_ARCHIVO_BENCHMARK);
			ventana.mostrarAccion("Archivo "+benchmarkArchivo.getNombreArchivo()+" creado");
		}
		buscarEstructura(benchmarkArchivo.getEstructuraArchivos());
		benchmarkMemoria.setEstructuraArchivos(ValoresPrueba.ESTRUCTURAS[ValoresPrueba.ESTRUCTURA_ARCHIVOS]);
	}

	private void crearBenchmarkArchivo()
	{
		Properties propiedades=System.getProperties();		
		benchmarkArchivo=new Benchmark();
		benchmarkArchivo.setNombreSO(propiedades.getProperty("os.name"));
		benchmarkArchivo.setArquitecturaSO(propiedades.getProperty("os.arch"));
		benchmarkArchivo.setVersionSO(propiedades.getProperty("os.version"));
		benchmarkArchivo.setUsuarioSO(propiedades.getProperty("user.name"));
		ValoresPrueba.setearEstructuraArchivos(benchmarkArchivo.getNombreSO());
		benchmarkArchivo.setEstructuraArchivos(ValoresPrueba.ESTRUCTURAS[ValoresPrueba.ESTRUCTURA_ARCHIVOS]);
	}

	private void crearBenchmarkMemoria()
	{
		Properties propiedades=System.getProperties();		
		benchmarkMemoria=new Benchmark();
		benchmarkMemoria.setNombreSO(propiedades.getProperty("os.name"));
		benchmarkMemoria.setArquitecturaSO(propiedades.getProperty("os.arch"));
		benchmarkMemoria.setVersionSO(propiedades.getProperty("os.version"));
		benchmarkMemoria.setUsuarioSO(propiedades.getProperty("user.name"));
		benchmarkMemoria.setEstructuraArchivos(ValoresPrueba.ESTRUCTURAS[ValoresPrueba.ESTRUCTURA_ARCHIVOS]);
	}

	public String getEstructuraArchivos()
	{
		return benchmarkMemoria.getEstructuraArchivos();
	}

	public boolean actualizarEstructura(String estructura)
	{
		if (estructura.equals(ValoresPrueba.ESTRUCTURAS[ValoresPrueba.ESTRUCTURA_ARCHIVOS]))
			return false;
		buscarEstructura(estructura);
		benchmarkMemoria.setEstructuraArchivos(ValoresPrueba.ESTRUCTURAS[ValoresPrueba.ESTRUCTURA_ARCHIVOS]);
		benchmarkArchivo.setEstructuraArchivos(ValoresPrueba.ESTRUCTURAS[ValoresPrueba.ESTRUCTURA_ARCHIVOS]);
		return GestionArchivo.grabarObjectArchivo(benchmarkArchivo,"recursos/tests/"+benchmarkArchivo.getNombreArchivo()+ValoresPrueba.EXTENSION_ARCHIVO_BENCHMARK);
	}

	private void buscarEstructura(String estructura)
	{
		int i;
		for (i=0;i<ValoresPrueba.ESTRUCTURAS.length;i++)
			if (ValoresPrueba.ESTRUCTURAS[i].equals(estructura))
					break;
		ValoresPrueba.ESTRUCTURA_ARCHIVOS=i;
	}

	public void ejecutarPrueba(int numero)
	{
		prueba=new Prueba(numero);
		prueba.ejecutar(this);
	}

	public void pruebaFinalizada()
	{
		benchmarkMemoria.setPrueba(prueba);
		ventana.pruebaFinalizada(prueba);
	}

	public void errorPrueba(Exception qq)
	{
		ventana.errorPrueba(prueba,qq);
	}

	public boolean crearArchivos()
	{
		return ValoresPrueba.crearArchivos();
	}

	public void finalizar()
	{
		eliminarArchivos();
	}

	private void eliminarArchivos()
	{
		ValoresPrueba.eliminarArchivos();
	}

	public boolean pruebaEjecutada(int numero)
	{
		return benchmarkMemoria.getPrueba(numero).ejecutada();
	}

	public boolean grabacionEjecutada()
	{
		return pruebaEjecutada(1);
	}

	public Prueba getPrueba(int numero)
	{
		return benchmarkMemoria.getPrueba(numero);
	}

	public boolean guardar()
	{
		if (!GestionArchivo.grabarObjectArchivo(benchmarkMemoria,"recursos/tests/"+benchmarkMemoria.getNombreArchivo()+ValoresPrueba.EXTENSION_ARCHIVO_BENCHMARK)) return false;
		try
		{
			benchmarkArchivo=(Benchmark)(GestionArchivo.cargarObjectArchivo("recursos/tests/"+benchmarkMemoria.getNombreArchivo()+ValoresPrueba.EXTENSION_ARCHIVO_BENCHMARK));	
		} catch (Exception qq)
		{
			return false;
		}
		return true;
	}

	public boolean guardarPrueba(int numero)
	{
		if (!pruebaEjecutada(numero)) return false;
		benchmarkArchivo.setPrueba(benchmarkMemoria.getPrueba(numero));
		return GestionArchivo.grabarObjectArchivo(benchmarkArchivo,"recursos/tests/"+benchmarkArchivo.getNombreArchivo()+ValoresPrueba.EXTENSION_ARCHIVO_BENCHMARK);
	}

	public String mostrarBenchmarkArchivo()
	{
		return benchmarkArchivo.toString();
	}
}