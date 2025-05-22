package com.pudutech.light_network.intercepter;

import android.text.TextUtils;
import java.io.IOException;
import java.util.List;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes5.dex
 */
/* loaded from: classes.dex */
public abstract class RequestInterceptor implements Interceptor {
    public abstract HttpUrl addMoreHost(int i);

    @Override // okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request request = chain.request();
        Request.Builder newBuilder = request.newBuilder();
        List<String> headers = request.headers("url_type");
        if (headers != null && headers.size() > 0) {
            newBuilder.removeHeader("url_type");
            String str = headers.get(0);
            HttpUrl addMoreHost = TextUtils.isEmpty(str) ? null : addMoreHost(Integer.parseInt(str));
            return chain.proceed(newBuilder.url(request.url().newBuilder().scheme(addMoreHost.scheme()).host(addMoreHost.host()).port(addMoreHost.port()).build()).build());
        }
        return chain.proceed(request);
    }
}
