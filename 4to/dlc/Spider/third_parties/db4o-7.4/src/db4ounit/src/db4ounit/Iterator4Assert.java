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
package db4ounit;

import com.db4o.foundation.*;


public class Iterator4Assert {

	public static void areEqual(Iterator4 expected, Iterator4 actual) {
		if (null == expected) {
			Assert.isNull(actual);
			return;
		}
		Assert.isNotNull(actual);		
		while (expected.moveNext()) {
			assertNext(expected.current(), actual);
		}
		if (actual.moveNext()) {
			unexpected(actual.current());
		}
	}

	private static void unexpected(Object element) {
		Assert.fail("Unexpected element: " + element);
	}

	public static void assertNext(final Object expected, Iterator4 iterator) {
		Assert.isTrue(iterator.moveNext(), "'" + expected + "' expected.");
		Assert.areEqual(expected, iterator.current());
	}

	public static void areEqual(Object[] expected, Iterator4 iterator) {
		areEqual(new ArrayIterator4(expected), iterator);
	}

	public static void sameContent(Object[] expected, Iterator4 actual) {
		sameContent(new ArrayIterator4(expected), actual);
	}

	public static void sameContent(Iterator4 expected, Iterator4 actual) {
		final Collection4 allExpected = new Collection4(expected);
		while (actual.moveNext()) {
			final Object current = actual.current();
			final Object removed = allExpected.remove(current);
			if (null == removed) {
				unexpected(current);
			}
		}
		Assert.isTrue(allExpected.isEmpty(), allExpected.toString());
	}

}
