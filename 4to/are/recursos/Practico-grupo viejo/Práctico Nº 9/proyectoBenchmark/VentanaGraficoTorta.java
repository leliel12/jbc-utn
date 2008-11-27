import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

/**
 *  Clase Visual(Ventana) que muestra un grafico de torta.
 *  @version Noviembre de 2005
 */

public class VentanaGraficoTorta extends JFrame
{
	private GestionGrafico gestionGrafico;
	private Container panelFondo;
	private int valores[];
	private String label[],titulo;
	
	public VentanaGraficoTorta(int va[],String l[],String t,GestionGrafico g)
	{
		valores=va;
		label=l;
		titulo=t;
		gestionGrafico=g;
		
		this.setTitle(titulo);
		
		panelFondo=this.getContentPane();
		this.addWindowListener(new EscuchaVentana());
		panelFondo.setBackground(Color.white);
		setBounds(GestionVisual.DIMENSION_DETALLE);
		GestionVisual.centrar(this);
	}
	public VentanaGraficoTorta(int va[],String l[],String t)
	{
		valores=va;
		label=l;
		titulo=t;
		gestionGrafico=new GestionGrafico();
		
		this.setTitle(titulo);
		
		panelFondo=this.getContentPane();
		this.addWindowListener(new EscuchaVentana());
		panelFondo.setBackground(Color.white);
		setBounds(GestionVisual.DIMENSION_DETALLE);
		GestionVisual.centrar(this);		
	}	
	public void paint(Graphics g)
	{
		super.paint(g);
		g=panelFondo.getGraphics();
		gestionGrafico.setArea(g);
		gestionGrafico.setAnchoAlto(panelFondo.getWidth(),panelFondo.getHeight());
		gestionGrafico.setX(panelFondo.getWidth()/2);
		gestionGrafico.setY(10);
		gestionGrafico.setXYLabel(30,50);
		gestionGrafico.setDiametro((int)(panelFondo.getWidth()/2.3));
		gestionGrafico.dibujarGraficoTorta(valores,label,true);
		gestionGrafico.dibujarBordeGraficoTorta();
		if (!GestionVector.verificarUnoTotal(gestionGrafico.getValores()))
		{
			gestionGrafico.dibujarBordesInternosGraficoTorta(valores);			
		}
	}
	class EscuchaVentana extends WindowAdapter
	{
		public void windowClosing(WindowEvent e)
		{
			respuestaVentanaCerrada();
		}
	}
	
	public void respuestaVentanaCerrada()
	{
		dispose();
	}		
	public void setVisible(boolean v)
	{
		super.setVisible(v);
		this.setEnabled(true);
	}
}