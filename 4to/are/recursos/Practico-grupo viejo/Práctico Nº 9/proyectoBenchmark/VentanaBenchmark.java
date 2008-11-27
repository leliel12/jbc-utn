/*
 * VentanaBenchmark.java
 *
 * Created on 15 de julio de 2006, 12:59 PM
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

public class VentanaBenchmark extends javax.swing.JFrame {
    
	private DefaultTableModel modeloTablaInfo,modeloTabla1,modeloTabla2,modeloTabla3,modeloTabla4,modeloTabla5;
	private boolean estadoParam;
	private boolean operacionExitosa;

	private DialogoEspera dialogoEspera;
	private DialogoDescripcion dialogoDescripcion;
	
	private Benchmark benchmark;
	private GestorBenchmark gestor;

    /** Creates new form VentanaBenchmark */
    public VentanaBenchmark() {
        initComponents();
        setearFrame();
		setearModelos();
		setearTablas();    
		crearGestor();
		crearDialogos();
		configuracionInicial();
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
		dialogoEspera=new DialogoEspera();
		dialogoDescripcion=new DialogoDescripcion("recursos/texto/descripcionBenchmark.txt");
	}

	private void crearGestor()
	{
		gestor=new GestorBenchmark(this);
	}

	private void configuracionInicial()
	{
		estadoParam=true;

		activarOp1(false);
		activarOp2(false);
		activarOp3(false);
		activarOp4(false);
		activarOp5(false);
		borrarFilas(modeloTablaInfo);
		borrarFilas(modeloTabla1);
		borrarFilas(modeloTabla2);
		borrarFilas(modeloTabla3);
		borrarFilas(modeloTabla4);
		borrarFilas(modeloTabla5);	
		completarInformacion();
		jTabbedPane1.setSelectedIndex (0);
	}

	private void completarInformacion()
	{
		Properties propiedades=System.getProperties();
		String infoSistema[][];
		infoSistema=new String [11][2];
		infoSistema[0][0]="Nombre del Sistema Operativo";infoSistema[0][1]="os.name";
		infoSistema[1][0]="Arquitectura del Sistema Operativo";infoSistema[1][1]="os.arch";
		infoSistema[2][0]="Version del Sistema Operativo";infoSistema[2][1]="os.version";
		infoSistema[3][0]="Usuario Sistema Operativo";infoSistema[3][1]="user.name";
		infoSistema[4][0]="Home Usuario Sistema Operativo";infoSistema[4][1]="user.home";
		infoSistema[5][0]="Directorio Usuario Sistema Operativo";infoSistema[5][1]="user.dir";
		infoSistema[6][0]="Version Java";infoSistema[6][1]="java.version";
		infoSistema[7][0]="Home Java";infoSistema[7][1]="java.home";
		infoSistema[8][0]="Version Maquina Virtual";infoSistema[8][1]="java.vm.version";
		infoSistema[9][0]="Nombre especificacion Maquina Virtual";infoSistema[9][1]="java.vm.specification.name";
		infoSistema[10][0]="Java Runtime Environment vendor";infoSistema[10][1]="java.vendor";
		
		for (int i=0;i<infoSistema.length;i++)
			modeloTablaInfo.addRow(new Object[]{infoSistema[i][0],propiedades.getProperty(infoSistema[i][1])});
		modeloTablaInfo.addRow(new Object[]{"Estructura de archivos",gestor.getEstructuraArchivos()});
	}

    private void borrarFilas(DefaultTableModel modelo)
    {
    	while (modelo.getRowCount()!=0)
			modelo.removeRow(0);
    }      

	private void actionEstructura()
	{
		Object o = JOptionPane.showInputDialog(null,
            "Seleccione la estructura de archivos", "Estructura de archivos",
            JOptionPane.INFORMATION_MESSAGE, null,
            ValoresPrueba.ESTRUCTURAS,ValoresPrueba.ESTRUCTURAS[ValoresPrueba.ESTRUCTURA_ARCHIVOS]);
		if (o==null)
		{
			mostrarAccion("No se modifico la estructura de archivos "+ValoresPrueba.ESTRUCTURAS[ValoresPrueba.ESTRUCTURA_ARCHIVOS]);
			return;
		}
		String estructura=""+o;
		if (gestor.actualizarEstructura(estructura))
		{
			mostrarAccion("Estrutura de archivos seleccionada: "+estructura);	
			tablaInfo.setValueAt(estructura,11,1);
		}
		else
			mostrarAccion("No se modifico la estructura de archivos "+estructura);
	}

	private void actionGuardarTodo()
	{
        int ret=JOptionPane.showConfirmDialog(null,"¿Desea guardar todas las pruebas del benchmark?","Guardar pruebas",JOptionPane.YES_NO_OPTION);
		if (ret==JOptionPane.YES_OPTION)
			if(gestor.guardar())
				mostrarAccion("Todas las pruebas se han guardado");
			else
				mostrarAccion("Error al grabar todas las pruebas");	
	}

	private void actionDescripcion()
	{
		mostrarDescripcion();
	}

	private void actionGuardar()
	{
		int pos=jTabbedPane1.getSelectedIndex();
		String op=jTabbedPane1.getTitleAt(jTabbedPane1.getSelectedIndex());
		if (!gestor.pruebaEjecutada(pos))
		{
			mostrarMensaje("No se ha ejecutado "+op);
			mostrarAccion("No se pudo guardar "+op);
			return;
		}
		gestor.guardarPrueba (pos);
		mostrarAccion(op+" guardado");
	} 

	private void mostrarVentanaGraficoBarras(int valores[][],String lX[],String lY[],String titulo,String titulos[])
	{
		VentanaGraficoBarras ventana=new VentanaGraficoBarras (titulo,valores,lX,lY,titulo,true,true,true,titulos,new GestionGrafico());
		ventana.setVisible(true);			
	}       

	private void actionBarras()
	{
		int pos=jTabbedPane1.getSelectedIndex();
		String op=jTabbedPane1.getTitleAt(jTabbedPane1.getSelectedIndex());
		if (!gestor.pruebaEjecutada(pos))
		{
			mostrarMensaje("No se ha ejecutado "+op);
			mostrarAccion("No se pudo guardar "+op);
			return;
		}		
		
		mostrarBarras(gestor.getPrueba(pos),op);
	}

	private void mostrarBarras(Prueba prueba,String op)
	{
		int valores[][]=new int[2][2];
		valores[0][1]=(int)prueba.getTiempoTotalA();
		valores[1][1]=(int)prueba.getTiempoTotalB();
		String labelX[]=new String[]{"Archivo A","Archivo B"};
		String labelY[]=getLabelY(new int[]{(int)prueba.getTiempoTotalA(),(int)prueba.getTiempoTotalB()},10);
		String titulos[]={"Archivos de prueba","Tiempo ms"};
		mostrarVentanaGraficoBarras(valores,labelX,labelY,"Prueba: "+op,titulos);				    			
	}

    private String[] getLabelY(int v[],int cantidadIntervalos)
    {
   		double mayor=GestionVector.getMayor(v);
   		double tamanioIntervalo=mayor/cantidadIntervalos;
   		int i;
   		String aux[]=new String[cantidadIntervalos];
   		for (i=0;i<aux.length-1;i++)
   		{
   			aux[i]=new String(""+Matematica.redondear(tamanioIntervalo*(i+1),4));
   		}
   		aux[aux.length-1]=new String(""+Matematica.redondear(mayor,4));   		
    	return aux;
    }     	

	private void actionTorta()
	{
		int pos=jTabbedPane1.getSelectedIndex();
		String op=jTabbedPane1.getTitleAt(jTabbedPane1.getSelectedIndex());
		if (!gestor.pruebaEjecutada(pos))
		{
			mostrarMensaje("No se ha ejecutado "+op);
			mostrarAccion("No se pudo guardar "+op);
			return;
		}

		mostrarTorta(gestor.getPrueba(pos),op);
	}

	private void mostrarTorta(Prueba prueba,String op)
	{
		int valores[]=new int[]{(int)prueba.getTiempoTotalA(),(int)prueba.getTiempoTotalB()};
		String label[]=new String[]{"ms en Archivo A","ms en Archivo B"};
		String titulo="Tiempo en ms: "+op;
		mostrarVentanaGraficoTorta(valores,label,titulo);	
	}

	private void mostrarVentanaGraficoTorta(int valores[],String label[],String titulo)
	{
		VentanaGraficoTorta ventana=new VentanaGraficoTorta (valores,label,titulo,new GestionGrafico());
		ventana.setVisible(true);			
	}        

	private void actionDetalle()
	{
		int pos=jTabbedPane1.getSelectedIndex();
		String op=jTabbedPane1.getTitleAt(jTabbedPane1.getSelectedIndex());
		if (!gestor.pruebaEjecutada(pos))
		{
			mostrarMensaje("No se ha ejecutado "+op);
			mostrarAccion("No se pudo guardar "+op);
			return;
		}

		mostrarDetalle(gestor.getPrueba(pos),op);
	}

	private void mostrarDetalle(Prueba prueba,String op)
	{
		DialogoInforme dialogo=new DialogoInforme(op,prueba.getDescripcion());
		dialogo.setVisible(true);
	}

	private void activarOp1(boolean activo)
	{
		btBarras1.setEnabled(activo);
		btTorta1.setEnabled(activo);
		btGuardar1.setEnabled(activo);
		btDet1.setEnabled(activo);
	}

	private void activarOp2(boolean activo)
	{
		btBarras2.setEnabled(activo);
		btTorta2.setEnabled(activo);
		btGuardar2.setEnabled(activo);
		btDet2.setEnabled(activo);
	}

	private void activarOp3(boolean activo)
	{
		btBarras3.setEnabled(activo);
		btTorta3.setEnabled(activo);
		btGuardar3.setEnabled(activo);
		btDet3.setEnabled(activo);
	}

	private void activarOp4(boolean activo)
	{
		btBarras4.setEnabled(activo);
		btTorta4.setEnabled(activo);
		btGuardar4.setEnabled(activo);
		btDet4.setEnabled(activo);
	}

	private void activarOp5(boolean activo)
	{
		btBarras5.setEnabled(activo);
		btTorta5.setEnabled(activo);
		btGuardar5.setEnabled(activo);
		btDet5.setEnabled(activo);
	}

	private void setearModelos()
	{
		modeloTablaInfo=new DefaultTableModel();		
		modeloTabla1=new DefaultTableModel();
		modeloTabla2=new DefaultTableModel();
		modeloTabla3=new DefaultTableModel();
		modeloTabla4=new DefaultTableModel();
		modeloTabla5=new DefaultTableModel();
		tablaInfo.setModel(modeloTablaInfo);
		jTable1.setModel(modeloTabla1);
		jTable2.setModel(modeloTabla2);
		jTable3.setModel(modeloTabla3);
		jTable4.setModel(modeloTabla4);
		jTable5.setModel(modeloTabla5);
	}

    private void setearTablas()
    {
    	modeloTablaInfo.setColumnIdentifiers(new String[]{"Item","Detalle"});
    	modeloTabla1.setColumnIdentifiers(new String[]{"Archivo","Cantidad registros","Longitud registro","Total registros","Hora inicio","Hora fin","Tiempo total","Valor Inicial","Valor final","Cantidad milisegundos"});
		modeloTabla2.setColumnIdentifiers(new String[]{"Archivo","Cantidad registros","Longitud registro","Total registros","Hora inicio","Hora fin","Tiempo total","Valor Inicial","Valor final","Cantidad milisegundos"});
		modeloTabla3.setColumnIdentifiers(new String[]{"Archivo","Cantidad registros","Longitud registro","Total registros","Hora inicio","Hora fin","Tiempo total","Valor Inicial","Valor final","Cantidad milisegundos"});
		modeloTabla4.setColumnIdentifiers(new String[]{"Archivo","Cantidad registros","Longitud registro","Total registros","Hora inicio","Hora fin","Tiempo total","Valor Inicial","Valor final","Cantidad milisegundos"});
		modeloTabla5.setColumnIdentifiers(new String[]{"Archivo","Cantidad registros","Longitud registro","Total registros","Hora inicio","Hora fin","Tiempo total","Valor Inicial","Valor final","Cantidad milisegundos"});
    	tablaInfo.getColumnModel().getColumn(0).setPreferredWidth(265);
		tablaInfo.getColumnModel().getColumn(1).setPreferredWidth(1600);
		setAnchoTabla(jTable1,modeloTabla1,120);
    	setAnchoTabla(jTable2,modeloTabla2,120);
		setAnchoTabla(jTable3,modeloTabla3,120);
		setAnchoTabla(jTable4,modeloTabla4,120);
    	setAnchoTabla(jTable5,modeloTabla5,120);
//		tablaInfo.showHorScroll(true);
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

	public void errorPrueba(Prueba prueba,Exception qq)
	{
		operacionExitosa=false;
		switch (prueba.getNumero())
		{
			case 1:operacion1Finalizada(prueba);break;
			case 2:operacion2Finalizada(prueba);break;
			case 3:operacion3Finalizada(prueba);break;
			case 4:operacion4Finalizada(prueba);break;
			case 5:operacion5Finalizada(prueba);break;
		}
		mostrarMensaje("Error prueba: "+qq.getMessage());
		GestionExcepcion.mostrarDialogo(qq);
	}

	public void pruebaFinalizada(Prueba prueba)
	{
		operacionExitosa=true;
		switch (prueba.getNumero())
		{
			case 1:operacion1Finalizada(prueba);break;
			case 2:operacion2Finalizada(prueba);break;
			case 3:operacion3Finalizada(prueba);break;
			case 4:operacion4Finalizada(prueba);break;
			case 5:operacion5Finalizada(prueba);break;
		}
		if (dialogoEspera.isVisible())
			dialogoEspera.setVisible(false);
	}

	private void mostrarPrueba(Prueba prueba,DefaultTableModel modelo)
	{
		modelo.addRow(new Object[]{"Archivo A",""+prueba.getCantRegA(),""+prueba.getLongRegA(),""+prueba.getTotalRegA(),""+prueba.getHoraInicioA(),""+prueba.getHoraFinA(),""+prueba.getTiempoTotalHoraA(),""+prueba.getValorInicioA(),""+prueba.getValorFinA(),""+prueba.getTiempoTotalA()});
		modelo.addRow(new Object[]{"Archivo B",""+prueba.getCantRegB(),""+prueba.getLongRegB(),""+prueba.getTotalRegB(),""+prueba.getHoraInicioB(),""+prueba.getHoraFinB(),""+prueba.getTiempoTotalHoraB(),""+prueba.getValorInicioB(),""+prueba.getValorFinB(),""+prueba.getTiempoTotalB()});
	}

	private void operacionIniciada()
	{
		setEnabled(false);
		operacionExitosa=false;
	}

	private void operacionFinalizada()
	{
		setEnabled(true);
		if (operacionExitosa)
			mostrarAccion("Fin exitoso de ejecucion de operacion: "+jTabbedPane1.getTitleAt(jTabbedPane1.getSelectedIndex()));		
		else
			mostrarAccion("Cancelacion de ejecucion de operacion: "+jTabbedPane1.getTitleAt(jTabbedPane1.getSelectedIndex()));		
    }

	private void operacion1Iniciada()
	{
		operacionIniciada();
		btBarras1.setEnabled(operacionExitosa);
		btTorta1.setEnabled(operacionExitosa);
		btGuardar1.setEnabled(operacionExitosa);
		btDet1.setEnabled(operacionExitosa);
		borrarFilas(modeloTabla1);
	}

	private void operacion1Finalizada(Prueba prueba)
	{
		operacionFinalizada();
		btBarras1.setEnabled(operacionExitosa);
		btTorta1.setEnabled(operacionExitosa);
		btGuardar1.setEnabled(operacionExitosa);
		btDet1.setEnabled(operacionExitosa);
		mostrarPrueba(prueba,modeloTabla1);
	}

	private void operacion2Iniciada()
	{
		operacionIniciada();
		btBarras2.setEnabled(operacionExitosa);
		btTorta2.setEnabled(operacionExitosa);
		btGuardar2.setEnabled(operacionExitosa);
		btDet2.setEnabled(operacionExitosa);
		borrarFilas(modeloTabla2);
	}

	private void operacion2Finalizada(Prueba prueba)
	{
		operacionFinalizada();
		btBarras2.setEnabled(operacionExitosa);
		btTorta2.setEnabled(operacionExitosa);
		btGuardar2.setEnabled(operacionExitosa);
		btDet2.setEnabled(operacionExitosa);
		mostrarPrueba(prueba,modeloTabla2);
	}

	private void operacion3Iniciada()
	{
		operacionIniciada();
		btBarras3.setEnabled(operacionExitosa);
		btTorta3.setEnabled(operacionExitosa);
		btGuardar3.setEnabled(operacionExitosa);
		btDet3.setEnabled(operacionExitosa);
		borrarFilas(modeloTabla3);
	}

	private void operacion3Finalizada(Prueba prueba)
	{
		operacionFinalizada();
		btBarras3.setEnabled(operacionExitosa);
		btTorta3.setEnabled(operacionExitosa);
		btGuardar3.setEnabled(operacionExitosa);
		btDet3.setEnabled(operacionExitosa);
		mostrarPrueba(prueba,modeloTabla3);
	}

	private void operacion4Iniciada()
	{
		operacionIniciada();
		btBarras4.setEnabled(operacionExitosa);
		btTorta4.setEnabled(operacionExitosa);
		btGuardar4.setEnabled(operacionExitosa);
		btDet4.setEnabled(operacionExitosa);
		borrarFilas(modeloTabla4);
	}

	private void operacion4Finalizada(Prueba prueba)
	{
		operacionFinalizada();
		btBarras4.setEnabled(operacionExitosa);
		btTorta4.setEnabled(operacionExitosa);
		btGuardar4.setEnabled(operacionExitosa);
		btDet4.setEnabled(operacionExitosa);
		mostrarPrueba(prueba,modeloTabla4);
	}

	private void operacion5Iniciada()
	{
		operacionIniciada();
		btBarras5.setEnabled(operacionExitosa);
		btTorta5.setEnabled(operacionExitosa);
		btGuardar5.setEnabled(operacionExitosa);
		btDet5.setEnabled(operacionExitosa);
		borrarFilas(modeloTabla5);
	}

	private void operacion5Finalizada(Prueba prueba)
	{
		operacionFinalizada();
		btBarras5.setEnabled(operacionExitosa);
		btTorta5.setEnabled(operacionExitosa);
		btGuardar5.setEnabled(operacionExitosa);
		btDet5.setEnabled(operacionExitosa);
		mostrarPrueba(prueba,modeloTabla5);
	}

	private void actionEjecutar1()
	{
		if (!ValoresPrueba.archivosCreados())
		{
			mostrarMensaje("No se han creado los archivos de prueba");
			return;
		}
		long longReg1;
		try
		{
			longReg1=Long.parseLong(txtRegA1.getText());
		} catch (Exception qq)
		{
			mostrarMensaje("La longitud de registro A de operacion 1 no se corresponde con una longitud valida");
			mostrarAccion("Cantidad de registros de operacion 1 inapropiada");
			return;
		}
		long longReg2;
		try
		{
			longReg2=Long.parseLong(txtRegB1.getText());
		} catch (Exception qq)
		{
			mostrarMensaje("La longitud de registro B de operacion 1 no se corresponde con una longitud valida");
			mostrarAccion("Cantidad de registros de operacion 1 inapropiada");
			return;
		}
		if (longReg1<1024)
		{
			mostrarMensaje("La cantidad de registros minima es 1024");
			mostrarAccion("Cantidad de registros de archivo 1 inapropiada");
			return;
		}
		if (longReg2<1024)
		{
			mostrarMensaje("La cantidad de registros minima es 1024");
			mostrarAccion("Cantidad de registros de archivo 2 inapropiada");
			return;
		}

		ValoresPrueba.CANTIDAD_REG_A_1=longReg1;
		ValoresPrueba.CANTIDAD_REG_B_1=longReg2;
		operacion1Iniciada();
		ejecutar();
	}

	private void ejecutar()
	{
		String op=jTabbedPane1.getTitleAt(jTabbedPane1.getSelectedIndex());
		mostrarAccion("Inicio de ejecucion de operacion: "+op);		
		dialogoEspera.setVisible(op);
		gestor.ejecutarPrueba(jTabbedPane1.getSelectedIndex());
	}

	private void actionEjecutar2()
	{
		if (!ValoresPrueba.archivosCreados())
		{
			mostrarMensaje("No se han creado los archivos de prueba");
			return;
		}
		if (!gestor.grabacionEjecutada())
		{
			mostrarMensaje("No se ha ejecutado prueba de grabacion inicial");
			return;
		}
		long longReg1;
		try
		{
			longReg1=Long.parseLong(txtRegA2.getText());
		} catch (Exception qq)
		{
			mostrarMensaje("La longitud de registro A de operacion 2 no se corresponde con una longitud valida");
			mostrarAccion("Cantidad de registros de operacion 2 inapropiada");
			return;
		}
		long longReg2;
		try
		{
			longReg2=Long.parseLong(txtRegB2.getText());
		} catch (Exception qq)
		{
			mostrarMensaje("La longitud de registro B de operacion 2 no se corresponde con una longitud valida");
			mostrarAccion("Cantidad de registros de operacion 2 inapropiada");
			return;
		}
		if (longReg1>ValoresPrueba.CANTIDAD_REG_A_1)
		{
			mostrarMensaje("La longitud de registro A de operacion 2 no puede ser mayor que la cantidad de registrado grabados en operacion 1");
			mostrarAccion("Cantidad de registros de operacion 2 inapropiada");
			return;
		}
		if (longReg2>ValoresPrueba.CANTIDAD_REG_B_1)
		{
			mostrarMensaje("La longitud de registro B de operacion 2 no puede ser mayor que la cantidad de registrado grabados en operacion 1");
			mostrarAccion("Cantidad de registros de operacion 2 inapropiada");
			return;
		}

		ValoresPrueba.CANTIDAD_REG_A_2=longReg1;
		ValoresPrueba.CANTIDAD_REG_B_2=longReg2;
		operacion2Iniciada();
		ejecutar();
	}

	private void actionEjecutar3()
	{
		if (!ValoresPrueba.archivosCreados())
		{
			mostrarMensaje("No se han creado los archivos de prueba");
			return;
		}
		if (!gestor.grabacionEjecutada())
		{
			mostrarMensaje("No se ha ejecutado prueba de grabacion inicial");
			return;
		}
		long longReg1;
		try
		{
			longReg1=Long.parseLong(txtRegA3.getText());
		} catch (Exception qq)
		{
			mostrarMensaje("La longitud de registro A de operacion 3 no se corresponde con una longitud valida");
			mostrarAccion("Cantidad de registros de operacion 3 inapropiada");
			return;
		}
		long longReg2;
		try
		{
			longReg2=Long.parseLong(txtRegB3.getText());
		} catch (Exception qq)
		{
			mostrarMensaje("La longitud de registro B de operacion 3 no se corresponde con una longitud valida");
			mostrarAccion("Cantidad de registros de operacion 3 inapropiada");
			return;
		}
		if (longReg1>ValoresPrueba.CANTIDAD_REG_A_1)
		{
			mostrarMensaje("La longitud de registro A de operacion 3 no puede ser mayor que la cantidad de registrado grabados en operacion 1");
			mostrarAccion("Cantidad de registros de operacion 3 inapropiada");
			return;
		}
		if (longReg2>ValoresPrueba.CANTIDAD_REG_B_1)
		{
			mostrarMensaje("La longitud de registro B de operacion 3 no puede ser mayor que la cantidad de registrado grabados en operacion 1");
			mostrarAccion("Cantidad de registros de operacion 3 inapropiada");
			return;
		}

		ValoresPrueba.CANTIDAD_REG_A_3=longReg1;
		ValoresPrueba.CANTIDAD_REG_B_3=longReg2;
		operacion3Iniciada();
		ejecutar();
	}

	private void actionEjecutar4()
	{
		if (!ValoresPrueba.archivosCreados())
		{
			mostrarMensaje("No se han creado los archivos de prueba");
			return;
		}
		if (!gestor.grabacionEjecutada())
		{
			mostrarMensaje("No se ha ejecutado prueba de grabacion inicial");
			return;
		}
		long longReg1;
		try
		{
			longReg1=Long.parseLong(txtRegA4.getText());
		} catch (Exception qq)
		{
			mostrarMensaje("La longitud de registro A de operacion 4 no se corresponde con una longitud valida");
			mostrarAccion("Cantidad de registros de operacion 4 inapropiada");
			return;
		}
		long longReg2;
		try
		{
			longReg2=Long.parseLong(txtRegB4.getText());
		} catch (Exception qq)
		{
			mostrarMensaje("La longitud de registro B de operacion 4 no se corresponde con una longitud valida");
			mostrarAccion("Cantidad de registros de operacion 4 inapropiada");
			return;
		}
		if (longReg1>ValoresPrueba.CANTIDAD_REG_A_1)
		{
			mostrarMensaje("La longitud de registro A de operacion 4 no puede ser mayor que la cantidad de registrado grabados en operacion 1");
			mostrarAccion("Cantidad de registros de operacion 4 inapropiada");
			return;
		}
		if (longReg2>ValoresPrueba.CANTIDAD_REG_B_1)
		{
			mostrarMensaje("La longitud de registro B de operacion 4 no puede ser mayor que la cantidad de registrado grabados en operacion 1");
			mostrarAccion("Cantidad de registros de operacion 4 inapropiada");
			return;
		}

		ValoresPrueba.CANTIDAD_REG_A_4=longReg1;
		ValoresPrueba.CANTIDAD_REG_B_4=longReg2;
		operacion4Iniciada();
		ejecutar();
	}

	private void actionEjecutar5()
	{
		if (!ValoresPrueba.archivosCreados())
		{
			mostrarMensaje("No se han creado los archivos de prueba");
			return;
		}
		if (!gestor.grabacionEjecutada())
		{
			mostrarMensaje("No se ha ejecutado prueba de grabacion inicial");
			return;
		}
		long longReg1;
		try
		{
			longReg1=Long.parseLong(txtRegA5.getText());
		} catch (Exception qq)
		{
			mostrarMensaje("La longitud de registro A de operacion 5 no se corresponde con una longitud valida");
			mostrarAccion("Cantidad de registros de operacion 5 inapropiada");
			return;
		}
		long longReg2;
		try
		{
			longReg2=Long.parseLong(txtRegB5.getText());
		} catch (Exception qq)
		{
			mostrarMensaje("La longitud de registro B de operacion 5 no se corresponde con una longitud valida");
			mostrarAccion("Cantidad de registros de operacion 5 inapropiada");
			return;
		}
		if (longReg1>ValoresPrueba.CANTIDAD_REG_A_1)
		{
			mostrarMensaje("La longitud de registro A de operacion 5 no puede ser mayor que la cantidad de registrado grabados en operacion 1");
			mostrarAccion("Cantidad de registros de operacion 5 inapropiada");
			return;
		}
		if (longReg2>ValoresPrueba.CANTIDAD_REG_B_1)
		{
			mostrarMensaje("La longitud de registro B de operacion 5 no puede ser mayor que la cantidad de registrado grabados en operacion 1");
			mostrarAccion("Cantidad de registros de operacion 5 inapropiada");
			return;
		}

		ValoresPrueba.CANTIDAD_REG_A_5=longReg1;
		ValoresPrueba.CANTIDAD_REG_B_5=longReg2;
		operacion4Iniciada();
		ejecutar();
	}

    private void setearFrame()
    {
        setTitle(Main.NOMBRE+" "+Main.VERSION+": Modulo Benchmark");
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtNom1 = new javax.swing.JTextField();
        txtLong1 = new javax.swing.JTextField();
        txtEst1 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtNom2 = new javax.swing.JTextField();
        txtLong2 = new javax.swing.JTextField();
        txtEst2 = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        btParametros = new javax.swing.JButton();
        btNuevo = new javax.swing.JButton();
        jSplitPane3 = new javax.swing.JSplitPane();
        jPanel4 = new javax.swing.JPanel();
        jSplitPane4 = new javax.swing.JSplitPane();
        jPanel6 = new javax.swing.JPanel();
        btSistema = new javax.swing.JButton();
        btOp1 = new javax.swing.JButton();
        btOp2 = new javax.swing.JButton();
        btOp3 = new javax.swing.JButton();
        btOp4 = new javax.swing.JButton();
        btOp5 = new javax.swing.JButton();
		btGuardar = new javax.swing.JButton();
		btDescripcion = new javax.swing.JButton();
		btEstructura = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel14 = new javax.swing.JPanel();
        jSplitPane23 = new javax.swing.JSplitPane();
        jPanel49 = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTextPane8 = new javax.swing.JTextPane();
        jPanel50 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaInfo = new Tabla();
        jPanel13 = new javax.swing.JPanel();
        jSplitPane20 = new javax.swing.JSplitPane();
        jPanel43 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTextPane7 = new javax.swing.JTextPane();
        jPanel44 = new javax.swing.JPanel();
        jSplitPane21 = new javax.swing.JSplitPane();
        jPanel45 = new javax.swing.JPanel();
        jSplitPane22 = new javax.swing.JSplitPane();
        jPanel46 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        txtRegA1 = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        txtRegB1 = new javax.swing.JTextField();
        jPanel47 = new javax.swing.JPanel();
        tabla6 = new javax.swing.JScrollPane();
        jTable1 = new Tabla();
        jPanel48 = new javax.swing.JPanel();
        btEjecutar1 = new javax.swing.JButton();
        btGuardar1 = new javax.swing.JButton();
        btBarras1 = new javax.swing.JButton();
        btTorta1 = new javax.swing.JButton();
        btDet1 = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jSplitPane8 = new javax.swing.JSplitPane();
        jPanel19 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextPane3 = new javax.swing.JTextPane();
        jPanel20 = new javax.swing.JPanel();
        jSplitPane9 = new javax.swing.JSplitPane();
        jPanel21 = new javax.swing.JPanel();
        jSplitPane10 = new javax.swing.JSplitPane();
        jPanel22 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        txtRegA2 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtRegB2 = new javax.swing.JTextField();
        jPanel23 = new javax.swing.JPanel();
        tabla2 = new javax.swing.JScrollPane();
        jTable2 = new Tabla();
        jPanel51 = new javax.swing.JPanel();
        btEjecutar2 = new javax.swing.JButton();
        btGuardar2 = new javax.swing.JButton();
        btBarras2 = new javax.swing.JButton();
        btTorta2 = new javax.swing.JButton();
        btDet2 = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        jSplitPane11 = new javax.swing.JSplitPane();
        jPanel25 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextPane4 = new javax.swing.JTextPane();
        jPanel26 = new javax.swing.JPanel();
        jSplitPane12 = new javax.swing.JSplitPane();
        jPanel27 = new javax.swing.JPanel();
        jSplitPane13 = new javax.swing.JSplitPane();
        jPanel28 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        txtRegA3 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtRegB3 = new javax.swing.JTextField();
        jPanel29 = new javax.swing.JPanel();
        tabla3 = new javax.swing.JScrollPane();
        jTable3 = new Tabla();
        jPanel52 = new javax.swing.JPanel();
        btEjecutar3 = new javax.swing.JButton();
        btGuardar3 = new javax.swing.JButton();
        btBarras3 = new javax.swing.JButton();
        btTorta3 = new javax.swing.JButton();
        btDet3 = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        jSplitPane14 = new javax.swing.JSplitPane();
        jPanel31 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTextPane5 = new javax.swing.JTextPane();
        jPanel32 = new javax.swing.JPanel();
        jSplitPane15 = new javax.swing.JSplitPane();
        jPanel33 = new javax.swing.JPanel();
        jSplitPane16 = new javax.swing.JSplitPane();
        jPanel34 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        txtRegA4 = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtRegB4 = new javax.swing.JTextField();
        jPanel35 = new javax.swing.JPanel();
        tabla4 = new javax.swing.JScrollPane();
        jTable4 = new Tabla();
        jPanel53 = new javax.swing.JPanel();
        btEjecutar4 = new javax.swing.JButton();
        btGuardar4 = new javax.swing.JButton();
        btBarras4 = new javax.swing.JButton();
        btTorta4 = new javax.swing.JButton();
        btDet4 = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        jSplitPane17 = new javax.swing.JSplitPane();
        jPanel37 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTextPane6 = new javax.swing.JTextPane();
        jPanel38 = new javax.swing.JPanel();
        jSplitPane18 = new javax.swing.JSplitPane();
        jPanel39 = new javax.swing.JPanel();
        jSplitPane19 = new javax.swing.JSplitPane();
        jPanel40 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        txtRegA5 = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txtRegB5 = new javax.swing.JTextField();
        jPanel41 = new javax.swing.JPanel();
        tabla5 = new javax.swing.JScrollPane();
        jTable5 = new Tabla();
        jPanel54 = new javax.swing.JPanel();
        btEjecutar5 = new javax.swing.JButton();
        btGuardar5 = new javax.swing.JButton();
        btBarras5 = new javax.swing.JButton();
        btTorta5 = new javax.swing.JButton();
        btDet5 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtAcciones = new javax.swing.JTextPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        itemSalir = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        itemAcercaDe = new javax.swing.JMenuItem();
        itemDescripcion = new javax.swing.JMenuItem();
        itemAcelerador = new javax.swing.JMenuItem();

        getContentPane().setLayout(new java.awt.GridLayout(1, 1));

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                exitForm(evt);
            }
        });

        jSplitPane1.setDividerLocation(120);
        jSplitPane1.setDividerSize(3);
        jSplitPane1.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        jSplitPane1.setEnabled(false);
        jPanel1.setLayout(new java.awt.GridLayout(1, 1));

        jPanel1.setBorder(new javax.swing.border.TitledBorder("Archivos de benchmark"));
        jSplitPane2.setDividerLocation(540);
        jSplitPane2.setDividerSize(1);
        jSplitPane2.setEnabled(false);
        jPanel2.setLayout(new java.awt.GridLayout(3, 4, 5, 5));

        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 5));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Archivo");
        jPanel2.add(jLabel1);

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Nombre (.DAT)");
        jPanel2.add(jLabel2);

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Long. registro (bytes)");
        jPanel2.add(jLabel3);

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Estado");
        jPanel2.add(jLabel6);

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Archivo 1");
        jPanel2.add(jLabel4);

        txtNom1.setEditable(false);
        txtNom1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNom1.setText("TP91");
        jPanel2.add(txtNom1);

        txtLong1.setEditable(false);
        txtLong1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtLong1.setText("512");
        jPanel2.add(txtLong1);

        txtEst1.setEditable(false);
        txtEst1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtEst1.setText("No creado");
        jPanel2.add(txtEst1);

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Archivo 2");
        jPanel2.add(jLabel5);

        txtNom2.setEditable(false);
        txtNom2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNom2.setText("TP92");
        jPanel2.add(txtNom2);

        txtLong2.setEditable(false);
        txtLong2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtLong2.setText("4096");
        jPanel2.add(txtLong2);

        txtEst2.setEditable(false);
        txtEst2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtEst2.setText("No creado");
        jPanel2.add(txtEst2);

        jSplitPane2.setLeftComponent(jPanel2);

        jPanel3.setLayout(new java.awt.GridLayout(1, 2, 5, 5));

        jPanel3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 5, true));
        btParametros.setIcon(new javax.swing.ImageIcon("recursos/imagenes/editar.jpeg"));
        btParametros.setToolTipText("Editar los archivos de prueba");
        btParametros.setMinimumSize(new java.awt.Dimension(40, 35));
        btParametros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actionParametros(evt);
            }
        });

        jPanel3.add(btParametros);

        btNuevo.setIcon(new javax.swing.ImageIcon("recursos/imagenes/nuevo.jpeg"));
        btNuevo.setToolTipText("Crea los archivos de benchmark de acuerdo a la definicion de atributos establecida");
        btNuevo.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btNuevo.setIconTextGap(10);
        btNuevo.setMaximumSize(new java.awt.Dimension(59, 35));
        btNuevo.setMinimumSize(new java.awt.Dimension(40, 35));
        btNuevo.setAutoscrolls(true);
        btNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actionNuevo(evt);
            }
        });

        jPanel3.add(btNuevo);

        jSplitPane2.setRightComponent(jPanel3);

        jPanel1.add(jSplitPane2);

        jSplitPane1.setLeftComponent(jPanel1);

        jSplitPane3.setDividerLocation(310);
        jSplitPane3.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        jSplitPane3.setEnabled(false);
        jPanel4.setLayout(new java.awt.GridLayout(1, 1));

        jPanel4.setBorder(new javax.swing.border.TitledBorder("Benchmark"));
        jSplitPane4.setDividerLocation(50);
        jSplitPane4.setDividerSize(2);
        jSplitPane4.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        jPanel6.setLayout(new java.awt.GridLayout(1, 7, 5, 5));

        jPanel6.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 5));
        btSistema.setIcon(new javax.swing.ImageIcon("recursos/imagenes/sistema.jpeg"));
        btSistema.setMnemonic('f');
        btSistema.setToolTipText("Informaci\u00f3n del sistema");
        btSistema.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actionSistema(evt);
            }
        });

        jPanel6.add(btSistema);

        btOp1.setIcon(new javax.swing.ImageIcon("recursos/imagenes/o1.jpeg"));
        btOp1.setMnemonic('1');
        btOp1.setToolTipText("Abrir y grabar");
        btOp1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actionOp1(evt);
            }
        });

        jPanel6.add(btOp1);

        btOp2.setIcon(new javax.swing.ImageIcon("recursos/imagenes/o2.jpeg"));
        btOp2.setMnemonic('2');
        btOp2.setToolTipText("Leer secuencialmente");
        btOp2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actionOp2(evt);
            }
        });

        jPanel6.add(btOp2);

        btOp3.setIcon(new javax.swing.ImageIcon("recursos/imagenes/o3.jpeg"));
        btOp3.setMnemonic('3');
        btOp3.setToolTipText("Leer y regrabar secuencialmente");
        btOp3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actionOp3(evt);
            }
        });

        jPanel6.add(btOp3);

        btOp4.setIcon(new javax.swing.ImageIcon("recursos/imagenes/o4.JPG"));
        btOp4.setMnemonic('4');
        btOp4.setToolTipText("Leer aleatoriamente");
        btOp4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actionOp4(evt);
            }
        });

        jPanel6.add(btOp4);

        btOp5.setIcon(new javax.swing.ImageIcon("recursos/imagenes/o5.JPG"));
        btOp5.setMnemonic('5');
        btOp5.setToolTipText("Leer y regrabar en forma aleatoria");
        btOp5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actionOp5(evt);
            }
        });

        jPanel6.add(btOp5);

        btEstructura.setIcon(new javax.swing.ImageIcon("recursos/imagenes/estructura.Jpeg"));
        btEstructura.setMnemonic('e');
        btEstructura.setToolTipText("Modificar estructura de archivos");
        btEstructura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actionEstructura();
            }
        });

        jPanel6.add(btEstructura);

        btGuardar.setIcon(new javax.swing.ImageIcon("recursos/imagenes/guardar.jpeg"));
        btGuardar.setMnemonic('g');
        btGuardar.setToolTipText("Guardar el benchmark");
        btGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actionGuardarTodo();
            }
        });

        jPanel6.add(btGuardar);

        btDescripcion.setIcon(new javax.swing.ImageIcon("recursos/imagenes/descripcion.jpeg"));
        btDescripcion.setMnemonic('o');
        btDescripcion.setToolTipText("Descripcion de "+Main.NOMBRE);
        btDescripcion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actionDescripcion();
            }
        });

        jPanel6.add(btDescripcion);

        jSplitPane4.setLeftComponent(jPanel6);

        jPanel7.setLayout(new java.awt.GridLayout(1, 1));

        jPanel14.setLayout(new java.awt.GridLayout());

        jSplitPane23.setDividerLocation(155);
        jSplitPane23.setDividerSize(2);
        jSplitPane23.setEnabled(false);
        jPanel49.setLayout(new java.awt.GridLayout());

        jPanel49.setBorder(new javax.swing.border.TitledBorder("Explicacion"));
        jTextPane8.setEditable(false);
        jTextPane8.setText("Muestra informaci\u00f3n sobre el sistema sobre el que se ejecutan las pruebas");
        jScrollPane8.setViewportView(jTextPane8);

        jPanel49.add(jScrollPane8);

        jSplitPane23.setLeftComponent(jPanel49);

        jPanel50.setLayout(new java.awt.GridLayout());

        jPanel50.setBorder(new javax.swing.border.TitledBorder("Informacion"));
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
        jScrollPane2.setViewportView(tablaInfo);

        jPanel50.add(jScrollPane2);

        jSplitPane23.setRightComponent(jPanel50);

        jPanel14.add(jSplitPane23);

        jTabbedPane1.addTab("Informacion del sistema", jPanel14);

        jPanel13.setLayout(new java.awt.GridLayout());

        jSplitPane20.setDividerLocation(155);
        jSplitPane20.setDividerSize(2);
        jSplitPane20.setEnabled(false);
        jPanel43.setLayout(new java.awt.GridLayout());

        jPanel43.setBorder(new javax.swing.border.TitledBorder("Explicacion"));
        jTextPane7.setEditable(false);
        jTextPane7.setText("Leer en forma aleatoria de los archivos registros y regrabarlos");
        jScrollPane7.setViewportView(jTextPane7);

        jPanel43.add(jScrollPane7);

        jSplitPane20.setLeftComponent(jPanel43);

        jPanel44.setLayout(new java.awt.GridLayout());

        jSplitPane21.setDividerLocation(150);
        jSplitPane21.setDividerSize(1);
        jSplitPane21.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        jPanel45.setLayout(new java.awt.GridLayout());

        jSplitPane22.setDividerLocation(50);
        jSplitPane22.setDividerSize(1);
        jSplitPane22.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        jPanel46.setLayout(new java.awt.GridLayout());

        jPanel46.setBorder(new javax.swing.border.TitledBorder("Parametros de ejecucion"));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("Cantidad registros A");
        jPanel46.add(jLabel17);

        txtRegA1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtRegA1.setText("10240");
        jPanel46.add(txtRegA1);

        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("Cantidad registros B");
        jPanel46.add(jLabel18);

        txtRegB1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtRegB1.setText("1280");
        jPanel46.add(txtRegB1);

        jSplitPane22.setLeftComponent(jPanel46);

        jPanel47.setLayout(new java.awt.GridLayout());

        jPanel47.setBorder(new javax.swing.border.TitledBorder("Resultado de ejecucion"));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        tabla6.setViewportView(jTable1);

        jPanel47.add(tabla6);

        jSplitPane22.setRightComponent(jPanel47);

        jPanel45.add(jSplitPane22);

        jSplitPane21.setLeftComponent(jPanel45);

        jPanel48.setLayout(new java.awt.GridLayout());

        btEjecutar1.setIcon(new javax.swing.ImageIcon("recursos/imagenes/ejecutar.jpeg"));
        btEjecutar1.setToolTipText("Ejecutar prueba");
        btEjecutar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actionEjecutar1();
            }
        });
        jPanel48.add(btEjecutar1);

        btGuardar1.setIcon(new javax.swing.ImageIcon("recursos/imagenes/guardar.jpeg"));
        btGuardar1.setToolTipText("Guardar prueba");
        btGuardar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actionGuardar();
            }
        });
        jPanel48.add(btGuardar1);

        btBarras1.setIcon(new javax.swing.ImageIcon("recursos/imagenes/barras.jpeg"));
        btBarras1.setToolTipText("Ver grafico de Barras");
        btBarras1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actionBarras();
            }
        });
        jPanel48.add(btBarras1);

        btTorta1.setIcon(new javax.swing.ImageIcon("recursos/imagenes/torta.jpeg"));
        btTorta1.setToolTipText("Ver grafico de Torta");
        btTorta1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actionTorta();
            }
        });
        jPanel48.add(btTorta1);

        btDet1.setIcon(new javax.swing.ImageIcon("recursos/imagenes/detalle.jpeg"));
        btDet1.setToolTipText("Ver detalle de la prueba");
        btDet1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actionDetalle();
            }
        });
        jPanel48.add(btDet1);

        jSplitPane21.setRightComponent(jPanel48);

        jPanel44.add(jSplitPane21);

        jSplitPane20.setRightComponent(jPanel44);

        jPanel13.add(jSplitPane20);

        jTabbedPane1.addTab("Abrir y grabar secuencialmente", jPanel13);

        jPanel9.setLayout(new java.awt.GridLayout());

        jSplitPane8.setDividerLocation(155);
        jSplitPane8.setDividerSize(2);
        jSplitPane8.setEnabled(false);
        jPanel19.setLayout(new java.awt.GridLayout());

        jPanel19.setBorder(new javax.swing.border.TitledBorder("Explicacion"));
        jTextPane3.setEditable(false);
        jTextPane3.setText("Leer secuencialmente de los archivos registros");
        jScrollPane3.setViewportView(jTextPane3);

        jPanel19.add(jScrollPane3);

        jSplitPane8.setLeftComponent(jPanel19);

        jPanel20.setLayout(new java.awt.GridLayout());

        jSplitPane9.setDividerLocation(150);
        jSplitPane9.setDividerSize(1);
        jSplitPane9.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        jPanel21.setLayout(new java.awt.GridLayout());

        jSplitPane10.setDividerLocation(50);
        jSplitPane10.setDividerSize(1);
        jSplitPane10.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        jPanel22.setLayout(new java.awt.GridLayout());

        jPanel22.setBorder(new javax.swing.border.TitledBorder("Parametros de ejecucion"));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Cantidad registros A");
        jPanel22.add(jLabel9);

        txtRegA2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtRegA2.setText("10240");
        jPanel22.add(txtRegA2);

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Cantidad registros B");
        jPanel22.add(jLabel10);

        txtRegB2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtRegB2.setText("1280");
        jPanel22.add(txtRegB2);

        jSplitPane10.setLeftComponent(jPanel22);

        jPanel23.setLayout(new java.awt.GridLayout());

        jPanel23.setBorder(new javax.swing.border.TitledBorder("Resultado de ejecucion"));
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
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
        tabla2.setViewportView(jTable2);

        jPanel23.add(tabla2);

        jSplitPane10.setRightComponent(jPanel23);

        jPanel21.add(jSplitPane10);

        jSplitPane9.setLeftComponent(jPanel21);

        jPanel51.setLayout(new java.awt.GridLayout());

        btEjecutar2.setIcon(new javax.swing.ImageIcon("recursos/imagenes/ejecutar.jpeg"));
        btEjecutar2.setToolTipText("Ejecutar prueba");
        btEjecutar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actionEjecutar2();
            }
		});
        jPanel51.add(btEjecutar2);

        btGuardar2.setIcon(new javax.swing.ImageIcon("recursos/imagenes/guardar.jpeg"));
        btGuardar2.setToolTipText("Guardar prueba");
        btGuardar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actionGuardar();
            }
        });
        jPanel51.add(btGuardar2);

        btBarras2.setIcon(new javax.swing.ImageIcon("recursos/imagenes/barras.jpeg"));
        btBarras2.setToolTipText("Ver grafico de Barras");
        btBarras2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actionBarras();
            }
        });
        jPanel51.add(btBarras2);

        btTorta2.setIcon(new javax.swing.ImageIcon("recursos/imagenes/torta.jpeg"));
        btTorta2.setToolTipText("Ver grafico de Torta");
        btTorta2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actionTorta();
            }
        });
        jPanel51.add(btTorta2);

        btDet2.setIcon(new javax.swing.ImageIcon("recursos/imagenes/detalle.jpeg"));
        btDet2.setToolTipText("Ver detalle de la prueba");
        btDet2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actionDetalle();
            }
        });
        jPanel51.add(btDet2);

        jSplitPane9.setRightComponent(jPanel51);

        jPanel20.add(jSplitPane9);

        jSplitPane8.setRightComponent(jPanel20);

        jPanel9.add(jSplitPane8);

        jTabbedPane1.addTab("Leer secuencialmente", jPanel9);

        jPanel10.setLayout(new java.awt.GridLayout());

        jSplitPane11.setDividerLocation(155);
        jSplitPane11.setDividerSize(2);
        jSplitPane11.setEnabled(false);
        jPanel25.setLayout(new java.awt.GridLayout());

        jPanel25.setBorder(new javax.swing.border.TitledBorder("Explicacion"));
        jTextPane4.setEditable(false);
        jTextPane4.setText("Leer secuencialmente de los archivos registros y regrabarlos");
        jScrollPane4.setViewportView(jTextPane4);

        jPanel25.add(jScrollPane4);

        jSplitPane11.setLeftComponent(jPanel25);

        jPanel26.setLayout(new java.awt.GridLayout());

        jSplitPane12.setDividerLocation(150);
        jSplitPane12.setDividerSize(1);
        jSplitPane12.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        jPanel27.setLayout(new java.awt.GridLayout());

        jSplitPane13.setDividerLocation(50);
        jSplitPane13.setDividerSize(1);
        jSplitPane13.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        jPanel28.setLayout(new java.awt.GridLayout());

        jPanel28.setBorder(new javax.swing.border.TitledBorder("Parametros de ejecucion"));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Cantidad registros A");
        jPanel28.add(jLabel11);

        txtRegA3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtRegA3.setText("10240");
        jPanel28.add(txtRegA3);

        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Cantidad registros B");
        jPanel28.add(jLabel12);

        txtRegB3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtRegB3.setText("1280");
        jPanel28.add(txtRegB3);

        jSplitPane13.setLeftComponent(jPanel28);

        jPanel29.setLayout(new java.awt.GridLayout());

        jPanel29.setBorder(new javax.swing.border.TitledBorder("Resultado de ejecucion"));
        jTable3.setModel(new javax.swing.table.DefaultTableModel(
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
        tabla3.setViewportView(jTable3);

        jPanel29.add(tabla3);

        jSplitPane13.setRightComponent(jPanel29);

        jPanel27.add(jSplitPane13);

        jSplitPane12.setLeftComponent(jPanel27);

        jPanel52.setLayout(new java.awt.GridLayout());

        btEjecutar3.setIcon(new javax.swing.ImageIcon("recursos/imagenes/ejecutar.jpeg"));
        btEjecutar3.setToolTipText("Ejecutar prueba");
        btEjecutar3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actionEjecutar3();
            }
        });
        jPanel52.add(btEjecutar3);

        btGuardar3.setIcon(new javax.swing.ImageIcon("recursos/imagenes/guardar.jpeg"));
        btGuardar3.setToolTipText("Guardar prueba");
        btGuardar3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actionGuardar();
            }
        });
        jPanel52.add(btGuardar3);

        btBarras3.setIcon(new javax.swing.ImageIcon("recursos/imagenes/barras.jpeg"));
        btBarras3.setToolTipText("Ver grafico de Barras");
        btBarras3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actionBarras();
            }
        });
        jPanel52.add(btBarras3);

        btTorta3.setIcon(new javax.swing.ImageIcon("recursos/imagenes/torta.jpeg"));
        btTorta3.setToolTipText("Ver grafico de Torta");
        btTorta3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actionTorta();
            }
        });
        jPanel52.add(btTorta3);

        btDet3.setIcon(new javax.swing.ImageIcon("recursos/imagenes/detalle.jpeg"));
        btDet3.setToolTipText("Ver detalle de la prueba");
        btDet3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actionDetalle();
            }
        });
        jPanel52.add(btDet3);

        jSplitPane12.setRightComponent(jPanel52);

        jPanel26.add(jSplitPane12);

        jSplitPane11.setRightComponent(jPanel26);

        jPanel10.add(jSplitPane11);

        jTabbedPane1.addTab("Leer y regrabar secuencialmente", jPanel10);

        jPanel11.setLayout(new java.awt.GridLayout());

        jSplitPane14.setDividerLocation(155);
        jSplitPane14.setDividerSize(2);
        jSplitPane14.setEnabled(false);
        jPanel31.setLayout(new java.awt.GridLayout());

        jPanel31.setBorder(new javax.swing.border.TitledBorder("Explicacion"));
        jTextPane5.setEditable(false);
        jTextPane5.setText("Leer en forma aleatoria de los archivos registros");
        jScrollPane5.setViewportView(jTextPane5);

        jPanel31.add(jScrollPane5);

        jSplitPane14.setLeftComponent(jPanel31);

        jPanel32.setLayout(new java.awt.GridLayout());

        jSplitPane15.setDividerLocation(150);
        jSplitPane15.setDividerSize(1);
        jSplitPane15.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        jPanel33.setLayout(new java.awt.GridLayout());

        jSplitPane16.setDividerLocation(50);
        jSplitPane16.setDividerSize(1);
        jSplitPane16.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        jPanel34.setLayout(new java.awt.GridLayout());

        jPanel34.setBorder(new javax.swing.border.TitledBorder("Parametros de ejecucion"));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Cantidad registros A");
        jPanel34.add(jLabel13);

        txtRegA4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtRegA4.setText("10240");
        jPanel34.add(txtRegA4);

        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Cantidad registros B");
        jPanel34.add(jLabel14);

        txtRegB4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtRegB4.setText("1280");
        jPanel34.add(txtRegB4);

        jSplitPane16.setLeftComponent(jPanel34);

        jPanel35.setLayout(new java.awt.GridLayout());

        jPanel35.setBorder(new javax.swing.border.TitledBorder("Resultado de ejecucion"));
        jTable4.setModel(new javax.swing.table.DefaultTableModel(
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
        tabla4.setViewportView(jTable4);

        jPanel35.add(tabla4);

        jSplitPane16.setRightComponent(jPanel35);

        jPanel33.add(jSplitPane16);

        jSplitPane15.setLeftComponent(jPanel33);

        jPanel53.setLayout(new java.awt.GridLayout());

        btEjecutar4.setIcon(new javax.swing.ImageIcon("recursos/imagenes/ejecutar.jpeg"));
        btEjecutar4.setToolTipText("Ejecutar prueba");
        btEjecutar4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actionEjecutar4();
            }
        });
        jPanel53.add(btEjecutar4);

        btGuardar4.setIcon(new javax.swing.ImageIcon("recursos/imagenes/guardar.jpeg"));
        btGuardar4.setToolTipText("Guardar prueba");
        btGuardar4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actionGuardar();
            }
        });
        jPanel53.add(btGuardar4);

        btBarras4.setIcon(new javax.swing.ImageIcon("recursos/imagenes/barras.jpeg"));
        btBarras4.setToolTipText("Ver grafico de Barras");
        btBarras4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actionBarras();
            }
        });
        jPanel53.add(btBarras4);

        btTorta4.setIcon(new javax.swing.ImageIcon("recursos/imagenes/torta.jpeg"));
        btTorta4.setToolTipText("Ver grafico de Torta");
        btTorta4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actionTorta();
            }
        });
        jPanel53.add(btTorta4);

        btDet4.setIcon(new javax.swing.ImageIcon("recursos/imagenes/detalle.jpeg"));
        btDet4.setToolTipText("Ver detalle de la prueba");
        btDet4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actionDetalle();
            }
        });
        jPanel53.add(btDet4);

        jSplitPane15.setRightComponent(jPanel53);

        jPanel32.add(jSplitPane15);

        jSplitPane14.setRightComponent(jPanel32);

        jPanel11.add(jSplitPane14);

        jTabbedPane1.addTab("Leer aleatoriamente", jPanel11);

        jPanel12.setLayout(new java.awt.GridLayout());

        jSplitPane17.setDividerLocation(155);
        jSplitPane17.setDividerSize(2);
        jSplitPane17.setEnabled(false);
        jPanel37.setLayout(new java.awt.GridLayout());

        jPanel37.setBorder(new javax.swing.border.TitledBorder("Explicacion"));
        jTextPane6.setEditable(false);
        jTextPane6.setText("Leer en forma aleatoria de los archivos registros y regrabarlos");
        jScrollPane6.setViewportView(jTextPane6);

        jPanel37.add(jScrollPane6);

        jSplitPane17.setLeftComponent(jPanel37);

        jPanel38.setLayout(new java.awt.GridLayout());

        jSplitPane18.setDividerLocation(150);
        jSplitPane18.setDividerSize(1);
        jSplitPane18.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        jPanel39.setLayout(new java.awt.GridLayout());

        jSplitPane19.setDividerLocation(50);
        jSplitPane19.setDividerSize(1);
        jSplitPane19.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        jPanel40.setLayout(new java.awt.GridLayout());

        jPanel40.setBorder(new javax.swing.border.TitledBorder("Parametros de ejecucion"));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("Cantidad registros A");
        jPanel40.add(jLabel15);

        txtRegA5.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtRegA5.setText("10240");
        jPanel40.add(txtRegA5);

        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("Cantidad registros B");
        jPanel40.add(jLabel16);

        txtRegB5.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtRegB5.setText("1280");
        jPanel40.add(txtRegB5);

        jSplitPane19.setLeftComponent(jPanel40);

        jPanel41.setLayout(new java.awt.GridLayout());

        jPanel41.setBorder(new javax.swing.border.TitledBorder("Resultado de ejecucion"));
        jTable5.setModel(new javax.swing.table.DefaultTableModel(
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
        tabla5.setViewportView(jTable5);

        jPanel41.add(tabla5);

        jSplitPane19.setRightComponent(jPanel41);

        jPanel39.add(jSplitPane19);

        jSplitPane18.setLeftComponent(jPanel39);

        jPanel54.setLayout(new java.awt.GridLayout());

        btEjecutar5.setIcon(new javax.swing.ImageIcon("recursos/imagenes/ejecutar.jpeg"));
        btEjecutar5.setToolTipText("Ejecutar prueba");
        btEjecutar5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actionEjecutar5();
            }
        });
        jPanel54.add(btEjecutar5);

        btGuardar5.setIcon(new javax.swing.ImageIcon("recursos/imagenes/guardar.jpeg"));
        btGuardar5.setToolTipText("Guardar prueba");
        btGuardar5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actionGuardar();
            }
        });
        jPanel54.add(btGuardar5);

        btBarras5.setIcon(new javax.swing.ImageIcon("recursos/imagenes/barras.jpeg"));
        btBarras5.setToolTipText("Ver grafico de Barras");
        btBarras5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actionBarras();
            }
        });
        jPanel54.add(btBarras5);

        btTorta5.setIcon(new javax.swing.ImageIcon("recursos/imagenes/torta.jpeg"));
        btTorta5.setToolTipText("Ver grafico de Torta");
        btTorta5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actionTorta();
            }
        });
        jPanel54.add(btTorta5);

        btDet5.setIcon(new javax.swing.ImageIcon("recursos/imagenes/detalle.jpeg"));
        btDet5.setToolTipText("Ver detalle de la prueba");
        btDet5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actionDetalle();
            }
        });
        jPanel54.add(btDet5);

        jSplitPane18.setRightComponent(jPanel54);

        jPanel38.add(jSplitPane18);

        jSplitPane17.setRightComponent(jPanel38);

        jPanel12.add(jSplitPane17);

        jTabbedPane1.addTab("Leer y regrabar aleatoriamente", jPanel12);

        jPanel7.add(jTabbedPane1);

        jSplitPane4.setRightComponent(jPanel7);

        jPanel4.add(jSplitPane4);

        jSplitPane3.setLeftComponent(jPanel4);

        jPanel5.setLayout(new java.awt.GridLayout(1, 1));

        jPanel5.setBorder(new javax.swing.border.TitledBorder("Acciones realizadas"));
        jPanel5.setMinimumSize(new java.awt.Dimension(32, 30));
        jPanel5.setPreferredSize(new java.awt.Dimension(20, 30));
        txtAcciones.setEditable(false);
        jScrollPane1.setViewportView(txtAcciones);

        jPanel5.add(jScrollPane1);

        jSplitPane3.setRightComponent(jPanel5);

        jSplitPane1.setRightComponent(jSplitPane3);

        getContentPane().add(jSplitPane1);

        jMenu1.setMnemonic('p');
        jMenu1.setText("Modulo");
        itemSalir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_MASK));
        itemSalir.setMnemonic('s');
        itemSalir.setText("Salir de Benchmark");
        itemSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actionSalir(evt);
            }
        });

        jMenu1.add(itemSalir);

        jMenuBar1.add(jMenu1);

        jMenu2.setMnemonic('a');
        jMenu2.setText("Benchmark");
        itemAcercaDe.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, 0));
        itemAcercaDe.setMnemonic('c');
        itemAcercaDe.setText("Acerca de");
        itemAcercaDe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actionAcercaDe(evt);
            }
        });

        //jMenu2.add(itemAcercaDe);

        itemDescripcion.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, 0));
        itemDescripcion.setMnemonic('d');
        itemDescripcion.setText("Archivo guardado");
        itemDescripcion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actionDescripcion(evt);
            }
        });

        jMenu2.add(itemDescripcion);

        itemAcelerador.setMnemonic('a');
        itemAcelerador.setText("Aceleradores");
        itemAcelerador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actionAcelerador(evt);
            }
        });

        //jMenu2.add(itemAcelerador);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        pack();
    }//GEN-END:initComponents

    private void actionAcelerador(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actionAcelerador
        // Add your handling code here:
    }//GEN-LAST:event_actionAcelerador

    private void actionDescripcion(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actionDescripcion
        DialogoInforme dialogo=new DialogoInforme("Benchmark guardado",gestor.mostrarBenchmarkArchivo());
		dialogo.setVisible(true);
    }//GEN-LAST:event_actionDescripcion

    private void actionAcercaDe(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actionAcercaDe
        // Add your handling code here:
    }//GEN-LAST:event_actionAcercaDe

    private void actionSalir(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actionSalir
		gestor.finalizar();
		Main.cerrarVentanaBenchmark();
    }//GEN-LAST:event_actionSalir

    private void actionOp5(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actionOp5
        jTabbedPane1.setSelectedIndex (5);
    }//GEN-LAST:event_actionOp5

    private void actionOp4(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actionOp4
        jTabbedPane1.setSelectedIndex (4);
    }//GEN-LAST:event_actionOp4

    private void actionOp3(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actionOp3
        jTabbedPane1.setSelectedIndex (3);
    }//GEN-LAST:event_actionOp3

    private void actionOp2(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actionOp2
        jTabbedPane1.setSelectedIndex (2);
    }//GEN-LAST:event_actionOp2

    private void actionOp1(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actionOp1
        jTabbedPane1.setSelectedIndex (1);
    }//GEN-LAST:event_actionOp1

    private void actionSistema(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actionSistema
        jTabbedPane1.setSelectedIndex (0);
    }//GEN-LAST:event_actionSistema

    private void actionNuevo(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actionNuevo
        if (estadoParam)
			crearArchivos();
		else
			cancelarParametros();
    }//GEN-LAST:event_actionNuevo

	private void crearArchivos()
	{
		if (gestor.crearArchivos())
		{
			txtEst1.setText("Creado");
			txtEst2.setText("Creado");			
			mostrarAccion("Archivos "+ValoresPrueba.NOMBRE_A+" y "+ValoresPrueba.NOMBRE_B+" creados");
		}
		else
		{
			txtEst1.setText("No creado");
			txtEst2.setText("No creado");
			mostrarAccion("Error al crear archivos: "+ValoresPrueba.NOMBRE_A+" y "+ValoresPrueba.NOMBRE_B);
		}
	}
	
	private void cancelarParametros()
	{
		mostrarAccion("Modifcacion de parametros de archivo cancelada");	
		txtNom1.setText(""+ValoresPrueba.NOMBRE_A);
		txtNom2.setText(""+ValoresPrueba.NOMBRE_B);
		txtLong1.setText (""+ValoresPrueba.LONGITUD_REG_A);
		txtLong2.setText (""+ValoresPrueba.LONGITUD_REG_B);
		txtNom1.setEditable(false);
		txtNom2.setEditable(false);
		txtLong1.setEditable(false);
		txtLong2.setEditable(false);
		btParametros.setIcon(new javax.swing.ImageIcon("recursos/imagenes/editar.jpeg"));
		btNuevo.setIcon(new javax.swing.ImageIcon("recursos/imagenes/nuevo.jpeg"));
		btNuevo.setToolTipText("Crea los archivos de benchmark de acuerdo a la definicion de atributos establecida");
		btParametros.setToolTipText("Editar los archivos de prueba");
		estadoParam=!estadoParam;
	}
	
    private void actionParametros(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actionParametros
        
		if (estadoParam)
			modificarParametros();
		else
			guardarParametros();
    }//GEN-LAST:event_actionParametros

	private void modificarParametros()
	{
		txtNom1.setEditable(true);
		txtNom2.setEditable(true);
		txtLong1.setEditable(true);
		txtLong2.setEditable(true);
		btParametros.setIcon(new javax.swing.ImageIcon("recursos/imagenes/salvar.jpeg"));
		btNuevo.setIcon(new javax.swing.ImageIcon("recursos/imagenes/cancelar.jpeg"));
		btParametros.setToolTipText("Setear las modificaciones en los archivos de prueba");
		btNuevo.setToolTipText("Cancelar las modificaciones en los archivos de prueba");
		estadoParam=!estadoParam;
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

	private void guardarParametros()
	{
		String nomAr1=txtNom1.getText();
		if (!GestionString.esPalabra(nomAr1))
		{
			mostrarMensaje("El nombre del archivo 1 no se corresponde con un nombre de archivo valido");
			mostrarAccion("Nombre de archivo 1 inapropiado");
			return;
		}
		String nomAr2=txtNom2.getText();
		if (!GestionString.esPalabra(nomAr2))
		{
			mostrarMensaje("El nombre del archivo 2 no se corresponde con un nombre de archivo valido");
			mostrarAccion("Nombre de archivo 2 inapropiado");
			return;
		}
		long longReg1;
		try
		{
			longReg1=Long.parseLong(txtLong1.getText());
		} catch (Exception qq)
		{
			mostrarMensaje("La longitud de registro 1 no se corresponde con una longitud valida");
			mostrarAccion("Longitud de registro de archivo 1 inapropiada");
			return;
		}
		long longReg2;
		try
		{
			longReg2=Long.parseLong(txtLong2.getText());
		} catch (Exception qq)
		{
			mostrarMensaje("La longitud de registro 2 no se corresponde con una longitud valida");
			mostrarAccion("Longitud de registro de archivo 2 inapropiada");
			return;
		}
		if (longReg1<64)
		{
			mostrarMensaje("La longitud de registro minima es 64");
			mostrarAccion("Longitud de registro de archivo 1 inapropiada");
			return;
		}
		if (longReg2<64)
		{
			mostrarMensaje("La longitud de registro minima es 64");
			mostrarAccion("Longitud de registro de archivo 2 inapropiada");
			return;
		}

		ValoresPrueba.NOMBRE_A=nomAr1;
		ValoresPrueba.NOMBRE_B=nomAr2;
		ValoresPrueba.LONGITUD_REG_A=longReg1;
		ValoresPrueba.LONGITUD_REG_B=longReg2;

		mostrarAccion("Modificacion de parametros de archivos exitosa");

		txtNom1.setEditable(false);
		txtNom2.setEditable(false);
		txtLong1.setEditable(false);
		txtLong2.setEditable(false);
		btParametros.setIcon(new javax.swing.ImageIcon("recursos/imagenes/editar.jpeg"));
		btNuevo.setIcon(new javax.swing.ImageIcon("recursos/imagenes/nuevo.jpeg"));
		btParametros.setToolTipText("Editar los archivos de prueba");
		btNuevo.setToolTipText("Crea los archivos de benchmark de acuerdo a la definicion de atributos establecida");
		estadoParam=!estadoParam;
	}
    
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
    private javax.swing.JButton btBarras1;
    private javax.swing.JButton btBarras2;
    private javax.swing.JButton btBarras3;
    private javax.swing.JButton btBarras4;
    private javax.swing.JButton btBarras5;
    private javax.swing.JButton btDet1;
    private javax.swing.JButton btDet2;
    private javax.swing.JButton btDet3;
    private javax.swing.JButton btDet4;
    private javax.swing.JButton btDet5;
    private javax.swing.JButton btEjecutar1;
    private javax.swing.JButton btEjecutar2;
    private javax.swing.JButton btEjecutar3;
    private javax.swing.JButton btEjecutar4;
    private javax.swing.JButton btEjecutar5;
    private javax.swing.JButton btGuardar1;
    private javax.swing.JButton btGuardar2;
    private javax.swing.JButton btGuardar3;
    private javax.swing.JButton btGuardar4;
    private javax.swing.JButton btGuardar5;
    private javax.swing.JButton btNuevo;
    private javax.swing.JButton btOp1;
    private javax.swing.JButton btOp2;
    private javax.swing.JButton btOp3;
    private javax.swing.JButton btOp4;
    private javax.swing.JButton btOp5;
    private javax.swing.JButton btParametros;
    private javax.swing.JButton btSistema;
    private javax.swing.JButton btTorta1;
    private javax.swing.JButton btTorta2;
    private javax.swing.JButton btTorta3;
    private javax.swing.JButton btTorta4;
    private javax.swing.JButton btTorta5;
	private javax.swing.JButton btGuardar;
	private javax.swing.JButton btDescripcion;
	private javax.swing.JButton btEstructura;
    private javax.swing.JMenuItem itemAcelerador;
    private javax.swing.JMenuItem itemAcercaDe;
    private javax.swing.JMenuItem itemDescripcion;
    private javax.swing.JMenuItem itemSalir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JPanel jPanel34;
    private javax.swing.JPanel jPanel35;
    private javax.swing.JPanel jPanel37;
    private javax.swing.JPanel jPanel38;
    private javax.swing.JPanel jPanel39;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel40;
    private javax.swing.JPanel jPanel41;
    private javax.swing.JPanel jPanel43;
    private javax.swing.JPanel jPanel44;
    private javax.swing.JPanel jPanel45;
    private javax.swing.JPanel jPanel46;
    private javax.swing.JPanel jPanel47;
    private javax.swing.JPanel jPanel48;
    private javax.swing.JPanel jPanel49;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel50;
    private javax.swing.JPanel jPanel51;
    private javax.swing.JPanel jPanel52;
    private javax.swing.JPanel jPanel53;
    private javax.swing.JPanel jPanel54;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JSplitPane jSplitPane10;
    private javax.swing.JSplitPane jSplitPane11;
    private javax.swing.JSplitPane jSplitPane12;
    private javax.swing.JSplitPane jSplitPane13;
    private javax.swing.JSplitPane jSplitPane14;
    private javax.swing.JSplitPane jSplitPane15;
    private javax.swing.JSplitPane jSplitPane16;
    private javax.swing.JSplitPane jSplitPane17;
    private javax.swing.JSplitPane jSplitPane18;
    private javax.swing.JSplitPane jSplitPane19;
    private javax.swing.JSplitPane jSplitPane2;
    private javax.swing.JSplitPane jSplitPane20;
    private javax.swing.JSplitPane jSplitPane21;
    private javax.swing.JSplitPane jSplitPane22;
    private javax.swing.JSplitPane jSplitPane23;
    private javax.swing.JSplitPane jSplitPane3;
    private javax.swing.JSplitPane jSplitPane4;
    private javax.swing.JSplitPane jSplitPane8;
    private javax.swing.JSplitPane jSplitPane9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    private javax.swing.JTable jTable5;
    private javax.swing.JTextPane jTextPane3;
    private javax.swing.JTextPane jTextPane4;
    private javax.swing.JTextPane jTextPane5;
    private javax.swing.JTextPane jTextPane6;
    private javax.swing.JTextPane jTextPane7;
    private javax.swing.JTextPane jTextPane8;
    private javax.swing.JScrollPane tabla2;
    private javax.swing.JScrollPane tabla3;
    private javax.swing.JScrollPane tabla4;
    private javax.swing.JScrollPane tabla5;
    private javax.swing.JScrollPane tabla6;
    private javax.swing.JTable tablaInfo;
    private javax.swing.JTextPane txtAcciones;
    private javax.swing.JTextField txtEst1;
    private javax.swing.JTextField txtEst2;
    private javax.swing.JTextField txtLong1;
    private javax.swing.JTextField txtLong2;
    private javax.swing.JTextField txtNom1;
    private javax.swing.JTextField txtNom2;
    private javax.swing.JTextField txtRegA1;
    private javax.swing.JTextField txtRegA2;
    private javax.swing.JTextField txtRegA3;
    private javax.swing.JTextField txtRegA4;
    private javax.swing.JTextField txtRegA5;
    private javax.swing.JTextField txtRegB1;
    private javax.swing.JTextField txtRegB2;
    private javax.swing.JTextField txtRegB3;
    private javax.swing.JTextField txtRegB4;
    private javax.swing.JTextField txtRegB5;
    // End of variables declaration//GEN-END:variables

	class DialogoEspera extends javax.swing.JDialog {
    
	    /** Creates new form DialogoEspera */
    	public DialogoEspera() {
	        initComponents();
			setearFrame();
    	}
	    
		private void setearFrame()
		{
        	setTitle(Main.NOMBRE+" "+Main.VERSION+": Modulo Benchmark");
        	setBounds(new Rectangle(200,100,370,200));
			setIconImage(Toolkit.getDefaultToolkit().getImage("recursos/imagenes/sombra.jpeg"));			
			setResizable(false);
			//setIconified(false);
        	GestionVisual.centrar(this);     	
		}

		public void setVisible(String operacion)
		{
			super.setVisible(true);
			jTextPane1.setText("\n"+"Espere mientras se ejecuta la operacion: "+"\n"+operacion);
		}
		
    	/** This method is called from within the constructor to
	     * initialize the form.
    	 * WARNING: Do NOT modify this code. The content of this method is
	     * always regenerated by the Form Editor.
    	 */
	    private void initComponents() {
    	    jSplitPane1 = new javax.swing.JSplitPane();
	        jPanel1 = new javax.swing.JPanel();
        	jLabel1 = new javax.swing.JLabel();
    	    jPanel2 = new javax.swing.JPanel();
	        jTextPane1 = new javax.swing.JTextPane();

        	getContentPane().setLayout(new java.awt.GridLayout(1, 1));

    	    setTitle("Prueba en ejecucion");
	        addWindowListener(new java.awt.event.WindowAdapter() {
            	public void windowClosing(java.awt.event.WindowEvent evt) {
            	    exitForm(evt);
        	    }
    	    });

	        jSplitPane1.setDividerLocation(120);
        	jSplitPane1.setDividerSize(0);
    	    jPanel1.setLayout(new java.awt.GridLayout(1, 1));

	        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 10));
        	jLabel1.setIcon(new javax.swing.ImageIcon("recursos/imagenes/sombra.jpeg"));
    	    jPanel1.add(jLabel1);

	        jSplitPane1.setLeftComponent(jPanel1);

        	jPanel2.setLayout(new java.awt.GridLayout(1, 1));

    	    jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 10));
	        jTextPane1.setBackground(new java.awt.Color(204, 204, 204));
        	jTextPane1.setEditable(false);
    	    jTextPane1.setFont(new java.awt.Font("Dialog", 2, 16));
	        jPanel2.add(jTextPane1);

        	jSplitPane1.setRightComponent(jPanel2);

    	    getContentPane().add(jSplitPane1);
	
	        pack();
    	}
    
    	/** Exit the Application */
	    private void exitForm(java.awt.event.WindowEvent evt) {
        setVisible(false);
	    }
    
    	// Variables declaration - do not modify
	    private javax.swing.JLabel jLabel1;
    	private javax.swing.JPanel jPanel1;
	    private javax.swing.JPanel jPanel2;
    	private javax.swing.JSplitPane jSplitPane1;
	    private javax.swing.JTextPane jTextPane1;
    	// End of variables declaration
    
	}
    
}
