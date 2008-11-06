/** 
 *  Main.java
 * 
 *  Copyright (C) - 2008 - Juanbc
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
package bits;

import bits.view.MainJFrame;


/**
 *
 * @author JuanBC
 * @version 1.0
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            String error = "Failure in charge system Look and Feel";
            System.err.println(error);
            javax.swing.JOptionPane.showMessageDialog(null, error, "ERROR", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
        MainJFrame.open();
   }
}
