import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

/**
 *  Clase Visual(Ventana) que muestra un grafico de barras.
 *  @version Noviembre de 2005
 */

public class VentanaGraficoBarras extends JFrame
{
	private GestionGrafico gestionGrafico;
	private Container panelFondo;
	private int valores[][];
	private String labelX[],labelY[],titulo,titulos[];
	private boolean mostrarValores,mostrarParalelos,mostrarMeridianos;
	
	public VentanaGraficoBarras (String tituloVentana,int va[][],String lX[],String lY[],String ti,boolean mV,boolean mP,boolean mM,String tit[],GestionGrafico g)
	{
		valores=va;
		labelX=lX;
		labelY=lY;
		titulo=ti;
		mostrarValores=mV;
		mostrarParalelos=mP;
		mostrarMeridianos=mM;
		titulos=tit;
		gestionGrafico=g;
		
		this.setTitle(tituloVentana);
		
		panelFondo=this.getContentPane();
		this.addWindowListener(new EscuchaVentana());
		panelFondo.setBackground(Color.white);
		setBounds(GestionVisual.DIMENSION_TESAURO);
		GestionVisual.centrar(this);
	}
	public void paint(Graphics g)
	{
		super.paint(g);
		g=panelFondo.getGraphics();
		gestionGrafico.setArea(g);
		gestionGrafico.setAnchoAlto(panelFondo.getWidth(),panelFondo.getHeight());
		gestionGrafico.setX(110);
		gestionGrafico.setY(panelFondo.getHeight()-60);
		gestionGrafico.setXYTexto(20,(gestionGrafico.getY()+20));
		gestionGrafico.setLongitudX(panelFondo.getWidth()-(65*2));
		gestionGrafico.setLongitudY(panelFondo.getHeight()-(55*2));
		gestionGrafico.setLongitudDivisionX(15);
		gestionGrafico.setLongitudDivisionY(15);
		gestionGrafico.setCantidadDivisionesX(5);
		gestionGrafico.setCantidadDivisionesY(4);
		gestionGrafico.dibujarEjes();		
		gestionGrafico.dibujarBarras(valores);
		gestionGrafico.dibujarBordesBarras();
		if (mostrarValores) gestionGrafico.dibujarLabelY(labelY);
		gestionGrafico.dibujarLabelX(labelX);
		gestionGrafico.dibujarDivisiones();		
		gestionGrafico.dibujarTitulo(titulo,true);
		gestionGrafico.dibujarTitulosBarras(titulos);
		if (mostrarMeridianos) gestionGrafico.dibujarMeridianos();
		if (mostrarParalelos) gestionGrafico.dibujarParalelos();
		//System.out.println(""+getWidth());
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