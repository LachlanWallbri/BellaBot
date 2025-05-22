package com.pudutech.disinfect.baselib.cookie;

import com.pudutech.disinfect.baselib.cookie.cache.CookieCache;
import com.pudutech.disinfect.baselib.cookie.persistence.CookiePersistor;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import okhttp3.Cookie;
import okhttp3.HttpUrl;

/* compiled from: PersistentCookieJar.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u00112\u00020\u0001:\u0001\u0011B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u0007\u001a\u00020\bH\u0016J\b\u0010\t\u001a\u00020\bH\u0016J\u0016\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u001e\u0010\u000f\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\u000e2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH\u0016R\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0012"}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/cookie/PersistentCookieJar;", "Lcom/pudutech/disinfect/baselib/cookie/ClearableCookieJar;", "cache", "Lcom/pudutech/disinfect/baselib/cookie/cache/CookieCache;", "persistor", "Lcom/pudutech/disinfect/baselib/cookie/persistence/CookiePersistor;", "(Lcom/pudutech/disinfect/baselib/cookie/cache/CookieCache;Lcom/pudutech/disinfect/baselib/cookie/persistence/CookiePersistor;)V", "clear", "", "clearSession", "loadForRequest", "", "Lokhttp3/Cookie;", "url", "Lokhttp3/HttpUrl;", "saveFromResponse", "cookies", "Companion", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class PersistentCookieJar implements ClearableCookieJar {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private CookieCache cache;
    private CookiePersistor persistor;

    @JvmStatic
    private static final List<Cookie> filterPersistentCookies(List<Cookie> list) {
        return INSTANCE.filterPersistentCookies(list);
    }

    @JvmStatic
    private static final boolean isCookieExpired(Cookie cookie) {
        return INSTANCE.isCookieExpired(cookie);
    }

    @Override // com.pudutech.disinfect.baselib.cookie.ClearableCookieJar
    public void clear() {
    }

    public PersistentCookieJar(CookieCache cache, CookiePersistor persistor) {
        Intrinsics.checkParameterIsNotNull(cache, "cache");
        Intrinsics.checkParameterIsNotNull(persistor, "persistor");
        this.cache = cache;
        this.persistor = persistor;
        CookieCache cookieCache = this.cache;
        if (cookieCache != null) {
            cookieCache.addAll(persistor.loadAll());
        }
    }

    @Override // okhttp3.CookieJar
    public synchronized void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
        Intrinsics.checkParameterIsNotNull(url, "url");
        Intrinsics.checkParameterIsNotNull(cookies, "cookies");
        CookieCache cookieCache = this.cache;
        if (cookieCache != null) {
            cookieCache.addAll(cookies);
        }
        CookiePersistor cookiePersistor = this.persistor;
        if (cookiePersistor != null) {
            cookiePersistor.saveAll(INSTANCE.filterPersistentCookies(cookies));
        }
    }

    @Override // okhttp3.CookieJar
    public List<Cookie> loadForRequest(HttpUrl url) {
        Intrinsics.checkParameterIsNotNull(url, "url");
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        CookieCache cookieCache = this.cache;
        if (cookieCache != null) {
            Iterator<Cookie> it = cookieCache != null ? cookieCache.iterator() : null;
            if (it == null) {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.MutableIterator<okhttp3.Cookie>");
            }
            Iterator asMutableIterator = TypeIntrinsics.asMutableIterator(it);
            if (asMutableIterator != null) {
                while (asMutableIterator.hasNext()) {
                    Cookie cookie = (Cookie) asMutableIterator.next();
                    if (INSTANCE.isCookieExpired(cookie)) {
                        arrayList.add(cookie);
                        if (asMutableIterator != null) {
                            asMutableIterator.remove();
                        }
                    } else if (cookie.matches(url)) {
                        arrayList2.add(cookie);
                    }
                }
            }
        }
        CookiePersistor cookiePersistor = this.persistor;
        if (cookiePersistor != null) {
            cookiePersistor.removeAll(arrayList);
        }
        return arrayList2;
    }

    @Override // com.pudutech.disinfect.baselib.cookie.ClearableCookieJar
    public synchronized void clearSession() {
        List<Cookie> loadAll;
        CookieCache cookieCache;
        CookieCache cookieCache2 = this.cache;
        if (cookieCache2 != null) {
            cookieCache2.clear();
        }
        CookiePersistor cookiePersistor = this.persistor;
        if (cookiePersistor != null && (loadAll = cookiePersistor.loadAll()) != null && (cookieCache = this.cache) != null) {
            cookieCache.addAll(loadAll);
        }
    }

    /* compiled from: PersistentCookieJar.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001c\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0003J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0005H\u0003¨\u0006\n"}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/cookie/PersistentCookieJar$Companion;", "", "()V", "filterPersistentCookies", "", "Lokhttp3/Cookie;", "cookies", "isCookieExpired", "", "cookie", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes3.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: private */
        @JvmStatic
        public final List<Cookie> filterPersistentCookies(List<Cookie> cookies) {
            ArrayList arrayList = new ArrayList();
            for (Cookie cookie : cookies) {
                if (cookie.persistent()) {
                    arrayList.add(cookie);
                }
            }
            return arrayList;
        }

        /* JADX INFO: Access modifiers changed from: private */
        @JvmStatic
        public final boolean isCookieExpired(Cookie cookie) {
            return cookie.expiresAt() < System.currentTimeMillis();
        }
    }
}
