package com.pudutech.pd_network.interceptor;

import com.google.gson.Gson;
import com.pudutech.pd_network.PdNetworkManager;
import com.pudutech.pd_network.bean.BaseResponse;
import com.pudutech.pd_network.log.NetWorkLog;
import java.nio.charset.Charset;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;

/* compiled from: AutoVerifyInterceptor.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\b\u001a\u0004\u0018\u00010\u00042\u0006\u0010\t\u001a\u00020\nH\u0002J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, m3961d2 = {"Lcom/pudutech/pd_network/interceptor/AutoVerifyInterceptor;", "Lokhttp3/Interceptor;", "()V", "ACCESS_TOKEN_KEY", "", "TAG", "gson", "Lcom/google/gson/Gson;", "getResponseBody", "responseBody", "Lokhttp3/ResponseBody;", "intercept", "Lokhttp3/Response;", "chain", "Lokhttp3/Interceptor$Chain;", "pd_network_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class AutoVerifyInterceptor implements Interceptor {
    private final String TAG = "AutoVerifyInterceptor";
    private final Gson gson = new Gson();
    private final String ACCESS_TOKEN_KEY = "Access-Token";

    @Override // okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) {
        Intrinsics.checkParameterIsNotNull(chain, "chain");
        HttpUrl url = chain.request().url();
        String str = url.scheme() + "://" + url.host();
        Response proceed = chain.proceed(chain.request().newBuilder().addHeader(this.ACCESS_TOKEN_KEY, PdNetworkManager.f10310INSTANCE.token(str)).build());
        if (proceed.code() == 200 && proceed.body() != null) {
            Request request = proceed.request();
            ResponseBody body = proceed.body();
            if (body == null) {
                Intrinsics.throwNpe();
            }
            String responseBody = getResponseBody(body);
            NetWorkLog.INSTANCE.mo3280i(this.TAG, "intercept > \n " + responseBody);
            try {
                BaseResponse baseResponse = (BaseResponse) this.gson.fromJson(responseBody, BaseResponse.class);
                if (baseResponse != null) {
                    if (baseResponse.isTokenInvalid()) {
                        if (str.length() > 0) {
                            NetWorkLog.INSTANCE.mo3280i(this.TAG, "token invalid url = " + request.url());
                            PdNetworkManager.f10310INSTANCE.refreshToken(str);
                            if (PdNetworkManager.f10310INSTANCE.token(str).length() > 0) {
                                return chain.proceed(chain.request().newBuilder().header(this.ACCESS_TOKEN_KEY, PdNetworkManager.f10310INSTANCE.token(str)).build());
                            }
                            NetWorkLog.INSTANCE.mo3280i(this.TAG, "refresh token error");
                        }
                    }
                }
            } catch (Exception unused) {
                NetWorkLog.INSTANCE.mo3279e(this.TAG, "intercept > " + request.url());
            }
        }
        return proceed;
    }

    private final String getResponseBody(ResponseBody responseBody) {
        BufferedSource bufferedSource = responseBody.get$this_asResponseBody();
        bufferedSource.request(Long.MAX_VALUE);
        Buffer clone = bufferedSource.buffer().clone();
        Charset forName = Charset.forName("UTF-8");
        Intrinsics.checkExpressionValueIsNotNull(forName, "Charset.forName(\"UTF-8\")");
        return clone.readString(forName);
    }
}
