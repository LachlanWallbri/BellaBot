package io.netty.handler.ssl;

import java.util.ArrayList;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
final class ApplicationProtocolUtil {
    private static final int DEFAULT_LIST_SIZE = 2;

    private ApplicationProtocolUtil() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static List<String> toList(Iterable<String> iterable) {
        return toList(2, iterable);
    }

    static List<String> toList(int i, Iterable<String> iterable) {
        if (iterable == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(i);
        for (String str : iterable) {
            if (str == null || str.isEmpty()) {
                throw new IllegalArgumentException("protocol cannot be null or empty");
            }
            arrayList.add(str);
        }
        if (arrayList.isEmpty()) {
            throw new IllegalArgumentException("protocols cannot empty");
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static List<String> toList(String... strArr) {
        return toList(2, strArr);
    }

    static List<String> toList(int i, String... strArr) {
        if (strArr == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(i);
        for (String str : strArr) {
            if (str == null || str.isEmpty()) {
                throw new IllegalArgumentException("protocol cannot be null or empty");
            }
            arrayList.add(str);
        }
        if (arrayList.isEmpty()) {
            throw new IllegalArgumentException("protocols cannot empty");
        }
        return arrayList;
    }
}
