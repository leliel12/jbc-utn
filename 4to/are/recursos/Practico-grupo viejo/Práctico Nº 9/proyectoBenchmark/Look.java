
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;
import javax.swing.border.*;
import javax.swing.tree.*;

/**
 * Un look and feel disponible para la aplicacion
 * @version Marzo 2006
 */
public class Look implements Comparable
{
	private String nombre;
	private String clase;	
	
	/**
	 * Crea un look con un nombre y una clase
	 * @param nombre nombre del look and feel
	 * @param clase clase del look and feel
	 */
	public Look(String nombre,String clase)
	{
		setNombre(nombre);
		setClase(clase);
	}
	
	/**
	 * Acceso al nombre del look and feel
	 * @return nombre del look and feel
	 */
	public String getNombre()
	{
		return nombre;
	}
	
	/**
	 * Acceso a la clase del look and feel
	 * @return clase del look and feel
	 */
	public String getClase()
	{
		return clase;
	}
	
	/**
	 * Modifica el nombre del look and feel
	 * @param nombre nombre del look
	 */
	public void setNombre(String nombre)
	{
		this.nombre=nombre;
	}
	
	/**
	 * Modifica la clase del look and feel
	 * @param clase clase del look
	 */
	public void setClase(String clase)
	{
		this.clase=clase;
	}
	
	/**
	 * Redefine el metodo compareTo de Object
	 * @param o Object para comparar si se trata de un look and feel mayor, menor o igual
	 * @return boolean que indica si se trata del mismo Look, o es mayor o menor
	 */
	public int compareTo(Object o)
	{
		if (o.getClass()!=Look.class) return -1;
		Look u=(Look)o;
		return this.nombre.compareTo(u.nombre);
	}
	
	/**
	 * Indica si la clase del look and feel esta disponible
	 * @return boolean que indica si la clase del look and feel esta disponible
	 */
	public boolean esDisponible()
	{
    	try 
        {
            Class lnfClass = Class.forName(clase);
            LookAndFeel newLAF = (LookAndFeel)(lnfClass.newInstance());
            return newLAF.isSupportedLookAndFeel();
        } 
        catch (Exception e) 
        { 
            return false;
        }
	}	
	
	/**
	 * Actualiza el look de un contenedor
	 * @param c contenedor cuyo look and feel sera actualizado
	 */
	public void actualizar(Container c)
	{
		try
		{
			javax.swing.UIManager.setLookAndFeel(clase);
			SwingUtilities.updateComponentTreeUI(c);		
		} catch (Exception qq)
		{
			;
		}
	}
	
}

