/*
 * RAFConstants.java
 *
 * Created  2008, 20:39
 *
 *  Copyright (C) -  2008 - JuanBC
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
package clase_02.back;

/**
 * Contiene constantes para forma de abrir un RandomAccessFile;
 * @author JuanBC
 * @version 1.0
 */
public abstract class RAFConstants {

    /** Read Only */
    public static final String RO = "r";
    /** Read & Write */
    public static final String RW = "rw";
    /** Read & Write Sincrono de datos y metadatos del archivo(se graba en forma sincrona en el dispositivo) */
    public static final String RWS = "rws";
    /** Read & Write Sincrono de datos de archivo(se graba en forma sincrona en el dispositivo) */
    public static final String RWD = "rwd";
}
