package tsb.examenFinal.util;

/**
 * Esta clase empaqueta las variables de los XML
 *  Asi si tenemos un elemento <code>"<lalal atributo="valor">"</code>
 * se genera una instancia de esta clase con la sentencia
 * <code>new Atribute("atributo","valor");</code>
 */
public class Attribute {
    private String name;
    private String value;

    public Attribute(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public Attribute() {
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
    
    @Override
    public String toString(){
        return this.name +"=\""+this.value+"\"";
    }
    
}
