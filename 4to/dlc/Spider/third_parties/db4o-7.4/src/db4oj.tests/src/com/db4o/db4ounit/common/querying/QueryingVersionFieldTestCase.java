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
package com.db4o.db4ounit.common.querying;

import com.db4o.*;
import com.db4o.config.*;
import com.db4o.ext.*;
import com.db4o.query.*;

import db4ounit.*;
import db4ounit.extensions.*;


public class QueryingVersionFieldTestCase extends AbstractDb4oTestCase {
    
    public static void main(String[] arguments) {
        new QueryingVersionFieldTestCase().runSolo();
    }
    
    public static class Item {
        
        public String name;
        
        public Item(String name_){
            name = name_;
        }
        
    }
    
    protected void configure(Configuration config) throws Exception {
        config.objectClass(Item.class).generateVersionNumbers(true);
    }
    
    public void test(){
        
        storeItems(new String[] {"1", "2", "3"});
        db().commit();
        long initialTransactionVersionNumber = db().version();
        
        updateItem("2", "modified2");
        db().commit();
        long updatedTransactionVersionNumber = db().version();  
        
        Query q = db().query();
        q.constrain(Item.class);
        q.descend(VirtualField.VERSION).constrain(new Long(initialTransactionVersionNumber)).greater();
        
        // This part really isn't needed for this test case, but it shows, how changes
        // between two specific transaction commits can be queried.
        q.descend(VirtualField.VERSION).constrain(new Long(updatedTransactionVersionNumber)).smaller().equal();
        
        ObjectSet objectSet = q.execute();
        Assert.areEqual(1, objectSet.size());
        Item item = (Item) objectSet.next();
        Assert.areEqual("modified2", item.name);
        
        
        
    }

    private void updateItem(String originalName, String updatedName) {
        Item item = queryForItem(originalName);
        item.name = updatedName;
        store(item);
        
    }

    private Item queryForItem(String name) {
        Query q = newQuery(Item.class);
        q.descend("name").constrain(name);
        ObjectSet objectSet = q.execute();
        Assert.areEqual(1, objectSet.size());
        return (Item) objectSet.next();
    }

    private void storeItems(String[] names) {
        for (int i = 0; i < names.length; i++) {
            Item item = new Item(names[i]);
            store(item);
        }
    }

}
