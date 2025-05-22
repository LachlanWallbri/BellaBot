package io.netty.handler.codec.http.cookie;

import io.netty.handler.codec.DateFormatter;
import io.netty.util.internal.ObjectUtil;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public final class ServerCookieEncoder extends CookieEncoder {
    public static final ServerCookieEncoder STRICT = new ServerCookieEncoder(true);
    public static final ServerCookieEncoder LAX = new ServerCookieEncoder(false);

    private ServerCookieEncoder(boolean z) {
        super(z);
    }

    public String encode(String str, String str2) {
        return encode(new DefaultCookie(str, str2));
    }

    public String encode(Cookie cookie) {
        String name = ((Cookie) ObjectUtil.checkNotNull(cookie, "cookie")).name();
        String value = cookie.value() != null ? cookie.value() : "";
        validateCookie(name, value);
        StringBuilder stringBuilder = CookieUtil.stringBuilder();
        if (cookie.wrap()) {
            CookieUtil.addQuoted(stringBuilder, name, value);
        } else {
            CookieUtil.add(stringBuilder, name, value);
        }
        if (cookie.maxAge() != Long.MIN_VALUE) {
            CookieUtil.add(stringBuilder, "Max-Age", cookie.maxAge());
            Date date = new Date((cookie.maxAge() * 1000) + System.currentTimeMillis());
            stringBuilder.append("Expires");
            stringBuilder.append('=');
            DateFormatter.append(date, stringBuilder);
            stringBuilder.append(';');
            stringBuilder.append(' ');
        }
        if (cookie.path() != null) {
            CookieUtil.add(stringBuilder, "Path", cookie.path());
        }
        if (cookie.domain() != null) {
            CookieUtil.add(stringBuilder, "Domain", cookie.domain());
        }
        if (cookie.isSecure()) {
            CookieUtil.add(stringBuilder, "Secure");
        }
        if (cookie.isHttpOnly()) {
            CookieUtil.add(stringBuilder, "HTTPOnly");
        }
        return CookieUtil.stripTrailingSeparator(stringBuilder);
    }

    private static List<String> dedup(List<String> list, Map<String, Integer> map) {
        boolean[] zArr = new boolean[list.size()];
        Iterator<Integer> it = map.values().iterator();
        while (it.hasNext()) {
            zArr[it.next().intValue()] = true;
        }
        ArrayList arrayList = new ArrayList(map.size());
        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (zArr[i]) {
                arrayList.add(list.get(i));
            }
        }
        return arrayList;
    }

    public List<String> encode(Cookie... cookieArr) {
        if (((Cookie[]) ObjectUtil.checkNotNull(cookieArr, "cookies")).length == 0) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList(cookieArr.length);
        HashMap hashMap = (!this.strict || cookieArr.length <= 1) ? null : new HashMap();
        boolean z = false;
        for (int i = 0; i < cookieArr.length; i++) {
            Cookie cookie = cookieArr[i];
            arrayList.add(encode(cookie));
            if (hashMap != null) {
                z |= hashMap.put(cookie.name(), Integer.valueOf(i)) != null;
            }
        }
        return z ? dedup(arrayList, hashMap) : arrayList;
    }

    public List<String> encode(Collection<? extends Cookie> collection) {
        if (((Collection) ObjectUtil.checkNotNull(collection, "cookies")).isEmpty()) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList(collection.size());
        HashMap hashMap = (!this.strict || collection.size() <= 1) ? null : new HashMap();
        boolean z = false;
        int i = 0;
        for (Cookie cookie : collection) {
            arrayList.add(encode(cookie));
            if (hashMap != null) {
                int i2 = i + 1;
                z |= hashMap.put(cookie.name(), Integer.valueOf(i)) != null;
                i = i2;
            }
        }
        return z ? dedup(arrayList, hashMap) : arrayList;
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0059  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0081  */
    /* JADX WARN: Removed duplicated region for block: B:34:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public List<String> encode(Iterable<? extends Cookie> iterable) {
        int i;
        boolean z;
        Iterator it = ((Iterable) ObjectUtil.checkNotNull(iterable, "cookies")).iterator();
        if (!it.hasNext()) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        Cookie cookie = (Cookie) it.next();
        HashMap hashMap = (this.strict && it.hasNext()) ? new HashMap() : null;
        arrayList.add(encode(cookie));
        if (hashMap == null) {
            i = 0;
        } else {
            if (hashMap.put(cookie.name(), 0) != null) {
                i = 1;
                z = true;
                while (it.hasNext()) {
                    Cookie cookie2 = (Cookie) it.next();
                    arrayList.add(encode(cookie2));
                    if (hashMap != null) {
                        int i2 = i + 1;
                        z = (hashMap.put(cookie2.name(), Integer.valueOf(i)) != null) | z;
                        i = i2;
                    }
                }
                return !z ? dedup(arrayList, hashMap) : arrayList;
            }
            i = 1;
        }
        z = false;
        while (it.hasNext()) {
        }
        if (!z) {
        }
    }
}
