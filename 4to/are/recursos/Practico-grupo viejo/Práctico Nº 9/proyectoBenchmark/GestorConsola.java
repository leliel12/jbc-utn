/*
 * GestorConsola.java
 *
 * Created on 17 de julio de 2006, 04:28 PM
 */

/**
 *
 * @author  pas
 */
import java.io.*;

public class GestorConsola
{
	private static String nombreArchivo="init.SOM";

	private VentanaConsola ventana;

	private Benchmark benchmarks[];
	private String archivos[];

	private String label[];
	private double valores[];
	String titulo,titulos[];

	public GestorConsola(VentanaConsola ventana)
	{
		this.ventana=ventana;
		crearObjetos();
		cargarBenchmarks();
	}

	private void crearObjetos()
	{
		benchmarks=new Benchmark[0];
		archivos=new String[0];
	}

	private void quitar(int pos)
	{
		Benchmark bAux[]=benchmarks;
		String aAux[]=archivos;
		benchmarks=new Benchmark[bAux.length-1];
		archivos=new String[aAux.length-1];
		int j=0;
		for (int i=0;i<bAux.length;i++)
		{
			if (i!=j)
			{
				benchmarks[j]=bAux[i];
				archivos[j]=aAux[i];
				j++;
			}
		}
	}

	public void benchmarkSeleccionado(int pos)
	{
		ventana.mostrarBenchmark(benchmarks[pos],archivos[pos]);
	}
	
	public void quitarBenchmark(int pos)
	{
		String path=archivos[pos];
		ventana.mostrarAccion(archivos[pos]+" removido");
		quitar(pos);
		ventana.quitarBenchmark(path,pos);
	}

	public void agregarBenchmark()
	{
		File files[]=GestionArchivo.seleccionarArchivos(new String[]{ValoresPrueba.EXTENSION_ARCHIVO},"recursos/tests");
		if (files==null||files.length==0) 
		{
			ventana.mostrarAccion("No se seleccionaron benchmarks");
			return;
		}
		for (int i=0;i<files.length;i++)
			agregarBenchmark(files[i]);
	}

	private void cargarBenchmarks()
	{
		try
		{
			String aux[]=(String[])(GestionArchivo.cargarObjectArchivo("recursos/archivos/"+nombreArchivo));
			for (int i=0;i<aux.length;i++)
				agregarBenchmark(new File(aux[i]));
		} catch(Exception qq){}
	}

	public void finalizar()
	{
		try
		{
			String actual=GestionArchivo.getDirectorioActual();
			for (int i=0;i<archivos.length;i++)
				if (GestionString.contieneStringComienzo(archivos[i],actual))
					archivos[i]=archivos[i].substring(actual.length(),archivos[i].length());
			GestionArchivo.grabarObjectArchivo(archivos,"recursos/archivos/"+nombreArchivo);
		} catch (Exception qq) {}
	}

	private void agregarBenchmark(File file)
	{
		try
		{
			if (!file.exists())
			{
				ventana.mostrarAccion("Error: Archivo "+file.getAbsolutePath()+" no existente");
				return;
			}
			Benchmark benchmark=(Benchmark)(GestionArchivo.cargarObjectArchivo(file.getAbsolutePath()));
			if (existeBenchmark(file.getAbsolutePath()))
			{
				ventana.mostrarAccion("Error: Archivo "+file.getAbsolutePath()+" ya ha estaba cargado");
				return;
			}
			agregar(benchmark,file.getAbsolutePath());
			ventana.agregarBenchmark(benchmark,file.getName(),file.getAbsolutePath());
			ventana.mostrarAccion("Archivo "+file.getAbsolutePath()+" cargado");
		}
		catch (Exception qq)
		{
			ventana.mostrarAccion("Error al cargar "+file.getAbsolutePath());
		}
	}

	private boolean existeBenchmark(String path)
	{
		for (int i=0;i<archivos.length;i++)
			if (archivos[i].equals(path))
				return true;
		return false;
	}

	private void agregar(Benchmark benchmark,String path)
	{
		Benchmark bAux[]=benchmarks;
		String aAux[]=archivos;
		benchmarks=new Benchmark[bAux.length+1];
		archivos=new String[aAux.length+1];
		for (int i=0;i<bAux.length;i++)
		{
			benchmarks[i]=bAux[i];
			archivos[i]=aAux[i];
		}
		benchmarks[bAux.length]=benchmark;
		archivos[bAux.length]=path;
	}

	public void graficarBarrasPrueba(String tipo,int pos)
	{
		if (!cargarValoresBarrasPrueba(tipo,pos))
		{
			ventana.mostrarMensaje("No hay pruebas cargadas");
			return;
		}
		graficarBarras();
	}

	public void graficarTortaPrueba(String tipo,int pos)
	{
		if (!cargarValoresTortaPrueba(tipo,pos))
		{
			ventana.mostrarMensaje("No hay pruebas cargadas");
			return;
		}
		graficarTorta();
	}

	private void graficarTorta()
	{
		int v[]=GestionGraficos.getValoresTorta(valores);
		GestionGraficos.mostrarVentanaGraficoTorta(v,label,titulo);		
		resetear();		    			
	}

	private boolean cargarValoresTortaPrueba(String tipo,int pos)
	{
		if (tipo.equals("tiempo total")) return cargarTiempoTotal(pos);
		if (tipo.equals("tiempo total A")) return cargarTiempoTotalA(pos);
		if (tipo.equals("tiempo total B")) return cargarTiempoTotalB(pos);
		return false;
	}

	private boolean cargarTiempoTotal(int pos)
	{
		int c=cantidadPruebas(pos);
		if (c==0) return false;

		label=new String[c];
		valores=new double[label.length];

		int j=0;
		Prueba p=null;
		Benchmark b=null;
		for (int i=0;i<benchmarks.length;i++)
		{
			b=benchmarks[i];
			p=b.getPrueba(pos);
			if (p.ejecutada())
			{
				label[j]=b.getUsuarioSO();
				valores[j]=p.getTiempoTotalA()+p.getTiempoTotalB();
				j++;
			}
		}

		titulo="Tiempo total de pruebas";
		return true;
	}

	private boolean cargarTiempoTotalA(int pos)
	{
		int c=cantidadPruebas(pos);
		if (c==0) return false;

		label=new String[c];
		valores=new double[label.length];

		int j=0;
		Prueba p=null;
		Benchmark b=null;
		for (int i=0;i<benchmarks.length;i++)
		{
			b=benchmarks[i];
			p=b.getPrueba(pos);
			if (p.ejecutada())
			{
				label[j]=b.getUsuarioSO();
				valores[j]=p.getTiempoTotalA();
				j++;
			}
		}

		titulo="Tiempo total de pruebas de archivo A";
		return true;
	}

	private boolean cargarTiempoTotalB(int pos)
	{
		int c=cantidadPruebas(pos);
		if (c==0) return false;

		label=new String[c];
		valores=new double[label.length];

		int j=0;
		Prueba p=null;
		Benchmark b=null;
		for (int i=0;i<benchmarks.length;i++)
		{
			b=benchmarks[i];
			p=b.getPrueba(pos);
			if (p.ejecutada())
			{
				label[j]=b.getUsuarioSO();
				valores[j]=p.getTiempoTotalB();
				j++;
			}
		}

		titulo="Tiempo total de pruebas de archivo B";
		return true;
	}

	private void graficarBarras()
	{
		String labelY[]=GestionGraficos.getLabelY(valores);
		int v[][]=GestionGraficos.getValoresBarras(valores);
		GestionGraficos.mostrarVentanaGraficoBarras(v,label,labelY,titulo,titulos);
		resetear();				    			
	}

	private boolean cargarValoresBarrasPrueba(String tipo,int pos)
	{
		if (tipo.equals("reg/ms"))	return cargarRegMs(pos);
		if (tipo.equals("reg/ms A")) return cargarRegMsA(pos);
		if (tipo.equals("reg/ms B")) return cargarRegMsB(pos);
		if (tipo.equals("cantidad reg")) return cargarCantidadReg(pos);
		if (tipo.equals("cantidad reg A")) return cargarCantidadRegA(pos);
		if (tipo.equals("cantidad reg B")) return cargarCantidadRegB(pos);
		if (tipo.equals("longitud reg")) return cargarLongitudReg(pos);
		if (tipo.equals("longitud reg A")) return cargarLongitudRegA(pos);
		if (tipo.equals("longitud reg B")) return cargarLongitudRegB(pos);
		if (tipo.equals("total reg")) return cargarTotalReg(pos);
		if (tipo.equals("total reg A")) return cargarTotalRegA(pos);
		if (tipo.equals("total reg B")) return cargarTotalRegB(pos);
		return false;
	}

	private int cantidadPruebas(int pos)
	{
		int c=0;
		for (int i=0;i<benchmarks.length;i++)
			if (benchmarks[i].getPrueba(pos).ejecutada())
				c++;
		return c;
	
	}

	private boolean cargarRegMs(int pos)
	{
		int c=cantidadPruebas(pos);
		if (c==0) return false;

		label=new String[c*2];
		valores=new double[label.length];

		int j=0;
		Prueba p=null;
		Benchmark b=null;
		for (int i=0;i<benchmarks.length;i++)
		{
			b=benchmarks[i];
			p=b.getPrueba(pos);
			if (p.ejecutada())
			{
				label[j*2]=b.getUsuarioSO()+" A";
				label[j*2+1]=b.getUsuarioSO()+" B";
				valores[j*2]=p.getRegMsA()*10000;
				valores[j*2+1]=p.getRegMsB()*10000;
				j++;
			}
		}

		titulo="Registros procesados por 10000 ms";
		titulos=new String[]{"Archivo","10000 reg/ms"};
		return true;
	}

	private boolean cargarRegMsA(int pos)
	{
		int c=cantidadPruebas(pos);
		if (c==0) return false;

		label=new String[c];
		valores=new double[label.length];

		int j=0;
		Prueba p=null;
		Benchmark b=null;
		for (int i=0;i<benchmarks.length;i++)
		{
			b=benchmarks[i];
			p=b.getPrueba(pos);
			if (p.ejecutada())
			{
				label[j]=b.getUsuarioSO()+" A";
				valores[j]=p.getRegMsA()*10000;
				j++;
			}
		}

		titulo="Registros procesados por 10000 ms en archivo A";
		titulos=new String[]{"Archivo","10000 reg/ms"};
		return true;
	}

	private boolean cargarRegMsB(int pos)
	{
		int c=cantidadPruebas(pos);
		if (c==0) return false;

		label=new String[c];
		valores=new double[label.length];

		int j=0;
		Prueba p=null;
		Benchmark b=null;
		for (int i=0;i<benchmarks.length;i++)
		{
			b=benchmarks[i];
			p=b.getPrueba(pos);
			if (p.ejecutada())
			{
				label[j]=b.getUsuarioSO()+" B";
				valores[j]=p.getRegMsB()*10000;
				j++;
			}
		}

		titulo="Registros procesados por 10000 ms en archivo B";
		titulos=new String[]{"Archivo","10000 reg/ms"};
		return true;
	}

	private boolean cargarCantidadReg(int pos)
	{
		int c=cantidadPruebas(pos);
		if (c==0) return false;

		label=new String[c*2];
		valores=new double[label.length];

		int j=0;
		Prueba p=null;
		Benchmark b=null;
		for (int i=0;i<benchmarks.length;i++)
		{
			b=benchmarks[i];
			p=b.getPrueba(pos);
			if (p.ejecutada())
			{
				label[j*2]=b.getUsuarioSO()+" A";
				label[j*2+1]=b.getUsuarioSO()+" B";
				valores[j*2]=p.getCantRegA();
				valores[j*2+1]=p.getCantRegB();
				j++;
			}
		}

		titulo="Cantidad de registros procesados";
		titulos=new String[]{"Archivo","Cantidad reg"};
		return true;
	}

	private boolean cargarCantidadRegA(int pos)
	{
		int c=cantidadPruebas(pos);
		if (c==0) return false;

		label=new String[c];
		valores=new double[label.length];

		int j=0;
		Prueba p=null;
		Benchmark b=null;
		for (int i=0;i<benchmarks.length;i++)
		{
			b=benchmarks[i];
			p=b.getPrueba(pos);
			if (p.ejecutada())
			{
				label[j]=b.getUsuarioSO()+" A";
				valores[j]=p.getCantRegA();
				j++;
			}
		}

		titulo="Cantidad de registros procesados en archivo A";
		titulos=new String[]{"Archivo","Cantidad reg"};
		return true;
	}

	private boolean cargarCantidadRegB(int pos)
	{
		int c=cantidadPruebas(pos);
		if (c==0) return false;

		label=new String[c];
		valores=new double[label.length];

		int j=0;
		Prueba p=null;
		Benchmark b=null;
		for (int i=0;i<benchmarks.length;i++)
		{
			b=benchmarks[i];
			p=b.getPrueba(pos);
			if (p.ejecutada())
			{
				label[j]=b.getUsuarioSO()+" B";
				valores[j]=p.getCantRegB();
				j++;
			}
		}

		titulo="Cantidad de registros procesados en archivo B";
		titulos=new String[]{"Archivo","Cantidad reg"};
		return true;
	}

	private boolean cargarLongitudReg(int pos)
	{
		int c=cantidadPruebas(pos);
		if (c==0) return false;

		label=new String[c*2];
		valores=new double[label.length];

		int j=0;
		Prueba p=null;
		Benchmark b=null;
		for (int i=0;i<benchmarks.length;i++)
		{
			b=benchmarks[i];
			p=b.getPrueba(pos);
			if (p.ejecutada())
			{
				label[j*2]=b.getUsuarioSO()+" A";
				label[j*2+1]=b.getUsuarioSO()+" B";
				valores[j*2]=p.getLongRegA();
				valores[j*2+1]=p.getLongRegB();
				j++;
			}
		}

		titulo="Longitud de registros procesados";
		titulos=new String[]{"Archivo","Longitud reg"};
		return true;
	}

	private boolean cargarLongitudRegA(int pos)
	{
		int c=cantidadPruebas(pos);
		if (c==0) return false;

		label=new String[c];
		valores=new double[label.length];

		int j=0;
		Prueba p=null;
		Benchmark b=null;
		for (int i=0;i<benchmarks.length;i++)
		{
			b=benchmarks[i];
			p=b.getPrueba(pos);
			if (p.ejecutada())
			{
				label[j]=b.getUsuarioSO()+" A";
				valores[j]=p.getLongRegA();
				j++;
			}
		}

		titulo="Longitud de registros procesados en archivo A";
		titulos=new String[]{"Archivo","Longitud reg"};
		return true;
	}

	private boolean cargarLongitudRegB(int pos)
	{
		int c=cantidadPruebas(pos);
		if (c==0) return false;

		label=new String[c];
		valores=new double[label.length];

		int j=0;
		Prueba p=null;
		Benchmark b=null;
		for (int i=0;i<benchmarks.length;i++)
		{
			b=benchmarks[i];
			p=b.getPrueba(pos);
			if (p.ejecutada())
			{
				label[j]=b.getUsuarioSO()+" B";
				valores[j]=p.getLongRegB();
				j++;
			}
		}

		titulo="Longitud de registros procesados en archivo B";
		titulos=new String[]{"Archivo","Longitud reg"};
		return true;
	}

	private boolean cargarTotalReg(int pos)
	{
		int c=cantidadPruebas(pos);
		if (c==0) return false;

		label=new String[c*2];
		valores=new double[label.length];

		int j=0;
		Prueba p=null;
		Benchmark b=null;
		for (int i=0;i<benchmarks.length;i++)
		{
			b=benchmarks[i];
			p=b.getPrueba(pos);
			if (p.ejecutada())
			{
				label[j*2]=b.getUsuarioSO()+" A";
				label[j*2+1]=b.getUsuarioSO()+" B";
				valores[j*2]=p.getTotalRegA();
				valores[j*2+1]=p.getTotalRegB();
				j++;
			}
		}

		titulo="Total de registros procesados";
		titulos=new String[]{"Archivo","Total reg"};
		return true;
	}

	private boolean cargarTotalRegA(int pos)
	{
		int c=cantidadPruebas(pos);
		if (c==0) return false;

		label=new String[c];
		valores=new double[label.length];

		int j=0;
		Prueba p=null;
		Benchmark b=null;
		for (int i=0;i<benchmarks.length;i++)
		{
			b=benchmarks[i];
			p=b.getPrueba(pos);
			if (p.ejecutada())
			{
				label[j]=b.getUsuarioSO()+" A";
				valores[j]=p.getTotalRegA();
				j++;
			}
		}

		titulo="Total de registros procesados en archivo A";
		titulos=new String[]{"Archivo","Total reg"};
		return true;
	}

	private boolean cargarTotalRegB(int pos)
	{
		int c=cantidadPruebas(pos);
		if (c==0) return false;

		label=new String[c];
		valores=new double[label.length];

		int j=0;
		Prueba p=null;
		Benchmark b=null;
		for (int i=0;i<benchmarks.length;i++)
		{
			b=benchmarks[i];
			p=b.getPrueba(pos);
			if (p.ejecutada())
			{
				label[j]=b.getUsuarioSO()+" B";
				valores[j]=p.getTotalRegB();
				j++;
			}
		}

		titulo="Total de registros procesados en archivo B";
		titulos=new String[]{"Archivo","Total reg"};
		return true;
	}

	public String getDetalleBenchmarks()
	{
		if (benchmarks.length==0)
			return null;

		String aux="\n";

		aux+="\t"+"Cantidad de benchmarks: "+benchmarks.length+"\n\n";
		aux+="\t\t"+"Archivos abiertos"+"\n";
		for (int i=0;i<benchmarks.length;i++)
			aux+="\t"+archivos[i]+"\n";
		aux+="\n\t"+"Cantidad de pruebas"+"\n";
		valores=contarPruebas();
		label=getUsuarioBenchmarks();
		for (int i=0;i<valores.length;i++)
			aux+="\t"+label[i]+": "+(Matematica.redondear(valores[i],0))+"\n";		
		label=getSO();	
		valores=contarSO(label);
		aux+="\n\t"+"Cantidad de benchmarks por sistema operativo"+"\n";
		for (int i=0;i<valores.length;i++)
			aux+="\t"+label[i]+": "+(Matematica.redondear(valores[i],0))+"\n";		
		label=getEstructuraArchivos();
		valores=contarEstructuraArchivos(label);
		aux+="\n\t"+"Cantidad de benchmarks por estructura de archivo"+"\n";
		for (int i=0;i<valores.length;i++)
			aux+="\t"+label[i]+": "+(Matematica.redondear(valores[i],0))+"\n";		
	
		return aux;
	}

	public String getDetallePrueba(int pos)
	{
		int c=cantidadPruebas(pos);
		if (c==0)
			return null;
		
		String aux="\n";

		double tTotal=0;
		double tTotalA=0;
		double tTotalB=0;
		double longPromA=0;
		double longPromB=0;
		double cantPromA=0;
		double cantPromB=0;
		double regMsPromA=0;
		double regMsPromB=0;

		Prueba p=null;
		Benchmark b=null;
		for (int i=0;i<benchmarks.length;i++)
		{
			b=benchmarks[i];
			p=b.getPrueba(pos);
			if (p.ejecutada())
			{
				tTotalA=p.getTiempoTotalA();
				tTotalB=p.getTiempoTotalB();
				tTotal+=tTotalA+tTotalB;
				longPromA+=p.getLongRegA();
				longPromB+=p.getLongRegB();
				cantPromA+=p.getCantRegA();
				cantPromB+=p.getCantRegA();	
				regMsPromA+=p.getRegMsA()*10000;
				regMsPromB+=p.getRegMsB()*10000;
			}
		}	
		aux+="\t"+"Cantidad de benchmarks: "+c+"\n"+	
		"\t"+"Tiempo total pruebas: "+tTotal+"\n"+	
		"\t"+"Tiempo total archivo A: "+tTotalA+"\n"+
		"\t"+"Tiempo total archivo B: "+tTotalB+"\n"+
		"\t"+"Longitud promedio de registro archivo A: "+Matematica.redondear((double)((double)longPromA/(double)c),4)+"\n"+
		"\t"+"Longitud promedio de registro archivo B: "+Matematica.redondear((double)((double)longPromB/(double)c),4)+"\n"+
		"\t"+"Cantidad promedio de registro archivo A: "+Matematica.redondear((double)((double)cantPromA/(double)c),4)+"\n"+
		"\t"+"Cantidad promedio de registro archivo B: "+Matematica.redondear((double)((double)cantPromB/(double)c),4)+"\n"+
		"\t"+"Promedio de reg/seg * 10000 de archivo A: "+Matematica.redondear((double)((double)regMsPromA/(double)c),4)+"\n"+
		"\t"+"Promedio de reg/seg * 10000 de archivo B: "+Matematica.redondear((double)((double)regMsPromB/(double)c),4)+"\n";

		return aux;
	}

	public void graficarBarrasInfo(String tipo)
	{
		if (benchmarks.length==0)
		{
			ventana.mostrarMensaje("No hay benchmarks cargados");
			return;
		}
		cargarValoresBarrasInfo(tipo);
		graficarBarras();
	}

	public void graficarTortaInfo(String tipo)
	{
		if (benchmarks.length==0)
		{
			ventana.mostrarMensaje("No hay benchmarks cargados");
			return;
		}
		cargarValoresTortaInfo(tipo);
		graficarTorta();
	}

	private void cargarValoresBarrasInfo(String tipo)
	{
		if (tipo.equals("sist. operativos"))
			cargarSistemasOperativos();
		else
		if (tipo.equals("estruct. de archivo")) 
			cargarEstructurasArchivo();
		if (tipo.equals("cantidad de pruebas")) 
			cargarCantidadPruebas();
	}

	private void cargarCantidadPruebas()
	{
		label=getUsuarioBenchmarks();	
		valores=contarPruebas();
		titulo="Cantidad de pruebas por benchmark";
		titulos=new String[]{"Benchmark","Cantidad de pruebas"};
	}

	private double[] contarPruebas()
	{
		double aux[]=new double[benchmarks.length];
		for (int i=0;i<aux.length;i++)
			aux[i]=getCantidadPruebas(benchmarks[i]);
		return aux;
	}

	private int getCantidadPruebas(Benchmark benchmark)
	{
		int c=0;
		for (int i=1;i<=5;i++)
			if (benchmark.getPrueba(i).ejecutada())
				c++;
		return c;
	}

	private String[] getUsuarioBenchmarks()
	{
		String aux[]=new String[benchmarks.length];
		for (int i=0;i<aux.length;i++)
			aux[i]=benchmarks[i].getUsuarioSO();
		return aux;
	}

	private void cargarValoresTortaInfo(String tipo)
	{
		if (tipo.equals("sist. operativos"))	
			cargarSistemasOperativos();
		else
		if (tipo.equals("estruct. de archivo")) 
			cargarEstructurasArchivo();
	}

	private void cargarSistemasOperativos()
	{
		label=getSO();	
		valores=contarSO(label);
		titulo="Sistemas operativos analizados por cantidad";
		titulos=new String[]{"Sistema operativo","Cantidad benchmarks"};
	}

	private double[] contarSO(String so[])
	{	
		double c[]=new double[so.length];
		for (int i=0;i<benchmarks.length;i++)
			for (int j=0;j<so.length;j++)
				if (so[j].equals(benchmarks[i].getNombreSO()))
				{
					c[j]++;
					break;
				}
		return c;
	}	

	private String[] getSO()
	{
		String aux[]=new String[benchmarks.length];
		for (int i=0;i<aux.length;i++)
			aux[i]=benchmarks[i].getNombreSO();
		return GestionString.getStringDiferentes(aux);
	}

	private void cargarEstructurasArchivo()
	{
		label=getEstructuraArchivos();
		valores=contarEstructuraArchivos(label);
		titulo="Estructuras de archivos analizadas por cantidad";
		titulos=new String[]{"Estructura de archivo","Cantidad benchmarks"};
	}

	private double[] contarEstructuraArchivos(String ea[])
	{	
		double c[]=new double[ea.length];
		for (int i=0;i<benchmarks.length;i++)
			for (int j=0;j<ea.length;j++)
				if (ea[j].equals(benchmarks[i].getEstructuraArchivos()))
				{
					c[j]++;
					break;
				}
		return c;
	}

	private String[] getEstructuraArchivos()
	{
		String aux[]=new String[benchmarks.length];
		for (int i=0;i<aux.length;i++)
			aux[i]=benchmarks[i].getEstructuraArchivos();
		return GestionString.getStringDiferentes(aux);
	}

	private void resetear()
	{
		label=null;
		titulo=null;
		titulos=null;
		valores=null;
	}
}