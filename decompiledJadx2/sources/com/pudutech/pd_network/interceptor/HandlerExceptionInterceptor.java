package com.pudutech.pd_network.interceptor;

import android.util.Log;
import com.pudutech.pd_network.PdNetworkManager;
import com.pudutech.pd_network.PdNetworkType;
import com.pudutech.pd_network.log.NetWorkLog;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/* compiled from: HandlerExceptionInterceptor.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000¨\u0006\t"}, m3961d2 = {"Lcom/pudutech/pd_network/interceptor/HandlerExceptionInterceptor;", "Lokhttp3/Interceptor;", "()V", "TAG", "", "intercept", "Lokhttp3/Response;", "chain", "Lokhttp3/Interceptor$Chain;", "pd_network_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class HandlerExceptionInterceptor implements Interceptor {
    private final String TAG = "HandlerExceptionInterceptor";

    @Override // okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) {
        Intrinsics.checkParameterIsNotNull(chain, "chain");
        Request request = chain.request();
        PdNetworkType networkType = PdNetworkManager.f10310INSTANCE.networkType();
        if (!networkType.isConnect()) {
            throw new NetworkUnConnectException(request.url(), networkType);
        }
        try {
            return chain.proceed(chain.request());
        } catch (Exception e) {
            NetWorkLog netWorkLog = NetWorkLog.INSTANCE;
            String str = this.TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("catch and exchange exception > ");
            Exception exc = e;
            sb.append(Log.getStackTraceString(exc));
            netWorkLog.mo3280i(str, sb.toString());
            if (e instanceof IOException) {
                throw exc;
            }
            throw new IOException(exc);
        }
    }
}
