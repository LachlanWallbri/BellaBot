package com.pudutech.pd_network.interceptor;

import com.pudutech.pd_network.PdNetworkType;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.HttpUrl;

/* compiled from: HandlerExceptionInterceptor.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u000b"}, m3961d2 = {"Lcom/pudutech/pd_network/interceptor/NetworkUnConnectException;", "Ljava/io/IOException;", "url", "Lokhttp3/HttpUrl;", "networkType", "Lcom/pudutech/pd_network/PdNetworkType;", "(Lokhttp3/HttpUrl;Lcom/pudutech/pd_network/PdNetworkType;)V", "getNetworkType", "()Lcom/pudutech/pd_network/PdNetworkType;", "getUrl", "()Lokhttp3/HttpUrl;", "pd_network_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class NetworkUnConnectException extends IOException {
    private final PdNetworkType networkType;
    private final HttpUrl url;

    public final PdNetworkType getNetworkType() {
        return this.networkType;
    }

    public final HttpUrl getUrl() {
        return this.url;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NetworkUnConnectException(HttpUrl url, PdNetworkType networkType) {
        super("url:" + url + " networkType:" + networkType + " un connect network");
        Intrinsics.checkParameterIsNotNull(url, "url");
        Intrinsics.checkParameterIsNotNull(networkType, "networkType");
        this.url = url;
        this.networkType = networkType;
    }
}
