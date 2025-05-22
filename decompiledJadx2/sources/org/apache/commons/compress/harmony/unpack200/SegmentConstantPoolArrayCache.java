package org.apache.commons.compress.harmony.unpack200;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.List;

/* loaded from: classes9.dex */
public class SegmentConstantPoolArrayCache {
    protected IdentityHashMap knownArrays = new IdentityHashMap(1000);
    protected String[] lastArray;
    protected List lastIndexes;
    protected String lastKey;

    public List indexesForArrayKey(String[] strArr, String str) {
        if (!arrayIsCached(strArr)) {
            cacheArray(strArr);
        }
        if (this.lastArray == strArr && this.lastKey == str) {
            return this.lastIndexes;
        }
        this.lastArray = strArr;
        this.lastKey = str;
        this.lastIndexes = ((CachedArray) this.knownArrays.get(strArr)).indexesForKey(str);
        return this.lastIndexes;
    }

    protected boolean arrayIsCached(String[] strArr) {
        return this.knownArrays.containsKey(strArr) && ((CachedArray) this.knownArrays.get(strArr)).lastKnownSize() == strArr.length;
    }

    protected void cacheArray(String[] strArr) {
        if (arrayIsCached(strArr)) {
            throw new IllegalArgumentException("Trying to cache an array that already exists");
        }
        this.knownArrays.put(strArr, new CachedArray(strArr));
        this.lastArray = null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes9.dex */
    public class CachedArray {
        int lastKnownSize;
        String[] primaryArray;
        HashMap primaryTable;

        public CachedArray(String[] strArr) {
            this.primaryArray = strArr;
            this.lastKnownSize = strArr.length;
            this.primaryTable = new HashMap(this.lastKnownSize);
            cacheIndexes();
        }

        public int lastKnownSize() {
            return this.lastKnownSize;
        }

        public List indexesForKey(String str) {
            if (!this.primaryTable.containsKey(str)) {
                return Collections.EMPTY_LIST;
            }
            return (List) this.primaryTable.get(str);
        }

        protected void cacheIndexes() {
            int i = 0;
            while (true) {
                String[] strArr = this.primaryArray;
                if (i >= strArr.length) {
                    return;
                }
                String str = strArr[i];
                if (!this.primaryTable.containsKey(str)) {
                    this.primaryTable.put(str, new ArrayList());
                }
                ((ArrayList) this.primaryTable.get(str)).add(Integer.valueOf(i));
                i++;
            }
        }
    }
}
