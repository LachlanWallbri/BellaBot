package com.pudutech.peanut.presenter.net;

import android.content.Context;
import androidx.core.app.NotificationCompat;
import androidx.exifinterface.media.ExifInterface;
import com.pudutech.disinfect.baselib.network.response.VoicePackCloudResponse;
import com.pudutech.light_network.HttpConfig;
import com.pudutech.light_network.HttpsServiceType;
import com.pudutech.light_network.RetrofitManager;
import com.pudutech.peanut.presenter.net.req.AuthorizationPackReq;
import com.pudutech.peanut.presenter.net.req.FileUpdateReq;
import com.pudutech.peanut.presenter.net.req.RobotActiveReq;
import com.pudutech.peanut.presenter.net.req.RobotActiveStatusReq;
import com.pudutech.peanut.presenter.net.req.VoicePackInfoReq;
import com.pudutech.peanut.presenter.net.resp.AuthorizationPackResp;
import com.pudutech.peanut.presenter.net.resp.FileUpdateResp;
import com.pudutech.peanut.presenter.net.resp.RobotActiveResp;
import com.pudutech.peanut.presenter.net.resp.RobotActiveStatusResp;
import io.reactivex.Observable;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import retrofit2.Retrofit;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Url;

/* compiled from: ServerApiManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001:\u0002\u001e\u001fB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0019\u001a\u00020\u0018J\u000e\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u001a\u0010\u0010\u001a\u00020\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006 "}, m3961d2 = {"Lcom/pudutech/peanut/presenter/net/ServerApiManager;", "", "()V", "COUPON_LIST_URL", "", "DEBUG_URL", "DEBUG_URL_SOLICIT", "DEBUG_URL_SOLICIT_TEST", "DISH_URL", "MAP_LIST_URL", "PRODUCT_URL", "ROW_DEBUG_URL", "TAG", "UPLOAD_URL", "V3_URL", "V5_URL", "isTestServer", "", "()Z", "setTestServer", "(Z)V", "retrofit", "Lretrofit2/Retrofit;", "serverApi", "Lcom/pudutech/peanut/presenter/net/ServerApiManager$ServerApi;", "getServerApi", "init", "", "context", "Landroid/content/Context;", "HttpResult", "ServerApi", "presenter_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class ServerApiManager {
    private static final String COUPON_LIST_URL = "/api/robot/couponList";
    private static final String DEBUG_URL = "http://rmp-test.pudutech.com";
    private static final String DEBUG_URL_SOLICIT = "https://business.pudutech.com";
    private static final String DEBUG_URL_SOLICIT_TEST = "https://business-test.pudutech.com";
    private static final String DISH_URL = "/api/robot/dishesList";
    public static final ServerApiManager INSTANCE = new ServerApiManager();
    private static final String MAP_LIST_URL = "/api/v5/map/list";
    private static final String PRODUCT_URL = "https://rmp.pudutech.com";
    private static final String ROW_DEBUG_URL = "http://queue-call-test.pudutech.com";
    private static final String TAG = "ServerApiManager";
    private static final String UPLOAD_URL = "/api/v5/map/upload";
    private static final String V3_URL = "/api/v3/report";
    private static final String V5_URL = "/api/v5/report";
    private static boolean isTestServer;
    private static Retrofit retrofit;
    private static ServerApi serverApi;

    private ServerApiManager() {
    }

    public final boolean isTestServer() {
        return isTestServer;
    }

    public final void setTestServer(boolean z) {
        isTestServer = z;
    }

    public final void init(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        HttpConfig.INSTANCE.setConnectTimeout(10L);
        HttpConfig.INSTANCE.setReadTimeout(10L);
        HttpConfig.INSTANCE.setWriteTimeout(10L);
        Retrofit retrofitManager = RetrofitManager.getInstance(context, HttpsServiceType.Cloud, null, null, true);
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

    /* compiled from: ServerApiManager.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B#\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0004\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00018\u0000¢\u0006\u0002\u0010\bJ\t\u0010\u0012\u001a\u00020\u0004HÆ\u0003J\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u0010\u0010\u0014\u001a\u0004\u0018\u00018\u0000HÆ\u0003¢\u0006\u0002\u0010\u000eJ6\u0010\u0015\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00018\u0000HÆ\u0001¢\u0006\u0002\u0010\u0016J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u0002HÖ\u0003J\t\u0010\u001a\u001a\u00020\u0004HÖ\u0001J\t\u0010\u001b\u001a\u00020\u0006HÖ\u0001R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u0015\u0010\u0007\u001a\u0004\u0018\u00018\u0000¢\u0006\n\n\u0002\u0010\u000f\u001a\u0004\b\r\u0010\u000eR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u001c"}, m3961d2 = {"Lcom/pudutech/peanut/presenter/net/ServerApiManager$HttpResult;", ExifInterface.GPS_DIRECTION_TRUE, "", "code", "", NotificationCompat.CATEGORY_MESSAGE, "", "data", "(ILjava/lang/String;Ljava/lang/Object;)V", "getCode", "()I", "setCode", "(I)V", "getData", "()Ljava/lang/Object;", "Ljava/lang/Object;", "getMsg", "()Ljava/lang/String;", "component1", "component2", "component3", "copy", "(ILjava/lang/String;Ljava/lang/Object;)Lcom/pudutech/peanut/presenter/net/ServerApiManager$HttpResult;", "equals", "", "other", "hashCode", "toString", "presenter_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public static final /* data */ class HttpResult<T> {
        private int code;
        private final T data;
        private final String msg;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ HttpResult copy$default(HttpResult httpResult, int i, String str, Object obj, int i2, Object obj2) {
            if ((i2 & 1) != 0) {
                i = httpResult.code;
            }
            if ((i2 & 2) != 0) {
                str = httpResult.msg;
            }
            if ((i2 & 4) != 0) {
                obj = httpResult.data;
            }
            return httpResult.copy(i, str, obj);
        }

        /* renamed from: component1, reason: from getter */
        public final int getCode() {
            return this.code;
        }

        /* renamed from: component2, reason: from getter */
        public final String getMsg() {
            return this.msg;
        }

        public final T component3() {
            return this.data;
        }

        public final HttpResult<T> copy(int code, String msg, T data) {
            return new HttpResult<>(code, msg, data);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof HttpResult)) {
                return false;
            }
            HttpResult httpResult = (HttpResult) other;
            return this.code == httpResult.code && Intrinsics.areEqual(this.msg, httpResult.msg) && Intrinsics.areEqual(this.data, httpResult.data);
        }

        public int hashCode() {
            int i = this.code * 31;
            String str = this.msg;
            int hashCode = (i + (str != null ? str.hashCode() : 0)) * 31;
            T t = this.data;
            return hashCode + (t != null ? t.hashCode() : 0);
        }

        public String toString() {
            return "HttpResult(code=" + this.code + ", msg=" + this.msg + ", data=" + this.data + ")";
        }

        public HttpResult(int i, String str, T t) {
            this.code = i;
            this.msg = str;
            this.data = t;
        }

        public /* synthetic */ HttpResult(int i, String str, Object obj, int i2, DefaultConstructorMarker defaultConstructorMarker) {
            this((i2 & 1) != 0 ? 0 : i, str, obj);
        }

        public final int getCode() {
            return this.code;
        }

        public final T getData() {
            return this.data;
        }

        public final String getMsg() {
            return this.msg;
        }

        public final void setCode(int i) {
            this.code = i;
        }
    }

    /* compiled from: ServerApiManager.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J(\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\b\b\u0003\u0010\u0006\u001a\u00020\u00072\b\b\u0001\u0010\b\u001a\u00020\tH'J(\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\u00040\u00032\b\b\u0003\u0010\u0006\u001a\u00020\u00072\b\b\u0001\u0010\b\u001a\u00020\fH'J(\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\u00040\u00032\b\b\u0003\u0010\u0006\u001a\u00020\u00072\b\b\u0001\u0010\b\u001a\u00020\u000fH'J(\u0010\u0010\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u00040\u00032\b\b\u0003\u0010\u0006\u001a\u00020\u00072\b\b\u0001\u0010\b\u001a\u00020\u0012H'J(\u0010\u0013\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00140\u00040\u00032\b\b\u0003\u0010\u0006\u001a\u00020\u00072\b\b\u0001\u0010\b\u001a\u00020\u0015H'¨\u0006\u0016"}, m3961d2 = {"Lcom/pudutech/peanut/presenter/net/ServerApiManager$ServerApi;", "", "getAuthorizationPack", "Lio/reactivex/Observable;", "Lcom/pudutech/peanut/presenter/net/ServerApiManager$HttpResult;", "Lcom/pudutech/peanut/presenter/net/resp/AuthorizationPackResp;", "url", "", "reqData", "Lcom/pudutech/peanut/presenter/net/req/AuthorizationPackReq;", "getFileUpdate", "Lcom/pudutech/peanut/presenter/net/resp/FileUpdateResp;", "Lcom/pudutech/peanut/presenter/net/req/FileUpdateReq;", "getRobotActive", "Lcom/pudutech/peanut/presenter/net/resp/RobotActiveResp;", "Lcom/pudutech/peanut/presenter/net/req/RobotActiveReq;", "getRobotActiveStatus", "Lcom/pudutech/peanut/presenter/net/resp/RobotActiveStatusResp;", "Lcom/pudutech/peanut/presenter/net/req/RobotActiveStatusReq;", "getVoicePackInfo", "Lcom/pudutech/disinfect/baselib/network/response/VoicePackCloudResponse;", "Lcom/pudutech/peanut/presenter/net/req/VoicePackInfoReq;", "presenter_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public interface ServerApi {
        @POST
        Observable<HttpResult<AuthorizationPackResp>> getAuthorizationPack(@Url String url, @Body AuthorizationPackReq reqData);

        @POST
        Observable<HttpResult<FileUpdateResp>> getFileUpdate(@Url String url, @Body FileUpdateReq reqData);

        @POST
        Observable<HttpResult<RobotActiveResp>> getRobotActive(@Url String url, @Body RobotActiveReq reqData);

        @POST
        Observable<HttpResult<RobotActiveStatusResp>> getRobotActiveStatus(@Url String url, @Body RobotActiveStatusReq reqData);

        @POST
        Observable<HttpResult<VoicePackCloudResponse>> getVoicePackInfo(@Url String url, @Body VoicePackInfoReq reqData);

        /* compiled from: ServerApiManager.kt */
        @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
        /* loaded from: classes5.dex */
        public static final class DefaultImpls {
            public static /* synthetic */ Observable getFileUpdate$default(ServerApi serverApi, String str, FileUpdateReq fileUpdateReq, int i, Object obj) {
                if (obj != null) {
                    throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getFileUpdate");
                }
                if ((i & 1) != 0) {
                    str = ServerApiManager.INSTANCE.isTestServer() ? "http://rmp-test.pudutech.com/api/v3/report" : "https://rmp.pudutech.com/api/v3/report";
                }
                return serverApi.getFileUpdate(str, fileUpdateReq);
            }

            public static /* synthetic */ Observable getVoicePackInfo$default(ServerApi serverApi, String str, VoicePackInfoReq voicePackInfoReq, int i, Object obj) {
                if (obj != null) {
                    throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getVoicePackInfo");
                }
                if ((i & 1) != 0) {
                    str = ServerApiManager.INSTANCE.isTestServer() ? "http://rmp-test.pudutech.com/api/v3/report" : "https://rmp.pudutech.com/api/v3/report";
                }
                return serverApi.getVoicePackInfo(str, voicePackInfoReq);
            }

            public static /* synthetic */ Observable getAuthorizationPack$default(ServerApi serverApi, String str, AuthorizationPackReq authorizationPackReq, int i, Object obj) {
                if (obj != null) {
                    throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getAuthorizationPack");
                }
                if ((i & 1) != 0) {
                    str = ServerApiManager.INSTANCE.isTestServer() ? "http://rmp-test.pudutech.com/api/v3/report" : "https://rmp.pudutech.com/api/v3/report";
                }
                return serverApi.getAuthorizationPack(str, authorizationPackReq);
            }

            public static /* synthetic */ Observable getRobotActiveStatus$default(ServerApi serverApi, String str, RobotActiveStatusReq robotActiveStatusReq, int i, Object obj) {
                if (obj != null) {
                    throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getRobotActiveStatus");
                }
                if ((i & 1) != 0) {
                    str = ServerApiManager.INSTANCE.isTestServer() ? "http://rmp-test.pudutech.com/api/v5/report" : "https://rmp.pudutech.com/api/v5/report";
                }
                return serverApi.getRobotActiveStatus(str, robotActiveStatusReq);
            }

            public static /* synthetic */ Observable getRobotActive$default(ServerApi serverApi, String str, RobotActiveReq robotActiveReq, int i, Object obj) {
                if (obj != null) {
                    throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getRobotActive");
                }
                if ((i & 1) != 0) {
                    str = ServerApiManager.INSTANCE.isTestServer() ? "http://rmp-test.pudutech.com/api/v5/report" : "https://rmp.pudutech.com/api/v5/report";
                }
                return serverApi.getRobotActive(str, robotActiveReq);
            }
        }
    }
}
