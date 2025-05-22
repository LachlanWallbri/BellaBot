package com.pudutech.bumblebee.presenter.net.req;

import com.pudutech.bumblebee.business.robotsdk.SDK;
import com.pudutech.bumblebee.presenter.utils.WifiUtil;
import com.pudutech.disinfect.baselib.util.PackageUtil;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BaseReq.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0010\t\n\u0002\b\b\b\u0016\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\u0004X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\bR\u001a\u0010\f\u001a\u00020\u0004X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u0006\"\u0004\b\u000e\u0010\bR\u001a\u0010\u000f\u001a\u00020\u0010X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0015\u001a\u00020\u0004X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0006\"\u0004\b\u0017\u0010\b¨\u0006\u0018"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/net/req/BaseReq;", "", "()V", "hardver", "", "getHardver", "()Ljava/lang/String;", "setHardver", "(Ljava/lang/String;)V", "mac", "getMac", "setMac", "softver", "getSoftver", "setSoftver", "timestamp", "", "getTimestamp", "()J", "setTimestamp", "(J)V", "type", "getType", "setType", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public class BaseReq {
    private long timestamp;
    private String mac = "";
    private String type = "";
    private String softver = "";
    private String hardver = "";

    public BaseReq() {
        String mac = WifiUtil.INSTANCE.getMac();
        setMac(mac != null ? mac : "");
        setTimestamp(System.currentTimeMillis() / 1000);
        setSoftver(PackageUtil.INSTANCE.getSoftVersion());
        setHardver(SDK.INSTANCE.getHardwareVersion());
    }

    public String getMac() {
        return this.mac;
    }

    public void setMac(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.mac = str;
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(long j) {
        this.timestamp = j;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.type = str;
    }

    public String getSoftver() {
        return this.softver;
    }

    public void setSoftver(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.softver = str;
    }

    public String getHardver() {
        return this.hardver;
    }

    public void setHardver(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.hardver = str;
    }
}
