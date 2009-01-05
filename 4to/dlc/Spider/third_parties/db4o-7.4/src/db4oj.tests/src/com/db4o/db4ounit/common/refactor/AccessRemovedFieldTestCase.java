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

import com.db4o.internal.*;

import db4ounit.*;

public class AccessRemovedFieldTestCase extends AccessFieldTestCaseBase implements TestLifeCycle {

	private static final Class FIELD_TYPE = Integer.TYPE;
	private static final String FIELD_NAME = "_value";
	private static final int FIELD_VALUE = 42;
	
	public void testRemovedField() {
		final Class targetClazz = RemovedFieldData.class;
		renameClass(OriginalData.class, ReflectPlatform.fullyQualifiedName(targetClazz));
		assertField(targetClazz, FIELD_NAME, FIELD_TYPE, new Integer(FIELD_VALUE));
	}

	protected Object newOriginalData() {
		return new OriginalData(FIELD_VALUE);
	}

	public static class OriginalData {
		public int _value;
		public String _name;
			
		public OriginalData(int value) {
			_value = value;
		}
	}
	
	public static class RemovedFieldData {
		public String _name;
	}

}
