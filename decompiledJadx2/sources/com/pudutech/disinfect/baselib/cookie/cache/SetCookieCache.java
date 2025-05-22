package com.pudutech.disinfect.baselib.cookie.cache;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlin.jvm.internal.markers.KMutableIterator;
import okhttp3.Cookie;
import org.mozilla.javascript.ES6Iterator;

/* compiled from: SetCookieCache.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010(\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\u000eB\u0007\b\u0012¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0007\u001a\u00020\b2\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\n0\tH\u0016J\b\u0010\u000b\u001a\u00020\bH\u0016J\u000f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\n0\rH\u0096\u0002R\u001e\u0010\u0003\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u0004j\b\u0012\u0004\u0012\u00020\u0005`\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000f"}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/cookie/cache/SetCookieCache;", "Lcom/pudutech/disinfect/baselib/cookie/cache/CookieCache;", "()V", "cookies", "Ljava/util/HashSet;", "Lcom/pudutech/disinfect/baselib/cookie/cache/IdentifiableCookie;", "Lkotlin/collections/HashSet;", "addAll", "", "", "Lokhttp3/Cookie;", "clear", "iterator", "", "SetCookieCacheIterator", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class SetCookieCache implements CookieCache {
    private HashSet<IdentifiableCookie> cookies = new HashSet<>();

    private SetCookieCache() {
    }

    @Override // com.pudutech.disinfect.baselib.cookie.cache.CookieCache
    public void clear() {
        this.cookies.clear();
    }

    @Override // java.lang.Iterable
    public Iterator<Cookie> iterator() {
        return new SetCookieCacheIterator();
    }

    @Override // com.pudutech.disinfect.baselib.cookie.cache.CookieCache
    public void addAll(Collection<Cookie> cookies) {
        Intrinsics.checkParameterIsNotNull(cookies, "cookies");
        Iterator<IdentifiableCookie> it = IdentifiableCookie.INSTANCE.decorateAll(cookies).iterator();
        while (it.hasNext()) {
            IdentifiableCookie next = it.next();
            this.cookies.remove(next);
            this.cookies.add(next);
        }
    }

    /* compiled from: SetCookieCache.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010)\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\b\u0086\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\t\u0010\u0006\u001a\u00020\u0007H\u0096\u0002J\t\u0010\b\u001a\u00020\u0002H\u0096\u0002J\b\u0010\t\u001a\u00020\nH\u0016R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00050\u0001X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000b"}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/cookie/cache/SetCookieCache$SetCookieCacheIterator;", "", "Lokhttp3/Cookie;", "(Lcom/pudutech/disinfect/baselib/cookie/cache/SetCookieCache;)V", "iterator", "Lcom/pudutech/disinfect/baselib/cookie/cache/IdentifiableCookie;", "hasNext", "", ES6Iterator.NEXT_METHOD, "remove", "", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes3.dex */
    public final class SetCookieCacheIterator implements Iterator<Cookie>, KMutableIterator {
        private Iterator<IdentifiableCookie> iterator;

        public SetCookieCacheIterator() {
            Iterator it = SetCookieCache.this.cookies.iterator();
            if (it == null) {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.MutableIterator<com.pudutech.disinfect.baselib.cookie.cache.IdentifiableCookie>");
            }
            this.iterator = TypeIntrinsics.asMutableIterator(it);
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.iterator.hasNext();
        }

        @Override // java.util.Iterator
        public Cookie next() {
            return this.iterator.next().getCookie();
        }

        @Override // java.util.Iterator
        public void remove() {
            this.iterator.remove();
        }
    }
}
