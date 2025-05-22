package com.pudutech.disinfect.baselib.network.interceptor;

import com.pudutech.disinfect.baselib.base.KtxKt;
import com.pudutech.disinfect.baselib.network.NetWorkUtil;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* compiled from: CacheInterceptor.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\u0004¨\u0006\f"}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/network/interceptor/CacheInterceptor;", "Lokhttp3/Interceptor;", "day", "", "(I)V", "getDay", "()I", "setDay", "intercept", "Lokhttp3/Response;", "chain", "Lokhttp3/Interceptor$Chain;", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class CacheInterceptor implements Interceptor {
    private int day;

    public CacheInterceptor() {
        this(0, 1, null);
    }

    public CacheInterceptor(int i) {
        this.day = i;
    }

    public /* synthetic */ CacheInterceptor(int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? 7 : i);
    }

    public final int getDay() {
        return this.day;
    }

    public final void setDay(int i) {
        this.day = i;
    }

    @Override // okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) {
        Intrinsics.checkParameterIsNotNull(chain, "chain");
        Request request = chain.request();
        if (!NetWorkUtil.INSTANCE.isNetworkAvailable(KtxKt.getAppContext())) {
            request = request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build();
        }
        Response proceed = chain.proceed(request);
        if (!NetWorkUtil.INSTANCE.isNetworkAvailable(KtxKt.getAppContext())) {
            proceed.newBuilder().removeHeader("Pragma").header("Cache-Control", "public,max-age=3600").build();
        } else {
            int i = this.day * 86400;
            proceed.newBuilder().removeHeader("Pragma").header("Cache-Control", "public, only-if-cached,max-stale=" + i).build();
        }
        return proceed;
    }
}
