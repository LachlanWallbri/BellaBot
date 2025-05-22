package com.pudutech.disinfect.baselib.network.req;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* compiled from: RobotMapReq.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u000e\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\f\"\u0004\b\u0011\u0010\u000eR\u001a\u0010\u0012\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\f\"\u0004\b\u0014\u0010\u000eR\u001a\u0010\u0015\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\f\"\u0004\b\u0017\u0010\u000e¨\u0006\u0018"}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/network/req/RobotMapReq;", "", "()V", "lv", "", "getLv", "()I", "setLv", "(I)V", "mac", "", "getMac", "()Ljava/lang/String;", "setMac", "(Ljava/lang/String;)V", "md5", "getMd5", "setMd5", "name", "getName", "setName", "url", "getUrl", "setUrl", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class RobotMapReq {
    private int lv;
    private String name = "";
    private String url = "";
    private String md5 = "";
    private String mac = "";

    public final String getName() {
        return this.name;
    }

    public final void setName(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.name = str;
    }

    public final String getUrl() {
        return this.url;
    }

    public final void setUrl(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.url = str;
    }

    public final int getLv() {
        return this.lv;
    }

    public final void setLv(int i) {
        this.lv = i;
    }

    public final String getMd5() {
        return this.md5;
    }

    public final void setMd5(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.md5 = str;
    }

    public final String getMac() {
        return this.mac;
    }

    public final void setMac(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.mac = str;
    }
}
