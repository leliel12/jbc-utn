/* Copyright (C) 2004 - 2008  db4objects Inc.  http://www.db4o.com

This file is part of the db4o open source object database.

db4o is free software; you can redistribute it and/or modify it under
the terms of version 2 of the GNU General Public License as published
by the Free Software Foundation and as clarified by db4objects' GPL 
interpretation policy, available at
http://www.db4o.com/about/company/legalpolicies/gplinterpretation/
Alternatively you can write to db4objects, Inc., 1900 S Norfolk Street,
Suite 350, San Mateo, CA 94403, USA.

db4o is distributed in the hope that it will be useful, but WITHOUT ANY
WARRANTY; without even the implied warranty of MERCHANTABILITY or
FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
for more details.

You should have received a copy of the GNU General Public License along
with this program; if not, write to the Free Software Foundation, Inc.,
59 Temple Place - Suite 330, Boston, MA  02111-1307, USA. */
package com.db4o.db4ounit.common.handlers;

import com.db4o.foundation.*;
import com.db4o.internal.handlers.array.*;
import com.db4o.reflect.*;

import db4ounit.*;
import db4ounit.extensions.*;


/**
 * @exclude
 */
public class MultidimensionalArrayIterator4TestCase extends AbstractDb4oTestCase {
    
    public void testEmptyArray() {
        assertExhausted(iterate(new Object[0])); 
    }
    
    public void testStringArray() {
        Iterator4 i = iterate(new Object[] { new Object[]{"foo", "bar"}, new Object[] {"fly"} });
        Assert.isTrue(i.moveNext());
        Assert.areEqual("foo", i.current());
        
        Assert.isTrue(i.moveNext());
        Assert.areEqual("bar", i.current());
        
        Assert.isTrue(i.moveNext());
        Assert.areEqual("fly", i.current());
        
        assertExhausted(i);
    }
    
    public void testIntArray() {
        Iterator4 i = iterate(new int[][] { new int[]{1, 2}, new int[] {3} });
        Assert.isTrue(i.moveNext());
        Assert.areEqual(new Integer(1), i.current());
        
        Assert.isTrue(i.moveNext());
        Assert.areEqual(new Integer(2), i.current());
        
        Assert.isTrue(i.moveNext());
        Assert.areEqual(new Integer(3), i.current());
        
        assertExhausted(i);
    }

    
    private void assertExhausted(final Iterator4 i) {
        Assert.isFalse(i.moveNext());       
        Assert.expect(ArrayIndexOutOfBoundsException.class, new CodeBlock(){
            public void run() throws Throwable {
                System.out.println(i.current());
            }
        });
    }
    
    private Iterator4 iterate(Object[] array){
        return new MultidimensionalArrayIterator(reflectArray(), array);
    }
    
    private ReflectArray reflectArray(){
        return reflector().array();
    }


}
