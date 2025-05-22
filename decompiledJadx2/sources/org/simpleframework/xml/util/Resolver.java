package org.simpleframework.xml.util;

import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.simpleframework.xml.util.Match;

/* loaded from: classes9.dex */
public class Resolver<M extends Match> extends AbstractSet<M> {
    protected final Resolver<M>.Stack stack = new Stack();
    protected final Resolver<M>.Cache cache = new Cache();

    public M resolve(String str) {
        List<M> list = (List) this.cache.get(str);
        if (list == null) {
            list = resolveAll(str);
        }
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    public List<M> resolveAll(String str) {
        List<M> list = (List) this.cache.get(str);
        if (list != null) {
            return list;
        }
        char[] charArray = str.toCharArray();
        if (charArray == null) {
            return null;
        }
        return resolveAll(str, charArray);
    }

    private List<M> resolveAll(String str, char[] cArr) {
        ArrayList arrayList = new ArrayList();
        Iterator it = this.stack.iterator();
        while (it.hasNext()) {
            Match match = (Match) it.next();
            if (match(cArr, match.getPattern().toCharArray())) {
                this.cache.put(str, arrayList);
                arrayList.add(match);
            }
        }
        return arrayList;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean add(M m) {
        this.stack.push((Resolver<M>.Stack) m);
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public Iterator<M> iterator() {
        return (Iterator<M>) this.stack.sequence();
    }

    public boolean remove(M m) {
        this.cache.clear();
        return this.stack.remove(m);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public int size() {
        return this.stack.size();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public void clear() {
        this.cache.clear();
        this.stack.clear();
    }

    private boolean match(char[] cArr, char[] cArr2) {
        return match(cArr, 0, cArr2, 0);
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0017, code lost:
    
        if (r9 < r8.length) goto L61;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x0019, code lost:
    
        return true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x001c, code lost:
    
        if (r8[r9] != '?') goto L66;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x001e, code lost:
    
        r9 = r9 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0021, code lost:
    
        if (r9 < r8.length) goto L66;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0023, code lost:
    
        return true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0025, code lost:
    
        if (r7 >= r6.length) goto L62;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x002b, code lost:
    
        if (r6[r7] == r8[r9]) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x002f, code lost:
    
        if (r8[r9] != '?') goto L64;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x003e, code lost:
    
        r7 = r7 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0035, code lost:
    
        if (r8[r9 - 1] == '?') goto L63;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x003b, code lost:
    
        if (match(r6, r7, r8, r9) == false) goto L65;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x003d, code lost:
    
        return true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x0042, code lost:
    
        if (r6.length != r7) goto L33;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x0044, code lost:
    
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x0045, code lost:
    
        r0 = r7 + 1;
        r1 = r9 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x004d, code lost:
    
        if (r6[r7] == r8[r9]) goto L58;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x0053, code lost:
    
        if (r8[r1 - 1] == '?') goto L59;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x0055, code lost:
    
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x0056, code lost:
    
        r7 = r0;
        r9 = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x000e, code lost:
    
        if (r8[r9] == '*') goto L8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0012, code lost:
    
        if (r8[r9] != '*') goto L60;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0014, code lost:
    
        r9 = r9 + 1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private boolean match(char[] cArr, int i, char[] cArr2, int i2) {
        while (i2 < cArr2.length && i < cArr.length) {
        }
        if (cArr2.length == i2) {
            return cArr.length == i;
        }
        while (cArr2[i2] == '*') {
            i2++;
            if (i2 >= cArr2.length) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public class Cache extends LimitedCache<List<M>> {
        public Cache() {
            super(1024);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public class Stack extends LinkedList<M> {
        private Stack() {
        }

        @Override // java.util.LinkedList, java.util.Deque
        public void push(M m) {
            Resolver.this.cache.clear();
            addFirst(m);
        }

        public void purge(int i) {
            Resolver.this.cache.clear();
            remove(i);
        }

        public Iterator<M> sequence() {
            return new Sequence();
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes9.dex */
        public class Sequence implements Iterator<M> {
            private int cursor;

            public Sequence() {
                this.cursor = Stack.this.size();
            }

            @Override // java.util.Iterator
            public M next() {
                if (!hasNext()) {
                    return null;
                }
                Stack stack = Stack.this;
                int i = this.cursor - 1;
                this.cursor = i;
                return (M) stack.get(i);
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                return this.cursor > 0;
            }

            @Override // java.util.Iterator
            public void remove() {
                Stack.this.purge(this.cursor);
            }
        }
    }
}
