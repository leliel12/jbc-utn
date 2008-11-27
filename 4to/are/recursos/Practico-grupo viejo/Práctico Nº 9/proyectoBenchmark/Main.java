/*
 * Main.java
 *
 * Created on 16 de julio de 2006, 19:03 AM
 */

/**
 *
 * @author  pas
 */

public class Main
{

	public static String NOMBRE="Sombra";
	public static String VERSION="0.97 beta+";	

	private static javax.swing.JFrame ventanaPrincipal,ventanaBenchmark,ventanaConsola;

	public static void main(String ar[])
	{
		gestionarDirectorios();
		ventanaPrincipal=new VentanaPrincipal();
		mostrarVentanaPrincipal(true);
	}

	public static void mostrarVentanaPrincipal(boolean mostrar)
	{
		ventanaPrincipal.setVisible(mostrar);
	}
		
	public static void mostrarVentanaConsola()
	{
		ventanaConsola=new VentanaConsola();
		mostrarVentanaPrincipal(false);
		ventanaConsola.setVisible(true);
	}

	public static void mostrarVentanaBenchmark()
	{
		ventanaBenchmark=new VentanaBenchmark();
		mostrarVentanaPrincipal(false);
		ventanaBenchmark.setVisible(true);
	}

	public static void cerrarVentanaConsola()
	{
		ventanaConsola.dispose();
		mostrarVentanaPrincipal(true);
	}
	
	public static void cerrarVentanaBenchmark()
	{
		ventanaBenchmark.dispose();
		mostrarVentanaPrincipal(true);
	}

	public static void cerrarAplicacion()
	{
		System.exit(0);
	}

	private static void gestionarDirectorios()
	{
		GestionArchivo.gestionarDirectorio("recursos");
		GestionArchivo.gestionarDirectorio("recursos/tests");
		GestionArchivo.gestionarDirectorio("recursos/imagenes");
		GestionArchivo.gestionarDirectorio("recursos/texto");
		GestionArchivo.gestionarDirectorio("recursos/archivos");
	}
}