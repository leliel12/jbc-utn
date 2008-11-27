import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

/**
 *  Una clase utilizada para la creacion de los graficos existentes en el programa.
 *  Dependiendo del tipo de grafico que se desea construir se utilizaran los diferentes metodos en secuencia diferente.
 *  @version Noviembre de 2005
 */

public class GestionGrafico 
{
	private int x,y,longitudX,longitudY,
	barras[][],avance[][][],resultadoAvance[][],cantidadDivisionesX,cantidadDivisionesY,longitudDivisionX,longitudDivisionY,
	xTexto,yTexto,xTitulo,yTitulo,ancho,alto,diametro,xLabel,yLabel,valores[],valorMaximoY;
	private int tipoGrafico;
	private boolean concavo;
	private Graphics area;
	private Color colores[];
	private Font fuenteTitulo;
	private String labelX[],labelY[],titulo,label[];
	private int esperado;
	
	public GestionGrafico(Graphics a)
	{
		area=a;
		cargarColores();
		fuenteTitulo=new Font("TimesRoman",Font.BOLD,50);
	}
	public GestionGrafico()
	{
		cargarColores();
	}
	public int getY()
	{
		return y;
	}
	public void cargarColores()
	{
		colores=new Color[6];
		colores[0]=Color.red;
		colores[1]=Color.blue;
		colores[2]=Color.green;
		colores[3]=Color.pink;
		colores[4]=Color.orange;
		colores[5]=Color.cyan;
	}
	public Graphics getArea()
	{
		return area;
	}
	public void setArea(Graphics a)
	{
		area=a;
	}
	public void setXY(int x,int y)
	{
		this.x=x;
		this.y=y;
	}
	public void setX(int x)
	{
		this.x=x;
	}
	public void setY(int y)
	{
		this.y=y;
	}
	public void setLongitudX(int lX)
	{
		longitudX=lX;
	}
	public void setLongitudY(int lY)
	{
		longitudY=lY;
	}
	public void setlongitudXY(int lX,int lY)	
	{
		longitudX=lX;
		longitudY=lY;
	}
	public void setTipoGrafico(int tG)
	{
		tipoGrafico=tG;
	}
	public void setXYTexto(int x,int y)
	{
		xTexto=x;
		yTexto=y;
	}
	
	public void dibujarEjes()
	{
		area.drawLine(x,y,x+longitudX,y);
		area.drawLine(x,y,x,y-longitudY);
	}
	public void dibujarEjes(int x,int y, int lX,int lY)
	{
		this.x=x;
		this.y=y;
		longitudX=lX;
		longitudY=lY;
		dibujarEjes();
	}	
	public void setCantidadDivisionesX(int c)
	{
		cantidadDivisionesX=c;
	}
	public void setCantidadDivisionesY(int c)
	{
		cantidadDivisionesY=c;
	}	
	public void setCantidadDivisionesXY(int cX,int cY)
	{
		cantidadDivisionesX=cX;
		cantidadDivisionesY=cY;
	}
	public void setLongitudDivisionX(int c)
	{
		longitudDivisionX=c;
	}
	public void setLongitudDivisionY(int c)
	{
		longitudDivisionY=c;
	}	
	public void setLongitudDivisionXY(int cX,int cY)
	{
		longitudDivisionX=cX;
		longitudDivisionY=cY;
	}
	public void dibujarDivisiones()
	{
		dibujarDivisionesX();
		dibujarDivisionesY();
	}
	public void dibujarDivisionesY()
	{
		int longDiv=(int)(longitudY/cantidadDivisionesY);
		int xInicio=(int)(x-(longitudDivisionY/2));
		int xFin=xInicio+longitudDivisionY;
		int posY=y-longitudY;
		for (int i=0;i<cantidadDivisionesY;i++)
		{
			area.drawLine(xInicio,posY,xFin,posY);
			posY+=longDiv;
		}		
	}
	public void dibujarDivisionesX()
	{
		int longDiv=(int)(longitudX/cantidadDivisionesX);
		int yInicio=(int)(y-(longitudDivisionX/2));
		int yFin=yInicio+longitudDivisionX;
		int posX=x+longDiv;
		for (int i=0;i<cantidadDivisionesX-1;i++)
		{
			area.drawLine(posX,yInicio,posX,yFin);
			posX+=longDiv;
		}
		area.drawLine(longitudX+x,yInicio,longitudX+x,yFin);
	}
	public void dibujarParalelos()
	{
		int longDiv=(int)(longitudX/cantidadDivisionesX);
		int yInicio=(int)(y-(longitudDivisionX/2));
		int yFin=yInicio+longitudDivisionX;
		int posX=x+longDiv;
		for (int i=0;i<cantidadDivisionesX-1;i++)
		{
			area.drawLine(posX,y-longitudY,posX,y);
			posX+=longDiv;
		}
		area.drawLine(longitudX+x,y-longitudY,longitudX+x,y);		
	}
	public void dibujarMeridianos()
	{
		int longDiv=(int)(longitudY/cantidadDivisionesY);
		int xInicio=(int)(x-(longitudDivisionY/2));
		int xFin=xInicio+longitudDivisionY;
		int posY=y-longitudY;
		for (int i=0;i<cantidadDivisionesY;i++)
		{
			area.drawLine(x,posY,x+longitudX,posY);
			posY+=longDiv;
		}		
	}
	public void dibujarDivisiones(int lX,int lY)
	{
		longitudDivisionX=lX;
		longitudDivisionY=lY;
		dibujarDivisiones();
	}
	public void dibujarDivisiones(int lX,int lY,int cX,int cY)
	{
		longitudDivisionX=lX;
		longitudDivisionY=lY;
		cantidadDivisionesX=cX;
		cantidadDivisionesY=cY;		
		dibujarDivisiones();
	}
	public void dibujarGraficoBarra()
	{
		dibujarEjes();
		dibujarDivisiones();
	}
	public void setBarras(int valores[][])
	{
		valorMaximoY=calcularMaximoY(valores);
		
		barras=new int[valores.length][valores[0].length];
		
		cargarBarraX(valores);
		cargarBarraY(valores,valorMaximoY);
		cantidadDivisionesX=valores.length;
	}
	public void setBarras(int valores[][],int cantidadDivisionesY)
	{
		setBarras(valores);
		this.cantidadDivisionesY=cantidadDivisionesY;
	}	
	public void dibujarBarras(int valores[][])
	{
		setBarras(valores);
		dibujarBarras();
	}
	public void dibujarBarras(int valores[][],String lX[],String lY[])
	{
		labelX=lX;
		labelY=lY;
		dibujarBarras(valores);
		dibujarLabels();
	}
	
	public void dibujarEsperado(int esperado)
	{
		Color colorAux=area.getColor();
		area.setColor(GestionVisual.GRANATE);
		int posY=y-(esperado*longitudY)/valorMaximoY;
		area.drawLine(x-15,posY,x+longitudX+15,posY);
		area.drawString(""+esperado,x+longitudX+1,posY);
		area.setColor(colorAux);
	}
	
	public void dibujarBarras()
	{
		Color colorAux=area.getColor();
		int posColor=0;
		int i=0;
		for (i=0;i<barras.length-1;i++)
		{
			if (posColor==colores.length) posColor=0;
			area.setColor(colores[posColor]);
			area.fillRect(barras[i][0],y-barras[i][1],longitudX/cantidadDivisionesX,barras[i][1]);
			posColor++;
		}
		if (posColor==colores.length) posColor=0;
		area.setColor(colores[posColor]);		
		area.fillRect(barras[i][0],y-barras[i][1],longitudX-barras[i][0]+x,barras[i][1]);
		area.setColor(colorAux);
	}
	public void dibujarBordesBarras()
	{
		int i=0;
		for (i=0;i<barras.length-1;i++)
		{
			area.drawRect(barras[i][0],y-barras[i][1],longitudX/cantidadDivisionesX,barras[i][1]);
		}
		area.drawRect(barras[i][0],y-barras[i][1],longitudX-barras[i][0]+x,barras[i][1]);
	}
	public void dibujarLabels()
	{
		dibujarLabelX();
		dibujarLabelY();
	}
	public void dibujarLabels(String lX[],String lY[])
	{
		labelX=lX;
		labelY=lY;
		dibujarLabelX();
		dibujarLabelY();		
	}
	public void dibujarLabelX()
	{
		cantidadDivisionesX=labelX.length;
		int longDiv=(int)(longitudX/cantidadDivisionesX);
		int posX=x;
		int i=0;
		int aux=0;
		int a=0;
		for (i=0;i<cantidadDivisionesX;i++)
		{
			aux=labelX[i].length()/2;
			if (i%2==0)
				a=yTexto;
			else
				a=y-longitudY-10;
			area.drawString(labelX[i],posX+aux,a);
			posX+=longDiv;
		}
	}
	public void dibujarLabelXAvance(String labelX[])
	{
		cantidadDivisionesX=labelX.length;
		int longDiv=(int)(longitudX/cantidadDivisionesX);
		int posX=x+longDiv;
		int i=0;
		int aux=0;
		area.drawString("0",x,yTexto);
		for (i=0;i<labelX.length-1;i++)
		{
			aux=labelX[i].length()/2;
			area.drawString(labelX[i],posX+aux,yTexto);
			posX+=longDiv;
		}
		area.drawString(labelX[i],x+longitudX,yTexto);
	}	
	public void dibujarLabelX(String lX[])
	{
		labelX=lX;
		dibujarLabelX();
	}
	public void dibujarLabelY()
	{
		cantidadDivisionesY=labelY.length;
		int longDiv=(int)(longitudY/cantidadDivisionesY);
		int posY=y-longitudY;
		area.drawString("            0",xTexto,y);
		for (int i=(labelY.length-1);i>=0;i--)
		{
			area.drawString(labelY[i],xTexto,posY);
			posY+=longDiv;
		}		
	}
	public void dibujarLabelY(String lY[])
	{
		labelY=lY;
		dibujarLabelY();
	}
	public void dibujarLabelYAvance(String labelY[])
	{
		cantidadDivisionesY=labelY.length;
		int longDiv=(int)(longitudY/cantidadDivisionesY);
		int posY=y-longitudY;
		area.drawString("            0",xTexto,y);
		for (int i=(labelY.length-1);i>=0;i--)
		{
			area.drawString(labelY[i],xTexto,posY);
			posY+=longDiv;
		}				
	}
	public int calcularMaximoY(int valores[][])
	{
		int maximo=valores[0][1];
		for (int i=1;i<valores.length;i++)
		{
			if (valores[i][1]>maximo) maximo=valores[i][1];
		}
		return maximo;
	}
	
	public void cargarBarraX(int valores[][])
	{
		int pos=x;
		int porcion=longitudX/valores.length;
		for (int i=0;i<valores.length;i++)
		{
			barras[i][0]=pos;
			pos+=porcion;
		}
	}
	public void cargarBarraY(int valores[][],int maximo)
	{
		if (maximo==0) maximo=1;
		for (int i=0;i<valores.length;i++)
		{
			barras[i][1]=(valores[i][1]*longitudY)/maximo;
		}
	}
	public void setLabelXY(String lX[],String lY[])
	{
		labelX=lX;
		labelY=lY;
	}
	public void setLabelX(String lX[])
	{
		labelX=lX;
	}
	public void setLabelY(String lY[])
	{
		labelY=lY;
	}	
	public void dibujarTitulo(String ti)
	{
		titulo=ti;
		dibujarTitulo();
	}
	public void dibujarTitulo(String ti,boolean proc)
	{
		titulo=ti;
		if (proc) calcularXYTitulo();
		dibujarTitulo();
	}
	public void dibujarTitulo()
	{
		Font aux=area.getFont();
		area.setFont(fuenteTitulo);
		area.drawString(titulo,xTitulo,yTitulo);
		area.setFont(aux);
	}
	public void calcularXYTitulo()
	{
		xTitulo=(ancho/2)-(titulo.length()/2);
		yTitulo=20;
	}
	public void dibujarTitulo(String ti, int xT,int yT)
	{
		titulo=ti;
		xTitulo=xT;
		yTitulo=yT;
		dibujarTitulo();
	}
	public void setTitulo(String ti)
	{
		this.titulo=ti;
	}
	public void setAncho(int a)
	{
		ancho=a;
	}
	public void setAlto(int a)
	{
		alto=a;
	}
	public void setAnchoAlto(int an,int al)
	{
		ancho=an;
		alto=al;
	}
	public void dibujarGraficoTorta(int valores[])
	{
		this.valores=valores;
		int angulo=0;
		Color colorAux=area.getColor();
		int posColor=0;		
		for (int i=0;i<valores.length;i++)
		{
			if (colores.length==posColor) posColor=0;
			area.setColor(colores[posColor]);
			area.fillArc(x,y,diametro,diametro,angulo,valores[i]);
			angulo+=valores[i];
			posColor++;
		}
		area.setColor(colorAux);
	}
	public void dibujarBordesInternosGraficoTorta(int valoresIniciales[])
	{
		int valores[]=procesarValoresRecibidosTorta(valoresIniciales);
		x=x+diametro/2;
		y=y+diametro/2;
		int angulo=0;
		int xAngulo=0;
		int yAngulo=0;
		for (int i=0;i<valores.length;i++)
		{
			yAngulo=(int)(Math.sin(Math.toRadians(angulo))*(diametro/2));
			xAngulo=(int)(Math.cos(Math.toRadians(angulo))*(diametro/2));
			area.drawLine(x,y,xAngulo+x,y-yAngulo);
			angulo+=valores[i];
		}		
	}
	public void dibujarTitulosBarras(String titulos[])
	{
		area.drawString(titulos[0],longitudX-14,yTexto+20);
		area.drawString(titulos[1],xTexto,y-longitudY-22);
	}
	public void dibujarBordeGraficoTorta()
	{
		area.drawOval(x,y,diametro,diametro);
	}
	public void dibujarLabelTorta(String l[])
	{
		label=l;
		dibujarLabelTorta();
	}
	public void dibujarLabelTorta()
	{
		Color colorAux=area.getColor();
		int posColor=0;		
		int posY=yLabel;
		for (int i=0;i<label.length;i++)
		{
			if (colores.length==posColor) posColor=0;
			area.setColor(colores[posColor]);			
			area.drawString(label[i],xLabel,yLabel);
			yLabel+=(int)(area.getFont().getSize()*1.5);
			posColor++;
		}
		area.setColor(colorAux);
	}
	public void dibujarGraficoTorta(int valores[],String l[],boolean procesar)
	{
		label=l;
		int valoresFinales[]=null;
		if(procesar) 
		{
			valoresFinales=procesarValoresRecibidosTorta(valores);
		} else
		{
			valoresFinales=valores;
		}
		dibujarGraficoTorta(valoresFinales);
		dibujarLabelTorta();
	}
	public int[] procesarValoresRecibidosTorta(int valores[])
	{
		int total=0;
		for (int i=0;i<valores.length;i++)
		{
			total+=valores[i];
		}
		if (total==0) total=1;
		for (int i=0;i<valores.length-1;i++)
		{
			valores[i]=(valores[i]*360)/total;
		}
		total=0;
		int i;
		for (i=0;i<valores.length-1;i++)
		{
			total+=valores[i];
		}
		valores[i]=360-total;
		return valores;
	}
	public void setLabel(String l[])
	{
		label=l;
	}
	public void dibujarGraficoTorta(int valores[],int r)
	{
		diametro=r;
		dibujarGraficoTorta(valores);
	}
	public void setDiametro(int r)
	{
		diametro=r;
	}
	public void setXYLabel(int x,int y)
	{
		xLabel=x;
		yLabel=y;
	}
	public void setAvance(int avance[][][],boolean proc,int tG)
	{
		setAvance(avance);	
		tipoGrafico=tG;
		if (proc)
		{
			procesarAvance(avance);
		}
	}
	public void setAvance(int avance[][][])
	{
		this.avance=avance;
	}
	public void procesarAvance(int arregloEntero[][][])
	{
		int mayor=getMayor(arregloEntero);
		int posColor=0;
		for (int i=0;i<arregloEntero.length;i++)
		{
			concavo=true;
			area.setColor(colores[posColor]);
			if (posColor==colores.length-1) posColor=0;
			else posColor++;
			procesarProporcionalidad(arregloEntero[i],mayor);
			
			if (tipoGrafico!=0&&tipoGrafico!=3)
			for (int j=0;j<arregloEntero[i].length-1;j++)
			{
				switch (tipoGrafico)
				{
					case 1:area.drawLine(x+arregloEntero[i][j][0]-i,y-arregloEntero[i][j][1]-i,x+arregloEntero[i][j+1][0]-i,y-arregloEntero[i][j+1][1]-i);break;
					//case 2:dibujarArcoAvance(arregloEntero[i][j][0],arregloEntero[i][j][1],arregloEntero[i][j+1][0],arregloEntero[i][j+1][1],i);break;
					case 2:area.drawLine(x+arregloEntero[i][j][0]-i,y-arregloEntero[i][j][1]-i,x+arregloEntero[i][j+1][0]-i,y-arregloEntero[i][j+1][1]-i);break;
				}
			}
			if (tipoGrafico==3)
			{
				dibujarInterpolacion(arregloEntero[i]);				
			}
			if(tipoGrafico==0||tipoGrafico==2)
			for (int j=0;j<arregloEntero[i].length;j++)
			{
				area.drawOval(x+arregloEntero[i][j][0]-2-i,y-arregloEntero[i][j][1]-2-i,4,4);
			}
			
/*			int retorno[]=new int[longitudX];
			for (int k=0;k<retorno.length;k++)
			{
				retorno[k]=procedimientoLagrange(k,arregloEntero[i]);	
			}	
			for (int j=0;j<retorno.length;j++)
			{
				area.drawLine(x+j,y-retorno[j],x+j,y-retorno[j]);
			}
*/		}
		area.setColor(Color.black);
	}
	public void dibujarArcoAvance(int x1,int y1,int x2,int y2,int i)
	{
		//area.fillArc(x+x1,y-y1,200,200,
		//		       (int)Math.toRadians(20),(int)Math.toRadians(70));
		if (y1==y2)
		{
			area.drawLine(x+x1-i,y-y1-i,x+x2-i,y-y2-i);
			
		} else
		if (y1>y2)
		{
			area.setColor(Color.yellow);
			area.drawArc(x+(x2-x1),y-y1,(x2-x1)*2,(y1-y2)*2,//y1*2,
				       (int)Math.toRadians(0),(int)Math.toRadians(90));
			//area.drawRect(x+(x2-x1),y-y1,(x2-x1)*2,(y1-y2)*2);
			return;
			
		} 
		if (concavo)
		{
			area.setColor(Color.white);
			area.drawArc(x+x1,y-y2,(x2-x1)*2,(y2-y1)*2,//y2*2,
				       (int)Math.toRadians(90),(int)Math.toRadians(180));			
			//area.drawRect(x+x1,y-y2,(x2-x1)*2,(y2-y1)*2);
		} else
		{
			area.setColor(Color.pink);
			area.drawArc(x+(x2-x1),y-(y2-y1)*2-y1,(x2-x1)*2,(y2-y1)*2,//y2*2,
				       (int)Math.toRadians(-90),(int)Math.toRadians(0));			
			//area.drawRect(x+(x2-x1),y-(y2-y1)*2-y1,(x2-x1)*2,(y2-y1)*2);
		}
		concavo=!concavo;
	}
	public void procesarProporcionalidad(int arregloOrigen[][],int mayor)
	{
		for (int i=0;i<arregloOrigen.length;i++)
		{
			arregloOrigen[i][0]=(arregloOrigen[i][0]*longitudX)/arregloOrigen[arregloOrigen.length-1][0];
			arregloOrigen[i][1]=(arregloOrigen[i][1]*longitudY)/mayor;
		}
	}
	public static int getMayor(int a[][][])
	{
		int mayor=a[0][0][1];
		for (int i=0;i<a.length;i++)
		{
			for (int j=0;j<a[i].length;j++)
			{
					if (mayor<a[i][j][1])
					{
						mayor=a[i][j][1];
					}					
			}
		}	
		if (mayor==0) return 1;
		return mayor;
	}
	
	public void dibujarInterpolacion(int valores[][])
	{
		int aux[][];
		int valoresPintado[];
		for (int i=0;i<valores.length-2;i++)
		{
			aux=new int[3][2];
			for (int j=0;j<aux.length;j++)
			{
				aux[j][0]=valores[i+j][0];
				aux[j][1]=valores[i+j][1];
			}
			valoresPintado=new int[aux[2][0]-aux[0][0]];
			int pos=calcularPosicionMedio(valoresPintado,aux,valores[i+1][0]);
			//System.out.println("pos: "+pos);
			cargarValoresPintadoInterpolacion(valoresPintado,aux);
//			if (i==0)
//			{
				for (int k=0;k<valoresPintado.length;k++)
				{
					area.drawLine(k+x+valores[i][0],y-valoresPintado[k],k+x+valores[i][0],y-valoresPintado[k]);
				} 
//			} else
/*			{
				pos=aux[1][0];
				for (int k=valores[i+1][0];k<valoresPintado.length;k++)
				{
					area.drawLine(x+k,y-valoresPintado[pos],x+k,y-valoresPintado[pos]);
					pos++;
				} 				
			}
*/		}
	}
	
	public int calcularPosicionMedio(int valoresPintado[],int valores[][],int valor)
	{
		for (int i=0;i<valoresPintado.length;i++)
		{
			for (int j=0;j<valores.length;j++)
			{
				if (valores[j][0]==valor)
				return i;
			}
		}
		return 0;
	}
	
	public void cargarValoresPintadoInterpolacion(int valoresPintado[],int valores[][])
	{
		for (int i=0;i<valoresPintado.length;i++)
		{
			valoresPintado[i]=procedimientoLagrange(i,valores);
		}
	}
	
	public int[] ejecutarLagrange(int valores[][])
	{
		int retorno[]=new int[longitudX];
		for (int i=0;i<retorno.length;i++)
		{
			retorno[i]=procedimientoLagrange(i,valores);	
		}	
		return retorno;
	}
	
	private int procedimientoLagrange(int valorBuscado,int valores[][])
	{
		double resultado=0;
		double aux=0;
		int i;
		for (i=0;i<valores.length;i++)
		{
			aux=1;
			for (int j=0;j<valores.length;j++)
			{
				if (j!=i)
				{
					aux=aux*(valorBuscado-valores[j][0])/(valores[i][0]-valores[j][0]);
				}
			}
			resultado+=valores[i][1]*aux;
			
		}
		return (int)resultado;
	}	
	public void mostrarNombres(String nombres[],int x,int y)
	{
		int posY=y;
		int posColor=0;		
		for (int i=0;i<nombres.length;i++)
		{
			area.setColor(colores[posColor]);
			if (posColor==colores.length-1) posColor=0;
			else posColor++;
			area.drawString(nombres[i],x,posY);
			posY+=15;
		}
		area.setColor(Color.black);
	}
	public int[] getValores()
	{
		return valores;
	}
}