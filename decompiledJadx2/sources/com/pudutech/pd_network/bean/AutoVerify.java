package com.pudutech.pd_network.bean;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: config.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0019\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\u0006\u0010\f\u001a\u00020\rJ\u0006\u0010\u000e\u001a\u00020\u000fR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0007\"\u0004\b\u000b\u0010\t¨\u0006\u0010"}, m3961d2 = {"Lcom/pudutech/pd_network/bean/AutoVerify;", "", "mac", "", "pid", "(Ljava/lang/String;Ljava/lang/String;)V", "getMac", "()Ljava/lang/String;", "setMac", "(Ljava/lang/String;)V", "getPid", "setPid", "check", "", "toRequest", "Lcom/pudutech/pd_network/bean/InternalGetSecretReq;", "pd_network_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class AutoVerify {
    private String mac;
    private String pid;

    public AutoVerify() {
        this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
    }

    public AutoVerify(String mac, String pid) {
        Intrinsics.checkParameterIsNotNull(mac, "mac");
        Intrinsics.checkParameterIsNotNull(pid, "pid");
        this.mac = mac;
        this.pid = pid;
    }

    public /* synthetic */ AutoVerify(String str, String str2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? "" : str, (i & 2) != 0 ? "" : str2);
    }

    public final String getMac() {
        return this.mac;
    }

    public final String getPid() {
        return this.pid;
    }

    public final void setMac(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.mac = str;
    }

    public final void setPid(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.pid = str;
    }

    public final void check() {
        if (this.mac.length() == 0) {
            throw new IllegalAccessException("参数校验失败");
        }
    }

    public final InternalGetSecretReq toRequest() {
        return new InternalGetSecretReq(this.pid, this.mac, this.pid.length() == 0 ? 2 : 1);
    }
}
