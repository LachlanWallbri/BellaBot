package com.google.api.client.util;

import java.util.Collection;

/* JADX WARN: Classes with same name are omitted:
  
 */
@Deprecated
/* loaded from: classes2.dex */
public final class Collections2 {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T> Collection<T> cast(Iterable<T> iterable) {
        return (Collection) iterable;
    }

    private Collections2() {
    }
}
