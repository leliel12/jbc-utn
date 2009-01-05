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
package com.db4o.db4ounit.common.refactor;

import com.db4o.*;
import com.db4o.config.*;
import com.db4o.ext.*;
import com.db4o.foundation.io.*;

import db4ounit.*;

public abstract class AccessFieldTestCaseBase {

	private final String DATABASE_PATH = Path4.getTempFileName();

	public void setUp() throws Exception {
		deleteFile();
		withDatabase(new DatabaseAction() {
			public void runWith(ObjectContainer db) {
				db.store(newOriginalData());
			}
		});
	}

	public void tearDown() throws Exception {
		deleteFile();
	}

	protected void renameClass(Class origClazz, String targetName) {
		Configuration config = Db4o.newConfiguration();
		config.objectClass(origClazz).rename(targetName);
		withDatabase(config, new DatabaseAction() {
			public void runWith(ObjectContainer db) {
				// do nothing
			}
		});
	}

	protected abstract Object newOriginalData();

	protected void assertField(final Class targetClazz, final String fieldName, final Class fieldType, final Object fieldValue) {
				withDatabase(new DatabaseAction() {
					public void runWith(ObjectContainer db) {
						StoredClass storedClass = db.ext().storedClass(targetClazz);
						StoredField storedField = storedClass.storedField(fieldName, fieldType);
						ObjectSet result = db.query(targetClazz);
						Assert.areEqual(1, result.size());
						Object obj = result.next();
						Object value = storedField.get(obj);
						Assert.areEqual(fieldValue, value);
					}
				});
			}

	private void deleteFile() {
		File4.delete(DATABASE_PATH);
	}

	private static interface DatabaseAction {
		void runWith(ObjectContainer db);
	}

	private void withDatabase(DatabaseAction action) {
		withDatabase(Db4o.newConfiguration(), action);
	}

	private void withDatabase(Configuration config, DatabaseAction action) {
		ObjectContainer db = Db4o.openFile(config, DATABASE_PATH);
		try {
			action.runWith(db);
		}
		finally {
			db.close();
		}
	}

}