package com.pudutech.leaselib.net;

import com.pudutech.base.Pdlog;
import com.pudutech.leaselib.LeaseSdk;
import com.pudutech.light_network.HttpConfig;
import com.pudutech.light_network.HttpsServiceType;
import com.pudutech.light_network.RetrofitManager;
import com.pudutech.light_network.intercepter.RequestInterceptor;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.HttpUrl;
import retrofit2.Retrofit;

/* compiled from: ServiceManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000/\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000*\u0001\u0010\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0014\u001a\u00020\u0015R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u001a\u0010\t\u001a\u00020\nX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u0010\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0011R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082.¢\u0006\u0002\n\u0000¨\u0006\u0016"}, m3961d2 = {"Lcom/pudutech/leaselib/net/ServiceManager;", "", "()V", "DEBUG_URL", "", "PRODUCT_URL", "TAG", "URT_TYPE_DEBUG", "URT_TYPE_PRODUCT", "apiService", "Lcom/pudutech/leaselib/net/ApiService;", "getApiService", "()Lcom/pudutech/leaselib/net/ApiService;", "setApiService", "(Lcom/pudutech/leaselib/net/ApiService;)V", "requestInterceptor", "com/pudutech/leaselib/net/ServiceManager$requestInterceptor$1", "Lcom/pudutech/leaselib/net/ServiceManager$requestInterceptor$1;", "retrofit", "Lretrofit2/Retrofit;", "init", "", "lib_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class ServiceManager {
    private static final String DEBUG_URL = "http://rmp-test.pudutech.com/api/v3/report";
    private static final String PRODUCT_URL = "https://rmp.pudutech.com/api/v3/report";
    private static final String TAG = "ServiceManager";
    public static final String URT_TYPE_DEBUG = "1";
    public static final String URT_TYPE_PRODUCT = "2";
    public static ApiService apiService;
    private static Retrofit retrofit;
    public static final ServiceManager INSTANCE = new ServiceManager();
    private static final ServiceManager$requestInterceptor$1 requestInterceptor = new RequestInterceptor() { // from class: com.pudutech.leaselib.net.ServiceManager$requestInterceptor$1
        @Override // com.pudutech.light_network.intercepter.RequestInterceptor
        public HttpUrl addMoreHost(int p0) {
            HttpUrl parse;
            Pdlog.m3273d("ServiceManager", "addMoreHost url type = " + p0);
            if (p0 == Integer.parseInt("1")) {
                parse = HttpUrl.parse("http://rmp-test.pudutech.com/api/v3/report");
                if (parse == null) {
                    Intrinsics.throwNpe();
                }
                Intrinsics.checkExpressionValueIsNotNull(parse, "HttpUrl.parse(DEBUG_URL)!!");
            } else {
                if (p0 != Integer.parseInt("2")) {
                    throw new IOException("url_type is not define");
                }
                parse = HttpUrl.parse("https://rmp.pudutech.com/api/v3/report");
                if (parse == null) {
                    Intrinsics.throwNpe();
                }
                Intrinsics.checkExpressionValueIsNotNull(parse, "HttpUrl.parse(PRODUCT_URL)!!");
            }
            return parse;
        }
    };

    private ServiceManager() {
    }

    public final ApiService getApiService() {
        ApiService apiService2 = apiService;
        if (apiService2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("apiService");
        }
        return apiService2;
    }

    public final void setApiService(ApiService apiService2) {
        Intrinsics.checkParameterIsNotNull(apiService2, "<set-?>");
        apiService = apiService2;
    }

    public final void init() {
        HttpConfig.INSTANCE.setConnectTimeout(10L);
        Retrofit retrofitManager = RetrofitManager.getInstance(LeaseSdk.INSTANCE.getContext(), HttpsServiceType.Cloud, null, requestInterceptor, true);
        Intrinsics.checkExpressionValueIsNotNull(retrofitManager, "RetrofitManager.getInsta…terceptor, true\n        )");
        retrofit = retrofitManager;
        Retrofit retrofit3 = retrofit;
        if (retrofit3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("retrofit");
        }
        Object create = retrofit3.create(ApiService.class);
        Intrinsics.checkExpressionValueIsNotNull(create, "retrofit.create(ApiService::class.java)");
        apiService = (ApiService) create;
        Object[] objArr = new Object[1];
        StringBuilder sb = new StringBuilder();
        sb.append("init finish ");
        ApiService apiService2 = apiService;
        if (apiService2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("apiService");
        }
        sb.append(apiService2);
        objArr[0] = sb.toString();
        Pdlog.m3273d(TAG, objArr);
    }
}
