/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dtd.syntax.tmp;

/**
 *
 * @author juan
 */
public class Variable {
    private String name;
    private String value;

    public Variable(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public Variable() {
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
    
    public String toString(){
        return "Var->" + this.name +"="+this.value;
    }
    
}
