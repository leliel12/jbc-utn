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
package com.db4o.db4ounit.common.internal;

import com.db4o.ext.*;

import db4ounit.*;
import db4ounit.extensions.*;
import db4ounit.extensions.fixtures.*;

public class StoredClassInstanceCountTestCase extends AbstractDb4oTestCase {

	public static class ItemA {
	}

	public static class ItemB {
	}

	private static final int COUNT_A = 5;
	
	
	protected void store() throws Exception {
		for(int idx = 0; idx < COUNT_A; idx++) {
			store(new ItemA());
		}
		store(new ItemB());
	}

	public void testInstanceCount() {
		assertInstanceCount(ItemA.class, COUNT_A);
		assertInstanceCount(ItemB.class, 1);
		store(new ItemA());
		deleteAll(ItemB.class);
		assertInstanceCount(ItemA.class, COUNT_A + 1);
		assertInstanceCount(ItemB.class, 0);
	}

	public void testTransactionalInstanceCount() {
		if(!isClientServer()) {
			return;
		}
		ExtObjectContainer otherClient = ((Db4oClientServer) fixture()).openNewClient();
		store(new ItemA());
		deleteAll(ItemB.class);
		assertInstanceCount(db(), ItemA.class, COUNT_A + 1);
		assertInstanceCount(db(), ItemB.class, 0);
		assertInstanceCount(otherClient, ItemA.class, COUNT_A);
		assertInstanceCount(otherClient, ItemB.class, 1);
		db().commit();
		assertInstanceCount(db(), ItemA.class, COUNT_A + 1);
		assertInstanceCount(db(), ItemB.class, 0);
		assertInstanceCount(otherClient, ItemA.class, COUNT_A + 1);
		assertInstanceCount(otherClient, ItemB.class, 0);
		otherClient.store(new ItemB());
		assertInstanceCount(db(), ItemB.class, 0);
		assertInstanceCount(otherClient, ItemB.class, 1);
		otherClient.commit();
		assertInstanceCount(db(), ItemB.class, 1);
		assertInstanceCount(otherClient, ItemB.class, 1);
		otherClient.close();
	}
	
	private void assertInstanceCount(Class clazz, int expectedCount) {
		assertInstanceCount(db(), clazz, expectedCount);
	}

	private void assertInstanceCount(ExtObjectContainer container, Class clazz, int expectedCount) {
		StoredClass storedClazz = container.ext().storedClass(clazz);
		Assert.areEqual(expectedCount, storedClazz.instanceCount());
	}
	
	public static void main(String[] args) {
		new StoredClassInstanceCountTestCase().runAll();
	}
}
