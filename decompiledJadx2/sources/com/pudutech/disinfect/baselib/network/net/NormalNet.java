package com.pudutech.disinfect.baselib.network.net;

import com.google.gson.GsonBuilder;
import com.pudutech.disinfect.baselib.ext.util.LogExtKt;
import com.pudutech.disinfect.baselib.network.BaseNetworkApi;
import com.pudutech.disinfect.baselib.network.interceptor.logging.LogInterceptor;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.security.cert.CertificateException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* compiled from: NormalNet.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \n2\u00020\u0001:\u0001\nB\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0016J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\u0007\u001a\u00020\tH\u0016¨\u0006\u000b"}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/network/net/NormalNet;", "Lcom/pudutech/disinfect/baselib/network/BaseNetworkApi;", "()V", "getSSLSocketFactory", "Ljavax/net/ssl/SSLSocketFactory;", "setHttpClientBuilder", "Lokhttp3/OkHttpClient$Builder;", "builder", "setRetrofitBuilder", "Lretrofit2/Retrofit$Builder;", "Companion", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class NormalNet extends BaseNetworkApi {
    private static final String TAG = "NormalNet";

    @Override // com.pudutech.disinfect.baselib.network.BaseNetworkApi
    public OkHttpClient.Builder setHttpClientBuilder(OkHttpClient.Builder builder) {
        Intrinsics.checkParameterIsNotNull(builder, "builder");
        LogExtKt.logD$default("setHttpClientBuilder", null, 1, null);
        builder.addInterceptor(new LogInterceptor());
        builder.connectTimeout(10L, TimeUnit.SECONDS);
        builder.readTimeout(5L, TimeUnit.SECONDS);
        builder.writeTimeout(5L, TimeUnit.SECONDS);
        return builder;
    }

    public final SSLSocketFactory getSSLSocketFactory() throws Exception {
        TrustManager[] trustManagerArr = {new X509TrustManager() { // from class: com.pudutech.disinfect.baselib.network.net.NormalNet$getSSLSocketFactory$trustAllCerts$1
            @Override // javax.net.ssl.X509TrustManager
            public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                Intrinsics.checkParameterIsNotNull(chain, "chain");
                Intrinsics.checkParameterIsNotNull(authType, "authType");
            }

            @Override // javax.net.ssl.X509TrustManager
            public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                Intrinsics.checkParameterIsNotNull(chain, "chain");
                Intrinsics.checkParameterIsNotNull(authType, "authType");
            }

            @Override // javax.net.ssl.X509TrustManager
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }
        }};
        SSLContext sSLContext = SSLContext.getInstance("TLS");
        Intrinsics.checkExpressionValueIsNotNull(sSLContext, "SSLContext.getInstance(\"TLS\")");
        sSLContext.init(null, trustManagerArr, new SecureRandom());
        return sSLContext.getSocketFactory();
    }

    @Override // com.pudutech.disinfect.baselib.network.BaseNetworkApi
    public Retrofit.Builder setRetrofitBuilder(Retrofit.Builder builder) {
        Intrinsics.checkParameterIsNotNull(builder, "builder");
        builder.addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()));
        return builder;
    }
}
