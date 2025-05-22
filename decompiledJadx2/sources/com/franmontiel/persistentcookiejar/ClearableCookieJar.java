package com.franmontiel.persistentcookiejar;

import okhttp3.CookieJar;

/* JADX WARN: Classes with same name are omitted:
  classes.dex
 */
/* loaded from: classes2.dex */
public interface ClearableCookieJar extends CookieJar {
    void clear();

    void clearSession();
}
