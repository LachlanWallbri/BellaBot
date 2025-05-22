package com.pudutech.pd_network;

import android.content.Context;
import com.pudutech.pd_network.bean.PdNetworkClientBuilder;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;

/* compiled from: client.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00008\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0016\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b\u001a\u0016\u0010\t\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fH\u0002\u001a\u0018\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0007\u001a\u00020\bH\u0002\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0012"}, m3961d2 = {"BASE_URL", "", "TAG", "normalClient", "Lretrofit2/Retrofit;", "context", "Landroid/content/Context;", "builder", "Lcom/pudutech/pd_network/bean/PdNetworkClientBuilder;", "okBuilder", "Lokhttp3/OkHttpClient$Builder;", "list", "", "Lokhttp3/Interceptor;", "retrofitBuilder", "Lretrofit2/Retrofit$Builder;", "client", "Lokhttp3/OkHttpClient;", "pd_network_release"}, m3962k = 2, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class ClientKt {
    private static final String BASE_URL = "https://www.pudutech.com";
    private static final String TAG = "InstanceClient";

    public static final Retrofit normalClient(Context context, PdNetworkClientBuilder builder) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(builder, "builder");
        Retrofit build = retrofitBuilder(okBuilder(builder.getInterceptor$pd_network_release()).build(), builder).build();
        Intrinsics.checkExpressionValueIsNotNull(build, "retrofitBuilder(this, builder).build()");
        Intrinsics.checkExpressionValueIsNotNull(build, "okBuilder(builder.interc…s, builder).build()\n    }");
        Intrinsics.checkExpressionValueIsNotNull(build, "run {\n    okBuilder(buil… builder).build()\n    }\n}");
        return build;
    }

    private static final Retrofit.Builder retrofitBuilder(OkHttpClient okHttpClient, PdNetworkClientBuilder pdNetworkClientBuilder) {
        Retrofit.Builder builder = new Retrofit.Builder();
        Iterator<T> it = pdNetworkClientBuilder.getMCallAdapterFactory$pd_network_release().iterator();
        while (it.hasNext()) {
            builder.addCallAdapterFactory((CallAdapter.Factory) it.next());
        }
        Iterator<T> it2 = pdNetworkClientBuilder.getMConverterFactory$pd_network_release().iterator();
        while (it2.hasNext()) {
            builder.addConverterFactory((Converter.Factory) it2.next());
        }
        builder.client(okHttpClient);
        builder.baseUrl(BASE_URL);
        return builder;
    }

    private static final OkHttpClient.Builder okBuilder(List<? extends Interceptor> list) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            builder.addInterceptor((Interceptor) it.next());
        }
        builder.connectTimeout(15L, TimeUnit.SECONDS);
        builder.readTimeout(10L, TimeUnit.SECONDS);
        builder.writeTimeout(10L, TimeUnit.SECONDS);
        return builder;
    }
}
