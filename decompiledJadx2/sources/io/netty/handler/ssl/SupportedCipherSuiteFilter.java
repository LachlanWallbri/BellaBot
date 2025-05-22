package io.netty.handler.ssl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public final class SupportedCipherSuiteFilter implements CipherSuiteFilter {
    public static final SupportedCipherSuiteFilter INSTANCE = new SupportedCipherSuiteFilter();

    private SupportedCipherSuiteFilter() {
    }

    @Override // io.netty.handler.ssl.CipherSuiteFilter
    public String[] filterCipherSuites(Iterable<String> iterable, List<String> list, Set<String> set) {
        ArrayList arrayList;
        String next;
        if (list == null) {
            throw new NullPointerException("defaultCiphers");
        }
        if (set == null) {
            throw new NullPointerException("supportedCiphers");
        }
        if (iterable == null) {
            arrayList = new ArrayList(list.size());
            iterable = list;
        } else {
            arrayList = new ArrayList(set.size());
        }
        Iterator<String> it = iterable.iterator();
        while (it.hasNext() && (next = it.next()) != null) {
            if (set.contains(next)) {
                arrayList.add(next);
            }
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }
}
