package org.jboss.netty.util;

/* loaded from: classes7.dex */
public final class ExternalResourceUtil {
    public static void release(ExternalResourceReleasable... externalResourceReleasableArr) {
        ExternalResourceReleasable[] externalResourceReleasableArr2 = new ExternalResourceReleasable[externalResourceReleasableArr.length];
        for (int i = 0; i < externalResourceReleasableArr.length; i++) {
            if (externalResourceReleasableArr[i] == null) {
                throw new NullPointerException("releasables[" + i + "]");
            }
            externalResourceReleasableArr2[i] = externalResourceReleasableArr[i];
        }
        for (ExternalResourceReleasable externalResourceReleasable : externalResourceReleasableArr2) {
            externalResourceReleasable.releaseExternalResources();
        }
    }

    private ExternalResourceUtil() {
    }
}
