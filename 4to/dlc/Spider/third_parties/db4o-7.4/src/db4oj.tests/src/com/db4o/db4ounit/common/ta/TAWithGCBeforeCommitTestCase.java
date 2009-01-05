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
package com.db4o.db4ounit.common.ta;

import com.db4o.activation.*;
import com.db4o.config.*;
import com.db4o.ta.*;

import db4ounit.*;
import db4ounit.extensions.*;

public class TAWithGCBeforeCommitTestCase extends AbstractDb4oTestCase {

	private static final String UPDATED_ID = "X";
	private static final String ORIG_ID = "U";

	public static class Item implements Activatable {

		public String _id;
		
		public Item(String id) {
			_id = id;
		}
		
		public void id(String id) {
			activate(ActivationPurpose.WRITE);
			_id = id;
		}
		
		public String id() {
			activate(ActivationPurpose.READ);
			return _id;
		}
		
		private transient Activator _activator;

		public void bind(Activator activator) {
			if (this._activator == activator) {
				return;
			}
			if (activator != null && this._activator != null) {
				throw new IllegalStateException();
			}
			this._activator = activator;
		}

		public void activate(ActivationPurpose purpose) {
			if (this._activator == null) return;
			this._activator.activate(purpose);
		}

	}

	protected void configure(Configuration config) throws Exception {
		config.add(new TransparentPersistenceSupport());
	}
	
	protected void store() throws Exception {
		store(new Item(ORIG_ID));
	}
	
	public void testCommit() {
		Item item = (Item)retrieveOnlyInstance(Item.class);
		item.id(UPDATED_ID);
		item = null;
		System.gc();
		db().commit();
		item = (Item)retrieveOnlyInstance(Item.class);
		db().refresh(item, Integer.MAX_VALUE);
		Assert.areEqual(UPDATED_ID, item.id());
	}

	public void testRollback() {
		Item item = (Item)retrieveOnlyInstance(Item.class);
		item.id(UPDATED_ID);
		item = null;
		System.gc();
		db().rollback();
		item = (Item)retrieveOnlyInstance(Item.class);
		db().refresh(item, Integer.MAX_VALUE);
		Assert.areEqual(ORIG_ID, item.id());
	}

	public static void main(String[] args) {
		new TAWithGCBeforeCommitTestCase().runAll();
	}
}
