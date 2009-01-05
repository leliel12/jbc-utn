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

import java.util.*;

import com.db4o.config.*;
import com.db4o.foundation.*;
import com.db4o.internal.*;
import com.db4o.internal.handlers.*;
import com.db4o.typehandlers.*;

import db4ounit.*;
import db4ounit.extensions.*;


public class SecondClassTestCase extends AbstractDb4oTestCase{
    
    
    static Hashtable4 objectIsSecondClass;
    
    public static class Item{
        
    }
    
    public static class CustomSecondClassItem{
        
    }
    
    public static class CustomFirstClassItem{
        
    }

    
    static{
        objectIsSecondClass = new Hashtable4();
        register(new Integer(1), true);
        register(new Date(), true);
        register("astring", true);
        register(new Item(), false);
        register(new int[] {1}, false);
        register(new Date[] {new Date()}, false);
        register(new Item[] {new Item()}, false);
        register(new CustomFirstClassItem(), false);
        register(new CustomSecondClassItem(), true);
    }

    private static void register(Object obj, boolean isSecondClass) {
        objectIsSecondClass.put(obj, new Boolean(isSecondClass));
    }
    
    public static class FirstClassTypeHandler extends FirstClassObjectHandler{
        
    }
    
    public static class SecondClassTypeHandler extends FirstClassObjectHandler implements EmbeddedTypeHandler{
        
    }

    
    protected void configure(Configuration config) throws Exception {
        config.registerTypeHandler(
            new SingleClassTypeHandlerPredicate(CustomFirstClassItem.class), 
            new FirstClassTypeHandler());
        config.registerTypeHandler(
            new SingleClassTypeHandlerPredicate(CustomSecondClassItem.class), 
            new SecondClassTypeHandler());
    }
    
    protected void store() throws Exception {
        store(new Item());
        store(new CustomFirstClassItem());
        store(new CustomSecondClassItem());
        
    }
    
    public void test(){
        Iterator4 i = objectIsSecondClass.keys();
        while(i.moveNext()){
            Object currentObject = i.current();
            boolean isSecondClass = ((Boolean)objectIsSecondClass.get(currentObject)).booleanValue();
            ClassMetadata classMetadata = container().classMetadataForObject(currentObject);
            Assert.areEqual(isSecondClass, classMetadata.isSecondClass());
        }
        
        
        
    }

}
