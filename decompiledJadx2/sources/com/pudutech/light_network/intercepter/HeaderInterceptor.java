package com.pudutech.light_network.intercepter;

import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes5.dex
 */
/* loaded from: classes.dex */
public abstract class HeaderInterceptor implements Interceptor {
    public abstract Request.Builder addHeader(Request.Builder builder);

    @Override // okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request.Builder newBuilder = chain.request().newBuilder();
        addHeader(newBuilder);
        return chain.proceed(newBuilder.build());
    }
}
