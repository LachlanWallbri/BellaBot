package org.mozilla.javascript.commonjs.module.provider;

import java.net.URLConnection;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes2.dex */
public interface UrlConnectionExpiryCalculator {
    long calculateExpiry(URLConnection uRLConnection);
}
