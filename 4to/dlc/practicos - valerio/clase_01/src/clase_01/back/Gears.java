 /* 
 *  Gears.java
 * 
 *  Copyright (C) -  __YEAR__ juan
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
package clase_01.back;

import java.io.*;

/**
 *  Clase para crear objetos que manejan archivos para jValerio01
 * 
 * @author juan
 */
public class Gears {

    private final String sepChar = File.separator;
    private final File temp_dir = new File(System.getProperties().getProperty("java.io.tmpdir") + sepChar + "jvalerio01");

    /**
     * crea un objeto del tipo gears con un directorio temporal
     */
    public Gears() {
        temp_dir.mkdirs();
        temp_dir.deleteOnExit();
        System.out.println("Directorio temporal: " + temp_dir.getAbsolutePath());
    }

    /**
     * remueve todos los archivos .bak de un directorio dado
     * @param dir
     */
    public void removeBak(File dir) {
        if (dir != null) {
            System.out.println("A Eliminar los *.bak de: " + dir.getPath());
            for (int i = 0; i < dir.list().length; i++) {
                if (dir.list()[i].toLowerCase().endsWith(".bak") && !dir.listFiles()[i].isDirectory()) {
                    dir.listFiles()[i].delete();
                }
            }
        }
    }

    /**
     * mueve archivos de un directorio al directorio personal de la aplicacion
     * @param dir
     */
    public void moveFilesToTemp(File dir) {
        File destino;
        if (dir != null) {
            System.out.println("Moviendo a " + temp_dir + "(directorio temporal) el contenido de: " + dir.getPath());
            for (int i = 0; i < dir.list().length; i++) {
                if (!dir.listFiles()[i].isDirectory()) {
                    destino = new File(temp_dir.getAbsoluteFile() + sepChar + dir.listFiles()[i].getName());
                    destino.deleteOnExit();
                    this.move(dir.listFiles()[i], destino);
                }
            }
        }
    }

    /**
     * elimina un directorio sin importar su contenido
     * NOTA: tipor odt me estan generando kilombos
     * @param dir
     */
    public void delTree(File dir) {
        if (dir != null) {
            System.out.println("Destruyendo a: " + dir.getPath());
            remDir(dir);
            dir.delete();
        }
    }

    /**
     * funcion recursiva para eliminar un directorio
     * @param dir
     */
    public void remDir(File dir) {
        for (int i = 0; i < dir.list().length; i++) {
            if (dir.listFiles()[i].isDirectory()) {
                remDir(dir.listFiles()[i]);
            }
            dir.listFiles()[i].delete();
        }
    }

    /**
     * esta clase copia de un archivo a otro en modo binario
     * Si uno simplemente reutiliza el cambio de path, suele tener conflictos
     * si se "mueve" de un sistema de archivos a otros, como es mi caso
     * donde mi carpeta /tmp (linux) es una partición diferente
     * @param from
     * @param to
     */
    private void move(File from, File to) {
        System.out.println(from.getAbsolutePath() + " ---> " + to.getAbsolutePath());
        try {
            FileInputStream fileInput = new FileInputStream(from);
            BufferedInputStream bufferedInput = new BufferedInputStream(fileInput);
            FileOutputStream fileOutput = new FileOutputStream(to);
            BufferedOutputStream bufferedOutput = new BufferedOutputStream(fileOutput);
            byte[] array = new byte[1000];
            int leidos = bufferedInput.read(array);
            while (leidos > 0) {
                bufferedOutput.write(array, 0, leidos);
                leidos = bufferedInput.read(array);
            }
            bufferedInput.close();
            bufferedOutput.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        from.delete();
    }
}
