/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dtd.tools;

import java.util.ArrayList;

/**
 *
 * @author juan
 */
public class Queue<E> extends ArrayList<E> {

    public void push(E toAdd) {
        super.add(toAdd);
    }
    
    public E peek(){
        return super.get(0);
    }
    
    public E pop(){
        return super.remove(0);
    }
    
}
