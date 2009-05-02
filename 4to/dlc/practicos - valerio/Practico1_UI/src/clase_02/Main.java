package clase_02;

import clase_02.view.MainJFrame;
import java.io.PrintStream;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class Main
{
  public static void main(String[] args)
  {
    try
    {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    } catch (Exception e) {
      String error = "Failure in charge system Look and Feel";
      System.err.println(error);
      JOptionPane.showMessageDialog(null, error, "ERROR", 0);
    }
    MainJFrame.open();
  }
}