package com.pudutech.disinfect.baselib.network;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import me.jessyan.retrofiturlmanager.RetrofitUrlManager;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* compiled from: BaseNetworkApi.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b&\u0018\u0000 \u00192\u00020\u0001:\u0001\u0019B\u0005¢\u0006\u0002\u0010\u0002J'\u0010\f\u001a\u0002H\r\"\u0004\b\u0000\u0010\r2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u0002H\r0\u000f2\u0006\u0010\u0010\u001a\u00020\u0011¢\u0006\u0002\u0010\u0012J\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0014H&J\u0010\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0015\u001a\u00020\u0017H&J\u000e\u0010\u0006\u001a\u00020\u00002\u0006\u0010\u0018\u001a\u00020\u0004R\u001a\u0010\u0003\u001a\u00020\u0004X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0003\u0010\u0005\"\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\t8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000b¨\u0006\u001a"}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/network/BaseNetworkApi;", "", "()V", "isTest", "", "()Z", "setTest", "(Z)V", "okHttpClient", "Lokhttp3/OkHttpClient;", "getOkHttpClient", "()Lokhttp3/OkHttpClient;", "getApi", ExifInterface.GPS_DIRECTION_TRUE, "serviceClass", "Ljava/lang/Class;", "baseUrl", "", "(Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/Object;", "setHttpClientBuilder", "Lokhttp3/OkHttpClient$Builder;", "builder", "setRetrofitBuilder", "Lretrofit2/Retrofit$Builder;", "boolean", "Companion", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public abstract class BaseNetworkApi {
    private static final String TAG = "BaseNetworkApi";
    private boolean isTest;

    public abstract OkHttpClient.Builder setHttpClientBuilder(OkHttpClient.Builder builder);

    public abstract Retrofit.Builder setRetrofitBuilder(Retrofit.Builder builder);

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: isTest, reason: from getter */
    public final boolean getIsTest() {
        return this.isTest;
    }

    /* renamed from: setTest, reason: collision with other method in class */
    protected final void m4336setTest(boolean z) {
        this.isTest = z;
    }

    public final <T> T getApi(Class<T> serviceClass, String baseUrl) {
        Intrinsics.checkParameterIsNotNull(serviceClass, "serviceClass");
        Intrinsics.checkParameterIsNotNull(baseUrl, "baseUrl");
        Retrofit.Builder retrofitBuilder = new Retrofit.Builder().baseUrl(baseUrl).client(getOkHttpClient());
        Intrinsics.checkExpressionValueIsNotNull(retrofitBuilder, "retrofitBuilder");
        return (T) setRetrofitBuilder(retrofitBuilder).build().create(serviceClass);
    }

    public final BaseNetworkApi setTest(boolean r1) {
        this.isTest = r1;
        return this;
    }

    private final OkHttpClient getOkHttpClient() {
        OkHttpClient.Builder builder = RetrofitUrlManager.getInstance().with(new OkHttpClient.Builder());
        Intrinsics.checkExpressionValueIsNotNull(builder, "builder");
        return setHttpClientBuilder(builder).build();
    }
}
