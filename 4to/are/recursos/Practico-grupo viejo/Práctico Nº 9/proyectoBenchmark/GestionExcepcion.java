

import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Clase que proporciona metodos para la gestion de excepciones
 * @version Diciembre de 2005
 */
public class GestionExcepcion
{
	/**
	 * nombre del archivo por defecto donde se graban los datos de la excepcion
	 */
	public static String ARCHIVO_DEFECTO="excepcion.txt";
	
	/**
	 * GRABAR_ARCHIVO_ALEATORIO indica que se le asignara un nombre de tipo excepcion1425.txt siendo los numeros aleatorios (entre 0 y 4999)
	 */
	public static int GRABAR_ARCHIVO_ALEATORIO=0;
	
	/**
	* GRABAR_ARCHIVO_DEFECTO indica que el nombre del archivo sera el establecido por defecto
	*/
	public static int GRABAR_ARCHIVO_DEFECTO=1;
	
	/**
	 * graba los datos de la excepcion pasada por parametro en un archivo de nombre aleatorio
	 * el archivo se ubica en el directorio actual de la aplicacion
	 * ejemplo: excepcion2154.txt
	 * @param e Exception cuyos datos quieren ser grabados
	 * @return boolean indicando el exito de la operacion
	 */
	public static boolean grabarAleatorio(Exception e)
	{
		return grabar(e,"excepcion"+(int)(Math.random()*5000)+".txt");	
	}
	
	/**
	 * graba los datos de la excepcion pasada por parametro en un archivo cuyo nombre esta asignado por defecto
	 * el archivo se ubica en el directorio actual de la aplicacion
	 * @param e Exception cuyos datos quieren ser grabados
	 * @return boolean indicando el exito de la operacion
	 */	
	public static boolean grabar(Exception e)
	{
		return grabar(e,ARCHIVO_DEFECTO);	
	}

	/**
	 * graba los datos de la excepcion pasada por parametro en un archivo
	 * el archivo se ubica en el directorio actual de la aplicacion
	 * el nombre del archivo sera un por defecto o uno aleatorio dependiendo del valor de la variable tipo pasada por parametro
	 * @param e Exception cuyos datos quieren ser grabados
	 * @param tipo int que indica el nombre del archivo a utilizar
	 * @return boolean indicando el exito de la operacion
	 */	
	public static boolean grabar(Exception e,int tipo)
	{
		if (tipo==GRABAR_ARCHIVO_ALEATORIO) return grabarAleatorio(e);
		if (tipo==GRABAR_ARCHIVO_DEFECTO) return grabar(e);
		return false;
	}

	/**
	 * graba los datos de la excepcion pasada por parametro en un archivo
	 * el nombre del archivo tambien es pasado por parametro
	 * @param e Exception cuyos datos quieren ser grabados
	 * @param nombreArchivo String que representa el nombre del archivo donde guardar los datos
	 * @return boolean indicando el exito de la operacion
	 */	
	public static boolean grabar(Exception e,String nombreArchivo)
	{
		return GestionArchivo.grabarStringArchivo(getDatos(e),nombreArchivo);
	}
	
	/**
	 * retorna un String que es la traza de la excepcion pasada por parametro
	 * @param e Excepcion cuya traza quiere ser conocida
	 * @return String que es la traza de la excepcion
	 */
	public static String getTraza(Exception e)
	{
		String traza="";
		StackTraceElement a[]=e.getStackTrace();
		int i;
		for (i=0;i<a.length-1;i++)
		{
			traza+=a[i]+"\n";
		}
		traza+=a[i];
		return traza;		
	}
	
	/**
	 * retorna una linea en la traza de la excepcion pasad por parametro
	 * se pasa por parametro la posicion en la traza
	 * @param e Excepcion cuya traza quiere ser conocida
	 * @param pos int que es el paso dentro de la traza que se quiere conocer
	 * @return String que es el la linea inidicada dentro del paso de la traza de la excepcion
	 */
	public static String getTraza(Exception e,int pos)
	{
		StackTraceElement a[]=e.getStackTrace();
		if (pos>=0&&pos<a.length) return a[pos].toString();
		return null;
	}
	
	/**
	 * retorna la ultima linea de la traza de la excepcion pasad por parametro
	 * @param e Excepcion cuya traza quiere ser conocida
	 * @return String que es la ultima linea de la traza de la excepcion
	 */
	public static String getUltimaTraza(Exception e)
	{
		StackTraceElement a[]=e.getStackTrace();
		if (a==null||a.length==0) return "";
		return a[0].toString();
	}
	
	/**
	 * acceso a los datos de la excepcion pasada por parametro
	 * @param e Excepcion cuyos datos quieren ser conocidos
	 * @return String que son los datos de la excepcion
	 */
	public static String getDatos(Exception e)
	{
		return "Excepcion"+"\n"+"date: "+(new Date()).toString()+"\n"+
		"Clase: "+e.getClass().getName()+"\n"+
		"Mensaje: "+"\n"+e.getMessage()+"\n\n"+
		"Traza: "+"\n"+getTraza(e);
	}
	
	/**
	 * muestra por consola los datos de la excepcion pasada por parametro
	 * @param e Excepcion cuyos datos son mostrados por consola
	 */
	public static void mostrarDatos(Exception e)
	{
		System.out.println(getDatos(e));
	}
	
	/**
	 * muestra un cuadro de mensaje indicando la clase y linea donde se produjo la excepcion pasada por parametro
	 * @param e Excepcion cuyos datos son mostrados en un cuadro de mensaje
	 */
	public static void mostrarMensaje(Exception e)
	{
		JOptionPane.showMessageDialog(null,"mensaje: "+e.getMessage()+"\n"+"linea: "+getUltimaTraza(e),""+e.getClass().getName(),JOptionPane.ERROR_MESSAGE);
	}

	/**
	 * muestra una ventana de dialogo con los datos de la excepcion pasada por parametro
	 * @param e Excepcion cuyos datos son mostrados en una ventana de dialogo
	 */	
	public static void mostrarDialogo(Exception e)
	{
		Dialogo dialogo=new Dialogo(getDatos(e));
	}
	
	/**
	 * Clase visual (ventana de dialogo) para mostrar los datos de la excepcion
	 */
	static class Dialogo
	{
		private JDialog dialogo;
		private Container panelFondo;
		private JTextArea txt;
		private JScrollPane scr;
		
		public Dialogo(String datos)
		{
			dialogo=new JDialog();
			dialogo.setTitle("Ha ocurrido una excepcion");
			dialogo.setModal(true);
			dialogo.setSize(400,170);
			GestionVisual.centrarContenedor(dialogo);
			panelFondo=dialogo.getContentPane();
			panelFondo.setLayout(new GridLayout(1,1));
			txt=new JTextArea();
			scr=new JScrollPane(txt);
			txt.setEditable(false);
			txt.setText(datos);
			panelFondo.add(scr);
			scr.getVerticalScrollBar().setValue(0);
			dialogo.addWindowListener(new EscuchaVentana());
			dialogo.setVisible(true);
		}
		
		class EscuchaVentana extends WindowAdapter
		{
			public void windowClosing(WindowEvent e)
			{
				dialogo.dispose();
			}
		}
	}
}