
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.filechooser.*;

/**
 * Clase que proporciona metodos para la gestion de archivos
 * @version Diciembre de 2005
 */
public class GestionArchivo implements ValoresPatron
{
    /**
    * Realiza la carga de los bytes de un archivo cuyo nombre es pasado por parametro
    * efectuando la lectura de sus bytes y almacenadolos en un vector que luego es retornado
    * Si el archivo no existe o se produce una excepcion se retorna un String ""
    * @param nombreArchivo nombre del archivo del cual se cargara el vector con sus bytes
    * @return vector que contiene los bytes del archivo cuyo nombre es pasado por parametro o ""
    */	
	public static byte[] cargarBytesArchivo(String nombreArchivo)
	{
		try
		{
			File archivo=new File(nombreArchivo);
			String aux=new String("");
			if (!archivo.exists()) return null;
			FileInputStream fileInputStream;
			BufferedInputStream bufferedInputStream;
			byte[] buffer;
			fileInputStream=new FileInputStream(archivo);
			bufferedInputStream=new BufferedInputStream(fileInputStream);
			buffer=new byte[(int)archivo.length()];		
			int byteLectura=0;
			byteLectura=bufferedInputStream.read(buffer);
			return buffer;	
		} catch(Exception qq)
		{
			return null;
		}
	}
    
    /**
    * Realiza la carga de un archivo cuyo nombre es pasado por parametro
    * efectuando la lectura de sus bytes y almacenadolos en un String que luego es retornado
    * Si el archivo no existe o se produce una excepcion se retorna un String ""
    * @param nombreArchivo nombre del archivo del cual se cargara el String con sus bytes
    * @return String que contiene los bytes del archivo cuyo nombre es pasado por parametro o ""
    */	
	public static String cargarStringArchivo(String nombreArchivo)
	{
		try
		{
			File archivo=new File(nombreArchivo);
			String aux=new String("");
			if (!archivo.exists()) return "";
			FileInputStream fileInputStream;
			BufferedInputStream bufferedInputStream;
			byte[] buffer;
			fileInputStream=new FileInputStream(archivo);
			bufferedInputStream=new BufferedInputStream(fileInputStream);
			buffer=new byte[(int)archivo.length()];		
			int byteLectura=0;
			byteLectura=bufferedInputStream.read(buffer);
			int i;
			for (i=0;i<buffer.length;i++)
			{
				aux+=""+((char)buffer[i]);
				//System.out.println(""+i);
			}	
			return aux;	
		} catch(Exception qq)
		{
			return "";
		}
	}
	
    /**
    * Realiza grabacion en disco de un archivo cuyos bytes son leidos uno a uno de un String pasado por parametro
    * @param archivo String cuyos bytes seran grabados
    * @param nombreArchivo String nombre del archivo para efectuar la grabacion
    * @return boolean que indica exito de la grabacion efectuada
    */		
	public static boolean grabarStringArchivo(String archivo,String nombreArchivo)
	{
		try
		{
			FileOutputStream fileOutputStream=new FileOutputStream(new File(nombreArchivo));
			for (int i=0;i<archivo.length();i++)
			{
				fileOutputStream.write(archivo.charAt(i));
			}
			fileOutputStream.close();
			return true;
		}
		catch (Exception qqq)
		{
			return false;
		}
	}

    /**
    * Realiza la carga de un objeto de la clase Object a través del nombre de archivo pasado por parametro
    * Si el archivo no existe o se produce una excepcion se retorna null
    * @param nombreArchivo String nombre del archivo del cual se cargara el objeto
    * @return objeto resultante de la carga efectuada sobre el archivo correspondiente o null
    */		
	public static Object cargarObjectArchivo(String nombreArchivo)
	{
		File archivo;
		FileInputStream fileInputStream;
		ObjectInputStream objectInputStream;
		Object objeto;
		try
		{
			archivo=new File(nombreArchivo);
			if (archivo.exists())
			{
				fileInputStream=new FileInputStream(archivo);
				objectInputStream=new ObjectInputStream(fileInputStream);
				objeto=objectInputStream.readObject();
				fileInputStream.close();
				return objeto;
			}
		}
		catch (Exception qq)
		{
			//GestionExcepcion.mostrarDialogo(qq);
		}
		return null;
	}

    /**
    * Realiza la carga de una lista a través del nombre de archivo pasado por parametro
    * Si el archivo no existe o se produce una excepcion se retorna una nueva lista
    * @param nombreArchivo String nombre del archivo del cual se cargara la lista
    * @return lista resultante de la carga efectuada sobre el archivo correspondiente o una nueva lista
    */		
	public static Lista cargarListaArchivo(String nombreArchivo)
	{
		try
		{
			Object objeto=cargarObjectArchivo(nombreArchivo);
			if (objeto!=null)
			{
				Lista lista=(Lista)objeto;
				return lista;
			}
		}
		catch (Exception qq) {}
		return new Lista();		
	}	

    /**
    * Realiza grabacion en disco de un objeto de la clase Objet en un archivo cuyo nombre es pasado por parametro
    * @param objeto Object objeto a guardar
    * @param nombreArchivo String nombre del archivo donde se guardara el objeto
    * @return boolean que indica exito de la grabacion efectuada
    */		
	public static boolean grabarObjectArchivo(Object objeto,String nombreArchivo)
	{
		File archivo;
		FileOutputStream fileOutputStream;
		ObjectOutputStream objectOutputStream;	
		if (objeto==null||nombreArchivo==null) return false;
		try
		{
			archivo=new File(nombreArchivo);
			fileOutputStream=new FileOutputStream(archivo);
			objectOutputStream=new ObjectOutputStream(fileOutputStream);
			objectOutputStream.writeObject(objeto);
			objectOutputStream.flush();
			fileOutputStream.close();
			return true;
		}
		catch (Exception eq)
		{
			return false;
		}
	}

    /**
    * Metodo que proporciona informacion de un archivo en forma de arreglo unidimensional de String
    * @param nombreArchivo String nombre del archivo del cual se quiere obtener informacion
    * @return arreglo unidimensional de String que proporciona informacion del archivo
    */		
	public static String[] getInformacionArchivoArreglo(String nombreArchivo)
	{
		String info[]=new String[12];
		for (int i=0;i<info.length;i++)
		{
			info[i]="";
		}
		info[0]="Nombre";
		info[2]="Path";
		info[4]="Tamanio";
		info[6]="Derecho lectura";
		info[8]="Derecho escritura";
		info[10]="Ultima modificacion";
		File archivo=new File(nombreArchivo);
		info[1]=archivo.getName();
		if (archivo.exists())
		{
			info[3]=archivo.getAbsolutePath();
			info[5]=""+archivo.length()+" bytes";
			info[7]=""+archivo.canRead();
			info[9]=""+archivo.canWrite();
			info[11]=""+getDateUltimaModificacion(archivo);
		} else
		{
			info[3]="Archivo inexistente";
		}
		return info;
	}

    /**
    * Metodo para la transferencia de los bytes de un archivo a otro archivo
    * @param nombreArchivoOrigen String nombre del archivo de origen de los bytes
    * @param nombreArchivoDestino String nombre del archivo de destino de los bytes
    * @return boolean que indica exito de la grabacion efectuada
    */		
	public static boolean transferirArchivo(String nombreArchivoOrigen,String nombreArchivoDestino)
	{
		try
		{
			grabarStringArchivo(cargarStringArchivo(nombreArchivoOrigen),nombreArchivoDestino);
			return true;
		}
		catch (Exception qq)
		{
			return false;
		}
	}

    /**
    * Metodo para asegurar la existencia de un directorio
    * @param nombreDirectorio String nombre del directorio cuya existencia quiere ser asegurada
    */		
    public static void gestionarDirectorio(String nombreDirectorio)
    {
    	File directorio=new File(nombreDirectorio);
        if (!directorio.exists()||(directorio.exists()&&!directorio.isDirectory()))
       	{
       		directorio.mkdir();
       	}
    }

    /**
    * Metodo para determinar si un archivo esta dentro de un directorio
    * @param nombreDirectorio String nombre del directorio que representa la ubicacion a verificar
    * @param nombreArchivo String nombre del archivo cuya ubicacion quiere ser verificada
    * @return boolean que indica si el archivo esta dentro del directorio
    */	    
    public static boolean estaArchivoEnDirectorio(String nombreDirectorio,String nombreArchivo)	
    {
    	File directorio=new File (nombreDirectorio);
    	File archivo=new File(nombreArchivo);    	
    	if (!directorio.exists()||!archivo.exists()||!directorio.isDirectory()) return false;
    	File archivos[]=directorio.listFiles();
    	int i;
    	for (i=0;i<archivos.length&&!archivos[i].getAbsolutePath().equals(archivo.getAbsolutePath());i++);
    	return !(i==archivos.length);
    }
    
    /**
    * Metodo que retorna un string con los paths de los archivos que estan en el arbol del directorio cuyo nombre fue pasado por parametro
    * @param nombreDirectorio string que es el nombre del directorio del cual se quieren conocer los paths de los archivos del subarbol
    * @return vector de string con los paths de los archivos del subarbol del directorio cuyo nombre fue pasado por parametro
    */	    
    public static String[] getPathArchivosDirectorio(String nombreDirectorio)
    {
		File directorio=new File(nombreDirectorio);
		if (!directorio.exists()||!directorio.isDirectory()) return new String[0];
		Lista lista=new Lista();
		insertarArchivosLista(lista,directorio);
		return getPathArchivosLista(lista);
    }

    /**
    * Metodo que retorna un string con los archivos que estan en el arbol del directorio cuyo nombre fue pasado por parametro
    * @param nombreDirectorio string que es el nombre del directorio del cual se quieren conocer los archivos del subarbol
    * @return vector de File con los archivos del subarbol del directorio cuyo nombre fue pasado por parametro
    */	    
    public static File[] getArchivosDirectorio(String nombreDirectorio)
    {
		File directorio=new File(nombreDirectorio);
		if (!directorio.exists()||!directorio.isDirectory()) return new File[0];
		Lista lista=new Lista();
		insertarArchivosLista(lista,directorio);
		return getArchivosLista(lista);
    } 
    
    /**
    * Metodo que elimina los archivos del subarbol del directorio cuyo nombre ha sido pasado por parametro que cumplan con un patron tambien pasado por parametro
    * @param nombreDirectorio string que es el nombre del directorio del cual se quieren eliminar los archivos del subarbol
    * @param patron string que es el patron de comparacion de los nombres de los archivos del subarbol del directorio
    * @param tipo int que representa la posicion dentro del string donde se efectuara la comparacion del patron
    * @return vector de String con los path de los archivos eliminados
    */	 
    public static String[] eliminarArchivosDirectorioConPatron(String nombreDirectorio,String patron,int tipo)
    {
    	try
    	{
			Lista lista=new Lista();
			File a[]=getArchivosDirectorio(nombreDirectorio);
			for (int i=0;i<a.length;i++)
			{
				if ((tipo==PATRON&&GestionString.contieneString(a[i].getName(),patron))||
				(tipo==PATRON_COMIENZO&&GestionString.contieneStringComienzo(a[i].getName(),patron))||
				(tipo==PATRON_FINAL&&GestionString.contieneStringFinal(a[i].getName(),patron)))
				{
					lista.insertar(a[i].getAbsolutePath());
					a[i].delete();    		
				}
			}
			return GestionString.getStringsLista(lista);
    	}
    	catch (Exception qq)
    	{
    		return null;
    	}
    }

    /**
    * Metodo que retorna un vector con los archivos del subarbol de un directorio cuyos nombres cumplan con un patron
    * @param nombreDirectorio string que es el nombre del directorio del cual se quieren conocer los archivos del subarbol
    * @param patron string que es el patron de comparacion de los nombres de los archivos del subarbol del directorio
    * @return vector de File con los archivos del subarbol del directorio cuyos nombres cumplen con el patron
    */	    
    public static File[] getArchivosDirectorioConPatron(String nombreDirectorio,String patron)
    {
    	return getArchivosDirectorioConPatronTipo(nombreDirectorio,patron,GestionString.PATRON);
    }

    /**
    * Metodo que retorna un vector con los archivos del subarbol de un directorio cuyos nombres cumplan con un patron al comienzo de ese nombre
    * @param nombreDirectorio string que es el nombre del directorio del cual se quieren conocer los archivos del subarbol
    * @param patron string que es el patron de comparacion de los nombres de los archivos del subarbol del directorio
    * @return vector de File con los archivos del subarbol del directorio cuyos nombres cumplen con el patron al comienzo de ese nombre
    */	    
    public static File[] getArchivosDirectorioConPatronComienzo(String nombreDirectorio,String patron)
    {
    	return getArchivosDirectorioConPatronTipo(nombreDirectorio,patron,GestionString.PATRON_COMIENZO);
    }

    /**
    * Metodo que retorna un vector con los archivos del subarbol de un directorio cuyos nombres cumplan con un patron al final de ese nombre
    * @param nombreDirectorio string que es el nombre del directorio del cual se quieren conocer los archivos del subarbol
    * @param patron string que es el patron de comparacion de los nombres de los archivos del subarbol del directorio
    * @return vector de File con los archivos del subarbol del directorio cuyos nombres cumplen con el patron al final de ese nombre
    */	    
    public static File[] getArchivosDirectorioConPatronFinal(String nombreDirectorio,String patron)
    {
    	return getArchivosDirectorioConPatronTipo(nombreDirectorio,patron,GestionString.PATRON_FINAL);
    }        

    /**
    * Metodo que retorna un vector con los archivos del subarbol de un directorio cuyos nombres cumplan con un patron en una posicion definida por el tipo pasado por parametro
    * @param nombreDirectorio string que es el nombre del directorio del cual se quieren conocer los archivos del subarbol
    * @param patron string que es el patron de comparacion de los nombres de los archivos del subarbol del directorio
    * @param tipo int que indica la posicion donde se efectuara la comparacion del patron
    * @return vector de File con los archivos del subarbol del directorio cuyos nombres cumplen con el patron en una posicion definida por el tipo pasado por parametro
    */	    
    public static File[] getArchivosDirectorioConPatronTipo(String nombreDirectorio,String patron,int tipo)
    {
		File a[]=getArchivosDirectorio(nombreDirectorio);
		Lista lista=new Lista();
		for (int i=0;i<a.length;i++)
		{
			if ((tipo==PATRON&&GestionString.contieneString(a[i].getName(),patron))||
			(tipo==PATRON_COMIENZO&&GestionString.contieneStringComienzo(a[i].getName(),patron))||
			(tipo==PATRON_FINAL&&GestionString.contieneStringFinal(a[i].getName(),patron)))
			{
				lista.insertar(a[i]);
			}
		}
		return getArchivosLista(lista);
    }   

   /**
    * Metodo que retorna un vector de File que representa los File guardados en un lista
    * @param lista que es la Lista de la cual se extraen los File
    * @return arreglo unidimensional de File que representa los nodos de la lista en forma de File
    */    
    private static File[] getArchivosLista(Lista lista) 
    {
		File archivos[]=new File[lista.getCantidadNodos()];
		Comparable aux[]=lista.getInfos();
		for (int i=0;i<archivos.length;i++)
		{
			archivos[i]=(File)aux[i];
		}
		return archivos;    	
    }
    
   /**
    * Metodo que retorna un vector de string que representa los paths de los File guardados en un lista
    * @param lista que es la Lista de la cual se extraen los File
    * @return arreglo unidimensional de string que representa los path de los nodos de la lista en forma de File
    */
    private static String[] getPathArchivosLista(Lista lista) 
    {
		String strings[]=new String[lista.getCantidadNodos()];
		Comparable aux[]=lista.getInfos();
		for (int i=0;i<strings.length;i++)
		{
			strings[i]=((File)aux[i]).getAbsolutePath();
		}
		return strings;    	
    }    

   /**
    * Metodo que retorna un string con el path (no absoluto) de un archivo cuyo nombre ha sido pasado por parametro
    * @param nombreArchivo que es un string con el nombre del archivo cuyo path quiere ser conocido
    * @return string que representa el path no absoluto del archivo cuyo nombre ha sido pasado por parametro
    */    
    public static String getPathDirectorio(String nombreArchivo)
    {
    	File archivo=new File(nombreArchivo);
    	return getPathDirectorio(archivo);
    }
    
    /**
    * Metodo que retorna un string con el path (no absoluto) de un archivo que ha sido pasado por parametro
    * @param archivo File que representa el archivo cuyo path quiere ser conocido
    * @return string que representa el path no absoluto del archivo que ha sido pasado por parametro
    */    
    public static String getPathDirectorio(File archivo)
    {
    	if (!archivo.exists()) return "";
    	String aux=archivo.getAbsolutePath();
    	return aux.substring(0,aux.length()-archivo.getName().length());    	
    }

   /**
    * Metodo recursivo que inserta los archivos del subarbol de un directorio (cuyo nombre ha sido pasado por parametro) en forma de File (si no son directorios) en una lista (tambien parametro)
    * @param lista que es la Lista en la cual se insertan los File
    * @param directorio que es el File del cual se extraen los archivos
    */    
    private static void insertarArchivosLista(Lista lista,File directorio)
    {
    	File archivos[]=directorio.listFiles();
    	for (int i=0;i<archivos.length;i++)
    	{
    		if (archivos[i].isDirectory())
    		{
    			insertarArchivosLista(lista,archivos[i]);
    		} else
    		{
    			lista.insertar(archivos[i]);
    		}
    	}
    }
    
    /**
     * Modifica el nombre de un archivo pasado por parametro por un nuevo nombre, tambien parametro
     * @param archivo File cuyo nombre quiere ser cambiado
     * @param nuevoNombre String que es el nuevo nombre que se le quiere asignar al archivo
     * @return boolean indicando el exito de la modificacion
     */
    public static boolean modificarNombreArchivo(File archivo,String nuevoNombre)
    {
    	if (archivo==null||nuevoNombre==null||!archivo.exists()) return false;
	    try
	    {
	    	if (!GestionNotacionArchivo.esNombreArchivo(nuevoNombre)) return false;
    		if (GestionNotacionArchivo.getExtensionNombreArchivo(archivo.getName()).equals(GestionNotacionArchivo.getExtensionNombreArchivo(nuevoNombre)))
    		{
	    		archivo.renameTo(new File(getPathDirectorio(archivo)+nuevoNombre));
	    		return true;
    		}
    		return false;
    	} catch (Exception qq)
    	{
    		return false;
    	}
    }
    
    /**
     * Mueve un archivo a un directorio, ambos pasados por parametro en forma de archivo (File)
     * @param archivo File que es el archivo que quiere ser movido
     * @param directorio File donde quiere ser movido el archivo
     * @return boolean indicando el exito de la operacion
     */
    public static boolean moverArchivo(File archivo,File directorio)
    {
    	if (archivo==null||directorio==null||!archivo.exists()||!directorio.exists()) return false;
    	if (!directorio.isDirectory()) return false;
		try
		{
   			if (GestionString.contieneString(directorio.getAbsolutePath(),archivo.getAbsolutePath())) return false;
 			archivo.renameTo(new File(directorio+"/"+archivo.getName()));
   			return true;
    	} catch (Exception qq)
    	{
    		return false;
    	}
    }

    /**
     * Mueve un archivo a un directorio, ambos pasados por parametro a traves de su path
     * @param pathArchivo String que es el nombre del archivo que quiere ser movido
     * @param pathDirectorio String que es el nombre del directorio donde quiere ser movido el archivo
     * @return boolean indicando el exito de la operacion
     */    
    public static boolean moverArchivo(String pathArchivo,String pathDirectorio)
    {
    	return moverArchivo(new File(pathArchivo),new File(pathDirectorio));
    }

	/**
	 * Muestra un chooser para la seleccion de un directorio, con el path del directorio inicial a mostrar (pasado por parametro)
	 * @param directorioComienzo String que representa el path del directorio que primero se muestra para efectuar la seleccion
	 * @return File que es el archivo que ha sido seleccionado, o null si no se ha seleccionado ninguno
	 */    
    public static  File seleccionarDirectorio(String directorioComienzo)
    {
    	SeleccionadorDirectorio s=new SeleccionadorDirectorio(false,directorioComienzo);
		File a=s.getArchivo();
		s.dispose();
    	return a;
    }
	/**
	 * Muestra un chooser para la seleccion de un directorio
	 * @return File que es el archivo que ha sido seleccionado, o null si no se ha seleccionado ninguno
	 */        
    public static  File seleccionarDirectorio()
    {
    	return seleccionarDirectorio(null);
    }

 	/***
 	 * Muestra un chooser para seleccionar varios directorios, con el path del directorio inicial a mostrar (pasado por parametro) 
	 * @param directorioComienzo String que representa el path del directorio que primero se muestra para efectuar la seleccion
	 * @return vector de File que contiene los directorios seleccionados
	 */    
    public static File[] seleccionarDirectorios(String directorioComienzo)
    {
    	SeleccionadorDirectorio s=new SeleccionadorDirectorio(true,directorioComienzo);
		File a[]=s.getArchivos();
		s.dispose();
    	return a;    	
    }

 	/***
 	 * Muestra un chooser para seleccionar varios directorios
	 * @return vector de File que contiene los directorios seleccionados
	 */       
    public static File[] seleccionarDirectorios()
    {
    	return seleccionarDirectorios(null);
    }

    /**
     * Muestra un chooser para seleccionar varios archivos, conociendo filtros y el directorio inicial a mostrarse (pasados por parametro)
     * @param filtros vector de String que contiene extensiones de filtrado en la seleccion de archivos
	 * @param directorioComienzo String que representa el path del directorio que primero se muestra para efectuar la seleccion
	 * @return vector de File que contiene los archivos que han sido seleccionados y han cumplido con alguna regla de filtrado
	 */
    public static File[] seleccionarArchivos(String filtros[],String directorioComienzo)
    {
    	SeleccionadorArchivo s=null;
    	try
    	{
    		s=new SeleccionadorArchivo(true,filtros,directorioComienzo);
    	}catch(Exception qq){;}
    	File a[]=s.getArchivos();
    	s.dispose();
    	return a;
    }

    /**
     * Muestra un chooser para seleccionar varios archivos, conociendo filtros (pasado por parametro)
     * @param filtros vector de String que contiene extensiones de filtrado en la seleccion de archivos
	 * @return vector de File que contiene los archivos que han sido seleccionados y han cumplido con alguna regla de filtrado
	 */    
    public static File[] seleccionarArchivos(String filtros[])
    {
    	return seleccionarArchivos(filtros,null);
    }

    /**
     * Muestra un chooser para seleccionar varios archivos, conociendo un unico filtro y el directorio inicial a mostrarse (pasados por parametro)
     * @param filtro String que representa una extension de filtrado de seleccion
	 * @param directorioComienzo String que representa el path del directorio que primero se muestra para efectuar la seleccion
	 * @return vector de File que contiene los archivos que han sido seleccionados y han cumplido con la regla de filtrado
	 */    
    public static File[] seleccionarArchivos(String filtro,String directorioComienzo)
    {
    	String f[]={filtro};
    	return seleccionarArchivos(f,directorioComienzo);
    }

    /**
     * Muestra un chooser para seleccionar varios archivos, conociendo un unico filtro (pasado por parametro)
     * @param filtro String que representa una extension de filtrado de seleccion
	 * @return vector de File que contiene los archivos que han sido seleccionados y han cumplido con la regla de filtrado
	 */        
    public static File[] seleccionarArchivos(String filtro)
    {
    	return seleccionarArchivos(filtro,null);
    }

    /**
     * Muestra un chooser para seleccionar un archivo, conociendo filtros y el directorio inicial a mostrarse (pasados por parametro)
     * @param filtros vector de String que contiene extensiones de filtrado en la seleccion de archivos
	 * @param directorioComienzo String que representa el path del directorio que primero se muestra para efectuar la seleccion
	 * @return un File que es el archivo que ha sido seleccionado y ha cumplido con alguna regla de filtrado
	 */    
    public static File seleccionarArchivo(String filtros[],String directorioComienzo)
    {
    	SeleccionadorArchivo s=null;
    	try
    	{
    		s=new SeleccionadorArchivo(false,filtros,directorioComienzo);
    	} catch (Exception qq)
    	{
    		;
    	}
    	File a=s.getArchivo();
    	s.dispose();
    	return a;    	
    }

    /**
     * Muestra un chooser para seleccionar un archivo, conociendo filtros (pasado por parametro)
     * @param filtro vector de String que contiene extensiones de filtrado en la seleccion de archivos
	 * @return un File que es el archivo que ha sido seleccionado y ha cumplido con alguna regla de filtrado
	 */        
    public static File seleccionarArchivo(String filtro)
    {
    	String f[]={filtro};
    	return seleccionarArchivo(f,null);    	
    }
    
	/** 
	 * Realiza la grabacion en disco de un objeto (de tipo Object),  a traves de la seleccion de un directorio
	 * Se conoce el nombre de archivo que se le quiere asignar (pasado por parametro)
	 * @param objeto Objet que se quiere grabar
	 * @param nombreArchivo String que es el nombre que se quiere asignar al objeto persistente
	 * @return boolean que indica el exito de la grabacion
	 */	
	public static boolean grabarObjetoSeleccionandoDirectorio(Object objeto,String nombreArchivo)
	{
		return grabarObjetoSeleccionandoDirectorio(objeto,nombreArchivo,null);
	}
	
	/**
	 * Verifica si existe un archivo cuyo nombre fue pasado por parametro
	 * @param nombreArchivo nombre del archivo cuya existencia es verificada
	 * @return boolean que indica si existe un archivo cuyo nombre fue pasado por parametro
	 */
	public static boolean existe(String nombreArchivo)
	{
		if (nombreArchivo==null) return false;
		File archivo=new File(nombreArchivo);
		return archivo.exists();
	}
	
	/**
	 * Retorna el path absoluto del archivo cuyo nombre es pasado por parametro
	 * @param nombreArchivo nombre del archivo cuyo path quiere ser conocido
	 * @return path absoluto del archivo cuyo nombre es pasado por parametro
	 */
	public static String getPathAbsoluto(String nombreArchivo)
	{
		if (nombreArchivo==null) return "";
		File archivo=new File(nombreArchivo);
		if (!archivo.exists()) return "";
		return archivo.getAbsolutePath();
	}    
    
    /**
     * Clase visual para mostrar un chooser para la seleccion de un/os directorio/s
     */
	static class SeleccionadorDirectorio extends JDialog
	{
		private File archivo,archivos[];
	    private JFileChooser chooser;
	
		public SeleccionadorDirectorio (boolean multiseleccion,String directorioComienzo)
		{
			chooser = new JFileChooser();
			chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			if(multiseleccion)
			{
				chooser.setMultiSelectionEnabled(true);
			} else
			{
				chooser.setMultiSelectionEnabled(false);
			}
			try
			{
				File comienzo=getDirectorio(directorioComienzo);
				if (comienzo!=null)
				{
					chooser.setCurrentDirectory(comienzo);
				}			
			} catch (Exception qq) {;}		
			chooser.setDialogType(JFileChooser.OPEN_DIALOG);
			int retval = chooser.showDialog(null, null);
			switch (retval)
			{
				case JFileChooser.APPROVE_OPTION:
				{
					if (multiseleccion)
					{
						archivos = chooser.getSelectedFiles();
					} else
					{
						archivo = chooser.getSelectedFile();
					}
				} break;
				default:
				{
					archivo=null;
					archivos=null;	
				}break;
			}
		}
		
		public File getArchivo()
		{
			return archivo;
		}
		
		public File[] getArchivos()
		{
			return archivos;
		}
	}    

    /**
     * Clase visual para mostrar un chooser para la seleccion de un/os archivo/s
     */	
	static class SeleccionadorArchivo extends JDialog
	{
		private File archivo,archivos[];
	    private JFileChooser chooser;
	    private String filtros[];
	
		public SeleccionadorArchivo (boolean multiseleccion,String f[],String directorioComienzo)
		{
			filtros=f;
			chooser = new JFileChooser();
			chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			if(multiseleccion)
			{
				chooser.setMultiSelectionEnabled(true);
			} else
			{
				chooser.setMultiSelectionEnabled(false);
			}
			if (filtros!=null)
			{
				for (int i=0;i<filtros.length;i++)
				{
					if (GestionNotacionArchivo.esExtensionArchivo("."+filtros[i]))
					{
						chooser.addChoosableFileFilter(new ExampleFileFilter(filtros[i],"Archivo "+filtros[i]));
					} else
					{
						//System.out.println(filtros[i]+" no corresponde a un filtro");
					}
				}
			}			
			try
			{
				File comienzo=getDirectorio(directorioComienzo);
				if (comienzo!=null)
				{
					chooser.setCurrentDirectory(comienzo);
				}			
			} catch (Exception qq) {;}
			chooser.setDialogType(JFileChooser.OPEN_DIALOG);
			int retval = chooser.showDialog(null, null);
			switch (retval)
			{
				case JFileChooser.APPROVE_OPTION:
				{
					if (multiseleccion)
					{
						archivos = chooser.getSelectedFiles();
						archivos=getArchivosConsistentesExtensiones(archivos,filtros);
					} else
					{
						archivo = chooser.getSelectedFile();
						if(!esArchivoConsistenteExtensiones(archivo,filtros))
						{
							archivo=null;
						}
					}
				} break;
				default:
				{
					archivo=null;
					archivos=null;	
				}break;
			}
		}
		
		public File getArchivo()
		{
			return archivo;
		}
		
		public File[] getArchivos()
		{
			return archivos;
		}
	}    

	/** 
	 * Retorna los archivos de un vector pasado por parametro que ha cumplido con alguna regla de filtrado, las cuales tambien fueron pasadas por parametro
	 * @param archivos vector de File que son los archivos cuya consistencia de filtrado de extension es verificada
	 * @param filtros vector de String que constituyen las reglas de filtrado a considerar
	 * @return vector de File con los vectores que han superado las reglas de filtrado
	 */
	private static File[] getArchivosConsistentesExtensiones(File archivos[],String filtros[])	
	{
		Lista lista=new Lista();
		for (int i=0;i<archivos.length;i++)
		{
			if (esArchivoConsistenteExtensiones(archivos[i],filtros))
			lista.insertar(archivos[i]);
		}
		return getArchivosLista(lista);
		
	}
	
	/**
	 * Indica si un archivo pasado por parametro supera alguna regla de filtrado del conjunto de reglas pasados por parametro en forma de vector
	 * @param archivo File que es el archivo cuya consistencia de filtrado de extension es evaluada
	 * @param filtros vector de String que constituyen las reglas de filtrado a considerar
	 * @return boolean indicando si el archivo ha cumplido con alguna regla de filtrado
	 */
	private static boolean esArchivoConsistenteExtensiones(File archivo,String filtros[])
	{
		for (int i=0;i<filtros.length;i++)
		{
			if (GestionString.contieneStringFinal(archivo.getName().toLowerCase(),("."+filtros[i]).toLowerCase()))
			{
				//System.out.println("archivo "+archivo.getName()+" es consistente");
				return true;
			}
		}
		//System.out.println("archivo "+archivo.getName()+" no es consistente");
		return false;
	}

	/**
	 * Retorna un File que representa un directorio conociendo su path, pasado por parametro
	 * @param pathDirectorio String que es el path del directorio que se quiere obtener
	 * @return File que es el directorio requerido, o null si no se trata de un directorio o no existe
	 */
	private static File getDirectorio(String pathDirectorio)
	{
		if (pathDirectorio==null||pathDirectorio.equals("")) return null;
		File directorio=new File(pathDirectorio);
		if (directorio.exists()&&directorio.isDirectory()) return directorio;
		return null;
	}
	
	/** 
	 * Realiza la grabacion en disco de un objeto (de tipo Object),  a traves de la seleccion de un directorio
	 * Se conoce el nombre de archivo que se le quiere asignar y el nombre del directorio que quiere ser mostrado inicialmente (pasado por parametro)
	 * @param objeto Objet que se quiere grabar
	 * @param nombreArchivo String que es el nombre que se quiere asignar al objeto persistente
	 * @param pathDirectorioComienzo String que representa el path del directorio que se quiere mostrar inicialmente
	 * @return boolean que indica el exito de la grabacion
	 */
	public static boolean grabarObjetoSeleccionandoDirectorio(Object objeto,String nombreArchivo,String pathDirectorioComienzo)
	{
		if (nombreArchivo==null||!GestionNotacionArchivo.esNombreArchivo(nombreArchivo)) return false;
		File directorio=seleccionarDirectorio(pathDirectorioComienzo);
		if (directorio!=null)
		{
			return grabarObjectArchivo(objeto,directorio.getAbsolutePath()+"/"+nombreArchivo);
		}
		return false;
	}
	
	/**
	 * Retorna el date de ultima modificacion del archivo
	 * @return date de ultima modificacion del archivo
	 */	
	public static Date getDateUltimaModificacion(File archivo)
	{
		if (archivo==null) return null;
		return GestionDate.getDate(archivo.lastModified());
	}
	
	/**
	 * Acceso al directorio actual
	 * @return directorio actual
	 */
	public static String getDirectorioActual()
	{
		File file=new File(".");
		String actual=file.getAbsolutePath();
		return actual.substring(0,actual.length()-1);
	}
}