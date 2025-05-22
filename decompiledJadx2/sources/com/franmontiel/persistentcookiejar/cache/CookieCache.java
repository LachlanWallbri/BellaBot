package com.franmontiel.persistentcookiejar.cache;

import java.util.Collection;
import okhttp3.Cookie;

/* JADX WARN: Classes with same name are omitted:
  classes.dex
 */
/* loaded from: classes2.dex */
public interface CookieCache extends Iterable<Cookie> {
    void addAll(Collection<Cookie> collection);

    void clear();
}
