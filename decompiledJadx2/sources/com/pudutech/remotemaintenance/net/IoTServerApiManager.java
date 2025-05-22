package com.pudutech.remotemaintenance.net;

import android.content.Context;
import androidx.exifinterface.media.ExifInterface;
import com.pudutech.light_network.HttpConfig;
import com.pudutech.remotemaintenance.net.req.DeviceRegisterReq;
import com.pudutech.remotemaintenance.net.resp.DeviceRegisterResp;
import io.reactivex.Observable;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Url;

/* compiled from: IoTServerApiManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001:\u0002\u0015\u0016B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0010\u001a\u00020\u000fJ\u000e\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u001a\u0010\u0007\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\t\"\u0004\b\n\u0010\u000bR\u000e\u0010\f\u001a\u00020\rX\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0017"}, m3961d2 = {"Lcom/pudutech/remotemaintenance/net/IoTServerApiManager;", "", "()V", "DEBUG_URL", "", "DEVICE_REGISTER", "PRODUCT_URL", "isTestServer", "", "()Z", "setTestServer", "(Z)V", "retrofit", "Lretrofit2/Retrofit;", "serverApi", "Lcom/pudutech/remotemaintenance/net/IoTServerApiManager$ServerApi;", "getServerApi", "init", "", "context", "Landroid/content/Context;", "HttpResult", "ServerApi", "app_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class IoTServerApiManager {
    public static final String DEBUG_URL = "https://iot-test.pudutech.com";
    public static final String DEVICE_REGISTER = "/api/v1/device/register";
    public static final IoTServerApiManager INSTANCE = new IoTServerApiManager();
    public static final String PRODUCT_URL = "https://iot.pudutech.com";
    private static boolean isTestServer;
    private static Retrofit retrofit;
    private static ServerApi serverApi;

    private IoTServerApiManager() {
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
        OkHttpClient build = new OkHttpClient.Builder().connectTimeout(30L, TimeUnit.SECONDS).readTimeout(30L, TimeUnit.SECONDS).writeTimeout(30L, TimeUnit.SECONDS).build();
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(isTestServer ? DEBUG_URL : PRODUCT_URL);
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        builder.client(build);
        Retrofit build2 = builder.build();
        Intrinsics.checkExpressionValueIsNotNull(build2, "builder.build()");
        retrofit = build2;
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

    /* compiled from: IoTServerApiManager.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B#\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0004\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00018\u0000¢\u0006\u0002\u0010\bJ\t\u0010\u0012\u001a\u00020\u0004HÆ\u0003J\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u0010\u0010\u0014\u001a\u0004\u0018\u00018\u0000HÆ\u0003¢\u0006\u0002\u0010\nJ6\u0010\u0015\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00018\u0000HÆ\u0001¢\u0006\u0002\u0010\u0016J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u0002HÖ\u0003J\t\u0010\u001a\u001a\u00020\u0004HÖ\u0001J\t\u0010\u001b\u001a\u00020\u0006HÖ\u0001R\u0015\u0010\u0007\u001a\u0004\u0018\u00018\u0000¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\t\u0010\nR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011¨\u0006\u001c"}, m3961d2 = {"Lcom/pudutech/remotemaintenance/net/IoTServerApiManager$HttpResult;", ExifInterface.GPS_DIRECTION_TRUE, "", "ret", "", "message", "", "data", "(ILjava/lang/String;Ljava/lang/Object;)V", "getData", "()Ljava/lang/Object;", "Ljava/lang/Object;", "getMessage", "()Ljava/lang/String;", "getRet", "()I", "setRet", "(I)V", "component1", "component2", "component3", "copy", "(ILjava/lang/String;Ljava/lang/Object;)Lcom/pudutech/remotemaintenance/net/IoTServerApiManager$HttpResult;", "equals", "", "other", "hashCode", "toString", "app_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes.dex */
    public static final /* data */ class HttpResult<T> {
        private final T data;
        private final String message;
        private int ret;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ HttpResult copy$default(HttpResult httpResult, int i, String str, Object obj, int i2, Object obj2) {
            if ((i2 & 1) != 0) {
                i = httpResult.ret;
            }
            if ((i2 & 2) != 0) {
                str = httpResult.message;
            }
            if ((i2 & 4) != 0) {
                obj = httpResult.data;
            }
            return httpResult.copy(i, str, obj);
        }

        /* renamed from: component1, reason: from getter */
        public final int getRet() {
            return this.ret;
        }

        /* renamed from: component2, reason: from getter */
        public final String getMessage() {
            return this.message;
        }

        public final T component3() {
            return this.data;
        }

        public final HttpResult<T> copy(int ret, String message, T data) {
            return new HttpResult<>(ret, message, data);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof HttpResult)) {
                return false;
            }
            HttpResult httpResult = (HttpResult) other;
            return this.ret == httpResult.ret && Intrinsics.areEqual(this.message, httpResult.message) && Intrinsics.areEqual(this.data, httpResult.data);
        }

        public int hashCode() {
            int i = this.ret * 31;
            String str = this.message;
            int hashCode = (i + (str != null ? str.hashCode() : 0)) * 31;
            T t = this.data;
            return hashCode + (t != null ? t.hashCode() : 0);
        }

        public String toString() {
            return "HttpResult(ret=" + this.ret + ", message=" + this.message + ", data=" + this.data + ")";
        }

        public HttpResult(int i, String str, T t) {
            this.ret = i;
            this.message = str;
            this.data = t;
        }

        public /* synthetic */ HttpResult(int i, String str, Object obj, int i2, DefaultConstructorMarker defaultConstructorMarker) {
            this((i2 & 1) != 0 ? 0 : i, str, obj);
        }

        public final T getData() {
            return this.data;
        }

        public final String getMessage() {
            return this.message;
        }

        public final int getRet() {
            return this.ret;
        }

        public final void setRet(int i) {
            this.ret = i;
        }
    }

    /* compiled from: IoTServerApiManager.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J(\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\b\b\u0003\u0010\u0006\u001a\u00020\u00072\b\b\u0001\u0010\b\u001a\u00020\tH'¨\u0006\n"}, m3961d2 = {"Lcom/pudutech/remotemaintenance/net/IoTServerApiManager$ServerApi;", "", "deviceRegister", "Lio/reactivex/Observable;", "Lcom/pudutech/remotemaintenance/net/IoTServerApiManager$HttpResult;", "Lcom/pudutech/remotemaintenance/net/resp/DeviceRegisterResp;", "url", "", "reqData", "Lcom/pudutech/remotemaintenance/net/req/DeviceRegisterReq;", "app_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes.dex */
    public interface ServerApi {
        @POST
        Observable<HttpResult<DeviceRegisterResp>> deviceRegister(@Url String url, @Body DeviceRegisterReq reqData);

        /* compiled from: IoTServerApiManager.kt */
        @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
        /* loaded from: classes.dex */
        public static final class DefaultImpls {
            public static /* synthetic */ Observable deviceRegister$default(ServerApi serverApi, String str, DeviceRegisterReq deviceRegisterReq, int i, Object obj) {
                if (obj != null) {
                    throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: deviceRegister");
                }
                if ((i & 1) != 0) {
                    str = IoTServerApiManager.INSTANCE.isTestServer() ? "https://iot-test.pudutech.com/api/v1/device/register" : "https://iot.pudutech.com/api/v1/device/register";
                }
                return serverApi.deviceRegister(str, deviceRegisterReq);
            }
        }
    }
}
