/*
 * Gears.java
 *
 * Created on 13 de marzo de 2008, 20:39
 *
 *  Copyright (C) -  2008 - juan
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
package bits.back;

import java.io.File;



/**
 * El corazon de la aplicación
 * @author JuanBC
 * @version 1.0
 */
public class Gears {

    private File fileFrom;
    private File fileTo;
    private String toName;
    private String fromName;

    public Gears(File f) {
        Debug.out("Abriendo Archivo: " + f.getAbsolutePath());
        this.fileFrom = f;
        this.fromName=f.getName();
        this.toName=SufixGenerator.getName(f);
        fileTo = new File(this.getPathTo());
        Debug.out("Creado o Abierto el archivo para escribir: "+ fileTo.getAbsolutePath());
    }
    
    public String getPathFrom(){
        return fileFrom.getAbsolutePath();
    }
    
    public String getPathTo(){
        String aux=this.getPathFrom();
        return aux.replaceAll(this.fromName,this.toName);
    }
    
    public void convert(){
        
    }
    
    public void close(){
        Debug.out("Cerrando...");
    }
    
}
