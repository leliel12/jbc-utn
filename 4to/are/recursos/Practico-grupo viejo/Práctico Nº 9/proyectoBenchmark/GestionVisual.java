
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;
import javax.swing.border.*;
import javax.swing.tree.*;


/**
 * Clase para gestionar la visualizacion de objetos de interfaz
 * define visualizaciones (VisualAplicacion), fuentes ,dimensiones para objetos (destinado especialmente para ventanas y dialogos)
 * y look and feel de aplicacion
 * @version Marzo 2006
 */
public class GestionVisual
{
	/** Directorio donde se encuentran las imagenes de interfaz*/
	public static final String DIRECTORIO="recursos/imagenes/";
	
	/** Archivo donde se guardan los ultimos look and feel y visual seleccionados*/
//	private static String nombreArchivo=Main.DIRECTORIO+"gv.dat";	
	
	// Archivos de imagen
	private static final Icon customOpenIcon=new ImageIcon(DIRECTORIO+"abrir.gif");
  	private static final Icon customClosedIcon=new ImageIcon(DIRECTORIO+"cerrar.gif");
  	private static final Icon customLeafIcon=new ImageIcon(DIRECTORIO+"leaf.gif");	   	
	
	/** Rendender de arboles*/
	public static final DefaultTreeCellRenderer rendender = new DefaultTreeCellRenderer();
	
	// Path del archivo de imagen icono
	public static final String pathIcono=DIRECTORIO+"icono.gif";
	
	/** Icono de la aplicacion*/
	public static Image icono;
	
	/** Path del fondo de la aplicacion*/
	private static String pathFondo=DIRECTORIO+"fondo.gif";
	
	/** Fondo de la aplicacion*/
	public static Icon fondo;

	/**
	 * Setea los valores de posicion de look y posicion de visual 
	 * @param posLook posicion de look
	 * @param posVisual posicion de visual
	 */
	public static void set(int posLook,int posVisual)
	{
		if (posLook<0||posLook>=look.length) posLook=0;
		if (posVisual<0||posVisual>=visual.length) posVisual=0;
	}
	
	/** 
	 * Actualiza la visualizacion del contenedor con los valores seteados actualmente
	 * @param c contenedor cuya visualizacion es actualizada
	 */
	public static void actualizar(Container c)
	{
		actualizarLook(c);
		actualizarVisual(c);
	}
	
	/**
	 * Actualiza el look and feel del contenedor
	 * @param c contenedor cuyo look and feel sera actualizado
	 */
	public static void actualizarLook(Container c)
	{
		look[posLook].actualizar(c);
	}
	
	/**
	 * Actualiza el contenedor en termino de visual aplicacion
	 * @param c cointenedor cuya visualizacion sera actualizada
	 */
	public static void actualizarVisual(Container c)
	{
		visual[posVisual].actualizar(c);
	}
	
	/**
	 * Centra el contenedor en la pantalla
	 * @param c contenedor a ser centrado en la pantalla
	 */
	public static void centrar(Container c)
	{
		centrarContenedor(c);
	}
	
	/**
	 * Centra el contenedor en la pantalla
	 * @param c contenedor a ser centrado en la pantalla
	 */
	public static void centrarContenedor(Container c)
	{
        c.setLocation((int)((getAnchoPantalla()/2)-(int)(c.getWidth()/2)),
        (int)((getAltoPantalla()/2)-(int)(c.getHeight()/2)));
	}	
	
	/**
	 * retorna el ancho de la pantalla
	 * @return int que es el ancho de la pantalla
	 */
	public static int getAnchoPantalla()	
	{
		return Toolkit.getDefaultToolkit().getScreenSize().width;
	}
	
	/**
	 * retorna el alto de la pantalla
	 * @return int que es el alto de la pantalla
	 */	
	public static int getAltoPantalla()
	{
		return Toolkit.getDefaultToolkit().getScreenSize().height;
	}
	
	/**
	 * retorna el valor para el cual se centra horizontalmente un componente cuyo ancho es pasado por parametro
	 * @param ancho int que es el ancho del componente
	 * @return int que es la posicion X para centrar el componente
	 */
	public static int getXCentro(int ancho)
	{
		return (getAnchoPantalla()/2)-(ancho/2);
	}
	
	/**
	 * retorna el valor para el cual se centra verticalmente un componente cuyo alto es pasado por parametro
	 * @param alto int que es el alto del componente
	 * @return int que es la posicion Y para centrar el componente
	 */
	public static int getYCentro(int alto)
	{
		return (getAltoPantalla()/2)-(alto/2);
	}
	
	/**
	 * Setea el icono de una ventana
	 * @param ventana ventana al cual se le incorporara icono
	 * @param path path del icono de la ventana
	 */
	public static void setIcono(JFrame ventana,String path)	
	{
		ventana.setIconImage(Toolkit.getDefaultToolkit().getImage(path));			
	}

	/**
	 * modifica el icono a traves del path del archivo pasado por parametro	
	 * @param pathIcono String que es el path del archivo que sera el icono de la aplicacion
	 */
	private static void cargarIcono()
	{
		try
		{
        	icono=Toolkit.getDefaultToolkit().getImage(pathIcono);
		} catch (Exception qq){}
	}
	// Colores
		
	/** Color violeta claro */
	public static Color VIOLETA_CLARO=new Color(204,204,255);
	
	/** Color violeta oscuro */
	public static Color VIOLETA_OSCURO=new Color(153,153,255);
	
	/** Color azul francia */
	public static Color AZUL_FRANCIA=new Color(74,102,140);
	
	/** Color azul-celeste claro */
	public static Color AZUL_CELESTE_CLARO=new Color(102,157,224);
	
	/** Color granate */
	public static Color GRANATE=new Color(139,0,0);
	
	/** Color verde musgo */
	public static Color VERDE_MUSGO=new Color(74,102,0);
	
	/** Color verde opaco */
	public static Color VERDE_OPACO=new Color(0,153,153);
	
	/** Color verde claro */
	public static Color VERDE_CLARO=new Color(204,255,204);
	
	/** Color verde no brillante */
	public static Color VERDE_NO_BRILLANTE=new Color(0,204,204);
	
	/** Color naranja claro */
	public static Color NARANJA_CLARO=new Color(255,204,153);
	
	/** Color amarillo claro */
	public static Color AMARILLO_CLARO=new Color(255,255,153);
	
	/** Color celeste opaco */
	public static Color CELESTE_OPACO=new Color(153,204,255);
	
	/** Color celeste claro */
	public static Color CELESTE_CLARO=new Color(204,255,255);
	
	/** Color marron verde */
	public static Color MARRON_VERDE=new Color(102,102,0);
	
	// Dimensiones
	
	/** Dimension reducido */
	public static Rectangle DIMENSION_REDUCIDO=new Rectangle(200,100,250,90);
	
	/** Dimension inicio */
	public static Rectangle DIMENSION_INICIO=new Rectangle(200,100,400,300);	
	
	/** Dimension barra */
	public static Rectangle DIMENSION_BARRA=new Rectangle(200,100,360,240);	
	
	/** Dimension de principal */
	public static Rectangle DIMENSION_PRINCIPAL=new Rectangle(20,15,662,566);
	
	/** Dimension de dialogo */
	public static Rectangle DIMENSION_DIALOGO=new Rectangle(200,100,250,155);
	
	/** Dimension de ventana */
	public static Rectangle DIMENSION_VENTANA=new Rectangle (50,20,650,500);
	
	/** Dimension de ventana */
	public static Rectangle DIMENSION_TESAURO=new Rectangle (50,20,755,450);	
	
	/** Dimension de detalle */
	public static Rectangle DIMENSION_DETALLE=new Rectangle (300,50,410,250);
	
	/** Dimension mediana */
	public static Rectangle DIMENSION_MEDIANA=new Rectangle (300,50,315,250);	
	
	/** Dimension media*/
	public static Rectangle DIMENSION_MEDIO=new Rectangle(100,20,410,390);
	
	/** Dimension acerca*/
	public static Rectangle DIMENSION_ACERCA=new Rectangle(100,20,315,340);	
	
	/** Dimension simple */
	public static Rectangle DIMENSION_SIMPLE=new Rectangle (300,50,505,460);
	
	/** Dimension standar */
	public static Rectangle DIMENSION_STANDAR=new Rectangle (300,50,485,380);	
	
	/** Dimension fija */
	public static Rectangle DIMENSION_FIJA=new Rectangle (300,50,430,360);		
	
	/** Dimension inversa */
	public static Rectangle DIMENSION_INVERSA=new Rectangle (300,50,430,275);			
	
	/** Dimension date */
	public static Rectangle DIMENSION_DATE=new Rectangle (300,50,395,420);			
	
	/** Dimension estatica */
	public static Rectangle DIMENSION_ESTATICA=new Rectangle (300,50,519,438);				
	
	/** Dimension respaldo */
	public static Rectangle DIMENSION_RESPALDO=new Rectangle (300,50,565,420);					
	
	/** Dimension minima */
	public static Rectangle DIMENSION_MINIMA=new Rectangle (300,50,287,351);					
	
	/** Dimension archivo */
	public static Rectangle DIMENSION_ARCHIVO=new Rectangle (300,50,420,215);						
	
	// Visuales aplicacion
	
	/** Visual aplicacion base */
	public static VisualAplicacion VISUAL_BASE=new VisualAplicacion("base estatica",Color.lightGray,Color.lightGray,Color.black,Color.black,Color.black,Color.black,new Font ("Verdana",Font.PLAIN,12),new Font ("Times New Roman",Font.BOLD,11),new Font ("Times New Roman",Font.PLAIN,12),new Font ("Verdana",Font.PLAIN,13),new Font ("Verdana",Font.PLAIN,12),new Font ("Arial",Font.PLAIN,12));
	
	/** Visual aplicacion verde */
	public static VisualAplicacion VISUAL_VERDE=new VisualAplicacion("verde statico",VERDE_OPACO,Color.black,Color.WHITE,Color.WHITE,Color.BLUE,Color.BLACK,new Font ("Verdana",Font.PLAIN,12),new Font ("Times New Roman",Font.BOLD,11),new Font ("Times New Roman",Font.PLAIN,12),new Font ("Verdana",Font.PLAIN,13),new Font ("Verdana",Font.PLAIN,12),new Font ("Verdana",Font.PLAIN,12));	
	
	/** Visual aplicacion alifraco*/
	public static final VisualAplicacion ALIFRACO=new VisualAplicacion("Alifraco",VIOLETA_CLARO,VIOLETA_OSCURO,Color.black,Color.darkGray,Color.blue,Color.black,new Font ("Verdana",Font.PLAIN,12),new Font ("Arial",Font.BOLD,11),new Font ("Garamond",Font.PLAIN,12),new Font ("Arial",Font.PLAIN,13),new Font ("Times New Roman",Font.PLAIN,12),new Font ("Verdana",Font.PLAIN,12));
	
	/** Visual aplicacion delegado*/
	public static final VisualAplicacion DELEGADO=new VisualAplicacion("Delegado",AZUL_FRANCIA,AZUL_CELESTE_CLARO,Color.white,Color.white,Color.white,Color.white,new Font ("Verdana",Font.PLAIN,12),new Font ("Arial",Font.BOLD,11),new Font ("Garamond",Font.PLAIN,12),new Font ("Arial",Font.PLAIN,13),new Font ("Times New Roman",Font.PLAIN,12),new Font ("Verdana",Font.PLAIN,12));
	
	/** Visual aplicacion fumanchu*/
	public static final VisualAplicacion FUMANCHU=new VisualAplicacion("Fumanchu",GRANATE,VERDE_MUSGO,Color.lightGray,Color.lightGray,Color.white,Color.white,new Font ("Verdana",Font.PLAIN,12),new Font ("Arial",Font.BOLD,11),new Font ("Garamond",Font.PLAIN,12),new Font ("Arial",Font.PLAIN,13),new Font ("Times New Roman",Font.PLAIN,12),new Font ("Verdana",Font.PLAIN,12));
	
	/** Visual aplicacion makropp*/
	public static final VisualAplicacion MAKROPP=new VisualAplicacion("makropp",VERDE_OPACO,Color.black,Color.WHITE,Color.WHITE,Color.BLUE,Color.BLACK,new Font ("Verdana",Font.PLAIN,12),new Font ("Times New Roman",Font.BOLD,11),new Font ("Times New Roman",Font.PLAIN,12),new Font ("Verdana",Font.PLAIN,13),new Font ("Verdana",Font.PLAIN,12),new Font ("Verdana",Font.PLAIN,12));
	
	/** Visual aplicacion saraz*/
	public static final VisualAplicacion SARAZ=new VisualAplicacion("Saraz",Color.ORANGE,Color.PINK,Color.BLUE,Color.BLUE,Color.BLUE,Color.BLUE,new Font ("Verdana",Font.PLAIN,12),new Font ("Arial",Font.BOLD,11),new Font ("Garamond",Font.PLAIN,12),new Font ("Arial",Font.PLAIN,13),new Font ("Times New Roman",Font.PLAIN,12),new Font ("Verdana",Font.PLAIN,12));
	
	/** Visual aplicacion nero*/
	public static final VisualAplicacion NERO=new VisualAplicacion("Nero",VERDE_CLARO,VERDE_CLARO,Color.red,Color.red,Color.blue,Color.red,new Font ("Verdana",Font.PLAIN,12),new Font ("Arial",Font.BOLD,11),new Font ("Garamond",Font.PLAIN,12),new Font ("Arial",Font.PLAIN,13),new Font ("Times New Roman",Font.PLAIN,12),new Font ("Verdana",Font.PLAIN,12));
	
	/** Visual aplicacion bibs*/
	public static final VisualAplicacion BIBS=new VisualAplicacion("Bib's",NARANJA_CLARO,AMARILLO_CLARO,Color.BLACK,Color.BLACK,Color.BLUE,Color.BLACK,new Font ("Verdana",Font.PLAIN,12),new Font ("Times New Roman",Font.BOLD,11),new Font ("Times New Roman",Font.PLAIN,12),new Font ("Verdana",Font.PLAIN,13),new Font ("Verdana",Font.PLAIN,12),new Font ("Arial",Font.PLAIN,12));
	
	/** Visual aplicacion fritz*/
	public static final VisualAplicacion FRITZ=new VisualAplicacion("Fritz",Color.RED,Color.WHITE,Color.BLACK,Color.BLACK,Color.WHITE,Color.WHITE,new Font ("Verdana",Font.PLAIN,12),new Font ("Arial",Font.BOLD,11),new Font ("Garamond",Font.PLAIN,12),new Font ("Arial",Font.PLAIN,13),new Font ("Times New Roman",Font.PLAIN,12),new Font ("Verdana",Font.PLAIN,12));
	
	/** Visual aplicacion firri*/
	public static final VisualAplicacion FIRRI=new VisualAplicacion("Firri",Color.GREEN,Color.ORANGE,Color.BLACK,Color.darkGray,Color.BLACK,Color.BLACK,new Font ("Verdana",Font.PLAIN,12),new Font ("Arial",Font.BOLD,11),new Font ("Garamond",Font.PLAIN,12),new Font ("Arial",Font.PLAIN,13),new Font ("Times New Roman",Font.PLAIN,12),new Font ("Verdana",Font.PLAIN,12));
	
	/** Visual aplicacion mana*/
	public static final VisualAplicacion MANA=new VisualAplicacion("Mana",VERDE_NO_BRILLANTE,CELESTE_OPACO,Color.black,Color.darkGray,Color.blue,Color.black,new Font ("Verdana",Font.PLAIN,12),new Font ("Arial",Font.BOLD,11),new Font ("Garamond",Font.PLAIN,12),new Font ("Arial",Font.PLAIN,13),new Font ("Times New Roman",Font.PLAIN,12),new Font ("Verdana",Font.PLAIN,12));
	
	/** Visual aplicacion relax*/
	public static final VisualAplicacion RELAX=new VisualAplicacion("Relax",Color.BLUE,Color.GREEN,Color.BLACK,Color.ORANGE,Color.BLUE,Color.ORANGE,new Font ("Verdana",Font.PLAIN,12),new Font ("Arial",Font.BOLD,11),new Font ("Garamond",Font.PLAIN,12),new Font ("Arial",Font.PLAIN,13),new Font ("Times New Roman",Font.PLAIN,12),new Font ("Verdana",Font.PLAIN,12));
	
	/** Visual aplicacion rivarosa*/
	public static final VisualAplicacion RIVAROSA=new VisualAplicacion("Rivarosa",MARRON_VERDE,MARRON_VERDE,Color.WHITE,Color.WHITE,Color.BLUE,Color.BLACK,new Font ("Verdana",Font.PLAIN,12),new Font ("Arial",Font.BOLD,11),new Font ("Garamond",Font.PLAIN,12),new Font ("Arial",Font.PLAIN,13),new Font ("Times New Roman",Font.PLAIN,12),new Font ("Verdana",Font.PLAIN,12));
	
	/** Visual aplicacion volvo*/
	public static final VisualAplicacion VOLVO=new VisualAplicacion("Volvo",CELESTE_CLARO,CELESTE_CLARO,Color.BLUE,Color.BLUE,Color.BLUE,Color.BLACK,new Font ("Verdana",Font.PLAIN,12),new Font ("Times New Roman",Font.BOLD,11),new Font ("Times New Roman",Font.PLAIN,12),new Font ("Verdana",Font.PLAIN,13),new Font ("Verdana",Font.PLAIN,12),new Font ("Arial",Font.PLAIN,12));
	
	/** Visual aplicacion base*/
	public static final VisualAplicacion BASE=new VisualAplicacion("Base",Color.lightGray,Color.lightGray,Color.black,Color.black,Color.black,Color.black,new Font ("Verdana",Font.PLAIN,12),new Font ("Times New Roman",Font.BOLD,11),new Font ("Times New Roman",Font.PLAIN,12),new Font ("Verdana",Font.PLAIN,13),new Font ("Verdana",Font.PLAIN,12),new Font ("Arial",Font.PLAIN,12));
	
	/** Visual de aplicacion disponibles*/
	private static final VisualAplicacion visual[]=new VisualAplicacion[]{ALIFRACO,DELEGADO,FUMANCHU,MAKROPP,SARAZ,NERO,BIBS,FRITZ,FIRRI,MANA,RELAX,RIVAROSA,VOLVO,BASE};
	
	/** Visual aplicacion actual*/
	private static int posVisual;
	
	/** Look and feel metal*/
	public static final Look METAL=new Look("Metal","javax.swing.plaf.metal.MetalLookAndFeel");
	
	/** Look and feel motif*/
	public static final Look MOTIF=new Look("Motif","com.sun.java.swing.plaf.motif.MotifLookAndFeel");
	
	/** Look and feel windows*/
	public static final Look WINDOWS=new Look("Windows","com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
	
	/** Look and feel gtk*/
	public static final Look GTK=new Look("GTK","com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
	
	/** Look and feel nim rod*/
	public static final Look NIM_ROD=new Look("NimRod","com.nilo.plaf.nimrod.NimRODLookAndFeel");
	
	/** Look and feel metouia*/
	public static final Look METOUIA=new Look("Metouia","net.sourceforge.mlf.metouia.MetouiaLookAndFeel");
	
	/** Look and feel xp*/
	public static final Look XP=new Look("XP","com.stefankrause.xplookandfeel.XPLookAndFeel");
	
	//public static final Look QUAQUA=new Look("Quaqua","ch.randelshofer.quaqua.QuaquaLookAndFeel");
	
	/** Look and feel hippo*/
	public static final Look HIPPO=new Look("Hippo","se.diod.hippo.plaf.HippoLookAndFeel");
	
	/** Looks and feel disponibles*/
	public static Look look[];
	
	/** Look and feel actual*/
	private static int posLook;
	
	/** 
	 * Carga los looks and feel
	 */
	private static void cargarLooks()
	{
		Lista lista=new Lista();
		if (METAL.esDisponible()) lista.insertarUltimo(METAL);
		if (MOTIF.esDisponible()) lista.insertarUltimo(MOTIF);
		if (WINDOWS.esDisponible()) lista.insertarUltimo(WINDOWS);
		if (GTK.esDisponible()) lista.insertarUltimo(GTK);
		if (NIM_ROD.esDisponible()) lista.insertarUltimo(NIM_ROD);
		if (METOUIA.esDisponible()) lista.insertarUltimo(METOUIA);		
		if (XP.esDisponible()) lista.insertarUltimo(XP);		
//		if (QUAQUA.esDisponible()) lista.insertarUltimo(QUAQUA);		
		if (HIPPO.esDisponible()) lista.insertarUltimo(HIPPO);		
//		look=Look.getLooks(lista);
	}
	
}