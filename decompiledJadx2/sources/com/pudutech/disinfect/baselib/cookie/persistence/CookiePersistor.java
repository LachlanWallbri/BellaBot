package com.pudutech.disinfect.baselib.cookie.persistence;

import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import okhttp3.Cookie;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* compiled from: CookiePersistor.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u001e\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\u000e\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H&J\u0016\u0010\u0007\u001a\u00020\u00032\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00060\tH&J\u0016\u0010\n\u001a\u00020\u00032\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00060\tH&¨\u0006\u000b"}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/cookie/persistence/CookiePersistor;", "", "clear", "", "loadAll", "", "Lokhttp3/Cookie;", "removeAll", "cookies", "", "saveAll", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public interface CookiePersistor {
    void clear();

    List<Cookie> loadAll();

    void removeAll(Collection<Cookie> cookies);

    void saveAll(Collection<Cookie> cookies);
}
