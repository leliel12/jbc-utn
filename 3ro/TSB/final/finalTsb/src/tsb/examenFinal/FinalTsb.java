package tsb.examenFinal;

import tsb.examenFinal.ui.MainJFrame;

/**
 *
 * Lansa la aplicacion
 */
public class FinalTsb {

    public static void main(String[] args) {
        try {
            javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            String error = "Failure in charge system Look and Feel";
            System.err.println(error);
        }
        MainJFrame.showWindow();
    }
}
