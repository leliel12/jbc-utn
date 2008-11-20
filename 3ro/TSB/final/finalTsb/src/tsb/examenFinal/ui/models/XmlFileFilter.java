package tsb.examenFinal.ui.models;

import java.io.File;

/**
 *
 * Filtro de archivos pa que solo se vean los xml
 */
public class XmlFileFilter extends javax.swing.filechooser.FileFilter {

    @Override
    public boolean accept(File arg0) {
        if (arg0.isDirectory()) {
            return true;
        } else {
            try {
                String ext = arg0.getName();
                ext = ext.substring(ext.indexOf("."), ext.length());
                return ext.compareToIgnoreCase(".xml")==0;

            } catch (Exception e) {
                return false;
            }
        }

    }

    @Override
    public String getDescription() {
        return "XML file";
    }
}