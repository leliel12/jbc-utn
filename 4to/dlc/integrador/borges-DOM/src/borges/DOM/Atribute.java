package borges.DOM;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author juan
 */
public class Atribute {

    private String name;
    private String value;

    public Atribute(String name, String value) {
        this.name = name;
        this.value = value;
    }
    
    public Atribute(){
        
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    
    
    
}
