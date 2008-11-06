package soporte;

/**
 * Esta clase muestra la forma de crear filtros para nombres de archivos para un JFileChooser. 
 * Filtra todos los tipos de archivos, excepto aquellos cuyas extensiones conoce. Las extensiones
 * son de la forma ".xxx" (para cualquier valor que asuma "xxx"). Se ignora la diferencia entre 
 * minúsculas y mayúsculas.
 *
 * Ejemplo: El siguiente segmento crea un nuevo filtro que filtra todos los archivos excepto los
 * que lleven extensión gif y jpg:
 *
 *     JFileChooser chooser = new JFileChooser();
 *     SimpleFileFilter filter = new SimpleFileFilter(new String{"gif", "jpg"}, "JPEG & GIF Images");
 *     chooser.addChoosableFileFilter(filter);
 *     chooser.showOpenDialog(this);
 *
 * @author Jeff Dinkins - Sun Microsystems, Inc. All Rights Reserved. Copyright (c) 2004.
 * @version 1.16 04/07/26
 */


import java.io.File;
import java.util.Hashtable;
import java.util.Enumeration;
import javax.swing.*;
import javax.swing.filechooser.*;

public class SimpleFileFilter extends FileFilter {

    private static String TYPE_UNKNOWN = "Tipo desconocido";
    private static String HIDDEN_FILE = "Archivo oculto";

    private Hashtable filters = null;
    private String description = null;
    private String fullDescription = null;
    private boolean useExtensionsInDescription = true;

    /**
     * Crea un filtro. Si no se agregan filtros, entonces todos los archivos son aceptados.
     * @see #addExtension
     */
    public SimpleFileFilter() {
	this.filters = new Hashtable();
    }

    /**
     * Crea un filtro que acepta archivos con la extensión dada.
     * Ejemplo: new SimpleFileFilter("jpg");
     * @see #addExtension
     */
    public SimpleFileFilter(String extension) {
	this(extension,null);
    }

    /**
     * Crea un filtro que acepta el tipo de archivo dado.
     * Ejemplo: new SimpleFileFilter("jpg", "JPEG Image Images");
     *
     * Note que el "." antes de la extensión no es necesario. Si se coloca, será ignorado. 
     * @see #addExtension
     */
    public SimpleFileFilter(String extension, String description) {
	this();
	if(extension!=null) addExtension(extension);
 	if(description!=null) setDescription(description);
    }

    /**
     * Crea un filtro a partir de un arreglo de cadenas.
     * Ejemplo: new SimpleFileFilter(String {"gif", "jpg"});
     *
     * Note que el "." antes de la extensión no es necesario. Si se coloca, será ignorado. 
     * @see #addExtension
     */
    public SimpleFileFilter(String[] filters) {
	this(filters, null);
    }

    /**
     * Crea un filtro a partir de un arreglo de cadenas y una descripción.
     * Ejemplo: new ExampleFileFilter(String {"gif", "jpg"}, "Gif and JPG Images");
     *
     * Note que el "." antes de la extensión no es necesario. Si se coloca, será ignorado. 
     * @see #addExtension
     */
    public SimpleFileFilter(String[] filters, String description) {
	this();
	for (int i = 0; i < filters.length; i++) {
	    // agrega los filtros uno por uno
	    addExtension(filters[i]);
	}
 	if(description!=null) setDescription(description);
    }

    /**
     * Retorna true si este archivo debería mostrarse en el panel de directorios,
     * false en caso contrario. Los archivos que comiencen con "." se ignoran.
     * @see #getExtension
     * @see FileFilter#accepts
     */
    public boolean accept(File f) {
	if(f != null) {
	    if(f.isDirectory()) {
		return true;
	    }
	    String extension = getExtension(f);
	    if(extension != null && filters.get(getExtension(f)) != null) {
		return true;
	    };
	}
	return false;
    }

    /**
     * Retorna la extensión del nombre de un archivo. 
     * @see #getExtension
     * @see FileFilter#accept
     */
     public String getExtension(File f) {
	if(f != null) {
	    String filename = f.getName();
	    int i = filename.lastIndexOf('.');
	    if(i>0 && i<filename.length()-1) {
		return filename.substring(i+1).toLowerCase();
	    };
	}
	return null;
    }

    /**
     * Agrega un tipo de archivo al filtro.
     * Ejemplo: el siguiente código creará un filtro que fitlra todos los archivos excepto aquellos
     * con extensión ".jpg" y ".tif":
     *
     *   SimpleFileFilter filter = new SimpleFileFilter();
     *   filter.addExtension("jpg");
     *   filter.addExtension("tif");
     */
    public void addExtension(String extension) {
	if(filters == null) {
	    filters = new Hashtable(5);
	}
	filters.put(extension.toLowerCase(), this);
	fullDescription = null;
    }


    /**
     * Retorna una descripción genérica, apta para comprensión humana de este filtro.
     * Por ejemplo: "JPEG and GIF Image Files (*.jpg, *.gif)"
     *
     * @see setDescription
     * @see setExtensionListInDescription
     * @see isExtensionListInDescription
     * @see FileFilter#getDescription
     */
    public String getDescription() {
	if(fullDescription == null) {
	    if(description == null || isExtensionListInDescription()) {
 		fullDescription = description==null ? "(" : description + " (";
		// crea la descripción desde la lista de extensiones 
		Enumeration extensions = filters.keys();
		if(extensions != null) {
		    fullDescription += "." + (String) extensions.nextElement();
		    while (extensions.hasMoreElements()) {
			fullDescription += ", ." + (String) extensions.nextElement();
		    }
		}
		fullDescription += ")";
	    } else {
		fullDescription = description;
	    }
	}
	return fullDescription;
    }

    /**
     * Configura la descripción general. 
     * Ejemplo: filter.setDescription("Gif and JPG Images");
     *
     * @see setDescription
     * @see setExtensionListInDescription
     * @see isExtensionListInDescription
     */
    public void setDescription(String description) {
	this.description = description;
	fullDescription = null;
    }

    /**
     * Determina si la extensión debería aparecer en la descripción general.
     * Sólo es relevante si una descripción fue provista en el cosntructor o usando
     * setDescription();
     *
     * @see getDescription
     * @see setDescription
     * @see isExtensionListInDescription
     */
    public void setExtensionListInDescription(boolean b) {
	useExtensionsInDescription = b;
	fullDescription = null;
    }

    /**
     * Retorna un boolean que indica si la lista de extensiones está en la descripción general.
     * Sólo es relevante si una descripción fue provista en el cosntructor o usando
     * setDescription();
     *
     * @see getDescription
     * @see setDescription
     * @see setExtensionListInDescription
     */
    public boolean isExtensionListInDescription() {
	return useExtensionsInDescription;
    }
}
