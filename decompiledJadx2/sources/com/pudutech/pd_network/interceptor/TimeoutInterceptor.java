package com.pudutech.pd_network.interceptor;

import android.text.TextUtils;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/* compiled from: TimeoutInterceptor.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u0000 \u00072\u00020\u0001:\u0001\u0007B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\b"}, m3961d2 = {"Lcom/pudutech/pd_network/interceptor/TimeoutInterceptor;", "Lokhttp3/Interceptor;", "()V", "intercept", "Lokhttp3/Response;", "chain", "Lokhttp3/Interceptor$Chain;", "Companion", "pd_network_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class TimeoutInterceptor implements Interceptor {
    public static final String CONNECT_TIMEOUT = "CONNECT_TIMEOUT";
    public static final String READ_TIMEOUT = "READ_TIMEOUT";
    public static final String WRITE_TIMEOUT = "WRITE_TIMEOUT";

    @Override // okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) {
        Intrinsics.checkParameterIsNotNull(chain, "chain");
        Request request = chain.request();
        int connectTimeoutMillis = chain.connectTimeoutMillis();
        int readTimeoutMillis = chain.readTimeoutMillis();
        int writeTimeoutMillis = chain.writeTimeoutMillis();
        String header = request.header(CONNECT_TIMEOUT);
        String header2 = request.header(READ_TIMEOUT);
        String header3 = request.header(WRITE_TIMEOUT);
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
        newBuilder.removeHeader(CONNECT_TIMEOUT);
        newBuilder.removeHeader(READ_TIMEOUT);
        newBuilder.removeHeader(WRITE_TIMEOUT);
        return chain.withConnectTimeout(connectTimeoutMillis, TimeUnit.MILLISECONDS).withReadTimeout(readTimeoutMillis, TimeUnit.MILLISECONDS).withWriteTimeout(writeTimeoutMillis, TimeUnit.MILLISECONDS).proceed(newBuilder.build());
    }
}
