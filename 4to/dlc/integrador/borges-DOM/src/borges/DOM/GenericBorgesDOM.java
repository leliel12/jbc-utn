package borges.DOM;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

/**
 *
 * @author juan
 */
public abstract class GenericBorgesDOM {
    
   private ArrayList<Atribute> atributes;

    public GenericBorgesDOM() {
        atributes = new ArrayList<Atribute>();
    }
    
    public void setAtribute(String name, String value){
        Atribute a= new Atribute(name, value);
        this.setAtribute(a);
    }
    
    public void setAtribute(Atribute atribute){
        this.atributes.add(atribute);
    }

    public Atribute[] getAtribute(String name){
        Vector<Atribute> v=new Vector<Atribute>();
        for (Iterator<Atribute> it = this.atributes.iterator(); it.hasNext();) {
            Atribute atribute = it.next();
            if(atribute.getName().compareToIgnoreCase(name)==0){
                v.add(atribute);
            }
        }//for
        return (Atribute[]) v.toArray();
    }   
   

}
