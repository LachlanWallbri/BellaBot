package com.pudutech.pd_network.interceptor;

import com.pudutech.pd_network.PdNetConfig;
import com.pudutech.pd_network.PdNetworkManager;
import com.pudutech.pd_network.bean.DeviceType;
import com.pudutech.pd_network.bean.GatewayName;
import com.pudutech.pd_network.log.NetWorkLog;
import com.pudutech.remotemaintenance.aliyun.config.OSSConfig;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/* compiled from: AutoSwitchHostInterceptor.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u0000 \n2\u00020\u0001:\u0001\nB\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, m3961d2 = {"Lcom/pudutech/pd_network/interceptor/AutoSwitchHostInterceptor;", "Lokhttp3/Interceptor;", "()V", "TAG", "", "kotlin.jvm.PlatformType", "intercept", "Lokhttp3/Response;", "chain", "Lokhttp3/Interceptor$Chain;", "Companion", "pd_network_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class AutoSwitchHostInterceptor implements Interceptor {
    public static final String CUSTOM_HOST = "CUSTOM_HOST";
    private final String TAG = getClass().getSimpleName();

    @Override // okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) {
        Intrinsics.checkParameterIsNotNull(chain, "chain");
        Request request = chain.request();
        String header = request.header(CUSTOM_HOST);
        String str = header;
        if (str == null || str.length() == 0) {
            String host = request.url().host();
            NetWorkLog netWorkLog = NetWorkLog.INSTANCE;
            String TAG = this.TAG;
            Intrinsics.checkExpressionValueIsNotNull(TAG, "TAG");
            netWorkLog.mo3280i(TAG, "newHost.isNullOrEmpty crtHost : " + host);
            return chain.proceed(request);
        }
        Request.Builder newBuilder = request.newBuilder();
        HttpUrl url = request.url();
        String host2 = url.host();
        GatewayName fromTag = GatewayName.INSTANCE.fromTag(header);
        if (PdNetConfig.INSTANCE.getDeviceType() == DeviceType.Robot && Intrinsics.areEqual(fromTag, GatewayName.DEVICE_BIZ.INSTANCE)) {
            String str2 = "switch host > error > deviceType&gateway Mismatch > " + PdNetConfig.INSTANCE.getDeviceType() + ' ' + fromTag + ' ' + url;
            NetWorkLog netWorkLog2 = NetWorkLog.INSTANCE;
            String TAG2 = this.TAG;
            Intrinsics.checkExpressionValueIsNotNull(TAG2, "TAG");
            netWorkLog2.mo3280i(TAG2, str2);
            throw new IOException(str2);
        }
        if (PdNetConfig.INSTANCE.getDeviceType() == DeviceType.Other && Intrinsics.areEqual(fromTag, GatewayName.ROBOT_BIZ.INSTANCE)) {
            String str3 = "switch host > error > deviceType&gateway Mismatch >  " + PdNetConfig.INSTANCE.getDeviceType() + ' ' + fromTag + ' ' + url;
            NetWorkLog netWorkLog3 = NetWorkLog.INSTANCE;
            String TAG3 = this.TAG;
            Intrinsics.checkExpressionValueIsNotNull(TAG3, "TAG");
            netWorkLog3.mo3280i(TAG3, str3);
            throw new IOException(str3);
        }
        if (fromTag != null) {
            NetWorkLog netWorkLog4 = NetWorkLog.INSTANCE;
            String TAG4 = this.TAG;
            Intrinsics.checkExpressionValueIsNotNull(TAG4, "TAG");
            netWorkLog4.mo3280i(TAG4, "switch host > start ");
            header = PdNetworkManager.f10310INSTANCE.getGatewayExecute(fromTag, true).getHost();
            NetWorkLog netWorkLog5 = NetWorkLog.INSTANCE;
            String TAG5 = this.TAG;
            Intrinsics.checkExpressionValueIsNotNull(TAG5, "TAG");
            netWorkLog5.mo3280i(TAG5, "switch host > end " + header + ' ');
        }
        String str4 = header;
        NetWorkLog netWorkLog6 = NetWorkLog.INSTANCE;
        String TAG6 = this.TAG;
        Intrinsics.checkExpressionValueIsNotNull(TAG6, "TAG");
        netWorkLog6.mo3280i(TAG6, "intercept > oldHost = " + host2 + " newHost = " + str4);
        newBuilder.url(StringsKt.replace$default(url.toString(), host2, StringsKt.replace$default(StringsKt.replace$default(str4, "http://", "", false, 4, (Object) null), OSSConfig.PREFIX_HTTPS, "", false, 4, (Object) null), false, 4, (Object) null));
        newBuilder.removeHeader(CUSTOM_HOST);
        return chain.proceed(newBuilder.build());
    }
}
