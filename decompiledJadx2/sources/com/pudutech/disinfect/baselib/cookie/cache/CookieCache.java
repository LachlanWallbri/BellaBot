package com.pudutech.disinfect.baselib.cookie.cache;

import java.util.Collection;
import kotlin.Metadata;
import kotlin.jvm.internal.markers.KMappedMarker;
import okhttp3.Cookie;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* compiled from: CookieCache.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\b\u0002\bf\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u0016\u0010\u0003\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00020\u0006H&J\b\u0010\u0007\u001a\u00020\u0004H&¨\u0006\b"}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/cookie/cache/CookieCache;", "", "Lokhttp3/Cookie;", "addAll", "", "cookies", "", "clear", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public interface CookieCache extends Iterable<Cookie>, KMappedMarker {
    void addAll(Collection<Cookie> cookies);

    void clear();
}
