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
package com.db4o.db4ounit.common.freespace;

import java.util.*;

import com.db4o.*;
import com.db4o.config.*;
import com.db4o.db4ounit.common.handlers.*;
import com.db4o.ext.*;

import db4ounit.*;


/**
 * @exclude
 */
public class FreespaceManagerMigrationTestCase extends FormatMigrationTestCaseBase {
    
    int [][] INT_ARRAY_DATA = { {1,2}, {3,4}};
    
    String [][] STRING_ARRAY_DATA = { {"a", "b"}, {"c","d"}};
    
    public static class StClass {
        
        public int id;

        public Vector vect;

        public Vector getVect() {
            return vect;
        }

        public void setVect(Vector vect) {
            this.vect = vect;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
    
    protected void configureForStore(Configuration config) {
        if(notApplicableForDb4oVersion()){
            return;
        }
        commonConfigure(config);
        config.freespace().useIndexSystem();
    }

    private boolean notApplicableForDb4oVersion() {
        return db4oMajorVersion() < 5;
    }
    
    protected void configureForTest(Configuration config) {
        if(notApplicableForDb4oVersion()){
            return;
        }
        commonConfigure(config);
        config.freespace().useBTreeSystem();
    }

    private void commonConfigure(Configuration config) {
        // config.blockSize(8);
        config.objectClass(StClass.class).cascadeOnActivate(true);
        config.objectClass(StClass.class).cascadeOnUpdate(true);
        config.objectClass(StClass.class).cascadeOnDelete(true);
        config.objectClass(StClass.class).minimumActivationDepth(5);
        config.objectClass(StClass.class).updateDepth(10);
    }

    protected void assertObjectsAreReadable(ExtObjectContainer objectContainer) {
        if(notApplicableForDb4oVersion()){
            return;
        }
        ObjectSet objectSet = objectContainer.query(StClass.class);
        for (int i = 0; i < 2; i++) {
            StClass cls = (StClass) objectSet.next();
            Vector v = cls.getVect();
            int[][] intArray = (int[][]) v.get(0);
            ArrayAssert.areEqual(INT_ARRAY_DATA[0], intArray[0]);
            ArrayAssert.areEqual(INT_ARRAY_DATA[1], intArray[1]);
            String [][] stringArray = (String[][]) v.get(1);
            ArrayAssert.areEqual(STRING_ARRAY_DATA[0], stringArray[0]);
            ArrayAssert.areEqual(STRING_ARRAY_DATA[1], stringArray[1]);
            objectContainer.delete(cls);
        }
    }

    protected String fileNamePrefix() {
        return "freespace";
    }

    protected void store(ExtObjectContainer objectContainer) {
        if(notApplicableForDb4oVersion()){
            return;
        }
        for( int i=0; i< 10; i++){
            StClass cls = new StClass();
            Vector v = new Vector(10);
            v.add(INT_ARRAY_DATA);
            v.add(STRING_ARRAY_DATA);
            cls.setId(i);
            cls.setVect(v);
            objectContainer.set(cls);
        }
    }


}
