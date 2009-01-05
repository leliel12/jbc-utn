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
package com.db4o.db4ounit.common.defragment;

import com.db4o.*;
import com.db4o.config.*;
import com.db4o.query.*;

import db4ounit.*;
import db4ounit.extensions.*;
import db4ounit.extensions.fixtures.*;

public class DefragInheritedFieldIndexTestCase extends AbstractDb4oTestCase implements OptOutCS {

	private static final String FIELD_NAME = "_name";
	private static final String[] NAMES = {"Foo", "Bar", "Baz"};
	
	protected void configure(Configuration config) throws Exception {
		config.objectClass(ParentItem.class).objectField(FIELD_NAME).indexed(true);
	}

	protected void store() throws Exception {
		for (int nameIdx = 0; nameIdx < NAMES.length; nameIdx++) {
			store(new ChildItem(NAMES[nameIdx]));
		}
	}

	public void testDefragInheritedFieldIndex() throws Exception {
		assertQueryByIndex();
		defragment();
		assertQueryByIndex();
	}
	
	private void assertQueryByIndex() {
		Query query = newQuery(ChildItem.class);
		query.descend(FIELD_NAME).constrain(NAMES[0]);
		ObjectSet result = query.execute();
		Assert.areEqual(1, result.size());
		Assert.areEqual(NAMES[0], ((ChildItem)result.next())._name);
	}

	public static class ParentItem {
		public String _name;
		
		public ParentItem(String name) {
			_name = name;
		}
	}
	
	public static class ChildItem extends ParentItem {
		public ChildItem(String name) {
			super(name);
		}
	}
}
