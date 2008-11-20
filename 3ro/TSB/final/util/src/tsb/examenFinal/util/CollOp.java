package tsb.examenFinal.util;

import java.util.Collection;

/**
 *
 *  Llena una coleccion con un array
 */
public abstract class CollOp {

    public static Collection fillCollection(final Collection c, Object[] array){
        for (int i = 0; i < array.length; i++) {
            Object object = array[i];
            c.add(object);
        }
        return c;
    }

}
