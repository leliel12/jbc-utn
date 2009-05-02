package clase_02.view;

import clase_02.back.Book;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

public class AddBookJDialog extends JDialog
{
  private static int isbn;
  private static String nombre;
  private static float precio;
  private JButton jButton1;
  private JButton jButton2;
  private JLabel jLabel1;
  private JLabel jLabel2;
  private JLabel jLabel3;
  private JLabel jLabel5;
  private JLabel jLabel6;
  private JLabel jLabel7;
  private JTextField jTextField1;
  private JTextField jTextField2;
  private JTextField jTextField3;

  public static Book open()
  {
    new AddBookJDialog().setVisible(true);
    if (nombre != null) return new Book(isbn, nombre, precio);
    return null;
  }

  private AddBookJDialog()
  {
    super(new JFrame(), true);
    initComponents();
    emptyFields();
    nombre = null;
  }

  private void emptyFields()
  {
    boolean a = true;
    this.jLabel5.setText("OK!");
    this.jLabel6.setText("OK!");
    this.jLabel7.setText("OK!");
    if (this.jTextField1.getText().trim().compareTo("") == 0) {
      a = false;
      this.jLabel5.setText("");
    }
    if (this.jTextField2.getText().trim().compareTo("") == 0) {
      a = false;
      this.jLabel6.setText("");
    }
    if (this.jTextField3.getText().trim().compareTo("") == 0) {
      a = false;
      this.jLabel7.setText("");
    }
    this.jButton1.setEnabled(a);
    canParse();
  }

  private void canParse()
  {
    boolean a = true;
    try {
      isbn = Integer.parseInt(this.jTextField1.getText());
    } catch (Exception ex) {
      this.jLabel5.setText("");
      a = false;
    }
    try {
      String aux = this.jTextField3.getText().replace(',', '.');
      precio = Float.parseFloat(aux);
    } catch (Exception aux) {
      this.jLabel7.setText("");
      a = false;
    }
    this.jButton1.setEnabled(a);
  }

  private void initComponents()
  {
    this.jLabel1 = new JLabel();
    this.jLabel2 = new JLabel();
    this.jLabel3 = new JLabel();
    this.jTextField1 = new JTextField();
    this.jTextField2 = new JTextField();
    this.jTextField3 = new JTextField();
    this.jButton1 = new JButton();
    this.jButton2 = new JButton();
    this.jLabel5 = new JLabel();
    this.jLabel6 = new JLabel();
    this.jLabel7 = new JLabel();

    setDefaultCloseOperation(2);
    setTitle("Agregar Libro...");
    setModal(true);
    setResizable(false);

    this.jLabel1.setText("Nombre");

    this.jLabel2.setText("ISBN");

    this.jLabel3.setText("Precio");

    this.jTextField1.addKeyListener(new KeyAdapter() {
      public void keyReleased(KeyEvent evt) {
        AddBookJDialog.this.jTextField1KeyReleased(evt);
      }

    });
    this.jTextField2.addKeyListener(new KeyAdapter() {
      public void keyReleased(KeyEvent evt) {
        AddBookJDialog.this.jTextField2KeyReleased(evt);
      }

    });
    this.jTextField3.addKeyListener(new KeyAdapter() {
      public void keyReleased(KeyEvent evt) {
        AddBookJDialog.this.jTextField3KeyReleased(evt);
      }

    });
    this.jButton1.setText("Aceptar");
    this.jButton1.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        AddBookJDialog.this.jButton1ActionPerformed(evt);
      }

    });
    this.jButton2.setText("Cancelar");
    this.jButton2.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        AddBookJDialog.this.jButton2ActionPerformed(evt);
      }

    });
    this.jLabel5.setFont(new Font("Zekton", 1, 13));
    this.jLabel5.setForeground(new Color(61, 185, 13));
    this.jLabel5.setText("OK!");

    this.jLabel6.setFont(new Font("Zekton", 1, 13));
    this.jLabel6.setForeground(new Color(61, 185, 13));
    this.jLabel6.setText("OK!");

    this.jLabel7.setFont(new Font("Zekton", 1, 13));
    this.jLabel7.setForeground(new Color(61, 185, 13));
    this.jLabel7.setText("OK!");

    GroupLayout layout = new GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jLabel1).addComponent(this.jLabel2).addComponent(this.jLabel3)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jTextField1, -2, 433, -2).addComponent(this.jTextField3, -2, 433, -2).addComponent(this.jTextField2, -2, 433, -2)).addGap(18, 18, 18).addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING).addComponent(this.jLabel6).addComponent(this.jLabel5).addComponent(this.jLabel7))).addGroup(layout.createSequentialGroup().addComponent(this.jButton2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jButton1))).addContainerGap()));

    layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jLabel2).addComponent(this.jTextField1, -2, -1, -2).addComponent(this.jLabel5)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jLabel1).addComponent(this.jLabel6).addComponent(this.jTextField2, -2, -1, -2)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jLabel3).addComponent(this.jLabel7).addComponent(this.jTextField3, -2, -1, -2)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jButton1).addComponent(this.jButton2)).addContainerGap(-1, 32767)));

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    setBounds((screenSize.width - 573) / 2, (screenSize.height - 181) / 2, 573, 181); }

  private void jTextField3KeyReleased(KeyEvent evt) {
    emptyFields();
  }

  private void jTextField2KeyReleased(KeyEvent evt) {
    emptyFields();
  }

  private void jTextField1KeyReleased(KeyEvent evt) {
    emptyFields();
  }

  private void jButton1ActionPerformed(ActionEvent evt) {
    nombre = this.jTextField2.getText();
    dispose();
  }

  private void jButton2ActionPerformed(ActionEvent evt) {
    dispose();
  }

  public static void main(String[] args)
  {
    EventQueue.invokeLater(new Runnable()
    {
      public void run() {
        AddBookJDialog dialog = new AddBookJDialog(null);
        dialog.addWindowListener(new WindowAdapter()
        {
          public void windowClosing(WindowEvent e) {
            System.exit(0);
          }

        });
        dialog.setVisible(true);
      }
    });
  }
}