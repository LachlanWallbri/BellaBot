package com.pudutech.light_network;

import android.content.Context;
import com.pudutech.light_network.intercepter.HeaderInterceptor;
import com.pudutech.light_network.intercepter.RequestInterceptor;
import java.io.InputStream;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes5.dex
 */
/* loaded from: classes.dex */
public final class RetrofitManager {
    private static String TAG = "Https";
    private static final String baseUrl = "http://172.16.13.253:8081/";
    private static boolean sDebug = true;
    private static HeaderInterceptor sHeaderInterceptor;
    private static RequestInterceptor sRequestInterceptor;

    private static Retrofit create(Context context, HttpsServiceType httpsServiceType) {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(baseUrl);
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        if (httpsServiceType != HttpsServiceType.HTTP) {
            builder.client(OKHttpClient.getInstance4Https(context, httpsServiceType, sHeaderInterceptor, sRequestInterceptor, sDebug));
        } else {
            builder.client(OKHttpClient.getInstance(context, sHeaderInterceptor, sRequestInterceptor, sDebug));
        }
        return builder.build();
    }

    private static Retrofit create(Context context, InputStream inputStream) {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(baseUrl);
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        builder.client(OKHttpClient.getInstance4Https(context, HttpsServiceType.DEBUGSRV, sHeaderInterceptor, sRequestInterceptor, sDebug, inputStream));
        return builder.build();
    }

    public static Retrofit getInstance(Context context, HeaderInterceptor headerInterceptor, RequestInterceptor requestInterceptor, boolean z) {
        sHeaderInterceptor = headerInterceptor;
        sRequestInterceptor = requestInterceptor;
        sDebug = z;
        return create(context, HttpsServiceType.HTTP);
    }

    public static Retrofit getInstance(Context context, HttpsServiceType httpsServiceType, HeaderInterceptor headerInterceptor, RequestInterceptor requestInterceptor, boolean z) {
        sHeaderInterceptor = headerInterceptor;
        sRequestInterceptor = requestInterceptor;
        sDebug = z;
        return create(context, httpsServiceType);
    }

    public static Retrofit getInstanceDebug(Context context, HeaderInterceptor headerInterceptor, RequestInterceptor requestInterceptor, boolean z, InputStream inputStream) {
        sHeaderInterceptor = headerInterceptor;
        sRequestInterceptor = requestInterceptor;
        sDebug = z;
        return create(context, inputStream);
    }
}
