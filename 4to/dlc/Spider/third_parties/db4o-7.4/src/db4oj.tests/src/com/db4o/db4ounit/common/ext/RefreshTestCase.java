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
package com.db4o.db4ounit.common.ext;

import com.db4o.*;
import com.db4o.config.*;
import com.db4o.ext.*;
import com.db4o.query.*;

import db4ounit.Assert;
import db4ounit.extensions.Db4oClientServerTestCase;

public class RefreshTestCase extends Db4oClientServerTestCase {

	public static void main(String[] args) {
		new RefreshTestCase().runAll();
	}

	public static class Item {
		public String name;
		public Item child;

		public Item(String name, Item child) {
			this.name = name;
			this.child = child;
		}
	}
	
	protected void configure(Configuration config) throws Exception {
		config.objectClass(Item.class).cascadeOnUpdate(true);
	}

	protected void store() {
		Item r3 = new Item("o3", null);
		Item r2 = new Item("o2", r3);
		Item r1 = new Item("o1", r2);
		store(r1);
	}

	public void test() {
	    
		ExtObjectContainer oc1 = openNewClient();
		ExtObjectContainer oc2 = openNewClient();
		
		try {
			Item r1 = getRoot(oc1);
			r1.name = "cc";
			oc1.refresh(r1, 0);
			Assert.areEqual("cc", r1.name);
			oc1.refresh(r1, 1);
			Assert.areEqual("o1", r1.name);
			r1.child.name = "cc";
			oc1.refresh(r1, 1);
			Assert.areEqual("cc", r1.child.name);
			oc1.refresh(r1, 2);
			Assert.areEqual("o2", r1.child.name);

			Item r2 = getRoot(oc2);
			r2.name = "o21";
			r2.child.name = "o22";
			r2.child.child.name = "o23";
			oc2.store(r2);
			oc2.commit();

			oc1.refresh(r1, 3);
			Assert.areEqual("o21", r1.name);
			Assert.areEqual("o22", r1.child.name);
			Assert.areEqual("o23", r1.child.child.name);

		} finally {
			oc1.close();
			oc2.close();
		}
	}

	private Item getRoot(ObjectContainer oc) {
		return getByName(oc, "o1");
	}

	private Item getByName(ObjectContainer oc, final String name) {
		Query q = oc.query();
		q.constrain(Item.class);
		q.descend("name").constrain(name);
		ObjectSet objectSet = q.execute();
		return (Item) objectSet.next();
	}

}
