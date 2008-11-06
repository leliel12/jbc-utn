package test;
import java.io.*;
public class Principal02
{
    public static void main(String args[])
    {
       File fd = new File ("datos.dat");
       
       int c = 72;
       short s;
       char car;
       
       try
       {
            RandomAccessFile f = new RandomAccessFile(fd, "rw");
            f.writeInt(c); // graba 00000000 00000000 00000000 01001000
            f.seek(0);  // vuelvo al principio...
            
            s = f.readShort();  // recupera: 00000000 00000000
            car = f.readChar(); // recupera: 00000000 01001000
            
            System.out.println("Valor s:   " + s);
            System.out.println("Valor car: " + car);
            
            f.close();
       }
       catch(IOException e)
       {
            System.out.println("Error: " + e.getMessage());
            System.exit(1);
       }
    }
}
