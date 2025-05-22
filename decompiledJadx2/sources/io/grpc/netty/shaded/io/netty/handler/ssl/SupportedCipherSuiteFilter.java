package io.grpc.netty.shaded.io.netty.handler.ssl;

import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class SupportedCipherSuiteFilter implements CipherSuiteFilter {
    public static final SupportedCipherSuiteFilter INSTANCE = new SupportedCipherSuiteFilter();

    private SupportedCipherSuiteFilter() {
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.ssl.CipherSuiteFilter
    public String[] filterCipherSuites(Iterable<String> iterable, List<String> list, Set<String> set) {
        ArrayList arrayList;
        String next;
        ObjectUtil.checkNotNull(list, "defaultCiphers");
        ObjectUtil.checkNotNull(set, "supportedCiphers");
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
        return (String[]) arrayList.toArray(new String[0]);
    }
}
