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

import com.db4o.config.*;
import com.db4o.internal.handlers.*;
import com.db4o.typehandlers.*;

import db4ounit.*;
import db4ounit.extensions.*;

public class IgnoreFieldsTypeHandlerTestCase extends AbstractDb4oTestCase {
	
	public static class Item1 {
		
		public int id1;
		
	}
	
	public static class Item2 extends Item1 {
		
		public int id2;
		
	}

	public static class Item3 extends Item2 {
		
		public int id3;
		
	}
	
	public static class Item4 extends Item3 {
		
		public int id4;
		
	}
	
	public static class Item5 extends Item4 {
		
		public int id5;
		
	}
	
	protected void configure(Configuration config) throws Exception {
		config.registerTypeHandler(new SingleClassTypeHandlerPredicate(Item2.class), new IgnoreFieldsTypeHandler());
		config.registerTypeHandler(new SingleClassTypeHandlerPredicate(Item4.class), new IgnoreFieldsTypeHandler());
	}
	
	protected void store() throws Exception {
		Item5 item = new Item5();
		item.id1 = 1;
		item.id2 = 2;
		item.id3 = 3;
		item.id4 = 4;
		item.id5 = 5;
		store(item);
	}
	
	public void test(){
		Item5 item = (Item5) retrieveOnlyInstance(Item5.class);
		Assert.areEqual(1, item.id1);
		Assert.areEqual(0, item.id2);
		Assert.areEqual(3, item.id3);
		Assert.areEqual(0, item.id4);
		Assert.areEqual(5, item.id5);
	}

}
