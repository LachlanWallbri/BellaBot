package com.pudutech.bumblebee.presenter.net;

import android.content.Context;
import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.pudutech.bumblebee.presenter.net.req.AliyunIotSecretReq;
import com.pudutech.bumblebee.presenter.net.resp.AliyunIotSecretResp;
import com.pudutech.light_network.HttpConfig;
import com.pudutech.light_network.HttpsServiceType;
import com.pudutech.light_network.RetrofitManager;
import io.reactivex.Observable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import retrofit2.Retrofit;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Url;

/* compiled from: IotServerApiManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\u0018B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0013\u001a\u00020\u0012J\u000e\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u001a\u0010\u0005\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\f\"\u0004\b\r\u0010\u000eR\u000e\u0010\u000f\u001a\u00020\u0010X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0019"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/net/IotServerApiManager;", "", "()V", "IOT_SECRET_URL", "", "IOT_SECRET_URL_TEST", "getIOT_SECRET_URL_TEST", "()Ljava/lang/String;", "setIOT_SECRET_URL_TEST", "(Ljava/lang/String;)V", "isTest", "", "()Z", "setTest", "(Z)V", "retrofit", "Lretrofit2/Retrofit;", "serverApi", "Lcom/pudutech/bumblebee/presenter/net/IotServerApiManager$ServerApi;", "getServerApi", "init", "", "context", "Landroid/content/Context;", "ServerApi", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class IotServerApiManager {
    private static final String IOT_SECRET_URL = "https://iot.api.pudutech.com/iot/robot/register";
    private static boolean isTest;
    private static Retrofit retrofit;
    private static ServerApi serverApi;
    public static final IotServerApiManager INSTANCE = new IotServerApiManager();
    private static String IOT_SECRET_URL_TEST = "http://iot.api.pudutech.com:8010/iot/robot/register";

    private IotServerApiManager() {
    }

    public final String getIOT_SECRET_URL_TEST() {
        return IOT_SECRET_URL_TEST;
    }

    public final void setIOT_SECRET_URL_TEST(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        IOT_SECRET_URL_TEST = str;
    }

    public final boolean isTest() {
        return isTest;
    }

    public final void setTest(boolean z) {
        isTest = z;
    }

    public final void init(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        HttpConfig.INSTANCE.setConnectTimeout(10L);
        HttpConfig.INSTANCE.setReadTimeout(10L);
        HttpConfig.INSTANCE.setWriteTimeout(10L);
        Retrofit retrofitManager = RetrofitManager.getInstance(context, HttpsServiceType.IOTSR, null, null, true);
        Intrinsics.checkExpressionValueIsNotNull(retrofitManager, "RetrofitManager.getInsta…     null, true\n        )");
        retrofit = retrofitManager;
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

    /* compiled from: IotServerApiManager.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\"\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0003\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010\u0007\u001a\u00020\bH'¨\u0006\t"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/net/IotServerApiManager$ServerApi;", "", "getAliyunIOTSecret", "Lio/reactivex/Observable;", "Lcom/pudutech/bumblebee/presenter/net/resp/AliyunIotSecretResp;", "url", "", TmpConstant.KEY_IOT_PERFORMANCE_EVENT_REQ, "Lcom/pudutech/bumblebee/presenter/net/req/AliyunIotSecretReq;", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public interface ServerApi {
        @POST
        Observable<AliyunIotSecretResp> getAliyunIOTSecret(@Url String url, @Body AliyunIotSecretReq req);

        /* compiled from: IotServerApiManager.kt */
        @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
        /* loaded from: classes4.dex */
        public static final class DefaultImpls {
            public static /* synthetic */ Observable getAliyunIOTSecret$default(ServerApi serverApi, String str, AliyunIotSecretReq aliyunIotSecretReq, int i, Object obj) {
                if (obj != null) {
                    throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getAliyunIOTSecret");
                }
                if ((i & 1) != 0) {
                    str = IotServerApiManager.INSTANCE.isTest() ? IotServerApiManager.INSTANCE.getIOT_SECRET_URL_TEST() : IotServerApiManager.IOT_SECRET_URL;
                }
                return serverApi.getAliyunIOTSecret(str, aliyunIotSecretReq);
            }
        }
    }
}
