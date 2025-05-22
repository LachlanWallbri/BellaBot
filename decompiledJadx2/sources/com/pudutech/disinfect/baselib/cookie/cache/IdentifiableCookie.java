package com.pudutech.disinfect.baselib.cookie.cache;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Cookie;

/* compiled from: IdentifiableCookie.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u0000 \f2\u00020\u0001:\u0001\fB\u000f\b\u0012\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0007\b\u0002¢\u0006\u0002\u0010\u0005J\u0013\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\u0006\u0010\t\u001a\u00020\u0003J\b\u0010\n\u001a\u00020\u000bH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082.¢\u0006\u0002\n\u0000¨\u0006\r"}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/cookie/cache/IdentifiableCookie;", "", "cookie", "Lokhttp3/Cookie;", "(Lokhttp3/Cookie;)V", "()V", "equals", "", "other", "getCookie", "hashCode", "", "Companion", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class IdentifiableCookie {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private Cookie cookie;

    @JvmStatic
    public static final ArrayList<IdentifiableCookie> decorateAll(Collection<Cookie> collection) {
        return INSTANCE.decorateAll(collection);
    }

    private IdentifiableCookie() {
    }

    public /* synthetic */ IdentifiableCookie(Cookie cookie, DefaultConstructorMarker defaultConstructorMarker) {
        this(cookie);
    }

    private IdentifiableCookie(Cookie cookie) {
        this();
        this.cookie = cookie;
    }

    public final Cookie getCookie() {
        Cookie cookie = this.cookie;
        if (cookie == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cookie");
        }
        return cookie;
    }

    /* compiled from: IdentifiableCookie.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J&\u0010\u0003\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u0004j\b\u0012\u0004\u0012\u00020\u0005`\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0007¨\u0006\n"}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/cookie/cache/IdentifiableCookie$Companion;", "", "()V", "decorateAll", "Ljava/util/ArrayList;", "Lcom/pudutech/disinfect/baselib/cookie/cache/IdentifiableCookie;", "Lkotlin/collections/ArrayList;", "cookies", "", "Lokhttp3/Cookie;", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes3.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public final ArrayList<IdentifiableCookie> decorateAll(Collection<Cookie> cookies) {
            Intrinsics.checkParameterIsNotNull(cookies, "cookies");
            ArrayList<IdentifiableCookie> arrayList = new ArrayList<>();
            Iterator<Cookie> it = cookies.iterator();
            while (it.hasNext()) {
                arrayList.add(new IdentifiableCookie(it.next(), null));
            }
            return arrayList;
        }
    }

    public boolean equals(Object other) {
        if (!(other instanceof IdentifiableCookie)) {
            return false;
        }
        IdentifiableCookie identifiableCookie = (IdentifiableCookie) other;
        Cookie cookie = identifiableCookie.cookie;
        if (cookie == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cookie");
        }
        String name = cookie.name();
        Cookie cookie2 = this.cookie;
        if (cookie2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cookie");
        }
        if (!name.equals(cookie2.name())) {
            return false;
        }
        Cookie cookie3 = identifiableCookie.cookie;
        if (cookie3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cookie");
        }
        String domain = cookie3.domain();
        Cookie cookie4 = this.cookie;
        if (cookie4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cookie");
        }
        if (!domain.equals(cookie4.domain())) {
            return false;
        }
        Cookie cookie5 = identifiableCookie.cookie;
        if (cookie5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cookie");
        }
        String path = cookie5.path();
        Cookie cookie6 = this.cookie;
        if (cookie6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cookie");
        }
        if (!path.equals(cookie6.path())) {
            return false;
        }
        Cookie cookie7 = identifiableCookie.cookie;
        if (cookie7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cookie");
        }
        Boolean valueOf = Boolean.valueOf(cookie7.secure());
        Cookie cookie8 = this.cookie;
        if (cookie8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cookie");
        }
        if (!valueOf.equals(Boolean.valueOf(cookie8.secure()))) {
            return false;
        }
        Cookie cookie9 = identifiableCookie.cookie;
        if (cookie9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cookie");
        }
        Boolean valueOf2 = Boolean.valueOf(cookie9.hostOnly());
        Cookie cookie10 = this.cookie;
        if (cookie10 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cookie");
        }
        return valueOf2.equals(Boolean.valueOf(cookie10.hostOnly()));
    }

    public int hashCode() {
        Cookie cookie = this.cookie;
        if (cookie == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cookie");
        }
        int hashCode = (527 + cookie.name().hashCode()) * 31;
        Cookie cookie2 = this.cookie;
        if (cookie2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cookie");
        }
        int hashCode2 = (hashCode + cookie2.name().hashCode()) * 31;
        Cookie cookie3 = this.cookie;
        if (cookie3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cookie");
        }
        int hashCode3 = (hashCode2 + cookie3.path().hashCode()) * 31;
        Cookie cookie4 = this.cookie;
        if (cookie4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cookie");
        }
        int hashCode4 = (hashCode3 + Boolean.hashCode(cookie4.secure())) * 31;
        Cookie cookie5 = this.cookie;
        if (cookie5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cookie");
        }
        return hashCode4 + (!cookie5.hostOnly() ? 1 : 0);
    }
}
