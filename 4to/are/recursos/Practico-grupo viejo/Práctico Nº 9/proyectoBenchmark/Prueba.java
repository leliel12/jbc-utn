/*
 * Prueba.java
 *
 * Created on 16 de julio de 2006, 11:19 AM
 */

/**
 *
 * @author  pas
 */

import java.util.*;
import java.io.*;

public class Prueba implements Serializable,Comparable,Runnable
{
	private int numero;
	private Date inicioA,finA,inicioB,finB;
	private long longRegA,longRegB,cantRegA,cantRegB;
	private boolean ejecutada;

	private transient Thread hilo;
	private transient File archivo;
	private transient long cantidad,longitud;
	private transient Date inicio,fin;
	private transient GestorBenchmark gestor;

	public Prueba(int numero)
	{
		setNumero(numero);
		setEjecutada(false);
		setInicioA(null);
		setFinA(null);
		setInicioB(null);
		setFinB(null);
		setLongRegA(0);
		setCantRegA(0);
		setLongRegB(0);
		setCantRegB(0);
		hilo=new Thread(this);
	}

	public void setNumero(int numero)
	{
		this.numero=numero;
	}

	public void setInicioA(Date inicioA)
	{
		this.inicioA=inicioA;
	}

	public void setFinA(Date finA)
	{
		this.finA=finA;
	}

	public void setInicioB(Date inicioB)
	{
		this.inicioB=inicioB;
	}

	public void setFinB(Date finB)
	{
		this.finB=finB;
	}

	public void setLongRegA(long longRegA)
	{
		this.longRegA=longRegA;
	}

	public void setCantRegA(long cantRegA)
	{
		this.cantRegA=cantRegA;
	}

	public void setLongRegB(long longRegB)
	{
		this.longRegB=longRegB;
	}

	public void setCantRegB(long cantRegB)
	{
		this.cantRegB=cantRegB;
	}

	public void setEjecutada(boolean ejecutada)
	{
		this.ejecutada=ejecutada;
	}

	public int getNumero()
	{
		return numero;
	}

	public Date getInicioA()
	{
		return inicioA;
	}

	public Date getFinA()
	{
		return finA;
	}

	public Date getInicioB()
	{
		return inicioB;
	}

	public Date getFinB()
	{
		return finB;
	}

	public long getLongRegA()
	{
		return longRegA;
	}

	public long getCantRegA()
	{
		return cantRegA;
	}

	public long getLongRegB()
	{
		return longRegB;
	}

	public long getCantRegB()
	{
		return cantRegB;
	}

	public long getTotalRegA()
	{
		return cantRegA*longRegA;
	}

	public long getTotalRegB()
	{
		return cantRegB*longRegB;
	}

	public String getHoraInicioA()
	{
		return getFormatoHora(inicioA);
	}

	public double getRegMsA()
	{
		return (double)((double)getTiempoTotalA()/(double)(getTotalRegA()));
	}

	public double getRegMsB()
	{
		return (double)((double)getTiempoTotalB()/(double)(getTotalRegB()));
	}

	public String getHoraFinA()
	{
		return getFormatoHora(finA);
	}

	public long getTiempoTotalA()
	{
		return finA.getTime()-inicioA.getTime ();
	}

	public long getTiempoTotalB()
	{
		return finB.getTime()-inicioB.getTime ();
	}

	public String getTiempoTotalHoraA()
	{
		return getDetalleHora(finA.getTime()-inicioA.getTime ());
	}

	public String getTiempoTotalHoraB()
	{
		return getDetalleHora(finB.getTime()-inicioB.getTime ());
	}

	public long getValorInicioA()
	{
		return inicioA.getTime ();
	}

	public long getValorFinA()
	{
		return finA.getTime ();
	}

	public long getValorInicioB()
	{
		return inicioB.getTime ();
	}

	public long getValorFinB()
	{
		return finB.getTime ();
	}

	public String getHoraInicioB()
	{
		return getFormatoHora(inicioB);
	}

	public String getHoraFinB()
	{
		return getFormatoHora(finB);
	}

	private String getFormatoHora(Date date)
	{
		return date.toString();
	}

	private String getDetalleHora(long milis)
	{
		long segundos=(int)(milis/1000);
		long ms=milis%1000;
		long minutos=segundos/60;
		segundos=segundos%60;
		long horas=minutos/60;
		minutos=minutos%60;

		return ""+horas+" h "+" "+minutos+" m "+segundos+" s "+ms+" ms";
	}

	public boolean ejecutada()
	{
		return ejecutada;
	}

	public String getDescripcion()
	{
		return 
		"\n"+
		"\t\t"+"Archivo A"+"\n\n"+
		"\t"+"Longitud de registros: "+getLongRegA()+"\n"+
		"\t"+"Cantidad de registros: "+getCantRegA()+"\n"+
		"\t"+"Totalidad de registros: "+getTotalRegA()+"\n"+
		"\t"+"Hora inicio: "+getHoraInicioA()+"\n"+	
		"\t"+"Hora fin: "+getHoraFinA()+"\n"+
		"\t"+"Tiempo total: "+getTiempoTotalHoraA()+"\n"+
		"\t"+"Cantidad total milisegundos: "+getTiempoTotalA()+	
		"\n\n"+
		"\t\t"+"Archivo B"+
		"\n\n"+
		"\t"+"Longitud de registros: "+getLongRegB()+"\n"+
		"\t"+"Cantidad de registros: "+getCantRegB()+"\n"+
		"\t"+"Totalidad de registros: "+getTotalRegB()+"\n"+
		"\t"+"Hora inicio: "+getHoraInicioB()+"\n"+	
		"\t"+"Hora fin: "+getHoraFinB()+"\n"+
		"\t"+"Tiempo total: "+getTiempoTotalHoraB()+"\n"+
		"\t"+"Cantidad total milisegundos: "+getTiempoTotalB()+	
		"\t\t";
	}

	public String toString()
	{
		String aux="Prueba: "+numero+" ejecutada: "+ejecutada;
		if (ejecutada)
			aux+="\n"+getDescripcion();
		return aux;
	}

	public int compareTo(Object o)
	{
		if (!(o instanceof Prueba)) return -1;
		Prueba u=(Prueba)o;
		return this.numero-u.numero;
	}

	public void ejecutar(GestorBenchmark gestor)
	{
		this.gestor=gestor;
		hilo.start();
	}

	public void run()
	{
		try
		{
			ejecutarA();
			ejecutarB();
			ejecutada=true;
			gestor.pruebaFinalizada();
		} catch (Exception qq)
		{
			ejecutada=false;
			gestor.errorPrueba(qq);
		}
	}

	private void ejecutarA() throws Exception
	{
		setLongRegA(ValoresPrueba.LONGITUD_REG_A);
		switch (numero)
		{
			case 1:setCantRegA(ValoresPrueba.CANTIDAD_REG_A_1);break;
			case 2:setCantRegA(ValoresPrueba.CANTIDAD_REG_A_2);break;
			case 3:setCantRegA(ValoresPrueba.CANTIDAD_REG_A_3);break;
			case 4:setCantRegA(ValoresPrueba.CANTIDAD_REG_A_4);break;
			case 5:setCantRegA(ValoresPrueba.CANTIDAD_REG_A_5);break;
		}
		ejecutarOperacion(ValoresPrueba.getArchivoA(),longRegA,cantRegA);
		inicioA=inicio;
		finA=fin;
	}

	private void ejecutarB() throws Exception
	{
		setLongRegB(ValoresPrueba.LONGITUD_REG_B);
		switch (numero)
		{
			case 1:setCantRegB(ValoresPrueba.CANTIDAD_REG_B_1);break;
			case 2:setCantRegB(ValoresPrueba.CANTIDAD_REG_B_2);break;
			case 3:setCantRegB(ValoresPrueba.CANTIDAD_REG_B_3);break;
			case 4:setCantRegB(ValoresPrueba.CANTIDAD_REG_B_4);break;
			case 5:setCantRegB(ValoresPrueba.CANTIDAD_REG_B_5);break;
		}
		ejecutarOperacion(ValoresPrueba.getArchivoB(),longRegB,cantRegB);
		inicioB=inicio;
		finB=fin;
	} 

	private void ejecutarOperacion(File archivo,long longReg,long cantReg) throws Exception
	{
		this.cantidad=cantReg;
		this.longitud=longReg;
		this.archivo=archivo;
		switch (numero)
		{
			case 1:grabarSecuencialmente();break;
			//case 2:grabarAleatoriamente();break;
			case 2:leerSecuencialmente();break;
			case 3:leerYRegrabarSecuencialmente();break;
			case 4:leerAleatoriamente();break;
			case 5:leerYRegrabarAleatoriamente();break;
		}
	}

	private void grabarSecuencialmente() throws Exception
	{
    	inicio=new Date();

       	BufferedWriter out = new
        BufferedWriter(new FileWriter(archivo.getName(),false));
        for(int i=0;i<cantidad;i++)
	       	for(int j=0;j<longitud;j++)
    	        out.write("a");
        out.close();
		fin=new Date();
	}

	private void leerSecuencialmente() throws Exception
	{
    	inicio=new Date();
      	BufferedReader in = new BufferedReader(new FileReader(archivo.getName()));
      	String str;
       	while ((str = in.readLine()) != null);
       	in.close();
		fin=new Date();
	}
	
	private void leerYRegrabarSecuencialmente() throws Exception
	{
		inicio=new Date();

		BufferedReader in = new BufferedReader(new FileReader(archivo.getName()));
        String str;
        while ((str = in.readLine()) != null);
        in.close();

       	BufferedWriter out = new
        BufferedWriter(new FileWriter(archivo.getName(),false));
        for(int i=0;i<cantidad;i++)
          	for(int j=0;j<longitud;j++)
                out.write("a");
        out.close();
		fin=new Date();
	}

	private void grabarAleatoriamente() throws Exception
	{
    	inicio=new Date();
       	for(int i=0;i<(cantidad/2);i++)
        {
        	RandomAccessFile raf = new RandomAccessFile(archivo, "rw");
        	raf.seek(archivo.length());
          		for(int k=0;k<longitud;k++)
               		raf.writeChars("q");
        	raf.close();
		}
		fin=new Date();
	}

	private void leerAleatoriamente() throws Exception
	{
      	inicio=new Date();
       	for(int i=0;i<cantidad;i++)
       	{
        	String str;
        	RandomAccessFile raf = new RandomAccessFile(archivo, "rw");
          	raf.seek(archivo.length());
           	for(int k=0;k<longitud;k++)
            	str=raf.readLine();
           	raf.close();
	    }
		fin=new Date();
	}
	
	private void leerYRegrabarAleatoriamente() throws Exception
	{
      	inicio=new Date();
       	for(int i=0;i<cantidad;i++)
      	{
       		String str;
       		RandomAccessFile raf = new RandomAccessFile(archivo, "rw");
           		raf.seek(archivo.length());
           	for(int k=0;k<longitud;k++)
            	str=raf.readLine();
           	raf.close();
	    }

       	for(int i=0;i<(cantidad/2);i++)
        {
        	RandomAccessFile raf = new RandomAccessFile(archivo, "rw");
        	raf.seek(archivo.length());
        	for(int k=0;k<longitud;k++)
            	raf.writeChars("l");
           	raf.close();
        }
		fin=new Date();
	}
}