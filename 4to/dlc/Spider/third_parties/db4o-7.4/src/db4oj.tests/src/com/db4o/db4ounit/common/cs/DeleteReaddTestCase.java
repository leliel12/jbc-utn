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
package com.db4o.db4ounit.common.cs;

import com.db4o.ext.*;

import db4ounit.extensions.*;


public class DeleteReaddTestCase extends Db4oClientServerTestCase {
    
    public static class ItemParent {
        
    }
    
    public static class Item extends ItemParent{
        
        public String name;
        
        public Item(String name_){
            name = name_;
        }
    }
    
    protected void store() throws Exception {
        store(new Item("one"));
    }
    
    public void testDeleteReadd(){
        ExtObjectContainer client1 = db();
        ExtObjectContainer client2 = openNewClient();
        
        Item item1 = (Item) retrieveOnlyInstance(client1, Item.class);
        Item item2 = (Item) retrieveOnlyInstance(client2, Item.class);
        
        client1.delete(item1);
        
        client1.commit();
        
        client2.store(item2);
        client2.commit();
        client2.close();
        
        retrieveOnlyInstance(client1, Item.class);
        retrieveOnlyInstance(client1, ItemParent.class);
    }
    
    public static void main(String[] arguments) {
        new DeleteReaddTestCase().runClientServer();
    }

}
