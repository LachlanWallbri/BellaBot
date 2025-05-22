package org.jboss.netty.util.internal;

import java.io.Serializable;
import java.util.Comparator;

/* loaded from: classes7.dex */
public final class CaseIgnoringComparator implements Comparator<String>, Serializable {
    public static final CaseIgnoringComparator INSTANCE = new CaseIgnoringComparator();
    private static final long serialVersionUID = 4582133183775373862L;

    private CaseIgnoringComparator() {
    }

    @Override // java.util.Comparator
    public int compare(String str, String str2) {
        return str.compareToIgnoreCase(str2);
    }

    private Object readResolve() {
        return INSTANCE;
    }
}
