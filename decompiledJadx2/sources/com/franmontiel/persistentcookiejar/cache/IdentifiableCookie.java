package com.franmontiel.persistentcookiejar.cache;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import okhttp3.Cookie;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes.dex
 */
/* loaded from: classes2.dex */
public class IdentifiableCookie {
    private Cookie cookie;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static List<IdentifiableCookie> decorateAll(Collection<Cookie> collection) {
        ArrayList arrayList = new ArrayList(collection.size());
        Iterator<Cookie> it = collection.iterator();
        while (it.hasNext()) {
            arrayList.add(new IdentifiableCookie(it.next()));
        }
        return arrayList;
    }

    IdentifiableCookie(Cookie cookie) {
        this.cookie = cookie;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Cookie getCookie() {
        return this.cookie;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof IdentifiableCookie)) {
            return false;
        }
        IdentifiableCookie identifiableCookie = (IdentifiableCookie) obj;
        return identifiableCookie.cookie.name().equals(this.cookie.name()) && identifiableCookie.cookie.domain().equals(this.cookie.domain()) && identifiableCookie.cookie.path().equals(this.cookie.path()) && identifiableCookie.cookie.secure() == this.cookie.secure() && identifiableCookie.cookie.hostOnly() == this.cookie.hostOnly();
    }

    public int hashCode() {
        return ((((((((527 + this.cookie.name().hashCode()) * 31) + this.cookie.domain().hashCode()) * 31) + this.cookie.path().hashCode()) * 31) + (!this.cookie.secure() ? 1 : 0)) * 31) + (!this.cookie.hostOnly() ? 1 : 0);
    }
}
