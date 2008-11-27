

import java.io.*;

/**
 * Una clase para gestionar nombres y extensiones de archivos
 * @version Diciembre 2005
 */
public class GestionNotacionArchivo
{
	/**
	 * int que indica que se evalua el nombre del archivo sin considerar existencia de extension
	 */
	public static int EXTENSION_INDETERMINADA=0;
	
	/**
	 * int que indica que se analiza el nombre del archivo considerando la no existencia de una extension
	 */
	public static int SIN_EXTENSION=1;
	
	/**
	 * int que indica que se analiza el nombre del archivo considerando la existencia de una extension
	 */
	public static int CON_EXTENSION=2;
	
	/**
	 * indica si el string pasado por parametro puede ser extension de un archivo
	 * @param extension String que es analizado para determinar si puede ser extension de un archivo
	 * @return boolean que indica si el string puede ser extension de un archivo
	 */
	public static boolean esExtensionArchivo(String extension)
	{
		if (extension==null||extension.length()<2||extension.length()>5||extension.charAt(0)!='.') return false;
		return GestionString.esTodoAlfanumerico(extension.substring(1,extension.length()));
	}
	
	/**
	 * indica si el string pasado por parametro puede ser el nombre de una archivo
	 * considerando la obligatoriedad o no de una extension, dependiendo del parametro int tipo
	 * @param nombre String que es el nombre de archivo a analizar
	 * @param tipo int tipo que indica la obligatoriedad de considerar una extension en el nombre del archivo
	 * @return boolean indicando si el string nombre puede ser efectivamente el nombre de un archivo
	 */
	public static boolean esNombreArchivo(String nombre,int tipo)
	{
		if (tipo==EXTENSION_INDETERMINADA) return esNombreArchivo(nombre);
		if (tipo==CON_EXTENSION) return esNombreArchivoConExtension(nombre);
		if (tipo==SIN_EXTENSION&&!tieneExtensionNombreArchivo(nombre)) return esNombreArchivo(nombre);
		return false;
	}
	
	/**
	 * indica si el string pasado por parametro puede ser el nombre de una archivo	
	 * considerando que puede o no tener extension
	 * @param nombre String que es el nombre de archivo a analizar
	 * @return boolean indicando si el string nombre puede ser efectivamente el nombre de un archivo
	 */
	public static boolean esNombreArchivo(String nombre)
	{
		if (nombre==null) return false;
		if (tieneExtensionNombreArchivo(nombre))
		{
			if (nombre.charAt(0)=='.') return false;
			return (GestionString.esTodoAlfanumerico(nombre.substring(0,nombre.length()-getExtensionNombreArchivo(nombre).length())));
		}
		return GestionString.esTodoAlfanumerico(nombre);
	}

	/**
	 * indica si el string pasado por parametro puede ser el nombre de una archivo	
	 * considerando que debe tener extension
	 * @param nombre String que es el nombre de archivo a analizar
	 * @return boolean indicando si el string nombre puede ser efectivamente el nombre de un archivo
	 */	
	public static boolean esNombreArchivoConExtension(String nombre)
	{
		if (nombre==null||!tieneExtensionNombreArchivo(nombre)) return false;
		return esNombreArchivo(nombre);
	}
	
	/**
	 * retorna la extension de un archivo pasado por parametro
	 * @param archivo File de cuyo nombre se quiere conocer la extension
	 * @return String que es la extension del archivo
	 */
	public static String getExtensionNombreArchivo(File archivo)
	{
		if (archivo==null) return null;
		return getExtensionNombreArchivo(archivo.getName());
	}

	/**
	 * retorna la extension de un nombre de archivo pasado por parametro
	 * @param nombre String del cual se extraera la extension
	 * @return String que es la extension del nombre de archivo
	 */	
	public static String getExtensionNombreArchivo(String nombre)
	{

		if (nombre==null) return null;
		int i;
		for (i=nombre.length()-1;i>=0&&nombre.charAt(i)!='.';i--);
		if (i<=0) return "";
		String aux=nombre.substring(i,nombre.length());
		if (GestionString.esTodoAlfanumerico(aux.substring(1,aux.length()))) return aux;
		return null;
	}
	
	/**
	 * Indica si el archivo parametro tiene como extension la pasada por parametro
	 * @param archivo archivo cuya extension es verificada
	 * @param extension extension de verificacion
	 * @return boolean que indica si el archivo parametro tiene como extension la pasada por parametro
	 */
	public static boolean esExtensionDeArchivo(File archivo,String extension)
	{
		if (archivo==null||extension==null) return false;
		String aux=getExtensionNombreArchivo(archivo);
		if (aux==null) return false;
		return aux.equals(extension);
	}
	
	/**
	 * indica si el nombre de archivo pasado por parametro tiene una extension valida
	 * @param nombre String nombre cuya existencia de extension es evaluada
	 * @return boolean que indica si el nombre tiene extension valida
	 */
	public static boolean tieneExtensionNombreArchivo(String nombre)
	{
		if (nombre==null) return false;
		int i;
		for (i=0;i<nombre.length()&&nombre.charAt(i)!='.';i++);
		if (i==nombre.length()) return false;
		String aux=nombre.substring(i,nombre.length());
		return (GestionString.esTodoAlfanumerico(aux.substring(1,aux.length())));
	}	
	
	/**
	 * Indica si el nombre de archivo es limpio, solo compuesto de caracteres alfanumericos
	 * @param nombre nombre de archivo a analizar
	 * @return boolean que indica si el nombre de archivo es limpio, solo compuesto de caracteres alfanumericos
	 */
	public static boolean esNombreLimpio(String nombre)
	{
		if (nombre==null) return false;
		for (int i=0;i<nombre.length();i++)
			if (!Character.isLetterOrDigit(nombre.charAt(i))&&nombre.charAt(i)!=' ')
				return false;
		int c=0;
		for (int i=0;i<nombre.length();i++)
			if (Character.isLetterOrDigit(nombre.charAt(i)))
				c++;
		if(c==0) return false;
		return Character.isLetter(nombre.charAt(0));
	}
}