package tsb.examenFinal.util;

import java.util.ArrayList;

/**
 *
 * DataStructure de cola
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
