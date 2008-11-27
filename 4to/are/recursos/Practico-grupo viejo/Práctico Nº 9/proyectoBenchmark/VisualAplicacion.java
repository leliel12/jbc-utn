
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;
import javax.swing.border.*;
import javax.swing.tree.*;
/**
 *  Una clase que establece colores y fuentes para la aplicacion
 *  @version Septiembre de 2005
 */
public class VisualAplicacion implements Comparable,Serializable,Describible
{
	private String nombre,descripcion;
	private Color colorFondo;
	private Color colorBoton;
	private Color colorTextoBoton;
	private Color colorTextoItem;
	private Color colorTextoMenu;
	private Color colorTextoAuxiliar;
	private Font fuenteMenu;
	private Font fuenteItem;
	private Font fuenteTexto;
	private Font fuenteBoton;
	private Font fuenteTextField;
	private Font fuenteTextArea;
	private Date dateAlta;
	
    /** 
    *  Constructor de VisualAplicación con parametros
    *  @param n nombre del visual aplicación
    *  @param cF color de fondo de la aplicacion
    *  @param cB color de objetos tipo botón
    *  @param cTB color de texto de objetos tipo botón
    *  @param cTV color de texto de ítems
    *  @param cTM color de texto de menú
    *  @param cTP color de texto auxiliar
    *  @param fM fuente de menú
    *  @param fI fuente de ítem
    *  @param fMe fuente de texto normal
    *  @param fB fuente de botón
    *  @param fV fuente de campo de texto
    *  @param fP fuente de área de texto
    */
	public VisualAplicacion(String n,Color cF,Color cB,Color cTB,Color cTV,Color cTM,Color cTP,Font fM,Font fI,Font fMe,Font fB,Font fV,Font fP)
	{
		nombre=n;
		colorFondo=cF;
		colorBoton=cB;
		colorTextoBoton=cTB;
		colorTextoItem=cTV;
		colorTextoMenu=cTM;
		colorTextoAuxiliar=cTP;
		fuenteMenu=fM;
		fuenteItem=fI;
		fuenteTexto=fMe;
		fuenteBoton=fB;
		fuenteTextField=fV;
		fuenteTextArea=fP;
		dateAlta=new Date();
	}
	
    /** 
    *  Constructor de VisualAplicación con parametros, incluyendo su descripcion
    *  @param n nombre del visual aplicación
    *  @param des descripcion del visual aplicacion
    *  @param cF color de fondo de la aplicacion
    *  @param cB color de objetos tipo botón
    *  @param cTB color de texto de objetos tipo botón
    *  @param cTV color de texto de ítems
    *  @param cTM color de texto de menú
    *  @param cTP color de texto auxiliar
    *  @param fM fuente de menú
    *  @param fI fuente de ítem
    *  @param fMe fuente de texto normal
    *  @param fB fuente de botón
    *  @param fV fuente de campo de texto
    *  @param fP fuente de área de texto
    */
	public VisualAplicacion(String n,String des,Color cF,Color cB,Color cTB,Color cTV,Color cTM,Color cTP,Font fM,Font fI,Font fMe,Font fB,Font fV,Font fP)
	{
		this(n,cF,cB,cTB,cTV,cTM,cTP,fM,fI,fMe,fB,fV,fP);
		this.descripcion=des;
	}	
	
	/**
	 * constructor que recibe como parametro el nombre del visualAplicacion
	 * @param nombre String que es el nombre del visualAplicacion
	 */
	public VisualAplicacion(String nombre)
	{
		this.nombre=nombre;
		dateAlta=new Date();
	}
	
	/**
	 * constructor que recibe como parametro el nombre y descripcion del visualAplicacion
	 * @param nombre String que es el nombre del visualAplicacion
	 * @param descripcion String que es la descripcion del visualAplicacion
	 */
	public VisualAplicacion(String nombre,String descripcion)
	{
		this(nombre);
		this.descripcion=descripcion;
	}	

    /** 
    *  Acceso al nombre del visual aplicacion
    *  @return nombre del visual aplicación
    */	
	public String getNombre()
	{
		return nombre;
	}
	
    /** 
    *  Acceso al color de fondo de la aplicacion
    *  @return color de fondo de la aplicacion
    */	
	public Color getColorFondo()
	{
		return colorFondo;
	}
	
    /** 
    *  Acceso al color de objetos tipo botón
    *  @return color de objetos tipo botón
    */		
	public Color getColorBoton()
	{
		return colorBoton;
	}
	
    /** 
    *  Acceso al color de texto de objetos tipo botón
    *  @return color de texto de objetos tipo botón
    */		
	public Color getColorTextoBoton()
	{
		return colorTextoBoton;
	}
	
    /** 
    *  Acceso al color de texto de ítems
    *  @return color de texto de ítems
    */		
	public Color getColorTextoItem()
	{
		return colorTextoItem;
	}
	
    /** 
    *  Acceso al color de texto de menú
    *  @return color de texto de menú
    */		
	public Color getColorTextoMenu()
	{
		return colorTextoMenu;
	}
	
    /** 
    *  Acceso al color de texto auxiliar
    *  @return color de texto auxiliar
    */		
	public Color getColorTextoAuxiliar()
	{
		return colorTextoAuxiliar;
	}
	
    /** 
    *  Acceso a la fuente de menú
    *  @return Font fuente de menú
    */		
	public Font getFuenteMenu()
	{
		return fuenteMenu;
	}
	
    /** 
    *  Acceso a la fuente de ítem
    *  @return Font fuente de ítem
    */			
	public Font getFuenteItem()
	{
		return fuenteItem;
	}
	
    /** 
    *  Acceso a la fuente de texto normal
    *  @return Font fuente de texto normal
    */					
	public Font getFuenteTexto()
	{
		return fuenteTexto;
	}
	
    /** 
    *  Acceso a la fuente de botón
    *  @return Font fuente de botón
    */					
	public Font getFuenteBoton()
	{
		return fuenteBoton;
	}
	
    /** 
    *  Acceso a la fuente de campo de texto
    *  @return Font fuente de campo de texto
    */					
	public Font getFuenteTextField()
	{
		return fuenteTextField;
	}
	
    /** 
    *  Acceso a la fuente de area de texto
    *  @return Font fuente de area de texto
    */					
	public Font getFuenteTextArea()
	{
		return fuenteTextArea;
	}
	
    /** 
    *  Modifica el color de fondo de la aplicacion
    *  @param colorFondo nuevo color de fondo de la aplicacion
    */	
	public void setColorFondo(Color colorFondo)
	{
		this.colorFondo=colorFondo;
	}
	
    /** 
    *  Modifica el color de objetos tipo botón
    *  @param colorBoton nuevo color de objetos tipo botón
    */		
	public void setColorBoton(Color colorBoton)
	{
		this.colorBoton=colorBoton;
	}
	
    /** 
    *  Modifica el color de texto de objetos tipo botón
    *  @param colorTextoBoton nuevo color de texto de objetos tipo botón
    */		
	public void setColorTextoBoton(Color colorTextoBoton)
	{
		this.colorTextoBoton=colorTextoBoton;
	}
	
    /** 
    *  Modifica el color de texto de ítems
    *  @param colorTextoItem nuevo color de texto de ítems
    */		
	public void setColorTextoItem(Color colorTextoItem)
	{
		this.colorTextoItem=colorTextoItem;
	}
	
    /** 
    *  Modifica el color de texto de menú
    *  @param colorTextoMenu nuevo color de texto de menú
    */		
	public void setColorTextoMenu(Color colorTextoMenu)
	{
		this.colorTextoMenu=colorTextoMenu;
	}
	
    /** 
    *  Modifica el color de texto auxiliar
    *  @param colorTextoAuxiliar nuevo color de texto auxiliar
    */		
	public void setColorTextoAuxiliar(Color colorTextoAuxiliar)
	{
		this.colorTextoAuxiliar=colorTextoAuxiliar;
	}
	
    /** 
    *  Modifica la fuente de menú
    *  @param fuenteMenu nueva fuente de menú
    */		
	public void setFuenteMenu(Font fuenteMenu)
	{
		this.fuenteMenu=fuenteMenu;
	}
	
    /** 
    *  Modifica la fuente de ítem
    *  @param fuenteItem nueva fuente de ítem
    */			
	public void setFuenteItem(Font fuenteItem)
	{
		this.fuenteItem=fuenteItem;
	}
	
    /** 
    *  Modifica la fuente de texto normal
    *  @param fuenteTexto nueva fuente de texto normal
    */					
	public void setFuenteTexto(Font fuenteTexto)
	{
		this.fuenteTexto=fuenteTexto;
	}
	
    /** 
    *  Modifica la fuente de botón
    *  @param fuenteBoton nueva fuente de botón
    */					
	public void setFuenteBoton(Font fuenteBoton)
	{
		this.fuenteBoton=fuenteBoton;
	}
	
    /** 
    *  Modifica la fuente de campo de texto
    *  @param fuenteTextField nueva fuente de campo de texto
    */					
	public void setFuenteTextField(Font fuenteTextField)
	{
		this.fuenteTextField=fuenteTextField;
	}
	
    /** 
    *  Modifica la fuente de area de texto
    *  @param fuenteTextArea nueva fuente de area de texto
    */					
	public void setFuenteTextArea(Font fuenteTextArea)
	{
		this.fuenteTextArea=fuenteTextArea;
	}	
	
	/**
	 * Acceso a la descripcion del VisualAplicacion
	 * @return String que es la descripcion del VisualAplicacion
	 */
	public String getDescripcion()
	{
		return descripcion;
	}
	
   /**
   *  Acceso al date de creacion
   *  @return date de creacion
   */ 
   public Date getDateAlta()
   {
   		return dateAlta;
   }
   
   /**
   *  Modifica el date de creacion
   *  @param dateAlta Date que representa el date de creacion
   */ 
   public void setDateAlta(Date dateAlta)
   {
   		this.dateAlta=dateAlta;
   }    	
	
	/**
	 * Modifica la descripcion del VisualAplicacion
	 * @param descripcion nueva descripcion
	 */
	public void setDescripcion(String descripcion)
	{
		this.descripcion=descripcion;
	}	
	
    /**
    *  Redefine el método toString
    *  @return el contenido de la visual aplicación
    */	
	public String toString()
	{
		return "nombre: "+nombre+"\n"+"color fondo: "+colorFondo+"\n"+
		"color boton: "+colorBoton+"\n"+"color texto boton: "+colorTextoBoton+"\n"+
		"colorTextoItem: "+colorTextoItem+"\n"+"color texto menu: "+colorTextoMenu+"\n"+
		"color texto auxiliar: "+colorTextoAuxiliar+"\n"+"fuente menu: "+fuenteMenu+"\n"+
		"fuente item: "+fuenteItem+"\n"+"fuente texto: "+fuenteTexto+"\n"+
		"fuente boton: "+fuenteBoton+"\n"+"fuente text field: "+fuenteTextField+"\n"+
		"fuente text area: "+fuenteTextArea;
	}
	
	/**
	 * Redefine el metodo hashCode de object
	 * @return int que es el codigo del visualAplicacion
	 */
	public int hashCode()
	{
		return nombre.hashCode();
	}
	
	/**
	 * Redefine el metodo equals de Object
	 * @param o Object para comparar si se trata de un visual con el mismo codigo
	 * @return boolean que indica si se trata del mismo VisualAplicacion
	 */
	public boolean equals(Object o)
	{
		if (o.getClass()!=VisualAplicacion.class) return false;
		VisualAplicacion u=(VisualAplicacion)o;
		return this.nombre.equals(u.nombre);
	}
	
	/** Define el metodo compareTo de Comparable
	 * @param o Object para efectuar la comparacion
	 * @return int que indica el resultado de la comparacion
	 */
	public int compareTo(Object o)
	{
		if (o.getClass()!=VisualAplicacion.class) return -1;
		VisualAplicacion u=(VisualAplicacion)o;
		return this.nombre.compareTo(u.nombre);
	}
	
	/**
	 * Actualiza la visual aplicacion del contenedor con el VisualAplicacion actual
	 * @param contenedor Container cuya VisualAplicacion sera actualizado con el VisualAplicacion actual
	 */		
	public void actualizar(Container contenedor)
	{
		Component componentes[]=contenedor.getComponents();
		if (contenedor instanceof JFrame)
		{
			if (((JFrame)contenedor).isResizable())
			{
				((JFrame)contenedor).setResizable(false);
			}
			((JFrame)contenedor).setEnabled(true);
			GestionVisual.centrar(contenedor);
			((JFrame)contenedor).setIconImage(GestionVisual.icono);			
		} else
		if (contenedor instanceof JDialog)
		{
			if (((JDialog)contenedor).isResizable())
			{
				((JDialog)contenedor).setResizable(false);
			}
			((JDialog)contenedor).setModal(true);
			GestionVisual.centrar(contenedor);
		} else
		if (contenedor instanceof Frame)
		{
			if (((Frame)contenedor).isResizable())
			{
				((Frame)contenedor).setResizable(false);
			}
			((Frame)contenedor).setEnabled(true);
			GestionVisual.centrar(contenedor);
			((Frame)contenedor).setIconImage(GestionVisual.icono);			
		} else		
		if (contenedor instanceof JInternalFrame)
		{
			((JInternalFrame)contenedor).setClosable(true);
			((JInternalFrame)contenedor).setIconifiable(true);
			((JInternalFrame)contenedor).setFrameIcon(new ImageIcon(GestionVisual.pathIcono));
		}
		for (int i=0;i<componentes.length;i++)
		{
			if (componentes[i] instanceof Container)
			{
				setFormatoContainer((Container)componentes[i]);
				actualizar((Container)componentes[i]);
			} 
			if (componentes[i] instanceof JMenu)
				procesarMenu((JMenu)componentes[i]);
			if (componentes[i] instanceof JPanel)
			{
				setFormatoPanel((JPanel)componentes[i]);
			}						
			if (componentes[i] instanceof JButton)
			{
				setFormatoBoton((JButton)componentes[i]);
			}			
			if (componentes[i] instanceof JTextField)
			{
				setFormatoTextField((JTextField)componentes[i]);
			} 
			if (componentes[i] instanceof JComboBox)
			{
				setFormatoComboBox((JComboBox)componentes[i]);
			}
			if (componentes[i] instanceof JTable)
			{
				setFormatoTabla((JTable)componentes[i]);
			}			
			if (componentes[i] instanceof JLabel)
			{
				setFormatoLabel((JLabel)componentes[i]);
			}			
			if (componentes[i] instanceof JTabbedPane)
			{
				setFormatoTabbed((JTabbedPane)componentes[i]);
			}						
			if (componentes[i] instanceof JTextArea)
			{
				setFormatoTextArea((JTextArea)componentes[i]);
			}						
			if (componentes[i] instanceof JSlider)
			{
				setFormatoSlider((JSlider)componentes[i]);
			}		
			if (componentes[i] instanceof JList)
			{
				setFormatoLista((JList)componentes[i]);
			}		
			if (componentes[i] instanceof JCheckBox)
			{
				setFormatoCheckBox((JCheckBox)componentes[i]);
			}		
			if (componentes[i] instanceof JTree)
			{
				setFormatoTree((JTree)componentes[i]);
			}								
			if (componentes[i] instanceof JSplitPane)
			{
				setFormatoSplit((JSplitPane)componentes[i]);
			}											
			if (componentes[i] instanceof JComponent)
			{
				((JComponent)componentes[i]).updateUI();
			}
		}
	}
	
      /**
       * Modifica la visualizacion de un contenedor
       * @param i contenedor que sera visualmente modificado
       */	
	public void setFormatoContainer(Container i)
	{
		i.setBackground(getColorFondo());
	}

      /**
       * Modifica la visualizacion de un Panel
       * @param i Panel que sera visualmente modificado
       */		
	public void setFormatoPanel(JPanel i)
	{
		i.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		i.setBackground(getColorFondo());
		if (i.getBorder()instanceof TitledBorder)
		{
			((TitledBorder)i.getBorder()).setTitleColor(getColorTextoBoton());
			((TitledBorder)i.getBorder()).setTitleFont(getFuenteBoton());
			((TitledBorder)i.getBorder()).setTitleColor(getColorTextoBoton());
			((TitledBorder)i.getBorder()).setTitleFont(getFuenteBoton());		
		}		
	}
	
      /**
       * Modifica la visualizacion de un Item de menu
       * @param i Item de menu que sera visualmente modificado
       */			
	public void setFormatoMenuItem(JMenuItem i)
	{
		i.setCursor(new Cursor(Cursor.HAND_CURSOR));
		i.setFont(getFuenteItem());
		i.setBackground(getColorFondo());
		i.setForeground(getColorTextoItem());
	}
	
      /**
       * Modifica la visualizacion de un menu
       * @param i menu que sera visualmente modificado
       */		
	public void setFormatoMenu(JMenu i)
	{
		i.setCursor(new Cursor(Cursor.HAND_CURSOR));
		i.setFont(getFuenteMenu());
		i.setBackground(getColorFondo());
		i.setForeground(getColorTextoMenu());
	}	

      /**
       * Modifica la visualizacion de un boton
       * @param i boton que sera visualmente modificado
       */		
	public void setFormatoBoton(JButton i)
	{
		i.setCursor(new Cursor(Cursor.HAND_CURSOR));
		i.setBackground(getColorBoton());
		i.setForeground(getColorTextoBoton());
		i.setFont(getFuenteBoton());
	}
	
      /**
       * Modifica la visualizacion de un campo de texto
       * @param i campo de texto que sera visualmente modificado
       */			
	public void setFormatoTextField(JTextField i)
	{
		i.setForeground(getColorTextoAuxiliar());
		i.setFont(getFuenteTextField());		
	}	
	
      /**
       * Modifica la visualizacion de un combo
       * @param i combo que sera visualmente modificado
       */			
	public void setFormatoComboBox(JComboBox i)
	{
		i.setCursor(new Cursor(Cursor.HAND_CURSOR));
		i.setBackground(getColorBoton());
		i.setForeground(getColorTextoBoton());
		i.setFont(getFuenteBoton());
	}	
	
      /**
       * Modifica la visualizacion de una etiqueta
       * @param i etiqueta que sera visualmente modificado
       */			
	public void setFormatoLabel(JLabel i)
	{
		i.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		i.setFont(getFuenteTexto());
		i.setBackground(getColorFondo());
		i.setForeground(getColorTextoBoton());		
	}
	
      /**
       * Modifica la visualizacion de un area de texto
       * @param i JTextArea area de texto de menu que sera visualmente modificado
       */			
	public void setFormatoTextArea(JTextArea i)
	{
		//i.setBackground(Color.WHITE);
		i.setForeground(getColorTextoBoton());
		i.setBackground(getColorBoton());
		i.setFont(getFuenteTexto());				
	}	
	
      /**
       * Modifica la visualizacion de un slider
       * @param i slider que sera visualmente modificado
       */			
	public void setFormatoSlider(JSlider i)
	{
		i.setCursor(new Cursor(Cursor.HAND_CURSOR));
		if (i.getBorder()instanceof TitledBorder)
		{
			((TitledBorder)i.getBorder()).setTitleColor(getColorTextoBoton());
			((TitledBorder)i.getBorder()).setTitleFont(getFuenteBoton());
			((TitledBorder)i.getBorder()).setTitleColor(getColorTextoBoton());
			((TitledBorder)i.getBorder()).setTitleFont(getFuenteBoton());		
		}
	}	

      /**
       * Modifica la visualizacion de una lista
       * @param i lista que sera visualmente modificado
       */	
	public void setFormatoLista(JList i)	
	{
		i.setCursor(new Cursor(Cursor.HAND_CURSOR));
		i.setBackground(getColorBoton());
		i.setForeground(getColorTextoBoton());
	}

      /**
       * Modifica la visualizacion de un checkBox
       * @param i checkBox que sera visualmente modificado
       */	
	public void setFormatoCheckBox(JCheckBox i)
	{
		i.setCursor(new Cursor(Cursor.HAND_CURSOR));
		i.setBackground(getColorFondo());
		i.setForeground(getColorTextoBoton());
		i.setFont(getFuenteBoton());		
	}		
	
      /**
       * Modifica la visualizacion de un jtree
       * @param i jtree que sera visualmente modificado
       */	
	public void setFormatoTree(JTree i)
	{
		i.setCursor(new Cursor(Cursor.HAND_CURSOR));
		//i.setBackground(getColorFondo());
		i.setBackground(Color.white);
		i.setForeground(getColorTextoBoton());
		i.setFont(getFuenteBoton());
		GestionVisual.rendender.setBackground(getColorFondo());
		i.setCellRenderer(GestionVisual.rendender);
		i.setEnabled(true);
	}	
	
      /**
       * Modifica la visualizacion de un splitPane
       * @param i splitPane que sera visualmente modificado
       */	
	public void setFormatoSplit(JSplitPane i)
	{
		i.setBackground(getColorFondo());
		i.setForeground(getColorTextoBoton());
		i.setFont(getFuenteBoton());
/*		if (i.getOrientation()==javax.swing.JSplitPane.VERTICAL_SPLIT)
			if (i.isEnabled()) 
				i.setCursor(new Cursor(Cursor.E_RESIZE_CURSOR));
		else
			if (i.isEnabled()) 
				i.setCursor(new Cursor(Cursor.N_RESIZE_CURSOR));
*/		if (i.getDividerSize()>=10) i.setDividerSize(8);
	}
	
      /**
       * Modifica la visualizacion de un tabbedPane
       * @param i tabbedPane que sera visualmente modificado
       */	
	public void setFormatoTabbed(JTabbedPane i)
	{
		i.setCursor(new Cursor(Cursor.HAND_CURSOR));
		i.setBackground(getColorFondo());
		i.setForeground(getColorTextoBoton());
	}
	
	/**
	 * Modifica la visualizacion de una tabla
	 * @param i tabla a ser actualizada visualmente
	 */
	public void setFormatoTabla(JTable i)
	{
		i.setEnabled(false);
	}
	
	public void procesarMenu(JMenu menu)
	{
		setFormatoMenuItem(menu);
		Component items[]=(menu.getMenuComponents());
		for (int j=0;j<items.length;j++)
		{
			try
			{	if (items[j] instanceof JMenu)
					procesarMenu((JMenu)items[j]);
				if (items[j] instanceof JMenuItem)
					setFormatoMenuItem((JMenuItem)items[j]);
			} catch (Exception qq) {;}
		}
	}
}