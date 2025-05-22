package org.apache.commons.compress.harmony.unpack200.bytecode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import org.apache.commons.compress.harmony.unpack200.Segment;

/* loaded from: classes9.dex */
public class ClassConstantPool {
    protected Map indexCache;
    private boolean resolved;
    protected HashSet entriesContainsSet = new HashSet();
    protected HashSet othersContainsSet = new HashSet();
    private final HashSet mustStartClassPool = new HashSet();
    private final List others = new ArrayList(500);
    private final List entries = new ArrayList(500);

    public ClassFileEntry add(ClassFileEntry classFileEntry) {
        if (classFileEntry instanceof ByteCode) {
            return null;
        }
        if (classFileEntry instanceof ConstantPoolEntry) {
            if (this.entriesContainsSet.add(classFileEntry)) {
                this.entries.add(classFileEntry);
            }
        } else if (this.othersContainsSet.add(classFileEntry)) {
            this.others.add(classFileEntry);
        }
        return classFileEntry;
    }

    public void addNestedEntries() {
        ArrayList arrayList = new ArrayList(512);
        ArrayList arrayList2 = new ArrayList(512);
        arrayList.addAll(this.entries);
        arrayList.addAll(this.others);
        boolean z = true;
        while (true) {
            if (!z && arrayList.size() <= 0) {
                return;
            }
            arrayList2.clear();
            int size = this.entries.size();
            int size2 = this.others.size();
            for (int i = 0; i < arrayList.size(); i++) {
                ClassFileEntry classFileEntry = (ClassFileEntry) arrayList.get(i);
                ClassFileEntry[] nestedClassFileEntries = classFileEntry.getNestedClassFileEntries();
                arrayList2.addAll(Arrays.asList(nestedClassFileEntries));
                if ((classFileEntry instanceof ByteCode) && ((ByteCode) classFileEntry).nestedMustStartClassPool()) {
                    this.mustStartClassPool.addAll(Arrays.asList(nestedClassFileEntries));
                }
                add(classFileEntry);
            }
            z = (this.entries.size() == size && this.others.size() == size2) ? false : true;
            arrayList.clear();
            arrayList.addAll(arrayList2);
        }
    }

    public int indexOf(ClassFileEntry classFileEntry) {
        if (!this.resolved) {
            throw new IllegalStateException("Constant pool is not yet resolved; this does not make any sense");
        }
        Map map = this.indexCache;
        if (map == null) {
            throw new IllegalStateException("Index cache is not initialized!");
        }
        Integer num = (Integer) map.get(classFileEntry);
        if (num != null) {
            return num.intValue() + 1;
        }
        return -1;
    }

    public int size() {
        return this.entries.size();
    }

    public ClassFileEntry get(int i) {
        if (!this.resolved) {
            throw new IllegalStateException("Constant pool is not yet resolved; this does not make any sense");
        }
        return (ClassFileEntry) this.entries.get(i - 1);
    }

    public void resolve(Segment segment) {
        initialSort();
        sortClassPool();
        this.resolved = true;
        for (int i = 0; i < this.entries.size(); i++) {
            ((ClassFileEntry) this.entries.get(i)).resolve(this);
        }
        for (int i2 = 0; i2 < this.others.size(); i2++) {
            ((ClassFileEntry) this.others.get(i2)).resolve(this);
        }
    }

    private void initialSort() {
        TreeSet treeSet = new TreeSet(new Comparator() { // from class: org.apache.commons.compress.harmony.unpack200.bytecode.-$$Lambda$ClassConstantPool$h6_ZuWj6PGIr2ZBxXpgnFxEYOhw
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return ClassConstantPool.lambda$initialSort$0(obj, obj2);
            }
        });
        TreeSet treeSet2 = new TreeSet(new Comparator() { // from class: org.apache.commons.compress.harmony.unpack200.bytecode.-$$Lambda$ClassConstantPool$Jv9HJAfGorYU2zofZ1-bUm6Z3xU
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                int compareTo;
                compareTo = ((CPUTF8) obj).underlyingString().compareTo(((CPUTF8) obj2).underlyingString());
                return compareTo;
            }
        });
        TreeSet treeSet3 = new TreeSet(new Comparator() { // from class: org.apache.commons.compress.harmony.unpack200.bytecode.-$$Lambda$ClassConstantPool$PyMVlrmr90NKRCbkD6HEl3zPuQY
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                int compareTo;
                compareTo = ((CPClass) obj).getName().compareTo(((CPClass) obj2).getName());
                return compareTo;
            }
        });
        for (int i = 0; i < this.entries.size(); i++) {
            ConstantPoolEntry constantPoolEntry = (ConstantPoolEntry) this.entries.get(i);
            if (constantPoolEntry.getGlobalIndex() == -1) {
                if (constantPoolEntry instanceof CPUTF8) {
                    treeSet2.add(constantPoolEntry);
                } else if (constantPoolEntry instanceof CPClass) {
                    treeSet3.add(constantPoolEntry);
                } else {
                    throw new Error("error");
                }
            } else {
                treeSet.add(constantPoolEntry);
            }
        }
        this.entries.clear();
        this.entries.addAll(treeSet);
        this.entries.addAll(treeSet2);
        this.entries.addAll(treeSet3);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ int lambda$initialSort$0(Object obj, Object obj2) {
        return ((ConstantPoolEntry) obj).getGlobalIndex() - ((ConstantPoolEntry) obj2).getGlobalIndex();
    }

    public List entries() {
        return Collections.unmodifiableList(this.entries);
    }

    protected void sortClassPool() {
        ArrayList arrayList = new ArrayList(this.entries.size());
        ArrayList arrayList2 = new ArrayList(this.entries.size());
        for (int i = 0; i < this.entries.size(); i++) {
            ClassFileEntry classFileEntry = (ClassFileEntry) this.entries.get(i);
            if (this.mustStartClassPool.contains(classFileEntry)) {
                arrayList.add(classFileEntry);
            } else {
                arrayList2.add(classFileEntry);
            }
        }
        this.indexCache = new HashMap(this.entries.size());
        this.entries.clear();
        int i2 = 0;
        for (int i3 = 0; i3 < arrayList.size(); i3++) {
            ClassFileEntry classFileEntry2 = (ClassFileEntry) arrayList.get(i3);
            this.indexCache.put(classFileEntry2, Integer.valueOf(i2));
            if ((classFileEntry2 instanceof CPLong) || (classFileEntry2 instanceof CPDouble)) {
                this.entries.add(classFileEntry2);
                this.entries.add(classFileEntry2);
                i2 += 2;
            } else {
                this.entries.add(classFileEntry2);
                i2++;
            }
        }
        for (int i4 = 0; i4 < arrayList2.size(); i4++) {
            ClassFileEntry classFileEntry3 = (ClassFileEntry) arrayList2.get(i4);
            this.indexCache.put(classFileEntry3, Integer.valueOf(i2));
            if ((classFileEntry3 instanceof CPLong) || (classFileEntry3 instanceof CPDouble)) {
                this.entries.add(classFileEntry3);
                this.entries.add(classFileEntry3);
                i2 += 2;
            } else {
                this.entries.add(classFileEntry3);
                i2++;
            }
        }
    }

    public ClassFileEntry addWithNestedEntries(ClassFileEntry classFileEntry) {
        add(classFileEntry);
        for (ClassFileEntry classFileEntry2 : classFileEntry.getNestedClassFileEntries()) {
            addWithNestedEntries(classFileEntry2);
        }
        return classFileEntry;
    }
}
