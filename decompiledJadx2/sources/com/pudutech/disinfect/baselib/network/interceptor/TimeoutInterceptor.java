package com.pudutech.disinfect.baselib.network.interceptor;

import android.text.TextUtils;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* compiled from: TimeoutInterceptor.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016R\u0014\u0010\u0003\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006R\u0014\u0010\t\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0006¨\u0006\u000f"}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/network/interceptor/TimeoutInterceptor;", "Lokhttp3/Interceptor;", "()V", com.pudutech.pd_network.interceptor.TimeoutInterceptor.CONNECT_TIMEOUT, "", "getCONNECT_TIMEOUT", "()Ljava/lang/String;", com.pudutech.pd_network.interceptor.TimeoutInterceptor.READ_TIMEOUT, "getREAD_TIMEOUT", com.pudutech.pd_network.interceptor.TimeoutInterceptor.WRITE_TIMEOUT, "getWRITE_TIMEOUT", "intercept", "Lokhttp3/Response;", "chain", "Lokhttp3/Interceptor$Chain;", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class TimeoutInterceptor implements Interceptor {
    private final String CONNECT_TIMEOUT = com.pudutech.pd_network.interceptor.TimeoutInterceptor.CONNECT_TIMEOUT;
    private final String READ_TIMEOUT = com.pudutech.pd_network.interceptor.TimeoutInterceptor.READ_TIMEOUT;
    private final String WRITE_TIMEOUT = com.pudutech.pd_network.interceptor.TimeoutInterceptor.WRITE_TIMEOUT;

    public final String getCONNECT_TIMEOUT() {
        return this.CONNECT_TIMEOUT;
    }

    public final String getREAD_TIMEOUT() {
        return this.READ_TIMEOUT;
    }

    public final String getWRITE_TIMEOUT() {
        return this.WRITE_TIMEOUT;
    }

    @Override // okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) {
        Intrinsics.checkParameterIsNotNull(chain, "chain");
        Request request = chain.request();
        int connectTimeoutMillis = chain.connectTimeoutMillis();
        int readTimeoutMillis = chain.readTimeoutMillis();
        int writeTimeoutMillis = chain.writeTimeoutMillis();
        String header = request.header(this.CONNECT_TIMEOUT);
        String header2 = request.header(this.READ_TIMEOUT);
        String header3 = request.header(this.WRITE_TIMEOUT);
        if (!TextUtils.isEmpty(header)) {
            if (header == null) {
                Intrinsics.throwNpe();
            }
            Integer valueOf = Integer.valueOf(header);
            Intrinsics.checkExpressionValueIsNotNull(valueOf, "Integer.valueOf(connectNew!!)");
            connectTimeoutMillis = valueOf.intValue();
        }
        if (!TextUtils.isEmpty(header2)) {
            if (header2 == null) {
                Intrinsics.throwNpe();
            }
            Integer valueOf2 = Integer.valueOf(header2);
            Intrinsics.checkExpressionValueIsNotNull(valueOf2, "Integer.valueOf(readNew!!)");
            readTimeoutMillis = valueOf2.intValue();
        }
        if (!TextUtils.isEmpty(header3)) {
            if (header3 == null) {
                Intrinsics.throwNpe();
            }
            Integer valueOf3 = Integer.valueOf(header3);
            Intrinsics.checkExpressionValueIsNotNull(valueOf3, "Integer.valueOf(writeNew!!)");
            writeTimeoutMillis = valueOf3.intValue();
        }
        Request.Builder newBuilder = request.newBuilder();
        newBuilder.removeHeader(this.CONNECT_TIMEOUT);
        newBuilder.removeHeader(this.READ_TIMEOUT);
        newBuilder.removeHeader(this.WRITE_TIMEOUT);
        return chain.withConnectTimeout(connectTimeoutMillis, TimeUnit.MILLISECONDS).withReadTimeout(readTimeoutMillis, TimeUnit.MILLISECONDS).withWriteTimeout(writeTimeoutMillis, TimeUnit.MILLISECONDS).proceed(newBuilder.build());
    }
}
