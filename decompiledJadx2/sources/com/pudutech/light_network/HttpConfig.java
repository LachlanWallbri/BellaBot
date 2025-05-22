package com.pudutech.light_network;

import kotlin.Metadata;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes5.dex
 */
/* compiled from: HttpConfig.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\u000b\"\u0004\b\f\u0010\r¨\u0006\u000e"}, m3961d2 = {"Lcom/pudutech/light_network/HttpConfig;", "", "()V", "connectTimeout", "", "getConnectTimeout", "()J", "setConnectTimeout", "(J)V", "isDebug", "", "()Z", "setDebug", "(Z)V", "lib_network_release"}, m3962k = 1, m3963mv = {1, 1, 15})
/* loaded from: classes.dex */
public final class HttpConfig {
    public static final HttpConfig INSTANCE = new HttpConfig();
    private static long connectTimeout = 60;
    private static boolean isDebug;

    private HttpConfig() {
    }

    public final long getConnectTimeout() {
        return connectTimeout;
    }

    public final void setConnectTimeout(long j) {
        connectTimeout = j;
    }

    public final boolean isDebug() {
        return isDebug;
    }

    public final void setDebug(boolean z) {
        isDebug = z;
    }
}
