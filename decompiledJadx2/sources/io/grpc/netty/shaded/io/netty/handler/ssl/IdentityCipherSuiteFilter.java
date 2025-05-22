package io.grpc.netty.shaded.io.netty.handler.ssl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class IdentityCipherSuiteFilter implements CipherSuiteFilter {
    public static final IdentityCipherSuiteFilter INSTANCE = new IdentityCipherSuiteFilter(true);
    public static final IdentityCipherSuiteFilter INSTANCE_DEFAULTING_TO_SUPPORTED_CIPHERS = new IdentityCipherSuiteFilter(false);
    private final boolean defaultToDefaultCiphers;

    private IdentityCipherSuiteFilter(boolean z) {
        this.defaultToDefaultCiphers = z;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.ssl.CipherSuiteFilter
    public String[] filterCipherSuites(Iterable<String> iterable, List<String> list, Set<String> set) {
        String next;
        if (iterable == null) {
            if (this.defaultToDefaultCiphers) {
                return (String[]) list.toArray(new String[0]);
            }
            return (String[]) set.toArray(new String[0]);
        }
        ArrayList arrayList = new ArrayList(set.size());
        Iterator<String> it = iterable.iterator();
        while (it.hasNext() && (next = it.next()) != null) {
            arrayList.add(next);
        }
        return (String[]) arrayList.toArray(new String[0]);
    }
}
