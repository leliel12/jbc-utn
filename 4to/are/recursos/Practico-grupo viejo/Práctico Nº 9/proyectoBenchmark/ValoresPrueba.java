/*
 * ValoresPrueba.java
 *
 * Created on 15 de julio de 2006, 11:35 AM
 */

/**
 *
 * @author  pas
 */

import java.io.*;

public class ValoresPrueba
{
	public static String EXTENSION=".DAT";
	public static String NOMBRE_A="TP91";
	public static String NOMBRE_B="TP92";

	public static int ESTRUCTURA_ARCHIVOS=0;

	public static String ESTRUCTURAS[]=new String []{"FAT32","NTFS","Ext2","VFAT"};

	public static String EXTENSION_ARCHIVO_BENCHMARK=".BEN";
	public static String EXTENSION_ARCHIVO="BEN";

	public static long LONGITUD_REG_A=512;
	public static long LONGITUD_REG_B=4096;
	public static long CANTIDAD_REG_A_1=512;
	public static long CANTIDAD_REG_B_1=4096;
	public static long CANTIDAD_REG_A_2=512;
	public static long CANTIDAD_REG_B_2=4096;
	public static long CANTIDAD_REG_A_3=512;
	public static long CANTIDAD_REG_B_3=4096;
	public static long CANTIDAD_REG_A_4=512;
	public static long CANTIDAD_REG_B_4=4096;
	public static long CANTIDAD_REG_A_5=512;
	public static long CANTIDAD_REG_B_5=4096;

	private static File archivoA,archivoB;

	private static boolean archivosCreados=false;

	public static void setearEstructuraArchivos(String so)
	{
		if (so.toLowerCase().equals("linux"))
			ESTRUCTURA_ARCHIVOS=2;
		else
			ESTRUCTURA_ARCHIVOS=0;
	}

	public static boolean crearArchivos()
	{
		try
		{
			archivoA=new File(NOMBRE_A);
			archivoB=new File(NOMBRE_B);
			archivosCreados=true;
		}
		catch (Exception qq)
		{
			archivoA=null;
			archivoB=null;
			archivosCreados=false;
		}
		return archivosCreados;
	}

	public static boolean eliminarArchivos()
	{
		try
		{
			archivoA.delete();
			archivoB.delete();
			return true;
		} catch (Exception qq)
		{
			return false;
		}
	}

	public static boolean archivosCreados()
	{
		return archivosCreados;
	}

	public static File getArchivoA()
	{
		return archivoA;
	}

	public static File getArchivoB()
	{
		return archivoB;
	}
}