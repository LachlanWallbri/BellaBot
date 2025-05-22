package me.jessyan.retrofiturlmanager;

import okhttp3.HttpUrl;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* loaded from: classes2.dex */
class Utils {
    private Utils() {
        throw new IllegalStateException("do not instantiation me");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static HttpUrl checkUrl(String str) {
        HttpUrl parse = HttpUrl.parse(str);
        if (parse != null) {
            return parse;
        }
        throw new InvalidUrlException(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T> T checkNotNull(T t, String str) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(str);
    }
}
