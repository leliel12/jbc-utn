package interfaz;

/**
 * Clase de prueba para el editor simple: sólo contiene el método main 
 * @author Ing. Valerio Frittelli
 * @version Marzo de 2008
 */
public class Principal {

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaEditor().setVisible(true);
            }
        });
    }
}
