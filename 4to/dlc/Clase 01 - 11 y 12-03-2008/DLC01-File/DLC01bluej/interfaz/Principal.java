package interfaz;

/**
 * Clase para contener al método main de la aplicación que testea la
 * clase File
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
                new VentanaInicial().setVisible(true);
            }
        });
    }
    
}
