/*
 * VentanaConsola.java
 *
 * Created on 17 de julio de 2006, 01:30 PM
 */

/**
 *
 * @author  pas
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.table.*;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class VentanaConsola extends javax.swing.JFrame {
    
	private DefaultTableModel modeloTablaPruebas,modeloTablaDetalle,modeloTablaInfo,modeloTabla1,modeloTabla2,modeloTabla3,modeloTabla4,modeloTabla5;    
	private DefaultListModel modeloLista;

	private GestorConsola gestor;

	private DialogoDescripcion dialogoDescripcion;

	/** Creates new form VentanaConsola */
    public VentanaConsola() {
        initComponents();
        setearFrame();
		setearModelos();
		setearTablas();
		setearLista();
		completarCombos();
		configuracionInicial();
		crearDialogos();
		crearGestor();
    }

	private void completarCombos()
	{
		completarComboBarrasInfo();
		completarComboTortaInfo();
		
		completarComboBarrasPrueba(comboBarras2);
		completarComboBarrasPrueba(comboBarras3);
		completarComboBarrasPrueba(comboBarras4);
		completarComboBarrasPrueba(comboBarras5);
		completarComboBarrasPrueba(comboBarras7);

		completarComboTortaPrueba(comboTorta2);
		completarComboTortaPrueba(comboTorta3);
		completarComboTortaPrueba(comboTorta4);
		completarComboTortaPrueba(comboTorta5);
		completarComboTortaPrueba(comboTorta7);
	}

	private void completarComboBarrasInfo()
	{
		comboBarrasInfo.addItem("sist. operativos");
		comboBarrasInfo.addItem("estruct. de archivo");
		comboBarrasInfo.addItem("cantidad de pruebas");
	}

	private void completarComboTortaInfo()
	{
		comboTortaInfo.addItem("sist. operativos");
		comboTortaInfo.addItem("estruct. de archivo");
	}

	private void completarComboTortaPrueba(JComboBox combo)
	{
		combo.addItem("tiempo total");
		combo.addItem("tiempo total A");
		combo.addItem("tiempo total B");
	}

	private void completarComboBarrasPrueba(JComboBox combo)
	{
		combo.addItem("reg/ms");
		combo.addItem("reg/ms A");
		combo.addItem("reg/ms B");
		combo.addItem("cantidad reg");
		combo.addItem("cantidad reg A");
		combo.addItem("cantidad reg B");
		combo.addItem("longitud reg");
		combo.addItem("longitud reg A");
		combo.addItem("longitud reg B");
		combo.addItem("total reg");
		combo.addItem("total reg A");
		combo.addItem("total reg B");
	}

	private void actionTorta(JComboBox combo,int pos)
	{
		gestor.graficarTortaPrueba(""+combo.getSelectedItem(),pos);
	}

	private void actionBarras(JComboBox combo,int pos)
	{
		gestor.graficarBarrasPrueba(""+combo.getSelectedItem(),pos);
	}

	private void actionDetallePrueba(int pos)
	{
		String aux=gestor.getDetallePrueba(pos);
		if (aux==null)
		{
			mostrarMensaje("No hay pruebas cargadas");
			return;	
		}
		mostrarDetalle(tabbedPane.getTitleAt(pos),aux);
	}

	private void actionDetalleInfo()
	{
		String aux=gestor.getDetalleBenchmarks();
		if (aux==null)
		{
			mostrarMensaje("No hay benchmarks cargados");
			return;	
		}
		mostrarDetalle("Benchmarks cargados",aux);
	}

	private void mostrarDetalle(String titulo, String detalle)
	{
		DialogoInforme dialogo=new DialogoInforme(titulo,detalle);
		dialogo.setVisible(true);		
	}

	private void actionBarrasInfo()
	{
		gestor.graficarBarrasInfo(""+comboBarrasInfo.getSelectedItem());
	}

	private void actionTortaInfo()
	{
		gestor.graficarTortaInfo(""+comboTortaInfo.getSelectedItem());
	}

	private void configuracionInicial()
	{
		modeloTablaDetalle.addRow(new Object[]{"Creado",""});
		modeloTablaDetalle.addRow(new Object[]{"Nombre SO",""});
		modeloTablaDetalle.addRow(new Object[]{"Arquitectura SO",""});
		modeloTablaDetalle.addRow(new Object[]{"Version SO",""});
		modeloTablaDetalle.addRow(new Object[]{"Usuario SO",""});
		modeloTablaDetalle.addRow(new Object[]{"Estructura archivos",""});

   		modeloTablaPruebas.addRow(new Object[]{""+tabbedPane.getTitleAt(1),"Archivo A","","","","","","","","",""});
   		modeloTablaPruebas.addRow(new Object[]{""+tabbedPane.getTitleAt(1),"Archivo B","","","","","","","","",""});
		modeloTablaPruebas.addRow(new Object[]{""+tabbedPane.getTitleAt(2),"Archivo A","","","","","","","","",""});
		modeloTablaPruebas.addRow(new Object[]{""+tabbedPane.getTitleAt(2),"Archivo B","","","","","","","","",""});
		modeloTablaPruebas.addRow(new Object[]{""+tabbedPane.getTitleAt(3),"Archivo A","","","","","","","","",""});
		modeloTablaPruebas.addRow(new Object[]{""+tabbedPane.getTitleAt(3),"Archivo B","","","","","","","","",""});
		modeloTablaPruebas.addRow(new Object[]{""+tabbedPane.getTitleAt(4),"Archivo A","","","","","","","","",""});
		modeloTablaPruebas.addRow(new Object[]{""+tabbedPane.getTitleAt(4),"Archivo B","","","","","","","","",""});
		modeloTablaPruebas.addRow(new Object[]{""+tabbedPane.getTitleAt(5),"Archivo A","","","","","","","","",""});
		modeloTablaPruebas.addRow(new Object[]{""+tabbedPane.getTitleAt(5),"Archivo B","","","","","","","","",""});
	}
	
	private void crearGestor()
	{
		gestor=new GestorConsola(this);
	}

	private void setearLista()
	{
		modeloLista=new DefaultListModel();
		lista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		lista.setModel(modeloLista);
	}

	public void setVisible(boolean visible)
	{
		super.setVisible(visible);
		if (visible)
			mostrarDescripcion();
	}

	private void mostrarDescripcion()
	{
		dialogoDescripcion.setVisible(true);
	}

	private void crearDialogos()
	{
		dialogoDescripcion=new DialogoDescripcion("recursos/texto/descripcionConsola.txt");
	}

	private void setearModelos()
	{
		modeloTablaInfo=new DefaultTableModel();		
		modeloTabla1=new DefaultTableModel();
		modeloTabla2=new DefaultTableModel();
		modeloTabla3=new DefaultTableModel();
		modeloTabla4=new DefaultTableModel();
		modeloTabla5=new DefaultTableModel();
		modeloTablaPruebas=new DefaultTableModel();
		modeloTablaDetalle=new DefaultTableModel();
		tablaInfo.setModel(modeloTablaInfo);
		tabla1.setModel(modeloTabla1);
		tabla2.setModel(modeloTabla2);
		tabla3.setModel(modeloTabla3);
		tabla4.setModel(modeloTabla4);
		tabla5.setModel(modeloTabla5);
		tablaPruebas.setModel(modeloTablaPruebas);
		tablaDetalle.setModel(modeloTablaDetalle);
	}

    private void setearTablas()
    {
    	modeloTablaDetalle.setColumnIdentifiers(new String[]{"Item","Detalle"});
    	modeloTablaPruebas.setColumnIdentifiers(new String[]{"Prueba","Archivo","Cantidad registros","Longitud registro","Total registros","Hora inicio","Hora fin","Tiempo total","Valor Inicial","Valor final","Cantidad milisegundos"});
		modeloTablaInfo.setColumnIdentifiers(new String[]{"Benchmark","Path","Nombre SO","Arquitectura SO","Version SO","Usuario SO","Estructura archivos"});
    	modeloTabla1.setColumnIdentifiers(new String[]{"Benchmark","Path","Archivo","Cantidad registros","Longitud registro","Total registros","Hora inicio","Hora fin","Tiempo total","Valor Inicial","Valor final","Cantidad milisegundos"});
		modeloTabla2.setColumnIdentifiers(new String[]{"Benchmark","Path","Archivo","Cantidad registros","Longitud registro","Total registros","Hora inicio","Hora fin","Tiempo total","Valor Inicial","Valor final","Cantidad milisegundos"});
		modeloTabla3.setColumnIdentifiers(new String[]{"Benchmark","Path","Archivo","Cantidad registros","Longitud registro","Total registros","Hora inicio","Hora fin","Tiempo total","Valor Inicial","Valor final","Cantidad milisegundos"});
		modeloTabla4.setColumnIdentifiers(new String[]{"Benchmark","Path","Archivo","Cantidad registros","Longitud registro","Total registros","Hora inicio","Hora fin","Tiempo total","Valor Inicial","Valor final","Cantidad milisegundos"});
		modeloTabla5.setColumnIdentifiers(new String[]{"Benchmark","Path","Archivo","Cantidad registros","Longitud registro","Total registros","Hora inicio","Hora fin","Tiempo total","Valor Inicial","Valor final","Cantidad milisegundos"});
		setAnchoTabla(tablaInfo,modeloTablaInfo,130);		
		setAnchoTabla(tabla1,modeloTabla1,120);
    	setAnchoTabla(tabla2,modeloTabla2,120);
		setAnchoTabla(tabla3,modeloTabla3,120);
		setAnchoTabla(tabla4,modeloTabla4,120);
    	setAnchoTabla(tabla5,modeloTabla5,120);
    	tablaDetalle.getColumnModel().getColumn(0).setPreferredWidth(200);
		tablaDetalle.getColumnModel().getColumn(1).setPreferredWidth(400);
		setAnchoTabla(tablaPruebas,modeloTablaPruebas,120);
		tablaPruebas.getColumnModel().getColumn(0).setPreferredWidth(200);
    } 

   	private void setAnchoTabla(JTable tabla,DefaultTableModel modelo,int ancho)
    {
  		TableColumn column=null;
  		for (int i = 0; i < modelo.getColumnCount(); i++) 
  		{
  	     	column = tabla.getColumnModel().getColumn(i);
		    column.setPreferredWidth(ancho);
		}
    }

	public void agregarBenchmark(Benchmark benchmark,String nombre,String path)
	{
		modeloLista.addElement(nombre);
		completarInfo(benchmark,path,nombre);
		completarPruebas(benchmark,path,nombre);
	}

	private void completarInfo(Benchmark benchmark,String path,String nombre)
	{
		modeloTablaInfo.addRow(new Object[]{""+nombre,""+path,""+benchmark.getNombreSO(),""+benchmark.getArquitecturaSO(),""+benchmark.getVersionSO(),""+benchmark.getUsuarioSO(),""+benchmark.getEstructuraArchivos()});
	}

	private void completarPruebas(Benchmark benchmark,String path,String nombre)
	{
		completarPrueba(benchmark.getPrueba(1),path,nombre,modeloTabla1);
		completarPrueba(benchmark.getPrueba(2),path,nombre,modeloTabla2);
		completarPrueba(benchmark.getPrueba(3),path,nombre,modeloTabla3);
		completarPrueba(benchmark.getPrueba(4),path,nombre,modeloTabla4);
		completarPrueba(benchmark.getPrueba(5),path,nombre,modeloTabla5);
	}

	private void completarPrueba(Prueba prueba,String path,String nombre,DefaultTableModel modelo)	
	{
		if (!prueba.ejecutada()) return;
		modelo.addRow(new Object[]{""+nombre,""+path,"Archivo A",""+prueba.getCantRegA(),""+prueba.getLongRegA(),""+prueba.getTotalRegA(),""+prueba.getHoraInicioA(),""+prueba.getHoraFinA(),""+prueba.getTiempoTotalHoraA(),""+prueba.getValorInicioA(),""+prueba.getValorFinA(),""+prueba.getTiempoTotalA()});
		modelo.addRow(new Object[]{""+nombre,""+path,"Archivo B",""+prueba.getCantRegB(),""+prueba.getLongRegB(),""+prueba.getTotalRegB(),""+prueba.getHoraInicioB(),""+prueba.getHoraFinB(),""+prueba.getTiempoTotalHoraB(),""+prueba.getValorInicioB(),""+prueba.getValorFinB(),""+prueba.getTiempoTotalB()});
	}

	public void quitarBenchmark(String path,int pos)
	{
		txtArchivo.setText("");
		limpiarTablaDetalle();
		limpiarTablaPruebas();
		actualizarInfo(pos);
		actualizarPruebas(path);
		modeloLista.removeElementAt(pos);
	}

	private void actualizarInfo(int pos)
	{
		modeloTablaInfo.removeRow(pos);
	}

	private void actualizarPruebas(String path)
	{
		actualizarPrueba(path,modeloTabla1);
		actualizarPrueba(path,modeloTabla2);
		actualizarPrueba(path,modeloTabla3);
		actualizarPrueba(path,modeloTabla4);
		actualizarPrueba(path,modeloTabla5);
	}

	private void actualizarPrueba(String path,DefaultTableModel modelo)
	{
		int i;
		for (i=0;i<modelo.getRowCount();i++)
			if (path.equals(""+modelo.getValueAt(i,1)))
				break;
		if (i==modelo.getRowCount()) return;
		modelo.removeRow(i);
		modelo.removeRow(i);
	}

	private void limpiarTablaDetalle()
	{
		for (int i=0;i<6;i++)
			tablaDetalle.setValueAt("",i,1);
	}

	private void limpiarTablaPruebas()
	{
		for (int i=1;i<=5;i++)
		{
			limpiarFilaPrueba(i*2-2,"");
			limpiarFilaPrueba(i*2-1,"");
		}		
	}

    private void borrarFilas(DefaultTableModel modelo)
    {
    	while (modelo.getRowCount()!=0)
			modelo.removeRow(0);
    }      

	private void actionDescripcion()
	{
		mostrarDescripcion();
	}

	private void actionSeleccionLista()
	{
		int pos=lista.getSelectedIndex();
		if (pos==-1) return;
		gestor.benchmarkSeleccionado(pos);
	}

	public void mostrarBenchmark(Benchmark benchmark,String nombre)
	{
		txtArchivo.setText(nombre);
		mostrarDetalle(benchmark);
		mostrarPruebas(benchmark);
	}

	private void mostrarDetalle(Benchmark benchmark)
	{
		tablaDetalle.setValueAt(benchmark.getCreacion().toString(),0,1);
		tablaDetalle.setValueAt(benchmark.getNombreSO().toString(),1,1);
		tablaDetalle.setValueAt(benchmark.getArquitecturaSO().toString(),2,1);
		tablaDetalle.setValueAt(benchmark.getVersionSO().toString(),3,1);
		tablaDetalle.setValueAt(benchmark.getUsuarioSO().toString(),4,1);
		tablaDetalle.setValueAt(benchmark.getEstructuraArchivos().toString(),5,1);
	}

	private void mostrarPruebas(Benchmark benchmark)
	{
		Prueba prueba=null;
		for (int i=1;i<=5;i++)
		{
			prueba=benchmark.getPrueba(i);
			if (prueba.ejecutada())
				mostrarPrueba(prueba,i);
			else
			{	
				limpiarFilaPrueba(i*2-2,"-");
				limpiarFilaPrueba(i*2-1,"-");
			}
		}
	}

	private void limpiarFilaPrueba(int pos,String c)
	{
		for (int i=2;i<=10;i++)
			tablaPruebas.setValueAt(c,pos,i);
	}

	private void mostrarPrueba(Prueba prueba,int pos)
	{
		tablaPruebas.setValueAt(""+prueba.getCantRegA(),pos*2-2,2);
		tablaPruebas.setValueAt(""+prueba.getCantRegB(),pos*2-1,2);
		tablaPruebas.setValueAt(""+prueba.getLongRegA(),pos*2-2,3);
		tablaPruebas.setValueAt(""+prueba.getLongRegB(),pos*2-1,3);
		tablaPruebas.setValueAt(""+prueba.getTotalRegA(),pos*2-2,4);
		tablaPruebas.setValueAt(""+prueba.getTotalRegB(),pos*2-1,4);
		tablaPruebas.setValueAt(""+prueba.getHoraInicioA(),pos*2-2,5);
		tablaPruebas.setValueAt(""+prueba.getHoraInicioB(),pos*2-1,5);
		tablaPruebas.setValueAt(""+prueba.getHoraFinA(),pos*2-2,6);
		tablaPruebas.setValueAt(""+prueba.getHoraFinB(),pos*2-1,6);
		tablaPruebas.setValueAt(""+prueba.getTiempoTotalHoraA(),pos*2-2,7);
		tablaPruebas.setValueAt(""+prueba.getTiempoTotalHoraB(),pos*2-1,7);
		tablaPruebas.setValueAt(""+prueba.getValorInicioA(),pos*2-2,8);
		tablaPruebas.setValueAt(""+prueba.getValorInicioB(),pos*2-1,8);
		tablaPruebas.setValueAt(""+prueba.getValorFinA(),pos*2-2,9);
		tablaPruebas.setValueAt(""+prueba.getValorFinB(),pos*2-1,9);
		tablaPruebas.setValueAt(""+prueba.getTiempoTotalA(),pos*2-2,10);
		tablaPruebas.setValueAt(""+prueba.getTiempoTotalB(),pos*2-1,10);
	}

	private void actionAgregar()
	{
		gestor.agregarBenchmark();
	}

	private void actionQuitar()
	{
		int pos=lista.getSelectedIndex();
		if (pos==-1) return;
		gestor.quitarBenchmark(pos);
	}

	public void mostrarAccion(String accion)
	{
		Date date=new Date();
		String aux=date.toString()+" "+accion;
		txtAcciones.setText(txtAcciones.getText()+aux+"\n");
	}

	public void mostrarMensaje(String mensaje)
	{
		JOptionPane.showMessageDialog(null, mensaje, "Error",JOptionPane.ERROR_MESSAGE,new ImageIcon("recursos/imagenes/error.jpeg"));
	}

    private void actionOp5(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actionOp5
        tabbedPane.setSelectedIndex (5);
    }//GEN-LAST:event_actionOp5

    private void actionOp4(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actionOp4
        tabbedPane.setSelectedIndex (4);
    }//GEN-LAST:event_actionOp4

    private void actionOp3(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actionOp3
        tabbedPane.setSelectedIndex (3);
    }//GEN-LAST:event_actionOp3

    private void actionOp2(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actionOp2
        tabbedPane.setSelectedIndex (2);
    }//GEN-LAST:event_actionOp2

    private void actionOp1(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actionOp1
        tabbedPane.setSelectedIndex (1);
    }//GEN-LAST:event_actionOp1

    private void actionSistema(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actionSistema
        tabbedPane.setSelectedIndex (0);
    }//GEN-LAST:event_actionSistema

    private void setearFrame()
    {
        setTitle(Main.NOMBRE+" "+Main.VERSION+": Modulo Consola");
        setBounds(new Rectangle(200,100,800,600));
		setIconImage(Toolkit.getDefaultToolkit().getImage("recursos/imagenes/sombra.jpeg"));			
		setResizable(false);
        GestionVisual.centrar(this);     	
        setExtendedState(JFrame.MAXIMIZED_BOTH);
    }     
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        jSplitPane1 = new javax.swing.JSplitPane();
        jPanel1 = new javax.swing.JPanel();
        jSplitPane2 = new javax.swing.JSplitPane();
        jPanel2 = new javax.swing.JPanel();
        jSplitPane4 = new javax.swing.JSplitPane();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        lista = new javax.swing.JList();
        jPanel8 = new javax.swing.JPanel();
        btAgregar = new javax.swing.JButton();
        btQuitar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jSplitPane5 = new javax.swing.JSplitPane();
        jPanel9 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtArchivo = new javax.swing.JTextField();
        jPanel10 = new javax.swing.JPanel();
        jSplitPane8 = new javax.swing.JSplitPane();
        jPanel17 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablaDetalle = new Tabla();;
        jPanel19 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tablaPruebas = new Tabla();;
        jPanel4 = new javax.swing.JPanel();
        jSplitPane3 = new javax.swing.JSplitPane();
        jPanel5 = new javax.swing.JPanel();
        jSplitPane6 = new javax.swing.JSplitPane();
        jPanel11 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jPanel13 = new javax.swing.JPanel();
        btSistema = new javax.swing.JButton();
        btOp1 = new javax.swing.JButton();
        btOp2 = new javax.swing.JButton();
        btOp3 = new javax.swing.JButton();
        btOp4 = new javax.swing.JButton();
        btOp5 = new javax.swing.JButton();
		btDescripcion = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        tabbedPane = new javax.swing.JTabbedPane();
        jPanel14 = new javax.swing.JPanel();
        jSplitPane7 = new javax.swing.JSplitPane();
        jPanel18 = new javax.swing.JPanel();
        btDetInfo = new javax.swing.JButton();
        comboBarrasInfo = new javax.swing.JComboBox();
        btBarrasInfo = new javax.swing.JButton();
        comboTortaInfo = new javax.swing.JComboBox();
        btTortaInfo = new javax.swing.JButton();
        jPanel16 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tablaInfo = new Tabla();;
        jPanel15 = new javax.swing.JPanel();
        jSplitPane9 = new javax.swing.JSplitPane();
        jPanel20 = new javax.swing.JPanel();
        btDet1 = new javax.swing.JButton();
        comboBarras1 = new javax.swing.JComboBox();
        btBarras1 = new javax.swing.JButton();
        comboTorta1 = new javax.swing.JComboBox();
        btTorta1 = new javax.swing.JButton();
        jPanel21 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tablaXXX = new Tabla();;
        jPanel22 = new javax.swing.JPanel();
        jSplitPane10 = new javax.swing.JSplitPane();
        jPanel23 = new javax.swing.JPanel();
        btDet2 = new javax.swing.JButton();
        comboBarras2 = new javax.swing.JComboBox();
        btBarras2 = new javax.swing.JButton();
        comboTorta2 = new javax.swing.JComboBox();
        btTorta2 = new javax.swing.JButton();
        jPanel24 = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        tabla1 = new Tabla();;
        jPanel25 = new javax.swing.JPanel();
        jSplitPane11 = new javax.swing.JSplitPane();
        jPanel26 = new javax.swing.JPanel();
        btDet3 = new javax.swing.JButton();
        comboBarras3 = new javax.swing.JComboBox();
        btBarras3 = new javax.swing.JButton();
        comboTorta3 = new javax.swing.JComboBox();
        btTorta3 = new javax.swing.JButton();
        jPanel27 = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        tabla2 = new Tabla();;
        jPanel28 = new javax.swing.JPanel();
        jSplitPane12 = new javax.swing.JSplitPane();
        jPanel29 = new javax.swing.JPanel();
        btDet4 = new javax.swing.JButton();
        comboBarras4 = new javax.swing.JComboBox();
        btBarras4 = new javax.swing.JButton();
        comboTorta4 = new javax.swing.JComboBox();
        btTorta4 = new javax.swing.JButton();
        jPanel30 = new javax.swing.JPanel();
        jScrollPane10 = new javax.swing.JScrollPane();
        tabla3 = new Tabla();;
        jPanel31 = new javax.swing.JPanel();
        jSplitPane13 = new javax.swing.JSplitPane();
        jPanel32 = new javax.swing.JPanel();
        btDet5 = new javax.swing.JButton();
        comboBarras5 = new javax.swing.JComboBox();
        btBarras5 = new javax.swing.JButton();
        comboTorta5 = new javax.swing.JComboBox();
        btTorta5 = new javax.swing.JButton();
        jPanel33 = new javax.swing.JPanel();
        jScrollPane11 = new javax.swing.JScrollPane();
        tabla4 = new Tabla();;
        jPanel34 = new javax.swing.JPanel();
        jSplitPane14 = new javax.swing.JSplitPane();
        jPanel35 = new javax.swing.JPanel();
        btDet7 = new javax.swing.JButton();
        comboBarras7 = new javax.swing.JComboBox();
        btBarras7 = new javax.swing.JButton();
        comboTorta7 = new javax.swing.JComboBox();
        btTorta7 = new javax.swing.JButton();
        jPanel36 = new javax.swing.JPanel();
        jScrollPane12 = new javax.swing.JScrollPane();
        tabla5 = new Tabla();;
        jPanel6 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtAcciones = new javax.swing.JTextPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        menuPrograma = new javax.swing.JMenu();
        itemSalir = new javax.swing.JMenuItem();

        getContentPane().setLayout(new java.awt.GridLayout(1, 1));

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                exitForm(evt);
            }
        });

        jSplitPane1.setDividerLocation(245);
        jSplitPane1.setDividerSize(2);
        jSplitPane1.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        jSplitPane1.setEnabled(false);
        jPanel1.setLayout(new java.awt.GridLayout(1, 1));

        jSplitPane2.setDividerLocation(190);
        jSplitPane2.setDividerSize(2);
        jSplitPane2.setEnabled(false);
        jPanel2.setLayout(new java.awt.GridLayout(1, 1));

        jPanel2.setBorder(new javax.swing.border.TitledBorder("Benchmarks seleccionados"));
        jSplitPane4.setDividerLocation(170);
        jSplitPane4.setDividerSize(2);
        jSplitPane4.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        jSplitPane4.setEnabled(false);
        jPanel7.setLayout(new java.awt.GridLayout(1, 1));

        jPanel7.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 5));
        jScrollPane2.setViewportView(lista);

        jPanel7.add(jScrollPane2);

        jSplitPane4.setLeftComponent(jPanel7);

        jPanel8.setLayout(new java.awt.GridLayout(1, 2, 5, 5));

        jPanel8.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 5));
        btAgregar.setIcon(new javax.swing.ImageIcon("recursos/imagenes/agregar.gif"));
        btAgregar.setToolTipText("Agregar un archivo");
        btAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actionAgregar();
            }
        });
        jPanel8.add(btAgregar);

        btQuitar.setIcon(new javax.swing.ImageIcon("recursos/imagenes/quitar.gif"));
        btQuitar.setToolTipText("Quitar un archivo");
        btQuitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actionQuitar();
            }
        });
        jPanel8.add(btQuitar);

        jSplitPane4.setRightComponent(jPanel8);

        jPanel2.add(jSplitPane4);

        jSplitPane2.setLeftComponent(jPanel2);

        jPanel3.setLayout(new java.awt.GridLayout(1, 1));

        jPanel3.setBorder(new javax.swing.border.TitledBorder("Benchmark seleccionado"));
        jSplitPane5.setDividerLocation(25);
        jSplitPane5.setDividerSize(2);
        jSplitPane5.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        jSplitPane5.setEnabled(false);
        jPanel9.setLayout(new java.awt.GridLayout(1, 2, 3, 3));

        jPanel9.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 3));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Benchmark");
        jPanel9.add(jLabel1);

        txtArchivo.setEditable(false);
        txtArchivo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel9.add(txtArchivo);

        jSplitPane5.setLeftComponent(jPanel9);

        jPanel10.setLayout(new java.awt.GridLayout(1, 1));

        jSplitPane8.setDividerLocation(110);
        jSplitPane8.setDividerSize(3);
        jSplitPane8.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        jPanel17.setLayout(new java.awt.GridLayout());

        jPanel17.setBorder(new javax.swing.border.TitledBorder("Detalle del sistema"));
        tablaDetalle.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(tablaDetalle);

        jPanel17.add(jScrollPane3);

        jSplitPane8.setLeftComponent(jPanel17);

        jPanel19.setLayout(new java.awt.GridLayout());

        jPanel19.setBorder(new javax.swing.border.TitledBorder("Pruebas efectuadas"));
        tablaPruebas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane4.setViewportView(tablaPruebas);

        jPanel19.add(jScrollPane4);

        jSplitPane8.setRightComponent(jPanel19);

        jPanel10.add(jSplitPane8);

        jSplitPane5.setRightComponent(jPanel10);

        jPanel3.add(jSplitPane5);

        jSplitPane2.setRightComponent(jPanel3);

        jPanel1.add(jSplitPane2);

        jSplitPane1.setLeftComponent(jPanel1);

        jPanel4.setLayout(new java.awt.GridLayout(1, 1));

        jPanel4.setBorder(new javax.swing.border.TitledBorder("Comparacion de archivos"));
        jSplitPane3.setDividerLocation(200);
        jSplitPane3.setDividerSize(3);
        jSplitPane3.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        jSplitPane3.setEnabled(false);
        jPanel5.setLayout(new java.awt.GridLayout(1, 1));

        jSplitPane6.setDividerLocation(110);
        jSplitPane6.setDividerSize(1);
        jSplitPane6.setEnabled(false);
        jPanel11.setLayout(new java.awt.GridLayout(1, 1, 5, 5));

        jPanel11.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 3));
        jPanel13.setLayout(new java.awt.GridLayout(7, 1));

        btSistema.setBackground(new java.awt.Color(255, 255, 255));
        btSistema.setIcon(new javax.swing.ImageIcon("recursos/imagenes/sistema.jpeg"));
        btSistema.setToolTipText("Informaci\u00f3n del sistema");
        btSistema.setMaximumSize(new java.awt.Dimension(34, 30));
        btSistema.setMinimumSize(new java.awt.Dimension(34, 30));
        btSistema.setPreferredSize(new java.awt.Dimension(34, 50));
        btSistema.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actionSistema(evt);
            }
        });
        jPanel13.add(btSistema);

        btOp1.setBackground(new java.awt.Color(255, 255, 255));
        btOp1.setIcon(new javax.swing.ImageIcon("recursos/imagenes/o1.jpeg"));
        btOp1.setToolTipText("Abrir y grabar");
        btOp1.setMaximumSize(new java.awt.Dimension(34, 30));
        btOp1.setMinimumSize(new java.awt.Dimension(34, 30));
        btOp1.setPreferredSize(new java.awt.Dimension(34, 50));
        btOp1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actionOp1(evt);
            }
        });

        jPanel13.add(btOp1);

        btOp2.setBackground(new java.awt.Color(255, 255, 255));
        btOp2.setIcon(new javax.swing.ImageIcon("recursos/imagenes/o2.jpeg"));
        btOp2.setToolTipText("Leer secuencialmente");
        btOp2.setMaximumSize(new java.awt.Dimension(34, 30));
        btOp2.setMinimumSize(new java.awt.Dimension(34, 30));
        btOp2.setPreferredSize(new java.awt.Dimension(34, 50));
        btOp2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actionOp2(evt);
            }
        });

        jPanel13.add(btOp2);

        btOp3.setBackground(new java.awt.Color(255, 255, 255));
        btOp3.setIcon(new javax.swing.ImageIcon("recursos/imagenes/o3.jpeg"));
        btOp3.setToolTipText("Leer y regrabar secuencialmente");
        btOp3.setMaximumSize(new java.awt.Dimension(34, 30));
        btOp3.setMinimumSize(new java.awt.Dimension(34, 30));
        btOp3.setPreferredSize(new java.awt.Dimension(34, 50));
        btOp3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actionOp3(evt);
            }
        });

        jPanel13.add(btOp3);

        btOp4.setBackground(new java.awt.Color(255, 255, 255));
        btOp4.setIcon(new javax.swing.ImageIcon("recursos/imagenes/o4.JPG"));
        btOp4.setToolTipText("Leer aleatoriamente");
        btOp4.setMaximumSize(new java.awt.Dimension(34, 30));
        btOp4.setMinimumSize(new java.awt.Dimension(34, 30));
        btOp4.setPreferredSize(new java.awt.Dimension(34, 50));
        btOp4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actionOp4(evt);
            }
        });

        jPanel13.add(btOp4);

        btOp5.setBackground(new java.awt.Color(255, 255, 255));
        btOp5.setIcon(new javax.swing.ImageIcon("recursos/imagenes/o5.JPG"));
        btOp5.setToolTipText("Leer y regrabar en forma aleatoria");
        btOp5.setMaximumSize(new java.awt.Dimension(34, 30));
        btOp5.setMinimumSize(new java.awt.Dimension(34, 30));
        btOp5.setPreferredSize(new java.awt.Dimension(34, 50));
        btOp5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actionOp5(evt);
            }
        });

        jPanel13.add(btOp5);

        btDescripcion.setBackground(new java.awt.Color(255, 255, 255));
		btDescripcion.setIcon(new javax.swing.ImageIcon("recursos/imagenes/descripcion.jpeg"));
        btDescripcion.setMnemonic('o');
        btDescripcion.setToolTipText("Descripcion de "+Main.NOMBRE);
        btDescripcion.setMaximumSize(new java.awt.Dimension(34, 30));
        btDescripcion.setMinimumSize(new java.awt.Dimension(34, 30));
        btDescripcion.setPreferredSize(new java.awt.Dimension(34, 50));
        btDescripcion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actionDescripcion();
            }
        });

        jPanel13.add(btDescripcion);

        jScrollPane5.setViewportView(jPanel13);

        jPanel11.add(jScrollPane5);

        jSplitPane6.setLeftComponent(jPanel11);

        jPanel12.setLayout(new java.awt.GridLayout(1, 1));

        jPanel14.setLayout(new java.awt.GridLayout(1, 1));

        jSplitPane7.setDividerLocation(110);
        jSplitPane7.setDividerSize(1);
        jSplitPane7.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        jSplitPane7.setEnabled(false);
        jPanel18.setLayout(new java.awt.GridLayout(1, 5, 2, 2));

        jPanel18.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 2));
        btDetInfo.setIcon(new javax.swing.ImageIcon("recursos/imagenes/detalle.jpeg"));
		//btDetInfo.setBackground(new java.awt.Color(255, 255, 255));
        btDetInfo.setToolTipText("Informacion detallada");
        btDetInfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actionDetalleInfo();
            }
        });

        jPanel18.add(btDetInfo);

        comboBarrasInfo.setToolTipText("Criterio de grafico de barras");
        jPanel18.add(comboBarrasInfo);

        btBarrasInfo.setIcon(new javax.swing.ImageIcon("recursos/imagenes/barras.jpeg"));
        btBarrasInfo.setToolTipText("Grafico de barras");
        btBarrasInfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actionBarrasInfo();
            }
        });
        jPanel18.add(btBarrasInfo);

        comboTortaInfo.setToolTipText("Criterio de grafico de torta");
        jPanel18.add(comboTortaInfo);

        btTortaInfo.setIcon(new javax.swing.ImageIcon("recursos/imagenes/torta.jpeg"));
        btTortaInfo.setToolTipText("Grafico de torta");
        btTortaInfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actionTortaInfo();
            }
        });
        jPanel18.add(btTortaInfo);

        jSplitPane7.setRightComponent(jPanel18);

        jPanel16.setLayout(new java.awt.GridLayout(1, 1));

        tablaInfo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane6.setViewportView(tablaInfo);

        jPanel16.add(jScrollPane6);

        jSplitPane7.setLeftComponent(jPanel16);

        jPanel14.add(jSplitPane7);

        tabbedPane.addTab("Informacion del sistema", jPanel14);

        jPanel15.setLayout(new java.awt.GridLayout());

        jSplitPane9.setDividerLocation(110);
        jSplitPane9.setDividerSize(1);
        jSplitPane9.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        jSplitPane9.setEnabled(false);
        jPanel20.setLayout(new java.awt.GridLayout());

        jPanel20.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 2));
        btDet1.setIcon(new javax.swing.ImageIcon("recursos/imagenes/detalle.jpeg"));
		//btDet1.setBackground(new java.awt.Color(255, 255, 255));
        btDet1.setToolTipText("Informacion detallada");
        btDet1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actionDetallePrueba(1);
            }
        });
        jPanel20.add(btDet1);

        comboBarras1.setToolTipText("Criterio de grafico de barras");
        jPanel20.add(comboBarras1);

        btBarras1.setIcon(new javax.swing.ImageIcon("recursos/imagenes/barras.jpeg"));
        btBarras1.setToolTipText("Grafico de barras");
        btBarras1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actionBarras(comboBarras2,1);
            }
        });
        jPanel20.add(btBarras1);

        comboTorta1.setToolTipText("Criterio de grafico de torta");
        jPanel20.add(comboTorta1);

        btTorta1.setIcon(new javax.swing.ImageIcon("recursos/imagenes/torta.jpeg"));
        btTorta1.setToolTipText("Grafico de torta 1");
        btTorta1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actionTorta(comboTorta2,1);
            }
        });
        jPanel20.add(btTorta1);

        jSplitPane9.setRightComponent(jPanel20);

        jPanel21.setLayout(new java.awt.GridLayout());

        tablaXXX.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane7.setViewportView(tablaXXX);

        jPanel21.add(jScrollPane7);

        jSplitPane9.setLeftComponent(jPanel21);

        jPanel15.add(jSplitPane9);

        //tabbedPane.addTab("tab1", jPanel15);

        jPanel22.setLayout(new java.awt.GridLayout());

        jSplitPane10.setDividerLocation(110);
        jSplitPane10.setDividerSize(1);
        jSplitPane10.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        jSplitPane10.setEnabled(true);
        jPanel23.setLayout(new java.awt.GridLayout());

        jPanel23.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 2));
        btDet2.setIcon(new javax.swing.ImageIcon("recursos/imagenes/detalle.jpeg"));
		//btDet2.setBackground(new java.awt.Color(255, 255, 255));
        btDet2.setToolTipText("Informacion detallada");
        btDet2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actionDetallePrueba(1);
            }
        });
        jPanel23.add(btDet2);

        comboBarras2.setToolTipText("Criterio de grafico de barras");
        jPanel23.add(comboBarras2);

        btBarras2.setIcon(new javax.swing.ImageIcon("recursos/imagenes/barras.jpeg"));
        btBarras2.setToolTipText("Grafico de barras");
        btBarras2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actionBarras(comboBarras2,1);
            }
        });
        jPanel23.add(btBarras2);

        comboTorta2.setToolTipText("Criterio de grafico de torta");
        jPanel23.add(comboTorta2);

        btTorta2.setIcon(new javax.swing.ImageIcon("recursos/imagenes/torta.jpeg"));
        btTorta2.setToolTipText("Grafico de torta 1");
        btTorta2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actionTorta(comboTorta2,1);
            }
        });
        jPanel23.add(btTorta2);

        jSplitPane10.setRightComponent(jPanel23);

        jPanel24.setLayout(new java.awt.GridLayout());

        tabla1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane8.setViewportView(tabla1);

        jPanel24.add(jScrollPane8);

        jSplitPane10.setLeftComponent(jPanel24);

        jPanel22.add(jSplitPane10);

        tabbedPane.addTab("Abrir y grabar secuencialmente", jPanel22);

        jPanel25.setLayout(new java.awt.GridLayout());

        jSplitPane11.setDividerLocation(110);
        jSplitPane11.setDividerSize(1);
        jSplitPane11.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        jSplitPane11.setEnabled(false);
        jPanel26.setLayout(new java.awt.GridLayout());

        jPanel26.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 2));
        btDet3.setIcon(new javax.swing.ImageIcon("recursos/imagenes/detalle.jpeg"));
		//btDet3.setBackground(new java.awt.Color(255, 255, 255));
        btDet3.setToolTipText("Informa en detalle");
        btDet3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actionDetallePrueba(2);
            }
        });
        jPanel26.add(btDet3);

        comboBarras3.setToolTipText("Criterio de grafico de barras");
        jPanel26.add(comboBarras3);

        btBarras3.setIcon(new javax.swing.ImageIcon("recursos/imagenes/barras.jpeg"));
        btBarras3.setToolTipText("Grafico de barras");
        btBarras3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actionBarras(comboBarras3,2);
            }
        });
        jPanel26.add(btBarras3);

        comboTorta3.setToolTipText("Criterio de grafico de torta");
        jPanel26.add(comboTorta3);

        btTorta3.setIcon(new javax.swing.ImageIcon("recursos/imagenes/torta.jpeg"));
        btTorta3.setToolTipText("Grafico de torta 2");
        btTorta3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actionTorta(comboTorta3,2);
            }
        });
        jPanel26.add(btTorta3);

        jSplitPane11.setRightComponent(jPanel26);

        jPanel27.setLayout(new java.awt.GridLayout());

        tabla2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane9.setViewportView(tabla2);

        jPanel27.add(jScrollPane9);

        jSplitPane11.setLeftComponent(jPanel27);

        jPanel25.add(jSplitPane11);

        tabbedPane.addTab("Leer secuencialmente", jPanel25);

        jPanel28.setLayout(new java.awt.GridLayout());

        jSplitPane12.setDividerLocation(110);
        jSplitPane12.setDividerSize(1);
        jSplitPane12.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        jSplitPane12.setEnabled(false);
        jPanel29.setLayout(new java.awt.GridLayout());

        jPanel29.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 2));
        btDet4.setIcon(new javax.swing.ImageIcon("recursos/imagenes/detalle.jpeg"));
		//btDet4.setBackground(new java.awt.Color(255, 255, 255));
        btDet4.setToolTipText("Informacion en detalle");
        btDet4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actionDetallePrueba(3);
            }
        });
        jPanel29.add(btDet4);

        comboBarras4.setToolTipText("Criterio de grafico de barras");
        jPanel29.add(comboBarras4);

        btBarras4.setIcon(new javax.swing.ImageIcon("recursos/imagenes/barras.jpeg"));
        btBarras4.setToolTipText("Grafico de barras");
        btBarras4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actionBarras(comboBarras4,3);
            }
        });
        jPanel29.add(btBarras4);

        comboTorta4.setToolTipText("Criterio de grafico de torta");
        jPanel29.add(comboTorta4);

        btTorta4.setIcon(new javax.swing.ImageIcon("recursos/imagenes/torta.jpeg"));
        btTorta4.setToolTipText("Grafico de torta 3");
        btTorta4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actionTorta(comboTorta4,3);
            }
        });
        jPanel29.add(btTorta4);

        jSplitPane12.setRightComponent(jPanel29);

        jPanel30.setLayout(new java.awt.GridLayout());

        tabla3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane10.setViewportView(tabla3);

        jPanel30.add(jScrollPane10);

        jSplitPane12.setLeftComponent(jPanel30);

        jPanel28.add(jSplitPane12);

        tabbedPane.addTab("Leer y regrabar secuencialmente", jPanel28);

        jPanel31.setLayout(new java.awt.GridLayout());

        jSplitPane13.setDividerLocation(110);
        jSplitPane13.setDividerSize(1);
        jSplitPane13.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        jSplitPane13.setEnabled(false);
        jPanel32.setLayout(new java.awt.GridLayout());

        jPanel32.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 2));
        btDet5.setIcon(new javax.swing.ImageIcon("recursos/imagenes/detalle.jpeg"));
		//btDet5.setBackground(new java.awt.Color(255, 255, 255));
        btDet5.setToolTipText("Informacion en detalle");
        btDet5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actionDetallePrueba(4);
            }
        });
        jPanel32.add(btDet5);

        comboBarras5.setToolTipText("Criterio de grafico de barras");
        jPanel32.add(comboBarras5);

        btBarras5.setIcon(new javax.swing.ImageIcon("recursos/imagenes/barras.jpeg"));
        btBarras5.setToolTipText("Grafico de barras");
        btBarras5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actionBarras(comboBarras5,4);
            }
        });
        jPanel32.add(btBarras5);

        comboTorta5.setToolTipText("Criterio de grafico de torta");
        jPanel32.add(comboTorta5);

        btTorta5.setIcon(new javax.swing.ImageIcon("recursos/imagenes/torta.jpeg"));
        btTorta5.setToolTipText("Grafico de torta 4");
        btTorta5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actionTorta(comboTorta5,4);
            }
        });
        jPanel32.add(btTorta5);

        jSplitPane13.setRightComponent(jPanel32);

        jPanel33.setLayout(new java.awt.GridLayout());

        tabla4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane11.setViewportView(tabla4);

        jPanel33.add(jScrollPane11);

        jSplitPane13.setLeftComponent(jPanel33);

        jPanel31.add(jSplitPane13);

        tabbedPane.addTab("Leer aleatoriamente", jPanel31);

        jPanel34.setLayout(new java.awt.GridLayout());

        jSplitPane14.setDividerLocation(110);
        jSplitPane14.setDividerSize(1);
        jSplitPane14.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        jSplitPane14.setEnabled(false);
        jPanel35.setLayout(new java.awt.GridLayout());

        jPanel35.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 2));
        //btDet7.setBackground(new java.awt.Color(255, 255, 255));
		//btDet7.setBackground(new java.awt.Color(255, 255, 255));
        btDet7.setIcon(new javax.swing.ImageIcon("recursos/imagenes/detalle.jpeg"));
        btDet7.setToolTipText("det");
        btDet7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actionDetallePrueba(5);
            }
        });
        jPanel35.add(btDet7);

        //comboBarras7.setBackground(new java.awt.Color(255, 255, 255));
        comboBarras7.setToolTipText("Criterio de grafico de barras");
        jPanel35.add(comboBarras7);

        //btBarras7.setBackground(new java.awt.Color(255, 255, 255));
        btBarras7.setIcon(new javax.swing.ImageIcon("recursos/imagenes/barras.jpeg"));
        btBarras7.setToolTipText("Grafico de barras");
        btBarras7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actionBarras(comboBarras7,5);
            }
        });
        jPanel35.add(btBarras7);

        //comboTorta7.setBackground(new java.awt.Color(255, 255, 255));
        comboTorta7.setToolTipText("Criterio de grafico de torta");
        jPanel35.add(comboTorta7);

        //btTorta7.setBackground(new java.awt.Color(255, 255, 255));
        btTorta7.setIcon(new javax.swing.ImageIcon("recursos/imagenes/torta.jpeg"));
        btTorta7.setToolTipText("Grafico de torta 5");
        btTorta7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actionTorta(comboTorta7,5);
            }
        });
        jPanel35.add(btTorta7);

        jSplitPane14.setRightComponent(jPanel35);

        jPanel36.setLayout(new java.awt.GridLayout());

        tabla5.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane12.setViewportView(tabla5);

        jPanel36.add(jScrollPane12);

        jSplitPane14.setLeftComponent(jPanel36);

        jPanel34.add(jSplitPane14);

        tabbedPane.addTab("Leer y regrabar aleatoriamente", jPanel34);

        jPanel12.add(tabbedPane);

        jSplitPane6.setRightComponent(jPanel12);

        jPanel5.add(jSplitPane6);

        jSplitPane3.setLeftComponent(jPanel5);

        jPanel6.setLayout(new java.awt.GridLayout(1, 1));

        jPanel6.setBorder(new javax.swing.border.TitledBorder("Acciones realizadas"));
        txtAcciones.setEditable(false);
        jScrollPane1.setViewportView(txtAcciones);

        jPanel6.add(jScrollPane1);

        jSplitPane3.setRightComponent(jPanel6);

        jPanel4.add(jSplitPane3);

        jSplitPane1.setRightComponent(jPanel4);

        getContentPane().add(jSplitPane1);

        menuPrograma.setMnemonic('p');
        menuPrograma.setText("Modulo");
        itemSalir.setMnemonic('s');
		itemSalir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_MASK));
        itemSalir.setText("Salir de Consola");
        itemSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actionSalir(evt);
            }
        });

        menuPrograma.add(itemSalir);

        jMenuBar1.add(menuPrograma);

        setJMenuBar(jMenuBar1);

        lista.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                actionSeleccionLista();
            }
        });

        pack();
    }//GEN-END:initComponents

    private void actionSalir(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actionSalir
        gestor.finalizar();
		Main.cerrarVentanaConsola();
    }//GEN-LAST:event_actionSalir

    /** Exit the Application */
    private void exitForm(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_exitForm
        gestor.finalizar();
		Main.cerrarAplicacion();
    }//GEN-LAST:event_exitForm

    class Tabla extends JTable
    {
     	public Tabla()
     	{
     		super();
     		showHorScroll(true);
     	}
     	
     	public void showHorScroll(boolean show){
      	if (show)
      	{
          	setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
      	}
      	else
      	{
          	setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
      	}
     }    	
    }    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btAgregar;
    private javax.swing.JButton btBarrasInfo;
    private javax.swing.JButton btBarras1;
    private javax.swing.JButton btBarras2;
    private javax.swing.JButton btBarras3;
    private javax.swing.JButton btBarras4;
    private javax.swing.JButton btBarras5;
    private javax.swing.JButton btBarras7;
    private javax.swing.JButton btDetInfo;
    private javax.swing.JButton btDet1;
    private javax.swing.JButton btDet2;
    private javax.swing.JButton btDet3;
    private javax.swing.JButton btDet4;
    private javax.swing.JButton btDet5;
    private javax.swing.JButton btDet7;
    private javax.swing.JButton btSistema;
    private javax.swing.JButton btOp1;
    private javax.swing.JButton btOp2;
    private javax.swing.JButton btOp3;
    private javax.swing.JButton btOp4;
    private javax.swing.JButton btOp5;
    private javax.swing.JButton btQuitar;
    private javax.swing.JButton btTortaInfo;
    private javax.swing.JButton btTorta1;
    private javax.swing.JButton btTorta2;
    private javax.swing.JButton btTorta3;
    private javax.swing.JButton btTorta4;
    private javax.swing.JButton btTorta5;
    private javax.swing.JButton btTorta7;
	private javax.swing.JButton btDescripcion;
    private javax.swing.JComboBox comboBarrasInfo;
    private javax.swing.JComboBox comboBarras1;
    private javax.swing.JComboBox comboBarras2;
    private javax.swing.JComboBox comboBarras3;
    private javax.swing.JComboBox comboBarras4;
    private javax.swing.JComboBox comboBarras5;
    private javax.swing.JComboBox comboBarras7;
    private javax.swing.JComboBox comboTortaInfo;
    private javax.swing.JComboBox comboTorta1;
    private javax.swing.JComboBox comboTorta2;
    private javax.swing.JComboBox comboTorta3;
    private javax.swing.JComboBox comboTorta4;
    private javax.swing.JComboBox comboTorta5;
    private javax.swing.JComboBox comboTorta7;
    private javax.swing.JMenuItem itemSalir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JPanel jPanel34;
    private javax.swing.JPanel jPanel35;
    private javax.swing.JPanel jPanel36;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JSplitPane jSplitPane10;
    private javax.swing.JSplitPane jSplitPane11;
    private javax.swing.JSplitPane jSplitPane12;
    private javax.swing.JSplitPane jSplitPane13;
    private javax.swing.JSplitPane jSplitPane14;
    private javax.swing.JSplitPane jSplitPane2;
    private javax.swing.JSplitPane jSplitPane3;
    private javax.swing.JSplitPane jSplitPane4;
    private javax.swing.JSplitPane jSplitPane5;
    private javax.swing.JSplitPane jSplitPane6;
    private javax.swing.JSplitPane jSplitPane7;
    private javax.swing.JSplitPane jSplitPane8;
    private javax.swing.JSplitPane jSplitPane9;
    private javax.swing.JList lista;
    private javax.swing.JMenu menuPrograma;
    private javax.swing.JTabbedPane tabbedPane;
    private javax.swing.JTable tablaDetalle;
    private javax.swing.JTable tablaInfo;
    private javax.swing.JTable tablaXXX;
    private javax.swing.JTable tabla1;
    private javax.swing.JTable tabla2;
    private javax.swing.JTable tabla3;
    private javax.swing.JTable tabla4;
    private javax.swing.JTable tabla5;
    private javax.swing.JTable tablaPruebas;
    private javax.swing.JTextPane txtAcciones;
    private javax.swing.JTextField txtArchivo;
    // End of variables declaration//GEN-END:variables
    
}
