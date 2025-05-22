package com.pudutech.bumblebee.presenter.net;

import android.content.Context;
import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.amazonaws.mobile.client.internal.oauth2.OAuth2Constants;
import com.google.api.client.json.Json;
import com.google.gson.GsonBuilder;
import com.iflytek.aiui.AIUIConstant;
import com.pudutech.bumblebee.presenter.net.req.AliyunIotSecretReq;
import com.pudutech.bumblebee.presenter.net.req.IotHostReq;
import com.pudutech.bumblebee.presenter.net.resp.AliyunIotSecretResp;
import com.pudutech.bumblebee.presenter.net.resp.HttpResult;
import com.pudutech.bumblebee.presenter.net.resp.IotRegionResp;
import com.pudutech.bumblebee.presenter.net.resp.TokenResp;
import com.pudutech.bumblebee.presenter.utils.logging.LogInterceptor;
import io.reactivex.Observable;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.OkHttpClient;
import org.bouncycastle.cms.CMSAttributeTableGenerator;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Url;

/* compiled from: IOTHubServerManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\u001cB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0016\u001a\u00020\u0015J\u000e\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0007\u001a\u00020\bJ\u0010\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001aH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.¢\u0006\u0002\n\u0000R\u001a\u0010\t\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\u000b\"\u0004\b\f\u0010\rR\u0014\u0010\u000e\u001a\u00020\u000f8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001d"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/net/IOTHubServerManager;", "", "()V", "DEBUG_URL", "", "PRODUCT_URL", "TAG", AIUIConstant.KEY_CONTENT, "Landroid/content/Context;", "isTest", "", "()Z", "setTest", "(Z)V", "okHttpClient", "Lokhttp3/OkHttpClient;", "getOkHttpClient", "()Lokhttp3/OkHttpClient;", "retrofit", "Lretrofit2/Retrofit;", "serverApi", "Lcom/pudutech/bumblebee/presenter/net/IOTHubServerManager$ServerApi;", "getServerApi", "init", "", "setHttpClientBuilder", "Lokhttp3/OkHttpClient$Builder;", "builder", "ServerApi", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class IOTHubServerManager {
    private static final String DEBUG_URL = "http://rop-hub-test.pudutech.com/api/v1/region/getRobotEndpoint";
    private static final String PRODUCT_URL = "https://rop-hub.pudutech.com/api/v1/region/getRobotEndpoint";
    private static Context content;
    private static boolean isTest;
    private static Retrofit retrofit;
    private static ServerApi serverApi;
    public static final IOTHubServerManager INSTANCE = new IOTHubServerManager();
    private static final String TAG = TAG;
    private static final String TAG = TAG;

    private IOTHubServerManager() {
    }

    public final boolean isTest() {
        return isTest;
    }

    public final void setTest(boolean z) {
        isTest = z;
    }

    private final OkHttpClient getOkHttpClient() {
        return setHttpClientBuilder(new OkHttpClient.Builder()).build();
    }

    public final void init(Context content2) {
        Intrinsics.checkParameterIsNotNull(content2, "content");
        content = content2;
        Retrofit build = new Retrofit.Builder().addCallAdapterFactory(RxJava2CallAdapterFactory.create()).addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create())).baseUrl("https://rop-hub.pudutech.com").client(getOkHttpClient()).build();
        Intrinsics.checkExpressionValueIsNotNull(build, "Retrofit.Builder().addCa…ent(okHttpClient).build()");
        retrofit = build;
    }

    public final ServerApi getServerApi() {
        if (serverApi == null) {
            Retrofit retrofit3 = retrofit;
            if (retrofit3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("retrofit");
            }
            serverApi = (ServerApi) retrofit3.create(ServerApi.class);
        }
        ServerApi serverApi2 = serverApi;
        if (serverApi2 == null) {
            Intrinsics.throwNpe();
        }
        return serverApi2;
    }

    private final OkHttpClient.Builder setHttpClientBuilder(OkHttpClient.Builder builder) {
        if (builder != null) {
            builder.addInterceptor(new LogInterceptor());
            builder.connectTimeout(10L, TimeUnit.SECONDS);
            builder.readTimeout(10L, TimeUnit.SECONDS);
            builder.writeTimeout(10L, TimeUnit.SECONDS);
        }
        return builder;
    }

    /* compiled from: IOTHubServerManager.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J6\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0003\u0010\u0007\u001a\u00020\u00062\b\b\u0003\u0010\b\u001a\u00020\u00062\b\b\u0003\u0010\t\u001a\u00020\u0006H'J<\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010\r\u001a\u00020\u000e2\b\b\u0001\u0010\u000f\u001a\u00020\u00062\b\b\u0003\u0010\u0010\u001a\u00020\u0006H'J(\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u000b0\u00032\b\b\u0003\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010\u0013\u001a\u00020\u0014H'¨\u0006\u0015"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/net/IOTHubServerManager$ServerApi;", "", "genToken", "Lio/reactivex/Observable;", "Lcom/pudutech/bumblebee/presenter/net/resp/TokenResp;", "url", "", OAuth2Constants.GRANT_TYPE, "client_id", "client_secret", "getAliyunIOTSecret", "Lcom/pudutech/bumblebee/presenter/net/resp/HttpResult;", "Lcom/pudutech/bumblebee/presenter/net/resp/AliyunIotSecretResp;", TmpConstant.KEY_IOT_PERFORMANCE_EVENT_REQ, "Lcom/pudutech/bumblebee/presenter/net/req/AliyunIotSecretReq;", "token", CMSAttributeTableGenerator.CONTENT_TYPE, "getEdgeHost", "Lcom/pudutech/bumblebee/presenter/net/resp/IotRegionResp;", "reqData", "Lcom/pudutech/bumblebee/presenter/net/req/IotHostReq;", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public interface ServerApi {
        @FormUrlEncoded
        @POST
        Observable<TokenResp> genToken(@Url String url, @Field("grant_type") String grant_type, @Field("client_id") String client_id, @Field("client_secret") String client_secret);

        @POST
        Observable<HttpResult<AliyunIotSecretResp>> getAliyunIOTSecret(@Url String url, @Body AliyunIotSecretReq req, @Header("Authorization") String token, @Header("Content-Type") String contentType);

        @POST
        Observable<HttpResult<IotRegionResp>> getEdgeHost(@Url String url, @Body IotHostReq reqData);

        /* compiled from: IOTHubServerManager.kt */
        @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
        /* loaded from: classes4.dex */
        public static final class DefaultImpls {
            public static /* synthetic */ Observable getEdgeHost$default(ServerApi serverApi, String str, IotHostReq iotHostReq, int i, Object obj) {
                if (obj != null) {
                    throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getEdgeHost");
                }
                if ((i & 1) != 0) {
                    str = IOTHubServerManager.INSTANCE.isTest() ? IOTHubServerManager.DEBUG_URL : IOTHubServerManager.PRODUCT_URL;
                }
                return serverApi.getEdgeHost(str, iotHostReq);
            }

            public static /* synthetic */ Observable getAliyunIOTSecret$default(ServerApi serverApi, String str, AliyunIotSecretReq aliyunIotSecretReq, String str2, String str3, int i, Object obj) {
                if (obj != null) {
                    throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getAliyunIOTSecret");
                }
                if ((i & 8) != 0) {
                    str3 = Json.MEDIA_TYPE;
                }
                return serverApi.getAliyunIOTSecret(str, aliyunIotSecretReq, str2, str3);
            }

            public static /* synthetic */ Observable genToken$default(ServerApi serverApi, String str, String str2, String str3, String str4, int i, Object obj) {
                if (obj != null) {
                    throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: genToken");
                }
                if ((i & 2) != 0) {
                    str2 = "client_credentials";
                }
                if ((i & 4) != 0) {
                    str3 = "thom";
                }
                if ((i & 8) != 0) {
                    str4 = "nightworld";
                }
                return serverApi.genToken(str, str2, str3, str4);
            }
        }
    }
}
