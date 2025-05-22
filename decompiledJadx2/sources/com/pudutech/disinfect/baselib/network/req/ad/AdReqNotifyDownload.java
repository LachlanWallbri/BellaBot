package com.pudutech.disinfect.baselib.network.req.ad;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AdReqNotifyDownload.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u000b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u000f\u001a\u00020\u0003H\u0016R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u0010"}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/network/req/ad/AdReqNotifyDownload;", "", "network_type", "", "download", "", "(Ljava/lang/String;I)V", "getDownload", "()I", "setDownload", "(I)V", "getNetwork_type", "()Ljava/lang/String;", "setNetwork_type", "(Ljava/lang/String;)V", "toString", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class AdReqNotifyDownload {
    private int download;
    private String network_type;

    public AdReqNotifyDownload(String network_type, int i) {
        Intrinsics.checkParameterIsNotNull(network_type, "network_type");
        this.network_type = network_type;
        this.download = i;
    }

    public final int getDownload() {
        return this.download;
    }

    public final String getNetwork_type() {
        return this.network_type;
    }

    public final void setDownload(int i) {
        this.download = i;
    }

    public final void setNetwork_type(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.network_type = str;
    }

    public String toString() {
        return "AdReqNotifyDownload(network_type = '" + this.network_type + "',download = '" + this.download + "')";
    }
}
