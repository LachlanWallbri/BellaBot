package com.pudutech.pd_network.bean;

import com.google.gson.GsonBuilder;
import com.pudutech.pd_network.interceptor.AutoSwitchHostInterceptor;
import com.pudutech.pd_network.interceptor.AutoVerifyInterceptor;
import com.pudutech.pd_network.interceptor.HandlerExceptionInterceptor;
import com.pudutech.pd_network.interceptor.TimeoutInterceptor;
import com.pudutech.pd_network.interceptor.logging.LogInterceptor;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Interceptor;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/* compiled from: config.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0014\u0010\u0012\u001a\u00020\u00132\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0015J\u0014\u0010\u0016\u001a\u00020\u00132\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0015J\u000e\u0010\u0017\u001a\u00020\u00132\u0006\u0010\u0017\u001a\u00020\u0018J\u0014\u0010\u0003\u001a\u00020\u00132\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00050\u0015J\b\u0010\u0019\u001a\u00020\u001aH\u0016R \u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR \u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0004X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\u0007\"\u0004\b\r\u0010\tR \u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0004X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0007\"\u0004\b\u0011\u0010\t¨\u0006\u001b"}, m3961d2 = {"Lcom/pudutech/pd_network/bean/PdNetworkClientBuilder;", "", "()V", "interceptor", "", "Lokhttp3/Interceptor;", "getInterceptor$pd_network_release", "()Ljava/util/List;", "setInterceptor$pd_network_release", "(Ljava/util/List;)V", "mCallAdapterFactory", "Lretrofit2/CallAdapter$Factory;", "getMCallAdapterFactory$pd_network_release", "setMCallAdapterFactory$pd_network_release", "mConverterFactory", "Lretrofit2/Converter$Factory;", "getMConverterFactory$pd_network_release", "setMConverterFactory$pd_network_release", "callAdapterFactory", "", "list", "", "converterFactory", "handlerException", "", "toString", "", "pd_network_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class PdNetworkClientBuilder {
    private List<Interceptor> interceptor = CollectionsKt.mutableListOf(new HandlerExceptionInterceptor(), new AutoSwitchHostInterceptor(), new AutoVerifyInterceptor(), new TimeoutInterceptor(), new LogInterceptor());
    private List<CallAdapter.Factory> mCallAdapterFactory;
    private List<Converter.Factory> mConverterFactory;

    public PdNetworkClientBuilder() {
        GsonConverterFactory create = GsonConverterFactory.create(new GsonBuilder().create());
        Intrinsics.checkExpressionValueIsNotNull(create, "GsonConverterFactory.cre…e(GsonBuilder().create())");
        this.mConverterFactory = CollectionsKt.mutableListOf(create);
        RxJava2CallAdapterFactory create2 = RxJava2CallAdapterFactory.create();
        Intrinsics.checkExpressionValueIsNotNull(create2, "RxJava2CallAdapterFactory.create()");
        this.mCallAdapterFactory = CollectionsKt.mutableListOf(create2);
    }

    public final List<Interceptor> getInterceptor$pd_network_release() {
        return this.interceptor;
    }

    public final void setInterceptor$pd_network_release(List<Interceptor> list) {
        Intrinsics.checkParameterIsNotNull(list, "<set-?>");
        this.interceptor = list;
    }

    public final List<Converter.Factory> getMConverterFactory$pd_network_release() {
        return this.mConverterFactory;
    }

    public final void setMConverterFactory$pd_network_release(List<Converter.Factory> list) {
        Intrinsics.checkParameterIsNotNull(list, "<set-?>");
        this.mConverterFactory = list;
    }

    public final List<CallAdapter.Factory> getMCallAdapterFactory$pd_network_release() {
        return this.mCallAdapterFactory;
    }

    public final void setMCallAdapterFactory$pd_network_release(List<CallAdapter.Factory> list) {
        Intrinsics.checkParameterIsNotNull(list, "<set-?>");
        this.mCallAdapterFactory = list;
    }

    public final void interceptor(List<? extends Interceptor> list) {
        Intrinsics.checkParameterIsNotNull(list, "list");
        this.interceptor.addAll(list);
    }

    public final void converterFactory(List<? extends Converter.Factory> list) {
        Intrinsics.checkParameterIsNotNull(list, "list");
        this.mConverterFactory.addAll(list);
    }

    public final void callAdapterFactory(List<? extends CallAdapter.Factory> list) {
        Intrinsics.checkParameterIsNotNull(list, "list");
        this.mCallAdapterFactory.addAll(list);
    }

    public final void handlerException(boolean handlerException) {
        Object obj;
        Iterator<T> it = this.interceptor.iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            } else {
                obj = it.next();
                if (((Interceptor) obj) instanceof HandlerExceptionInterceptor) {
                    break;
                }
            }
        }
        Interceptor interceptor = (Interceptor) obj;
        if (handlerException) {
            if (interceptor == null) {
                this.interceptor.add(0, new HandlerExceptionInterceptor());
            }
        } else if (interceptor != null) {
            this.interceptor.remove(interceptor);
        }
    }

    public String toString() {
        return "PdNetworkClientBuilder(interceptor=" + this.interceptor + ", mConverterFactory=" + this.mConverterFactory + ", mCallAdapterFactory=" + this.mCallAdapterFactory + ')';
    }
}
